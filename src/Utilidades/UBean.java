package Utilidades;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UBean {
	
	public static ArrayList<Field> obtenerAtributos(Object o){
		List<Field> fields = new ArrayList<Field>();
		for (Field f : o.getClass().getDeclaredFields()) {
			fields.add(f);
		}
		return (ArrayList<Field>) fields;
	}
	
	public static void ejecutarSet(Object o, String att, Object valor){
		
	}
	
	public static void ejecutarGet(Object o, String att){
			
	}

}
