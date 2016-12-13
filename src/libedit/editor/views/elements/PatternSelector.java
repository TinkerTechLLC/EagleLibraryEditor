package libedit.editor.views.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import libedit.editor.models.patterns.Pattern;
import libedit.editor.views.abstracts.AbstractPatternForm;
import libedit.editor.views.abstracts.PatternEditor;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PatternSelector extends JPanel {

    private List<PatternEditor>      observers = new ArrayList<PatternEditor>();
    private List<Pattern>            patterns  = new ArrayList<Pattern>();
    private DefaultListModel<String> listModel = new DefaultListModel<String>();
    private JList<String>            list;
    private AbstractPatternForm      patternPanel;
    private JTextField               txtPatternName;
    private int                      lastSelected;

    /**
     * Create the panel.
     */
    public PatternSelector(AbstractPatternForm patternPanel) {
        lastSelected = 0;

        this.patternPanel = patternPanel;
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setLayout(new MigLayout("ins 0", "[175,grow]", "[][200:n,grow][][][][]"));

        JLabel lblPattern = new JLabel(patternPanel.getType() + " Pattern");
        add(lblPattern, "cell 0 0,alignx center");

        list = new JList<String>(listModel);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectPattern(list.getSelectedIndex());
            }
        });
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(list, "cell 0 1,grow");

        JButton btnAddPattern = new JButton("Add Pattern");
        btnAddPattern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                addPattern();
            }
        });

        txtPatternName = new JTextField();
        txtPatternName.setHorizontalAlignment(SwingConstants.CENTER);
        txtPatternName.setText("New Pattern Name");
        add(txtPatternName, "cell 0 2,growx");
        txtPatternName.setColumns(10);

        JButton btnRenamePattern = new JButton("Rename Pattern");
        btnRenamePattern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                renamePattern();
            }
        });
        add(btnRenamePattern, "cell 0 3,growx");
        add(btnAddPattern, "cell 0 4,growx");

        JButton btnDeletePattern = new JButton("Delete Pattern");
        btnDeletePattern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePattern();
            }
        });
        add(btnDeletePattern, "cell 0 5,growx");

    }

    private void selectPattern(int patternNum) {
        lastSelected = patternNum;
        if (lastSelected == -1) {
            lastSelected = 0;
            list.setSelectedIndex(lastSelected);
        }
        patternPanel.loadPattern(patterns.get(lastSelected));
    }

    private void addPattern() {
        String name = txtPatternName.getText();
        addPattern(name);
    }

    public void addPattern(String name) {

        // Create a new pattern
        listModel.addElement(name);
        Pattern pattern = patternPanel.getNewPattern(name);

        // Add it to the list and populate the text fields in the pattern panel
        patterns.add(pattern);
        patternPanel.loadPattern(pattern);

        lastSelected = listModel.size() - 1;
        list.setSelectedIndex(lastSelected);
    }

    private void renamePattern() {
        int index = list.getSelectedIndex();
        String name = this.txtPatternName.getText();
        if (index != -1) {
            Pattern p = patterns.get(list.getSelectedIndex());
            p.setName(name);
            listModel.remove(index);
            listModel.add(index, name);
            list.setSelectedIndex(index);
        }
    }

    private void deletePattern() {
        // Don't allow deletion of the last pattern
        if (listModel.size() == 1) {
            return;
        }
        if (list.getSelectedIndex() != -1) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            patterns.remove(index);
            if (index != 0) {
                lastSelected = index - 1;
            }
            else {
                lastSelected = 0;
            }
            list.setSelectedIndex(lastSelected);
            selectPattern(lastSelected);
        }
        updateObservers();
    }

    public List<Pattern> getPatterns() {
        return this.patterns;
    }

    public void registerObserver(PatternEditor observer) {
        observers.add(observer);
    }

    private void updateObservers() {
        for (PatternEditor o : observers) {
            o.update();
        }
    }
}
