package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionFactory {

	private DataSource datasource;
	
	public ConnectionFactory() {
		var pooledDataSource = new ComboPooledDataSource();
	
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/Hotel_Alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("12345");
		pooledDataSource.setMaxPoolSize(10);
		
		datasource = pooledDataSource;
		
	}
	public Connection recuperarConexion() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
              
	}
	
}
