package service;

import dataTransferObjects.SessionDTO;
import dataTransferObjects.UserRequestDTO;
import entity.LegalDocumentType;
import entity.Request;
import entity.User;
import entity.enums.RequestStatus;

import java.util.ArrayList;
import java.util.List;

public class UserService extends Service{
    public UserService(SessionDTO session){
        super(session);
    }
    public List<LegalDocumentType> getAvailableDocumentTypes(){
        User user=new User();
        user.setId(session.getId());
        int currentRequests=requestRepo.selectRequestsThisYear(user);
        if (currentRequests < 3) {
            return legalDocumentTypeRepo.getAllTypes();
        }else{
            if(currentRequests<personalPropertyRepo.getPersonalPropertyCount(user)*3+3){
                List<LegalDocumentType> urr=legalDocumentTypeRepo.getBuildingDocumentTypes();
                System.out.println(urr.size());
                return urr;
            }
            else
                return new ArrayList<>();
        }
    }
    public boolean makeRequest(UserRequestDTO request){
        Request r=request.toRequest();
        User current=userRepo.fetchUserByID(session.getId());
        r.setRequester(current);
        r.autofillFields();
        return requestRepo.addRequest(r);
    }
    public boolean cancelRequest(UserRequestDTO request){
        Request r=request.toRequest();
        r.setRequester(userRepo.fetchUserByID(session.getId()));
        return requestRepo.changeStatus(r, RequestStatus.CANCELED);
    }
    public List<UserRequestDTO> seeMyRequests(){
        User current=new User();
        current.setId(session.getId());
        List<UserRequestDTO> requests=new ArrayList<>();
        requestRepo.getAllRequestsByUser(current).stream().forEach(x->{
            requests.add(new UserRequestDTO(x));
        });
        return requests;
    }

}
