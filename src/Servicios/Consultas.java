package Servicios;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;
import Utilidades.UBean;
import Utilidades.UConexion;

public class Consultas {
	
		private static List<Field> atributos;
			
			
			public static void guardar(Object o){
				
			atributos = new ArrayList<>();	
			Connection connection = UConexion.getConnection();
			
			Class c = o.getClass();
			
			
			StringBuilder query= new StringBuilder(); 
		    query.append("Insert into "); 
			
			//Retorno las columnas del array
			atributos = UBean.obtenerAtributos(o);
			
			//concateno el nombre de la tabla
			query.append(((Tabla)c.getAnnotation(Tabla.class)).nombre());
			query.append(" (");
			
			//concateno cada una de las columnas
			for (Field field : atributos) {
				if (field.getAnnotation(Columna.class) != null) {
				query.append(((Columna)field.getAnnotation(Columna.class)).nombre());
				query.append(",");
				}
			}
			query.delete(query.length()-1, query.length());
			query.append(") values (");
			
			try {
				for (Field field : atributos) {
					if (field.getAnnotation(Columna.class) != null) {
						if (UBean.ejecutarGet(o, field.getName()).getClass().equals(String.class)){
							query.append("'");
							query.append(UBean.ejecutarGet(o, field.getName()));
							query.append("'");
							query.append(",");
						}
						else {
							query.append(UBean.ejecutarGet(o, field.getName()));
							query.append(",");
						}
					}
		
				} 
				query.delete(query.length()-1, query.length());
				query.append(")");
				System.out.println(query.toString());
				PreparedStatement st = connection.prepareStatement(query.toString());
				st.execute();
				connection.close();
			} 	
			catch (SecurityException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		
		public static void modificar(Object o){
			
			
			atributos = new ArrayList<>();	
			Connection connection = UConexion.getConnection();
			Integer id=0;
			
			Class c = o.getClass();
			
			//Retorno las columnas del array
			atributos = UBean.obtenerAtributos(o);
			
			
			StringBuilder query= new StringBuilder(); 
		    query.append("Update "); 
			
			//concateno el nombre de la tabla
			query.append(((Tabla)c.getAnnotation(Tabla.class)).nombre() + " set ");			
			
			try {
				for (Field field : atributos) {
					if (field.getAnnotation(Columna.class) != null) {
						if (UBean.ejecutarGet(o, field.getName()).getClass().equals(String.class)){
							query.append(((Columna)field.getAnnotation(Columna.class)).nombre());
							query.append("=");
							query.append("'");
							query.append(UBean.ejecutarGet(o, field.getName()));
							query.append("'");
							query.append(",");
						}
						else {
							query.append(((Columna)field.getAnnotation(Columna.class)).nombre());
							query.append("=");
							query.append(UBean.ejecutarGet(o, field.getName()));
							query.append(",");
						}
					}
					if(field.getAnnotation(Id.class) != null) {
						id = (Integer) UBean.ejecutarGet(o, field.getName());
					}
				} 
				query.delete(query.length()-1, query.length());
				query.append(" where id=" +  id );
				System.out.println(query.toString());
				PreparedStatement st = connection.prepareStatement(query.toString());
				st.execute();
				connection.close();
			} 	
			catch (SecurityException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		
		public static void eliminar(Object o){
			
			ArrayList<Columna> columnas = new ArrayList<Columna>();
			Field IdTraido = null;
			Connection connection = UConexion.getConnection();
			Integer id=0;
			Class c = o.getClass();
			
			//Retorno las columnas del array
			atributos = UBean.obtenerAtributos(o);
			
			StringBuilder query= new StringBuilder(); 
		    query.append("Delete from "); 
			
			//concateno el nombre de la tabla
			query.append(((Tabla)c.getAnnotation(Tabla.class)).nombre());			
			
			try {
				for (Field field : atributos) {
					if(field.getAnnotation(Id.class) != null) {
						id = (Integer) UBean.ejecutarGet(o, field.getName());
					}
				} 
				query.append(" where id=" +  id );
				System.out.println(query.toString());
				PreparedStatement st = connection.prepareStatement(query.toString());
				st.execute();
				connection.close();
			} 	
			catch (SecurityException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		public static Object obtenerPorId(Class clase, Object id){
			
			Connection connection = UConexion.getConnection();
			atributos = new ArrayList<>();	
			Field IdTraida = null;
			Object objRetorno = new Object();
			StringBuilder query= new StringBuilder(); 
			
		    query.append("Select * from "); 
			
		    //Creo Objeto Tabla
		    Tabla tabla = (Tabla)clase.getAnnotation(Tabla.class);
			//concateno el nombre de la tabla
			query.append(tabla.nombre());
			
			Constructor[] constructors = clase.getConstructors();
			
			for (Constructor constructor : constructors) {
				if (constructor.getParameterCount() == 0 ) {
					try {
						objRetorno = constructor.newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					
			atributos = UBean.obtenerAtributos(objRetorno);
			
			for(Field f : atributos)
			{
				if (f.getAnnotation(Id.class) != null)
				{
					IdTraida = f;
				}
			}
			
			query.append(" where " + IdTraida.getName() + " = " + id);
			
			System.out.println(query.toString());
			try {
				PreparedStatement st = connection.prepareStatement(query.toString());
				ResultSet queryRes = st.executeQuery();
				
				while(queryRes.next())
				{
					for(Field f : atributos)
					{
						if(f.getAnnotation(Columna.class) != null)
						{
							UBean.ejecutarSet(objRetorno,f.getName(), queryRes.getObject(f.getAnnotation(Columna.class).nombre()));
						}
					}
				}
				
				UBean.ejecutarSet(objRetorno,IdTraida.getName(),id);
				System.out.println(objRetorno);
				
				return objRetorno;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
		}
}
