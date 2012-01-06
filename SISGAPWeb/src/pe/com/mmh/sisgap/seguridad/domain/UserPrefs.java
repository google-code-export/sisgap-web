
package pe.com.mmh.sisgap.seguridad.domain;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;


import org.apache.commons.lang.ArrayUtils;
/**
 * @author jrincon
 *
 */
public class UserPrefs implements Serializable{
	private String[] filtros; //filtros de consulta general
	private String[] campos; //campos de resultado de consulta general
	private String[] exportaciones; //campos de resultado de consulta general
	private Map verificadores;//cod_veri: nombre, x, y
	
	public UserPrefs(){
		verificadores = new HashMap();
	}
	
	public static Verificador parseVerificador(String data){		
		String[] atts = data.split("\\|");
		
		Verificador v = new Verificador(atts[0],atts[1]);
		v.setX(Integer.parseInt(atts[2]));
		v.setY(Integer.parseInt(atts[3]));
		
		return v;
	}
	
	public void addVerificador(Verificador v){
		if(!verificadores.containsKey(v.getId()))
			verificadores.put(v.getId(), v);		
	}	
	
	public void cambiarPos(String id, int x , int y){
		Verificador v = (Verificador)verificadores.get(id);
		if(v==null) return ;
		v.setX(x);
		v.setY(y);		
	}
	public void actualizarPosiciones(Map map){
		Iterator<Map.Entry> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = it.next();
			Verificador v = (Verificador)this.verificadores.get(me.getKey());
			v.setX(((Verificador)me.getValue()).getX());
			v.setY(((Verificador)me.getValue()).getY());			
		}
	}	
	public Verificador getVerificadores(String key){
		if(verificadores==null) verificadores = new HashMap();
		if(!verificadores.containsKey(key))
			verificadores.put(key,new Verificador());	
		return (Verificador)verificadores.get(key);		
	}
	public void setVerificadores(String key, Object value){
		verificadores.put(key, value);
	}
	/**
	 * @return Returns the campos.
	 */
	public String[] getCampos() {
		return campos;
	}
	/**
	 * @param campos The campos to set.
	 */
	public void setCampos(String[] campos) {
		this.campos = campos;
	}
	/**
	 * @return Returns the filtros.
	 */
	public String[] getFiltros() {
		return filtros;
	}
	/**
	 * @param filtros The filtros to set.
	 */
	public void setFiltros(String[] filtros) {
		this.filtros = filtros;
	}
	/**
	 * @return Returns the verificadores.
	 */
	public Map getVerificadores() {
		return verificadores;
	}
	/**
	 * @param verificadores The verificadores to set.
	 */
	public void setVerificadores(Map verMap) {
		//remover los que no estan en el map
		if(verificadores!=null){
			String[] keys = (String[])this.verificadores.keySet()
				.toArray( new String[this.verificadores.size()]);
			
			for(String s: keys){
				if(!verMap.containsKey(s))
					this.verificadores.remove(s);
			}
		}else{
			verificadores = new HashMap();
		}
		//agregar verificadores nuevos		
		Iterator<Map.Entry> it = verMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = it.next();
			if(!this.verificadores.containsKey(me.getKey())){
				this.verificadores.put(me.getKey(), new Verificador((String)me.getKey(), (String)me.getValue()));
			}				  
		}				
	}
    
    public boolean tieneFiltro(String filtro){
        /*System.out.println("filtro ="+filtro);
        System.out.println(ArrayUtils.toString(this.filtros));*/
        if(this.filtros==null || this.filtros.length<=0 ) return true;
         return ArrayUtils.contains(this.filtros,filtro);
        
    }
    
    public boolean tieneCampo(String campo){
        if(this.campos==null || this.campos.length<=0 ) return true;
        return ArrayUtils.contains(this.campos,campo);
        
    }  
    
    public boolean tieneExportacion(String exportacion){
        if(this.exportaciones==null || this.exportaciones.length<=0 ) return true;
        return ArrayUtils.contains(this.exportaciones,exportacion);
        
    }     
	/**
	 * @return Returns the exportaciones.
	 */
	public String[] getExportaciones() {
		return exportaciones;
	}
	/**
	 * @param exportaciones The exportaciones to set.
	 */
	public void setExportaciones(String[] exportaciones) {
		this.exportaciones = exportaciones;
	}
}
