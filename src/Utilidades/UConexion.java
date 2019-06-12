package Utilidades;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UConexion {
	
	private static Connection myConexion;
	
	
	private UConexion(){
		
		this.myConexion = null;
		
		try {
			//creo objeto de tipo properties y recupero los datos del archivo frameword.properties
			Properties p = new Properties();
			p.load(new FileReader("framework.properties"));
			
			//se cargan los driver de mysql en memoria
			Class.forName(p.getProperty("driver"));
			
			// se crea un string con la ruta del motor de mysql
			String rutaBD = p.getProperty("ruta_bd");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			
			//me conecto a la base con el string + usuario + contraseña
			this.myConexion = DriverManager.getConnection(rutaBD,user,password);
			System.out.println("Se conecto correctamente");
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (myConexion == null)
		{
			new UConexion();
		}
		return myConexion;
	}
	
}
