package libedit.eagle.models.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleObj;

public class Layer extends EagleObj {

    int     number;
    String  name;
    int     color;
    int     fill;
    boolean visible;
    boolean active;

    public Layer(Element xml) {
        super(xml);
    }

    public Layer(int number, String name, int color, int fill, boolean visible, boolean active) {
        super();
        this.number = number;
        this.name = name;
        this.color = color;
        this.fill = fill;
        this.visible = visible;
        this.active = active;
    }

    @Override
    public void parseXML(Element xml) {
        try {
            number = xml.getAttribute("number").getIntValue();
            name = xml.getAttributeValue("name");
            color = xml.getAttribute("color").getIntValue();
            fill = xml.getAttribute("fill").getIntValue();
            visible = xml.getAttribute("visible").getBooleanValue();
            active = xml.getAttribute("active").getBooleanValue();
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("layer");
        xml.setAttribute("number", Integer.toString(number));
        xml.setAttribute("name", name);
        xml.setAttribute("color", Integer.toString(color));
        xml.setAttribute("fill", Integer.toString(fill));
        xml.setAttribute("visible", visible ? "yes" : "no");
        xml.setAttribute("active", active ? "yes" : "no");
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.LAYER;
    }

    @Override
    public void printContents(int tabLevel) {
        // TODO Auto-generated method stub

    }

}