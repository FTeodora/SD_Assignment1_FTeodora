package service;

import dataTransferObjects.SessionDTO;

import dataTransferObjects.ViewPersonalDataDTO;
import entity.PersonalProperty;
import entity.User;
import repository.LegalDocumentTypeRepo;
import repository.PersonalPropertyRepo;
import repository.RequestRepo;
import repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

public abstract class Service {
    protected SessionDTO session=null;
    protected UserRepo userRepo=new UserRepo();
    protected RequestRepo requestRepo=new RequestRepo();
    protected LegalDocumentTypeRepo legalDocumentTypeRepo=new LegalDocumentTypeRepo();
    protected PersonalPropertyRepo personalPropertyRepo=new PersonalPropertyRepo();
    public Service(SessionDTO session){this.session=session;}
    public OutsiderService logOut(){
        this.session=null;
        return new OutsiderService();
    }
    public SessionDTO getSession(){ return this.session;}
    public ViewPersonalDataDTO viewPersonalData(){
        return new ViewPersonalDataDTO(userRepo.findByEMail(session.geteMail()));
    }
    public List<PersonalProperty> viewPersonalProperties(){
        User u=new User();
        u.setId(session.getId());
        return  personalPropertyRepo.getAllPersonalProperties(u);
    }
    public boolean addPersonalProperty(PersonalProperty newProperty){
        User u=new User();
        u.setId(session.getId());
        newProperty.setUser(u);
        newProperty.autofillFields();
        return personalPropertyRepo.insertNewAddress(newProperty);
    }
    public boolean removePersonalProperty(PersonalProperty oldProperty){
        return personalPropertyRepo.removeAddress(oldProperty);
    }

}
