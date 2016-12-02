package libedit;

import java.util.List;

import org.jdom2.Document;

import libedit.abstractobjects.EagleObj;
import libedit.containers.EagleDoc;
import libedit.containers.Library;
import libedit.containers.Pkg;
import libedit.factories.PatternFactory;
import libedit.factories.PatternFactory.PadCount;
import libedit.factories.PatternFactory.PadSize;

public class Main {

    static String libraryPath = "C:/test_short.xml";

    public static void main(String[] args) {
        Document jdom = LibraryParser.parseXML(libraryPath);

        EagleDoc eagleDoc = new EagleDoc(jdom.getRootElement());

        LibraryParser.printFile(jdom);
        System.out.println("\n\n\n");

        Library lib = (Library) eagleDoc.getObjByType(Library.class);
        lib.printContents();
        System.out.println("\n\n\n");

        PadCount pc = new PadCount(3, 4, 3, 4);
        PadSize ps = new PadSize(1.12f, 0.5f);
        List<EagleObj> pkgChildren = PatternFactory.createSMDPattern(pc, ps, 2, 8, 10, true);
        Pkg pkg = new Pkg("a_new_package_with_long_name", pkgChildren);

        lib.getPackages().add(pkg);

        lib.printContents();
    }

}
