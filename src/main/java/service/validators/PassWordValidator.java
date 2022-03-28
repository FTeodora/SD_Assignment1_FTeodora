package service.validators;

public class PassWordValidator implements TextInputValidator{
    @Override
    public void validate(Object input) throws InputException {
        char[] password=(char[])input;
        if(password.length<1)
            throw new InputException(mandatoryFieldMessage);
    }
}
