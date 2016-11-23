package libedit.objects;

public class SMD extends EagleObj {
    private int   padNum;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private int   layer;

    public SMD(int padNum, float x, float y, float dx, float dy, int layer) {
        this.padNum = padNum;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.layer = layer;

    }

    public int getPadNum() {
        return padNum;
    }

    public void setPadNum(int padNum) {
        this.padNum = padNum;
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

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public String toXMLString() {
        String ret = "<smd name=\"" + padNum + "\" " +
                "x=\"" + x + "\" " +
                "y=\"" + y + "\" " +
                "dx=\"" + dx + "\" " +
                "dy=\"" + dy + "\" " +
                "layer=\"" + layer + "\"/>";
        return ret;
    }

    @Override
    public int getPriority() {
        return Priority.SMD;
    }
}
