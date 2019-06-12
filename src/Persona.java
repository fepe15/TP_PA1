import Anotaciones.Columna;
import Anotaciones.Tabla;

@Tabla (nombre="sys_persona")
public class Persona {
	
	private Integer Id;
	@Columna (nombre="Per_nombre")
	private String nombre;
	@Columna (nombre="Per_apellido")
	private String apellido;
	
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

}
