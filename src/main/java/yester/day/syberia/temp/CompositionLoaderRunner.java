package yester.day.syberia.temp;

import org.codehaus.preon.DecodingException;
import yester.day.syberia.loaders.Loader;
import yester.day.syberia.loaders.composition.CompositionLoader;
import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.loaders.mesh.MeshLoader;
import yester.day.syberia.loaders.object3d.Object3DLoader;
import yester.day.syberia.system.Composition;
import yester.day.syberia.system.Mesh;
import yester.day.syberia.system.Object3D;
import yester.day.syberia.system.SystemObject;

import java.io.*;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 8 jan 2011  12:04:12
 */
public class CompositionLoaderRunner {


    public static void main(String[] args) throws IOException, DecodingException {
        if (args.length < 1
                && false
                ) {
            System.out.println("Usage: java -jar unpack.jar FILE [DESTDIR]");
            System.exit(1);

        }

        String file =
                args.length < 1
//                ? "/home/day/Projects/Syberia/data/Reverse/ValReceptionInn.cmo"
        ? "/Projects/Syberia/data/Reverse/BaseCMO.cmo"
                : args[0];


//        String file = "/home/day/Projects/Syberia/data/_bullet.nmo";

        InputStream is = new FileInputStream(file);
        is = new BufferedInputStream(is);
        Loader nullLoader = new Loader() {
            public SystemObject load(ObjectHeader header, InputStream stream, Composition composition)
                    throws IOException {
                return header;
            }
        };



        CompositionLoader loader = new CompositionLoader(
//                new ObjectChunkDumper()
                nullLoader
        );
        loader.registerLoader(0x2A, new Object3DLoader());
        loader.registerLoader(0x20, new MeshLoader());

        Composition c = loader.read(is);
        System.out.println(c.getObjectByID(0x8ba));
        System.out.println(c.getObjectByID(0x8bb));

        Mesh kathe = (Mesh) c.getObjectByID(0x8ba);
        Object3D body = (Object3D) c.getObjectByID(0x8bb);
        Mesh.Vertex[] vertexes = body.getVertices();
        System.out.println("Verts size: "+vertexes.length);


        PrintStream stream = new PrintStream(file + ".raw");
        outer:
        for (Mesh.Face face : kathe.getFaces()) {
            StringBuffer buf = new StringBuffer();
            for (int idx : face.getVerticesNumber()){
//                System.out.println(vertexes.get(idx));
                if(idx >= vertexes.length) continue outer;
                buf.append(vertexes[idx]);
            }
//            System.out.println();
            stream.println(buf);
        }

        stream.close();

        System.exit(1);


        System.err.println("Total: "+Integer.toHexString(c.objectCount()));
//        System.err.println(c.getObject(0x2Be6));
//        System.exit(1);
        for (SystemObject object : c) {
            if(object == null) continue;
            System.err.println(object);
        }
    }
}
