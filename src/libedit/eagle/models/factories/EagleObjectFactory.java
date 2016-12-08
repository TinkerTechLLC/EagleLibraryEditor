package libedit.eagle.models.factories;

import org.jdom2.Element;

import libedit.eagle.models.abstractobjects.EagleObj;
import libedit.eagle.models.containers.Device;
import libedit.eagle.models.containers.DeviceSet;
import libedit.eagle.models.containers.Library;
import libedit.eagle.models.containers.ObjList;
import libedit.eagle.models.containers.Pkg;
import libedit.eagle.models.containers.Symbol;
import libedit.eagle.models.objects.Circle;
import libedit.eagle.models.objects.Description;
import libedit.eagle.models.objects.Grid;
import libedit.eagle.models.objects.Layer;
import libedit.eagle.models.objects.Pad;
import libedit.eagle.models.objects.Pin;
import libedit.eagle.models.objects.Polygon;
import libedit.eagle.models.objects.Rect;
import libedit.eagle.models.objects.SMD;
import libedit.eagle.models.objects.Setting;
import libedit.eagle.models.objects.Text;
import libedit.eagle.models.objects.Wire;
import libedit.eagle.models.symbolobj.Connect;
import libedit.eagle.models.symbolobj.Gate;
import libedit.eagle.models.symbolobj.Technology;

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
