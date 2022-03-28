package presentation.controller;

import dataTransferObjects.SessionDTO;

public abstract class AfterLogInController extends Controller{
    public AfterLogInController(SessionDTO session){

    }
    public AfterLogInController(AfterLogInController previous){
        super(previous);
    }
    public SessionDTO getSession(){return this.service.getSession();}
    public void doLogOut(){ this.service.logOut();}
}
