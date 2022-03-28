package presentation.controller;

import dataTransferObjects.ViewPersonalDataDTO;


public class PersonalDataController extends AfterLogInController{
    public PersonalDataController(AfterLogInController previous){
        super(previous);
    }

    public ViewPersonalDataDTO getPersonalData(){
        return (service).viewPersonalData();
    }
}
