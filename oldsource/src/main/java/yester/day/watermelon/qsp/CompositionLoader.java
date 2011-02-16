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
