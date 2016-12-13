package libedit.editor.views.abstracts;

import javax.swing.JPanel;

import libedit.editor.models.patterns.Pattern;

@SuppressWarnings("serial")
public abstract class AbstractPatternPanel extends JPanel {

    private String type;

    public AbstractPatternPanel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract Pattern getPattern();

    public abstract Pattern getNewPattern(String name);

    public abstract void loadPattern(Pattern pattern);

    protected abstract void updatePattern();

}
