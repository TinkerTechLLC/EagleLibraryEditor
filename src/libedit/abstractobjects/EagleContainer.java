package libedit.abstractobjects;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import libedit.containers.Device;
import libedit.containers.Library;
import libedit.containers.ObjList;
import libedit.containers.Pkg;
import libedit.objects.Circle;
import libedit.objects.Pad;
import libedit.objects.Pin;
import libedit.objects.Polygon;
import libedit.objects.Rect;
import libedit.objects.SMD;
import libedit.objects.Text;
import libedit.objects.Wire;

public abstract class EagleContainer extends EagleObj {

    protected List<EagleObj> children;

    public EagleContainer(List<EagleObj> children) {
        super();
        this.children = children;
    }

    public EagleContainer(Element xml) {
        super(xml);
    }

    public void parseXMLChildren(Element xml) {

        // Make sure the object list is initialized before modifying it
        if (children == null) {
            children = new ArrayList<EagleObj>();
        }
        List<Element> childrenElements = xml.getChildren();
        for (Element e : childrenElements) {
            EagleObj thisObj = null;

            // List objects -- Plural element name ending indicates list
            if (e.getName().endsWith("s")) {
                thisObj = new ObjList(e);
            }

            // Basic objects
            else if (e.getName().equals("circle")) {
                thisObj = new Circle(e);
            }
            else if (e.getName().equals("pad")) {
                thisObj = new Pad(e);
            }
            else if (e.getName().equals("pin")) {
                thisObj = new Pin(e);
            }
            else if (e.getName().equals("polygon")) {
                thisObj = new Polygon(e);
            }
            else if (e.getName().equals("Rect")) {
                thisObj = new Rect(e);
            }
            else if (e.getName().equals("smd")) {
                thisObj = new SMD(e);
            }
            else if (e.getName().equals("text")) {
                thisObj = new Text(e);
            }
            else if (e.getName().equals("wire")) {
                thisObj = new Wire(e);
            }

            // Containers
            else if (e.getName().equals("device")) {
                thisObj = new Device(e);
            }
            else if (e.getName().equals("package")) {
                thisObj = new Pkg(e);
            }
            else if (e.getName().equals("library")) {
                thisObj = new Library(e);
            }
            children.add(thisObj);
        }
    }

    public List<EagleObj> getChildren() {
        return children;
    }

    public void setChildren(List<EagleObj> children) {
        this.children = children;
    }

    public List<Element> childrenToXML() {
        List<Element> childrenXML = new ArrayList<Element>();
        for (EagleObj o : children) {
            childrenXML.add(o.toXML());
        }
        return childrenXML;
    }

}
