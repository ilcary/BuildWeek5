package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Address;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Client;
import com.ilCary.EPIC_ENERGY_SERVICES.services.AddressService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ClientService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.MunicipalityService;

@RestController
@RequestMapping("/api/addresses/") //TODO impostare la rotta
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private MunicipalityService municipalityService;
    
    @Autowired
    private ClientService clientService;
    
    
//---------------------------- Get ---------------------------------
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Address> getAddressList() {
        return addressService.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Address getAddressById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }
    
//---------------------------- Post --------------------------------

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Address saveAddress(
    		@RequestParam(value="street",required=false) String street,
            @RequestParam(value="streetNum",required=false) String streetNum,
            @RequestParam(value="zip",required=false) String zip,
            @RequestParam(value="municipalityId",required=false) Long municipalityId,
            @RequestParam(value="clientId",required=false) Long clientId
    ) {
        Address address = Address.builder()
        		.street(street)
        		.streetNum(streetNum)
        		.zip(zip)
        		.municipality(municipalityService.getById(municipalityId))
        		.client(clientService.getById(clientId))
        		.build();

        logger.info("Save Address in AddressController");
        addressService.save(address);
        return address;
    }

//---------------------------- Put ---------------------------------    
    
    // Non serve veramente
    
//    @PutMapping("{id}")
//    //@PreAuthorize("hasRole('ADMIN')")
//    public Address updateUser(@PathVariable("id") Long id,
//    		 @RequestParam(value="street",required=false) String street,
//             @RequestParam(value="streetNum",required=false) String streetNum,
//             @RequestParam(value="zip",required=false) String zip,
//             @RequestParam(value="clientId",required=false) Long clientId
//     ) {
//
//    	Address address = addressService.getById(id);
//
//        if(street != null) address.setStreet(street);
//        if(streetNum != null) address.setStreetNum(streetNum);
//        if(zip != null) address.setZip(zip);
//        if(client != null) address.setClient(client);
//
//        addressService.save(address);
//        return address;
//    }
    
//---------------------------- Delete -------------------------------

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAddressById(@PathVariable("id") Long id) {
        addressService.deleteById(id);
        return "Address deleted successfully";
    }
    
}
