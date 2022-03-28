package presentation.util.objectWindow;

import presentation.util.formConverter.WindowObjectForm;
import presentation.util.formConverter.fields.JLabelField;

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
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ObjectWindow<T> extends JPanel {
    protected JTextArea head,center,left,right;
    private boolean isInitialized=false;
    private JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.TRAILING));
    protected List<WindowPanelButton<T>> extraActions=new ArrayList<>();
    protected JPanel viewMode=new JPanel(new BorderLayout());
    protected WindowObjectForm<T> editMode;
    private WindowPanelButton<T> editButton;
    protected T retainedInfo;
    Class objectClass;
    public ObjectWindow(Class objectClass,T displayedObject){
        this.objectClass=objectClass;
        this.retainedInfo=displayedObject;
        this.setLayout(new CardLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        this.setPreferredSize(new Dimension(660,250));
        this.setBackground(Color.yellow);
        viewMode.setBackground(getBackground());

        head=new JTextArea();
        head.setLineWrap(true);
        head.setEditable(false);
        head.setBorder(BorderFactory.createEmptyBorder(1,0,2,0));
        JPanel centerPanel=new JPanel(new GridLayout(1,0));

        center=new JTextArea();
        center.setLineWrap(true);
        center.setEditable(false);
        center.setBorder(BorderFactory.createEmptyBorder(0,2,0,2));

        left=new JTextArea();
        left.setLineWrap(true);
        left.setEditable(false);
        left.setBorder(BorderFactory.createEmptyBorder(0,1,0,2));

        right=new JTextArea();
        right.setLineWrap(true);
        right.setEditable(false);
        right.setBorder(BorderFactory.createEmptyBorder(0,2,0,1));

        centerPanel.add(left);
        centerPanel.add(center);
        centerPanel.add(right);
        head.setForeground(Color.BLACK);
        left.setForeground(Color.BLACK);
        right.setForeground(Color.BLACK);
        center.setForeground(Color.BLACK);

        viewMode.add(head,BorderLayout.NORTH);
        viewMode.add(centerPanel,BorderLayout.CENTER);
        viewMode.add(buttonPanel,BorderLayout.SOUTH);

        objectToLayout(displayedObject);
        this.add(viewMode);
        this.isInitialized=true;
    }
    public void addButtons(List<WindowPanelButton<T>> aboutButtons){
        aboutButtons.stream().forEach(v->{
            if(v.checkAddingConditions(this.retainedInfo))
            {
                WindowPanelButton<T> cloned=v.clone();
                cloned.setParentWindow(this);
                this.extraActions.add(cloned);
                this.buttonPanel.add(cloned);

            }
        });

    }
    public void makeEditable(ActionListener editAction, Predicate<T> editingCondition){

        if(editingCondition.test(this.retainedInfo)){
            editMode=new WindowObjectForm<>(objectClass,this.retainedInfo);
            editMode.addCancelButton(e->{switchToView();});
            editMode.addSubmit(editAction);
            editButton=new WindowPanelButton<>("Edit",e->{this.switchToEdit();},editingCondition);
            editButton.setParentWindow(this);
            buttonPanel.add(editButton);
            editMode.setBackground(this.getBackground());
            editMode.setFont(head.getFont());
            editMode.setForeground(head.getForeground());
            this.add(editMode.getFormZone());
        }

    }
    public void formatButton(Consumer<JButton> formattingFunction){
        if(editMode!=null)
        editMode.formatButtons(formattingFunction);
        if(editButton!=null)
            formattingFunction.accept(editButton);
        this.extraActions.stream().forEach(button -> {formattingFunction.accept(button);});
    }
    public void switchToView(){
        ((CardLayout)this.getLayout()).first(this);
    }
    public void switchToEdit(){
        ((CardLayout)this.getLayout()).last(this);
    }
    public T getRetainedInfo(){
        return this.retainedInfo;
    }

    private void updateInfo(){
        this.retainedInfo=(T) this.editMode.completeAndGetObject();
    }
    private void objectToLayout(T src){
        for(Field field:objectClass.getDeclaredFields()){
            if(field.isAnnotationPresent(SwingWindow.class)){
                try{
                    SwingWindow annotation = field.getAnnotation(SwingWindow.class);
                    Object value = (new PropertyDescriptor(field.getName(), this.objectClass)).getReadMethod().invoke(src);

                    JLabelField fieldLabel = new JLabelField();
                    fieldLabel.setInfo(value);
                    fieldLabel.setInfo(annotation.additionalLabel()+fieldLabel.getInfoAsString());
                    switch (annotation.positionInWindow()) {
                        case CENTER -> {center.append(fieldLabel.getInfoAsString());
                        }
                        case UP -> {head.append(fieldLabel.getInfoAsString());
                        }
                        case LEFT -> {left.append(fieldLabel.getInfoAsString());
                        }
                        case RIGHT -> right.append(fieldLabel.getInfoAsString());
                    }
                }catch (IntrospectionException| InvocationTargetException |IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setFont(Font font) {
        if(isInitialized){
            head.setFont(font);
            left.setFont(font);
            center.setFont(font);
            right.setFont(font);
            if(this.editMode!=null)
                editMode.setFont(font);
        }
    }
    public void setBackground(Color bg){
        if(isInitialized){
            head.setBackground(bg);
            left.setBackground(bg);
            center.setBackground(bg);
            right.setBackground(bg);
            this.viewMode.setBackground(bg);
            super.setBackground(bg);
            buttonPanel.setBackground(bg);
            if (editMode != null)
                editMode.setBackground(bg);
        }
    }
    public void setForeground(Color fg){
        if(isInitialized){
            head.setForeground(fg);
            left.setForeground(fg);
            center.setForeground(fg);
            right.setForeground(fg);
            if(this.editMode!=null)
                editMode.setForeground(fg);
        }

    }
}
