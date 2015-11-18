package edu.cis232.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiTableSelect2 {

	public static void main(String[] args) throws SQLException{
		final String DB_URL = "jdbc:derby:CoffeeDB;create=true";
		Connection conn = DriverManager.getConnection(DB_URL);
		
		Statement stmt = conn.createStatement();
		
		ResultSet results = stmt.executeQuery("select p.ProdNum, p.Description, c.Name, u.Cost "
			  + "from UnpaidOrder u, Customer c, Coffee p "
			  + "where c.CustomerNumber = u.CustomerNumber "
			  + "and p.ProdNum = u.ProdNum");
		
		while(results.next()){
			System.out.printf("Customer %s: $%,.2f  Product:%s(%s)%n", results.getString("Name"),
					results.getDouble("Cost"), results.getString("Description").trim(), 
					results.getString("ProdNum").trim());
		}
		
		conn.close();
	}

}
