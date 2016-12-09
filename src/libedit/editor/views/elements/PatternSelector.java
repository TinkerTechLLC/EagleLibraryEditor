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

import libedit.editor.models.patterns.Pattern;
import libedit.editor.views.abstracts.AbstractPatternPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PatternSelector extends JPanel {

    public List<Pattern>     patterns  = new ArrayList<Pattern>();
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    JList<String>            list;
    AbstractPatternPanel     patternPanel;
    private JTextField       txtPatternName;

    /**
     * Create the panel.
     */
    public PatternSelector(AbstractPatternPanel patternPanel) {
        this.patternPanel = patternPanel;
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setLayout(new MigLayout("ins 0", "[125:125px:125px,grow]", "[][200:n,grow][][][][]"));

        JLabel lblPattern = new JLabel(patternPanel.getType() + " Pattern");
        add(lblPattern, "cell 0 0,alignx center");

        list = new JList<String>(listModel);
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

    private void addPattern() {
        String name = txtPatternName.getText();
        listModel.addElement(name);
        Pattern pattern = patternPanel.getNewPattern(name);
        patterns.add(pattern);
        patternPanel.loadPattern(pattern);
        list.setSelectedIndex(listModel.getSize() - 1);
    }

    private void deletePattern() {
        if (list.getSelectedIndex() != -1) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            patterns.remove(index);
        }
    }

}
