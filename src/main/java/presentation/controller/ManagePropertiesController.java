package presentation.controller;

import entity.PersonalProperty;

import java.util.List;

public class ManagePropertiesController extends AfterLogInController{

    public ManagePropertiesController(AfterLogInController previous) {
        super(previous);
    }
    public List<PersonalProperty> fetchProperties(){
        return (this.service).viewPersonalProperties();
    }
    public boolean addProperty(PersonalProperty newProperty){
        return this.service.addPersonalProperty(newProperty);
    }
    public boolean deletePersonalProperty(PersonalProperty oldProperty){
        return this.service.removePersonalProperty(oldProperty);
    }
}
