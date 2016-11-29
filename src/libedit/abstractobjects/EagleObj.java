package libedit.abstractobjects;

import org.jdom2.Element;

public abstract class EagleObj implements Comparable<EagleObj> {

    abstract public class Priority {
        public static final int EAGLEDOC  = 0;
        public static final int DRAWING   = 1;
        public static final int SETTINGS  = 2;
        public static final int LAYER     = 3;
        public static final int LIBRARY   = 4;
        public static final int PACKAGE   = 5;
        public static final int WIRE      = 6;
        public static final int SMD       = 7;
        public static final int TEXT      = 8;
        public static final int RECTANGLE = 9;
        public static final int SYMBOLS   = 10;
        public static final int DEVICES   = 11;

    }

    abstract public int getPriority();

    abstract public void parseXML(Element xml);

    abstract public Element toXML();

    @Override
    public int compareTo(final EagleObj comp) {
        return Integer.compare(this.getPriority(), comp.getPriority());
    }

    public boolean isWire() {
        return this.getPriority() == Priority.WIRE;
    }

    public boolean isSMD() {
        return this.getPriority() == Priority.SMD;
    }

    public boolean isText() {
        return this.getPriority() == Priority.TEXT;
    }

    public boolean isRectangle() {
        return this.getPriority() == Priority.RECTANGLE;
    }
}
