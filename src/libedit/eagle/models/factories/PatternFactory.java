package libedit.eagle.models.factories;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import libedit.eagle.models.abstractobjects.EagleObj;
import libedit.eagle.models.enums.Layers;
import libedit.eagle.models.enums.PadShape;
import libedit.eagle.models.enums.Rot.Rotation;
import libedit.eagle.models.objects.Pad;
import libedit.eagle.models.objects.SMD;
import libedit.eagle.models.objects.Wire;
import libedit.editor.models.patterns.SMDPattern;
import libedit.editor.models.patterns.ThruHolePattern;

public class PatternFactory {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    static public class PadCount {

        public int down, right, up, left;

        public PadCount(int down, int right, int up, int left) {
            System.out.println("New up val: " + up);
            this.down = down;
            this.right = right;
            this.up = up;
            this.left = left;
        }

        public int totalPads() {
            return down + right + up + left;
        }

        public int firstPadNum(Direction dir) {
            switch (dir) {
            case DOWN:
                return 1;
            case RIGHT:
                return 1 + down;
            case UP:
                return 1 + down + right;
            case LEFT:
                return 1 + down + right + up;
            default:
                return 0;
            }
        }

        public String toString() {
            String ret = "Down: " + down + " Right: " + right + " Up: " + up + " Left: " + left;
            return ret;
        }
    }

    static public class PadSize {
        public float width, height;

        public PadSize(float width, float height) {
            this.width = width;
            this.height = height;
        }

        public String toString() {
            String ret = "Width: " + width + " Height: " + width;
            return ret;
        }

    }

    static public List<Pad> thruPadListFromPattern(ThruHolePattern pattern, int startingPin) {
        List<Pad> thruList = new ArrayList<Pad>();

        float x, y;
        PadCount pc = pattern.getPadCount();
        List<Float> params = new ArrayList<Float>();
        final int PARAM_COUNT = 2;
        float oaHeight = pattern.getArrayHeight();
        float pitch = pattern.getPinPitch();

        // Down pads
        for (int i = 0; i < pc.down; i++) {
            x = -(((float) pc.down - 1) / 2 * pitch) + i * pitch;
            y = -(oaHeight) / 2;
            params.add(x);
            params.add(y);
        }

        // Up pads
        for (int i = 0; i < pc.up; i++) {
            x = (((float) pc.up - 1) / 2 * pitch) - i * pitch;
            y = (oaHeight) / 2;
            params.add(x);
            params.add(y);
        }

        for (int i = 0; i < pc.totalPads(); i++) {
            int pin = i + startingPin;
            PadShape ps;
            if (i == 0) {
                ps = pattern.isFirstPadSquare() ? PadShape.SQUARE : PadShape.ROUND;
            }
            else {
                ps = PadShape.ROUND;
            }
            Pad thruPad = new Pad(Integer.toString(pin), params.get(i * PARAM_COUNT),
                    params.get(i * PARAM_COUNT + 1), pattern.getHoleSize(),
                    pattern.getPadSize(), ps, false);
            thruList.add(thruPad);
        }
        return thruList;
    }

    /**
     * Creates a list of SMD pads centered around (0,0) point, based upon the
     * provided pattern
     * 
     * @param pattern
     *            SMDPattern object
     * @param startingPin
     *            Number of first pin
     * @return
     */
    static public List<SMD> smdListFromPattern(SMDPattern pattern, int startingPin) {
        List<SMD> smdList = new ArrayList<SMD>();

        int layer = pattern.isTopLayer() ? Layers.TOP : Layers.BOTTOM;
        float x, y;
        PadCount pc = pattern.getPadCount();
        List<Float> params = new ArrayList<Float>();
        final int PARAM_COUNT = 2;
        float oaHeight = pattern.getArrayHeight();
        float oaWidth = pattern.getArrayWidth();
        float pitch = pattern.getPinPitch();
        PadSize padSize = pattern.getPadSize();

        // Down pads
        for (int i = 0; i < pc.down; i++) {
            x = -(((float) pc.down - 1) / 2 * pitch) + i * pitch;
            y = -(oaHeight - padSize.height) / 2;
            params.add(x);
            params.add(y);
        }

        // Right pads
        for (int i = 0; i < pc.right; i++) {
            x = (oaWidth - padSize.height) / 2;
            y = -(((float) pc.right - 1) / 2 * pitch) + i * pitch;
            params.add(x);
            params.add(y);
        }

        // Up pads
        for (int i = 0; i < pc.up; i++) {
            x = (((float) pc.up - 1) / 2 * pitch) - i * pitch;
            y = (oaHeight - padSize.height) / 2;
            params.add(x);
            params.add(y);
        }

        // Left pads
        for (int i = 0; i < pc.left; i++) {
            x = -(oaWidth - padSize.height) / 2;
            y = (((float) pc.left - 1) / 2 * pitch) - i * pitch;
            params.add(x);
            params.add(y);
        }

        // Set rotation for each pad and add to return list
        for (int i = 0; i < pc.totalPads(); i++) {
            Rotation rot;
            int pin = i + startingPin;
            if (pin < pc.firstPadNum(Direction.RIGHT)) {
                rot = Rotation.R0;
            }
            else if (pin >= pc.firstPadNum(Direction.RIGHT) && pin < pc.firstPadNum(Direction.UP)) {
                rot = Rotation.R90;
            }
            else if (pin >= pc.firstPadNum(Direction.UP) && pin < pc.firstPadNum(Direction.LEFT)) {
                rot = Rotation.R0;
            }
            else {
                rot = Rotation.R90;
            }

            SMD smd = new SMD(Integer.toString(pin), params.get(i * PARAM_COUNT), params.get(i * PARAM_COUNT + 1),
                    padSize.width, padSize.height, layer, rot);
            smdList.add(smd);
        }

        return smdList;
    }

    static public List<EagleObj> createSilkOutline(float width, float height, boolean topLayer) {
        List<EagleObj> outline = new ArrayList<EagleObj>();

        int layer = topLayer ? Layers.tPLACE : Layers.bPLACE;
        float wireWidth = 0.25f;

        List<Point2D.Float> corners = new ArrayList<Point2D.Float>();
        final int CORNER_COUNT = 4;
        for (int i = 0; i < CORNER_COUNT; i++) {
            int wSign = i < 2 ? 1 : -1;
            int hSign = i == 1 || i == 2 ? 1 : -1;
            corners.add(new Point2D.Float(wSign * width / 2, hSign * height / 2));
        }

        for (int i = 0; i < CORNER_COUNT; i++) {
            Point2D.Float p1 = corners.get(i);
            Point2D.Float p2 = i + 1 < CORNER_COUNT ? corners.get(i + 1) : corners.get(0);
            outline.add(new Wire(p1.x, p1.y, p2.x, p2.y, wireWidth, layer));
        }

        return outline;
    }
}
