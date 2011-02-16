package yester.day.syberia.loaders.common;

import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.system.SystemObject;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 13 feb 2011  21:34:04
 */
public class CommonSystemObject implements SystemObject{
    protected String name;
    int id;

    public CommonSystemObject(ObjectHeader header) {
        this(header.getName(), header.getID());
    }

    public CommonSystemObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" + "name='" + name + '\'' + ", id=" + id + '}';
    }
}
