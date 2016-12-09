package libedit.editor.views.elements;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import libedit.eagle.models.factories.PatternFactory.PadCount;
import libedit.editor.models.patterns.Pattern;
import libedit.editor.models.patterns.ThruHolePattern;
import libedit.editor.views.abstracts.AbstractPatternPanel;
import libedit.helpers.FloatField;
import libedit.helpers.IntegerField;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ThruPanel extends AbstractPatternPanel {

    private IntegerField      txtTopCount;
    private IntegerField      txtBottomCount;
    private FloatField        txtArrayHeight;
    private FloatField        txtPitch;
    private FloatField        txtHoleSize;
    private FloatField        txtPadSize;

    private JLabel            lblPadCount;
    private JLabel            lblPadCountBottom;
    private JLabel            lblArrayHeight;
    private JLabel            lblPinPitch;
    private JPanel            padCountPanel;
    private JPanel            padLayoutPanel;
    private JPanel            padPropertyPanel;

    private JLabel            lblHoleSize;
    private JLabel            lblPadSize;
    private JLabel            lblFirstPinSquare;

    private JRadioButton      rdbtnTrue;
    private JRadioButton      rdbtnFalse;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private JSeparator        separator;
    private JSeparator        separator_1;
    private JSeparator        separator_2;

    private JPanel            btnPanel;

    /**
     * Create the panel.
     */
    public ThruPanel() {

        super("Through Hole");

        setLayout(new MigLayout("", "[grow,center]", "[][][][][][grow]"));

        padCountPanel = new JPanel();
        add(padCountPanel, "cell 0 0,alignx center,growy");
        padCountPanel.setLayout(new MigLayout("", "[][]", "[][][][]"));

        lblPadCount = new JLabel("Pad Count");
        padCountPanel.add(lblPadCount, "cell 0 0 2 1,alignx center");

        separator_2 = new JSeparator();
        padCountPanel.add(separator_2, "cell 0 1 2 1,growx");

        JLabel lblPadCountTop = new JLabel("Top");
        padCountPanel.add(lblPadCountTop, "cell 0 2,alignx center");

        lblPadCountBottom = new JLabel("Bottom");
        padCountPanel.add(lblPadCountBottom, "cell 1 2,alignx center");

        txtTopCount = new IntegerField(0, 1000);
        txtTopCount.setHorizontalAlignment(SwingConstants.CENTER);
        txtTopCount.setText("0");
        padCountPanel.add(txtTopCount, "cell 0 3");
        txtTopCount.setColumns(10);

        txtBottomCount = new IntegerField(0, 1000);
        txtBottomCount.setHorizontalAlignment(SwingConstants.CENTER);
        txtBottomCount.setText("0");
        padCountPanel.add(txtBottomCount, "cell 1 3");
        txtBottomCount.setColumns(10);

        separator_1 = new JSeparator();
        add(separator_1, "cell 0 1,growx");

        padLayoutPanel = new JPanel();
        add(padLayoutPanel, "cell 0 2,alignx center,growy");
        padLayoutPanel.setLayout(new MigLayout("", "[][]", "[][]"));

        lblArrayHeight = new JLabel("Array Height");
        padLayoutPanel.add(lblArrayHeight, "cell 0 0,alignx center");

        lblPinPitch = new JLabel("Pin Pitch");
        padLayoutPanel.add(lblPinPitch, "cell 1 0,alignx center");

        txtArrayHeight = new FloatField(2, 0, Float.MAX_VALUE);
        txtArrayHeight.setHorizontalAlignment(SwingConstants.CENTER);
        txtArrayHeight.setText("10.0");
        padLayoutPanel.add(txtArrayHeight, "cell 0 1");
        txtArrayHeight.setColumns(10);

        txtPitch = new FloatField(2, 0, Float.MAX_VALUE);
        txtPitch.setText("2.54");
        txtPitch.setHorizontalAlignment(SwingConstants.CENTER);
        padLayoutPanel.add(txtPitch, "cell 1 1");
        txtPitch.setColumns(10);

        separator = new JSeparator();
        add(separator, "cell 0 3,growx");

        padPropertyPanel = new JPanel();
        add(padPropertyPanel, "cell 0 4,grow");
        padPropertyPanel.setLayout(new MigLayout("", "[grow][][][grow]", "[][grow][5:5:5][]"));

        lblHoleSize = new JLabel("Hole Size");
        padPropertyPanel.add(lblHoleSize, "cell 1 0,alignx center");

        lblPadSize = new JLabel("Pad Size");
        padPropertyPanel.add(lblPadSize, "cell 2 0,alignx center");

        txtHoleSize = new FloatField(2, 0, Float.MAX_VALUE);
        txtHoleSize.setHorizontalAlignment(SwingConstants.CENTER);
        txtHoleSize.setText("0.9");
        padPropertyPanel.add(txtHoleSize, "cell 1 1,growx");
        txtHoleSize.setColumns(10);

        txtPadSize = new FloatField(2, 0, Float.MAX_VALUE);
        txtPadSize.setText("2.0");
        txtPadSize.setHorizontalAlignment(SwingConstants.CENTER);
        padPropertyPanel.add(txtPadSize, "cell 2 1,growx");
        txtPadSize.setColumns(10);

        btnPanel = new JPanel();
        padPropertyPanel.add(btnPanel, "cell 1 3 2 1,alignx center");
        btnPanel.setLayout(new MigLayout("ins 0", "[47px,grow][51px,grow]", "[][23px,grow]"));

        lblFirstPinSquare = new JLabel("First Pin Square?");
        btnPanel.add(lblFirstPinSquare, "cell 0 0 2 1,alignx center");

        rdbtnTrue = new JRadioButton("True");
        btnPanel.add(rdbtnTrue, "cell 0 1,alignx left,aligny top");
        buttonGroup.add(rdbtnTrue);

        rdbtnFalse = new JRadioButton("False");
        btnPanel.add(rdbtnFalse, "cell 1 1,alignx left,aligny top");
        buttonGroup.add(rdbtnFalse);
        rdbtnFalse.setSelected(true);

    }

    public void setFirstPinSqure(boolean square) {
        rdbtnTrue.setSelected(square);
    }

    public boolean isFirstPinSqure() {
        return rdbtnTrue.isSelected();
    }

    @Override
    public void loadPattern(Pattern pattern) {
        ThruHolePattern thp = (ThruHolePattern) pattern;
        PadCount pc = thp.getPadCount();
        txtTopCount.setVal(pc.up);
        txtBottomCount.setVal(pc.down);
        txtArrayHeight.setVal(thp.getArrayHeight());;
        txtPitch.setVal(thp.getPinPitch());;
        txtHoleSize.setVal(thp.getHoleSize());;
        txtPadSize.setVal(thp.getPadSize());;
    }

    @Override
    public Pattern getNewPattern(String name) {
        return new ThruHolePattern(name);
    }

}
