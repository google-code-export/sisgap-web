package pe.com.mmh.sisgap.comun;

import java.io.Serializable;
import java.util.List;

public class Paginador implements Serializable {
	
	private List lista;
	private int total;
	private int paginacion;
	
	public List getLista() {
		return lista;
	}
	public void setLista(List lista) {
		this.lista = lista;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(int paginacion) {
		this.paginacion = paginacion;
	}

}
