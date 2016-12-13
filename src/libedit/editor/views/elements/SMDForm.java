package libedit.editor.views.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import libedit.eagle.models.factories.PatternFactory.PadCount;
import libedit.eagle.models.factories.PatternFactory.PadSize;
import libedit.editor.models.patterns.Pattern;
import libedit.editor.models.patterns.SMDPattern;
import libedit.editor.views.abstracts.AbstractPatternForm;
import libedit.helpers.FloatField;
import libedit.helpers.IntegerField;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SMDForm extends AbstractPatternForm {

    private SMDPattern        thisPattern;

    private IntegerField      txtRightCount;
    private IntegerField      txtLeftCount;
    private IntegerField      txtUpCount;
    private IntegerField      txtBottomCount;
    private FloatField        txtPadHeight;
    private FloatField        txtPadWidth;
    private FloatField        txtArrayHeight;
    private FloatField        txtArrayWidth;
    private FloatField        txtPitch;

    private JRadioButton      rdbtnTop;
    private JRadioButton      rdbtnBottom;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public SMDForm() {

        super("SMD");

        thisPattern = new SMDPattern("Default");

        setLayout(new MigLayout("", "[]", "[][][][][][][][grow]"));

        JPanel panel = new JPanel();
        add(panel, "cell 0 0,grow");
        panel.setLayout(new MigLayout("", "[grow][50][50][50][grow]", "[grow][][][][][grow]"));

        JLabel lblSmdPadCount = new JLabel("SMD Pad Count");
        panel.add(lblSmdPadCount, "cell 1 1 3 1,alignx center");

        txtUpCount = new IntegerField(0, 1000);
        txtUpCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updatePattern();
            }
        });
        panel.add(txtUpCount, "cell 2 2,growx");
        txtUpCount.setColumns(10);

        txtLeftCount = new IntegerField(0, 1000);
        txtLeftCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel.add(txtLeftCount, "cell 1 3,growx");
        txtLeftCount.setColumns(10);

        txtRightCount = new IntegerField(0, 1000);
        txtRightCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel.add(txtRightCount, "cell 3 3,growx");
        txtRightCount.setColumns(10);

        txtBottomCount = new IntegerField(0, 1000);
        txtBottomCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel.add(txtBottomCount, "cell 2 4,growx");
        txtBottomCount.setColumns(10);

        JSeparator separator = new JSeparator();
        add(separator, "cell 0 1,growx");

        JPanel panel_1 = new JPanel();
        add(panel_1, "cell 0 2,grow");
        panel_1.setLayout(new MigLayout("", "[grow][75][][75][grow]", "[grow][][][][grow]"));

        JLabel lblPadShape = new JLabel("Pad Shape");
        panel_1.add(lblPadShape, "cell 1 1 3 1,alignx center");

        JLabel lblHeight = new JLabel("Height");
        panel_1.add(lblHeight, "cell 1 2,alignx center");

        JLabel lblWidth = new JLabel("Width");
        panel_1.add(lblWidth, "cell 3 2,alignx center");

        txtPadHeight = new FloatField(2, 0, Float.MAX_VALUE);
        txtPadHeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel_1.add(txtPadHeight, "cell 1 3,growx");
        txtPadHeight.setColumns(10);

        txtPadWidth = new FloatField(2, 0, Float.MAX_VALUE);
        txtPadWidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel_1.add(txtPadWidth, "cell 3 3,growx");
        txtPadWidth.setColumns(10);

        JSeparator separator_1 = new JSeparator();
        add(separator_1, "cell 0 3,growx");

        JPanel panel_2 = new JPanel();
        add(panel_2, "cell 0 4,grow");
        panel_2.setLayout(new MigLayout("", "[grow][75][75][75][grow]", "[grow][][][][grow]"));

        JLabel lblPadLayout = new JLabel("Pad Layout");
        panel_2.add(lblPadLayout, "cell 1 1 3 1,alignx center");

        JLabel lblHeight_1 = new JLabel("Height");
        panel_2.add(lblHeight_1, "flowy,cell 1 2,alignx center");

        JLabel lblWidth_1 = new JLabel("Width");
        panel_2.add(lblWidth_1, "cell 2 2,alignx center");

        JLabel lblPitch = new JLabel("Pitch");
        panel_2.add(lblPitch, "cell 3 2,alignx center");

        txtArrayHeight = new FloatField(2, 0, Float.MAX_VALUE);
        txtArrayHeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel_2.add(txtArrayHeight, "cell 1 3");
        txtArrayHeight.setColumns(10);

        txtArrayWidth = new FloatField(2, 0, Float.MAX_VALUE);
        txtArrayWidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel_2.add(txtArrayWidth, "cell 2 3,growx");
        txtArrayWidth.setColumns(10);

        txtPitch = new FloatField(2, 0, Float.MAX_VALUE);
        txtPitch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        panel_2.add(txtPitch, "cell 3 3,growx");
        txtPitch.setColumns(10);

        JSeparator separator_2 = new JSeparator();
        add(separator_2, "cell 0 5,growx");

        JPanel panel_3 = new JPanel();
        add(panel_3, "cell 0 6,grow");
        panel_3.setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][grow]"));

        JLabel lblLayer = new JLabel("Layer");
        panel_3.add(lblLayer, "cell 1 1 2 1,alignx center");

        rdbtnTop = new JRadioButton("Top");
        rdbtnTop.setSelected(true);
        rdbtnTop.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                updatePattern();
            }
        });
        buttonGroup.add(rdbtnTop);
        panel_3.add(rdbtnTop, "cell 1 2");

        rdbtnBottom = new JRadioButton("Bottom");
        buttonGroup.add(rdbtnBottom);
        panel_3.add(rdbtnBottom, "cell 2 2");
    }

    public void setTopLayer(boolean isTopLayer) {
        rdbtnTop.setSelected(isTopLayer);
    }

    public boolean isTopLayer() {
        if (rdbtnTop.isSelected()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void loadPattern(Pattern pattern) {
        SMDPattern p = (SMDPattern) pattern;
        this.txtArrayHeight.setVal(p.getArrayHeight());
        this.txtArrayWidth.setVal(p.getArrayWidth());
        PadCount pc = p.getPadCount();
        this.txtBottomCount.setVal(pc.down);
        this.txtRightCount.setVal(pc.right);
        this.txtUpCount.setVal(pc.up);
        this.txtLeftCount.setVal(pc.left);
        PadSize ps = p.getPadSize();
        this.txtPadHeight.setVal(ps.height);
        this.txtPadWidth.setVal(ps.width);
        this.txtPitch.setVal(p.getPinPitch());
        this.setTopLayer(p.isTopLayer());
        thisPattern = p;
    }

    @Override
    public Pattern getNewPattern(String name) {
        return new SMDPattern(name);
    }

    @Override
    public Pattern getPattern() {
        return thisPattern;
    }

    @Override
    protected void updatePattern() {
        thisPattern.setArrayHeight(this.txtArrayHeight.getVal());
        thisPattern.setArrayWidth(this.txtArrayWidth.getVal());
        PadCount padCount = new PadCount(this.txtBottomCount.getVal(), this.txtRightCount.getVal(),
                this.txtUpCount.getVal(), this.txtLeftCount.getVal());
        thisPattern.setPadCount(padCount);
        PadSize padSize = new PadSize(this.txtPadWidth.getVal(), this.txtPadHeight.getVal());
        thisPattern.setPadSize(padSize);
        thisPattern.setPinPitch(this.txtPitch.getVal());
        thisPattern.setTopLayer(this.isTopLayer());
        updateObservers();
    }
}
