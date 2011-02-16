package yester.day.watermelon.qsp.mesh;

import java.nio.ByteBuffer;

public class Face {
   short i[] = new short[3];


   Face(ByteBuffer buffer) {
//            System.out.println(String.format("%08x %08x\n", buffer.getInt(), buffer.getInt()));
//            if(true) return;

       this.i[0] = buffer.getShort();
       this.i[1] = buffer.getShort();
       this.i[2] = buffer.getShort();
       short t  = buffer.getShort();
//            if(t!=1) System.err.println("T=="+t);
//            System.out.println(Arrays.toString(i));
//            if (i[0] != t) System.err.println("Last and first vertex not matching!");
   }
}
