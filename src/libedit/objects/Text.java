package libedit.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;
import libedit.enums.Rot;
import libedit.enums.Rot.Rotation;

public class Text extends EagleObj {

    String   text;
    float    x, y, size;
    int      layer;
    Rotation rot;

    @Override
    public int getPriority() {
        return Priority.TEXT;
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

}
