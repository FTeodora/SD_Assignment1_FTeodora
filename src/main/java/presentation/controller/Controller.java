package presentation.controller;

import service.Service;

public class Controller {
    protected Service service;
    public Controller(){

    }
    public Controller(Controller prev){
        this.service=prev.service;
    }
    public Controller(Service service){
        this.service=service;
    }
    public Service getService() {
        return service;
    }
}
