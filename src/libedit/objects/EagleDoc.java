package libedit.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EagleDoc extends EagleObj {

    List<EagleObj> objects;
    String         fileName;

    public EagleDoc(String fileName) {
        objects = new ArrayList<EagleObj>();
        this.fileName = fileName;
    }

    public void addEagleObject(EagleObj obj) {
        objects.add(obj);
        Collections.sort(objects);
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public int getPriority() {
        return Priority.EAGLEDOC;
    }

    @Override
    public String toXMLString() {
        String ret = "";
        ret += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        ret += "<!DOCTYPE eagle SYSTEM \"eagle.dtd\">\n";
        ret += "<eagle version=\"7.6.0\">\n";
        ret += "<drawing>\n";
        ret += "<settings>" +
                "<setting alwaysvectorfont=\"no\" />\n" +
                "<setting verticaltext=\"up\" />\n" +
                "</settings>\n";
        ret += "<grid distance=\"0.1\" "
                + "unitdist=\"mm\" "
                + "unit=\"mm\" "
                + "style=\"lines\" "
                + "multiple=\"1\" "
                + "display=\"yes\" "
                + "altdistance=\"0.01\" "
                + "altunitdist=\"mm\" "
                + "altunit=\"mm\" "
                + "/>\n";
        ret += Layers.toXMLStringStatic();

        ret += "<library>\n";
        ret += "<packages>\n";
        for (EagleObj obj : objects) {
            ret += obj.toXMLString();
            ret += "\n";
        }
        ret += "</packages>\n";
        ret += "</library>\n";
        ret += "</drawing>\n";
        ret += "</eagle>";
        return ret;
    }

}
