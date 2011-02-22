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

package yester.day.watermelon.qsp;

import org.codehaus.preon.DecodingException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 8 jan 2011  12:04:12
 */
public class CompositionLoader {


    public static void main(String[] args) throws IOException, DecodingException {
//        String path = "/home/day/work/me/Syberia/Work.unvirt/cube/", file = "Cube.nmo";
//        String path = "/home/day/work/me/Syberia/Work.unvirt/bar/", file = "AraHotelBar.cmo";
//        String path = "/home/day/work/me/Syberia/Work.unvirt/bullet/", file = "_bullet.nmo";
//        String path = "/home/day/work/me/Syberia/Work.unvirt/bluesuper/", file = "BlueSuperNova.cmo";
//        String path = "/home/day/work/me/Syberia/Work.unvirt/empty/", file = "empty.cmo";
        if(args.length < 1) {
            System.out.println("Usage: java -jar unpack.jar FILE [DESTDIR]");
            System.exit(1);
        }
        String path = args.length < 2 ? "" : args[1] , file = args[0];
        InputStream is = new FileInputStream(file);
        is = new BufferedInputStream(is);
        Composition c = Composition.read(is, path);
        System.out.println(c);
    }
}
