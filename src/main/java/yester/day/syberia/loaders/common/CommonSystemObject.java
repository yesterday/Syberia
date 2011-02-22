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
