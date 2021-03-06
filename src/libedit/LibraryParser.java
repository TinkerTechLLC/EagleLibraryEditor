package libedit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

public class LibraryParser {

    private static File xmlSource = new File("/EaglePadGenerator/test_data/test_short.xml");

    public static void parseXML() {
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
        System.out.println(xmlSource.isFile());

        try {
            org.w3c.dom.Document w3cDocument;
            w3cDocument = dombuilder.parse(xmlSource);
            DOMBuilder jdomBuilder = new DOMBuilder();
            Document jdomDocument = jdomBuilder.build(w3cDocument);
            System.out.println("Printing content");
            System.out.println("Content size: " + jdomDocument.getContent().size());
            printFile(jdomDocument);
        } catch (SAXException e) {
            System.err.println("SAX Exception");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("IO Exception");
            e.printStackTrace();
        }

    }

    private static void printFile(Document jdom) {
        printElementContents(jdom.getRootElement(), 0);
    }

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
