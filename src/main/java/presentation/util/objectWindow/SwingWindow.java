package presentation.util.objectWindow;

import java.awt.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SwingWindow {
    String additionalLabel() default "";
    FieldPosition positionInWindow() default FieldPosition.LEFT;

}
