package libedit.eagle.models.abstractobjects;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import libedit.eagle.models.factories.EagleObjectFactory;

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
            EagleObj thisObj = EagleObjectFactory.createObjFromXML(e);
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
        // System.out.println("Converting children of: " +
        // this.getClass().getSimpleName());
        for (EagleObj o : children) {
            childrenXML.add(o.toXML());
        }
        return childrenXML;
    }

    @Override
    public void printContents(int tabLevel) {
        printAttributes(tabLevel);
        for (EagleObj o : this.getChildren()) {
            printTabs(tabLevel);
            try {
                System.out.println(o.getPriority().toString());
                o.printContents(tabLevel + 1);
            } catch (NullPointerException e) {
                System.out.println("No priority available");
            }
        }
    }

    abstract protected void printAttributes(int tabLevel);

    public <T> EagleObj getObjByType(Class<T> type) {
        for (EagleObj o : getChildren()) {
            if (o.getClass() == type) {
                return o;
            }
            else if (EagleContainer.class.isAssignableFrom(o.getClass())) {
                EagleObj ret = ((EagleContainer) o).getObjByType(type);
                if (ret == null) {
                    continue;
                }
                else {
                    return ret;
                }
            }
        }
        return null;
    }
}
