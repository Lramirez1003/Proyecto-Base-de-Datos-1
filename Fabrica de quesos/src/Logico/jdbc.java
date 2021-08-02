package Logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class jdbc {
	    // Connect to your database.
	    // Replace server name, username, and password with your credentials
	    public static void main(String[] args) {
			String Url ="jdbc:sqlserver://192.168.77.24;"
	                + "database=vehiculos;"
	                + "user=lramirez;"
	                + "password=Enp0s3@03;"
	                + "encrypt=true;"
	                + "trustServerCertificate=true;"
	                + "loginTimeout=30;"
	                + "Trusted_Connection=True"
	                + "integratedSecurity=true";
		/*
	        String insertSql = "INSERT INTO Equipo (Nombre,fecha_fundacion) VALUES "
	                + "(?,?);";

	        ResultSet resultSet = null;

	        try (Connection connection = DriverManager.getConnection(Url);
	                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

	        	prepsInsertProduct.setString(1, "A");
	        	prepsInsertProduct.setString(2, "2018-01-01");
	            prepsInsertProduct.execute();
	            // Retrieve the generated key from the insert.
	            resultSet = prepsInsertProduct.getGeneratedKeys();

	            // Print the ID of the inserted row.
	            while (resultSet.next()) {
	                System.out.println("Generated: " + resultSet.getString(1));
	            }
	        }
	        // Handle any errors that may have occurred.
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}*/

	        ResultSet resultSet = null;
	        try (Connection connection = DriverManager.getConnection(Url);
	                Statement statement = connection.createStatement();) {

	            // Create and execute a SELECT SQL statement.
	            String selectSql = "SELECT * from Modelo";
	            resultSet = statement.executeQuery(selectSql);

	            // Print results from select statement
	            while (resultSet.next()) {
	                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
	            }

	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}