package hr.diplomski.pribaGetriba.mapping;

import hr.diplomski.pribaGetriba.model.Automobil;
import hr.diplomski.pribaGetriba.model.NoviNalog;
import hr.diplomski.pribaGetriba.model.Stranka;

public class ObjectMapping {

	public Stranka toStranka(NoviNalog noviNalog) {
		
		
		try {
			
			Stranka stranka = new Stranka();
			
			stranka.setIme(noviNalog.getIme());
			stranka.setPrezime(noviNalog.getPrezime());
			stranka.setOib(noviNalog.getOib());
			stranka.setAdresa(noviNalog.getAdresa());
			stranka.setEmail(noviNalog.getEmail());
			stranka.setTelefon(noviNalog.getTelefon());
			
			return stranka;
			
		}catch(Exception ex) {
			return null;
		}	
		
	}
	
	public Automobil toAutomobil(NoviNalog noviNalog) {
		
		try {
			
			Automobil automobil = new Automobil();
			
			automobil.setGodinaProizvodnje(noviNalog.getGodinaProizvodnje());
			automobil.setGorivo(noviNalog.getGorivo());
			automobil.setKilometri(noviNalog.getKilometri());
			automobil.setMarka(noviNalog.getMarka());
			automobil.setModel(noviNalog.getModel());
			automobil.setMjenjac(noviNalog.getMjenjac());
			automobil.setRegistracija(noviNalog.getRegistracija());
			automobil.setSnaga(noviNalog.getSnaga());
			automobil.setTip(noviNalog.getTip());
			automobil.setVin(noviNalog.getVin());
			
			return automobil;
			
		}catch(Exception ex) {
			return null;
		}
	}
}
