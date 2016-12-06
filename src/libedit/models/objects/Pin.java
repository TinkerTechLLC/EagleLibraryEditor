package libedit.models.objects;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.enums.Rot;
import libedit.models.enums.Rot.Rotation;

public class Pin extends EagleObj {

    String    name;
    float     x, y, length;
    Direction dir;
    int       swapLevel;
    Rotation  rot;

    public enum Direction {
        pas, pwr;
    }

    public Pin(Element xml) {
        super(xml);
    }

    public Pin(String name, float x, float y, float length, Direction dir, int swapLevel, Rotation rot) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.length = length;
        this.dir = dir;
        this.swapLevel = swapLevel;
        this.rot = rot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public int getSwapLevel() {
        return swapLevel;
    }

    public void setSwapLevel(int swapLevel) {
        this.swapLevel = swapLevel;
    }

    public Rotation getRot() {
        return rot;
    }

    public void setRot(Rotation rot) {
        this.rot = rot;
    }

    @Override
    public void parseXML(Element xml) {
        try {
            name = xml.getAttributeValue("name");
            x = xml.getAttribute("x").getFloatValue();
            y = xml.getAttribute("y").getFloatValue();
            dir = parseDir(xml.getAttributeValue("direction"));
            swapLevel = xml.getAttribute("swaplevel").getIntValue();
            rot = Rot.parseRotation(xml.getAttributeValue("rot"));
        } catch (DataConversionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Element toXML() {
        Element xml = new Element("pin");
        xml.setAttribute("name", name);
        xml.setAttribute("x", Float.toString(x));
        xml.setAttribute("y", Float.toString(x));
        xml.setAttribute("direction", dir.toString());
        xml.setAttribute("swapLevel", Integer.toString(swapLevel));
        xml.setAttribute("rot", rot.toString());
        return xml;
    }

    private Direction parseDir(String dir) {
        if (dir.equals(Direction.pwr.toString())) {
            return Direction.pwr;
        }
        else {
            return Direction.pas;
        }
    }

    @Override
    protected void setPriority() {
        this.priority = Priority.PIN;
    }

    @Override
    public void printContents(int tabLevel) {
        // TODO Auto-generated method stub

    }

}
