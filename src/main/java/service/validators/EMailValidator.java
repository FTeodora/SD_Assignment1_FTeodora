package service.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EMailValidator implements TextInputValidator{
    @Override
    public void validate(Object input) throws InputException {
        if(input==null||input.toString().equals(""))
            throw new InputException(mandatoryFieldMessage);
        Pattern pattern=Pattern.compile(eMailPattern);
        Matcher matcher=pattern.matcher(input.toString());
        if(!matcher.matches()) throw new InputException("Invalid e-mail format");
    }
}
