package conex;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexion {
	public String driver = "com.mysql.cj.jdbc.Driver";
	public String cadena = "jdbc:mysql://localhost:3306/bdVeterianrio";
	public String usuario = "root";
	public String clave = "yorsh2005";
	public Connection conn;

	public Connection obtenerConexion() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(cadena, usuario, clave);
			System.out.println("Conexion establecida correctamente");
		} catch (ClassNotFoundException e1) {
			System.out.println("Error en el Driver" + e1);
		} catch (SQLException e2) {
			System.out.println("Error en la conexion" + e2);
		}
		return conn;
	}

}
