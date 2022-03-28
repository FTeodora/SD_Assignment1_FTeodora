package presentation.util.formConverter.fields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JTextAreaFieldForm extends FormField{
    public JTextAreaFieldForm() {
        super(new JTextArea());
        ((JTextArea)component).setLineWrap(true);
    }

    @Override
    public String getInfo() {
        return ((JTextArea)component).getText();
    }

    @Override
    public void clear() {
        ((JTextArea)component).setText("");
    }

    @Override
    public String getInfoAsString() {
        return getInfo();
    }

    @Override
    public void setInfo(Object newInfo) {
        ((JTextArea)component).setText(String.valueOf(newInfo));
    }

    @Override
    public void addFieldAction(Runnable f) {
        ((JTextArea)component).getDocument().addDocumentListener(new DocumentListener() {
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
