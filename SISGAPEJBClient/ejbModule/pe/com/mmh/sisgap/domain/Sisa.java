package pe.com.mmh.sisgap.domain;

import java.util.Date;

public class Sisa {
	
	long codigo;
	String nombre;
	Date perido;
	String puesto;
	Integer cantidad;
	Integer totalpagos;
	Integer totaldias;
	Integer estado;
	String tranCodigo;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getPerido() {
		return perido;
	}
	public void setPerido(Date perido) {
		this.perido = perido;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Integer getTotalpagos() {
		return totalpagos;
	}
	public void setTotalpagos(Integer totalpagos) {
		this.totalpagos = totalpagos;
	}
	public Integer getTotaldias() {
		return totaldias;
	}
	public void setTotaldias(Integer totaldias) {
		this.totaldias = totaldias;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getTranCodigo() {
		return tranCodigo;
	}
	public void setTranCodigo(String tranCodigo) {
		this.tranCodigo = tranCodigo;
	}

}
