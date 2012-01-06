/*
 * Created on 23/05/2005
 *
 */
package pe.com.mmh.sisgap.seguridad.domain;

import java.io.Serializable;

/**
 * @author jrincon
 *
 */
public class Verificador implements Serializable, Comparable{
    private static final long serialVersionUID = 10L;
    
	public Verificador(){
		
	}
	public Verificador(String id, String nombre){
		this.id= id;
		this.nombre=nombre;
	}
	private String id;
	private String nombre;
	private int x; //posicion x
	private int y; //posicion y
	
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return Returns the x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x The x to set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return Returns the y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y The y to set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		return id+"|"+nombre+"|"+x+"|"+y;
	}	
    //----------Comparable Implementation
    public int compareTo(Object arg0){
        if(arg0==null) return 1;
        return (this.nombre.toUpperCase().compareTo(((Verificador)arg0).getNombre().toUpperCase()));
    }
}	