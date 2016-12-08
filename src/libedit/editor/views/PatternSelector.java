package libedit.editor.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class PatternSelector extends JPanel {

    // public List<Pattern> patterns = new ArrayList<Pattern>();

    /**
     * Create the panel.
     */
    public PatternSelector(String name) {
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setLayout(new MigLayout("ins 0", "[grow]", "[][grow][][]"));

        JLabel lblPattern = new JLabel(name + " Pattern");
        add(lblPattern, "cell 0 0,alignx center");

        JList list = new JList();
        list.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        add(list, "cell 0 1,grow");

        JButton btnAddPattern = new JButton("Add Pattern");
        add(btnAddPattern, "cell 0 2,growx");

        JButton btnDeletePattern = new JButton("Delete Pattern");
        add(btnDeletePattern, "cell 0 3,growx");

    }

}
