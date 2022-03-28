package service.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNPValidator implements TextInputValidator{
    @Override
    public void validate(Object input) throws InputException {

        if(input==null||input.equals(""))
            throw new InputException(mandatoryFieldMessage);
        Pattern pattern=Pattern.compile(CNPPattern);
        Matcher matcher=pattern.matcher((String)input);
        if(!matcher.matches()) throw new InputException("Invalid CNP format");
    }
}
