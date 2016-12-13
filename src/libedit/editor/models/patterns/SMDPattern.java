package libedit.editor.models.patterns;

import java.util.ArrayList;
import java.util.List;

import libedit.eagle.models.factories.PatternFactory.PadCount;
import libedit.eagle.models.factories.PatternFactory.PadSize;
import libedit.eagle.models.objects.Pad;

public class SMDPattern extends Pattern {

    PadCount  padCount;
    PadSize   padSize;
    List<Pad> pads;
    float     arrayHeight;
    float     arrayWidth;
    float     pinPitch;
    boolean   topLayer;

    public SMDPattern(String name) {
        super(name, PatternType.SMD);
        pads = new ArrayList<Pad>();
        padCount = new PadCount(0, 0, 0, 0);
        padSize = new PadSize(1.0f, 2.0f);
        arrayHeight = 8.0f;
        arrayWidth = 8.0f;
        pinPitch = 2.0f;
        topLayer = true;
    }

    public SMDPattern(String name, PadCount padCount, PadSize padSize,
            List<Pad> pads,
            float arrayHeight,
            float arrayWidth,
            float pinPitch, boolean isTopLayer) {
        super(name, PatternType.SMD);
        this.padCount = padCount;
        this.padSize = padSize;
        this.pads = pads;
        this.arrayHeight = arrayHeight;
        this.arrayWidth = arrayWidth;
        this.pinPitch = pinPitch;
        this.topLayer = isTopLayer;
    }

    public PadCount getPadCount() {
        return padCount;
    }

    public void setPadCount(PadCount padCount) {
        this.padCount = padCount;
    }

    public PadSize getPadSize() {
        return padSize;
    }

    public void setPadSize(PadSize padSize) {
        this.padSize = padSize;
    }

    public List<Pad> getPads() {
        return pads;
    }

    public void setPads(List<Pad> pads) {
        this.pads = pads;
    }

    public float getArrayHeight() {
        return arrayHeight;
    }

    public void setArrayHeight(float arrayHeight) {
        this.arrayHeight = arrayHeight;
    }

    public float getArrayWidth() {
        return arrayWidth;
    }

    public void setArrayWidth(float arrayWidth) {
        this.arrayWidth = arrayWidth;
    }

    public float getPinPitch() {
        return pinPitch;
    }

    public void setPinPitch(float pinPitch) {
        this.pinPitch = pinPitch;
    }

    public boolean isTopLayer() {
        return topLayer;
    }

    public void setTopLayer(boolean isTopLayer) {
        this.topLayer = isTopLayer;
    }

    @Override
    public String toString() {
        String ret = "Name: " + name + " ID: " + this.getID() + "\nPad Count -- " + padCount.toString()
                + "\nArray Width: " + arrayWidth + "\nArray Height: " + arrayHeight + "\nPin Pitch: " + pinPitch
                + "\nPad Size -- " + padSize.toString() + "\nTop layer: " + topLayer;
        return ret;
    }

}
