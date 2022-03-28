package presentation.util.formConverter.fields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class CharArrayWrapper extends FormField{
    public CharArrayWrapper(){
        super(new JTextField());
    }
    @Override
    public char[] getInfo() {
        return ((JTextField)component).getText().toCharArray();
    }

    @Override
    public void clear() {
        ((JTextField)component).setText("");
        this.errorLabel.setVisible(false);
    }

    @Override
    public String getInfoAsString() {
        return String.valueOf(getInfo());
    }

    @Override
    public void setInfo(Object newInfo) {
        char[] info=(char[])newInfo;
        if(newInfo==null)
            ((JTextField)component).setText("");
        else
        (   (JTextField)component).setText(String.valueOf(info));
    }

    @Override
    public void addFieldAction(Runnable l) {
        ((JTextField)component).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                l.run();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                l.run();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                l.run();
            }
        });
    }
}
