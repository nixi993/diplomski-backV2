package hr.diplomski.pribaGetriba.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hr.diplomski.pribaGetriba.model.Automobil;
import hr.diplomski.pribaGetriba.model.Djelatnik;
import hr.diplomski.pribaGetriba.model.DjelatnikRowMapper;
import hr.diplomski.pribaGetriba.model.Nalog;
import hr.diplomski.pribaGetriba.model.NoviNalog;
import hr.diplomski.pribaGetriba.model.Racun;
import hr.diplomski.pribaGetriba.model.Stranka;
import hr.diplomski.pribaGetriba.model.mapper.AutomobilRowMapper;
import hr.diplomski.pribaGetriba.model.mapper.NalogRowMapper;
import hr.diplomski.pribaGetriba.model.mapper.RacunRowMapper;
import hr.diplomski.pribaGetriba.model.mapper.StrankaRowMapper;
import hr.diplomski.pribaGetriba.service.EmailService;

@Repository
public class AutoServisRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EmailService emailService;
	
	public String dohvatiDjelatnika()
	{
		try {
		
	
			String sql = "select id,ime,prezime from pribaGetriba.dbo.djelatnik";
			
			Djelatnik djelatnik = jdbcTemplate.queryForObject(sql, new DjelatnikRowMapper());
			String asd = "";
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	public List<Nalog> dohvatiSveNaloge() {
		
		String sql = "select na.broj_naloga brojNaloga,na.opis_kvara opisKvara, na.status status, na.datum_zaprimanja datumZaprimanja,"+
				"na.djelatnik_id djelatnikId, na.datum_zavrsetka datumZavrsetka,"+
				"na.komentar_djelatnika komentarDjelatnika,"+
				"auto.marka marka,auto.model model,auto.tip tip,auto.vin vin,auto.registracija registracija,auto.mjenjac mjenjac,auto.gorivo gorivo, "+
			    "auto.kilometri kilometri,auto.snaga snaga,auto.godina_proizvodnje godinaProizvodnje, "+
			    "str.ime ime,str.prezime prezime,str.email email,str.telefon telefon, "+ 
			    "djel.ime imeDjelatnika, djel.prezime prezimeDjelatnika "+
				"from pribaGetriba.dbo.Nalog na "+
				"inner join pribaGetriba.dbo.Automobil auto on na.automobil_vin = auto.vin "+
				"inner join pribaGetriba.dbo.Stranka str on na.stranka_oib = str.oib "+
				"left join pribaGetriba.dbo.Djelatnik djel on djel.id = na.djelatnik_id;";
		
		
		
		
		List<Nalog> nalozi = jdbcTemplate.query(sql, new NalogRowMapper());
		
		return nalozi;
		
	}
	public List<Nalog> dohvatiNalogePoDjelatniku(String id) {
		
		List<Nalog> nalozi = new ArrayList<Nalog>();
		try {
			String sql = "select '' imeDjelatnika, '' prezimeDjelatnika, na.broj_naloga brojNaloga,na.opis_kvara opisKvara, na.status status, na.datum_zaprimanja datumZaprimanja, "+
					"na.djelatnik_id djelatnikId, na.datum_zavrsetka datumZavrsetka, "+
					"na.komentar_djelatnika komentarDjelatnika, "+
				    "auto.marka marka,auto.model model,auto.tip tip,auto.vin vin,auto.registracija registracija,auto.mjenjac mjenjac,auto.gorivo gorivo, "+
				    "auto.kilometri kilometri,auto.snaga snaga,auto.godina_proizvodnje godinaProizvodnje, "+
				    "str.ime ime,str.prezime prezime,str.email email,str.telefon telefon "+
					"from pribaGetriba.dbo.Nalog na " +
					"inner join pribaGetriba.dbo.Automobil auto on na.automobil_vin = auto.vin "+ 
					"inner join pribaGetriba.dbo.Stranka str on na.stranka_oib = str.oib "+ 
					"where na.djelatnik_id = '"+ id + "';";
			
			
			
			
			nalozi = jdbcTemplate.query(sql, new NalogRowMapper());
			
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return nalozi;
			
			
	}
	
	public List<Nalog> dohvatiNalogePoStranci(String id) {
		
		try {
		String sql = "select '' imeDjelatnika, '' prezimeDjelatnika, na.broj_naloga brojNaloga,na.opis_kvara opisKvara, na.status status, na.datum_zaprimanja datumZaprimanja, "+
				"na.djelatnik_id djelatnikId, na.datum_zavrsetka datumZavrsetka, "+ 
				"na.komentar_djelatnika komentarDjelatnika, "+ 
			    "auto.marka marka,auto.model model,auto.tip tip,auto.vin vin,auto.registracija registracija,auto.mjenjac mjenjac,auto.gorivo gorivo, "+ 
			    "auto.kilometri kilometri,auto.snaga snaga,auto.godina_proizvodnje godinaProizvodnje, "+
			    "str.ime ime,str.prezime prezime,str.email email,str.telefon telefon "+
				"from pribaGetriba.dbo.Nalog na "+
				"inner join pribaGetriba.dbo.Automobil auto on na.automobil_vin = auto.vin "+
				"inner join pribaGetriba.dbo.Stranka str on na.stranka_oib = str.oib "+  
				"where na.broj_naloga = '" + id + "';";
		
		
		
		
		List<Nalog> nalozi = jdbcTemplate.query(sql, new NalogRowMapper());
		
		return nalozi;
		
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	
		
}
	public String insertStranke(Stranka stranka) {
		
		String sql;
		int inserted = 0;
		int updated = 0;
		try {
			
			Stranka str = dohvatiStranku(stranka.getOib());
			if(str.getId() == 0) {
				sql = "Insert into pribaGetriba.dbo.Stranka (ime,prezime,adresa,telefon,email,oib) " +
					  "values(?,?,?,?,?,?)";
				inserted = jdbcTemplate.update(sql, stranka.getIme(),stranka.getPrezime(),
						stranka.getAdresa(),stranka.getTelefon(),
						stranka.getEmail(),stranka.getOib());
			}else {
				
				sql = "update pribaGetriba.dbo.Stranka "+
					  "set  ime = ?, prezime = ? , adresa = ? , telefon = ? ,email = ? ,oib = ? " +
					  " where id = ?";		
				
				updated = jdbcTemplate.update(sql, stranka.getIme(),stranka.getPrezime(),
						stranka.getAdresa(),stranka.getTelefon(),
						stranka.getEmail(),stranka.getOib(),str.getId());
			}
			
			
			
			if(inserted == 1 || updated == 1) {
				return "1";
			}else{
				return "Greska u unosu stranke.";
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "Greska u unosu stranke.";
		}

		
	}
	
	public String insertAutomobila(Automobil automobil) {
			
			try {
				String sql = "";
				int inserted = 0;
				int updated = 0;
				
				Automobil auto = dohvatiAutomobil(automobil.getVin());
				
				if(auto.getId() == 0) {
					
					sql = "Insert into pribaGetriba.dbo.Automobil (marka,model,tip,vin,registracija,mjenjac,gorivo,kilometri,snaga,godina_proizvodnje) values(?,?,?,?,?,?,?,?,?,?)";
					
					inserted = jdbcTemplate.update(sql, automobil.getMarka(),automobil.getModel(), automobil.getTip(),automobil.getVin(),
							automobil.getRegistracija(),automobil.getMjenjac(),automobil.getGorivo(),automobil.getKilometri(),automobil.getSnaga(),
							automobil.getGodinaProizvodnje());
				}else {
					
					sql = "update pribaGetriba.dbo.Automobil "+
						"set marka = ? , model = ? , tip = ?, vin = ?, registracija = ?, mjenjac = ?, gorivo = ?, "+
						"kilometri = ?, snaga = ?, godina_proizvodnje = ? "+
						"where id = ?;";
					
					updated = jdbcTemplate.update(sql, automobil.getMarka(),automobil.getModel(), automobil.getTip(),automobil.getVin(),
							automobil.getRegistracija(),automobil.getMjenjac(),automobil.getGorivo(),automobil.getKilometri(),automobil.getSnaga(),
							automobil.getGodinaProizvodnje(),auto.getId());
					
				}
				
				if(inserted == 1 || updated == 1) {
					return "1";
				}else{
					return "Greska u unosu automobila.";
				}
				
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
				return "Greska u unosu automobila.";
			}
	
			
	}
	
	public String insertNaloga(NoviNalog noviNalog,String emailTo) {
		
		try {

			
			String username = noviNalog.getPrezime().substring(0,3) + noviNalog.getIme().substring(0,3);
			String password = noviNalog.getVin().substring(0, 5) + noviNalog.getOib().substring(0, 5) + noviNalog.getOpisKvara().substring(0,5);
			
			String sql = "Insert into pribaGetriba.dbo.Nalog (automobil_vin,stranka_oib,opis_kvara,status,datum_zaprimanja,username,password,racun_id) values(?,?,?,?,CONVERT(DATE,?,104),?,?,?)";
			

			
			int inserted = jdbcTemplate.update(sql,noviNalog.getVin(),noviNalog.getOib(),noviNalog.getOpisKvara(),"Zaprimljeno", new Date(),username,password,13);
			
		
			if(inserted == 1) {
				emailService.posaljiMailKlijentu(emailTo, username, password);
				return "1";
			}else{
				return "Greska u unosu naloga.";
			}
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "Greska u unosu naloga.";
		}

		
	}
	
	public String preuzmiNalog(String brojNaloga, String radnikId) {

		
		try {
		
		jdbcTemplate.update("update pribaGetriba.dbo.Nalog "+
							"set djelatnik_id = ? "+
							"where broj_naloga = ?;",Integer.parseInt(radnikId),Integer.parseInt(brojNaloga));
		
		return radnikId;
		
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return "Greska u updateu naloga.";
		}
		
	}
	
	
	public String IzmjeniNalog(Nalog nalog) {
		try {
			
			jdbcTemplate.update("update pribaGetriba.dbo.Nalog "+
					"set komentar_djelatnika = ?, "+
					"status = ?, "+
					"datum_zavrsetka = ? "+
					"where broj_naloga = ?;",
					nalog.getKomentarDjelatnika(),nalog.getStatus(),nalog.getStatus().toLowerCase().equals("završeno") ? new Date() : null,
					nalog.getBrojNaloga());
			
			return "Uspjesno updateano!";
			
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
				return "Greska u updateu naloga.";
			}
		
	}

	public Stranka dohvatiStranku(String oib) {
		
		try {
		
			String sql = "select id, ime, prezime, oib, email, adresa, telefon "+
					"from pribaGetriba.dbo.Stranka "+
					"where oib = '" + oib + "'";
			
			List<Stranka> stranke = jdbcTemplate.query(sql, new StrankaRowMapper());
			
			if(stranke.isEmpty()) {
				return new Stranka();
			}else {
				return stranke.get(0);
			}
		}catch(Exception ex) {
			System.out.println("dohvatiStranku - " + ex.getMessage());
			return new Stranka();
		}
		
		
	}
	
	
	public Automobil dohvatiAutomobil(String vin) {
		try {
			
			String sql = "select id,marka,model,tip,vin,registracija,mjenjac,gorivo,kilometri,snaga,godina_proizvodnje godinaProizvodnje "+
					"from pribaGetriba.dbo.Automobil "+
					"where vin = '" + vin + "';";
			
			List<Automobil> auti = jdbcTemplate.query(sql, new AutomobilRowMapper());
			
			if(auti.isEmpty()) {
				return new Automobil();
			}else {
				return auti.get(0);
			}
			
		}catch(Exception ex) {
			System.out.println("dohvatiStranku - " + ex.getMessage());
			return new Automobil();
		}
				
	}
	
	public Racun dohvatiRacun(String username, String password) {
		
			
			String sql = "select  djel.id id,username, password, sifra_uloge sifraUloge, naziv_uloge nazivUloge "+
					"from pribaGetriba.dbo.Racun rac "+
					"inner join pribaGetriba.dbo.Djelatnik djel on rac.id = djel.racun_id "+
					"where djel.username = '" + username + "' and djel.password = '" + password + "' "+
					"union "+
					"select broj_naloga id ,username,password,sifra_uloge sifraUloge,naziv_uloge nazivUloge "+
					"from pribaGetriba.dbo.Nalog na "+
					"inner join pribaGetriba.dbo.Racun rac on na.racun_id = rac.id "+
					"where na.username = '" + username + "' and na.password = '" + password + "';";
					
			List<Racun> racuni = jdbcTemplate.query(sql, new RacunRowMapper());
			
			if(racuni.isEmpty()) {
				return new Racun();
			}else{
				return racuni.get(0);
			}
		
		
		
	}
	
	public String dohvatiBrojNaloga(String vin) {
		
		String sql = "select max(broj_naloga) broj_naloga from pribaGetriba.dbo.Nalog where automobil_vin = ? ";

	    String brojNaloga = (String) jdbcTemplate.queryForObject(
	            sql, new Object[] { vin }, String.class);
	    
	    
	    return brojNaloga;
	}
}
