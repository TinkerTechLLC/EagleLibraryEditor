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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.containers.Pkg;
import libedit.models.factories.PatternFactory;
import libedit.models.factories.PatternFactory.PadCount;
import libedit.models.factories.PatternFactory.PadSize;
import net.miginfocom.swing.MigLayout;

public class TestView extends JDialog {

    private final JPanel     contentPanel  = new JPanel();
    static String            libraryPath   = "C:/test_short.xml";
    static Pkg               pkg;
    private PackagePreviewer previewer     = new PackagePreviewer();
    private JTextField       padHeight;
    private JTextField       padWidth;
    private JTextField       overallHeight;
    private JTextField       overallWidth;
    private JSpinner         upPadCount    = new JSpinner();
    private JSpinner         downPadCount  = new JSpinner();
    private JSpinner         leftPadCount  = new JSpinner();
    private JSpinner         rightPadCount = new JSpinner();
    private JTextField       padPitch;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            TestView dialog = new TestView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        // Document jdom = LibraryParser.parseXML(libraryPath);
        //
        // EagleDoc eagleDoc = new EagleDoc(jdom.getRootElement());
        //
        // LibraryParser.printFile(jdom);
        // System.out.println("\n\n\n");
        //
        // Library lib = (Library) eagleDoc.getObjByType(Library.class);
        // lib.printContents();
        // System.out.println("\n\n\n");
        //
        // PadCount pc = new PadCount(2, 0, 8, 0);
        // PadSize ps = new PadSize(1.12f, 0.5f);
        // List<EagleObj> pkgChildren = PatternFactory.createSMDPattern(pc, ps,
        // 1.5f, 15, 15, true);
        // pkg = new Pkg("a_new_package_with_long_name", pkgChildren);

    }

    /**
     * Create the dialog.
     */
    public TestView() {
        setBounds(100, 100, 577, 398);
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
        contentPanel.setLayout(new MigLayout("", "[10px,grow][300][grow]", "[10px][grow][300,grow][grow]"));
        previewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println("Previewer clicked");
            }
        });
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, "cell 0 2,grow");
            panel.setLayout(new MigLayout("", "[50,grow][50,grow][50,grow,fill]", "[][][][][][][][][][]"));
            {
                JLabel lblPinCount = new JLabel("Pin Count");
                panel.add(lblPinCount, "cell 1 0");
            }
            {
                upPadCount.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent arg0) {
                        updatePackage();
                    }
                });

                panel.add(upPadCount, "cell 1 1,growx");
                upPadCount.setValue(4);
            }
            {
                leftPadCount.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        updatePackage();
                    }
                });
                leftPadCount.setValue(4);
                panel.add(leftPadCount, "cell 0 2,growx");
            }
            {
                rightPadCount.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        updatePackage();
                    }
                });
                panel.add(rightPadCount, "cell 2 2");
                rightPadCount.setValue(4);
            }
            {
                downPadCount.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        updatePackage();
                    }
                });
                panel.add(downPadCount, "cell 1 3,growx");
                downPadCount.setValue(4);
            }
            {
                JLabel lblPitch = new JLabel("Pitch");
                panel.add(lblPitch, "cell 1 4,alignx center");
            }
            {
                padPitch = new JTextField();
                padPitch.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        updatePackage();
                    }
                });
                padPitch.setText("2");
                panel.add(padPitch, "cell 1 5,growx");
                padPitch.setColumns(10);
            }
            {
                JLabel lblPadHeight = new JLabel("Pad Height");
                panel.add(lblPadHeight, "cell 0 6,alignx center");
            }
            {
                JLabel lblPadWidth = new JLabel("Pad Width");
                panel.add(lblPadWidth, "cell 2 6,alignx center");
            }
            {
                padHeight = new JTextField();
                padHeight.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        updatePackage();
                    }
                });
                padHeight.setText("2");
                panel.add(padHeight, "cell 0 7,growx");
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
                panel.add(padWidth, "cell 2 7,growx");
                padWidth.setColumns(10);
            }
            {
                JLabel lblOverallHeight = new JLabel("Overall Height");
                panel.add(lblOverallHeight, "cell 0 8,alignx center");
            }
            {
                JLabel lblOverallWidth = new JLabel("Overall Width");
                panel.add(lblOverallWidth, "cell 2 8,alignx center");
            }
            {
                overallHeight = new JTextField();
                overallHeight.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        updatePackage();
                    }
                });
                overallHeight.setText("5");
                panel.add(overallHeight, "cell 0 9,growx");
                overallHeight.setColumns(10);
            }
            {
                overallWidth = new JTextField();
                overallWidth.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        updatePackage();
                    }
                });
                overallWidth.setText("25");
                panel.add(overallWidth, "cell 2 9,growx");
                overallWidth.setColumns(10);
            }
        }
        contentPanel.add(previewer, "cell 1 2,grow");
    }

    private void updatePackage() {
        System.out.println("Updating package");
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
        pkg = new Pkg("a_new_package_with_long_name", pkgChildren);
        previewer.setPackage(pkg);

        previewer.repaint();
    }

}
