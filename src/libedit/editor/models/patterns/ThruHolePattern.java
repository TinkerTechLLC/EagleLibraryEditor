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
    boolean   firstPadSquare;

    public ThruHolePattern(String name) {
        this.name = name;
        pads = new ArrayList<Pad>();
        padCount = new PadCount(0, 0, 0, 0);
        arrayHeight = 8.0f;
        pinPitch = 2.54f;
        holeSize = 0.9f;
        firstPadSquare = false;
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
        this.firstPadSquare = firstPadSqure;
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
        System.out.println("New pad count -- " + this.padCount);
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

    public boolean isFirstPadSquare() {
        return firstPadSquare;
    }

    public void setFirstPadSquare(boolean firstPadSqure) {
        this.firstPadSquare = firstPadSqure;
    }

    @Override
    public String toString() {
        String ret = "Name: " + name + " ID: " + this.getID() + "\nPad Count -- " + padCount.toString()
                + "\nArray Height: " + arrayHeight + "\nPin Pitch: " + pinPitch + "\nHole Size: " + holeSize
                + "\nPad Size: " + padSize + "\nFirst Pad Square: " + firstPadSquare;
        return ret;
    }

}
