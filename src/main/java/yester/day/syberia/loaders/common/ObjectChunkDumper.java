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

package yester.day.syberia.loaders.common;

import yester.day.syberia.loaders.Loader;
import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.system.Composition;
import yester.day.syberia.system.SystemObject;
import yester.day.syberia.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 13 feb 2011  9:46:22
 */
public class ObjectChunkDumper implements Loader {

    public SystemObject load(ObjectHeader header, InputStream is, Composition composition) throws IOException {
        byte[] hdr = new byte[4 * 3];
        ByteBuffer bb = ByteBuffer.wrap(hdr).order(LITTLE_ENDIAN);
        IOUtils.readFully(is, hdr);
        int hS = bb.getInt(0);

        //            copy(is, size, path + name);
        int size = is.available();
        System.out.println("File: " + header.getName() +
                " size(cmo): " + size +
                ", size(hdr): " + hS + "(" + Integer.toHexString(hS) + ")"
        );

        hdr = new byte[8];
        bb = ByteBuffer.wrap(hdr).order(LITTLE_ENDIAN);
        int pos = 0;
        int jump;

        if (size > 12) while (true) {
            IOUtils.readFully(is, hdr);
            pos += 8;
            jump = bb.getInt(4) * 4;
            int toj = jump - pos;
            System.out.println("Label: " + Integer.toHexString(bb.getInt(0)) +
                    " siz: " + Integer.toHexString(bb.getInt(4)) +
                    " pos: " + Integer.toHexString(pos) +
                    " jmp: " + Integer.toHexString(jump) +
                    " toj: " + Integer.toHexString(toj)
            );
            if (jump == 0) break;
            if (toj == 0) continue;
            is.skip(toj);
            pos += toj;
        }
        return header;
    }
}
