package libedit.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import libedit.LibraryParser;
import libedit.fileio.FileChooser;
import libedit.models.abstractobjects.EagleObj;
import libedit.models.containers.Pkg;
import libedit.models.factories.PatternFactory;
import libedit.models.factories.PatternFactory.PadCount;
import libedit.models.factories.PatternFactory.PadSize;
import net.miginfocom.swing.MigLayout;

public class MainDialog extends JDialog {

    private final JPanel     contentPanel          = new JPanel();
    static Pkg               pkg;
    private PackagePreviewer previewer             = new PackagePreviewer();
    private JTextField       padHeight;
    private JTextField       padWidth;
    private JTextField       overallHeight;
    private JTextField       overallWidth;
    private JSpinner         upPadCount            = new JSpinner();
    private JSpinner         downPadCount          = new JSpinner();
    private JSpinner         leftPadCount          = new JSpinner();
    private JSpinner         rightPadCount         = new JSpinner();
    private JTextField       padPitch;
    private final int        DEFAULT_PINS_PER_SIDE = 7;
    private JTextField       txtPackageName;
    private JTextField       textPackageHeight;
    private JTextField       textPackageWidth;

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
        setBounds(100, 100, 577, 629);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnLoadComponent = new JButton("Load Component");
                btnLoadComponent.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        previewer.setPackage(pkg);
                    }
                });
                {
                    JButton btnSaveToLibrary = new JButton("Save to Library...");
                    btnSaveToLibrary.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if (new FileChooser().showChooser()) {
                                LibraryParser.savePackageToLibrary(pkg, FileChooser.getPath());
                            }
                        }
                    });
                    buttonPane.add(btnSaveToLibrary);
                }
                buttonPane.add(btnLoadComponent);
            }
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println(previewer.getSize());
                        previewer.repaint();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        contentPanel
                .setLayout(
                        new MigLayout("", "[grow][10px,grow][300][grow]", "[10px][35:35:35][300:300:300,grow][grow]"));
        previewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println("Previewer clicked");
            }
        });
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, "cell 1 1 2 1,grow");
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
            contentPanel.add(tabbedPane, "cell 1 2,grow");
            {
                JPanel outlinePanel = new JPanel();
                tabbedPane.addTab("New tab", null, outlinePanel, null);
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
                    textPackageHeight = new JTextField();
                    textPackageHeight.setText("17");
                    outlinePanel.add(textPackageHeight, "cell 0 1,growx");
                    textPackageHeight.setColumns(10);
                }
                {
                    textPackageWidth = new JTextField();
                    textPackageWidth.setText("17");
                    outlinePanel.add(textPackageWidth, "cell 2 1,growx");
                    textPackageWidth.setColumns(10);
                }
            }
            {
                JPanel smdPanel = new JPanel();
                tabbedPane.addTab("New tab", null, smdPanel, null);
                smdPanel.setLayout(new MigLayout("", "[50,grow][50,grow][50,grow,fill]", "[][][][][][][][][][]"));
                {
                    JLabel lblPadCount = new JLabel("SMD Pad Count");
                    smdPanel.add(lblPadCount, "cell 0 0 3 1,alignx center");
                }
                {
                    upPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent arg0) {
                            updatePackage();
                        }
                    });

                    smdPanel.add(upPadCount, "cell 1 1,growx");
                    upPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                }
                {
                    leftPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                            updatePackage();
                        }
                    });
                    leftPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                    smdPanel.add(leftPadCount, "cell 0 2,growx");
                }
                {
                    rightPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                            updatePackage();
                        }
                    });
                    smdPanel.add(rightPadCount, "cell 2 2");
                    rightPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                }
                {
                    downPadCount.addChangeListener(new ChangeListener() {
                        public void stateChanged(ChangeEvent e) {
                            updatePackage();
                        }
                    });
                    smdPanel.add(downPadCount, "cell 1 3,growx");
                    downPadCount.setValue(DEFAULT_PINS_PER_SIDE);
                }
                {
                    JLabel lblPitch = new JLabel("Pitch");
                    smdPanel.add(lblPitch, "cell 1 4,alignx center");
                }
                {
                    padPitch = new JTextField();
                    padPitch.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    padPitch.setText("2");
                    smdPanel.add(padPitch, "cell 1 5,growx");
                    padPitch.setColumns(10);
                }
                {
                    JLabel lblPadHeight = new JLabel("Pad Height");
                    smdPanel.add(lblPadHeight, "cell 0 6,alignx center");
                }
                {
                    JLabel lblPadWidth = new JLabel("Pad Width");
                    smdPanel.add(lblPadWidth, "cell 2 6,alignx center");
                }
                {
                    padHeight = new JTextField();
                    padHeight.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    padHeight.setText("2");
                    smdPanel.add(padHeight, "cell 0 7,growx");
                    padHeight.setColumns(10);
                }
                {
                    padWidth = new JTextField();
                    padWidth.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    padWidth.setText("1");
                    smdPanel.add(padWidth, "cell 2 7,growx");
                    padWidth.setColumns(10);
                }
                {
                    JLabel lblOverallHeight = new JLabel("Overall Height");
                    smdPanel.add(lblOverallHeight, "cell 0 8,alignx center");
                }
                {
                    JLabel lblOverallWidth = new JLabel("Overall Width");
                    smdPanel.add(lblOverallWidth, "cell 2 8,alignx center");
                }
                {
                    overallHeight = new JTextField();
                    overallHeight.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    overallHeight.setText("20");
                    smdPanel.add(overallHeight, "cell 0 9,growx");
                    overallHeight.setColumns(10);
                }
                {
                    overallWidth = new JTextField();
                    overallWidth.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            updatePackage();
                        }
                    });
                    overallWidth.setText("20");
                    smdPanel.add(overallWidth, "cell 2 9,growx");
                    overallWidth.setColumns(10);
                }
            }
        }
        contentPanel.add(previewer, "cell 2 2,grow");
    }

    private void updatePackage() {
        int down = (int) downPadCount.getValue();
        int right = (int) rightPadCount.getValue();
        int up = (int) upPadCount.getValue();
        int left = (int) leftPadCount.getValue();
        PadCount pc = new PadCount(down, right, up, left);

        if (padHeight == null)
            return;

        float pHeight = Float.parseFloat(padHeight.getText());
        float pWidth = Float.parseFloat(padWidth.getText());
        PadSize ps = new PadSize(pHeight, pWidth);

        float oaHeight = Float.parseFloat(overallHeight.getText());
        float oaWidth = Float.parseFloat(overallWidth.getText());

        float pitch = Float.parseFloat(padPitch.getText());

        List<EagleObj> pkgChildren = PatternFactory.createSMDPattern(pc, ps, pitch, oaHeight, oaWidth, true);
        pkg = new Pkg(txtPackageName.getText(), pkgChildren);
        previewer.setPackage(pkg);
        previewer.repaint();
    }

}
