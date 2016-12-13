package libedit.editor.views.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import libedit.eagle.models.factories.PackageBuilder;
import libedit.editor.models.patterns.Pattern;
import libedit.editor.views.elements.PatternSelector;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PatternEditor extends JPanel {

    List<PackageBuilder> observers = new ArrayList<PackageBuilder>();
    PatternSelector      selector;
    AbstractPatternForm  patternForm;
    boolean              initialized;

    /**
     * Create the panel.
     */
    public PatternEditor(AbstractPatternForm patternForm) {
        setLayout(new MigLayout("", "[125,grow][]", "[grow]"));

        this.patternForm = patternForm;
        this.patternForm.registerObserver(this);
        selector = new PatternSelector(this.patternForm);
        selector.registerObserver(this);
        add(selector, "cell 0 0,grow");
        add(this.patternForm, "cell 1 0,grow");
        initialized = false;
    }

    public void init() {
        // If the editor hasn't been initialized already, add a default pattern
        // to the selector
        if (!initialized) {
            selector.addPattern("Pattern 1");
            initialized = true;
        }
    }

    public void update() {
        System.out.println("Editor updating observers");
        for (PackageBuilder o : observers) {
            o.update();
        }
    }

    public List<Pattern> getPatterns() {
        return selector.getPatterns();
    }

    public void registerObserver(PackageBuilder observer) {
        observers.add(observer);
    }

}
