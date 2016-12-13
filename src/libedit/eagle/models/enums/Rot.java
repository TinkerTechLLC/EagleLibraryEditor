package libedit.eagle.models.enums;

public class Rot {

    public static enum Rotation {
        R0, R90, R180, R270;
    }

    public static Rotation parseRotation(String rotStr) {
        Rotation rot = Rotation.R0;
        if (rotStr == null || rotStr.equals(Rotation.R0.toString())) {

        }
        else if (rotStr.equals(Rotation.R90.toString())) {
            rot = Rotation.R90;
        }
        else if (rotStr.equals(Rotation.R180.toString())) {
            rot = Rotation.R180;
        }
        else if (rotStr.equals(Rotation.R270.toString())) {
            rot = Rotation.R270;
        }
        return rot;
    }
}
