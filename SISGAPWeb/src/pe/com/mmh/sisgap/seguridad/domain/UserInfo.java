package pe.com.mmh.sisgap.seguridad.domain;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author jrincon
 *
 */
public class UserInfo {
	public static final String OPERACIONES = "O";
	public static final String CLIENTE = "C";
	
	private String login;
	private String nombre;	
	private String tipo; // OPERACIONES|CLIENTE
	//datos personal
	private int id;
	private String codPersonal;
	private Integer idSede;
	//datos cliente
	private String codEntidad;
	private String codSubentidad;
	private String codUsuario;
	private String entidad;
	private String subEntidad;
    	
	private int idConfigCliente;
	private int nivel;
	private Integer amplitudMaxima;
	private UserPrefs preferencias;
    
    private boolean debeCambiarPwd;
    private boolean sugerirCambiarPwd;
    private int expiracionPwd;
    
    
    private String ip;    
    private String password;
    
	private Integer[] permisos;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean tienePermiso(Integer permiso){        
        return true;/*(permisos!=null && Arrays.binarySearch(permisos, permiso)>=0);*/
    }
    
	/**
	 * @return Returns the codEntidad.
	 */
	public String getCodEntidad() {
		return codEntidad;
	}
	/**
	 * @param codEntidad The codEntidad to set.
	 */
	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}
	/**
	 * @return Returns the codPersonal.
	 */
	public String getCodPersonal() {
		return codPersonal;
	}
	/**
	 * @param codPersonal The codPersonal to set.
	 */
	public void setCodPersonal(String codPersonal) {
		this.codPersonal = codPersonal;
	}
	/**
	 * @return Returns the codSubentidad.
	 */
	public String getCodSubentidad() {
		return codSubentidad;
	}
	/**
	 * @param codSubentidad The codSubentidad to set.
	 */
	public void setCodSubentidad(String codSubentidad) {
		this.codSubentidad = codSubentidad;
	}
	/**
	 * @return Returns the codUsuario.
	 */
	public String getCodUsuario() {
		return codUsuario;
	}
	/**
	 * @param codUsuario The codUsuario to set.
	 */
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	/**
	 * @return Returns the entidad.
	 */
	public String getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad The entidad to set.
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return Returns the id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return Returns the idConfigCliente.
	 */
	public int getIdConfigCliente() {
		return idConfigCliente;
	}
	/**
	 * @param idConfigCliente The idConfigCliente to set.
	 */
	public void setIdConfigCliente(int idConfigCliente) {
		this.idConfigCliente = idConfigCliente;
	}
	/**
	 * @return Returns the login.
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login The login to set.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return Returns the nivel.
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * @param nivel The nivel to set.
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
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
	 * @return Returns the preferencias.
	 */
	public UserPrefs getPreferencias() {
		return preferencias;
	}
	/**
	 * @param preferencias The preferencias to set.
	 */
	public void setPreferencias(UserPrefs preferencias) {
		this.preferencias = preferencias;
	}
	/**
	 * @return Returns the subEntidad.
	 */
	public String getSubEntidad() {
		return subEntidad;
	}
	/**
	 * @param subEntidad The subEntidad to set.
	 */
	public void setSubEntidad(String subEntidad) {
		this.subEntidad = subEntidad;
	}
	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	/**
	 * @return Returns the idSede.
	 */
	public Integer getIdSede() {
		return idSede;
	}
	/**
	 * @param idSede The idSede to set.
	 */
	public void setIdSede(Integer idSede) {
		this.idSede = idSede;
	}
    
    
    /**
     * @return Returns the debeCambiarPwd.
     */
    public boolean isDebeCambiarPwd() {
        return debeCambiarPwd;
    }
    /**
     * @param debeCambiarPwd The debeCambiarPwd to set.
     */
    public void setDebeCambiarPwd(boolean debeCambiarPwd) {
        this.debeCambiarPwd = debeCambiarPwd;
    }
    
    
    /**
     * @return Returns the permisos.
     */
    public Integer[] getPermisos() {        
        return permisos;
    }
    /**
     * @param permisos The permisos to set.
     */
    public void setPermisos(Integer[] permisos) {        
        this.permisos = permisos;
    }

	public Integer getAmplitudMaxima() {
		return amplitudMaxima;
	}
	public void setAmplitudMaxima(Integer amplitudMaxima) {
		this.amplitudMaxima = amplitudMaxima;
	}
	
    public boolean isSugerirCambiarPwd() {
		return sugerirCambiarPwd;
	}

	public void setSugerirCambiarPwd(boolean sugerirCambiarPwd) {
		this.sugerirCambiarPwd = sugerirCambiarPwd;
	}
	
	public int getExpiracionPwd() {
		return expiracionPwd;
	}

	public void setExpiracionPwd(int expiracionPwd) {
		this.expiracionPwd = expiracionPwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


}
