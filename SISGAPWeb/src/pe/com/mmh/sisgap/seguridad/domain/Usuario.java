package pe.com.mmh.sisgap.seguridad.domain;

import java.util.HashMap;
import java.util.List;

public class Usuario {
	
	private String codigo;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	private String userName;
	private String clave;
	private String email;
	private String flagEstado;
	
	private List lstRol;
	
	private Usuario usuarioAuditoria;
	
	private HashMap opcionesSeguridad = new HashMap();

	public HashMap getOpcionesSeguridad() {
		return opcionesSeguridad;
	}

	public void setOpcionesSeguridad(HashMap opcionesSeguridad) {
		this.opcionesSeguridad = opcionesSeguridad;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public Usuario getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(Usuario usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public String getFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(String flagEstado) {
		this.flagEstado = flagEstado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List getLstRol() {
		return lstRol;
	}

	public void setLstRol(List lstRol) {
		this.lstRol = lstRol;
	}



}
