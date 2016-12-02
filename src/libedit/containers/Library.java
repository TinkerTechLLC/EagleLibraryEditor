package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class Library extends EagleContainer {

    public Library(List<EagleObj> children) {
        super(children);
    }

    public Library(Element xml) {
        super(xml);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.LIBRARY;
    }

    @Override
    public void parseXML(Element xml) {
        parseXMLChildren(xml);
    }

    @Override
    public Element toXML() {
        Element xml = new Element("library");
        // System.out.println("Library children: " + this.children.size());
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

    @Override
    protected void printAttributes(int tabLevel) {
    }

}
