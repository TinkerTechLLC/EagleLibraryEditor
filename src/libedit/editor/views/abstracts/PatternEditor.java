package libedit.editor.views.abstracts;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import libedit.editor.models.patterns.Pattern;
import libedit.editor.views.elements.PatternSelector;
import net.miginfocom.swing.MigLayout;

public class PatternEditor extends JPanel {

    List<Pattern> patterns = new ArrayList<Pattern>();

    /**
     * Create the panel.
     */
    public PatternEditor(AbstractPatternPanel patternPanel) {
        setLayout(new MigLayout("", "[125][grow]", "[grow]"));

        add(new PatternSelector(patternPanel), "cell 0 0,grow");
        add(patternPanel, "cell 1 0,grow");
    }

}
