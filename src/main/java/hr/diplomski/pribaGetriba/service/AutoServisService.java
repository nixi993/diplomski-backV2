package hr.diplomski.pribaGetriba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.diplomski.pribaGetriba.model.Stranka;
import hr.diplomski.pribaGetriba.mapping.ObjectMapping;
import hr.diplomski.pribaGetriba.model.Automobil;
import hr.diplomski.pribaGetriba.model.Djelatnik;
import hr.diplomski.pribaGetriba.model.Nalog;
import hr.diplomski.pribaGetriba.model.NoviDjelatnik;
import hr.diplomski.pribaGetriba.model.NoviNalog;
import hr.diplomski.pribaGetriba.model.Racun;
import hr.diplomski.pribaGetriba.model.SingleDataResponse;
import hr.diplomski.pribaGetriba.repository.AutoServisRepository;

@Service
public class AutoServisService {
	
	@Autowired 
	AutoServisRepository autoServisRepository;
	
	public String insertStranke(Stranka stranka) {
		
		return autoServisRepository.insertStranke(stranka);
	}
	
	public SingleDataResponse noviDjelatnik(Djelatnik djelatnik) {
		
		SingleDataResponse response = autoServisRepository.dodajDjelatnika(djelatnik);
	
		return response;
	
	}
	
	public SingleDataResponse NoviNalog(NoviNalog noviNalog) {
		
		SingleDataResponse response = new SingleDataResponse();
		Stranka stranka = new Stranka();
		Automobil automobil = new Automobil();
		ObjectMapping objMapping = new ObjectMapping();
		
		try {
			
			stranka = objMapping.toStranka(noviNalog);
			automobil = objMapping.toAutomobil(noviNalog);
			
			if(stranka == null || automobil == null)
			{
				response.setSuccess(false);
				response.setData("Errorrrrrrrrrr");
				return response;
			
			}
			
			String result =autoServisRepository.insertStranke(stranka);
			
			if(result.equals("1")) {
				
				result = autoServisRepository.insertAutomobila(automobil);
				if(result.equals("1")) {
					result = autoServisRepository.insertNaloga(noviNalog,stranka.getEmail());
				}
		
			}
			
		
			
			response.setSuccess(true);
			response.setData(result);
			
			return response;
				
			
		}catch(Exception ex)
		{
			String as = ex.getMessage();
			return null;
		}
		
		
		
	}
	
	
	public List<Nalog> dohvatiSveNaloge(){
		return autoServisRepository.dohvatiSveNaloge();
	}
	public List<Nalog> dohvatiNalogePoDjelatniku(String id){
		return autoServisRepository.dohvatiNalogePoDjelatniku(id);
	}
	
	public List<Nalog> dohvatiNalogePoStranci(String id){
		return autoServisRepository.dohvatiNalogePoStranci(id);
	}
	
	public List<Djelatnik> dohvatiDjelatnike(){
		return autoServisRepository.djelatnici();
	}
	
	public SingleDataResponse obrisiDjelatnika(int id){
		return autoServisRepository.obrisiDjelatnika(id);
	}
	
	public String preuzmiNalog(String brojNaloga, String radnikId) {
		return autoServisRepository.preuzmiNalog(brojNaloga, radnikId);
			
	}
	
	public String izmjeniNalog(Nalog nalog) {
		return autoServisRepository.IzmjeniNalog(nalog);
			
	}
	
	public Stranka dohvatiStranku(String oib) {
		return autoServisRepository.dohvatiStranku(oib);
	}
	
	public Automobil dohvatiAutomobil(String vin) {
		return autoServisRepository.dohvatiAutomobil(vin);
	}
	
	public Racun dohvatiRacun(String username, String password) {
		return autoServisRepository.dohvatiRacun(username, password);
	}
	


}
