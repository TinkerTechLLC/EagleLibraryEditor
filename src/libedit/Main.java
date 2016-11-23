package libedit;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import helpers.Console;
import libedit.objects.EagleDoc;
import libedit.objects.Layers;
import libedit.objects.Pkg;

public class Main {

    public static void main(String[] args) {
        createDoc();
        LibraryParser.parseXML();

    }

    private static void createDoc() {

        Console.pln("Library Name: ");
        EagleDoc ret = new EagleDoc(Console.getString());
        Console.pln("\nPackage Name: ");
        Pkg pkg = new Pkg(Console.getString());
        int layer;
        while (true) {
            Console.pln("\nLayer (t/b): ");
            String layerStr = Console.getString();
            if (layerStr.substring(0, 1).equals("t")) {
                layer = Layers.TOP;
                break;
            }
            else if (layerStr.substring(0, 1).equals("b")) {
                layer = Layers.BOTTOM;
                break;
            }
        }
        Console.pln("\nPad count: ");
        int padCount = Console.getInteger();
        Console.pln("\nPad height: ");
        float padHeight = Float.parseFloat(Console.getString());
        Console.pln("\nPad width: ");
        float padWidth = Float.parseFloat(Console.getString());
        Console.pln("\nPad pitch: ");
        float pitch = Float.parseFloat(Console.getString());
        Console.pln("\nOverall pad span: ");
        float overallHeight = Float.parseFloat(Console.getString());
        pkg.createPads(padCount, padHeight, padWidth, pitch, overallHeight, layer);
        ret.addEagleObject(pkg);

        try {
            /*
             * String jarName = new
             * java.io.File(Main.class.getProtectionDomain()
             * .getCodeSource().getLocation().getPath()).getName();
             * String curPath =
             * Main.class.getProtectionDomain().getCodeSource().getLocation().
             * getPath();
             * curPath = curPath.substring(0, curPath.indexOf(jarName));
             * PrintWriter out = new PrintWriter(curPath + ret.getFileName() +
             * ".lbr");
             */
            String curPath = "C:\\Program Files\\EAGLE-7.6.0\\lbr\\User Libraries\\Dynamic Perception";
            PrintWriter out = new PrintWriter(curPath + ret.getFileName() + ".lbr");
            out.print(ret.toXMLString());
            out.close();
            System.out.println("New library file successfully created!");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
