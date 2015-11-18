package edu.cis232.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiTableSelect {

	public static void main(String[] args) throws SQLException{
		final String DB_URL = "jdbc:derby:CoffeeDB;create=true";
		Connection conn = DriverManager.getConnection(DB_URL);
		
		Statement stmt = conn.createStatement();
		
		ResultSet results = stmt.executeQuery("select c.Name, u.Cost from UnpaidOrder u, Customer c where c.CustomerNumber = u.CustomerNumber");
		
		while(results.next()){
			System.out.printf("Customer %s: $%,.2f%n", results.getString("Name"),
					results.getDouble("Cost"));
		}
		conn.close();
	}

}
