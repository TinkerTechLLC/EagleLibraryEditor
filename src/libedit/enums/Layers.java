package libedit.enums;

import libedit.abstractobjects.EagleObj;
import libedit.abstractobjects.EagleObj.Priority;

public class Layers extends EagleObj {

    static public final int TOP       = 1;
    static public final int ROUTE2    = 2;
    static public final int ROUTE3    = 3;
    static public final int ROUTE14   = 14;
    static public final int ROUTE15   = 15;
    static public final int BOTTOM    = 16;
    static public final int PADS      = 17;
    static public final int VIAS      = 18;
    static public final int UNROUTED  = 19;
    static public final int DIMENSION = 20;
    static public final int tPLACE    = 21;
    static public final int bPLACE    = 22;
    static public final int tORIGINS  = 23;
    static public final int bORIGINS  = 24;
    static public final int tNAMES    = 25;
    static public final int bNAMES    = 26;
    static public final int tVALUES   = 27;
    static public final int bVALUES   = 28;
    static public final int tSTOP     = 29;
    static public final int bSTOP     = 30;
    static public final int tCREAM    = 31;
    static public final int bCREAM    = 32;
    static public final int tFINISH   = 33;
    static public final int bFINISH   = 34;
    static public final int tGLUE     = 35;
    static public final int bGLUE     = 36;
    static public final int tTEST     = 37;
    static public final int bTEST     = 38;
    static public final int tKEEPOUT  = 39;
    static public final int bKEEPOUT  = 40;
    static public final int tRESTRICT = 41;
    static public final int bRESTRICT = 42;
    static public final int VRESTRICT = 43;
    static public final int DRILLS    = 44;
    static public final int HOLES     = 45;
    static public final int MILLING   = 46;
    static public final int MEASURES  = 47;
    static public final int DOCUMENT  = 48;
    static public final int REFERENCE = 49;
    static public final int tDOCU     = 50;
    static public final int bDOCU     = 51;
    static public final int PATCH_TOP = 101;
    static public final int VSCORE    = 102;
    static public final int FP3       = 103;
    static public final int NAME      = 104;
    static public final int NOTES     = 150;
    static public final int HEATSINK  = 151;
    static public final int EDGE      = 249;

    @Override
    public int getPriority() {
        return Priority.LAYER;
    }

    static public String toXMLStringStatic() {
        return new Layers().toXMLString();
    }

