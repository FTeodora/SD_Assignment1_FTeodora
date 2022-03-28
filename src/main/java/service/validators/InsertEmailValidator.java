package service.validators;


import entity.User;
import repository.UserRepo;

public class InsertEmailValidator extends EMailValidator {
    @Override
    public void validate(Object input) throws InputException {
        super.validate(input);
        User users=(new UserRepo()).findByEMail((String)input);
        if(users!=null)
            throw new InputException("This e-mail is already in use");
    }
}
