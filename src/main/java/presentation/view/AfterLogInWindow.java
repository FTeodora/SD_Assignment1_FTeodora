package presentation.view;

import dataTransferObjects.SessionDTO;
import presentation.controller.AfterLogInController;

import javax.swing.*;
import java.awt.*;

public abstract class AfterLogInWindow extends AppWindow{
    JButton logOut;
    JButton viewPersonalData;
    public AfterLogInWindow(LogInWindow previous, SessionDTO session) {
        super("Cluj-Napoca City Hall", previous);
        this.setSize(new Dimension(1200,800));
        this.setLocationRelativeTo(null);
        initializeNavbarButton(session.getName());
        this.setVisible(true);
    }
    public AfterLogInWindow(AfterLogInWindow previous){
        super("Cluj-Napoca City Hall", previous);
        initializeNavbarButton(((AfterLogInController)previous.getController()).getSession().getName());
        this.setVisible(true);

    }

    public AfterLogInWindow(String pageName, AfterLogInWindow previous){
        this(previous);
        JLabel pageTitle=new JLabel(pageName);
        pageTitle.setForeground(AppWindow.BACKGROUND_COLOR);
        pageTitle.setFont(AppWindow.TITLE_FONT);
        this.mainNavbar.addCenterContent(pageTitle);
        this.validate();
        this.repaint();
    }
    private void initializeNavbarButton(String sessionName){
        viewPersonalData=new JButton(sessionName);
        mainNavbar.addRightOption(viewPersonalData);
        viewPersonalData.addActionListener(e->{new PersonalDataWindow(this);});
        viewPersonalData.setBackground(AppWindow.HEADER_COLOR);
        viewPersonalData.setForeground(AppWindow.BUTTON_FOREGROUND);
        viewPersonalData.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        viewPersonalData.setFont(AppWindow.HEADER_FONT);

        logOut=new JButton();
        logOut.setPreferredSize(new Dimension(50,50));
        logOut.setBackground(AppWindow.HEADER_COLOR);
        addIconToButton(logOut,"images/log_out.png");
        mainNavbar.addRightOption(logOut);
        logOut.addActionListener(e->{
            ((AfterLogInController)controller).doLogOut();
            this.dispose();
            new LogInWindow();
        });
    }

}
