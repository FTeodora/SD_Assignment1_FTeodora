package presentation.controller;

import dataTransferObjects.ViewUserDTO;
import service.AdminService;

import java.util.ArrayList;
import java.util.List;

public class ManageUsersController extends AfterLogInController{
    public ManageUsersController(AfterLogInController previous) {
        super(previous);
    }
    public List<ViewUserDTO> showAllUsers(){
        List<ViewUserDTO> users=new ArrayList<>();
        ((AdminService)service).viewAllUsers().stream().forEach(x->users.add(new ViewUserDTO(x)));
        return users;
    }
    public boolean isSelf(ViewUserDTO checkedUser){
        return checkedUser.getId().equals(service.getSession().getId());
    }
    public boolean updateUser(ViewUserDTO userDTO){
        return ((AdminService)service).changeUserData(userDTO);
    }
    public boolean deleteUser(ViewUserDTO userDTO){
        return ((AdminService)service).removeUser(userDTO);
    }
    public List<ViewUserDTO> searchUser(ViewUserDTO user){
        return ((AdminService)service).searchUser(user);
    }
}
