package libedit.abstractobjects;

import org.jdom2.Element;

public abstract class EagleObj implements Comparable<EagleObj> {

    protected Priority priority;

    public enum Priority {
        EAGLEDOC(0), DRAWING(1), SETTING(2), GRID(3), LAYER(4), DEVICESET(5), GATE(6), DEVICE(7), SYMBOL(8), LIBRARY(
                9), PACKAGE(10), WIRE(11), SMD(12), PAD(13), TEXT(14), RECTANGLE(15), CIRCLE(16), POLYGON(17), PIN(
                        18), VERTEX(19), CONNECT(20), TECHNOLOGY(21), OBJLIST(22);
        int priority;

        Priority(int priorityVal) {
            this.priority = priorityVal;
        }

        int getVal() {
            return priority;
        }
    }

    protected EagleObj() {
        setPriority();
    }

    protected EagleObj(Element xml) {
        this.parseXML(xml);
        setPriority();
    }

    public Priority getPriority() {
        return priority;
    };

    abstract public void parseXML(Element xml);

    abstract public Element toXML();

    @Override
    public int compareTo(final EagleObj comp) {
        return Integer.compare(this.getPriority().getVal(), comp.getPriority().getVal());
    }

    protected abstract void setPriority();

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
