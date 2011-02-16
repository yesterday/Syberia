package yester.day.watermelon.qsp;

import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.Resolver;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundString;
import org.codehaus.preon.el.BindingException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.InflaterInputStream;

public class Composition {

    @BoundString(size = "8")
    String sign;

    @Bound
    int crc;

    @Bound
    int date;

    @Bound
    int plugin1;

    @Bound
    int plugin2;

    @Bound
    int flags;

    @Bound
    int compcsz;

    @Bound
    int objcsz;

    @Bound
    int objsz;

    @Bound
    int addpath;

    @Bound
    int componentsCount;

    @Bound
    int objectsCount;

    @Bound
    int zero;

    @Bound
    int version;

    @Bound
    int componentsSize;

    static Codec<ComponentList> cListCodec = Codecs.create(ComponentList.class);
    static Codec<Composition> codec = Codecs.create(Composition.class);
    private static final int HeaderSize = 64;

    Component[] components;

    static Composition read(InputStream stream, String path) throws IOException, DecodingException {
        byte[] bytes = new byte[HeaderSize];
        if (HeaderSize != stream.read(bytes)) throw error("Cannot read");
        Composition c = Codecs.decode(codec, bytes);
        if (!c.sign.equals("Nemo Fi\0")) throw error("Not a composition file");

        bytes = new byte[c.componentsSize + 4];
        stream.mark(4096 + c.compcsz);

        InputStream is =
                (c.componentsSize == c.compcsz)
                        ? stream
                        : new InflaterInputStream(stream);

        ByteBuffer.wrap(bytes).putInt(c.componentsCount);
//        if (c.componentsSize != is.read(bytes, 4, c.componentsSize)) throw error("Cannot read components");
        readFully(is, bytes, 4, c.componentsSize);

        ComponentList list = Codecs.decode(cListCodec, bytes);
        c.components = list.components;

        stream.reset();
        stream.skip(c.compcsz);
        is =
                (c.objcsz == c.objsz)
                        ? stream
                        : new InflaterInputStream(stream);
        int totalSize = c.componentsSize + HeaderSize;

        for (int i = 0, componentsLength = c.components.length; i < componentsLength; i++) {
            Component component = i == 0 ? null : c.components[i - 1];
            int size;
            String name;
            size = c.components[i].getOffset() - totalSize;
            name = component == null ? "00000_PARAMETEROPERATION" : componentFileName(component);
            copy(is, size, path + name);
            totalSize += size;
        }
        Component component = c.components[c.components.length - 1];
        int size = c.objsz - component.getOffset() + c.componentsSize + HeaderSize;
        String name = componentFileName(component);
        copy(is, size, path + name);

        return c;
    }

    static String componentFileName(Component component) {
        return String.format("%05x", component.getId()) +
                "_" + component.getData().replace(' ', '_').replace('/', '_') +
                "_" + CKType.resolve(component.getComponentType())
                ;
    }

    static void copy(InputStream is, int size, String name) throws IOException {
//        System.out.print("Writing " + name + " (" + size + " bytes)... ");
        byte[] b = new byte[size];
        Composition.readFully(is, b, 0, size);
        OutputStream os = new FileOutputStream(name);
        os.write(b);
        os.close();
//        System.out.println("done");
    }

    static RuntimeException error(String cause) {
        return new RuntimeException(cause);
    }


    @Override
    public String toString() {
        return "Composition{" +
                ", flags=" + Integer.toString(flags, 2) + "b" +
                ", compcsz=" + compcsz +
                ", objcsz=" + objcsz +
                ", objsz=" + objsz +
                ", addpath=" + addpath +
                ", components=" + componentsCount +
                ", objects=" + objectsCount +
                ", zero=" + zero +
                ", version=" + Integer.toString(version, 16) +
                ", compsz=" + componentsSize +
                "}\n" + Arrays.toString(components);
    }

    static class CompositionResolver implements Resolver {
        Composition c;

        CompositionResolver(Composition c) {
            this.c = c;
        }

        public Object get(String name) throws BindingException {
            System.err.println("Requested " + name);
            if ("componentsNumber".equals(name)) return c.componentsCount;
            return null;
        }

        public Resolver getOriginalResolver() {
            return null;
        }
    }

    public static int readFully(InputStream str, byte[] b, int offset, int size) throws IOException {

        int r, total = 0, len = size;

        while ((r = str.read(b, offset + total, len)) > 0) {
            total += r;
            len -= r;
        }

        return total;
    }
}
