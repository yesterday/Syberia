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
