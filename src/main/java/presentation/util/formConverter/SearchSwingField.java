package presentation.util.formConverter;

import presentation.util.formConverter.fields.JTextFieldForm;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchSwingField {
    Class componentType() default JTextFieldForm.class;
    String labelName() default "";
}
