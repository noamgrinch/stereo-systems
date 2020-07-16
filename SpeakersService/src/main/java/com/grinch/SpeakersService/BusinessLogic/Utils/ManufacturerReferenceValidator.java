package com.grinch.SpeakersService.BusinessLogic.Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.grinch.SpeakersService.BusinessLogic.Manufacturer;

public class ManufacturerReferenceValidator implements ConstraintValidator<ValidManufacturerReference, Manufacturer> {
	
    public void initialize(ValidSpeaker constraintAnnotation) {

    }


    public boolean isValid(Manufacturer object, ConstraintValidatorContext context) {
        if (!(object instanceof Manufacturer)) {
            throw new IllegalArgumentException("@ValidManufacturer only applies to Manufacturer instance.");
        }
        Manufacturer Manufacturer = (Manufacturer) object;

        if (Manufacturer.getName()==null || Manufacturer.getName().isEmpty()) {
            this.setContextMessage(context, "Manufacturer name cannot be null or empty.");
            return false;
        } 
        if (Manufacturer.getName().length()>50) {
            this.setContextMessage(context, "Manufacturer name cannot be bigger than 50 characters.");
            return false;
        } 
        return true;
    }
    
    private void setContextMessage(ConstraintValidatorContext context,String message) {
    	context.disableDefaultConstraintViolation();
    	context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
