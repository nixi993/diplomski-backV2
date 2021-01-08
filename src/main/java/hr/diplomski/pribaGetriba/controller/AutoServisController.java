package hr.diplomski.pribaGetriba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.diplomski.pribaGetriba.model.Osoba;
import hr.diplomski.pribaGetriba.model.Racun;
import hr.diplomski.pribaGetriba.model.SingleDataResponse;
import hr.diplomski.pribaGetriba.model.Automobil;
import hr.diplomski.pribaGetriba.model.Nalog;
import hr.diplomski.pribaGetriba.model.Stranka;
import hr.diplomski.pribaGetriba.model.NoviNalog;
import hr.diplomski.pribaGetriba.service.AutoServisService;
import hr.diplomski.pribaGetriba.service.EmailService;

@RestController
public class AutoServisController {
	
	@Autowired
	AutoServisService autoServisService;
	

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/NoviNalog")
	public SingleDataResponse NoviNalog(@RequestBody NoviNalog noviNalog){
		
		return autoServisService.NoviNalog(noviNalog);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/sviNalozi")
	public List<Nalog> sviNalozi(){
		
		return autoServisService.dohvatiSveNaloge();
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/naloziPoDjelatniku")
	public List<Nalog> naloziPoDjelatniku(String id){
		
		return autoServisService.dohvatiNalogePoDjelatniku(id);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/naloziPoStranci")
	public List<Nalog> naloziPoStranci(String id){
		
		return autoServisService.dohvatiNalogePoStranci(id);
		
	}
	

	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/preuzmiNalog")
	public String preuzmiNalog(String brojNaloga, String radnikId){
		
		return autoServisService.preuzmiNalog(brojNaloga,radnikId);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/izmjeniNalog")
	public String preuzmiNalog(@RequestBody Nalog nalog){
		
		return autoServisService.izmjeniNalog(nalog);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/dohvatiStranku")
	public Stranka dohvatiStranku(String oib){
		
		return autoServisService.dohvatiStranku(oib);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/dohvatiAuto")
	public Automobil dohvatiAutomobil(String vin){
		
		return autoServisService.dohvatiAutomobil(vin);
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/dohvatiRacun")
	public Racun dohvatiRacun(String username, String password){
		System.out.println("pozvan servis dohvatiRacun");
		return autoServisService.dohvatiRacun(username,password);
		
	}
	


}
