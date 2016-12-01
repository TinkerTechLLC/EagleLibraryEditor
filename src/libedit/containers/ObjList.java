package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

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
        Element xml = new Element(name);
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

}
