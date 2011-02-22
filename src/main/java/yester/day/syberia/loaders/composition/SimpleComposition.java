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
