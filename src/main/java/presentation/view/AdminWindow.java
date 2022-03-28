package presentation.view;

import dataTransferObjects.SessionDTO;
import presentation.controller.AdminController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AdminWindow extends MainMenuWindow {
    public AdminWindow(LogInWindow previous, SessionDTO session) {
        super(previous,session);
        this.controller=new AdminController(session);
        this.setVisible(true);
    }

    @Override
    public void createOptions(){
        super.createOptions();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        JButton manageUsers=new JButton("Manage users");
        manageUsers.addActionListener(e->{
            new ManageUsersWindow(this);
        });
        options.add(manageUsers);

        JButton manageRequests=new JButton("Manage requests");
        manageRequests.addActionListener(e->{
            new ManageRequests(this);
        });
        options.add(manageRequests);

        JButton manageDocumentTypes=new JButton("Manage document types");
        manageDocumentTypes.addActionListener(e->{
            new ManageDocumentTypesWindow(this);
        });
        options.add(manageDocumentTypes);
        addOptions();
    }
}
