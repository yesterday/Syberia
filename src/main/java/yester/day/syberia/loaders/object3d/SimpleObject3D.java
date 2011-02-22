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

package yester.day.syberia.loaders.object3d;

import yester.day.syberia.loaders.common.CommonSystemObject;
import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.system.Mesh;
import yester.day.syberia.system.Object3D;

public class SimpleObject3D extends CommonSystemObject implements Object3D {
    Mesh.Vertex[] verticles;

    public Mesh.Vertex[] getVertices() {
        return verticles;
    }

    public void setVertices(Mesh.Vertex[] verticles) {
        this.verticles = verticles;
    }

    SimpleObject3D(ObjectHeader header) {
        super(header);
    }

    SimpleObject3D(String name, int id) {
        super(name, id);
    }


}
