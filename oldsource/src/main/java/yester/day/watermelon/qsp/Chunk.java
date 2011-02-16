package yester.day.watermelon.qsp;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundList;

public class Chunk {
    @Bound
    public int size;

    @Bound
    public byte OxA;

    @Bound
    public byte classID;

    @Bound
    public short version;

    @Bound
    public int wordCound;

    @BoundList(size = "size-64")
    public byte[] contents;
}
