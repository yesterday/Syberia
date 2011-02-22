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

package yester.day.syberia.loaders.mesh;

import yester.day.syberia.loaders.common.AbstructChunkedLoader;
import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.system.Composition;
import yester.day.syberia.system.Mesh;
import yester.day.syberia.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static yester.day.syberia.utils.IOUtils.asInt;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 14 feb 2011  0:25:27
 */
public class MeshLoader<T extends SimpleMesh> extends AbstructChunkedLoader<T>{

    @Override
    @SuppressWarnings("unchecked")
    protected T createProduct(ObjectHeader header) {
        return (T) new SimpleMesh(header);
    }


    @Override
    protected void parseLabel(int label, T product, Composition c, InputStream stream) throws IOException {
        switch (label) {
            case 0x10000 :
                byte[] buf = new byte[4];
                IOUtils.readFully(stream, buf);
                int facesCount = asInt(buf);
                System.out.print(facesCount+ " faces"+IOUtils.pos(stream)+", ");
                Mesh.Face[] faces = new Mesh.Face[facesCount];
                for (int i = 0; i < facesCount; i++) faces[i] = new SimpleFace(stream);
                product.setFaces(faces);
        }
    }
}
