package com.grinch.SpeakersService.BusinessLogic.Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.grinch.SpeakersService.BusinessLogic.ManufacturerReference;

public class ManufacturerReferenceValidator implements ConstraintValidator<ValidManufacturerReference, ManufacturerReference> {
	
    public void initialize(ValidSpeaker constraintAnnotation) {

    }


    public boolean isValid(ManufacturerReference object, ConstraintValidatorContext context) {
        if (!(object instanceof ManufacturerReference)) {
            throw new IllegalArgumentException("@ValidManufacturerReference only applies to ManufacturerReference instance.");
        }
        ManufacturerReference manufacturerReference = (ManufacturerReference) object;

        if (manufacturerReference.getName()==null || manufacturerReference.getName().isEmpty()) {
            this.setContextMessage(context, "Manufacturer name cannot be null or empty.");
            return false;
        } 
        if (manufacturerReference.getName().length()>50) {
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
