package Logico;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
	
	Connection link = null;
	
	public  Connection getConexion()
	{
		String conexionURL = "jdbc:sqlserver://192.168.77.24;"
                + "database=fabrica_quesos_grupo2;"
                + "user=lramirez;"
                + "password=Enp0s3@03;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;"
                + "Trusted_Connection=True"
                + "integratedSecurity=true";
		
			try {
				
				link = DriverManager.getConnection(conexionURL);
				
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex +"No fue posible conectar");

			}
			return link;
	}

}
