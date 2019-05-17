package br.com.andlucsil.probesserver.report.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tcc_pos","postgres","admin");
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
