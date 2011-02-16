package yester.day.syberia.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 5 feb 2011  8:32:44
 */
public class IOUtils {
    private IOUtils() {

    }

    public static void copy(InputStream is, int size, String name) throws IOException {
//        System.out.print("Writing " + name + " (" + size + " bytes)... ");
        byte[] b = new byte[size];
        IOUtils.readFully(is, b, 0, size);
        OutputStream os = new FileOutputStream(name);
        os.write(b);
        os.close();
//        System.out.println("done");
    }

    public static int readFully(InputStream str, byte[] b, int offset, int size) throws IOException {

        int r, total = 0, len = size;

        while ((len -= (r = str.read(b, offset + total, len) )) > 0) {
            total += r;
//            len -= r;
        }

        return total;
    }

    public static int readFully(InputStream stream, byte[]b) throws IOException {
        return readFully(stream, b, 0, b.length);
    }


    public static String pos(InputStream stream) {
        return "(@ " + Integer.toHexString(((RestrictedInputStream) stream).getReaden()) + ")";
    }

    public static int asInt(byte []buf) {
        return ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }
}
