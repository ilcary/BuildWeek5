package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/addresses/") //TODO impostare la rotta
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address saveAddress(
            @RequestParam("street") String street,
            @RequestParam(value="streetNum",required=false) String streetNum,
            @RequestParam(value="zip",required=false) String zip,
            @RequestParam(value="city",required=false) String city,
            @RequestParam(value="client",required=false) Client client
    ) {
        Address address = Address.builder()
        		.street(street)
        		.streetNum(streetNum)
        		.zip(zip)
        		.city(city)
        		.client(client)
        		.build();

        logger.info("Save Address in AddressController");
        return addressService.save(address);
    }

    @GetMapping
    public List<Address> getAddressList() {
        return addressService.getAll();
    }

    @GetMapping("{id}")
    public Address getAddressById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    @DeleteMapping("{id}")
    public String deleteAddressById(@PathVariable("id") Long id) {
        addressService.deleteById(id);
        return "Address deleted successfully";
    }

    @PutMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Address updateUser(@PathVariable("id") Long id,
    		 @RequestParam(value="street",required=false) String street,
             @RequestParam(value="streetNum",required=false) String streetNum,
             @RequestParam(value="zip",required=false) String zip,
             @RequestParam(value="city",required=false) String city,
             @RequestParam(value="client",required=false) Client client
     ) {

    	Address address = addressService.getById(id);

        if(street != null) address.setStreet(street);
        if(streetNum != null) address.setStreetNum(streetNum);
        if(zip != null) address.setZip(zip);
        if(city != null) address.setCity(city);
        if(client != null) address.setClient(client);

        addressService.save(address);
        return address;
    }

}
