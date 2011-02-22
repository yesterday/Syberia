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
