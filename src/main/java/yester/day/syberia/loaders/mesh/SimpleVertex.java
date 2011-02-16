package yester.day.syberia.loaders.mesh;

import yester.day.syberia.system.Mesh;
import yester.day.syberia.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleVertex implements Mesh.Vertex{
   float x, y, z;

    public SimpleVertex(InputStream str) throws IOException {
        byte[] buf = new byte[12];
        IOUtils.readFully(str, buf);
        init(ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN));
    }


    public SimpleVertex(ByteBuffer buffer) {
        init(buffer);
    }

    void init(ByteBuffer buffer) {
        x = buffer.getFloat();
        z = buffer.getFloat();
        y = buffer.getFloat();
//       System.out.println(this);
    }

   @Override
   public String toString() {
       return f(x) + " " + f(y) + " " + f(z) + " ";
   }
   static float f(float f) {
   if(Math.abs(f) < .000001) return 0; else return f;
}
}
