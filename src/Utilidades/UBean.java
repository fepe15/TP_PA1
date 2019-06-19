package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
		
		String strSet = "set"+ att.substring(0,1).toUpperCase() + att.substring(1).toLowerCase();
		Method metSet;
		try {
			metSet = o.getClass().getDeclaredMethod(strSet);
			metSet.invoke(o, valor);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object ejecutarGet(Object o, String att){
		String strGet = "get"+ att.substring(0,1).toUpperCase() + att.substring(1).toLowerCase();
		Method metGet;
		Object objGet = new Object();
		try {
			metGet = o.getClass().getDeclaredMethod(strGet);
			metGet.invoke(o, objGet);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objGet;
	}

}
