package com.ilCary.EPIC_ENERGY_SERVICES;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Municipality;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Province;
import com.ilCary.EPIC_ENERGY_SERVICES.services.MunicipalityService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ProvinceService;

@SpringBootApplication
public class EpicEnergyServicesApplication {



	public static void main(String[] args) {
		SpringApplication.run(EpicEnergyServicesApplication.class, args);

	}
}
