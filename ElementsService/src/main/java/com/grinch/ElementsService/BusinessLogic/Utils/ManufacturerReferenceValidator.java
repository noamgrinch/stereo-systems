package com.grinch.ElementsService.BusinessLogic.Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.grinch.ElementsService.BusinessLogic.Manufacturer;


public class ManufacturerReferenceValidator implements ConstraintValidator<ValidManufacturerReference, Manufacturer> {
	
    public void initialize(ValidManufacturerReference constraintAnnotation) {

    }


    public boolean isValid(Manufacturer object, ConstraintValidatorContext context) {
        if (!(object instanceof Manufacturer)) {
            throw new IllegalArgumentException("@ValidManufacturer only applies to Manufacturer instance.");
        }
        Manufacturer Manufacturer = (Manufacturer) object;
        if (Manufacturer.getId()==null || Manufacturer.getId()<=0) {
            this.validationFailure(context, "Invalid Manufacturer id.");
        } 
        if (Manufacturer.getName()==null || Manufacturer.getName().isEmpty()) {
            this.validationFailure(context, "Manufacturer name cannot be null or empty.");
        } 
        if (Manufacturer.getName().length()>50) {
            this.validationFailure(context, "Manufacturer name cannot be bigger than 50 characters.");
        } 
        return true;
    }
    
    private boolean validationFailure(ConstraintValidatorContext context,String message) {
    	context.disableDefaultConstraintViolation();
    	context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    	return false;
    }

}
