package libedit.eagle.models.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleContainer;
import libedit.eagle.models.abstractobjects.EagleObj;

public class DeviceSet extends EagleContainer {

    String name, prefix, uservalue;

    public DeviceSet(Element xml) {
        super(xml);
    }

    public DeviceSet(String name, String prefix, String uservalue, List<EagleObj> children) {
        super(children);
        this.name = name;
        this.prefix = prefix;
        this.uservalue = uservalue;
        this.children = children;
    }

    @Override
    public void parseXML(Element xml) {
        name = xml.getAttributeValue("name");
        prefix = xml.getAttributeValue("prefix");
        uservalue = xml.getAttributeValue("uservalue");
        this.parseXMLChildren(xml);
    }

    @Override
    public Element toXML() {
        // System.out.println("Package children: " + this.children.size());
        Element xml = new Element("deviceset");
        xml.setAttribute("name", name);
        xml.setAttribute("prefix", prefix);
        if (uservalue != null) {
            xml.setAttribute("uservalue", uservalue);
        }
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.DEVICESET;
    }

    @Override
    protected void printAttributes(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name + " prefix=" + prefix + " uservalue=" + uservalue);
    }

}
