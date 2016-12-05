package libedit.models.enums;

public enum Unit {
    INCH, MIL, MIC, MM;

    static public Unit parseString(String unitStr) {
        for (Unit u : Unit.values()) {
            if (unitStr.toLowerCase().equals(u.toString().toLowerCase())) {
                return u;
            }
        }
        return null;
    }
}
