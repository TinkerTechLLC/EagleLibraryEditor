package libedit.eagle.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import libedit.eagle.models.containers.EagleDoc;
import libedit.eagle.models.containers.Library;
import libedit.eagle.models.containers.Pkg;
import libedit.helpers.FileEdit;

public class LibraryParser {

    /*
     * ~~~~~~~~~~~~Static Variables~~~~~~~~~~~~
     */

    private static String xmlPath = null;

    /*
     * ~~~~~~~~~~~~Static Public Methods~~~~~~~~~~~~
     */

    public static Document parseXML(String path) {
        xmlPath = path;

        // The second line of the Eagle library raw XML interferes with this XML
        // parser, so create a copy of the library without the line, but save it
        // for later to add back in when saving the final modified library file
        File workingFile = FileEdit.removeLineFromFile(new File(xmlPath),
                "<!DOCTYPE eagle SYSTEM \"eagle.dtd\">");

        // create the w3c DOM document from which JDOM is to be created
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // we are interested in making it namespace aware.
        factory.setNamespaceAware(true);
        DocumentBuilder dombuilder = null;
        try {
            dombuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Parser config Exception");
            e.printStackTrace();
        }

        Document jdomDocument = null;
        try {
            org.w3c.dom.Document w3cDocument;
            w3cDocument = dombuilder.parse(workingFile);
            DOMBuilder jdomBuilder = new DOMBuilder();
            jdomDocument = jdomBuilder.build(w3cDocument);
        } catch (SAXException e) {
            System.err.println("SAX Exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO Exception");
            e.printStackTrace();
        }

        // We don't need the temporary file later, so delete it now
        // workingFile.delete();
        return jdomDocument;
    }

    public static String getXmlPath() {
        return xmlPath;
    }

    public static void setXmlPath(String xmlPath) {
        LibraryParser.xmlPath = xmlPath;
    }

    public static void printFile(Document jdom) {
        printElementContents(jdom.getRootElement(), 0);
    }

    public static EagleDoc eagleDocFromLibrary(String path) {
        Document jdom = LibraryParser.parseXML(path);
        return new EagleDoc(jdom.getRootElement());
    }

    /**
     * Saves the an Eagle document to the path of the last library that was
     * loaded. If none has been loaded, the method does nothing.
     * 
     * @param eagleDoc
     *            The Eagle document to save to file
     */
    public static void saveEagleDocToLibrary(EagleDoc eagleDoc) {
        if (xmlPath != null) {
            saveEagleDocToLibrary(eagleDoc, xmlPath);
        }
    }

    /**
     * Saves an Eagle document to the path provided
     * 
     * @param eagleDoc
     *            The Eagle document to save the path
     * @param path
     *            Destination library path as a String
     */
    public static void saveEagleDocToLibrary(EagleDoc eagleDoc, String path) {
        try {
            Document jdom = new Document(eagleDoc.toXML());
            XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            xmlOut.output(jdom, writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("IO error writing EagleDoc to library file");
            e.printStackTrace();
        }
    }

    /**
     * Adds a new package to an existing Eagle library
     * 
     * @param eaglePackage
     *            The package to be added to the library
     * @param path
     *            The path of the destination library
     */
    public static void savePackageToLibrary(Pkg eaglePackage, String path) {
        EagleDoc eagleDoc = eagleDocFromLibrary(path);
        Library lib = (Library) eagleDoc.getObjByType(Library.class);
        lib.getPackages().add(eaglePackage);
        LibraryParser.saveEagleDocToLibrary(eagleDoc, path);
    }

    /*
     * ~~~~~~~~~~~~Static Private Methods~~~~~~~~~~~~
     */

    private static void printElementContents(Element e, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println("<" + e.getName() + "> ");
        for (int i = 0; i <= depth; i++) {
            System.out.print("\t");
        }
        for (Attribute a : e.getAttributes()) {
            System.out.print(a.getName() + "=" + a.getValue() + ", ");
        }
        System.out.println("");

        List<Element> children = e.getChildren();
        if (children.size() != 0) {
            for (Element ce : children) {
                printElementContents(ce, depth + 1);
            }
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println("</" + e.getName() + ">");
    }
}
