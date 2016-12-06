package libedit.fileio;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class FileChooser extends JFrame {
    private static String filename     = "";
    private static String dir          = "";
    private static String path         = "";

    private static String DEFAULT_PATH = "C:\\Program Files\\EAGLE-7.6.0\\lbr\\User Libraries\\Dynamic Perception";

    public boolean showChooser() {

        FileFilter eagleFiles = new FileNameExtensionFilter("Eagle Library", "lbr");

        JFileChooser c = new JFileChooser();
        c.setCurrentDirectory(new File(DEFAULT_PATH));
        c.setFileFilter(eagleFiles);
        c.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int rVal = c.showSaveDialog(FileChooser.this);

        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename = c.getSelectedFile().getName();
            dir = c.getCurrentDirectory().toString();
            path = dir + "/" + filename;
            return true;
        }
        else { // (rVal == JFileChooser.CANCEL_OPTION) {
            filename = "";
            dir = "";
            path = "";
            return false;
        }
    }

    public static String getFilename() {
        return filename;
    }

    public static String getDir() {
        return dir;
    }

    public static String getPath() {
        return path;
    }
}
