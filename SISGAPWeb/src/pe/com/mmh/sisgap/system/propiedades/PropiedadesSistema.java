package pe.com.mmh.sisgap.system.propiedades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import pe.com.mmh.sisgap.comun.constantes.Constantes;
import pe.com.mmh.sisgap.system.domain.Parametro;



public class PropiedadesSistema {
	
	private static Logger flog = Logger.getLogger(PropiedadesSistema.class);
		
	//private String idioma;
	private int userSessionTimeOut;
	private Integer cantidadRegistros;	
	private int codigoSistema;
	private String jdbcDatasource;
	private static PropiedadesSistema propiedadesSistema=null;	
	public static void init(String path) throws IOException {	
		flog.info("Ruta Propiedades Sistema   = [" + path +"]");	
		try{
			//FileInputStream fi = new FileInputStream(path);
			XmlBeanFactory factory=new XmlBeanFactory(new FileSystemResource(path));
			propiedadesSistema = (PropiedadesSistema) factory.getBean("propiedadesSistema");
			//fi.close();
			factory = null;
		}catch(Exception e ){
			flog.error("ERROR : Error inciando las propiedades del sistema.");
			flog.error("Sistema Finalizado.");
			flog.error("ERROR : " + e);
			System.exit(0);
		}
	}	
	public static PropiedadesSistema getInstance(){		
		return propiedadesSistema;
		
	}
		
	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}
	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	public int getUserSessionTimeOut() {
		return userSessionTimeOut;
	}
	public void setUserSessionTimeOut(int userSessionTimeOut) {
		this.userSessionTimeOut = userSessionTimeOut;
	}
	public int getCodigoSistema() {
		return codigoSistema;
	}
	public void setCodigoSistema(int codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	public String getJdbcDatasource() {
		return jdbcDatasource;
	}
	public void setJdbcDatasource(String jdbcDatasource) {
		this.jdbcDatasource = jdbcDatasource;
	}	
	
	private List<Parametro> lstAccionesCliente;
	private List<Parametro> lstAccionesModelo;
	private List<Parametro>	lstTipoTrama;
	private List<Parametro>	lstEstadosModeloGenerados;
	
	public List<Parametro> getLstEstadosModeloGenerados() {
		
		if(lstEstadosModeloGenerados==null){
			lstEstadosModeloGenerados=new ArrayList<Parametro>();
			lstEstadosModeloGenerados.add(new Parametro(Constantes.ESTADO_MODELO_PENDIENTE,"Pendiente"));
			lstEstadosModeloGenerados.add(new Parametro(Constantes.ESTADO_MODELO_APROBADO,"Aprobado"));
			lstEstadosModeloGenerados.add(new Parametro(Constantes.ESTADO_MODELO_TRANSMITIDO,"Transmitido"));
			}
		return lstEstadosModeloGenerados;
	}
	public List<Parametro> getLstAccionesCliente() {
		
		if(lstAccionesCliente==null){
			lstAccionesCliente=new ArrayList<Parametro>();
			lstAccionesCliente.add(new Parametro(Constantes.CLIENTE_ID_TODOS,Constantes.CLIENTE_TODOS));
			lstAccionesCliente.add(new Parametro(Constantes.CLIENTE_ID_NUEVO,Constantes.CLIENTE_NUEVO));
			lstAccionesCliente.add(new Parametro(Constantes.CLIENTE_ID_ACTUALIZADO,Constantes.CLIENTE_ACTUALIZADO));
			lstAccionesCliente.add(new Parametro(Constantes.CLIENTE_ID_NINGUNA,Constantes.CLIENTE_NINGUNA));
		}
		
		return lstAccionesCliente;
	}
	public void setLstAccionesCliente(List<Parametro> lstAcciones) {
		this.lstAccionesCliente = lstAcciones;
	}
	
	public List<Parametro> getLstAccionesModelo() {
		
		if(lstAccionesModelo==null){
			lstAccionesModelo=new ArrayList<Parametro>();
			lstAccionesModelo.add(new Parametro("0","Todos"));
			lstAccionesModelo.add(new Parametro(Constantes.ESTADO_MODELO_INTEGRANTE_APROBADO,"Aprobado"));
			lstAccionesModelo.add(new Parametro(Constantes.ESTADO_MODELO_INTEGRANTE_OBSERVADO,"Observado"));
			lstAccionesModelo.add(new Parametro(Constantes.ESTADO_MODELO_INTEGRANTE_PENDIENTE,"Pendiente"));
			
		}
		return lstAccionesModelo;
	}
	public void setLstAccionesModelo(List<Parametro> lstAccionesModelo) {
		this.lstAccionesModelo = lstAccionesModelo;
	}	
	
	
	public List<Parametro> getLstTipoTrama() {
		
		if(lstTipoTrama==null){
		lstTipoTrama=new ArrayList<Parametro>();
		lstTipoTrama.add(new Parametro(Constantes.TRAMA_TIPO_EJECUTOR,"Ejecutor"));
		lstTipoTrama.add(new Parametro(Constantes.TRAMA_TIPO_REPORTE_CREDITICIO,"Reporte Crediticio"));
		}
		
		return lstTipoTrama;
	}
	public void setLstTipoTrama(List<Parametro> lstTipoTrama) {
		this.lstTipoTrama = lstTipoTrama;
	}
}
