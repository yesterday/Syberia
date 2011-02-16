package yester.day.watermelon.qsp;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 7 jan 2011  17:06:49
 */

public class ComponentList {
    @BoundNumber(byteOrder = ByteOrder.BigEndian)
    int componentsNumber;

    @BoundList(type = Component.class, size = "componentsNumber")
//        List<Component> components;
            Component[] components;


}
