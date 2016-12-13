package libedit.editor.views.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import libedit.editor.models.patterns.Pattern;

@SuppressWarnings("serial")
public abstract class AbstractPatternForm extends JPanel {

    List<PatternEditor> observers = new ArrayList<PatternEditor>();

    private String      type;

    public AbstractPatternForm(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract Pattern getPattern();

    public abstract Pattern getNewPattern(String name);

    public abstract void loadPattern(Pattern pattern);

    protected abstract void updatePattern();

    public void registerObserver(PatternEditor observer) {
        observers.add(observer);
    }

    protected void updateObservers() {
        System.out.println("Form updating observers");
        for (PatternEditor o : observers) {
            o.update();
        }
    }

}
