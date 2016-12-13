package libedit.eagle.models.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleObj;
import libedit.eagle.models.enums.PadShape;

public class Pad extends EagleObj {

    String   name;
    float    x, y, drill, diameter;
    PadShape shape;
    boolean  stop;

    public Pad(Element xml) {
        super(xml);
    }

    public Pad(String name, float x, float y, float drill, float diameter, PadShape shape, boolean stop) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.drill = drill;
        this.diameter = diameter;
        this.stop = stop;
        this.shape = shape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public PadShape getShape() {
        return shape;
    }

    public void setShape(PadShape shape) {
        this.shape = shape;
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
            name = xml.getAttributeValue("name");
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
            drill = xml.getAttribute("drill").getFloatValue();
            diameter = xml.getAttribute("diameter").getFloatValue();
            shape = PadShape.parseString(xml.getAttributeValue("shape"));
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
        xml.setAttribute("shape", shape.toString().toLowerCase());
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.PAD;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name + " x=" + x + " y=" + y +
                " drill=" + drill + " diameter=" + diameter +
                "shape=" + shape.toString() + " stop=" + stop);
    }

}
