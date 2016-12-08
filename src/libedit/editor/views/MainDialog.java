package libedit.editor.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import libedit.eagle.models.LibraryParser;
import libedit.eagle.models.abstractobjects.EagleObj;
import libedit.eagle.models.containers.Pkg;
import libedit.eagle.models.enums.Unit;
import libedit.eagle.models.factories.PatternFactory;
import libedit.eagle.models.factories.PatternFactory.PadCount;
import libedit.eagle.models.factories.PatternFactory.PadSize;
import libedit.editor.fileio.FileChooser;
import libedit.editor.models.gui.EditorSettings;
import libedit.helpers.FloatField;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MainDialog extends JDialog {

    private final JPanel      contentPanel          = new JPanel();
    static Pkg                pkg;
    private PackagePreviewer  previewer             = new PackagePreviewer();
    private FloatField        padHeight;
    private FloatField        padWidth;
    private FloatField        overallHeight;
    private FloatField        overallWidth;
    private JSpinner          upPadCount            = new JSpinner();
    private JSpinner          downPadCount          = new JSpinner();
    private JSpinner          leftPadCount          = new JSpinner();
    private JSpinner          rightPadCount         = new JSpinner();
    private JTextField        padPitch;
    private final int         DEFAULT_PINS_PER_SIDE = 3;
    private JTextField        txtPackageName;
    private FloatField        textPackageHeight;
    private FloatField        textPackageWidth;
    private final ButtonGroup layerButtons          = new ButtonGroup();
    private JRadioButton      rdbtnTop;
    private JTextField        textField;
    // private JComboBox<String> comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            MainDialog dialog = new MainDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public MainDialog() {
        setResizable(false);
        setBounds(100, 100, 700, 550);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                {
                    JButton btnSaveToLibrary = new JButton("Save to Library...");
                    btnSaveToLibrary.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if (new FileChooser().showChooser()) {
                                if (new File(FileChooser.getPath()).exists()) {
                                    LibraryParser.savePackageToLibrary(pkg, FileChooser.getPath());
                                }
                                else {

                                }
                            }
                        }
                    });
                    buttonPane.add(btnSaveToLibrary);
                }
            }
        }
        contentPanel
                .setLayout(
                        new MigLayout("", "[10px,grow][325:325:325]", "[35:35:35][325:325px:325px][grow]"));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, "cell 0 0 2 1,grow");
            panel.setLayout(new MigLayout("", "[74px][86px,grow]", "[20px,grow]"));
            {
                JLabel lblPackageName = new JLabel("Package Name:");
                panel.add(lblPackageName, "cell 0 0,alignx left,aligny center");
            }
            {
                txtPackageName = new JTextField();
                txtPackageName.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String cleanTxt = txtPackageName.getText().trim().toUpperCase().replace(" ", "_");
                        txtPackageName.setText(cleanTxt);
                        updatePackage();
                    }
                });
                txtPackageName.setText("NEW_PACKAGE");
                panel.add(txtPackageName, "cell 1 0,grow");
                txtPackageName.setColumns(10);
            }
        }
        {
            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            contentPanel.add(tabbedPane, "cell 0 1,grow");
            {
                JPanel outlinePanel = new JPanel();
                tabbedPane.addTab("Outline", null, outlinePanel, null);
                outlinePanel.setLayout(new MigLayout("", "[grow][][grow]", "[][]"));
                {
                    JLabel lblPackageHeight = new JLabel("Package Height");
                    outlinePanel.add(lblPackageHeight, "cell 0 0");
                }
                {
                    JLabel lblPackageWidth = new JLabel("Package Width");
                    outlinePanel.add(lblPackageWidth, "cell 2 0");
                }
                {
                    textPackageHeight = new FloatField(2, 0, Float.MAX_VALUE);
                    textPackageHeight.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    textPackageHeight.setText("8");
                    outlinePanel.add(textPackageHeight, "cell 0 1,growx");
                    textPackageHeight.setColumns(10);
                }
                {
                    textPackageWidth = new FloatField(2, 0, Float.MAX_VALUE);
                    textPackageWidth.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            updatePackage();
                        }
                    });
                    textPackageWidth.setText("8");
                    outlinePanel.add(textPackageWidth, "cell 2 1,growx");
                    textPackageWidth.setColumns(10);
                }
            }
            {
                JPanel smdPanel = new JPanel();
                tabbedPane.addTab("SMD", null, smdPanel, null);
                smdPanel.setLayout(
                        new MigLayout("", "[100px,grow][50,grow][50,grow][50,grow,fill]",
                                "[grow][][][][][][][][][][][][][][grow]"));
                {
                    JPanel panel = new JPanel();
                    smdPanel.add(panel, "cell 0 0 1 15,grow");
                    panel.setLayout(new MigLayout("", "[grow]", "[][grow][][]"));
                    {
                        JLabel lblSmdPattern = new JLabel("SMD Pattern");
                        panel.add(lblSmdPattern, "cell 0 0,alignx center");
                    }
                    {
                        JList list = new JList();
                        panel.add(list, "cell 0 1,grow");
                    }
                    {
                        JButton btnNewPattern = new JButton("New Pattern");
                        panel.add(btnNewPattern, "cell 0 2,growx");
                    }
                    {
                        JButton btnDeletePattern = new JButton("Delete Pattern");
                        panel.add(btnDeletePattern, "cell 0 3,growx");
                    }
                }
                {
                    JLabel lblPadCount = new JLabel("SMD Pad Count");
                    smdPanel.add(lblPadCount, "cell 1 0 3 1,alignx center");
                }
                {
                    upPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent arg0) {
                            updatePackage();
                        }
                    });

                    smdPanel.add(upPadCount, "cell 2 1,growx");
                    upPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                }
                {
                    leftPadCount.setModel(new SpinnerNumberModel(new Integer(3), null, null, new Integer(1)));
                    leftPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                            updatePackage();
                        }
                    });
                    leftPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                    smdPanel.add(leftPadCount, "cell 1 2,growx");
                }
                {
                    rightPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                            updatePackage();
                        }
                    });
                    smdPanel.add(rightPadCount, "cell 3 2");
                    rightPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                }
                {
                    downPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                            updatePackage();
                        }
                    });
                    smdPanel.add(downPadCount, "cell 2 3,growx");
                    downPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                }
                {
                    JSeparator separator = new JSeparator();
                    smdPanel.add(separator, "cell 1 4 3 1,growx");
                }
                {
                    JLabel lblPadShape = new JLabel("Pad Shape");
                    smdPanel.add(lblPadShape, "cell 1 5 3 1,alignx center");
                }
                {
                    JLabel lblPadHeight = new JLabel("Height");
                    smdPanel.add(lblPadHeight, "cell 1 6,alignx center");
                }
                {
                    {
                        padHeight = new FloatField(2, 0, Float.MAX_VALUE);
                        padHeight.setHorizontalAlignment(SwingConstants.CENTER);
                        padHeight.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                updatePackage();
                            }
                        });
                        {
                            JLabel lblPadWidth = new JLabel("Width");
                            lblPadWidth.setHorizontalAlignment(SwingConstants.CENTER);
                            smdPanel.add(lblPadWidth, "cell 3 6,alignx center");
                        }
                        padHeight.setText("2");
                        smdPanel.add(padHeight, "cell 1 7,growx");
                        padHeight.setColumns(10);
                    }
                }
                {
                    padWidth = new FloatField(2, 0, Float.MAX_VALUE);
                    padWidth.setHorizontalAlignment(SwingConstants.CENTER);
                    padWidth.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    padWidth.setText("0.5");
                    smdPanel.add(padWidth, "cell 3 7,growx");
                    padWidth.setColumns(10);
                }
                {
                    JSeparator separator = new JSeparator();
                    smdPanel.add(separator, "cell 1 8 3 1,growx");
                }
                {
                    JLabel lblPadLayout = new JLabel("Pad Layout");
                    smdPanel.add(lblPadLayout, "cell 1 9 3 1,alignx center");
                }
                {
                    JLabel lblOverallHeight = new JLabel("Height");
                    smdPanel.add(lblOverallHeight, "cell 1 10,alignx center");
                }
                {
                    overallHeight = new FloatField(2, 0, Float.MAX_VALUE);
                    overallHeight.setHorizontalAlignment(SwingConstants.CENTER);
                    overallHeight.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    {
                        JLabel lblOverallWidth = new JLabel("Width");
                        lblOverallWidth.setHorizontalAlignment(SwingConstants.CENTER);
                        smdPanel.add(lblOverallWidth, "cell 2 10,alignx center");
                    }
                    {
                        JLabel lblPitch = new JLabel("Pitch");
                        smdPanel.add(lblPitch, "cell 3 10,alignx center");
                    }
                    overallHeight.setText("10");
                    smdPanel.add(overallHeight, "cell 1 11,growx");
                    overallHeight.setColumns(10);
                }
                {
                    overallWidth = new FloatField(2, 0, Float.MAX_VALUE);
                    overallWidth.setHorizontalAlignment(SwingConstants.CENTER);
                    overallWidth.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    overallWidth.setText("10");
                    smdPanel.add(overallWidth, "cell 2 11,growx");
                    overallWidth.setColumns(10);
                }
                padPitch = new JTextField();
                padPitch.setHorizontalAlignment(SwingConstants.CENTER);
                padPitch.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        updatePackage();
                    }
                });
                padPitch.setText("1");
                smdPanel.add(padPitch, "cell 3 11,growx");
                padPitch.setColumns(10);
                {
                    JSeparator separator = new JSeparator();
                    smdPanel.add(separator, "cell 1 12 3 1,growx");
                }
                {
                    JLabel lblLayer = new JLabel("Layer");
                    smdPanel.add(lblLayer, "cell 2 13,alignx center");
                }
                {
                    JPanel panel = new JPanel();
                    smdPanel.add(panel, "cell 1 14 3 1,grow");
                    {
                        rdbtnTop = new JRadioButton("Top");
                        rdbtnTop.addChangeListener(new ChangeListener() {
                            public void stateChanged(ChangeEvent arg0) {
                                updatePackage();
                            }
                        });
                        layerButtons.add(rdbtnTop);
                        rdbtnTop.setSelected(true);
                        panel.add(rdbtnTop);
                    }
                    {
                        JRadioButton rdbtnBottom = new JRadioButton("Bottom");
                        layerButtons.add(rdbtnBottom);
                        panel.add(rdbtnBottom);
                    }
                }
            }
            {
                JPanel panel = new JPanel();
                tabbedPane.addTab("Thru Hole", null, panel, null);
                panel.setLayout(new MigLayout("", "[grow][]", "[125][]"));
                {
                    JLabel lblThruHolePatterns = new JLabel("Thru Hole Patterns");
                    panel.add(lblThruHolePatterns, "flowy,cell 0 0,alignx center");
                }
                {
                    JList list = new JList();
                    panel.add(list, "cell 0 0,grow");
                }
            }
            {
                JPanel panel = new JPanel();
                tabbedPane.addTab("New tab", null, panel, null);
            }
        }
        {
            previewer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            contentPanel.add(previewer, "cell 1 1,grow");
            {
                JPanel panel = new JPanel();
                contentPanel.add(panel, "cell 0 2,grow");
                panel.setLayout(new MigLayout("", "[][grow]", "[][]"));
                {
                    JLabel lblUnits = new JLabel("Units");
                    panel.add(lblUnits, "cell 0 0,alignx trailing");
                }
                {
                    JComboBox<Object> unitSelect = new JComboBox<Object>(Unit.stringValues());
                    unitSelect.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            Unit newUnit = Unit.parseString((String) unitSelect.getSelectedItem());
                            EditorSettings.getInstance().setUnit(newUnit);
                        }
                    });
                    panel.add(unitSelect, "cell 1 0,growx");
                }
                {
                    JLabel lblGridSize = new JLabel("Grid Size");
                    panel.add(lblGridSize, "cell 0 1,alignx trailing");
                }
                {
                    textField = new JTextField();
                    textField.setText("1");
                    panel.add(textField, "cell 1 1,growx");
                    textField.setColumns(10);
                }
            }
            {
                JPanel messagePaneContainer = new JPanel();
                messagePaneContainer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                contentPanel.add(messagePaneContainer, "cell 1 2,grow");
                messagePaneContainer.setLayout(new CardLayout(0, 0));
                {
                    JTextPane messagePane = new JTextPane();
                    messagePaneContainer.add(messagePane, "name_263164151262789");
                }
            }
            previewer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    System.out.println("Previewer clicked");
                }
            });
        }
        {
            JMenuBar menuBar = new JMenuBar();
            setJMenuBar(menuBar);
            {
                JMenu mnFile = new JMenu("File");
                menuBar.add(mnFile);
                {
                    JMenuItem mntmExit = new JMenuItem("Exit");
                    mntmExit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });
                    mnFile.add(mntmExit);
                }
            }
        }

        // Register observers with EditorSettings model
        EditorSettings es = EditorSettings.getInstance();
        es.registerUnitObserver(overallHeight);
        es.registerUnitObserver(overallWidth);
        es.registerUnitObserver(padHeight);
        es.registerUnitObserver(padWidth);
        es.registerUnitObserver(textPackageHeight);
        es.registerUnitObserver(textPackageWidth);
        es.registerGridObserver(previewer);
    }

    private PadCount getPadCount() throws NullPointerException {
        int down = (int) downPadCount.getValue();
        int right = (int) rightPadCount.getValue();
        int up = (int) upPadCount.getValue();
        int left = (int) leftPadCount.getValue();
        return new PadCount(down, right, up, left);
    }

    private PadSize getPadSize() throws NullPointerException {
        float pWidth = Float.parseFloat(padWidth.getText());
        float pHeight = Float.parseFloat(padHeight.getText());
        return new PadSize(pWidth, pHeight);
    }

    private void updatePackage() {
        // A NullPointerException will be thrown when first instantiating the
        // GUI. It's okay to just ignore it.
        try {
            List<EagleObj> pkgChildren = new ArrayList<EagleObj>();

            // Get values for SMD pattern and add pattern to children
            PadCount pc = getPadCount();
            PadSize ps = getPadSize();

            float oaHeight = Float.parseFloat(overallHeight.getText());
            float oaWidth = Float.parseFloat(overallWidth.getText());

            float pitch = Float.parseFloat(padPitch.getText());
            boolean topLayer = rdbtnTop.isSelected();

            pkgChildren.addAll(PatternFactory.createSMDPattern(pc, ps, pitch, oaWidth, oaHeight, topLayer));

            // Get values for package outline and add result to children
            float pkgHeight = Float.parseFloat(textPackageHeight.getText());
            float pkgWidth = Float.parseFloat(textPackageWidth.getText());

            pkgChildren.addAll(PatternFactory.createSilkOutline(pkgWidth, pkgHeight, topLayer));

            pkg = new Pkg(txtPackageName.getText(), pkgChildren);

            previewer.setPackage(pkg);
            previewer.repaint();

        } catch (NullPointerException e) {
            return;
        }
    }

}
