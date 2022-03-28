package presentation.controller;

import dataTransferObjects.AdminRequestDTO;
import service.AdminService;

import java.util.List;

public class ManageRequestsController extends AfterLogInController{
    public ManageRequestsController(AdminController previous) {
        super(previous);
    }
    public boolean acceptRequest(AdminRequestDTO request){
        return ((AdminService)service).acceptRequest(request);
    }
    public boolean rejectRequest(AdminRequestDTO request){
        return ((AdminService)service).rejectRequest(request);
    }
    public List<AdminRequestDTO> viewAllRequests(){
        return ((AdminService)service).viewRequests();
    }
    public boolean deleteRequest(AdminRequestDTO request){
        return ((AdminService)service).deleteRequest(request);
    }
    public List<AdminRequestDTO> searchRequests(AdminRequestDTO filters){
        return ((AdminService)service).searchRequest(filters);
    }
}
