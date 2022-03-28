package presentation.util.formConverter;

import presentation.util.formConverter.fields.*;
import service.validators.InputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Get ready for the most cursed thing in Java history
 * <br>
 * This is a form, and some special user defined fields use some Java reflection magic to transform an object in Swing components.
 * You should have setters and a constructor with no parameters for the parameterized class
 * @param <T> class to be transformed. It doesn't even have to be an entity class. It could even be a DTO
 */
public class Form<T> {
    JPanel formZone=new JPanel(new BorderLayout());
    JPanel buttonZone=new JPanel(new FlowLayout(FlowLayout.TRAILING));
    JPanel fieldZone=new JPanel();
    FormButton<T> submit;
    FormButton<T> clear;
    FormButton<T> cancel;
    List<FormButton<T>> extraOptions;
    T generatedObject;
    protected Class<T> type;
    List<FormField> fieldList;
    public Form(){
        fieldZone.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.fieldList=new ArrayList<>();
        this.formZone.add(fieldZone,BorderLayout.CENTER);
        this.formZone.add(buttonZone,BorderLayout.SOUTH);
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(this.formZone);
    }
    public Form(Class<T> parameterClass){
        this();
        this.type=parameterClass;
        fieldZone.setLayout(new BoxLayout(fieldZone,BoxLayout.Y_AXIS));

        try{ this.generatedObject=this.type.getDeclaredConstructor().newInstance();}
        catch (InvocationTargetException |InstantiationException|IllegalAccessException |NoSuchMethodException e) {
            e.printStackTrace();
        }
        objectToForm();
        for(FormField field:this.fieldList){
            this.fieldZone.add(field.getFieldView());
        }

    }
    public Form(Class<T> parameterClass,T object){
        this(parameterClass);
        this.generatedObject=object;
        completeFields(object);

    }


    public void objectToForm(){
        for(Field field  : this.type.getDeclaredFields())
        {
            if (field.isAnnotationPresent(SwingField.class))
            {
                SwingField fieldClass=field.getAnnotation(SwingField.class);
                try{
                    FormField f=(FormField) fieldClass.componentType().getDeclaredConstructor().newInstance();
                    f.setRealName(field.getName());
                    if(fieldClass.labelName().equals(""))
                        f.setLabelName(field.getName());
                    else
                        f.setLabelName(fieldClass.labelName());
                    f.setValidation(fieldClass.validator());
                    this.fieldList.add(f);
                }
                catch(Exception exc){exc.printStackTrace();}
            }
        }
    }
    public void completeFields(T object){
        this.fieldList.stream().forEach(x->{
            try{
                Object fieldInfo=(new PropertyDescriptor(x.getRealName(),this.type)).getReadMethod().invoke(object);
                if(fieldInfo!=null)
                    x.setInfo(fieldInfo);
            }
            catch(Exception exc){exc.printStackTrace();}
        });
    }
    /**
     * Returns the JPanel containing all fields and buttons in order to add it to another JComponent
     * @return JPanel containing all fields and form buttons
     */
    public JPanel getFormZone(){return this.formZone;}

    /**
     * Adds a submit button for the form. Not all forms might want a submit button (for example, real time searching ones)
     * @param submitAction what should happen when the button is pressed
     */
    public void addSubmit(ActionListener submitAction){
        submit=new FormButton<>("Submit",this);
        buttonZone.add(submit);
        submit.addActionListener(submitAction);
        formZone.validate();
        formZone.repaint();
    }
    public void addClearButton(){
        clear=new FormButton<>("Clear",this);
        buttonZone.add(clear);
        clear.addActionListener(e->{clearForm();});
    }
    public void addCancelButton(ActionListener buttonAction){
        cancel=new FormButton<>("Cancel",this);
        cancel.addActionListener(buttonAction);
        buttonZone.add(cancel);
    }
    /**
     * Checks if every field has correct input and sends an object having the values from the fields.
     * Checking is done by calling the validate method for the TextInputValidator associated with the field
     * @return the generated object
     */
    public T completeAndGetObject() {
        for(FormField field:this.fieldList){
            try{
                field.validateInput();
                try{
                    PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getRealName(),this.type);
                    if(!field.getInfoAsString().equals(""))
                        propertyDescriptor.getWriteMethod().invoke(generatedObject,field.getInfo());
                }catch (IntrospectionException | InvocationTargetException | IllegalAccessException e)
                { e.printStackTrace();
                return null;}
            } catch (InputException ex)
             { JOptionPane.showMessageDialog(formZone,ex.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
                 return null;}
        }
        return this.generatedObject;
    }

    /**
     * Clears all the fields from the form
     */
    public void clearForm(){
        for(FormField field:this.fieldList){
            field.clear();
        }
        formZone.validate();
    }
    public void setBackground(Color bg){
        this.fieldZone.setBackground(bg);
        this.buttonZone.setBackground(bg);
        this.formZone.setBackground(bg);
        for(FormField field:this.fieldList){
            field.setBackground(bg);
        }
    }
    public void setForeground(Color bg){
        for(FormField field:this.fieldList){
            field.setForeground(bg);
        }
    }
    public void setFont(Font font){
        for(FormField field:this.fieldList){
            field.setFont(font);
        }
    }
    public void formatButtons(Consumer<JButton> formattingFunction){
        if(this.submit!=null)
            formattingFunction.accept(this.submit);
        if(this.clear!=null)
            formattingFunction.accept(this.clear);
        if(this.cancel!=null)
            formattingFunction.accept(this.cancel);
    }
    public void additionalFieldAction(Runnable action){
        this.fieldList.stream().forEach(e->e.addFieldAction(action));
    }
    public JButton getClearButton(){return this.clear;}
    public JButton getSubmitButton(){return this.submit;}
}
