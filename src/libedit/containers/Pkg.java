package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class Pkg extends EagleContainer {

    String name;

    public Pkg(String name, List<EagleObj> children) {
        super(children);
        this.name = name;
    }

    public Pkg(Element xml) {
        super(xml);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.PACKAGE;
    }

    @Override
    public void parseXML(Element xml) {
        parseXMLChildren(xml);
        name = xml.getAttributeValue("name");
    }

    @Override
    public Element toXML() {
        Element xml = new Element("package");
        xml.setAttribute("name", name);
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

    @Override
    protected void printAttributes(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name);
    }

}
