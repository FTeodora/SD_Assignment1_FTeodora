package presentation.controller;

import dataTransferObjects.LogInDTO;
import dataTransferObjects.SessionDTO;
import service.OutsiderService;
import service.validators.InputException;


public class LogInController extends Controller{
    private OutsiderService outsiderService=new OutsiderService();
    public LogInController(){
    }
    public SessionDTO doLogin(LogInDTO user)throws InputException{
        return outsiderService.logIn(user);
    }

    public OutsiderService getOutsiderService() {
        return outsiderService;
    }
}
