package libedit.editor.models.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.JTextComponent;

public class InfoMessage {
    List<JTextComponent>       observers = new ArrayList<JTextComponent>();
    String                     message;

    private static InfoMessage instance  = null;

    private InfoMessage() {
        this.message = "";
    }

    public static synchronized InfoMessage getInstance() {
        if (instance == null) {
            instance = new InfoMessage();
        }
        return instance;
    }

    public void registerObserver(JTextComponent observer) {
        observers.add(observer);
    }

    public void setMessage(String message) {
        this.message = message;
        for (JTextComponent o : observers) {
            o.setText(this.message);
        }
    }
}
