package libedit;

import java.util.List;

import org.jdom2.Document;

import libedit.models.abstractobjects.EagleObj;
import libedit.models.containers.EagleDoc;
import libedit.models.containers.Library;
import libedit.models.containers.Pkg;
import libedit.models.factories.PatternFactory;
import libedit.models.factories.PatternFactory.PadCount;
import libedit.models.factories.PatternFactory.PadSize;

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

        PadCount pc = new PadCount(2, 2, 2, 2);
        PadSize ps = new PadSize(1.12f, 0.5f);
        List<EagleObj> pkgChildren = PatternFactory.createSMDPattern(pc, ps, 2, 8, 10, true);
        Pkg pkg = new Pkg("a_new_package_with_long_name", pkgChildren);

        lib.getPackages().add(pkg);

        lib.printContents();
    }

}
