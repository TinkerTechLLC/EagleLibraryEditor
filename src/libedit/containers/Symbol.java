package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class Symbol extends EagleContainer {

    String name;

    public Symbol(Element xml) {
        super(xml);
    }

    public Symbol(String name, List<EagleObj> children) {
        super(children);
        this.name = name;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.SYMBOL;
    }

    @Override
    public void parseXML(Element xml) {
        name = xml.getAttributeValue("name");
        this.parseXMLChildren(xml);
    }

    @Override
    public Element toXML() {
        Element xml = new Element("symbol");
        // System.out.println("Symbol children: " + this.children.size());
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
