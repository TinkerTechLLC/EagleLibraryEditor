package libedit.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Pad extends EagleObj {

    int     name;
    float   x, y, drill, diameter;
    boolean stop;

    public Pad(Element xml) {
        super(xml);
    }

    public Pad(int name, float x, float y, float drill, float diameter, boolean stop) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.drill = drill;
        this.diameter = diameter;
        this.stop = stop;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDrill() {
        return drill;
    }

    public void setDrill(float drill) {
        this.drill = drill;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void parseXML(Element xml) {
        try {
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
            drill = xml.getAttribute("drill").getFloatValue();
            diameter = xml.getAttribute("diameter").getFloatValue();
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public Element toXML() {
        Element xml = new Element("pad");
        xml.setAttribute("x", Float.toString(x));
        xml.setAttribute("y", Float.toString(y));
        xml.setAttribute("drill", Float.toString(drill));
        xml.setAttribute("diameter", Float.toString(diameter));
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.PAD;
    }

    @Override
    public void printContents(int tabLevel) {
        // TODO Auto-generated method stub

    }

}
