package service;

import dataTransferObjects.AdminRequestDTO;
import dataTransferObjects.SessionDTO;
import dataTransferObjects.ViewUserDTO;
import entity.LegalDocumentType;
import entity.Request;
import entity.User;
import entity.enums.RequestStatus;
import service.validators.InputException;
import service.validators.InsertEmailValidator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminService extends Service{
    UserService userService;
    public AdminService(SessionDTO session){
        super(session);
        this.userService=new UserService(session);
    }
    public List<ViewUserDTO> searchUser(ViewUserDTO searchedUser){
        User userSearched= searchedUser.toUser();
        List<ViewUserDTO> results=new ArrayList<>();
        userRepo.findByFilters(userSearched).stream().forEach(u->results.add(new ViewUserDTO(u)));
        return results;
    }
    public List<User> viewAllUsers(){
        return userRepo.getAllUsers();
    }
    public boolean changeUserData(ViewUserDTO newData){
        User u;
        u=userRepo.fetchUserByID(newData.getId());
        System.out.println(u.getEMail()+" "+newData.getEMail());
        if(!u.getEMail().equals(newData.getEMail())){
            InsertEmailValidator v=new InsertEmailValidator();
            try{
                v.validate(newData.getEMail());
            }catch(InputException e){
                JOptionPane.showMessageDialog(null,"E-mail is already associated with another user");
                return false;
            }
        }
        u.setEMail(newData.getEMail());
        u.setFirstName(newData.getFirstName());
        u.setLastName(newData.getLastName());
        u.setCnp(newData.getCnp());
        u.setPhone(newData.getPhoneNumber());
        return userRepo.updateUser(u);
    }
    public boolean removeUser(ViewUserDTO toBeDeleted){
        return userRepo.deleteUser(toBeDeleted.getId());
    }
    public List<AdminRequestDTO> viewRequests(){
        List<Request> reqs= requestRepo.getAllDatabaseRequests();
        return reqs.stream().map(x->new AdminRequestDTO(x)).collect(Collectors.toList());
    }
    public boolean acceptRequest(AdminRequestDTO request){
        Request request1=request.toRequest();
       return requestRepo.changeStatus(request1, RequestStatus.ACCEPTED);
    }
    public boolean rejectRequest(AdminRequestDTO request){
        Request request1=request.toRequest();
        return requestRepo.changeStatus(request1, RequestStatus.REJECTED);
    }
    public boolean deleteRequest(AdminRequestDTO request){

        Request request1=request.toRequest();
        return requestRepo.deleteRequest(request1.getId());
    }
    public List<AdminRequestDTO> searchRequest(AdminRequestDTO filters){
        return requestRepo.filterRequestsWithJoins(filters.toRequest()).stream().map(x->new AdminRequestDTO(x)).collect(Collectors.toList());
    }

    public List<LegalDocumentType> viewDocumentTypes(){
        return legalDocumentTypeRepo.getAllTypes();
    }
    public boolean addDocumentType(LegalDocumentType newType){
        return legalDocumentTypeRepo.addDocumentType(newType);
    }
    public boolean editDocumentType(LegalDocumentType newType){

        if(legalDocumentTypeRepo.typeExists(newType.getTypeName()))
        {
            JOptionPane.showMessageDialog(null,"There is already a type with this name");
            return false;
        }
        return legalDocumentTypeRepo.editDocumentType(newType);
    }
}
