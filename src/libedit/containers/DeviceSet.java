package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class DeviceSet extends EagleContainer {

    String name;
    String packageStr;

    public DeviceSet(Element xml) {
        super(xml);
    }

    public DeviceSet(String name, String packageStr, List<EagleObj> children) {
        super(children);
        this.name = name;
        this.packageStr = packageStr;
        this.children = children;
    }

    @Override
    public void parseXML(Element xml) {
        // TODO Auto-generated method stub

    }

    @Override
    public Element toXML() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.DEVICESET;
    }

}
