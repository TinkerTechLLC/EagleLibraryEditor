package libedit.editor.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import libedit.eagle.models.LibraryParser;
import libedit.eagle.models.enums.Unit;
import libedit.eagle.models.factories.PackageBuilder;
import libedit.editor.fileio.FileChooser;
import libedit.editor.models.gui.EditorSettings;
import libedit.editor.views.abstracts.PatternEditor;
import libedit.editor.views.elements.SMDForm;
import libedit.editor.views.elements.ThruForm;
import libedit.helpers.FloatField;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class MainDialog extends JDialog {

    private final JPanel     contentPanel   = new JPanel();
    private PackagePreviewer previewer      = new PackagePreviewer();
    private JTextField       txtPackageName;
    private FloatField       textPackageHeight;
    private FloatField       textPackageWidth;
    private FloatField       gridSize;
    private PackageBuilder   packageBuilder = new PackageBuilder();

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
        setBounds(100, 100, 813, 719);
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
                            // Don't open the chooser is there's no package to
                            // export
                            if (packageBuilder.getPackage() != null) {
                                // If the file chooser was not aborted
                                if (new FileChooser().showChooser()) {
                                    if (new File(FileChooser.getPath()).exists()) {
                                        LibraryParser.savePackageToLibrary(packageBuilder.getPackage(),
                                                FileChooser.getPath());
                                    }
                                    else {

                                    }
                                }
                            }
                        }
                    });
                    buttonPane.add(btnSaveToLibrary);
                }
            }
        }
        contentPanel.setLayout(new MigLayout("", "[::375][325:325:325][grow]", "[35:n:60][325:325px:325px][][grow]"));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, "cell 0 0 2 1,growx,aligny center");
            panel.setLayout(new MigLayout("ins 0", "[74px][86px,grow]", "[20px,grow]"));
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
            contentPanel.add(tabbedPane, "cell 0 1 1 2,grow");
            {
                JPanel outlinePanel = new JPanel();
                tabbedPane.addTab("Outline", null, outlinePanel, null);
                outlinePanel.setLayout(new MigLayout("", "[50][][50][]", "[][]"));
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
                    textPackageHeight.setText("8.0");
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
                    textPackageWidth.setText("8.0");
                    outlinePanel.add(textPackageWidth, "cell 2 1,growx");
                    textPackageWidth.setColumns(10);
                }
            }
            {
                SMDForm smdPanel = new SMDForm();
                PatternEditor smdEditor = new PatternEditor(smdPanel);
                packageBuilder.registerModel(smdEditor);
                smdEditor.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentShown(ComponentEvent e) {
                        smdEditor.init();
                    }
                });

                tabbedPane.addTab("SMD", null, smdEditor, null);
            }
            {
                ThruForm thruPanel = new ThruForm();
                PatternEditor thruEditor = new PatternEditor(thruPanel);
                packageBuilder.registerModel(thruEditor);
                thruEditor.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentShown(ComponentEvent e) {
                        thruEditor.init();
                    }
                });
                tabbedPane.addTab("Thru Hole", null, thruEditor, null);
                thruEditor.setLayout(new MigLayout("", "[:125:125][]", "[125,grow]"));
            }
        }
        {
            packageBuilder.registerObserver(previewer);
            previewer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            contentPanel.add(previewer, "cell 1 1,grow");
            {
                JPanel panel = new JPanel();
                contentPanel.add(panel, "cell 0 3,grow");
                panel.setLayout(new MigLayout("", "[][grow]", "[][]"));
                {
                    JLabel lblUnits = new JLabel("Units");
                    panel.add(lblUnits, "cell 0 0,alignx trailing");
                }
                {
                    JComboBox<Object> unitSelect = new JComboBox<Object>(Unit.stringValues());
                    unitSelect.setEnabled(false);
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
                    gridSize = new FloatField(2, 0, Float.MAX_VALUE);
                    gridSize.setText("1.0");
                    panel.add(gridSize, "cell 1 1,growx");
                    gridSize.setColumns(10);
                }
            }
            {
                JPanel messagePaneContainer = new JPanel();
                messagePaneContainer.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                contentPanel.add(messagePaneContainer, "cell 1 3,grow");
                messagePaneContainer.setLayout(new CardLayout(0, 0));
                {
                    JTextPane messagePane = new JTextPane();
                    messagePane.setEditable(false);
                    messagePane.setText("No messages. That means you haven't broken anything yet.");
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
        es.registerUnitObserver(textPackageHeight);
        es.registerUnitObserver(textPackageWidth);
        es.registerGridObserver(previewer);
        es.registerUnitObserver(gridSize);
    }

    private void updatePackage() {
        // A NullPointerException will be thrown when first instantiating the
        // GUI. It's okay to just ignore it.
        try {
            previewer.repaint();

        } catch (NullPointerException e) {
            return;
        }
    }

}
