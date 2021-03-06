package libedit.objects;

import java.util.ArrayList;
import java.util.List;

public class Device extends EagleObj {
    String         name;
    List<EagleObj> objects;

    public Device(String name) {
        this.name = name;
        objects = new ArrayList<EagleObj>();
    }

    @Override
    public int getPriority() {
        return Priority.DEVICES;
    }

    @Override
    public String toXMLString() {
        String ret = "";
        ret += "<devicesets>\n";
        if (!objects.isEmpty()) {
            for (EagleObj obj : objects) {
                ret += obj.toXMLString();
            }
        }
        ret += "</devicesets>\n";
        return ret;
    }
}
