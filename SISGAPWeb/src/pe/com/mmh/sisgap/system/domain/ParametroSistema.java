package pe.com.mmh.sisgap.system.domain;

import java.util.List;

import pe.com.mmh.sisgap.seguridad.domain.Usuario;

//import pe.bat.cpr.common.DaoFactory;


public class ParametroSistema {
  private String codigo;
  private String nombre;
  private String descripcion;
  private String valor;
  private Integer orden;
  private String tipo;
  private Usuario usuario=new Usuario();
  
  
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getValor() {
	return valor;
}
public void setValor(String valor) {
	this.valor = valor;
}

/*
public static ParametroSistema getParametroSistema(String key)throws Exception{
	return DaoFactory.getSystemDAO().getParametroSistema(key);
	}

public static List obtenerListaParametroSistema() throws Exception{
	return DaoFactory.getSystemDAO().obtenerListaParametroSistema();
	}

public static void actualizarParametroSistema (List lstParametroSistema) throws Exception{
	DaoFactory.getSystemDAO().actualizarParametroSistema(lstParametroSistema);
	}
*/

public Integer getOrden() {
	return orden;
}
public void setOrden(Integer orden) {
	this.orden = orden;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}

}
