package hr.diplomski.pribaGetriba.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hr.diplomski.pribaGetriba.model.Racun;

public class RacunRowMapper implements RowMapper<Racun> {

	@Override
	public Racun mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Racun racun = new Racun();
		
		racun.setId(rs.getInt("id"));
		racun.setUsername(rs.getString("username"));
		racun.setPassword(rs.getString("password"));
		racun.setNazivUloge(rs.getString("nazivUloge"));
		racun.setSifraUloge(rs.getInt("sifraUloge"));
		
		return racun;
	}

}
