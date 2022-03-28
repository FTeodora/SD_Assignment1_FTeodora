package presentation.controller;

import dataTransferObjects.UserRequestDTO;
import entity.LegalDocumentType;
import service.UserService;

import java.util.List;

public class ManageRequestsUserController extends AfterLogInController{
    public ManageRequestsUserController(UserController previous) {
        super(previous);
    }
    public boolean submitRequest(UserRequestDTO newRequest){
        return ((UserService)service).makeRequest(newRequest);
    }
    public boolean cancelRequest(UserRequestDTO request){
        return ((UserService)service).cancelRequest(request);
    }
    public List<UserRequestDTO> fetchMyRequests(){
        return ((UserService)service).seeMyRequests();
    }
    public List<LegalDocumentType> fetchDocumentTypes(){
        return ((UserService)service).getAvailableDocumentTypes();
    }
}
