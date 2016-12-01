package libedit.objects;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Polygon extends EagleObj {

    float         width;
    int           layer;
    List<Point2D> verticies;

    public Polygon(Element xml) {
        super(xml);
    }

    public Polygon(float width, int layer, List<Point2D> verticies) {
        super();
        this.width = width;
        this.layer = layer;
        this.verticies = verticies;
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

    public List<Point2D> getVerticies() {
        return verticies;
    }

    public void setVerticies(List<Point2D> verticies) {
        this.verticies = verticies;
    }

    public void addVertex(Point2D point) {
        verticies.add(point);
    }

    @Override
    public void parseXML(Element xml) {
        try {
            width = xml.getAttribute("width").getFloatValue();
            layer = xml.getAttribute("layer").getIntValue();

            List<Element> tempVerticies = new ArrayList<Element>();
            tempVerticies = xml.getChildren();
            for (Element v : tempVerticies) {
                Point2D p = new Point();
                float x = v.getAttribute("x").getFloatValue();
                float y = v.getAttribute("y").getFloatValue();
                p.setLocation(x, y);
                this.verticies.add(p);
            }
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("polygon");

        xml.setAttribute("layer", Integer.toString(layer));
        xml.setAttribute("width", Float.toString(width));

        for (Point2D v : verticies) {
            Element vertex = new Element("vertex");
            vertex.setAttribute("x", Float.toString((float) v.getX()));
            vertex.setAttribute("y", Float.toString((float) v.getY()));
            xml.addContent(vertex);
        }
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.POLYGON;
    }

}
