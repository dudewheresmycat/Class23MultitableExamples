package edu.cis232.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.Environment;

public class CreateStudentDB {
	public static void main(String[] args) throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:derby:Students;create=true");
		System.out.println("Connected to database!");

		createStudentTable(conn);
		createAssignmentTable(conn);
		

		conn.close();
	}

	private static void createAssignmentTable(Connection conn)
			throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			String dropTable = "drop table Assignment";
			stmt.execute(dropTable);
			System.out.println("Assignment table dropped.");
		} catch (SQLException e) {
			System.out.println("Assignment table does not exist.");
		}

		String createTable = "create table Assignment("
				+ "studentId int not null references Student(id), name varchar(100), points int )";

		stmt.execute(createTable);
		
		stmt.executeUpdate("insert into Assignment values(1, 'bonus', 10)");

		System.out.println("Assignment table created.");
		
	}

	private static void createStudentTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();

		try {
			String dropTable = "drop table Student";
			stmt.execute(dropTable);
			System.out.println("Students table dropped.");
		} catch (SQLException e) {
			System.out.println("Students table does not exist.");
		}

		String createTable = "create table Student("
				+ "id int not null primary key, " + "name varchar(100))";
		
		stmt.execute(createTable);
		
		stmt.executeUpdate("insert into Student values (1, 'Kyle')");
		stmt.executeUpdate("insert into Student values (2, 'Nick')");
		stmt.executeUpdate("insert into Student values (3, 'Tyler')");
		stmt.executeUpdate("insert into Student values (4, 'Dylan')");
		stmt.executeUpdate("insert into Student values (5, 'Alex')");
		stmt.executeUpdate("insert into Student values (6, 'Jake')");
		stmt.executeUpdate("insert into Student values (7, 'Gabe')");

		System.out.println("Student table created.");
	}
}
