package service.validators;

@FunctionalInterface
public interface TextInputValidator {
    String CNPPattern="[5|6|1|2][0-9]{2}((0[0-9])|(1[0-2]))(([0-2][0-9])|(3[0-1]))[0-9]{6}";
    String eMailPattern="^[^0-9]+[0-9]*([.|-|_][\\w]+)*@[\\w]+(-[\\w]+)*[.][\\w]+";
    String phonePattern="07[0-9]{8}";
    String mandatoryFieldMessage="This field is mandatory";
    /**
     * Method for checking if an input from the GUI is correct
     * @param input input to be validated
     * @throws InputException if input is incorrect
     */
    void validate(Object input)throws InputException;
}
