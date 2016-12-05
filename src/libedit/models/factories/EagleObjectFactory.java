package libedit.models.factories;

import org.jdom2.Element;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.containers.Device;
import libedit.models.containers.DeviceSet;
import libedit.models.containers.Library;
import libedit.models.containers.ObjList;
import libedit.models.containers.Pkg;
import libedit.models.containers.Symbol;
import libedit.models.objects.Circle;
import libedit.models.objects.Description;
import libedit.models.objects.Grid;
import libedit.models.objects.Layer;
import libedit.models.objects.Pad;
import libedit.models.objects.Pin;
import libedit.models.objects.Polygon;
import libedit.models.objects.Rect;
import libedit.models.objects.SMD;
import libedit.models.objects.Setting;
import libedit.models.objects.Text;
import libedit.models.objects.Wire;
import libedit.models.symbolobj.Connect;
import libedit.models.symbolobj.Gate;
import libedit.models.symbolobj.Technology;

public class EagleObjectFactory {

    /**
     * Creates an EagleObj of appropriate subclass given an XML element
     * 
     * @param e
     *            A jdom2 XML element
     * @return EagleObj of appropriate subclass
     */
    static public EagleObj createObjFromXML(Element e) {

        EagleObj thisObj = null;

        // List objects -- Plural element name ending indicates list
        if (e.getName().endsWith("s") || e.getName().equals("drawing")) {
            thisObj = new ObjList(e);
        }

        // Non-construction objects
        else if (e.getName().equals("setting")) {
            thisObj = new Setting(e);
        }
        else if (e.getName().equals("grid")) {
            thisObj = new Grid(e);
        }
        else if (e.getName().equals("layer")) {
            thisObj = new Layer(e);
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
        else if (e.getName().equals("rectangle")) {
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

        // Meta objects
        else if (e.getName().equals("description")) {
            thisObj = new Description(e);
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
        else if (e.getName().equals("symbol")) {
            thisObj = new Symbol(e);
        }
        else if (e.getName().equals("deviceset")) {
            thisObj = new DeviceSet(e);
        }

        // Symbol objects
        else if (e.getName().equals("gate")) {
            thisObj = new Gate(e);
        }
        else if (e.getName().equals("connect")) {
            thisObj = new Connect(e);
        }
        else if (e.getName().equals("technology")) {
            thisObj = new Technology(e);
        }

        return thisObj;
    }
}
