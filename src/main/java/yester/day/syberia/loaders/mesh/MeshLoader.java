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
