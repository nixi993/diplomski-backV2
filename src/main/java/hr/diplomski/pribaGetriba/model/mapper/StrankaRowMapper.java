package hr.diplomski.pribaGetriba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.diplomski.pribaGetriba.model.Stranka;


public class StrankaRowMapper implements RowMapper<Stranka> {
	
	@Override
	public Stranka mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Stranka stranka = new Stranka();
		stranka.setId(rs.getInt("id"));
		stranka.setIme(rs.getString("ime"));
		stranka.setPrezime(rs.getString("prezime"));
		stranka.setOib(rs.getString("oib"));
		stranka.setEmail(rs.getString("email"));
		stranka.setTelefon(rs.getString("telefon"));
		stranka.setAdresa(rs.getString("adresa"));
		
		return stranka;
	}

}
