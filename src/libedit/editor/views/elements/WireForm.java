package libedit.editor.views.elements;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import libedit.editor.models.patterns.Pattern;
import libedit.editor.models.patterns.WirePattern;
import libedit.editor.models.patterns.WirePattern.WirePatternType;
import libedit.editor.views.abstracts.AbstractPatternForm;
import libedit.helpers.FloatField;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class WireForm extends AbstractPatternForm {

    private WirePattern thisPattern;

    private FloatField  txtHorizY;
    private FloatField  txtHorizLength;
    private FloatField  txtOutlineWidth;
    private FloatField  txtOutlineHeight;
    private FloatField  txtVertX;
    private FloatField  txtVertLength;
    private JTabbedPane tabbedPane;

    public WireForm() {
        super("Outline");
        setLayout(new CardLayout(0, 0));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane, "name_1423671602804");

        JPanel outlinePanel = new JPanel();
        String outlineTip = "Create a box shape centered around zero";
        tabbedPane.addTab("Outline", null, outlinePanel, outlineTip);
        tabbedPane.addTab("Outline", null, outlinePanel, null);
        outlinePanel.setLayout(new MigLayout("", "[grow][][75][grow]", "[][][grow]"));

        JLabel lblWidth = new JLabel("Width");
        outlinePanel.add(lblWidth, "cell 1 0,alignx trailing");

        txtOutlineWidth = new FloatField(2, 0, Float.MAX_VALUE);
        txtOutlineWidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updatePattern();
            }
        });
        outlinePanel.add(txtOutlineWidth, "cell 2 0,growx");
        txtOutlineWidth.setColumns(10);

        JLabel lblHeight = new JLabel("Height");
        outlinePanel.add(lblHeight, "cell 1 1,alignx trailing");

        txtOutlineHeight = new FloatField(2, 0, Float.MAX_VALUE);
        txtOutlineHeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        outlinePanel.add(txtOutlineHeight, "cell 2 1,growx");
        txtOutlineHeight.setColumns(10);

        JPanel horizontalPanel = new JPanel();
        String horizontalTip = "Create a vertical line at given Y offset with length centered around zero";
        tabbedPane.addTab("Horizontal", null, horizontalPanel, horizontalTip);
        horizontalPanel.setLayout(new MigLayout("", "[grow][][75][grow]", "[][][grow]"));

        JLabel lblYPosition = new JLabel("Y Position");
        horizontalPanel.add(lblYPosition, "cell 1 0,alignx trailing");

        txtHorizY = new FloatField(2, 0, Float.MAX_VALUE);
        txtHorizY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        horizontalPanel.add(txtHorizY, "cell 2 0,growx");
        txtHorizY.setColumns(10);

        JLabel lblLength = new JLabel("Length");
        horizontalPanel.add(lblLength, "cell 1 1,alignx trailing");

        txtHorizLength = new FloatField(2, 0, Float.MAX_VALUE);
        txtHorizLength.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        horizontalPanel.add(txtHorizLength, "cell 2 1,growx");
        txtHorizLength.setColumns(10);

        JPanel verticalPanel = new JPanel();
        String verticalTip = "Create a vertical line at given X offset with length centered around zero";
        tabbedPane.addTab("Vertical", null, verticalPanel, verticalTip);
        verticalPanel.setLayout(new MigLayout("", "[50,grow][][75][50,grow]", "[][][500,grow]"));

        JLabel lblXPosition = new JLabel("X Position");
        verticalPanel.add(lblXPosition, "cell 1 0,alignx trailing");

        txtVertX = new FloatField(2, 0, Float.MAX_VALUE);
        txtVertX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        verticalPanel.add(txtVertX, "cell 2 0,growx");
        txtVertX.setColumns(10);

        JLabel lblLength_1 = new JLabel("Length");
        lblLength_1.setHorizontalAlignment(SwingConstants.LEFT);
        verticalPanel.add(lblLength_1, "cell 1 1,alignx trailing");

        txtVertLength = new FloatField(2, 0, Float.MAX_VALUE);
        txtVertLength.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePattern();
            }
        });
        verticalPanel.add(txtVertLength, "cell 2 1,growx");
        txtVertLength.setColumns(10);
    }

    @Override
    public void loadPattern(Pattern pattern) {
        WirePattern p = (WirePattern) pattern;
        switch (p.getSubType()) {
        case OUTLINE:
            tabbedPane.setSelectedIndex(WirePatternType.OUTLINE.getTabIndex());
            txtOutlineWidth.setVal(p.getWidth());
            txtOutlineHeight.setVal(p.getHeight());
            break;
        case HORIZ:
            tabbedPane.setSelectedIndex(WirePatternType.HORIZ.getTabIndex());
            txtHorizLength.setVal(p.getLength());
            txtHorizY.setVal(p.getOffset());
            break;
        case VERT:
            tabbedPane.setSelectedIndex(WirePatternType.VERT.getTabIndex());
            txtVertLength.setVal(p.getLength());
            txtVertX.setVal(p.getOffset());
            break;
        }
        thisPattern = p;
    }

    @Override
    public Pattern getNewPattern(String name) {
        return new WirePattern(name, 0, 0);
    }

    @Override
    public Pattern getPattern() {
        return thisPattern;
    }

    @Override
    protected void updatePattern() {
        int index = tabbedPane.getSelectedIndex();
        if (index == WirePatternType.OUTLINE.getTabIndex()) {
            thisPattern.setOutline(txtOutlineWidth.getVal(), txtOutlineHeight.getVal());
        }
        else if (index == WirePatternType.HORIZ.getTabIndex()) {
            thisPattern.setHoriz(txtHorizLength.getVal(), txtHorizY.getVal());
        }
        else if (index == WirePatternType.VERT.getTabIndex()) {
            thisPattern.setVert(txtVertLength.getVal(), txtVertX.getVal());
        }
        updateObservers();
    }

}
