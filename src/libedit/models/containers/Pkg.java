package libedit.models.containers;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import libedit.models.abstractobjects.EagleContainer;
import libedit.models.abstractobjects.EagleObj;
import libedit.models.objects.SMD;

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

    public List<SMD> getSMDPads() {
        List<SMD> ret = new ArrayList<SMD>();
        for (EagleObj o : this.children) {
            if (o.isSMD()) {
                ret.add((SMD) o);
            }
        }
        return ret;
    }
}
