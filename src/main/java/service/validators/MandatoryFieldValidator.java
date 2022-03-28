package service.validators;

public class MandatoryFieldValidator implements TextInputValidator{
    @Override
    public void validate(Object input) throws InputException {
        if(input==null||((input instanceof String)&&input.equals("")))
            throw new InputException(mandatoryFieldMessage);
    }
}
