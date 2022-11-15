package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Municipality;
import com.ilCary.EPIC_ENERGY_SERVICES.models.Province;
import com.ilCary.EPIC_ENERGY_SERVICES.services.MunicipalityService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.ProvinceService;

@RestController
@RequestMapping("/api/provinces/") 
public class Init {

	@Autowired
	 ProvinceService ps;

	@Autowired
	 MunicipalityService ms;
	
//	http://localhost:8080/api/provinces/add
	
	@GetMapping("add")
	public void addProvincesAndMunicipalities() {
	
	List<List<String>> records = new ArrayList<>();

	Scanner scanner;

	try {
		scanner = new Scanner(new File("assets/comuni-italiani.csv"));
		while (scanner.hasNextLine()) {
			records.add(getRecordFromLine(scanner.nextLine()));
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}

	String currentP = "0";
	int i = 0;

	for (List<String> stringa : records) {

		String[] s = stringa.get(0).split(";");

		String provincia_id = s[0];
		String provincia_nome = s[3];
		
		if (!provincia_id.equals(currentP)) {

			Province p = Province.builder().name(provincia_nome).build();
			ps.save(p);

			currentP = provincia_id;

			i++;
			
		}

		String comune_nome = s[2];

		Municipality m = Municipality.builder().name(comune_nome).province(ps.getById((long) i)).build();
		ms.save(m);
	}

}

private List<String> getRecordFromLine(String line) {
	List<String> values = new ArrayList<String>();
	try (Scanner rowScanner = new Scanner(line)) {
		rowScanner.useDelimiter("\n");
		while (rowScanner.hasNext()) {
			values.add(rowScanner.next());
		}
	}
	return values;
}
}
