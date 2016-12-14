package libedit.editor.models.patterns;

import java.util.ArrayList;
import java.util.List;

import libedit.eagle.models.enums.Layers;
import libedit.eagle.models.objects.Wire;

public class WirePattern extends Pattern {

    List<Wire>      wires         = new ArrayList<Wire>();
    WirePatternType subType;
    float           width;
    float           height;
    float           length;
    float           offset;
    float           wireThickness = 0.5f;

    public enum WirePatternType {
        OUTLINE(0), HORIZ(1), VERT(2);

        int tabIndex;

        WirePatternType(int tabIndex) {
            this.tabIndex = tabIndex;
        }

        public int getTabIndex() {
            return tabIndex;
        }
    }

    public WirePattern(String name, float width, float height) {
        super(name, PatternType.WIRE);
        this.setOutline(width, height);
    }

    public WirePattern(String name, float length, float offset, boolean vertical) {
        super(name, PatternType.WIRE);
        if (this.subType == WirePatternType.HORIZ) {
            this.setHoriz(length, offset);
        }
        else {
            this.setVert(length, offset);
        }
    }

    public List<Wire> getWires() {
        return wires;
    }

    public WirePatternType getSubType() {
        return subType;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getLength() {
        return length;
    }

    public float getOffset() {
        return offset;
    }

    public void setOutline(float width, float height) {
        this.subType = WirePatternType.OUTLINE;
        this.width = width;
        this.height = height;
        wires.clear();
        wires.add(new Wire(width / 2, height / 2, -width / 2, height / 2, wireThickness, Layers.tPLACE));
        wires.add(new Wire(width / 2, -height / 2, -width / 2, -height / 2, wireThickness, Layers.tPLACE));
        wires.add(new Wire(width / 2, height / 2, width / 2, -height / 2, wireThickness, Layers.tPLACE));
        wires.add(new Wire(-width / 2, height / 2, -width / 2, -height / 2, wireThickness, Layers.tPLACE));
    }

    public void setHoriz(float length, float offset) {
        this.subType = WirePatternType.HORIZ;
        this.width = 0;
        this.height = 0;
        this.length = length;
        this.offset = offset;
        wires.clear();
        wires.add(new Wire(length / 2, offset, -length / 2, offset, 1.25f, Layers.tPLACE));
    }

    public void setVert(float length, float offset) {
        this.subType = WirePatternType.VERT;
        this.width = 0;
        this.height = 0;
        this.length = length;
        this.offset = offset;
        wires.clear();
        wires.add(new Wire(offset, length / 2, offset, -length / 2, 1.25f, Layers.tPLACE));
    }

}
