package pe.com.mmh.sisgap.system.exception;

public class ServiceException extends Exception {
	
	private String mensaje;
	private String codigoMensaje;
	private String codigoRetorno;
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	private String nombrePrograma;
	private String numeroOperacion;
	//Puede ser del tipo V:validacion, S:servidor, A:Autenticacion
	private String tipoError;
	public static final String VALIDACION="V";
	public static final String SERVIDOR="S";
	public static final String AUTENTICACION="A";
	

	public ServiceException(String tipoError,String codigoRetorno, String codigoMensaje,String mensaje,String nombrePrograma) {
		super(codigoRetorno+" "+codigoMensaje+" "+mensaje);
		this.codigoMensaje=codigoMensaje;
		this.codigoRetorno=codigoRetorno;
		this.mensaje=mensaje;
		this.tipoError=tipoError;
		this.nombrePrograma=nombrePrograma;
		// TODO Auto-generated constructor stub
	}
	
	public ServiceException(String codigoRetorno, String codigoMensaje,String mensaje) {
		super(codigoRetorno+" "+codigoMensaje+" "+mensaje);
		this.codigoMensaje=codigoMensaje;
		this.codigoRetorno=codigoRetorno;
		this.mensaje=mensaje;
		this.tipoError=VALIDACION;
		// TODO Auto-generated constructor stub
	}
	
	public ServiceException(String tipoError,String codigoRetorno, String codigoMensaje,String mensaje,String nombrePrograma,String numeroOperacion) {
		super(codigoRetorno+" "+codigoMensaje+" "+mensaje);
		this.codigoMensaje=codigoMensaje;
		this.codigoRetorno=codigoRetorno;
		this.mensaje=mensaje;
		this.tipoError=tipoError;
		this.nombrePrograma=nombrePrograma;
		this.numeroOperacion=numeroOperacion;
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String tipoError,String codigoRetorno, String codigoMensaje,String mensaje) {
		super(codigoRetorno+" "+codigoMensaje+" "+mensaje);
		this.codigoMensaje=codigoMensaje;
		this.codigoRetorno=codigoRetorno;
		this.mensaje=mensaje;
		this.tipoError=tipoError;
		// TODO Auto-generated constructor stub
	}
	public ServiceException() {
		
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}


	public String getTipoError() {
		return tipoError;
	}


	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

}
