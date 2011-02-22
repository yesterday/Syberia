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
