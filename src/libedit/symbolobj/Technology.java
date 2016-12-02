package libedit.symbolobj;

import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Technology extends EagleObj {

    String name;

    public Technology(Element xml) {
        super(xml);
    }

    public Technology(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void parseXML(Element xml) {
        name = xml.getAttributeValue("name");
    }

    @Override
    public Element toXML() {
        Element xml = new Element("technology");
        xml.setAttribute("name", name);
        return xml;
    }

    @Override
    public void printContents(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.TECHNOLOGY;
    }
}
