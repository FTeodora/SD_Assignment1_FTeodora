package presentation.util.formConverter;

import presentation.util.formConverter.fields.JTextFieldForm;
import service.validators.DefaultValidator;

import java.lang.annotation.*;

/**
 * Annotation for the fields to be converted in a swing form.
 * <br>
 * <b>NOTE! The validator field must be a TextInputValidator class (or something with the same structure)</b>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SwingField {
   Class componentType() default JTextFieldForm.class;
   String labelName() default "";
   Class validator() default DefaultValidator.class;
}
