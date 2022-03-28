package presentation.view;

import dataTransferObjects.ViewUserDTO;
import presentation.controller.AdminController;
import presentation.controller.ManageUsersController;

import java.util.List;

public class ManageUsersWindow extends ManagementWindow<ViewUserDTO> {
    public ManageUsersWindow( AfterLogInWindow previous) {
        super("Manage users", previous,"There are no users in the database that satisfy this criteria","Try searching something different");
        this.controller=new ManageUsersController((AdminController)previous.controller);
        this.managedEntities.enableEditingMode(
                generateListenerForEdit("User",u->((ManageUsersController)controller).updateUser(u)),
                (x)->{return !(((ManageUsersController)controller).isSelf(x));});
        this.managedEntities.addAction("Delete",generateListenerForWindowButton(
                u->((ManageUsersController)controller).deleteUser(u)
                ,"delete"),(x)->{return !(((ManageUsersController)controller).isSelf(x));});
        addSearchOption(ViewUserDTO.class,u->{
            List<ViewUserDTO> res=((ManageUsersController)controller).searchUser(u);
            this.content.validate();
            content.repaint();
            return res;
        });
        refresh();
    }
    @Override
    public void refresh(){
        this.managedEntities.refresh(((ManageUsersController)controller).showAllUsers());
        super.refresh();
    }

}
