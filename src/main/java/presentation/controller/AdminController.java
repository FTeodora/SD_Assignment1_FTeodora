package presentation.controller;

import dataTransferObjects.SessionDTO;
import service.AdminService;

public class AdminController extends  AfterLogInController{
    public AdminController(SessionDTO session){
        super(session);
        this.service=new AdminService(session);
    }
}
