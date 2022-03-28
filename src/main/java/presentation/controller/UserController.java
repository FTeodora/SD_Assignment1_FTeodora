package presentation.controller;

import dataTransferObjects.SessionDTO;
import service.UserService;

public class UserController extends AfterLogInController{
    public UserController(SessionDTO session){
        super(session);
        this.service=new UserService(session);
    }
}
