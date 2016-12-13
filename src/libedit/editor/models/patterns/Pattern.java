package libedit.editor.models.patterns;

abstract public class Pattern {

    private int        id;
    private static int idGen = 0;
    String             name;
    PatternType        type;

    public Pattern(String name, PatternType type) {
        id = idGen++;
        this.name = name;
        this.type = type;
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

    public PatternType getType() {
        return type;
    }
}
