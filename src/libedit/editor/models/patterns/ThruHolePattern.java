package libedit.editor.models.patterns;

import java.util.ArrayList;
import java.util.List;

import libedit.eagle.models.factories.PatternFactory.PadCount;
import libedit.eagle.models.objects.Pad;

public class ThruHolePattern extends Pattern {

    String    name;
    PadCount  padCount;
    List<Pad> pads;
    float     arrayHeight;
    float     pinPitch;
    float     holeSize;
    float     padSize;
    boolean   firstPadSqure;

    public ThruHolePattern(String name) {
        this.name = name;
        pads = new ArrayList<Pad>();
        padCount = new PadCount(0, 0, 0, 0);
        arrayHeight = 8.0f;
        pinPitch = 2.54f;
        holeSize = 0.9f;
        firstPadSqure = false;
    }

    public ThruHolePattern(String name, PadCount padCount, List<Pad> pads, float arrayHeight, float pinPitch,
            float holeSize, float padSize, boolean firstPadSqure) {
        super();
        this.name = name;
        this.padCount = padCount;
        this.pads = pads;
        this.arrayHeight = arrayHeight;
        this.pinPitch = pinPitch;
        this.holeSize = holeSize;
        this.padSize = padSize;
        this.firstPadSqure = firstPadSqure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PadCount getPadCount() {
        return padCount;
    }

    public void setPadCount(PadCount padCount) {
        this.padCount = padCount;
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

    public float getPinPitch() {
        return pinPitch;
    }

    public void setPinPitch(float pinPitch) {
        this.pinPitch = pinPitch;
    }

    public float getHoleSize() {
        return holeSize;
    }

    public void setHoleSize(float holeSize) {
        this.holeSize = holeSize;
    }

    public float getPadSize() {
        return padSize;
    }

    public void setPadSize(float padSize) {
        this.padSize = padSize;
    }

    public boolean isFirstPadSqure() {
        return firstPadSqure;
    }

    public void setFirstPadSqure(boolean firstPadSqure) {
        this.firstPadSqure = firstPadSqure;
    }

}
