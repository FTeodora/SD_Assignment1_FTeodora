package service.validators;

import dataTransferObjects.LogInDTO;
import entity.User;
import repository.UserRepo;

import java.util.Arrays;

public class LogInValidator implements TextInputValidator {
    UserRepo userRepo;
    public LogInValidator(UserRepo repo){
        this.userRepo=repo;
    }
    public void validate(Object input) throws InputException{
        LogInDTO user=(LogInDTO) input;
        User toBeLogged=this.userRepo.findByEMail(user.geteMail());
        if(toBeLogged==null) throw new InputException("There is no account associated with the e-mail");
        LogInDTO foundUser=new LogInDTO(toBeLogged.getEMail(),toBeLogged.getPassword());
        if(!Arrays.equals(user.getPassword(),foundUser.getPassword())) throw new InputException("Password is incorrect");
        ((LogInDTO)input).setUser(toBeLogged);

    }
}
