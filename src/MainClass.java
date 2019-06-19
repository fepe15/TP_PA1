import Servicios.Consultas;
import Utilidades.UBean;

public class MainClass {

	public static void main(String[] args) {
		
		Persona p = new Persona();
		
		p.setId(5);
		p.setNombre("Carlos");
		p.setApellido("Peralta");
		p.setDni(122);

		
		
		
		
		Consultas.eliminar(p);
		
	//	List<Field> listaAtri = new ArrayList<Field>();
	//	listaAtri = UBean.obtenerAtributos(p);
		
	//	for (Field field : listaAtri) {
	//		System.out.println(field.getName());
	//	}

		
	/*	Persona p = new Persona();
		p.setNombre("Fede");
		
		try {
			Field f = p.getClass().getDeclaredField("nombre");
			f.setAccessible(true);
			System.out.println(f.get(p));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  */
	}

}
