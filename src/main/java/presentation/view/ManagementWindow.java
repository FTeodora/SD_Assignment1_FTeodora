package presentation.view;

import presentation.util.Navbar;
import presentation.util.formConverter.Form;
import presentation.util.formConverter.FormButton;
import presentation.util.formConverter.SearchWindow;
import presentation.util.objectWindow.WindowPanel;
import presentation.util.objectWindow.WindowPanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class ManagementWindow<T> extends AfterLogInWindow {
    WindowPanel<T> managedEntities;
    Form<T> newEntityForm;
    JButton newTypeButton;
    Navbar footer;
    JFrame input=new JFrame("Add new");
    SearchWindow<T> searchWindow;
    public ManagementWindow(String pageName, AfterLogInWindow previous,String message1,String message2) {
        super(pageName, previous);
        content.setLayout(new BorderLayout());
        managedEntities=new WindowPanel<T>(message1,message2);
        managedEntities.setBackground(AppWindow.BACKGROUND_COLOR);
        managedEntities.setWindowsBackground(AppWindow.OBJECT_WINDOW_COLOR);
        managedEntities.setWindowsFontColor(AppWindow.OBJECT_WINDOW_TEXT_COLOR);
        managedEntities.setWindowsFont(AppWindow.WINDOW_FONT);
        managedEntities.setButtonFormatFunction(b->this.formatButton(b));
        managedEntities.refresh(new ArrayList<>());
        content.add(managedEntities,BorderLayout.CENTER);
        footer=new Navbar(AppWindow.HEADER_COLOR);
        this.add(footer, BorderLayout.SOUTH);
    }
    public void addInsertNewEntityOption(Class<T> objectClass,Predicate<T> addingAction,String entityName,boolean makeButton){
        this.newEntityForm=new Form<T>(objectClass);
        this.newEntityForm.addClearButton();
        this.newEntityForm.addSubmit(e->{
            T obj=((FormButton<T>)e.getSource()).getAssociatedInfo();
            if(obj==null)
                JOptionPane.showMessageDialog(this,"Couldn't create "+entityName+". Make sure input is correct","Err",JOptionPane.WARNING_MESSAGE);
            else
            {
                if(addingAction.test(obj))
                {
                    JOptionPane.showMessageDialog(this,entityName+" created successfully!","Info",JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                    newEntityForm.clearForm();
                    input.setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(this,"An internal error occurred and the type couldn't be added","Err",JOptionPane.ERROR_MESSAGE);
            }
        });
        this.newEntityForm.setBackground(AppWindow.BACKGROUND_COLOR);
        newEntityForm.setForeground(AppWindow.TEXT_COLOR);
        newEntityForm.setFont(AppWindow.FORM_FONT);
        newEntityForm.formatButtons(b->formatButton(b));
        input.setSize(new Dimension(500,500));
        input.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        input.setLocationRelativeTo(this);
        input.setResizable(false);
        input.setLayout(new BorderLayout());
        input.add(this.newEntityForm.getFormZone(),BorderLayout.CENTER);
        if(makeButton){
            this.newTypeButton=new JButton("Add "+entityName);
            formatButton(this.newTypeButton);
            this.newTypeButton.setPreferredSize(new Dimension(300,30));
            this.newTypeButton.addActionListener(e->{
                input.setVisible(true);

            });
            footer.addRightOption(this.newTypeButton);
        }
    }

    ActionListener generateListenerForEdit(String operatedEntityTypeName, Predicate<T> action){
        return e->{
            T u=(((FormButton<T>)(e.getSource())).getAssociatedInfo());
            if(u!=null)
            {
                if(action.test(u))
                {
                    JOptionPane.showMessageDialog(this,operatedEntityTypeName+" updated successfully!");
                    refresh();
                }
                else
                    JOptionPane.showMessageDialog(this,"An error occurred and the "+operatedEntityTypeName+" couldn't be updated","Error",JOptionPane.ERROR_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this,"Please make sure your inputs are correct","Warning",JOptionPane.WARNING_MESSAGE);
        };
    }
    ActionListener generateListenerForWindowButton(Predicate<T> action,String operationName){
        return e->{
            T u=(((WindowPanelButton<T>)(e.getSource())).getObject());
            if(u!=null)
            {
                if(action.test(u))
                {
                    JOptionPane.showMessageDialog(this,operationName+" done successfully!");
                    refresh();
                }
                else
                    JOptionPane.showMessageDialog(this,"An error occurred and the "+operationName+" wasn't successful","Error",JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this,"Couldn't retrieve information for operation","Error",JOptionPane.ERROR_MESSAGE);
        };
    }
    public void addSearchOption(Class<T> objectClass,Function<T,Collection<T>> searchAction){
        this.searchWindow =new SearchWindow<T>(objectClass,this.managedEntities, searchAction);
        JPanel wrapper=new JPanel();
        wrapper.add(this.searchWindow.getPanel());
        wrapper.setBackground(AppWindow.BACKGROUND_COLOR);
        this.content.add(wrapper,BorderLayout.WEST);
    }
}
