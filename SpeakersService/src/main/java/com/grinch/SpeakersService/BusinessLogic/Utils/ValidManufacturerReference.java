package com.grinch.SpeakersService.BusinessLogic.Utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = ManufacturerReferenceValidator.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidManufacturerReference {
    String message() default "{com.grinch.BusinessLogic.utils}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
