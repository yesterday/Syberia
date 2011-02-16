package yester.day.syberia.loaders.mesh;

import yester.day.syberia.loaders.common.CommonSystemObject;
import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.system.Mesh;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 14 feb 2011  0:30:38
 */
public class SimpleMesh extends CommonSystemObject implements Mesh {
    Face[] faces;
    public SimpleMesh(ObjectHeader header) {
        super(header);
    }

    public SimpleMesh(String name, int id) {
        super(name, id);
    }

    public void setFaces(Face[] faces) {
        this.faces = faces;
    }

    public Face[] getFaces() {
        return faces;
    }
}
