package yester.day.syberia.loaders.object3d;

import yester.day.syberia.loaders.common.AbstructChunkedLoader;
import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.loaders.mesh.SimpleVertex;
import yester.day.syberia.system.Composition;
import yester.day.syberia.system.Mesh;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static yester.day.syberia.utils.IOUtils.*;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 13 feb 2011  21:29:34
 */
public class Object3DLoader<R extends SimpleObject3D> extends AbstructChunkedLoader<R> {
    @Override
    @SuppressWarnings("unchecked")
    protected R createProduct(ObjectHeader header) {
        return (R) new SimpleObject3D(header);
        // я так и не понял зачем мне тут нужно кастовать, ведь все правильно!
        // 13 feb 2011 yesterday: теперь понял, ну тогда этот метод будет еще и сторожить систему типов
    }

    @Override
    protected void parseLabel(int label, R product, Composition c, InputStream stream) throws IOException {
        switch (label) {
            case 0x200000:
                byte[] mat = new byte[16 * 4];
                readFully(stream, mat);
                ByteBuffer buffer = ByteBuffer.wrap(mat).order(LITTLE_ENDIAN);
                for (int i = 0; i < 16; i++) {
                    System.out.print(buffer.getFloat() + " ");
                    if ((i + 1) % 4 == 0) System.out.println();
                }

                byte[] tmp = new byte[4];
                readFully(stream, tmp);



                int hzCount = asInt(tmp);
                byte[] hzBuf = new byte[hzCount * 4];
                readFully(stream, hzBuf);
                System.out.println("Связи (" + hzCount + ") с: ");

                buffer = ByteBuffer.wrap(hzBuf).order(LITTLE_ENDIAN);
                for (int i = 0; i < hzCount; i++) {
                    int p = buffer.getInt();
                    System.out.println(p + ": " + c.getObject(p));
                    readFully(stream, tmp);
                    if (asInt(tmp) != 0) System.out.println("Not NULL first word in children!");
                    readFully(stream, mat);
                }

                readFully(stream, tmp);
                int vertCount = asInt(tmp);
                System.out.println("Found " + vertCount + " vertices " + pos(stream));
                Mesh.Vertex[] vertices = new Mesh.Vertex[vertCount];
                for (int i = 0; i < vertCount; i++) {
                    readFully(stream, tmp);
//                    System.out.println("Vertex " + i + " is in " + asInt(tmp));
                    vertices[i] = new SimpleVertex(stream);
                    stream.skip(4 * ( (2 * asInt(tmp))));
                }
                product.setVertices(vertices);
                System.out.println("Done at "+pos(stream));

                break;
        }
    }

}


