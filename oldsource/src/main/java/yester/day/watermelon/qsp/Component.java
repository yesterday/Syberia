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
