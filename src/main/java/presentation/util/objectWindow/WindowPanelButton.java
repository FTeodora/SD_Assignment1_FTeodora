package presentation.util.objectWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Predicate;

/**
 *
 * @param <T> the class for the ObjectWindow object type associated with this button
 */
public class WindowPanelButton<T> extends JButton implements Cloneable{

    String actionName;
    ActionListener buttonAction;
    Predicate<T> addingCondition;
    ObjectWindow<T> parentWindow;
    /**
     *
     * @param buttonName the text for the button
     * @param action what should happen when the button is pressed
     * @param predicate in which conditions should the button be added to the corresponding ObjectWindow
     */
    WindowPanelButton(String buttonName,ActionListener action,Predicate<T> predicate){
        this.actionName=buttonName;
        this.buttonAction=action;
        this.addingCondition=predicate;
        this.setText(buttonName);
        this.addActionListener(buttonAction);
    }
    boolean checkAddingConditions(T testingObject){
        return this.addingCondition.test(testingObject);
    }
    public void setParentWindow(ObjectWindow<T> parent){
        this.parentWindow=parent;
    }
    public T getObject(){ return this.parentWindow.getRetainedInfo();}

    @Override
    public WindowPanelButton<T> clone() {
        try {
            WindowPanelButton clone = (WindowPanelButton) super.clone();
            clone = new WindowPanelButton(this.actionName,this.buttonAction,this.addingCondition);
            clone.parentWindow=this.parentWindow;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public void addAction(ActionListener e){
        ActionEvent event=new ActionEvent(this,420,this.actionName);
        this.fireActionPerformed(event);
        e.actionPerformed(event);
    }

}