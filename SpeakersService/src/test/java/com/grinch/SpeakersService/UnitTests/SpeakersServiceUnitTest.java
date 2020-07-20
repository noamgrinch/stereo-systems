package com.grinch.SpeakersService.UnitTests;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.grinch.SpeakersService.BusinessLogic.Manufacturer;
import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
import com.grinch.SpeakersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.SpeakersService.Exceptions.ResourceNotFoundException;
import com.grinch.SpeakersService.Repositories.SpeakersRepository;
import com.grinch.SpeakersService.Services.SpeakersService;

public class SpeakersServiceUnitTest {
    @InjectMocks
    private SpeakersService speakerService;
 
    @Mock
    private SpeakersRepository speakerRepository;
 
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void ValidSpeakerTest() throws Exception {
        Speaker speaker = new Speaker();
        speaker.setName("Evo");
        speaker.setId((long) 1);
        Mockito.when(speakerRepository.findById((long) 1)).thenReturn(Optional.ofNullable(speaker));
        Speaker result = speakerService.getSpeaker((long) 1);
        Assert.assertEquals(speaker.getName(), result.getName());
    }
    
    @Test
    public void InvalidSpeakerDuplicateNameTest() {
    	Exception exception = assertThrows(ResourceNotFoundException.class,()->{
    		speakerService.getSpeaker((long) 1);
    	});
    	assertTrue(exception instanceof ResourceNotFoundException);
    	String msg = "Speaker with id " + 1 + " was not found.";
    	assertTrue(exception.getMessage().equals(msg));
    }
    
    @Test
    public void DuplicateNameUpdateSpeakerTest() throws Exception {
        Speaker speaker = new Speaker();
        speaker.setName("Evo");
        speaker.setId((long) 1);
        speaker.setManufacturerReference(new Manufacturer((long) 1,"Test"));
        Speaker dup = new Speaker();
        dup.setId((long) 2);
        dup.setName("Evo");
        dup.setManufacturerReference(new Manufacturer((long) 1,"Test"));
        Mockito.when(speakerRepository.findByNameAndManufacturerReference_Id(speaker.getName(), speaker.getManufacturerReference().getId()))
        	.thenReturn(Optional.ofNullable(dup));
        Mockito.when(speakerRepository.findById((long) 1)).thenReturn(Optional.ofNullable(speaker));
        Exception e = assertThrows(ResourceAlreadyExistsException.class,()->{
    		speakerService.putSpeaker(speaker);
    	});
        String msg = "Speaker with name " + speaker.getName() + " is already exists for this manufacturer.";
        assertTrue(e instanceof ResourceAlreadyExistsException);
        assertTrue(msg.equals(e.getMessage()));
    }
}
