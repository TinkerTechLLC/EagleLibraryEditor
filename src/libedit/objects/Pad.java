package libedit.objects;

import org.jdom2.Element;

import libedit.abstractobjects.EagleObj;

public class Pad extends EagleObj {

    int     name;
    float   x, y, drill, diameter;
    boolean stop;

    @Override
    public int getPriority() {
        // TODO Auto-generated method stub
        return 0;
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

}