    @Override
    public String toXMLString() {
        String ret = "<layers>\n"
                + "<layer number=\"1\" name=\"Top\" color=\"4\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"2\" name=\"Route2\" color=\"1\" fill=\"3\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"3\" name=\"Route3\" color=\"4\" fill=\"3\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"14\" name=\"Route14\" color=\"1\" fill=\"6\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"15\" name=\"Route15\" color=\"4\" fill=\"6\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"16\" name=\"Bottom\" color=\"1\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"17\" name=\"Pads\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"18\" name=\"Vias\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"19\" name=\"Unrouted\" color=\"6\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"20\" name=\"Dimension\" color=\"15\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"21\" name=\"tPlace\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"22\" name=\"bPlace\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"23\" name=\"tOrigins\" color=\"15\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"24\" name=\"bOrigins\" color=\"15\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"25\" name=\"tNames\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"26\" name=\"bNames\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"27\" name=\"tValues\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"28\" name=\"bValues\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"29\" name=\"tStop\" color=\"7\" fill=\"3\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"30\" name=\"bStop\" color=\"7\" fill=\"6\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"31\" name=\"tCream\" color=\"7\" fill=\"4\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"32\" name=\"bCream\" color=\"7\" fill=\"5\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"33\" name=\"tFinish\" color=\"6\" fill=\"3\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"34\" name=\"bFinish\" color=\"6\" fill=\"6\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"35\" name=\"tGlue\" color=\"7\" fill=\"4\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"36\" name=\"bGlue\" color=\"7\" fill=\"5\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"37\" name=\"tTest\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"38\" name=\"bTest\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"39\" name=\"tKeepout\" color=\"4\" fill=\"11\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"40\" name=\"bKeepout\" color=\"1\" fill=\"11\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"41\" name=\"tRestrict\" color=\"4\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"42\" name=\"bRestrict\" color=\"1\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"43\" name=\"vRestrict\" color=\"2\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"44\" name=\"Drills\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"45\" name=\"Holes\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"46\" name=\"Milling\" color=\"3\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"47\" name=\"Measures\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"48\" name=\"Document\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"49\" name=\"Reference\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"50\" name=\"dxf\" color=\"7\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"51\" name=\"tDocu\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"52\" name=\"bDocu\" color=\"7\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"53\" name=\"tGND_GNDA\" color=\"7\" fill=\"9\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"54\" name=\"bGND_GNDA\" color=\"1\" fill=\"9\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"56\" name=\"wert\" color=\"7\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"57\" name=\"tCAD\" color=\"7\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"90\" name=\"Modules\" color=\"5\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"91\" name=\"Nets\" color=\"2\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"92\" name=\"Busses\" color=\"1\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"93\" name=\"Pins\" color=\"2\" fill=\"1\" visible=\"no\" active=\"yes\" />\n"
                + "<layer number=\"94\" name=\"Symbols\" color=\"4\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"95\" name=\"Names\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"96\" name=\"Values\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"97\" name=\"Info\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"98\" name=\"Guide\" color=\"6\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"99\" name=\"SpiceOrder\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"100\" name=\"Muster\" color=\"7\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"101\" name=\"Patch_Top\" color=\"12\" fill=\"4\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"102\" name=\"Vscore\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"103\" name=\"tMap\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"104\" name=\"Name\" color=\"16\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"105\" name=\"tPlate\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"106\" name=\"bPlate\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"107\" name=\"Crop\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"108\" name=\"tplace-old\" color=\"10\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"109\" name=\"ref-old\" color=\"11\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"110\" name=\"fp0\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"111\" name=\"LPC17xx\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"112\" name=\"tSilk\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"113\" name=\"IDFDebug\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"116\" name=\"Patch_BOT\" color=\"9\" fill=\"4\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"121\" name=\"_tsilk\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"122\" name=\"_bsilk\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"123\" name=\"tTestmark\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"124\" name=\"bTestmark\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"125\" name=\"_tNames\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"126\" name=\"_bNames\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"127\" name=\"_tValues\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"128\" name=\"_bValues\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"131\" name=\"tAdjust\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"132\" name=\"bAdjust\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"144\" name=\"Drill_legend\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"150\" name=\"Notes\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"151\" name=\"HeatSink\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"152\" name=\"_bDocu\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"153\" name=\"FabDoc1\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"154\" name=\"FabDoc2\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"155\" name=\"FabDoc3\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"199\" name=\"Contour\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"200\" name=\"200bmp\" color=\"1\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"201\" name=\"201bmp\" color=\"2\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"202\" name=\"202bmp\" color=\"3\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"203\" name=\"203bmp\" color=\"4\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"204\" name=\"204bmp\" color=\"5\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"205\" name=\"205bmp\" color=\"6\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"206\" name=\"206bmp\" color=\"7\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"207\" name=\"207bmp\" color=\"8\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"208\" name=\"208bmp\" color=\"9\" fill=\"10\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"209\" name=\"209bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"210\" name=\"210bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"211\" name=\"211bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"212\" name=\"212bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"213\" name=\"213bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"214\" name=\"214bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"215\" name=\"215bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"216\" name=\"216bmp\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"217\" name=\"217bmp\" color=\"18\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"218\" name=\"218bmp\" color=\"19\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"219\" name=\"219bmp\" color=\"20\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"220\" name=\"220bmp\" color=\"21\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"221\" name=\"221bmp\" color=\"22\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"222\" name=\"222bmp\" color=\"23\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"223\" name=\"223bmp\" color=\"24\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"224\" name=\"224bmp\" color=\"25\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"248\" name=\"Housing\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"249\" name=\"Edge\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "<layer number=\"250\" name=\"Descript\" color=\"3\" fill=\"1\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"251\" name=\"SMDround\" color=\"12\" fill=\"11\" visible=\"no\" active=\"no\" />\n"
                + "<layer number=\"254\" name=\"cooling\" color=\"7\" fill=\"1\" visible=\"yes\" active=\"yes\" />\n"
                + "</layers>\n";
        return ret;
    }
}
