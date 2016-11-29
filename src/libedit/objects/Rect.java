package libedit.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Rect extends EagleObj {

    float x1, y1, x2, y2;
    int   layer;

    @Override
    public int getPriority() {
        return Priority.RECTANGLE;
    }

    @Override
    public void parseXML(Element xml) {
        try {
            x1 = xml.getAttribute("x1").getFloatValue();
            y1 = xml.getAttribute("y1").getFloatValue();
            x2 = xml.getAttribute("x2").getFloatValue();
            y2 = xml.getAttribute("y2").getFloatValue();
            layer = xml.getAttribute("layer").getIntValue();

        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("rectangle");
        xml.setAttribute("x1", Float.toString(x1));
        xml.setAttribute("y1", Float.toString(y1));
        xml.setAttribute("x2", Float.toString(x2));
        xml.setAttribute("y2", Float.toString(y2));
        xml.setAttribute("layer", Integer.toString(layer));
        return xml;
    }

}
