package libedit.objects;

import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Description extends EagleObj {

    String text;

    public Description(Element xml) {
        super(xml);
    }

    public Description(String text) {
        this.text = text;
    }

    @Override
    public void parseXML(Element xml) {
        text = xml.getAttributeValue("description");
    }

    @Override
    public Element toXML() {
        Element xml = new Element("description");
        xml.setText(text);
        return xml;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("Description: " + text);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.DESCRIPTION;
    }

}
