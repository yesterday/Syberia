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

package yester.day.syberia.temp;

import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.loaders.composition.SimpleComposition;
import yester.day.syberia.loaders.object3d.Object3DLoader;
import yester.day.syberia.system.SystemObject;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 13 feb 2011  10:53:47
 */
public class MeshDumperRunner {
    public static void main(String[] args) throws IOException
    {
//        if(args.length < 1) {
//            System.out.println("No file specified");
//            System.exit(1);
//        }
        String file =
                args.length < 1
                ? "/home/day/Projects/Syberia/data/Reverse/ValReceptionInn.cmo.dir/"
                        +"01745_Momo_BODYPART"
                : args[0];
        FileInputStream fis = new FileInputStream(file);
        Object3DLoader loader = new Object3DLoader();

        SystemObject o =
                loader.load(new ObjectHeader(), fis, new SimpleComposition(0));

        System.out.println(o);
    }
}
