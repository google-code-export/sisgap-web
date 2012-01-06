package pe.com.mmh.sisgap.comun.constantes;
/**
 * @version 1.0
 * Clase que guarda la información de las constantes generales.
 * @modelguid {B658BDAA-0E71-4848-84B7-54D18B1BB588}
*/

public class Constantes
{
	

	public final static String ESTADO_MODELO_INTEGRANTE_PENDIENTE = "P";
	public final static String ESTADO_MODELO_INTEGRANTE_OBSERVADO = "O";
	public final static String ESTADO_MODELO_INTEGRANTE_APROBADO = "A";	
	
	
	public final static String ESTADO_MODELO_PENDIENTE = "P";
	public final static String ESTADO_MODELO_APROBADO = "A";
	public final static String ESTADO_MODELO_TRANSMITIDO = "T";
	
		
    public final static Integer MENU_GENERAL = 0001;
    public final static Integer MENU_EJECUTOR = 0002;
    public final static Integer MENU_REPORTE_CREDITICIO = 0003;
    public final static Integer MENU_ADMINISTRACION= 0004;
    public final static Integer MENU_REPORTES= 0005;
    
    public final static String TRAMA_TIPO_EJECUTOR="E" ;
    public final static String TRAMA_TIPO_REPORTE_CREDITICIO="R" ;
    
    //Para el envío de tramas
    public final static String TRAMA_HEAD_CODIGO_TRANSACCION="EXFF" ;    
    public final static String TRAMA_BODY_CODIGO_TRANSACCION_EJECUTOR="EXF1" ;
    public final static String TRAMA_BODY_CODIGO_TRANSACCION_REPORTE_CREDITICIO="EXF2" ;
    
    public final static String REPORTE_EJECUTOR_TRANSMITIR_TRAMA= "1";
    public final static String REPORTE_CREDITICIO_TRANSMITIR_TRAMA= "2";
    public final static String REPORTE_APROBAR_MODELOS_GENERADOS= "3";
    public final static String REPORTE_HISTORIAL_MODELOS= "4";
    public final static String REPORTE_CONCEPTOS_ATRIBUTOS= "5";
    public final static String REPORTE_DICCIONARIO_GENERAL= "6";
    public final static String REPORTE_MODELOS_GENERADOS= "7";
            
       
    public final static String SI="S";
    public final static String NO="N";
    public final static char ESPACIO=' ';
    
			
	//KEY PARA ACTIONMESSAGES
	public static final String ACTION_MESSAGE_ID = "message";	
	public static final String ACTION_SECONDARY_MESSAGE_ID = "secondary_message";
		
	/* Constantes para Login 
	 */
	public static final String USUARIO_LOGUEADO = "USUARIO_LOGUEADO";	
	public static final String FIND_FORWARD = "FIND_FORWARD"; // Indispensable para manejo de Excepciones


	public static final String EXCEPTION="EXCEPTION";
	public static final String ALERT_MSG="ALERT_MSG";
	public static final String EXITO="EXITO";
	
	
	public static final String ESTADO_ACTIVO = "A";
	public static final String ESTADO_INACTIVO = "I";
	
	public static final int TOTAL_NUMEROS_INTEGRANTES = 8;
	
	public static final Integer MODELO_SIN_INTEGRANTES = 0;
	public static final Integer MODELO_CON_INTEGRANTES = 1;
	
	public static final int MODELO_BUSQUEDA_TODOS = 0;
	public static final int MODELO_BUSQUEDA_CLIENTES = 1;
	public static final int MODELO_BUSQUEDA_EQUIFAX = 2;
	public static final int MODELO_BUSQUEDA_CLIENTES_NUEVOS = 3;
	public static final int MODELO_BUSQUEDA_EQUIFAX_NUEVOS = 4;
	
	public static final int MODELO_BUSQUEDA_ESTAN_HISTORIAL = 5;
	
	public static final String MODELO_CODIGO_EQUIFAX = "000307";
	public static final int PAGINACION_POPUP_ASOCIAR_MODELO = 10;
	public static final int MAX_LINEA_TXT_CLIENTE = 106;
	
	
	public static final String CLIENTE_ID_NINGUNA = "NI";
	public static final String CLIENTE_ID_NUEVO = "NU";
	public static final String CLIENTE_ID_ACTUALIZADO = "AC";
	public static final String CLIENTE_ID_TODOS = "TO";
	
	public static final String CLIENTE_NINGUNA = "Ninguna";
	public static final String CLIENTE_NUEVO = "Nuevo";
	public static final String CLIENTE_ACTUALIZADO = "Actualizado";
	public static final String CLIENTE_TODOS = "Todos";
	
	
	public static final String RUTA_RESOURCE_BUNDLE = "pe.com.equifax.paramweb.resources.mensajes";
	public static final Integer AUDITORIA_ID_TODOS = 0;
	public static final String AUDITORIA_TODOS = "Todos";
	
	public static final String REPORTE_SOCIO_01 = "REPORTE_SOCIO_01"; 

}
