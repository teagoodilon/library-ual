package br.com.compass.pb.libraryual.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAssociatedEntitiesValidator.class)
@Documented
public @interface ValidAssociatedEntities {

    String message() default "Invalid associated entities";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
