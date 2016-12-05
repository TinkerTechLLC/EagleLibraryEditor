package libedit.models.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.models.abstractobjects.EagleContainer;
import libedit.models.abstractobjects.EagleObj;

public class ObjList extends EagleContainer {

    String name;

    public ObjList(String name, List<EagleObj> children) {
        super(children);
        this.name = name;
    }

    public ObjList(Element xml) {
        super(xml);
        this.name = xml.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void parseXML(Element xml) {
        this.parseXMLChildren(xml);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.OBJLIST;
    }

    @Override
    public Element toXML() {
        // System.out.println("Converting children of " + name);
        Element xml = new Element(name);
        List<Element> xmlChildren = childrenToXML();
        if (xmlChildren.size() > 0) {
            for (Element e : xmlChildren) {
                xml.addContent(e);
            }
        }
        return xml;
    }

    @Override
    protected void printAttributes(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name);
    }

}
