package libedit.containers;

import java.util.Collections;
import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;
import libedit.enums.Layers;

public class EagleDoc extends EagleContainer {

    String fileName;

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

    public String toXMLString() {
        String ret = "";
        ret += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        ret += "<!DOCTYPE eagle SYSTEM \"eagle.dtd\">\n";
        ret += "<eagle version=\"7.6.0\">\n";
        ret += "<drawing>\n";
        ret += "<settings>" + "<setting alwaysvectorfont=\"no\" />\n" + "<setting verticaltext=\"up\" />\n"
                + "</settings>\n";
        ret += "<grid distance=\"0.1\" " + "unitdist=\"mm\" " + "unit=\"mm\" " + "style=\"lines\" " + "multiple=\"1\" "
                + "display=\"yes\" " + "altdistance=\"0.01\" " + "altunitdist=\"mm\" " + "altunit=\"mm\" " + "/>\n";
        ret += Layers.toXMLStringStatic();

        ret += "<library>\n";
        ret += "<packages>\n";
        for (EagleObj obj : children) {
            // ret += obj.toXMLString();
            ret += "\n";
        }
        ret += "</packages>\n";
        ret += "</library>\n";
        ret += "</drawing>\n";
        ret += "</eagle>";
        return ret;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.EAGLEDOC;
    }

    @Override
    public void parseXML(Element xml) {
        parseXMLChildren(xml);
    }

    @Override
    public Element toXML() {
        Element xml = new Element("eagle");
        for (Element e : childrenToXML()) {
            xml.addContent(e);
        }
        return xml;
    }

}
