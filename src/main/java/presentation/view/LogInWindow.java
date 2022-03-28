package presentation.view;

import dataTransferObjects.LogInDTO;
import dataTransferObjects.SessionDTO;
import entity.enums.Roles;
import presentation.controller.LogInController;
import presentation.util.ImagePanel;
import presentation.util.Navbar;

import service.AdminService;
import service.Service;
import service.validators.InputException;

import javax.swing.*;
import java.awt.*;


public class LogInWindow extends AppWindow {
    JTextField eMail;
    JPasswordField passwordField;
    JButton login,register;
    JLabel eMailLabel,passwordLabel;
    Navbar extraOptions;
    JPanel inputPanel;
    ImagePanel background;
    public LogInWindow(){
        super(700,550,"Log in");
        extraOptions=new Navbar(AppWindow.HEADER_COLOR);
        login=new JButton("Log in");
        formatButton(login);
        addLogInButtonEvent();
        register=new JButton("Register");
        addRegisterButtonListener();
        formatButton(register);
        extraOptions.addRightOption(login);
        extraOptions.addRightOption(register);
        inputPanel=new JPanel(new GridLayout(0,1));
        eMailLabel=new JLabel("e-mail:");
        eMailLabel.setFont(AppWindow.FORM_FONT);
        passwordLabel=new JLabel("password:");
        passwordLabel.setFont(AppWindow.FORM_FONT);
        controller=new LogInController();
        eMail=new JTextField();
        eMail.setMaximumSize(new Dimension(300,30));
        eMail.setPreferredSize(new Dimension(300,30));
        eMail.setFont(AppWindow.FORM_FONT);
        passwordField=new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300,30));
        passwordField.setPreferredSize(new Dimension(300,30));

        inputPanel.add(eMailLabel);
        inputPanel.add(eMail);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.setMinimumSize(new Dimension(400,250));
        inputPanel.setPreferredSize(new Dimension(400,250));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        background=new ImagePanel("background.jpg");
        background.add(inputPanel);
        background.setBorder(BorderFactory.createEmptyBorder(70,30,30,30));
        this.add(background,BorderLayout.CENTER);
        this.add(extraOptions, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);
    }
    public void addLogInButtonEvent(){
        login.addActionListener(e->{
            if(this.passwordField.getPassword().length>0)
            {
                try{
                    SessionDTO sessionDTO=((LogInController)controller).doLogin(new LogInDTO(this.eMail.getText().trim(),this.passwordField.getPassword()));
                    if(sessionDTO.getPermission().equals(Roles.ADMIN)){
                        new AdminWindow(this, sessionDTO);
                    }
                    else
                    {
                        if(sessionDTO.getPermission().equals(Roles.USER))
                            new UserWindow(this,sessionDTO);
                        else
                            JOptionPane.showMessageDialog(this,"Unknown permissions","Error",JOptionPane.ERROR_MESSAGE);
                    }


                }catch (InputException err){
                    JOptionPane.showMessageDialog(this,err.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    passwordField.setText("");
                }
            }
        });
    }
    public void addRegisterButtonListener(){
        register.addActionListener(e->{
            new RegisterWindow(this);
        });
    }
}
