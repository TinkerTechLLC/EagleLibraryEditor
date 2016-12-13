package libedit.editor.models.patterns;

abstract public class Pattern {

    private int        id;
    private static int idGen = 0;
    String             name;

    public Pattern(String name) {
        id = idGen++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return id;
    }
}
