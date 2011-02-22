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
