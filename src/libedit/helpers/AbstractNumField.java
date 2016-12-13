package libedit.helpers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class AbstractNumField<T> extends JTextField {

    protected AbstractNumField<T> field    = this;
    protected String              lastText = "";
    protected T                   maxVal;
    protected T                   minVal;

    protected AbstractNumField(T minVal, T maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
        init();
    }

    private void init() {
        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkFormat();
            }
        });
    }

    abstract protected void checkFormat();

    public void setVal(T val) {
        this.setText(val.toString());
        this.checkFormat();
    };

    abstract public T getVal();

}
