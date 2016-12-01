package libedit.containers;

import java.util.List;

import org.jdom2.Element;

import libedit.abstractobjects.EagleContainer;
import libedit.abstractobjects.EagleObj;

public class Symbol extends EagleContainer {

    String name;

    public Symbol(String name, List<EagleObj> children) {
        super(children);
        this.name = name;
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.SYMBOL;
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
