package libedit.eagle.models.containers;

import java.util.Collections;
import java.util.List;

import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleContainer;
import libedit.eagle.models.abstractobjects.EagleObj;

public class EagleDoc extends EagleContainer {

    String fileName;
    String version;

    public EagleDoc(Element xml) {
        super(xml);
    }

    public EagleDoc(String fileName, List<EagleObj> children) {
        super(children);
        this.fileName = fileName;
    }

    public void addEagleObject(EagleObj obj) {
        children.add(obj);
        Collections.sort(children);
    }

    public String getFileName() {
        return fileName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.EAGLEDOC;
    }

    @Override
    public void parseXML(Element xml) {
        version = xml.getAttributeValue("version");
        parseXMLChildren(xml);
    }

    @Override
    public Element toXML() {
        Element xml = new Element("eagle");
        xml.setAttribute("version", version);
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

    @Override
    protected void printAttributes(int tabLevel) {
        this.printTabs(tabLevel);
        System.out.println("version=" + version);
    }
}
