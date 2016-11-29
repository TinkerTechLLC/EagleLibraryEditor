package libedit.enums;

public class Rot {

    public static enum Rotation {
        R0, R90, R180, R270;
    }

    public static Rotation parseRotation(String rotStr) {
        Rotation rot;
        if (rotStr.equals(Rotation.R90.toString())) {
            rot = Rotation.R90;
        }
        if (rotStr.equals(Rotation.R180.toString())) {
            rot = Rotation.R180;
        }
        if (rotStr.equals(Rotation.R270.toString())) {
            rot = Rotation.R270;
        }
        else {
            rot = Rotation.R0;
        }
        return rot;
    }
}
