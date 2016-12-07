package libedit.models.factories;

import java.util.ArrayList;
import java.util.List;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.enums.Layers;
import libedit.models.enums.Rot.Rotation;
import libedit.models.objects.SMD;

public class PatternFactory {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    static public class PadCount {

        public int down, right, up, left;

        public PadCount(int down, int right, int up, int left) {
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
    }

    static public class PadSize {
        public float width, height;

        public PadSize(float width, float height) {
            this.width = width;
            this.height = height;
        }

    }

    /**
     * Creates a list of SMD pads centered around (0,0) point, based upon
     * pattern input parameters
     * 
     * @param padCount
     *            PadCount object
     * @param padShape
     *            PadShape object
     * @param pitch
     *            Distance from center to center of pads
     * @param height
     *            Distance between outside edges of top and bottom pads
     * @param width
     *            Distance between outside edges of left and right pads
     * @param topLayer
     *            If true, SMD pads are placed on top layer, otherwise they are
     *            placed on bottom
     * @return List of SMD pads
     */
    static public List<EagleObj> createSMDPattern(
            PadCount padCount, PadSize padShape,
            float pitch, float oaWidth, float oaHeight,
            boolean topLayer) {
        List<EagleObj> pattern = new ArrayList<EagleObj>();

        int layer = topLayer ? Layers.TOP : Layers.BOTTOM;
        float x, y;
        PadCount pc = padCount;
        List<Float> params = new ArrayList<Float>();
        final int PARAM_COUNT = 2;
        for (int i = 0; i < pc.down; i++) {
            x = -(((float) pc.down - 1) / 2 * pitch) + i * pitch;
            y = -(oaHeight - padShape.height) / 2;
            params.add(x);
            params.add(y);
        }
        for (int i = 0; i < pc.right; i++) {
            x = (oaWidth - padShape.width) / 2;
            y = -(((float) pc.right - 1) / 2 * pitch) + i * pitch;
            params.add(x);
            params.add(y);
        }
        for (int i = 0; i < pc.up; i++) {
            x = (((float) pc.up - 1) / 2 * pitch) - i * pitch;
            y = (oaHeight - padShape.height) / 2;
            params.add(x);
            params.add(y);
        }
        for (int i = 0; i < pc.left; i++) {
            x = -(oaWidth - padShape.width) / 2;
            y = (((float) pc.left - 1) / 2 * pitch) - i * pitch;
            params.add(x);
            params.add(y);
        }

        for (int i = 0; i < pc.totalPads(); i++) {
            Rotation rot;
            int pin = i + 1;
            if (pin < pc.firstPadNum(Direction.RIGHT)) {
                rot = Rotation.R0;
            }
            else if (pin >= pc.firstPadNum(Direction.RIGHT) &&
                    pin < pc.firstPadNum(Direction.UP)) {
                rot = Rotation.R90;
            }
            else if (pin >= pc.firstPadNum(Direction.UP) &&
                    pin < pc.firstPadNum(Direction.LEFT)) {
                rot = Rotation.R0;
            }
            else {
                rot = Rotation.R90;
            }
            SMD smd = new SMD(Integer.toString(pin),
                    params.get(i * PARAM_COUNT), params.get(i * PARAM_COUNT + 1),
                    padShape.width, padShape.height, layer, rot);
            pattern.add(smd);
        }

        return pattern;
    }
}
