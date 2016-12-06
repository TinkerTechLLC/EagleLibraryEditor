package libedit.models.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.enums.Rot;
import libedit.models.enums.Rot.Rotation;

public class SMD extends EagleObj {

    private String   name;
    private float    x;
    private float    y;
    private float    dx;
    private float    dy;
    private int      layer;
    private Rotation rot;

    public SMD(Element xml) {
        super(xml);
    }

    public SMD(String name, float x, float y, float dx, float dy, int layer, Rotation rot) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.layer = layer;
        this.rot = rot;
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

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Rotation getRot() {
        return rot;
    }

    public void setRot(Rotation rot) {
        this.rot = rot;
    }

    @Override
    public void parseXML(Element xml) {
        try {
            name = xml.getAttributeValue("name");
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
            dx = xml.getAttribute("dx").getFloatValue();
            dy = xml.getAttribute("dy").getFloatValue();
            layer = xml.getAttribute("layer").getIntValue();
            rot = Rot.parseRotation(xml.getAttributeValue("rot"));
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public Element toXML() {
        Element xml = new Element("smd");
        xml.setAttribute("name", name);
        xml.setAttribute("x", Float.toString(x));
        xml.setAttribute("y", Float.toString(y));
        xml.setAttribute("dx", Float.toString(dx));
        xml.setAttribute("dy", Float.toString(dy));
        xml.setAttribute("layer", Integer.toString(layer));
        xml.setAttribute("rot", rot.toString());
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.SMD;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name +
                " x=" + x + " y=" + y +
                " dx =" + dx + " dy=" + dy +
                " layer=" + layer + " rot=" + rot);
    }
}
