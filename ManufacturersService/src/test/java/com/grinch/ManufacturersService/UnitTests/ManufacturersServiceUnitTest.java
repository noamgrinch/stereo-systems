package com.grinch.ManufacturersService.UnitTests;

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

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;
import com.grinch.ManufacturersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ManufacturersService.Exceptions.StereoFiException;
import com.grinch.ManufacturersService.Repositories.ManufacturersRepository;
import com.grinch.ManufacturersService.Services.ClientOriginsServiceImpl;
import com.grinch.ManufacturersService.Services.ManufacturersService;





public class ManufacturersServiceUnitTest {
    @InjectMocks
    private ManufacturersService manufacturersService;
 
    @Mock
    private ManufacturersRepository manufacturersRepository;
    
    @Mock
    private ClientOriginsServiceImpl originsService;
 
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void validManufacturerTest() throws Exception {
    	Manufacturer manu = new Manufacturer();
    	manu.setName("Test");
    	manu.setId((long)1);
        Mockito.when(manufacturersRepository.findById((long) 1)).thenReturn(Optional.ofNullable(manu));
        Manufacturer result = manufacturersService.getManufacturer((long) 1);
        Assert.assertEquals(manu.getName(), result.getName());
    }
    
    @Test
    public void duplicateNameInsertTest() throws StereoFiException {
    	Manufacturer manu = new Manufacturer();
    	manu.setName("Evo");
    	manu.setId((long) 1);
    	Manufacturer dup = new Manufacturer();
        dup.setId((long) 2);
        dup.setName("Evo");
        Mockito.when(manufacturersRepository.findByName(manu.getName()))
        	.thenReturn(Optional.ofNullable(dup));
        Mockito.when(manufacturersRepository.findById((long) 1)).thenReturn(Optional.ofNullable(manu));
        Mockito.when(originsService.validate(manu.getOrigin())).thenReturn(true);
        Exception e = assertThrows(ResourceAlreadyExistsException.class,()->{
        	manufacturersService.putManufacturer(manu);
    	});
        String msg = "Manufacturer with name " + manu.getName() + " is already exists.";
        assertTrue(e instanceof ResourceAlreadyExistsException);
        assertTrue(msg.equals(e.getMessage()));
    }
    
    
}
