package presentation.view;

import dataTransferObjects.SessionDTO;
import presentation.controller.UserController;

import javax.swing.*;

public class UserWindow extends MainMenuWindow {
    public UserWindow(LogInWindow previous, SessionDTO session){
        super(previous,session);
        this.controller=new UserController(session);
        this.setVisible(true);
    }

    @Override
    public void createOptions() {
        super.createOptions();
        JButton requests=new JButton("My requests");
        requests.addActionListener(e->{
            new ManageRequestsUser(this);
        });
        this.options.add(requests);
        addOptions();

    }
}
