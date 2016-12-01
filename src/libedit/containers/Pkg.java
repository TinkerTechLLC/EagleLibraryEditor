package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class Pkg extends EagleContainer {

    String name;
    String description;

    public Pkg(List<EagleObj> children) {
        super(children);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.PACKAGE;
    }

    @Override
    public void parseXML(Element xml) {
        parseXMLChildren(xml);
        name = xml.getAttributeValue("name");
        description = xml.getText();
    }

    @Override
    public Element toXML() {
        Element xml = new Element("package");
        xml.setAttribute("name", name);
        xml.setText(description);
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

}
