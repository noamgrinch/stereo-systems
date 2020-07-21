package com.grinch.ElementsService.UnitTests;

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
import com.grinch.ElementsService.BusinessLogic.Manufacturer;
import com.grinch.ElementsService.BusinessLogic.Entites.Element;
import com.grinch.ElementsService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ElementsService.Repositories.ElementsRepository;
import com.grinch.ElementsService.Services.ElementsService;



public class ElementsServiceUnitTest {
    @InjectMocks
    private ElementsService ElementService;
 
    @Mock
    private ElementsRepository ElementRepository;
 
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void ValidElementTest() throws Exception {
        Element Element = new Element();
        Element.setName("Evo");
        Element.setId((long) 1);
        Mockito.when(ElementRepository.findById((long) 1)).thenReturn(Optional.ofNullable(Element));
        Element result = ElementService.getElement((long) 1);
        Assert.assertEquals(Element.getName(), result.getName());
    }
    
    
    @Test
    public void DuplicateNameUpdateElementTest() throws Exception {
        Element Element = new Element();
        Element.setName("Evo");
        Element.setId((long) 1);
        Element.setManufacturerReference(new Manufacturer((long) 1,"Test"));
        Element dup = new Element();
        dup.setId((long) 2);
        dup.setName("Evo");
        dup.setManufacturerReference(new Manufacturer((long) 1,"Test"));
        Mockito.when(ElementRepository.findByNameAndManufacturerReference_Id(Element.getName(), Element.getManufacturerReference().getId()))
        	.thenReturn(Optional.ofNullable(dup));
        Mockito.when(ElementRepository.findById((long) 1)).thenReturn(Optional.ofNullable(Element));
        Exception e = assertThrows(ResourceAlreadyExistsException.class,()->{
    		ElementService.putElement(Element);
    	});
        String msg = "Element with name " + Element.getName() + " is already exists for this manufacturer.";
        assertTrue(e instanceof ResourceAlreadyExistsException);
        assertTrue(msg.equals(e.getMessage()));
    }
}
