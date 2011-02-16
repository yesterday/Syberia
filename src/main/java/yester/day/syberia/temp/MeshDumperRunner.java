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
