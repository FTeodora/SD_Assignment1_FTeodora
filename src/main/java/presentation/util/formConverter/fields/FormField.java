package presentation.util.formConverter.fields;

import service.validators.InputException;
import service.validators.TextInputValidator;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Fields for the cursed form. This represents a field in an entity and has a label with the name of the field and a component for writing input.
 * This devil's spawn also keeps a class that's supposed to be a TextInputValidator in order to call its validate method by reflection
 * <br>
 * <b>NOTE!: The returning type of the component should be the same as the type that corresponds to the object field.
 * For example, if you have an eMail field in an object that is of type String, you should use JTextField  for the component
 * or stringify the data from the component (so getInfo() method should return String in this case) </b>
 */
public abstract class FormField {
    String realName;
    JComponent component;
    JLabel label;
    JLabel errorLabel;
    TextInputValidator validator;
    JPanel everything;
    public FormField(JComponent component){
        this.component=component;

        this.label=new JLabel();
        this.errorLabel=new JLabel();
        this.errorLabel.setForeground(Color.RED);
        this.errorLabel.setVisible(false);
        this.component.setPreferredSize(new Dimension(300,30));
        this.everything=new JPanel();
        this.everything.setBorder(BorderFactory.createEmptyBorder(0,15,10,15));
        this.everything.setLayout(new BoxLayout(this.everything,BoxLayout.Y_AXIS));
        everything.setAlignmentX(Component.LEFT_ALIGNMENT);
        everything.add(this.label);
        everything.add(this.component);
        everything.add(this.errorLabel);
        everything.validate();
        everything.repaint();

    }

    /**
     * Calls the validate() method from the InputValidator associated with the class on the current input in the field component, after focus is lost on said component
     * @throws InputException if input is incorrect. Mostly for the final validation from the Parent Form
     */
    public void validateInput() throws InputException{
        try{
            validator.validate(this.getInfo());
            if(this.errorLabel.isVisible())
                this.errorLabel.setVisible(false);
            everything.validate();
            everything.repaint();
        }
        catch(InputException e){
            errorLabel.setText(e.getMessage());
            this.errorLabel.setVisible(true);
            everything.validate();
            everything.repaint();
            throw new InputException(this.label.getText()+ e.getMessage());
        }
    }

    /**
     * @return the current info from the field component
     */
    public abstract Object getInfo();
    public abstract void clear();
    public abstract String getInfoAsString();
    public abstract void setInfo(Object newInfo);
    public abstract void addFieldAction(Runnable f);

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setRealName(String realName){this.realName=realName;
    }
    public void setLabelName(String name){this.label.setText(name);
    }

    public String getRealName() {
        return realName;
    }
    public void setValidation(Class validation){
        try{this.validator=(TextInputValidator) validation.getDeclaredConstructor().newInstance();} catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException| IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        this.component.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateInput();
                } catch (InputException ex) {}
            }
        });
    }
    public JPanel getFieldView(){return this.everything;}
    public void setBackground(Color bg){
        this.everything.setBackground(bg);
        this.label.setBackground(bg);
    }
    public void setForeground(Color fg){
        this.label.setForeground(fg);
        this.component.setForeground(fg);
    }
    public void setFont(Font font){
        this.label.setFont(font);
        this.component.setFont(font);
        this.component.getFont().deriveFont(0.8f);
        this.errorLabel.setFont(font);
        this.errorLabel.getFont().deriveFont(0.5f);
    }
}
