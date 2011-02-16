package yester.day.watermelon.qsp.mesh;

import yester.day.watermelon.qsp.Composition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 8 jan 2011  18:41:13
 */
public class MeshLoader {
    public static void main(String[] args) throws IOException {

        String file = args.length > 0 ? args[0] :"/work/me/Syberia/Work.blender/syb/" +
                "008ba_MESH_Kathe_Mesh"
//                "3075_MESH_Pyramid_Mesh"
//                "3055_MESH_Plane_1x1_Mesh"
//                "0005b_MESH__bullet_Mesh"
//                "3030_MESH_Cube_1x1x1_Mesh"
                ;
        InputStream is = new FileInputStream(file);

        byte[] data = new byte[4];
        is.read(data);
        int size = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getInt();
        data = new byte[size];
        if(size != Composition.readFully(is, data, 0, size)) {
            System.out.println("Incomplete file! must be "+size+ " bytes");
            System.exit(1);
        }
        ByteBuffer buff = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);


        int flags = buff.getInt();
        size = buff.getInt();


        List<Face> faces = new LinkedList<Face>();
        List<Vertex> vertexes = new ArrayList<Vertex>();


        while (buff.hasRemaining()) if (0xFFFFFFFF == buff.getInt()) break;



        while (buff.hasRemaining()) if (0x10000 == buff.getInt()) break;
        buff.getInt(); // 0xD usually
        int facesCount = buff.getInt();
        System.out.print(facesCount+ " faces"+pos(buff)+", ");

        for (int i = 0; i < facesCount; i++) faces.add(new Face(buff));




        while (buff.hasRemaining()) if (0x20000 == buff.getInt()) break;

        if (0 != buff.getInt()) { // always empty
            System.out.println("word after marker is not empty @ 0x" + Integer.toString(buff.position(), 16) + "!");
            System.exit(0);
        }

        int verticles = buff.getInt();
        System.out.print(verticles+" verticles"+pos(buff)+", ");
        buff.getInt(); //                \nm_SaveIDMax = " + buff.getInt() +
        buff.getInt(); //                "\nUKNWN = " + buff.getInt());

        int i = 0;
        try {
            for (; i < verticles; i++)  vertexes.add(new Vertex(buff));
        }catch(Exception e) {
            System.out.println("No more Data! Readed:"+i+" must be: "+verticles);
        }

        // по всей видимости после координат пикселей идут UV координаты их же
//        System.out.println("readed " + (verticles * 3) + " words.");
//        c = 0;
//        while (buff.hasRemaining()) {
//            c++;
//            int i = buff.getInt();
//            System.out.println(String.format("%08x", i) + " " +
//                    Integer.toString(i, 2) + " " +
//                    Float.intBitsToFloat(i));
//        }
//        System.out.println("Total: rest=" + c + " vertdata=" + (verticles * 3) + " total=" + (c + verticles * 3));
        System.out.println("writing: "+file+".raw");


        PrintStream stream = new PrintStream(file + ".raw");
        outer:
        for (Face face : faces) {
            StringBuffer buf = new StringBuffer();
            for (short idx : face.i){
//                System.out.println(vertexes.get(idx));
                if(idx >= vertexes.size()) continue outer;
                buf.append(vertexes.get(idx));
            }
//            System.out.println();
            stream.println(buf);
        }

        stream.close();
    }

    private static String pos(ByteBuffer buff) {
        return "(@x"+Integer.toString(buff.position()+4, 16)+")";
    }
}



