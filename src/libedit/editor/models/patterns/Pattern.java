package libedit.editor.models.patterns;

public class Pattern {

    private int        id;
    private static int idGen = 0;

    public Pattern() {
        id = idGen++;
    }

    public int getID() {
        return id;
    }
}
