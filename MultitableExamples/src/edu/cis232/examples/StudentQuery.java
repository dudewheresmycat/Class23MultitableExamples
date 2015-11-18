package edu.cis232.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentQuery {

	public static void main(String[] args) throws SQLException{
		final String DB_URL = "jdbc:derby:Students;create=true";
		Connection conn = DriverManager.getConnection(DB_URL);
		
		Statement stmt = conn.createStatement();
		
		ResultSet results = stmt.executeQuery("select s.name, a.points from Student s, Assignment a where a.studentId = s.id ");
		
		while(results.next()){
			System.out.printf("%s: %d%n", results.getString("name"),
					results.getInt("points"));
		}
		conn.close();
	}

}
