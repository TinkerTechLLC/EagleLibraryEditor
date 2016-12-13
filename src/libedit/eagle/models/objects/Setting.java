package libedit.eagle.models.objects;

import org.jdom2.Attribute;
import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleObj;

public class Setting extends EagleObj {

    String attributeName;
    String attributeValue;

    public Setting(Element xml) {
        super(xml);
    }

    @Override
    public void parseXML(Element xml) {
        Attribute attribute = xml.getAttributes().get(0);
        attributeName = attribute.getName();
        attributeValue = attribute.getValue();
    }

    @Override
    public Element toXML() {
        Element xml = new Element("setting");
        xml.setAttribute(attributeName, attributeValue);
        return xml;
    }

    @Override
    public void printContents(int tabLevel) {
        printTabs(tabLevel);
        System.out.println(attributeName + "=" + attributeValue);
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.SETTING;
    }

}
