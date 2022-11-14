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
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Address;
import com.ilCary.EPIC_ENERGY_SERVICES.services.AddressService;

@RestController
@RequestMapping("/api/addresses/") //TODO impostare la rotta
public class AddressController {

    private final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address saveAddress(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Address address = Address.builder().build();

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
    public Address updateAddress(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
            ) {

        Address address = addressService.getById(id);

        //TODO gestire il put

        addressService.save(address);
        return address;
    }

}
