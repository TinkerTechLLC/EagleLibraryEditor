package libedit.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pkg extends EagleObj {
    private String         name;
    private List<EagleObj> objects;

    public Pkg(String name) {
        this.name = name;
        objects = new ArrayList<EagleObj>();
    }

    public void createPads(int pinCount, float padHeight, float padWidth, float pitch, float overallHeight, int layer) {
        float yOffset = (overallHeight - padHeight) / 2;
        float xOffset;
        if ((pinCount / 2) % 2 == 0) {
            xOffset = (pinCount / 4 - 1) * pitch + (pitch / 2);
        }
        else {
            xOffset = (pinCount / 2 - 1) / 2 * pitch;
        }
        for (int i = 0; i < pinCount; i++) {
            int xPos = i < pinCount / 2 ? i : i - pinCount / 2;
            float x = -xOffset + xPos * pitch;
            float y = i < pinCount / 2 ? yOffset : -yOffset;
            this.addObject(new SMD(i + 1, x, y, padWidth, padHeight, layer));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SMD> getSmd() {
        List<SMD> ret = new ArrayList<SMD>();
        for (EagleObj obj : objects) {
            if (obj.isSMD()) {
                ret.add((SMD) obj);
            }
        }
        return ret;
    }

    public List<Wire> getWire() {
        List<Wire> ret = new ArrayList<Wire>();
        for (EagleObj obj : objects) {
            if (obj.isWire()) {
                ret.add((Wire) obj);
            }
        }
        return ret;
    }

    public List<Text> getText() {
        List<Text> ret = new ArrayList<Text>();
        for (EagleObj obj : objects) {
            if (obj.isText()) {
                ret.add((Text) obj);
            }
        }
        return ret;
    }

    public List<Rect> getRectangle() {
        List<Rect> ret = new ArrayList<Rect>();
        for (EagleObj obj : objects) {
            if (obj.isRectangle()) {
                ret.add((Rect) obj);
            }
        }
        return ret;
    }

    @Override
    public String toXMLString() {
        String ret = "";
        ret += "<package name=\"" + name + "\" >\n";
        for (EagleObj thisObj : objects) {
            ret += thisObj.toXMLString();
            ret += "\n";
        }
        ret += "</package>";
        return ret;
    }

    @Override
    public int getPriority() {
        return Priority.PACKAGE;
    }

    public void addObject(EagleObj obj) {
        objects.add(obj);
        Collections.sort(objects);
    }

}
