package libedit.models.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.enums.Unit;

public class Grid extends EagleObj {

    private float   distance, altdistance;
    private Unit    unitdist, unit, altunitdist, altunit;
    private String  style;
    private int     multiple;
    private boolean display;

    public Grid(Element xml) {
        super(xml);
    }

    @Override
    public void parseXML(Element xml) {
        try {
            distance = xml.getAttribute("distance").getFloatValue();
            altdistance = xml.getAttribute("altdistance").getFloatValue();
            unitdist = Unit.parseString(xml.getAttributeValue("unitdist"));
            unit = Unit.parseString(xml.getAttributeValue("unit"));
            altunitdist = Unit.parseString(xml.getAttributeValue("altunitdist"));
            altunit = Unit.parseString(xml.getAttributeValue("altunit"));
            style = xml.getAttributeValue("style");
            multiple = xml.getAttribute("multiple").getIntValue();
            display = xml.getAttribute("display").getBooleanValue();
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public Element toXML() {
        Element xml = new Element("grid");
        xml.setAttribute("distance", Float.toString(distance));
        xml.setAttribute("altdistance", Float.toString(altdistance));
        xml.setAttribute("unitdist", unitdist.toString().toLowerCase());
        xml.setAttribute("unit", unit.toString().toLowerCase());
        xml.setAttribute("altunitdist", altunitdist.toString().toLowerCase());
        xml.setAttribute("altunit", altunit.toString().toLowerCase());
        xml.setAttribute("style", style);
        xml.setAttribute("multiple", Integer.toString(multiple));
        xml.setAttribute("display", display ? "yes" : "no");
        return xml;
    }

    @Override
    public void printContents(int tabLevel) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setPriority() {
        this.priority = Priority.GRID;
    }

}
