package hr.diplomski.pribaGetriba.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigDB {

	@Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:sqlserver://DESKTOP-PU5UVJ5;Initial Catalog=pribaGetriba;Integrated Security=True\"");
        dataSource.setUsername("pribaGetriba");
        dataSource.setPassword("Abc123");

        return dataSource;
    }
}
