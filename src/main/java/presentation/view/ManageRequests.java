package presentation.view;

import dataTransferObjects.AdminRequestDTO;
import entity.Request;
import entity.enums.RequestStatus;
import presentation.controller.AdminController;
import presentation.controller.ManageRequestsController;
import presentation.controller.UserController;
import presentation.util.objectWindow.WindowPanelButton;

import java.util.List;

public class ManageRequests extends ManagementWindow<AdminRequestDTO> {
    public ManageRequests( AdminWindow previous) {
        super("Manage database requests", previous, "There are no requests are the moment","Wait for a user to submit a request");
        this.controller=new ManageRequestsController((AdminController) previous.controller);
        this.managedEntities.addAction("accept",e->{
                    AdminRequestDTO r=((WindowPanelButton<AdminRequestDTO>)e.getSource()).getObject();
            ((ManageRequestsController)controller).acceptRequest(r);
            refresh();
        },x-> x.getStatus().equals(RequestStatus.WAITING)
        );
        this.managedEntities.addAction("reject",e->{
            AdminRequestDTO r=((WindowPanelButton<AdminRequestDTO>)e.getSource()).getObject();
                    ((ManageRequestsController)controller).rejectRequest(r);
                    refresh();
                },x-> x.getStatus().equals(RequestStatus.WAITING)
        );
        this.managedEntities.addAction("delete",e->{
            AdminRequestDTO r=((WindowPanelButton<AdminRequestDTO>)e.getSource()).getObject();
                    ((ManageRequestsController)controller).deleteRequest(r);
                    refresh();
                },x-> true
        );
        addSearchOption(AdminRequestDTO.class,x->{
            List<AdminRequestDTO> res=((ManageRequestsController)controller).searchRequests(x);
             content.validate();
            content.repaint();
            return res;
        });
        refresh();
    }
    @Override
    public void refresh(){
        this.managedEntities.refresh(((ManageRequestsController)controller).viewAllRequests());
    }
}
