package libedit;

import org.jdom2.Document;

import libedit.abstractobjects.EagleObj;
import libedit.containers.EagleDoc;
import libedit.containers.Pkg;
import libedit.objects.SMD;

public class Main {

    static String libraryPath = "C:/test_long.xml";

    public static void main(String[] args) {
        Document jdom = LibraryParser.parseXML(libraryPath);
        if (jdom != null) {
            EagleDoc eagleDoc = new EagleDoc(jdom.getRootElement());
            System.out.println("Version: " + eagleDoc.getVersion());
            eagleDoc.printContents();
            Document newDoc = new Document(eagleDoc.toXML());
            LibraryParser.printFile(newDoc);
            Pkg pkg = (Pkg) eagleDoc.getObjByType(Pkg.class);
            System.out.println(pkg.getName());
            for (EagleObj o : pkg.getChildren()) {
                if (o.isSMD()) {
                    SMD smd = (SMD) o;
                    System.out.println(smd.getName());
                }
            }
        }
    }

}
