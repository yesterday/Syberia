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

package yester.day.watermelon.qsp.dataarray;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 12 jan 2011  7:15:23
 */
public class TestDataArrayLoader {
    public static void main(String[] args) throws IOException {
        String file = args.length == 0 ?
                "/work/me/Syberia/Work.syberia/BaseCMO.cmo.dir/" +
                        "00836_CMOSceneArray_DATAARRAY"
                : args[0];

        DataInputStream r = new DataInputStream(new FileInputStream(file));
        r.readInt(); // filesize
        r.readInt(); // flags
        r.readInt(); // fileSizeInWords

        r.readInt(); // header
        r.readInt(); // section size in words
        Coloumn[] coloumns = new Coloumn[r.readInt()]; // coloumns count
        for(int i = 0; i < coloumns.length; i++)  coloumns[i] = new Coloumn(r);

//        if(0x2000)

    }

        protected static String readPaddedString(DataInputStream str) throws IOException {
            int len = str.readInt(), padded = (len+4)&~3;
            len--;
            padded-=len;
            byte[] chars = new byte[len];
            if(len != str.read(chars)) throw new IOException("Want to read " + len + " bytes");
            String name = new String(chars);
            if(padded > len) chars = new byte[padded];
            str.read(chars, 0, padded);
            return name;
        }

    static class Coloumn {
        String name;
        Type type;

        Coloumn(DataInputStream str) throws IOException {
            name = readPaddedString(str);
            type = Type.values()[str.readInt()];
            if (type.ordinal() > 3) System.err.println("Watch out! col is " + type);
        }


    }

    enum Type {
        DUMMY,
        CKARRAYTYPE_INT,        // an integer
        CKARRAYTYPE_FLOAT,       // a float
        CKARRAYTYPE_STRING,     // a pointer to a string
        CKARRAYTYPE_OBJECT,     // a CKObject ID (CK_ID)
        CKARRAYTYPE_PARAMETER;  // a CKParameter ID
    }
}
