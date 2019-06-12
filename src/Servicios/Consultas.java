package Servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Anotaciones.Columna;
import Anotaciones.Tabla;
import Utilidades.UBean;
import Utilidades.UConexion;

public class Consultas {
	
	private static List<Field> atributos;
		
	public static void guardar(Object o){
		
	atributos = new ArrayList<>();	
	Connection connection = UConexion.getConnection();
	Class c = o.getClass();
	
	String query="Insert into "; 
	
	//Retorno las columnas del array
	atributos = UBean.obtenerAtributos(o);
	
	//concateno el nombre de la tabla
	query.concat(c.getAnnotation(Tabla.class).toString());
	query.concat(" (");
	
	//concateno cada una de las columnas
	for (Field field : atributos) {
		query.concat(field.getAnnotation(Columna.class).toString());
		query.concat(",");
	}
	query.concat(") (");
	
	for (Field field : atributos) {
		try {
			if (String.class.equals(field.getClass())){
			query.concat("'");
			query.concat(c.getDeclaredField(field.getName()).toString());
			query.concat("'");
			}
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.concat(",");
	}
	
	
	}
}
