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
