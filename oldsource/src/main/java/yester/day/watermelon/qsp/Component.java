package yester.day.watermelon.qsp;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;

public  class Component {
    @BoundNumber
    int id;

    @BoundNumber
    int componentType;

    @BoundNumber
    int size;

    @BoundNumber
    int recordLength;

    @BoundString(size = "recordLength")
    String data;

    public int getId() {
        return id;
    }

    public int getRecordLength() {
        return recordLength;
    }

    public String  getData() {
        return data;
    }

    public int getComponentType() {
        return componentType;
    }

    public int getOffset() {
        return size;
    }

    @Override
    public String toString() {
        return "ID "+ id +  "("+Integer.toString(id, 16)+"): "+
                CKType.resolve(componentType)+"("+ componentType + "), "+
                size + "(0x" + Integer.toString(size, 16) +"), " +
                new String(data)+"\n";
    }
}
