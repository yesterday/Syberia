package yester.day.syberia.system;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 5 feb 2011  8:39:33
 */
public interface Composition extends Iterable<SystemObject> {
    int objectCount();

    SystemObject getObject(int i);

    SystemObject getObjectByID(int id);
}
