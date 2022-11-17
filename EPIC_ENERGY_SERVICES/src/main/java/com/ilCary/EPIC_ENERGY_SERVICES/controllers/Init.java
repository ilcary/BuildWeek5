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

		List<List<String>> provinces = new ArrayList<>();

		Scanner scanner;
		Scanner scanner2;

		try {

			scanner = new Scanner(new File("assets/comuni-italiani.csv"));
			scanner2 = new Scanner(new File("assets/province-italiane.csv"));

			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
			while (scanner2.hasNextLine()) {
				provinces.add(getRecordFromLine(scanner2.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		records.remove(0);
		provinces.remove(0);

		// Province

		for (List<String> stringa : provinces) {

			String[] s = stringa.get(0).split(";");

			String provincia_nome = s[1];
			String provincia_sigla = s[0];
			String provincia_regione = s[2];

			Province p = Province.builder().nome(provincia_nome).sigla(provincia_sigla).regione(provincia_regione)
					.build();
			ps.save(p);

		}

		// Comuni

		for (List<String> stringa : records) {

			String[] s = stringa.get(0).split(";");
			String comune_nome = s[2];
			String provincia_nome = s[3];

			Municipality m = Municipality.builder().name(comune_nome).province(ps.getByNome(provincia_nome)).build();
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
