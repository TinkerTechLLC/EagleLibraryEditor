package libedit.objects;

import java.util.ArrayList;
import java.util.List;

public class Symbol extends EagleObj {

    String         name;
    List<EagleObj> objects;

    public Symbol(String name) {
        this.name = name;
        objects = new ArrayList<EagleObj>();
    }

    @Override
    public int getPriority() {
        return Priority.SYMBOLS;
    }

    @Override
    public String toXMLString() {
        String ret = "";
        ret += "<symbols>\n";
        if (!objects.isEmpty()) {
            for (EagleObj obj : objects) {
                ret += obj.toXMLString();
            }
        }
        ret += "</symbols>\n";
        return ret;
    }
}
