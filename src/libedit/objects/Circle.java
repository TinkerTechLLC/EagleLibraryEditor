package libedit.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Circle extends EagleObj {

    float x, y, radius, width;
    int   layer;

    public Circle(Element xml) {
        super(xml);
    }

    public Circle(float x, float y, float radius, float width, int layer) {
        super();
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.width = width;
        this.layer = layer;
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
            radius = xml.getAttribute("radius").getFloatValue();
            width = xml.getAttribute("width").getFloatValue();
            layer = xml.getAttribute("layer").getIntValue();
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("pin");
        xml.setAttribute("x", Float.toString(x));
        xml.setAttribute("y", Float.toString(x));
        xml.setAttribute("radius", Float.toString(radius));
        xml.setAttribute("width", Float.toString(width));
        xml.setAttribute("layer", Integer.toString(layer));
        return xml;
    }

    @Override
    protected void setPriority() {
        // TODO Auto-generated method stub

    }

    @Override
    public void printContents(int tabLevel) {
        // TODO Auto-generated method stub

    }

}
