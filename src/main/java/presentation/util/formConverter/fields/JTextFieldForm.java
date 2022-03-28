package presentation.util.formConverter.fields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JTextFieldForm extends FormField{
    public JTextFieldForm(){
        super(new JTextField());
    }
    @Override
    public String getInfo() {
        return ((JTextField)component).getText();
    }

    @Override
    public void clear() {
        ((JTextField)component).setText("");
        this.errorLabel.setVisible(false);
    }

    @Override
    public String getInfoAsString() {
        return getInfo();
    }

    @Override
    public void setInfo(Object newInfo) {
        ((JTextField)component).setText(newInfo.toString());
    }

    @Override
    public void addFieldAction(Runnable f) {

                ((JTextField)component).getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        f.run();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        f.run();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        f.run();

                    }
                });
    }
}
