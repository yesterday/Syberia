package yester.day.syberia.loaders.composition;

import yester.day.syberia.system.Composition;
import yester.day.syberia.system.SystemObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 5 feb 2011  9:16:47
 */
public class SimpleComposition implements Composition {
    protected Map<Integer, SystemObject>  map;
    protected SystemObject[] objects;

    public SimpleComposition(int n) {
        this.objects = new SystemObject[n];
        map = new HashMap<Integer, SystemObject>();
    }

    public int objectCount() {
        return objects.length;
    }

    public SystemObject getObject(int i) {
        return objects[i];
    }

    public SystemObject getObjectByID(int id) {
        return map.get(id);
    }

    public Iterator<SystemObject> iterator() {
        return new Iterator<SystemObject>() {
            int i = 0;
            public boolean hasNext() {
                return i < objects.length;
            }

            public SystemObject next() {
                return objects[i++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void put(int i, SystemObject systemObject) {
        objects[i] = systemObject;
        map.put(systemObject.getID(), systemObject );

    }
}
