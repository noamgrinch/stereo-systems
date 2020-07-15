package com.grinch.SpeakersService.BusinessLogic.Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;

public class SpeakerValidator implements ConstraintValidator<ValidSpeaker, Speaker> {
	
    public void initialize(ValidSpeaker constraintAnnotation) {

    }


    public boolean isValid(Speaker object, ConstraintValidatorContext context) {
        if (!(object instanceof Speaker)) {
            throw new IllegalArgumentException("@ValidSpeaker only applies to Speaker instance.");
        }
        Speaker speaker = (Speaker) object;

        if (speaker.getMinFreqResponse() >= speaker.getMaxFreqResponse()) {
            this.setContextMessage(context, "Minimum frequency cannot be lower than maximum frequency.");
            return false;
        } 
        return true;
    }
    
    private void setContextMessage(ConstraintValidatorContext context,String message) {
    	context.disableDefaultConstraintViolation();
    	context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
