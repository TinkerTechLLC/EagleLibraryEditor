package libedit.models.enums;

public enum Unit {
    MM, MIC, INCH, MIL;

    static public Unit parseString(String unitStr) {
        for (Unit u : Unit.values()) {
            if (unitStr.toLowerCase().equals(u.toString().toLowerCase())) {
                return u;
            }
        }
        return null;
    }

    static public String[] stringValues() {
        String[] stringVals = new String[Unit.values().length];
        for (int i = 0; i < Unit.values().length; i++) {
            stringVals[i] = Unit.values()[i].toString().toLowerCase();
        }
        return stringVals;
    }

    static public float convert(float val, Unit from, Unit to) {

        final float MM_PER_MIC = 1000;
        final float INCH_PER_MIC = 25400;
        final float MIL_PER_MIC = 25.4f;

        // Convert all to microns
        switch (from) {
        case MIC:
            break;
        case MM:
            val *= MM_PER_MIC;
            break;
        case INCH:
            val *= INCH_PER_MIC;
            break;
        case MIL:
            val *= MIL_PER_MIC;
            break;
        }

        switch (to) {
        case MIC:
            break;
        case MM:
            val /= MM_PER_MIC;
            break;
        case INCH:
            val /= INCH_PER_MIC;
            break;
        case MIL:
            val /= MIL_PER_MIC;
            break;
        }

        return val;
    }
}
