package service;

import dataTransferObjects.LogInDTO;
import dataTransferObjects.SessionDTO;
import entity.User;
import repository.PersonalPropertyRepo;
import repository.UserRepo;
import service.validators.InputException;
import service.validators.LogInValidator;

/**
 * A service for site visitors that don't have any accounts or are not yet logged in
 * Those users can either
 * <ul>
 * <li>register - to create an account</li>
 * <li>logIn - by using the e-mail and the password of their respective account</li>
 * </ul>
 */
public class OutsiderService {
    private UserRepo userRepo=new UserRepo();
    private PersonalPropertyRepo homeAddressRepo=new PersonalPropertyRepo();
    public void register(User user){
        user.autofillFields();
        userRepo.insertNewUser(user);
    }
    public SessionDTO logIn(LogInDTO logInData) throws InputException {
        try{
            LogInValidator validator=new LogInValidator(userRepo);
            validator.validate(logInData);
            SessionDTO sessionDTO=new SessionDTO(logInData);
            return sessionDTO;

        }catch(InputException e){
            throw e;
        }
    }
}
