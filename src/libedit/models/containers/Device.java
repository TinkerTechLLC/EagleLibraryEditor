package libedit.models.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.models.abstractobjects.EagleContainer;
import libedit.models.abstractobjects.EagleObj;

public class Device extends EagleContainer {

    String name;
    String packageStr;

    public Device(Element xml) {
        super(xml);
    }

    public Device(String name, String packageStr, List<EagleObj> children) {
        super(children);
        this.name = name;
        this.packageStr = packageStr;
    }

    @Override
    public Element toXML() {
        Element xml = new Element("device");
        xml.setAttribute("name", name);
        xml.setAttribute("package", packageStr);
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.DEVICE;
    }

    @Override
    public void parseXML(Element xml) {
        name = xml.getAttributeValue("name");
        packageStr = xml.getAttributeValue("package");
        parseXMLChildren(xml);
    }

    @Override
    protected void printAttributes(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("name=" + name + " package=" + packageStr);
    }
}
