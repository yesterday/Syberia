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

package yester.day.watermelon.qsp.texture;

import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 10 jan 2011  4:09:28
 */
public class TextureLoader {
    public static void main(String[] args) throws DecodingException, IOException {
       String file = args.length != 0 ? args[0] :
               "/work/me/Syberia/Work.texture/new/" +
//                       "00838_TEXTURE_Cur_Zoom"
                       "00806_TEXTURE_Cel_Body"
               ;
        Codec<Texture> codec =Codecs.create(Texture.class);
        Texture tex = Codecs.decode(codec, new File(file));
        System.out.println("tex: "+tex.width+"x"+tex.height+"@"+tex.BPP);
        BufferedImage image =new BufferedImage(tex.width, tex.height, BufferedImage.TYPE_INT_ARGB);


        int planes = tex.BPP / 8;
        for(int y = 0; y < tex.height; y++) {
        for(int x = 0; x < tex.width; x ++){
            int pix = 0;
            for(int i = 0; i < planes; i++) {
                pix += (int)tex.planes[i].values[x+tex.width*y] << (i* 8);
//                System.out.printf("%02x", tex.planes[i].values[x*y]);
            }
            image.setRGB(x, tex.height-y-1, pix);
//            System.out.print(" ");
//            System.out.printf("%08x ", pix);
        }
//            System.out.println();
        }
        ImageIO.write(image, "png", new File(file + ".png"));

    }

    public static class Texture {
        @Bound
        int length;

        @Bound byte OxA;
        @Bound byte classID;
        @Bound short version;

        @Bound int dataSizeInWords0;
        @Bound int unk0,  dataSizeInWords1, unk1;
        @Bound int BPP, width, height;
        @BoundList(size="BPP/8") int[] planesBindes;
        @Bound  int unk2;
        @BoundList(size="BPP/8") ColorPlane planes[];
    }

    public static class ColorPlane {
        @Bound int size;
        @BoundList(size = "outer.width*outer.height") byte[] values;
    }
}


