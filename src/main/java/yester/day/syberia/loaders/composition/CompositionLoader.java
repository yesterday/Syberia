/*
 * Copyright (C) 2011. Siberia Linux Port Team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package yester.day.syberia.loaders.composition;

import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;
import org.codehaus.preon.buffer.ByteOrder;
import yester.day.syberia.loaders.Loader;
import yester.day.syberia.system.Composition;
import yester.day.syberia.temp.CKType;
import yester.day.syberia.utils.IOUtils;
import yester.day.syberia.utils.RestrictedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.InflaterInputStream;

public class CompositionLoader {
    static Codec<ComponentList> cListCodec = Codecs.create(ComponentList.class);
    static Codec<CompositionHeader> codec = Codecs.create(CompositionHeader.class);
    private static final int HeaderSize = 64;

    protected Map<Integer, Loader> loaders = new HashMap<Integer, Loader>();
    protected Loader defaultLoader;

    public CompositionLoader(Loader defaultLoader) {
        this.defaultLoader = defaultLoader;
    }

    public void registerLoader(Integer componentType, Loader loader) {
        loaders.put(componentType, loader);
    }


    private ObjectHeader[] loadComponents(CompositionHeader c, InputStream stream) throws IOException, DecodingException {
        byte[] bytes = new byte[c.componentsSize + 4];
        stream.mark(4096 + c.compcsz);

        InputStream is = (c.componentsSize == c.compcsz) ? stream : new InflaterInputStream(stream);

        ByteBuffer.wrap(bytes).putInt(c.componentsCount);
        IOUtils.readFully(is, bytes, 4, c.componentsSize);

        ComponentList list = Codecs.decode(cListCodec, bytes);

        stream.reset();
        stream.skip(c.compcsz);
        return list.components;

    }


    public Composition read(InputStream stream) throws IOException, DecodingException {
        byte[] bytes = new byte[HeaderSize];
        if (HeaderSize != stream.read(bytes)) throw error("Cannot read");
        CompositionHeader c = Codecs.decode(codec, bytes);
        if (!c.sign.equals("Nemo Fi\0")) throw error("Not a composition file");

        ObjectHeader[] components = loadComponents(c, stream);

        InputStream is = (c.objcsz == c.objsz) ? stream : new InflaterInputStream(stream);

        int totalSize = c.componentsSize + HeaderSize;
        SimpleComposition composition = new SimpleComposition(components.length);

        is.skip(components[0].offset - totalSize);
        totalSize = components[0].offset;
        for (int i = 0, componentsLength = components.length; i < componentsLength; i++) {
            ObjectHeader component = components[i];
            int size = i == componentsLength - 1 ? totalSize : components[i + 1].offset - totalSize;

            RestrictedInputStream restris = new RestrictedInputStream(is, size);
            Loader l = loaders.get(component.componentType);
            if (l == null) l = defaultLoader;
            composition.put(i, l.load(component, restris, composition));
            restris.close();

            totalSize += size;
        }
        return composition;
    }

    static String componentFileName(ObjectHeader component) {
        return String.format("%05x", component.id) +
                "_" + component.name.replace(' ', '_').replace('/', '_') +
                "_" + CKType.resolve(component.componentType) + "(" + component.componentType + ")"
                ;
    }

    public static class ComponentList {
        @BoundNumber(byteOrder = ByteOrder.BigEndian)
        int componentsNumber;

        @BoundList(type = ObjectHeader.class, size = "componentsNumber")
        ObjectHeader[] components;
    }

    public static class CompositionHeader {
        @BoundString(size = "8")
        String sign;

        @Bound
        int date, crc, plugin1, plugin2, flags;

        @Bound
        int compcsz, objcsz, objsz, addpath;

        @Bound
        int componentsCount, objectsCount, zero, version, componentsSize;
    }


    static RuntimeException error(String cause) {
        return new RuntimeException(cause);
    }
}

