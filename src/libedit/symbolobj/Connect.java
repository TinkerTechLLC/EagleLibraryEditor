package libedit.symbolobj;

import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Connect extends EagleObj {

    private String gate, pin, pad;

    public Connect(Element xml) {
        super(xml);
    }

    public Connect(String gate, String pin, String pad) {
        super();
        this.gate = gate;
        this.pin = pin;
        this.pad = pad;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPad() {
        return pad;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    @Override
    public void parseXML(Element xml) {
        gate = xml.getAttributeValue("gate");
        pin = xml.getAttributeValue("pin");
        pad = xml.getAttributeValue("pad");
    }

    @Override
    public Element toXML() {
        Element xml = new Element("connect");
        xml.setAttribute("gate", gate);
        xml.setAttribute("pin", pin);
        xml.setAttribute("pad", pad);
        return xml;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("gate=" + gate + " pin=" + pin + " pad=" + pad);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.CONNECT;
    }
}
