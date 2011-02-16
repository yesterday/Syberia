package yester.day.watermelon.qsp.dataarray;

import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import org.codehaus.preon.annotation.*;
import org.codehaus.preon.buffer.ByteOrder;
import org.codehaus.preon.buffer.DefaultBitBuffer;

import java.io.IOException;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 12 jan 2011  8:15:42
 */
public class DataArrayLoader {
    @BoundList(size = "12")
    byte[] uselessHeader;

    @Bound
    int headMarker;

    @Bound
    int sizeInWords;

    @Bound
    int coloumnCount;

    @BoundList(size = "coloumnCount")
    Coloumn[] coloumns;

    @Bound
    int bodyMarker;

    @Bound
    int bodySize;

    @Bound
    int elementsCount;


    public static class PaddedSring {
        @Bound
        int length;

        @BoundString(size = "length-1")
        String value;

        @BoundList(size = "((length+3)/4*4) - length + 1")
        byte[] pad;

        public String toString() {
            return value;
        }
    }


    public static class Coloumn {
        @Bound
        int nameLength;
        @BoundString(size = "nameLength-1")
        String name;

//        @BoundList(size = "nameLength-nameLength/4*4+1")
        @BoundList(size = "((nameLength+3)/4*4) - nameLength + 1")
        byte[] pad;

        @BoundNumber(size = "32")
        int type;
//                Type type;
        @If("type = 5")
        @BoundList(size="3")
        int[] trash;
    }

    public enum Type {
        DUMMY,
        CKARRAYTYPE_INT,        // an integer
        CKARRAYTYPE_FLOAT,       // a float
        CKARRAYTYPE_STRING,     // a pointer to a string
        CKARRAYTYPE_OBJECT,     // a CKObject ID (CK_ID)
    }

    public static void main(String[] args) throws DecodingException, IOException {

        String file = args.length == 0 ?
                "/work/me/Syberia/Work.syberia/BaseCMO.cmo.dir/" +
                        "00836_CMOSceneArray_DATAARRAY" : args[0];
        DefaultBitBuffer buffer = new DefaultBitBuffer(file);

        Codec<PaddedSring> stringReader = Codecs.create(PaddedSring.class);
        Codec<DataArrayLoader> codec = Codecs.create(DataArrayLoader.class);
        DataArrayLoader array = Codecs.decode(codec, buffer, null, null);


        for (Coloumn arg : array.coloumns)
            System.out.printf(" %20s |", arg.name + " " + arg.type);
        System.out.println();
        for (int i = 0; i < array.coloumns.length; i++)
            System.out.print("-----------------------");
        System.out.println();

        for (int i = 0; i < array.elementsCount; i++) {
            for (Coloumn coloumn : array.coloumns) {
                switch (coloumn.type) {
                    case 5:
                    case 1: {
//                    case CKARRAYTYPE_INT: {
                        int r = buffer.readAsInt(32, ByteOrder.LittleEndian);
                        System.out.printf(" %8d(0x%08x) |", r, r);
                        break;
                    }
                    case 2: {
//                    case CKARRAYTYPE_FLOAT: {
                        int r = buffer.readAsInt(32, ByteOrder.LittleEndian);
                        float f = Float.intBitsToFloat(r);
                        System.out.printf(" %8f(0x%8x) |", f, r);
                        break;
                    }
                    case 3 : {
//                    case CKARRAYTYPE_STRING: {
                        PaddedSring s = Codecs.decode(stringReader, buffer,
                                null, null);
                        System.out.printf(" %20s |", s);
                        break;
                    }
                    default:
                        System.out.printf(" %16s |", "cannot read!");
                }
            }

            System.out.println();
        }


    }
}
