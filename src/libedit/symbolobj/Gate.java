package libedit.symbolobj;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Gate extends EagleObj {

    private String name, symbol;
    private Float  x, y;

    public Gate(Element xml) {
        super(xml);
    }

    public Gate(String name, String symbol, Float x, Float y) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public void parseXML(Element xml) {
        name = xml.getAttributeValue("name");
        symbol = xml.getAttributeValue("symbol");
        try {
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
        } catch (DataConversionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("gate");
        xml.setAttribute("name", name);
        xml.setAttribute("symbol", symbol);
        xml.setAttribute("x", Float.toString(x));
        xml.setAttribute("x", Float.toString(y));
        return xml;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name + " symbol=" + symbol + " x=" + x + " y=" + y);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.GATE;
    }

}
