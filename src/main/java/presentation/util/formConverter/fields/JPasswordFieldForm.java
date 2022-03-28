package presentation.util.formConverter.fields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;

public class JPasswordFieldForm extends FormField{

    public JPasswordFieldForm(){
        super(new JPasswordField());
    }
    @Override
    public char[] getInfo() {
        return ((JPasswordField)component).getPassword();
    }

    @Override
    public void clear() {
        ((JPasswordField)component).setText("");
        this.errorLabel.setVisible(false);
    }

    @Override
    public String getInfoAsString() {
        return String.valueOf(getInfo());
    }

    @Override
    public void setInfo(Object newInfo) {
    }

    @Override
    public void addFieldAction(Runnable l) {
        ((JPasswordField)component).getDocument().addDocumentListener(new DocumentListener() {
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
