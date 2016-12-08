package libedit.eagle.models.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleObj;
import libedit.eagle.models.enums.Rot;
import libedit.eagle.models.enums.Rot.Rotation;

public class Text extends EagleObj {

    String   text;
    float    x, y, size;
    int      layer;
    Rotation rot;

    public Text(Element xml) {
        super(xml);
    }

    public Text(String text, float x, float y, float size, int layer, Rotation rot) {
        super();
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.layer = layer;
        this.rot = rot;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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
            text = xml.getText();
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
            size = xml.getAttribute("size").getFloatValue();
            layer = xml.getAttribute("layer").getIntValue();
            rot = Rot.parseRotation(xml.getAttributeValue("rot"));
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("text");
        xml.setText(text);
        xml.setAttribute("x", Float.toString(x));
        xml.setAttribute("y", Float.toString(y));
        xml.setAttribute("size", Float.toString(size));
        xml.setAttribute("layer", Integer.toString(layer));
        xml.setAttribute("rot", rot.toString());
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.TEXT;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("text=" + text + " x=" + x + " y=" + y +
                " size=" + size + " layer=" + layer + " rot=" + rot);

    }

}
