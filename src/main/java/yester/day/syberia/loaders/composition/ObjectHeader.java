package yester.day.syberia.loaders.composition;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundString;
import yester.day.syberia.system.SystemObject;
import yester.day.syberia.temp.CKType;

public class ObjectHeader implements SystemObject {
    @Bound
    int id, componentType, offset, nameLength;

    @BoundString(size = "nameLength")
    String name;


    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getComponentType() {
        return componentType;
    }

    @Override
    public String toString() {
        return "ObjectHeader{" +
                "id=" + Integer.toHexString(id) +
                ", componentType=" + componentType + "("+CKType.resolve(componentType)+")"+
                ", offset=" + offset +
                ", name='" + name + '\'' +
                '}';
    }
}
