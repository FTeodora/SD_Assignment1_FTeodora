package presentation.util.formConverter;

import javax.swing.*;

public class FormButton<T> extends JButton {
    private Form<T> parentForm;
    public FormButton(String optionName,Form<T> parentForm){
        super(optionName);
        this.parentForm=parentForm;
    }
    public T getAssociatedInfo(){
        return parentForm.completeAndGetObject();
    }
}
