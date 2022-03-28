package presentation.view;

import entity.User;
import entity.enums.Roles;
import presentation.controller.LogInController;
import presentation.controller.RegisterController;
import presentation.util.formConverter.Form;


import javax.swing.*;
import java.awt.*;

public class RegisterWindow extends AppWindow{
    Form<User> userForm=new Form<>(User.class);
    public RegisterWindow(LogInWindow previous) {
        super("Register", previous);
        userForm.addClearButton();
        this.controller=new RegisterController((LogInController) previous.getController());
        userForm.addSubmit(e->{
            User newUser=userForm.completeAndGetObject();

            if(newUser!=null){
                newUser.setRole(Roles.USER);
                ((RegisterController)controller).register(newUser);
                JOptionPane.showMessageDialog(this,"Account created! You can now log in using these credentials","Success",JOptionPane.INFORMATION_MESSAGE);

            }else{
                JOptionPane.showMessageDialog(this,"An error occurred and the account couldn't be created","Error",JOptionPane.ERROR_MESSAGE);
            }


        });
        content.add(userForm.getFormZone(),BorderLayout.CENTER);
        userForm.setBackground(AppWindow.BACKGROUND_COLOR);
        userForm.setForeground(AppWindow.TEXT_COLOR);
        userForm.setFont(AppWindow.FORM_FONT);
        userForm.formatButtons(b->this.formatButton(b));
        setVisible(true);
    }
    @Override
    public void refresh(){
        userForm.clearForm();
    }
}
