package hr.diplomski.pribaGetriba.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DjelatnikRowMapper implements RowMapper<Djelatnik> {
	
    @Override
    public Djelatnik mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Djelatnik djelatnik = new Djelatnik();

    	djelatnik.setId(rs.getInt("ID"));
    	djelatnik.setIme(rs.getString("IME"));
    	djelatnik.setPrezime(rs.getString("PREZIME"));

    	return djelatnik;
    }
}