package yester.day.syberia.loaders;

import yester.day.syberia.loaders.composition.ObjectHeader;
import yester.day.syberia.system.Composition;
import yester.day.syberia.system.SystemObject;

import java.io.IOException;
import java.io.InputStream;


/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 5 feb 2011  7:08:12
 */
public interface Loader {

    SystemObject load(ObjectHeader header, InputStream stream, Composition composition) throws IOException;
}
