package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class Library extends EagleContainer {

    List<EagleObj> packages;
    List<EagleObj> symbols;
    List<EagleObj> devicesets;

    public Library(List<EagleObj> children) {
        super(children);
    }

    public Library(Element xml) {
        super(xml);
    }

    public List<EagleObj> getPackages() {
        return packages;
    }

    public void setPackages(List<EagleObj> packages) {
        this.packages = packages;
    }

    public List<EagleObj> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<EagleObj> symbols) {
        this.symbols = symbols;
    }

    public List<EagleObj> getDevicesets() {
        return devicesets;
    }

    public void setDevicesets(List<EagleObj> devicesets) {
        this.devicesets = devicesets;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.LIBRARY;
    }

    @Override
    public void parseXML(Element xml) {
        parseXMLChildren(xml);
        packages = ((EagleContainer) this.children.get(0)).getChildren();
        symbols = ((EagleContainer) this.children.get(1)).getChildren();
        devicesets = ((EagleContainer) this.children.get(2)).getChildren();
    }

    @Override
    public Element toXML() {
        Element xml = new Element("library");
        // System.out.println("Library children: " + this.children.size());
        if (children.isEmpty()) {
            if (packages != null) {
                children.add(new ObjList("packages", packages));
            }
            if (symbols != null) {
                children.add(new ObjList("symbols", symbols));
            }
            if (devicesets != null) {
                children.add(new ObjList("devicesets", devicesets));
            }
        }
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

    @Override
    protected void printAttributes(int tabLevel) {
    }

}
