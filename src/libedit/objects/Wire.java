package libedit.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Wire extends EagleObj {

    float x1, y1, x2, y2, width;
    int   layer;

    public Wire(Element xml) {
        super(xml);
    }

    public Wire(float x1, float y1, float x2, float y2, float width, int layer) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.layer = layer;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public void parseXML(Element xml) {
        try {
            x1 = xml.getAttribute("x1").getFloatValue();
            y1 = xml.getAttribute("y1").getFloatValue();
            x2 = xml.getAttribute("x2").getFloatValue();
            y2 = xml.getAttribute("y2").getFloatValue();
            width = xml.getAttribute("width").getFloatValue();
            layer = xml.getAttribute("layer").getIntValue();
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("wire");
        xml.setAttribute("x1", Float.toString(x1));
        xml.setAttribute("y1", Float.toString(y1));
        xml.setAttribute("x2", Float.toString(x2));
        xml.setAttribute("y2", Float.toString(y2));
        xml.setAttribute("width", Float.toString(width));
        xml.setAttribute("layer", Integer.toString(layer));
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.WIRE;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("x1=" + x1 + " y1=" + y1 +
                " x2=" + x2 + " y2=" + y2 +
                " width=" + width + " layer=" + layer);
    }

}
