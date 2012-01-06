package pe.com.mmh.sisgap.system.domain;

public class Parametro {
	
	private String id;
	private String nombre;
	private String descripcion;
	private String flagEstado;
	
	public Parametro(){}
	
	public Parametro(String id, String nombre){
		this.id=id;
		this.nombre= nombre;		
	}
	public String getFlagEstado() {
		return flagEstado;
	}
	public void setFlagEstado(String flagEstado) {
		this.flagEstado = flagEstado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
