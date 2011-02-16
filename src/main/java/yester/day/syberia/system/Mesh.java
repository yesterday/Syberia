package yester.day.syberia.system;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 14 feb 2011  0:27:46
 */
public interface Mesh extends SystemObject {
    Face[] getFaces();

    public static interface Face {
        int[] getVerticesNumber();
    }

    public static interface Vertex {
//        float[] getCoordinates();
    }
}
