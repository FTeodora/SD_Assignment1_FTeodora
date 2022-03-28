package service.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements TextInputValidator{
    @Override
    public void validate(Object input) throws InputException {

        if(input==null||input.equals(""))
            return;
        Pattern pattern=Pattern.compile(phonePattern);
        Matcher matcher=pattern.matcher((String)input);
        if(!matcher.matches()) throw new InputException("Phone number must respect the format 07**-***-***(without the lines)");
    }
}
