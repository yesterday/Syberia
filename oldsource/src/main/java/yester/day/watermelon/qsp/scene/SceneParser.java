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

package yester.day.watermelon.qsp.scene;

import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import yester.day.watermelon.qsp.Chunk;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 13 jan 2011  9:16:22
 */
public class SceneParser {
    public static void main(String[] args) throws DecodingException, IOException {
        String file = args.length == 0 ?
                "/work/me/Syberia/Work.syberia/scenes/" +
                "00270_Scene11280_SCENE"
                : args[0];

        Codec<Chunk> codec = Codecs.create(Chunk.class);
        Chunk ch = Codecs.decode(codec, new File(file));

        ByteBuffer buf = ByteBuffer.wrap(ch.contents).order(ByteOrder.LITTLE_ENDIAN);
        while(buf.getInt() != 0x20000 && buf.hasRemaining());
        while(buf.getInt() != 0 && buf.hasRemaining());
        int size = buf.getInt();
        if(size != buf.getInt()) {
            System.out.println("Size doesn't match");
            return;
        }

        List<Integer> list = new LinkedList<Integer>();
        int r;

        for(int i = 0; i < size; i++)
            if((r = buf.getInt()) != 0) list.add(r);

        if(args.length > 1) {
            for (Integer integer : list) System.out.printf("%05x ", integer);
            return;
        }

        System.out.printf("Scene has %d(0x%x) objects. Params at 0x%x\n", size, size, buf.position());
        buf.position(buf.position() + buf.getInt() * 4+4);

        for (Integer integer : list) System.out.printf("obj: %05x, flags: %x\n", integer, buf.getInt());
        System.out.printf("Ended at 0x%x", buf.position());


    }

}

