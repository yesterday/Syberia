package yester.day.watermelon.qsp.mesh;

import java.nio.ByteBuffer;

public class Vertex {
   float x, y, z;

   Vertex(ByteBuffer buffer) {
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
