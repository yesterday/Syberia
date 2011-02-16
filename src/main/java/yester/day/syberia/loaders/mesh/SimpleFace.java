package yester.day.syberia.loaders.mesh;

import yester.day.syberia.system.Mesh;
import yester.day.syberia.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleFace implements Mesh.Face {
    int i[] = new int[3];

    SimpleFace(InputStream str) throws IOException {
        byte[] buf = new byte[8];
        IOUtils.readFully(str, buf);
        init(ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN));
    }

    private void init(ByteBuffer buffer) {
        this.i[0] = buffer.getShort();
        this.i[1] = buffer.getShort();
        this.i[2] = buffer.getShort();
        short t = buffer.getShort();
    }

    SimpleFace(ByteBuffer buffer) {
//            System.out.println(String.format("%08x %08x\n", buffer.getInt(), buffer.getInt()));
//            if(true) return;
        init(buffer);

//            if(t!=1) System.err.println("T=="+t);
//            System.out.println(Arrays.toString(i));
//            if (i[0] != t) System.err.println("Last and first vertex not matching!");
    }

    public int[] getVerticesNumber() {
        return i;
    }
}
