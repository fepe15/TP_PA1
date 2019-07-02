import Anotaciones.Columna;
import Anotaciones.Tabla;
import Anotaciones.Id;;


@Tabla (nombre="sys_persona")
public class Persona {
	
	@Id(nombre="Id")
	private Integer Id;
	
	@Columna(nombre="nombre")
	private String nombre;
	
	@Columna (nombre="apellido")
	private String apellido;
	
	@Columna(nombre="dni")
	private Integer dni;
	
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
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nombre: " + this.nombre + " Apellido: " + this.apellido + " DNI: " + this.dni;
	}
}
