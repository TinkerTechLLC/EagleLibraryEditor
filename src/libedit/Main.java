package libedit;

import org.jdom2.Document;

import libedit.containers.EagleDoc;

public class Main {

    static String libraryPath = "C:/test_short.xml";

    public static void main(String[] args) {
        Document jdom = LibraryParser.parseXML(libraryPath);
        if (jdom != null) {
            EagleDoc eagleDoc = new EagleDoc(jdom.getRootElement());
            System.out.println("Version: " + eagleDoc.getVersion());
            eagleDoc.printContents();
            Document newDoc = new Document(eagleDoc.toXML());
            LibraryParser.printFile(newDoc);
        }
    }

}
