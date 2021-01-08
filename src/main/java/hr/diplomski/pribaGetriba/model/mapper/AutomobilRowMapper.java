package hr.diplomski.pribaGetriba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.diplomski.pribaGetriba.model.Automobil;

public class AutomobilRowMapper implements RowMapper<Automobil> {

	@Override
	public Automobil mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Automobil auto = new Automobil();
		
		auto.setId(rs.getInt("id"));
		auto.setMarka(rs.getString("marka"));
		auto.setModel(rs.getString("model"));
		auto.setTip(rs.getString("tip"));
		auto.setVin(rs.getString("vin"));
		auto.setRegistracija(rs.getString("registracija"));
		auto.setMjenjac(rs.getString("mjenjac"));
		auto.setGorivo(rs.getString("gorivo"));
		auto.setKilometri(rs.getString("kilometri"));
		auto.setSnaga(rs.getString("snaga"));
		auto.setGodinaProizvodnje(rs.getString("godinaProizvodnje"));
		
		return auto;
	}

	
	
}
