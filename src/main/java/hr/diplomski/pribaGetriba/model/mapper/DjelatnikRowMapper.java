package hr.diplomski.pribaGetriba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.diplomski.pribaGetriba.model.Djelatnik;

public class DjelatnikRowMapper implements RowMapper<Djelatnik> {
	
    @Override
    public Djelatnik mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Djelatnik djelatnik = new Djelatnik();

    	djelatnik.setId(rs.getInt("ID"));
    	djelatnik.setIme(rs.getString("IME"));
    	djelatnik.setPrezime(rs.getString("PREZIME"));
    	djelatnik.setTelefon(rs.getString("TELEFON"));
    	djelatnik.setEmail(rs.getString("EMAIL"));
    	djelatnik.setUsername(rs.getString("USERNAME"));
    	djelatnik.setPassword(rs.getString("PASSWORD"));
    	djelatnik.setVrstaDjelatnika(rs.getString("NAZIV_ULOGE"));
    	

    	return djelatnik;
    }
}


