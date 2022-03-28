package presentation.util.formConverter.fields;

import javax.swing.*;
import javax.swing.event.DocumentListener;

public class CheckBoxFieldForm extends FormField{
    public CheckBoxFieldForm(){super(new JCheckBox());}
    @Override
    public Object getInfo() {
        return ((JCheckBox)component).isSelected();
    }

    @Override
    public void clear() {
        ((JCheckBox)component).setSelected(false);
    }

    @Override
    public String getInfoAsString() {
        return String.valueOf(((JCheckBox)component).isSelected());
    }

    @Override
    public void setInfo(Object newInfo) {
        ((JCheckBox)component).setSelected((Boolean)newInfo);
    }

    @Override
    public void addFieldAction(Runnable l) {

        //((JCheckBox)component).addChangeListener(e->l.run());
    }
}
