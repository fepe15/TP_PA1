import Anotaciones.Columna;
import Anotaciones.Tabla;
import Anotaciones.Id;;


@Tabla (nombre="sys_persona")
public class Persona {
	
	@Id
	private Integer Id;
	
	@Columna(nombre="nombre")
	private String nombre;
	
	@Columna (nombre="apellido")
	private String apellido;
	
	@Columna(nombre="dni")
	private int dni;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}

}
