package hr.diplomski.pribaGetriba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import hr.diplomski.pribaGetriba.model.Nalog;



public class NalogRowMapper implements RowMapper<Nalog>  {

	@Override
	public Nalog mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Nalog nalog = new Nalog();
		
		nalog.setBrojNaloga(rs.getString("brojNaloga"));
		nalog.setOpisKvara(rs.getString("opisKvara"));
		nalog.setStatus(rs.getString("status"));
		nalog.setDatumZaprimanja(rs.getDate("datumZaprimanja"));
		nalog.setMarka(rs.getString("marka"));
		nalog.setModel(rs.getString("model"));
		nalog.setTip(rs.getString("tip"));
		nalog.setVin(rs.getString("vin"));
		nalog.setRegistracija(rs.getString("registracija"));
		nalog.setMjenjac(rs.getString("mjenjac"));
		nalog.setGorivo(rs.getString("gorivo"));
		nalog.setKilometri(rs.getString("kilometri"));
		nalog.setSnaga(rs.getString("snaga"));
		nalog.setGodinaProizvodnje(rs.getString("godinaProizvodnje"));
		nalog.setIme(rs.getString("ime"));
		nalog.setPrezime(rs.getString("prezime"));
		nalog.setEmail(rs.getString("email"));
		nalog.setTelefon(rs.getString("telefon"));
		nalog.setDjelatnikId(rs.getString("djelatnikId"));
		nalog.setDatumZavrsetka(rs.getDate("datumZavrsetka"));
		nalog.setKomentarDjelatnika(rs.getString("komentarDjelatnika"));
		nalog.setImeDjelatnika(rs.getString("imeDjelatnika"));
		nalog.setPrezimeDjelatnika(rs.getString("prezimeDjelatnika"));
		
		
	
		return nalog;
	}

}
