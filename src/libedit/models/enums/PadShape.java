package libedit.models.enums;

public enum PadShape {
    ROUND, SQUARE;

    static public PadShape parseString(String shape) {
        if (shape == null) {
            return ROUND;
        }
        for (PadShape p : PadShape.values()) {
            if (shape.toLowerCase().equals(p.toString().toLowerCase())) {
                return p;
            }
        }
        return null;
    }
}
