package com.grinch.ReceiversService.UnitTests;

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
import com.grinch.ReceiversService.BusinessLogic.Manufacturer;
import com.grinch.ReceiversService.BusinessLogic.Entities.Receiver;
import com.grinch.ReceiversService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ReceiversService.Repositories.ReceiversRepository;
import com.grinch.ReceiversService.Services.ReceiversService;

public class ReceiversServiceUnitTest {
    @InjectMocks
    private ReceiversService ReceiverService;
 
    @Mock
    private ReceiversRepository ReceiverRepository;
 
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void ValidReceiverTest() throws Exception {
        Receiver Receiver = new Receiver();
        Receiver.setName("Evo");
        Receiver.setId((long) 1);
        Mockito.when(ReceiverRepository.findById((long) 1)).thenReturn(Optional.ofNullable(Receiver));
        Receiver result = ReceiverService.getReceiver((long) 1);
        Assert.assertEquals(Receiver.getName(), result.getName());
    }
    
    
    @Test
    public void DuplicateNameUpdateReceiverTest() throws Exception {
        Receiver Receiver = new Receiver();
        Receiver.setName("Evo");
        Receiver.setId((long) 1);
        Receiver.setManufacturerReference(new Manufacturer((long) 1,"Test"));
        Receiver dup = new Receiver();
        dup.setId((long) 2);
        dup.setName("Evo");
        dup.setManufacturerReference(new Manufacturer((long) 1,"Test"));
        Mockito.when(ReceiverRepository.findByNameAndManufacturerReference_Id(Receiver.getName(), Receiver.getManufacturerReference().getId()))
        	.thenReturn(Optional.ofNullable(dup));
        Mockito.when(ReceiverRepository.findById((long) 1)).thenReturn(Optional.ofNullable(Receiver));
        Exception e = assertThrows(ResourceAlreadyExistsException.class,()->{
    		ReceiverService.putReceiver(Receiver);
    	});
        String msg = "Receiver with name " + Receiver.getName() + " is already exists for this manufacturer.";
        assertTrue(e instanceof ResourceAlreadyExistsException);
        assertTrue(msg.equals(e.getMessage()));
    }
}
