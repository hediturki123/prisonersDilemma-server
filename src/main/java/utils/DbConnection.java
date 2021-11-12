package utils;


import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DbConnection {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() throws SQLException {
        System.out.println("dataSource !!!");
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            Properties pr = new Properties();
            pr.setProperty("stringtype", "unspecified");
            config.setJdbcUrl(dbUrl);
            config.setDataSourceProperties(pr);
            config.setMaximumPoolSize(3);
            return new HikariDataSource(config);
        }
    }

}