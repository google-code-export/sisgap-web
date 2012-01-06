package pe.com.mmh.sisgap.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import org.apache.log4j.Logger;



/**
 * Clase que contiene métodos para trabajar con archivos.
 * @autor rsanchez
 */
public class FileUtil   
{	  
	public static final String SUFIJO_PNG =".png";
	public static final String SUFIJO_PDF =".pdf";
	public static final String SUFIJO_XLS =".xls";
	public static final String SUFIJO_JPG =".jpg";
	public static final String SUFIJO_XML =".xml";
	public static final String SUFIJO_FO=".fo";
	public static final String SUFIJO_RTF=".rtf";
	public static final String SUFIJO_ZIP=".zip";
	public static final String SUFIJO_TXT=".txt";
	public static final String SUFIJO_CSV=".csv";
	public static final String SUFIJO_SER=".ser";
	

	private static Logger logger = Logger.getLogger(FileUtil.class);
	
	/**
	 * Se encarga de guardar un archivo de hasta 2Md como máximo en un disco local.
	 * @param fileData Stream que contiene la información del archivo a guardarse.
	 * @param fileName Nombre con el que se guardará el archivo.
	 * @param savePath Ruta en la que se guardará el archivo.
	 * @throws Exception
	 */
	public static void saveFile(InputStream fileData, String fileName, String savePath) throws Exception {
		
		logger.debug("Ingresando a FileUtil.saveFile");
		try
		{
			StringBuffer sb = new StringBuffer();
			sb.append(savePath).append(fileName);

			File rutaGrabado = new File(sb.toString());
			File aux = new File(rutaGrabado.getParentFile().toString());
			
			if (!aux.exists())
				aux.mkdirs();

			InputStream stream = fileData;
			OutputStream bos = new FileOutputStream(rutaGrabado);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
			{
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
		}
		catch (FileNotFoundException fnfe)
		{
			// ocurrio un error!! el archivo no fue encontrado (error en escritura)
			throw fnfe;
		}
		catch (IOException ioe)
		{
			throw new Exception("Se atrapó IOException:" + ioe.toString());
		}
		catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Remplaza dentro de aInput las ocurrencias de la cadena aOldPattern
	 * por la cadena aNewPattern.
	 * @param aInput String dentro del cual se realizará la operación de remplazo.
	 * @param aOldPattern String que especifica la cadena a remplazarce.
	 * @param aNewPattern String que especifica la cadena de remplazo.
	 * @return
	 * @throws Exception
	 */
	public static String replace(String aInput, String aOldPattern, String aNewPattern) throws Exception {
		if ( aOldPattern.equals("") ) {
			throw new Exception("Debe especificar un valor para la cadena que se sustituirá.");
		}
		if ( aInput.equals("") ) {
			throw new Exception("Debe especificar un valor para la cadena en la que se realizará el remplazo.");
		}
		if (aNewPattern == null) {
			aNewPattern = "";
		}
		final StringBuffer result = new StringBuffer();
		int startIdx = 0;
		int idxOld = 0;
		while ((idxOld = aInput.indexOf(aOldPattern, startIdx)) >= 0) {
			result.append( aInput.substring(startIdx, idxOld) );
			result.append( aNewPattern );
			startIdx = idxOld + aOldPattern.length();
		}
		result.append( aInput.substring(startIdx) );
		return result.toString();
	}

	/**
	 * Se encarga de guardar un String en un disco local.
	 * @param texto, la cadena o contenido del archivo que se guardara. NO puede ser NULL
	 * @param pathFileName, nombre de la ruta unido con el nombre del archivo a crear. NO puede ser NULL. Por ejemplo: c:\\dir\\file.txt (caso windows)
	 * @throws Exception
	 */
	public static void saveStringToFile(String texto, String pathFileName) throws Exception {
		try
		{
			File f = new File(pathFileName);
			File aux = new File(f.getParentFile().toString());
			
			if (!aux.exists())
				aux.mkdirs();
			
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			String[] arregloTexto = convierteTiraEnArregloValidaVacio(texto, "\n");

			for (int i = 0; i < arregloTexto.length; i++) {
				bw.write(arregloTexto[i]);
				bw.newLine();
			}
			bw.close();
			fw.close();
		}
		catch (IOException e)
		{
			// ocurrio un error al escribir el archivo
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param tira
	 * @param separador
	 * @return
	 */
	public static String[] convierteTiraEnArregloValidaVacio(String tira, String separador)// throws Exception
	{
		int idxAnt = 0;
		String tokenDerecha = tira;
		List listaTokens = new ArrayList();
		
		while(idxAnt>=0){
			int idxOfSeparador = tokenDerecha.indexOf(separador);
			
			String tokenActual = null;
			if(idxOfSeparador >= 0){
				tokenActual = tokenDerecha.substring(0, idxOfSeparador);
				tokenDerecha = tokenDerecha.substring(idxOfSeparador + separador.length(), tokenDerecha.length() );
			}else{
				tokenActual = tokenDerecha;
				tokenDerecha = null;
			}
			idxAnt = idxOfSeparador;
			listaTokens.add(tokenActual);
		}
		
		String[] arrTokens = new String[listaTokens.size()];
		for(int i=0; i<listaTokens.size(); i++){
			arrTokens[i] = (String) listaTokens.get(i);
		}
		
		return arrTokens;
	}
	
	/**
	 * Lee y devuelve el contenido del archivo "file" tipo texto. 
	 * @param file Nombre del archivo cuyo contenido será recuperado.
	 * @return
	 * @throws Exception
	 */
	public static StringBuffer readFile(String file) throws Exception {
		StringBuffer contenidoCont = null;
		
		try
		{
			BufferedReader dis = new BufferedReader(new FileReader(file));
			contenidoCont = new StringBuffer("");
			String linea = "";
			boolean inicio = true;

			while ((linea = dis.readLine()) != null) {
				/*
				if (inicio) {
					inicio = false;
				} else {
					contenidoCont.append("<BR>");
				}
				*/
				contenidoCont.append(linea).append("\n");
			}
			contenidoCont.append("<BR>");
			
		}
		catch (FileNotFoundException e)
		{
			// ocurrio un error!! el archivo no fue encontrado (error en lectura)
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return contenidoCont;
	}
	


	public static String[] convierteLineaEnArreglo(String linea, String separador) throws Exception
	{
		StringTokenizer st = new java.util.StringTokenizer(linea, separador, false);	
		String[] resultado = new String[st.countTokens()];
			int x = 0;
			while (st.hasMoreTokens() == true) {
				String elemento = st.nextToken();
					resultado[x] = elemento;
					x++;
				}
		return resultado;
	}
	
	/**
	 * Copies src file to dst file.
	 * If the dst file does not exist, it is created
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public static void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
    
		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
	
	public static String obtenerRutaArchivosConfiguracion(String rutaFisica){
        // Nota: Verificar si la rutaFisica termina con un caracter "\",
		//       sino tuviera, colocarlo
		if (rutaFisica.substring(rutaFisica.length()-1,rutaFisica.length()).equals(java.io.File.separator)==false)
			rutaFisica = rutaFisica + java.io.File.separator;							
		//obtener ruta real donde estan ubicados los archivos de configuracion
		String rutaArchivosConfiguracion=rutaFisica + "WEB-INF"+java.io.File.separator+"xml"+File.separator;
		return rutaArchivosConfiguracion;		
		
	}
	//***Métodos Agregados
	
	 /****************************************************************************************************
	/*                                    Métodos de archivos temporales
	 * Los archivos se graban en la carpeta temporal del sistema, que
	 * generalmente es c:/temp en windows y /tmp en linux
	 * 
	 *****************************************************************************************************/

	
	/**
	 * Method createUniqueFileOnDirectory
	 * @param sufijo
	 * @param directorio
	 * @return File
	 * @throws Exception
	 */
	public synchronized static File createUniqueFileOnDirectory(String sufijo, File directorio) throws Exception
	{
		
		if (directorio.isDirectory()==false)
			throw new Exception("Parameter is not a directory");
		File resultado = File.createTempFile("bat_",sufijo, directorio);
		if (logger.isDebugEnabled())
			logger.debug("archivo generado : "+directorio.getPath()+ java.io.File.separator + resultado.getName());
		return resultado;	
	}

	/**
	 * Method createTempFile
	 * @param sufijo
	 * @return File
	 * @throws IOException
	 */
	public synchronized static File createTempFile(String sufijo) throws IOException
		{
			File resultado = File.createTempFile("bat_",sufijo);
			//resultado.deleteOnExit();
			if (logger.isDebugEnabled())
				logger.debug("archivo generado: "+System.getProperty("java.io.tmpdir") + resultado.getName());
			return resultado;
		}

	/**
	 * Method getSystemTempDir
	 * @return String
	 */
	public static String getSystemTempDir()
		{
			return System.getProperty("java.io.tmpdir");
		}


	/**Luis Medina
	 * Me devuelve una lista de Files que cumplen con el atributo de prefijo
	 * @param path
	 * @param prefix
	 * @return
	 */
	public static List obtenerArchivosSegunPrefijo(String path,final String  prefix){		
		File file=new File(path);
		String str[]=file.list(
					     new FilenameFilter(){					    	
					    	 public boolean accept(File dir, String name){					    		    
					    	        return name.startsWith(prefix);
					    	    }
					     }				
		             );
	   
		List lstFile=new ArrayList();
		File ff;
		for(int i=0;i<str.length;i++){
		      ff=new File(path+str[i]);
		      lstFile.add(ff);
		   }			
		return lstFile;		
	}
	/**
	 * Luis Medina
	 * @param lstFiles
	 * @return
	 */
	public static File obtenerMasReciente(List lstFiles){
		long max=0;
	    File file=new File("");
		for(int i=0;i<lstFiles.size();i++){
		    File ff=(File)lstFiles.get(i);
		    if(ff.lastModified()>max){
		    	max=ff.lastModified();
		    	file=ff;
		    }
		   
		}	
		return file;
	}
	
	/**
	 * 
	 * Elimina todos los archivos del path
	 * si no encuentra el directorio, lo crea
	 * @param path
	 */
	public static void eliminarArchivos( String path ){
		 File f = new File(path);
	    if (f.isDirectory()==false){	    	
	   	 f.mkdirs();
	     }
	      String files[] = f.list();      
	      logger.debug("Tamaño lista a eliminar:"+files.length);
	     //add some files to the zip...
	     for(int i=0;i<files.length;i++){
	   	   logger.debug("Eliminando:"+(path+files[i]));
		       File ff= new File(path+files[i]);
		       ff.delete();		    
	     }		
	}
	 public static void eliminarArchivos( String path , List lstFiles)throws Exception{
		
		 File f = new File(path);
	    if (f.isDirectory()==false){	    	
	   	 throw new Exception("No existe el directorio");
	   }      
	    
	   File file;
	   logger.debug("Tamaño lista a eliminar:"+lstFiles.size());
	   //add some files to the zip...
	   for(int i=0;i<lstFiles.size();i++){
	   	   file=(File)lstFiles.get(i);
		       file.delete();
		       logger.debug("Eliminando:"+(file.getName()));
	    }		
	 } 
	/**
	 * Verifica que exista el directorio, en caso no exista lo crea
	 * @param path
	 */
	public static void crearDirectorio(String path){
	     File f = new File(path);
		 if (f.isDirectory()==false){	    	
		  	     f.mkdirs();
		  }   	
	}
	
	 public static void renombrarArchivos( String path , List lstFiles, String extension)throws Exception{
			
		File f = new File(path);
	    if (f.isDirectory()==false){	    	
	   	 throw new Exception("No existe el directorio");
	   }      
	    
	   File file;
	   logger.debug("Tamaño lista a eliminar:"+lstFiles.size());
	   //add some files to the zip...
	   for(int i=0;i<lstFiles.size();i++){
	   	   file=(File)lstFiles.get(i);
	   	   String nombreAntiguo= file.getName().toString().trim();
		   String nombreNuevo=nombreAntiguo.substring(0,nombreAntiguo.indexOf("."))+extension;    
	   	   file.renameTo(new File(path, nombreNuevo));
		   logger.debug("Archivo : "+nombreAntiguo+" renombrado a: "+file.getName()+" path: "+path);
	    }		
	 }
	
	 public static void renombrarArchivos( String path , String fileName, String extension)throws Exception{
			
			File f = new File(path);
		    if (f.isDirectory()==false){	    	
		   	 throw new Exception("No existe el directorio");
		    }      
		    
		   File file=new File(path, fileName);
		   
		   	   String nombreAntiguo= fileName;
			   String nombreNuevo=nombreAntiguo.substring(0,nombreAntiguo.indexOf("."))+extension;    
		   	   file.renameTo(new File(path, nombreNuevo));
			   logger.debug("Archivo : "+nombreAntiguo+" renombrado a: "+nombreNuevo+" path: "+path);
		    		
		 }
	
}
	
