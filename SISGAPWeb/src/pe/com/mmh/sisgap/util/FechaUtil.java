package pe.com.mmh.sisgap.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import pe.com.mmh.sisgap.system.domain.Parametro;

/**
 * 
 * Clase utilitaria para manejo de fechas y horas
 */
public class FechaUtil {
	/** @modelguid {D7476452-5162-43A4-B553-862EF88AC7D0} */
	public static long NUMERO_BASE_RELOJ = 60; // numero base usado en reloj (60 seg, 60 min)
	/** @modelguid {3466F85A-1248-4B3E-A910-82C957FC842B} */
	public static long NUMERO_BASE_MILISEGUNDOS = 1000; // numero base usado en reloj (60 seg, 60 min)
	
	public static int FECHA_INICIO_ACTIVIDADES = 1;
	public static int FECHA_FIN_ACTIVIDADES = 2;

	/** @modelguid {7D375008-746C-4124-80A3-E5CDECC599DC} */
	public static String getFechaDDMMMEtiqueta(int dia, int mes, int ahno)
	{
		DecimalFormat ddformat = new DecimalFormat("00");
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();

		switch (mes) {
			case Calendar.JANUARY:
				sb.append("Enero");
				break;
			case Calendar.FEBRUARY:
				sb.append("Febrero");
				break;
			case Calendar.MARCH:
				sb.append("Marzo");
				break;
			case Calendar.APRIL:
				sb.append("Abril");
				break;
			case Calendar.MAY:
				sb.append("Mayo");
				break;
			case Calendar.JUNE:
				sb.append("Junio");
				break;
			case Calendar.JULY:
				sb.append("Julio");
				break;
			case Calendar.AUGUST:
				sb.append("Agosto");
				break;
			case Calendar.SEPTEMBER:
				sb.append("Setiembre");
				break;
			case Calendar.OCTOBER:
				sb.append("Octubre");
				break;
			case Calendar.NOVEMBER:
				sb.append("Noviembre");
				break;
			case Calendar.DECEMBER:
				sb.append("Diciembre");
				break;
		}

		sb.append(" ");
		sb.append(ddformat.format(dia));
		sb.append(",");
		sb.append(" ");
		sb.append(ahno);

		ddformat=null;
		return sb.toString();
	}

	/** @modelguid {554A6DF5-911C-489C-A9C4-EFA7A5B5EA83} */
	public static String getFechaDDMMMEtiqueta()
	{
		DecimalFormat ddformat = new DecimalFormat("00");
		Calendar cal = Calendar.getInstance();
		int dia = cal.get(Calendar.DATE);
		int mes = cal.get(Calendar.MONTH);
		int ahno = cal.get(Calendar.YEAR);
		StringBuffer sb = new StringBuffer();

		switch (mes) {
			case Calendar.JANUARY:
				sb.append("Enero");
				break;
			case Calendar.FEBRUARY:
				sb.append("Febrero");
				break;
			case Calendar.MARCH:
				sb.append("Marzo");
				break;
			case Calendar.APRIL:
				sb.append("Abril");
				break;
			case Calendar.MAY:
				sb.append("Mayo");
				break;
			case Calendar.JUNE:
				sb.append("Junio");
				break;
			case Calendar.JULY:
				sb.append("Julio");
				break;
			case Calendar.AUGUST:
				sb.append("Agosto");
				break;
			case Calendar.SEPTEMBER:
				sb.append("Setiembre");
				break;
			case Calendar.OCTOBER:
				sb.append("Octubre");
				break;
			case Calendar.NOVEMBER:
				sb.append("Noviembre");
				break;
			case Calendar.DECEMBER:
				sb.append("Diciembre");
				break;
		}

		sb.append("" );
		sb.append(ddformat.format(dia));
		sb.append(",");
		sb.append(" ");
		sb.append(ahno);

		ddformat=null;
		return sb.toString();
	}
	/**
	 * Resta dos fecha que son del tipo String
	 * @param fechaMinuendo
	 * @param fechaSustraendo
	 * @return long numero de dias
	 * @throws ParseException
	 * @modelguid {7EB43A94-FD96-42D5-8317-A30B7EBCF6AA}
	 */
	/*public static long diferenciaDias(String fechaSustraendo, String fechaMinuendo)
	throws ParseException{
	   long dias = stringToTimestamp(fechaMinuendo).getTime( ) - stringToTimestamp(fechaSustraendo).getTime( );
	   dias = dias / 1000l / 3600l / 24l;
	   return dias;
	}*/
	
  /** Resta dos fecha que son del tipo String
    * lmedina
	* @param fechaMinuendo
	* @param fechaSustraendo
	* @return long numero de dias
	* @throws ParseException
	* @modelguid {7EB43A94-FD96-42D5-8317-A30B7EBCF6AA}
	*/
   private static long diferenciaDias(String fechaSustraendo, String fechaMinuendo)
   throws ParseException{
	  long dias = stringToTimestamp5(fechaMinuendo).getTime( ) - stringToTimestamp5(fechaSustraendo).getTime( );
	  dias = dias / 1000l / 3600l / 24l;
	  return dias;
   }
	
   /**
    * lmedina
	* Method diferenciaDias
	* @param fechaSustraendo
	* @param fechaMinuendo
	* @return Long
	* @throws ParseException
	*/
  public static Long diferenciaDias(Timestamp fechaSustraendo, Timestamp fechaMinuendo) 
		throws ParseException{
		   if(fechaMinuendo != null && fechaSustraendo != null){
			   String str1=FechaUtil.TimestampToStringAAAAMMDDhhmm(fechaSustraendo);
			   String str2=FechaUtil.TimestampToStringAAAAMMDDhhmm(fechaMinuendo);
			   return	new Long (FechaUtil.diferenciaDias(str1,str2 )) ;			
		   }else{
			   return null;
		   }
	   }
	
   /**
	 * Resta dos fecha que son del tipo String
	 * lmedina
	 * @param fechaMinuendo
	 * @param fechaSustraendo
	 * @return long numero de horas
	 * @throws ParseException
	 * @modelguid {7EB43A94-FD96-42D5-8317-A30B7EBCF6AA}
	 */
	 private static long diferenciaHoras(String fechaSustraendo, String fechaMinuendo)
			   throws ParseException{
				  long dias = stringToTimestamp5(fechaMinuendo).getTime( ) - stringToTimestamp5(fechaSustraendo).getTime( );
				  dias = dias / 1000l / 3600l ;
				  return dias;
			   }
	
   /**
	* Method diferenciaDias
	* lmedina
	* @param fechaSustraendo
	* @param fechaMinuendo
	* @return Long
	* @throws ParseException
	*/
	public static Long diferenciaHoras(Timestamp fechaSustraendo, Timestamp fechaMinuendo) 
		throws ParseException{
		   if(fechaMinuendo != null && fechaSustraendo != null){
			   String str1=FechaUtil.TimestampToStringAAAAMMDDhhmm(fechaSustraendo);
			   String str2=FechaUtil.TimestampToStringAAAAMMDDhhmm(fechaMinuendo);
			   return	new Long (FechaUtil.diferenciaHoras(str1,str2 )) ;			
		   }else{
			   return null;
		   }
	   }		
	
	/**
	 * Obtiene el anio
	 * @return
	 * @modelguid {B59ED5D1-C048-4405-877D-E972848B0320}
	 */

	public static int getAnio( ) {
		Calendar cal =  Calendar.getInstance( ) ;
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Obtiene el mes
	 * @return
	 * @modelguid {5217418B-0FE2-432D-AC07-25287DEE54D9}
	 */

	public static int getMes( ) {
		return (Integer.parseInt(timestampToStringDDMMYYYY(getCurrentTimestamp()).substring(4,5)));
	}
	/**
		 * DEVUELVE LA FECHA ACTUAL DISMINUIDA EN 1 AÑO
		 * @param fechaMinuendo
		 * @param fechaSustraendo
		 * @return long numero de dias
		 * @throws ParseException
		 * @modelguid {23790045-83F8-4261-80CB-F18B10A3C9B6}
		 */
		public static Timestamp getTimestampAnioAnterior(int numAnios ) throws ParseException{
			String cadfecha = timestampToString(getCurrentTimestamp());
			int anio = Integer.parseInt(cadfecha.substring(6, 10)) - numAnios ;
			StringBuffer cadfechaant = new StringBuffer();
			cadfechaant.append(cadfecha.substring(0, 6));
			cadfechaant.append(String.valueOf(anio));
			cadfechaant.append(cadfecha.substring(10,19));
			return stringToTimestamp2(cadfechaant.toString());
		}

	/**
	 * Convierte un String que contiene una fecha a el tipo Timestamp
	 * @param s
	 * @return Timestamp
	 * @throws ParseException
	 * @modelguid {D2590BD1-4346-4873-8AC7-22CD3AF0E2E2}
	 */
	public static Timestamp stringToTimestamp(String s) throws ParseException{
		Timestamp outDate = null;
		if(s != null && s.length() == 10)
		{
			long time = new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}

	
	public static Timestamp stringToTimestamp_ddMMYYYY(String s) throws ParseException{
		Timestamp outDate = null;
		if(s != null && s.length() == 10)
		{
			long time = new SimpleDateFormat("dd-MM-yyyy").parse(s).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}
	
	/**
	 * Convierte un String que contiene una fecha a el tipo Timestamp
	 * @param s
	 * @return Timestamp
	 * @throws ParseException
	 * @modelguid {35C4EAE4-5FB7-41B6-8AA5-07F8208A545A}
	 */
	public static Timestamp stringYYYYMMDDToTimestamp(String s) throws ParseException{
		Timestamp outDate = null;
		if(s != null && s.length() == 8)
		{
			long time = new SimpleDateFormat("yyyyMMdd").parse(s).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}

	/** @modelguid {95B7D0F1-3C93-4E42-92AE-2D1932C874B7} */
	public static Timestamp stringToTimestamp2(String s) throws ParseException{
			Timestamp outDate = null;
			if(s != null )
			{
				long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s).getTime();
				outDate = new Timestamp(time);
			}
			return outDate;
	}		
	
	public static Timestamp stringToTimestamp3(String s) throws ParseException{
			Timestamp outDate = null;
			if(s != null )
			{
				long time = new SimpleDateFormat("hh:mm:ss").parse(s).getTime();
				outDate = new Timestamp(time);
			}
			return outDate;
	}
	
	public static Timestamp stringToTimestamp4(String s) throws ParseException{
			Timestamp outDate = null;
			if(s != null )
			{
				long time = new SimpleDateFormat("hh:mm").parse(s).getTime();
				outDate = new Timestamp(time);
			}
			return outDate;
	}
	
	
	/** @modelguid {95B7D0F1-3C93-4E42-92AE-2D1932C874B7} */
		public static Timestamp stringToTimestamp5(String s) throws ParseException{
				Timestamp outDate = null;
				if(s != null )
				{
					long time = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s).getTime();
					outDate = new Timestamp(time);
				}
				return outDate;
		}

	public static Timestamp stringToTimestamp6(String s) throws ParseException{
					Timestamp outDate = null;
					if(s != null )
					{
						long time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(s).getTime();
						outDate = new Timestamp(time);
					}
					return outDate;
			}
	public static Timestamp stringToTimestamp7(String s) throws ParseException{
						Timestamp outDate = null;
						if(s != null )
						{
							long time = new SimpleDateFormat("dd-MM-yyyy").parse(s).getTime();
							outDate = new Timestamp(time);
						}
						return outDate;
				}
	
	public static Timestamp string_YYYYMM_ToTimestamp(String s) throws ParseException{
		Timestamp outDate = null;
		if(s != null )
		{
			long time = new SimpleDateFormat("yyyyMM").parse(s).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
}
				
	/** @modelguid {42239473-6CC8-43EC-9D2B-55CF5496B21D} */
	public static String add(String origin, int days) throws Exception
	{
		/**
		  * This Method receives two parameters:
		  *
		  * String origin = String Date in the format "dd/mm/yyyy"
		  * int    days   = number of days to add or substract to the String Date
		  *
		  * And this method returns:
		  *
		  * A String Date in the format "dd/mm/yyyy" with the resultant date
		  *
		  * NOTE: if you want to "subtract" days you
		  *       should provide a negative integer for the parameter "days"
		 */

		// extract the year, month and day
		String sYear = origin.substring(6, 10);
		String sMonth = origin.substring(3, 5);
		String sDay = origin.substring(0, 2);

		int iYear = Integer.parseInt(sYear);
		int iMonth = Integer.parseInt(sMonth);
		int iDay = Integer.parseInt(sDay);

		iMonth--;

		// Convert the String Date to a Calendar Object
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(iYear, iMonth, iDay);

		// Add (or substract) the given days
		cal.add(Calendar.DATE, days);

		// Convert the Calendar Object to String
		StringBuffer sb = new StringBuffer();

		// Number Formaters
		java.text.DecimalFormat dformat = new java.text.DecimalFormat("00");
		java.text.DecimalFormat yformat = new java.text.DecimalFormat("0000");

		sb.append(dformat.format(cal.get(java.util.Calendar.DATE)));
		sb.append("-");
		sb.append(dformat.format(cal.get(java.util.Calendar.MONTH) + 1));
		sb.append("-");		
		sb.append(yformat.format(cal.get(java.util.Calendar.YEAR)));
		
		
		
		return sb.toString();
	}
	/**
	  Internal Method for class Tempo
	  Converts a Calendar Object to a String

	  values for input parameter "format":
		0 = return a String in the format 'dd/mm/yyyy'
		1 = return a String in the format 'dd/mm/yyyy hh:mm:ss' (24 hours)
	 * @modelguid {232B6D75-83FE-4AB3-BEF6-7134D0027BF2}
	  */

	private static String calendarToString(java.util.Calendar c, int format)
	{
		// Number Formaters
		java.text.DecimalFormat dformat = new java.text.DecimalFormat("00");
		java.text.DecimalFormat yformat = new java.text.DecimalFormat("0000");

		StringBuffer sb = new StringBuffer();

		sb.append(dformat.format(c.get(java.util.Calendar.DATE)));
		sb.append("/");
		//Remember: in Java, the first month is 0 (January)
		// that is why we must add 1 to the Calender.MONTH result
		sb.append(dformat.format(c.get(java.util.Calendar.MONTH) + 1));
		sb.append("/");
		sb.append(yformat.format(c.get(java.util.Calendar.YEAR)));

		if (format == 1)
			{
			//add the time
			sb.append(" ");

			sb.append(dformat.format(c.get(java.util.Calendar.HOUR_OF_DAY)));
			sb.append(":");
			sb.append(dformat.format(c.get(java.util.Calendar.MINUTE)));
			sb.append(":");
			sb.append(dformat.format(c.get(java.util.Calendar.SECOND)));
		}

		return sb.toString();
	}

	/** @modelguid {AEC7A2B9-5E25-44AD-A4EA-DBB70B6B27BC} */
	/*
	 * Devuelve la fecha en formato yyyy-mm-dd
	 * 
	 */
	public static String TimestampToStringAAAAMMDD(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	
	public static String TimestampToStringHHMMSS(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	
	/**lmedina
	 * Devuelve una fecha en formato dd-mm-yyyy
	 * @param t
	 * @return
	 */
	public static String TimestampToStringDDMMAAAA(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	/**lmedina
	 * Devuelve una fecha en formato dd-mm-yyyy
	 * @param t
	 * @return
	 */
	public static String TimestampToStringDDMMAAAA2(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	
	
	public static String TimestampToStringAAAA(Timestamp t ){
			if( t == null ) return new String();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			Date data = new Date(t.getTime());
			return formatter.format(data);
		
		}
		
	/**
	 * Luis Medina  19/12/2005 02:41:44 PM
	 * @param t
	 * @return
	 * Comentario:Retorna una fecha con el formato yyyy-MM-dd hh:mm:ss 
	 */
	public static String TimestampToStringAAAAMMDDhhmmss(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	
	/**
	 * Luis Medina  28/09/2006 02:41:44 PM
	 * @param t
	 * @return
	 * Comentario:Retorna una fecha con el formato yyyy-MM-dd hh:mm:ss 
	 */
	public static String TimestampToStringDDMMYYYYHHmm(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	/**
		 * ravila  19/12/2005 02:41:44 PM
		 * @param t
		 * @return
		 * Comentario:Retorna una fecha con el formato yyyy-MM-dd hh:mm:ss para certificado
		 */
		public static String TimestampToStringAAAAMMDDhhmmss1(Timestamp t ){
			if( t == null ) return new String();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
			Date data = new Date(t.getTime());
			return formatter.format(data);
		
		}
	/**
	 * @author hherrera
	 * @param t
	 * @return
	 * Comentario:Retorna una fecha con el formato yyyy.MM.dd.hh.mm.ssZ 
	 */
	public static String obtenerFechaFormatoQCX(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}


	/** @modelguid {AEC7A2B9-5E25-44AD-A4EA-DBB70B6B27BC} */
	public static String TimestampToStringDDMMAA(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(t.getTime());
		return formatter.format(data);

	}
	
	/** @modelguid {AEC7A2B9-5E25-44AD-A4EA-DBB70B6B27BC} */
	public static String TimestampToStringDDMMAA_YEAR_2DIGITS(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		Date data = new Date(t.getTime());
		return formatter.format(data);

	}
	
	public static String TimestampToStringDDMMAA1(Timestamp t ){
			if( t == null ) return new String();

			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
			Date data = new Date(t.getTime());
			return formatter.format(data);

		}
		
	public static String TimestampToStringDDMMAA2(Timestamp t ){
				if( t == null ) return new String();

				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
				Date data = new Date(t.getTime());
				return formatter.format(data);

			}
		
	public static String TimestampToStringMMAAAA(Timestamp t ){
					if( t == null ) return new String();

					SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
					Date data = new Date(t.getTime());
					return formatter.format(data);

				}
	/**
	 * Este método devuelve la hora y minutos como Timestamp dada una cadena en formato HH:mm (24 horas)
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param horaMinuto, la cadena fecha a convertir a Timestamp. Pejm: 10:20, 16:23
	 * @return la hora y minutos de la fecha en formato 24 horas como Timestamp.
	 * @throws Exception
	 * @modelguid {8CCF0624-E6D3-438C-8069-DBF3833EFF8D}
	 */
	public static Timestamp stringHHmmToTimestamp(String horaMinuto) throws Exception
	{
		Timestamp outDate = null;
		long time = 0;

		if(horaMinuto != null && horaMinuto.length() != 0)
		{
			time = (new SimpleDateFormat("HH:mm")).parse(horaMinuto).getTime();
			outDate = new Timestamp(time);
		}
		return outDate;
	}

	/**
	 * Este método devuelve la hora  en formato HH (24 horas)
	 * @author ravila - Ricardo Avila
	 * @version v1.0, Nov 16, 2005
	 * @param t, la fecha a convertir
	 * @return la hora  de la fecha en formato 24 horas. Pejm: 15, 09, etc
	 * @modelguid {B4D51084-166C-4A85-B65F-AEDFF0B7ADD4}
	 */
		
	public static String timestampToStringHH(Timestamp t)
	{
		SimpleDateFormat formatter = null;
		Date data = null;

		if( t == null )
		{
			return new String();
		}
		formatter = new SimpleDateFormat("HH");
		data = new Date(t.getTime());
		return formatter.format(data);
	}
	
	
	
	/**
	 * Este método devuelve el dia de una fecha
	 * @author lmedina -
	 * @param t, la fecha a convertir
	 * @return el dia ejm 01,02,31
	 * @modelguid {B4D51084-166C-4A85-B65F-AEDFF0B7ADD4}
	 */
	
	public static String timestampToStringDD(Timestamp t)
	{
		SimpleDateFormat formatter = null;
		Date data = null;

		if( t == null )
		{
			return new String();
		}
		formatter = new SimpleDateFormat("dd");
		data = new Date(t.getTime());
		return formatter.format(data);
	}
	
	
	
	//devuelve el mes 
	public static String timestampToStringMM(Timestamp t)
	{
		SimpleDateFormat formatter = null;
		Date data = null;

		if( t == null )
		{
			return new String();
		}
		formatter = new SimpleDateFormat("MM");
		data = new Date(t.getTime());
		return formatter.format(data);
	}
	
	
	
	/**
	 * Este método devuelve los minutos  en formato mm (24 horas)
	 * @author ravila - Ricardo Avila
	 * @version v1.0, Nov 16, 2005
	 * @param t, la fecha a convertir
	 * @return los minutos  de la fecha en formato 24 horas. Pejm: 15, 09, etc
	 * @modelguid {B60FB9B1-BA47-4917-8005-A0E569DA4CF0}
	 */
	public static String timestampToStringmm(Timestamp t)
	{
		SimpleDateFormat formatter = null;
		Date data = null;

		if( t == null )
		{
			return new String();
		}
		formatter = new SimpleDateFormat("mm");
		data = new Date(t.getTime());
		return formatter.format(data);
	}
	/**
	 * Este método devuelve la hora y minutos en formato HH:mm (24 horas)
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t, la fecha a convertir
	 * @return la hora y minutos de la fecha en formato 24 horas. Pejm: 15:03, 09:12, etc
	 * @modelguid {C007F880-69AA-4F77-9578-89EE016E1EA8}
	 */
	public static String timestampToStringHHmm(Timestamp t)
	{
		SimpleDateFormat formatter = null;
		Date data = null;

		if( t == null )
		{
			return new String();
		}
		formatter = new SimpleDateFormat("HH:mm");
		data = new Date(t.getTime());
		return formatter.format(data);
	}
	
	public static String timestampToStringHHmm1(Timestamp t)
		{
			SimpleDateFormat formatter = null;
			Date data = null;

			if( t == null )
			{
				return new String();
			}
			formatter = new SimpleDateFormat("HHmm");
			data = new Date(t.getTime());
			return formatter.format(data);
		}

	/**
	 * Devuelve el componente fecha de un Timestamp (dd/MM/yyyy)
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 16, 2005
	 * @param t, la fecha a truncar
	 * @return un timestamp que solo contiene el componente dd/MM/yyyy
	 * @modelguid {D315131D-826B-4B84-92EC-4FBA2C3211FF}
	 */
	public static Timestamp truncateTimestampddMMyyyy(Timestamp t) 
	{
	   GregorianCalendar gc1 = new GregorianCalendar();
	   GregorianCalendar gc2 = null;
	   int year = 0;
	   int month = 0;
	   int day = 0;

	   gc1.clear();
	   gc1.setTime(t);
	   year = gc1.get(Calendar.YEAR);
	   month = gc1.get(Calendar.MONTH);
	   day = gc1.get(Calendar.DAY_OF_MONTH);
	   gc2 = new GregorianCalendar(year, month, day);
	   return (new Timestamp(gc2.getTime().getTime())); 
	}

	/**
	 * Devuelve el componente hora-minuto de un Timestamp (HH:mm)
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 16, 2005
	 * @param t, la fecha a truncar
	 * @return un timestamp que solo contiene el componente HH:mm
	 * @modelguid {E873F1E1-1756-4915-9E4B-CF8CCAAE6DFA}
	 */
	public static Timestamp truncateTimestampHHmm(Timestamp t) 
	{
	   GregorianCalendar gc1 = new GregorianCalendar();
	   GregorianCalendar gc2 = new GregorianCalendar();
	   int year = 0;
	   int month = 0;
	   int day = 0;
	   int hour = 0;
	   int minute = 0;

	   gc1.clear();
	   gc1.setTime(t);
	   hour = gc1.get(Calendar.HOUR_OF_DAY);
	   minute = gc1.get(Calendar.MINUTE);
	   gc2.clear();
	   gc2.roll(Calendar.HOUR_OF_DAY, hour);
	   gc2.roll(Calendar.MINUTE, minute);
	   return (new Timestamp(gc2.getTime().getTime())); 
	}

	/**
	 * Este método devuelve la hora y minutos en formato HH:mm (24 horas)
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param tFull, la fecha base, de aqui solo se considera el componente fecha (dd/MM/yyyy)
	 * @param tHora, la fecha a sumar, de aqui solo se consideran las horas y minutos (HH:mm)
	 * @return la fecha base agregandole las horas y minutos de "tHora"
	 * @modelguid {4CB23314-0358-4D5C-A9E1-133408C0C991}
	 */
	public static Timestamp timestampAddTimestampHora(Timestamp tFull, Timestamp tHora) throws Exception
	{
		GregorianCalendar gc = new GregorianCalendar();
		Timestamp tBase = truncateTimestampddMMyyyy(tFull);
		Timestamp tOffSet = truncateTimestampHHmm(tHora);
		long milisegundosFechaCero = 0;

		gc.clear();
		milisegundosFechaCero = gc.getTime().getTime();
		return (new Timestamp(tBase.getTime() + tOffSet.getTime() - milisegundosFechaCero));
	}

	/**
	 * Este método devuelve un timestamp aumentado en "dias" dias
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t, la fecha usada como base
	 * @param dias, la cantidad de dias a aumentar
	 * @return un timestamp aumentado en "dias" dias
	 * @modelguid {AA1CEA63-0308-459E-80D4-8584C02765AC}
	 */
	public static Timestamp timestampAddDias(Timestamp t, int dias){
		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(new Date(t.getTime()));
		cal.add(Calendar.DATE, dias);
//		for (int i = 0; i < dias; i++) {
//			if (cal.get(Calendar.DATE) != cal.getActualMaximum(Calendar.DATE)) {
//				cal.roll(Calendar.DATE,true);
//			} else if (cal.get(Calendar.MONTH) != cal.getActualMaximum(Calendar.MONTH)) {
//				cal.roll(Calendar.MONTH, true);
//				cal.roll(Calendar.DATE, true);
//			} else {
//				cal.roll(Calendar.YEAR, true);
//				cal.roll(Calendar.MONTH, true);
//				cal.roll(Calendar.DATE, true);
//			}
//		}
		return (new Timestamp(cal.getTime().getTime()));
	}

	/**
	 * Este método devuelve un timestamp aumentado en "minutos" minutos
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t, la fecha usada como base
	 * @param minutos, la cantidad de minutos a aumentar
	 * @return un timestamp aumentado en "minutos" minutos
	 * @modelguid {F2695D9A-7135-409C-883C-42AC4C9DB4DD}
	 */
	public static Timestamp timestampAddMinutos(Timestamp t, int minutos){
		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(new Date(t.getTime()));
//		return (new Timestamp(cal.getTime().getTime() + minutos * NUMERO_BASE_RELOJ * NUMERO_BASE_MILISEGUNDOS));
		for (int i = 0; i < minutos; i++) {
			if (cal.get(Calendar.MINUTE) != cal.getActualMaximum(Calendar.MINUTE)) {
				cal.roll(Calendar.MINUTE, true);
			} else if (cal.get(Calendar.HOUR_OF_DAY) != cal.getActualMaximum(Calendar.HOUR_OF_DAY)) {
				cal.roll(Calendar.HOUR_OF_DAY, true);
				cal.roll(Calendar.MINUTE, true);
			} else if (cal.get(Calendar.DATE) != cal.getActualMaximum(Calendar.DATE)) {
				cal.roll(Calendar.DATE,true);
				cal.roll(Calendar.HOUR_OF_DAY, true);
				cal.roll(Calendar.MINUTE, true);
			} else if (cal.get(Calendar.MONTH) != cal.getActualMaximum(Calendar.MONTH)) {
				cal.roll(Calendar.MONTH, true);
				cal.roll(Calendar.DATE, true);
				cal.roll(Calendar.HOUR_OF_DAY, true);
				cal.roll(Calendar.MINUTE, true);
			} else {
				cal.roll(Calendar.YEAR, true);
				cal.roll(Calendar.MONTH, true);
				cal.roll(Calendar.DATE, true);
				cal.roll(Calendar.HOUR_OF_DAY, true);
				cal.roll(Calendar.MINUTE, true);
			}
		}
		return (new Timestamp(cal.getTime().getTime()));
	}

	/**
	 * Este método devuelve true o false dependiendo de la igualdad entre "t1" y "t2"
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t1, la fecha 1
	 * @param t2, la fecha 2
	 * @return true si t1 == t2, false caso contrario
	 * @modelguid {BB8BB42D-977F-46BC-B60F-37F70BBBF065}
	 */
	public static boolean timestampIsIgual(Timestamp t1, Timestamp t2) {
		return (t1.getTime() == t2.getTime());
	}

	/**
	 * Este método devuelve true o false dependiendo de quien sea el mayor entre "t1" y "t2"
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t1, la fecha que se espera sea menor
	 * @param t2, la fecha que se espera sea mayor
	 * @return true si t1 < t2, false caso contrario
	 * @modelguid {967821CE-0749-42A3-AA03-6F2241CBBCE0}
	 */
	public static boolean timestampIsMenor(Timestamp t1, Timestamp t2) {
		return (t1.getTime() < t2.getTime());
	}

	/**
	 * Este método devuelve true o false dependiendo de quien sea el mayor o igual entre "t1" y "t2"
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t1, la fecha que se espera sea menor o igual
	 * @param t2, la fecha que se espera sea mayor
	 * @return true si (t1 <= t2), false caso contrario
	 * @modelguid {BA8C71D0-376E-4F47-A202-090B6F0A7E33}
	 */
	public static boolean timestampIsMenorIgual(Timestamp t1, Timestamp t2){
		return (timestampIsIgual(t1, t2) || timestampIsMenor(t1, t2));
	}

	/**
	 * Este método devuelve true o false dependiendo de quien sea el mayor entre "t1" y "t2"
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t1, la fecha que se espera sea mayor
	 * @param t2, la fecha que se espera sea menor
	 * @return true si t1 > t2, false caso contrario
	 * @modelguid {0EC28730-80A1-4751-8626-F3839AF9D60E}
	 */
	public static boolean timestampIsMayor(Timestamp t1, Timestamp t2) {
		return (! timestampIsMenorIgual(t1, t2));
	}

	/**
	 * Este método devuelve true o false dependiendo de quien sea el mayor o igual entre "t1" y "t2"
	 * @author jespejo - Juan Carlos Espejo Gavilano
	 * @version v1.0, May 15, 2005
	 * @param t1, la fecha que se espera sea mayor o igual
	 * @param t2, la fecha que se espera sea menor
	 * @return true si (t1 >= t2), false caso contrario
	 * @modelguid {1D011CD7-0FCF-47BB-A057-C8C798EE87EC}
	 */
	public static boolean timestampIsMayorIgual(Timestamp t1, Timestamp t2){
		return (! timestampIsMenor(t1, t2));
	}

	/**
	Compares to Dates (in String format "dd/mm/yyyy")
	and returns an int with the possibles values:

	  -3 : DateA < DateB
	   0 : DateA = DateB
	  +2 : DateA > DateB

	 * @modelguid {CF6A5F27-D180-42C6-847E-15E3563E8DCD}
	*/

	public static int compare(String DateA, String DateB)
	{
		//Check if they are equal
		if (DateA.compareTo(DateB) == 0)
			{
			// DateA equals DateB
			return 0;
		}

		//Convert both Strings to Calendar Objects

		// extract the year, month and day
		String sYear = DateA.substring(6, 10);
		String sMonth = DateA.substring(3, 5);
		String sDay = DateA.substring(0, 2);

		int iYear = Integer.parseInt(sYear);
		int iMonth = Integer.parseInt(sMonth);
		int iDay = Integer.parseInt(sDay);

		iMonth--;
		java.util.Calendar calendarA = java.util.Calendar.getInstance();
		calendarA.set(iYear, iMonth, iDay);

		sYear = DateB.substring(6, 10);
		sMonth = DateB.substring(3, 5);
		sDay = DateB.substring(0, 2);

		iYear = Integer.parseInt(sYear);
		iMonth = Integer.parseInt(sMonth);
		iDay = Integer.parseInt(sDay);
		iMonth--;
		java.util.Calendar calendarB = java.util.Calendar.getInstance();
		calendarB.set(iYear, iMonth, iDay);

		// compare
		boolean result = calendarA.after(calendarB);

		if (result == true)
			{
			// DateA is greater than DateB
			return +2;
		}
		else
			{
			// DateB is greater than DateA
			return -3;
		}
	}
	/**
	 * Convert TimeStamp to a String in the format 'dd/mm/yyyy hh:mm:ss' (24 hour)
	 * @modelguid {3831B676-D5B1-45F3-858F-E8974F0BDAB9}
	 */
	public static String timestampToString(java.sql.Timestamp d)
	{
		return timestampToString(d,1);
	}

	/** @modelguid {7352B012-F363-42C0-9653-ED683E0CB44D} */
	public static String timestampToStringDDMMYYYY(java.sql.Timestamp d)
	{
		String fecha = timestampToString(d,0);
		if(fecha.equals("00/00/0000 00:00:00"))
			fecha = "";
		return fecha;
	}

	/**
	 * Convert TimeStamp to a String in the format 'dd/mm/yyyy hh:mm:ss' (24 hour)
	 * @modelguid {FE698ECE-C2C8-494D-AD04-8184E023062C}
	 */
	public static String timestampToString(java.sql.Timestamp d, int format)
	{
		if (d == null)
			return "00/00/0000 00:00:00";

		// convert to Calendar
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(d);

		return FechaUtil.calendarToString(cal, format);
	}

	/**
	 * Convert TimeStamp to a String in the format 'dd/mm/yyyy hh:mm:ss' (24 hour)
	 * @modelguid {11E65835-63D2-46C3-80F8-151A6B2AED08}
	 */
	public static String timestampToStringYYYYMMDD(java.sql.Timestamp d)
	{
		if (d == null)
			return "00/00/0000 00:00:00";

		// convert to Calendar
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(d);
		// Number Formaters
		java.text.DecimalFormat dformat = new java.text.DecimalFormat("00");
		java.text.DecimalFormat yformat = new java.text.DecimalFormat("0000");

		StringBuffer sb = new StringBuffer();

		//Remember: in Java, the first month is 0 (January)
		// that is why we must add 1 to the Calender.MONTH result
		sb.append(yformat.format(cal.get(java.util.Calendar.YEAR)));
		sb.append(dformat.format(cal.get(java.util.Calendar.MONTH) + 1));
		sb.append(dformat.format(cal.get(java.util.Calendar.DATE)));


		return sb.toString();
	}

	/**
	 * Convert java.util.Date to a String in the format 'dd/mm/yyyy'
	 * @modelguid {0EBD9F20-BA14-4E01-A417-D681E5321F9C}
	 */
	public static String dateToString(java.util.Date d)
	{
		if (d == null)
			return "00/00/0000";

		// convert Date to Calendar
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(d);

		return FechaUtil.calendarToString(cal,0);
	}
	/**
	 Returns a String of a Date in the format
	 "dd/mm/yyyy hh:mm:ss" of the Current Date (now)
	 * @modelguid {DBA55D69-13D5-4DB0-871C-6F73CB1CCEC1}
	 */
	public static String getCurrentDateTimeString()
	{
		// convert Date to Calendar
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return FechaUtil.calendarToString(cal,1);
	}
	/**
	 * Returns a String of a Date in the format
	 * " dd/mm/yyyy" of the Current Date (now)
	 * @return String date dd/mm/yyyy
	 * @modelguid {C627C1E8-A654-41B6-AB72-9645E4BE18AB}
	 */
	public static String getCurrentDateString( )
	{
		// convert Date to Calendar
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return FechaUtil.calendarToString(cal,0);
	}


	/**
	Returns the number of days between two dates
	(in String format "dd/mm/yyyy")

	 * @modelguid {EAAED09F-8114-47D9-81E7-A7E371666CC7}
	*/

	public static int getDaysBetween(String DateA, String DateB) throws Throwable
	{
		int cont = 0;

		// while (this.compare(DateA, DateB) != 2)

		while (DateA.compareTo(DateB) != 0)
			{
			cont++;
			DateA = FechaUtil.add(DateA, 1);
		}

		return cont;

	}
	/**
	  * This Method receives three parameters
	  *
	  *  - day
	  *  - month
	  *  - year
	  *
	  * And returns a String Date in the format "dd/mm/yyyy"
	  * @modelguid {BC31F39A-E08A-4A1B-9F62-FE2306DBFADA}
	 */

	public static String getStringDate(int dd, int mm, int yy)
	{
		StringBuffer sb = new StringBuffer();

		// Number Formaters
		java.text.DecimalFormat dformat = new java.text.DecimalFormat("00");
		java.text.DecimalFormat yformat = new java.text.DecimalFormat("0000");

		sb.append(dformat.format(dd));
		sb.append("/");
		sb.append(dformat.format(mm));
		sb.append("/");
		sb.append(yformat.format(yy));

		return sb.toString();
	}
	/*
	Receives a String in the format "dd/mm/yyyy hh:mm:ss" (24 hour) and
	returns a String in the format needed by Oracle.

	Example:
	  "09/02/2001 18:09:08"  ---->>> "to_date('2001-02-09 18:09:08','yyyy-mm-dd HH24:MI:SS')"
	 * @modelguid {B35468F6-B69C-4225-A70B-319F92B864ED}
	*/

	public static String stringTimeToOracleString(String s)
	{
		if (s.equals("00/00/0000 00:00:00"))
			return "''";

		StringBuffer sb = new StringBuffer();

		sb.append("to_date('");
		sb.append(s.substring(6, 10));
		sb.append("-");
		sb.append(s.substring(3, 5));
		sb.append("-");
		sb.append(s.substring(0, 2));
		sb.append(" ");
		sb.append(s.substring(11, 13));
		sb.append(":");
		sb.append(s.substring(14, 16));
		sb.append(":");
		sb.append(s.substring(17, 19));
		sb.append("','yyyy-mm-dd HH24:MI:SS')");

		return sb.toString();
	}
	/*
	Receives a String in the format "dd/mm/yyyy" and
	returns a String in the format needed by Oracle.

	Example:
		  "09/02/2001"  ---->>> "to_date('2001-02-09','yyyy-mm-dd')"
	 * @modelguid {B070707D-2647-4142-92F3-C712631C1BF6}
	*/

	public static String stringToOracleString(String s)
	{
		if (s.equals("00/00/0000"))
			return "''";

		StringBuffer sb = new StringBuffer();
		if (s.length() == 10)
		{
			sb.append("to_date('");
			sb.append(s.substring(6, 10));
			sb.append("-");
			sb.append(s.substring(3, 5));
			sb.append("-");
			sb.append(s.substring(0, 2));
			sb.append("','yyyy-mm-dd')");
		}
		return sb.toString();
	}
	/**
	Date Verification:
	 - Check for date format "dd/mm/yyyy"
	 - Also verifies that month is not greater than 12
	 - And day should not be greater than 31
	 * @modelguid {89817C2F-194D-4D65-8B85-279640BA890A}
	 */

	public static boolean verifyDate(String ind)
	{

		if (ind == null)
			return false;

		if (ind.length() != 10)
			return false;

		int year, month, day;
		String xyear, xmonth, xday;

		xyear = ind.substring(6, 10);
		xmonth = ind.substring(3, 5);
		xday = ind.substring(0, 2);

		try
			{
			year = Integer.parseInt(xyear);
			month = Integer.parseInt(xmonth);
			day = Integer.parseInt(xday);
		}
		catch (NumberFormatException e)
			{
			// invalid characters... return false
			return false;
		}

		if (month > 12 || month < 1)
			return false;

		if (day > 31 || day < 1)
			return false;

		// leap year
		boolean leap = false;
		if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0))
			leap = true;

		if ((month == 2) && (leap == true) && (day > 29))
		   return false;

		if ((month == 2) && (leap != true) && (day > 28))
		   return false;

		//specific days of the months
		if (   (day   > 31) && ( (month == 1) || (month == 3) || (month == 5) || (month == 7)  ||
			   (month == 8) || (month == 10) || (month == 12)   )  )
			return false;

		if (   (day > 30) && (  (month == 4) || (month == 6) || (month == 9) || (month == 11) )  )
			return false;

		// if all tests are ok... then return true
		return true;
	}

	/*
	Receives a String and
	returns a String in the format needed by Oracle.

	Example:
		  "SYSDATE"  ---->>> "to_date(sysdate,'yyyy-mm-dd')"
	 * @modelguid {B74DE652-F079-407A-90F2-A2387933B910}
	*/

	public static String stringToOracleString2(String s)
	{
		if (s.equals("00/00/0000"))
			return "''";

		StringBuffer sb = new StringBuffer();

		sb.append("to_date(");
		sb.append(s);
		sb.append(",'yyyy-mm-dd')");

		return sb.toString();
	}

	/** @modelguid {D511CDCC-314F-4B7B-8617-EC09055EF226} */
	public static Timestamp getCurrentTimestamp()
	{
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return new java.sql.Timestamp(cal.getTime().getTime());
	}

	/** @modelguid {E022364B-BA65-42D0-B712-40EE1F8CCC9D} */
	public static String getNameMonth(int i){
		String name = null;
		switch(i){
			case 0: name = "Enero"; break;
			case 1: name = "Febrero"; break;
			case 2: name = "Marzo"; break;
			case 3: name = "Abril"; break;
			case 4: name = "Mayo"; break;
			case 5: name = "Junio"; break;
			case 6: name = "Julio"; break;
			case 7: name = "Agosto"; break;
			case 8: name = "Septiembre"; break;
			case 9: name = "Octubre"; break;
			case 10: name = "Noviembre"; break;
			case 11: name = "Diciembre"; break;
		}
		return name;
	}
	
	
	public static String getNameDay(int i){
		String name = null;
		switch(i){
			case 1: name = "Domingo"; break;
			case 2: name = "Lunes"; break;
			case 3: name = "Martes"; break;
			case 4: name = "Miércoles"; break;
			case 5: name = "Jueves"; break;
			case 6: name = "Viernes"; break;
			case 7: name = "Sábado"; break;	
		}
		return name;
	}

	/** @modelguid {C0D87645-879B-4CDB-817E-C9C2C1666400} */
	public static void main(String argv[]){
		try{


//			FechaUtil.timestampIsMenorIgual(horaInicio, horaFin);
//			Timestamp t1 = stringToTimestamp("23/10/2005");
//			Timestamp t2 = stringHHmmToTimestamp("10:20");
//			t1 = timestampAddTimestampHora(t1, t2);
//			
			/*

			*/
			System.out.println(FechaUtil.TimestampToStringAAAAMMDD(FechaUtil.getCurrentTimestamp()));
			System.out.println(FechaUtil.TimestampToStringHHMMSS(FechaUtil.getCurrentTimestamp()));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*Metodo que recibe como parametro la fecha y te duvuelve la
		misma fecha en formato yyyymmdd utilizado despues para comparar
	 * @modelguid {D087A142-5E50-4347-AD35-FB4887A446DF}
	 */
	public static String convierteFecha(String fecha)
	{
			int year, month, day;
			String xyear, xmonth, xday;
			String nuevaFecha;
			int primeraSep = fecha.indexOf("/",0);
			int segundaSep = fecha.indexOf("/",primeraSep + 1);

			xyear = fecha.substring(segundaSep + 1, fecha.length());
			xmonth = fecha.substring(primeraSep + 1, segundaSep);
			if (xmonth.length()<=1)
			{
				xmonth = "0"+xmonth;
			}
			xday = fecha.substring(0, primeraSep);
			if (xday.length()<=1)
			{
				xday = "0"+xday;
			}
			nuevaFecha = xyear+xmonth+xday;

			return nuevaFecha;
	}
	/* Metodo que obtiene la cantidad de dias del mes segun año y mes
	 * @modelguid {5DE1AB4E-CC94-46B3-B0E7-77C5EAD4DA87}
	 */
	public static int cantidadDias(String mes, int ano) {
		int cantidadDias = 0;
		if(mes!=null) {
        
			if("ENERO".equals(mes.toUpperCase())) return 31;
			if("FEBRERO".equals(mes.toUpperCase()))
				if(ano%4==0)
					return 28;
				else
					return 29;
			if("MARZO".equals(mes.toUpperCase())) return 31;
			if("ABRIL".equals(mes.toUpperCase())) return 30;
			if("MAYO".equals(mes.toUpperCase())) return 31;
			if("JUNIO".equals(mes.toUpperCase())) return 30;
			if("JULIO".equals(mes.toUpperCase())) return 31;
			if("AGOSTO".equals(mes.toUpperCase())) return 31;
			if("SEPTIEMBRE".equals(mes.toUpperCase())) return 30;
			if("OCTUBRE".equals(mes.toUpperCase())) return 31;
			if("NOVIEMBRE".equals(mes.toUpperCase())) return 30;
			if("DICIEMBRE".equals(mes.toUpperCase())) return 31;
		}
		return cantidadDias;
	}
	/* Metodo que obtiene la cantidad de dias del mes segun año y mes
	 * @modelguid {AEC5280D-9DB1-4E22-B936-DCE73F4D0FB1}
	 */
	public static String numeroMes(String mes) {
		String numeroMes = "";
		if(mes!=null) {
			if("ENERO".equals(mes.toUpperCase())) return "01";
			if("FEBRERO".equals(mes.toUpperCase())) return "02";
			if("MARZO".equals(mes.toUpperCase())) return "03";
			if("ABRIL".equals(mes.toUpperCase())) return "04";
			if("MAYO".equals(mes.toUpperCase())) return "05";
			if("JUNIO".equals(mes.toUpperCase())) return "06";
			if("JULIO".equals(mes.toUpperCase())) return "07";
			if("AGOSTO".equals(mes.toUpperCase())) return "08";
			if("SEPTIEMBRE".equals(mes.toUpperCase())) return "09";
			if("OCTUBRE".equals(mes.toUpperCase())) return "10";
			if("NOVIEMBRE".equals(mes.toUpperCase())) return "11";
			if("DICIEMBRE".equals(mes.toUpperCase())) return "12";
		}
		return numeroMes;
	}
	/**
	 * Este metodo devuelve la fcha actual en el Formato de AAAA-MM-DD
	 * 
	 */
	public static String fechaActual() {
		String str;
		Timestamp today=getCurrentTimestamp();
		str=TimestampToStringAAAAMMDD(today);
		return str;
	}
	
	/**
	 * Este metodo devuelve la fecha en formato AAAA-MM-DD hh:mm
	 */
	public static String TimestampToStringAAAAMMDDhhmm(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	
	/**
	 * Este metodo devuelve el la fecha del ultimo dia de un determinado mes antes o despues de cierta fecha.
	 * El calculo de meses se hace utilizando numeros positivos o negativos.
	 * 
	 * @author fbustamante
	 * @param fecha
	 * @param meses
	 * @return
	 */
	public static java.util.Date fechaUltimoDia(java.util.Date fecha, int meses) {
		java.util.Date fechaUltimoDia;
		int anhos;
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(fecha);
		anhos = meses / 12;		
		if(anhos != 0) {
			meses = meses % 12;
		}
		cal.add(Calendar.YEAR,anhos);
		cal.add(Calendar.MONTH,meses);
		
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		fechaUltimoDia = cal.getTime();	
		return fechaUltimoDia;
	}
	
	/**
	 * Este metodo devuelve la fecha del ultimo dia de un determinado mes antes o despues de cierta fecha.
	 * El calculo de meses se hace utilizando numeros positivos o negativos.
	 * 
	 * @author fbustamante
	 * @param fecha
	 * @param meses
	 * @return
	 */
	public static Timestamp fechaUltimoDia(Timestamp fecha, int meses) {
		Timestamp fechaUltimoDia;
		int anhos;
		Calendar cal = Calendar.getInstance();
	
		cal.setTime(new Date(fecha.getTime()));
		anhos = meses / 12;		
		if(anhos != 0) {
			meses = meses % 12;
		}
		cal.add(Calendar.YEAR,anhos);
		cal.add(Calendar.MONTH,meses);
	
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		fechaUltimoDia = new Timestamp(cal.getTime().getTime());	
		return fechaUltimoDia;
	}
	
	public static String fechaActualEtiqueta(){
		
		Calendar cal = Calendar.getInstance();
		int dia = cal.get(Calendar.DATE);
		int mes = cal.get(Calendar.MONTH);
		int anio = cal.get(Calendar.YEAR);
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
		
		StringBuffer sb=new StringBuffer();
		sb.append(FechaUtil.getNameDay(diaSemana));
		sb.append(", ").append(dia).append(" de ").append(FechaUtil.getNameMonth(mes));
		sb.append( " del ").append(anio);
		
		return sb.toString();
		
	}
	
	
	/**
	 * Retorna el periodo en formato MMM YYYY
	 * @param periodo
	 * @return
	 */	public static String fechaFormato_MMM_YYYY(String periodo){
		
      
	   String anio=periodo.substring(0,4);
       String mes=periodo.substring(4,6);
       String nombreMes=getNameMonth(Integer.valueOf(mes).intValue()-1);
       
       StringBuffer sb=new StringBuffer();
       sb.append(nombreMes).append(" ").append(anio);
	   return sb.toString();
		
	}
	 
	/** @modelguid {AEC7A2B9-5E25-44AD-A4EA-DBB70B6B27BC} */
	/*
	 * Devuelve la fecha en formato yyyymm
	 * 
	 */
	public static String TimestampToStringAAAAMM(Timestamp t ){
		if( t == null ) return new String();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		Date data = new Date(t.getTime());
		return formatter.format(data);
		
	}
	 
	 /**
	  * Autor: lmedina 06/08/2007 01:52:22 PM
	  * Descripcion:Permite mostrar una lista de periodos en formato yyyymm usados para los calendarios
	  * del mes actual
	  * @param cantMeses Número de meses que se mostrarán hacia adelante y hacia atrás a partir del mes actual
	  * @return
	  */
	public static List obtenerPeriodosCalendario(int cantMeses){
			
	       Calendar cal=Calendar.getInstance();	
		   Parametro par;	
		   List lstPeriodo=new ArrayList();	
		   
		   cal.add(Calendar.MONTH, -cantMeses);	
		   String strMes=TextoUtil.codifica(new Long(cal.get(Calendar.MONTH)+1),2);
       	   String strAnio=String.valueOf(cal.get(Calendar.YEAR));
       	   par=new Parametro();
       	   par.setId(strAnio+strMes);
     	   par.setNombre(strAnio+strMes);
     	   lstPeriodo.add(par); 
		   for(int i=0;i<cantMeses*2;i++){
			  par=new Parametro();			 
			  cal.add(Calendar.MONTH, 1);			 				    
			  strMes=TextoUtil.codifica(new Long(cal.get(Calendar.MONTH)+1),2);
          	  strAnio=String.valueOf(cal.get(Calendar.YEAR));
          	  par.setId(strAnio+strMes);
          	  par.setNombre(strAnio+strMes);
          	  lstPeriodo.add(par);           	           	   
		   }	   		   		   
		   return lstPeriodo;			
		}
	
	
	/**
	 * Autor: lmedina 08/08/2007 09:58:51 AM
	 * Descripcion:
	 * @param cantMeses
	 * @return
	 */
	public static List obtenerPeriodosLogin(int cantMeses){
		
	       Calendar cal=Calendar.getInstance();	
		   Parametro par;	
		   List lstPeriodo=new ArrayList();	
		   
		   cal.add(Calendar.MONTH, -cantMeses);	
		   String strMes=TextoUtil.codifica(new Long(cal.get(Calendar.MONTH)+1),2);
    	   String strAnio=String.valueOf(cal.get(Calendar.YEAR));
    	   par=new Parametro();
    	   par.setId(strAnio+strMes);
  	       par.setNombre(strAnio+strMes);
  	       lstPeriodo.add(par); 
		   for(int i=0;i<=cantMeses;i++){
			  par=new Parametro();			 
			  cal.add(Calendar.MONTH, 1);			 				    
			  strMes=TextoUtil.codifica(new Long(cal.get(Calendar.MONTH)+1),2);
       	  strAnio=String.valueOf(cal.get(Calendar.YEAR));
       	  par.setId(strAnio+strMes);
       	  par.setNombre(strAnio+strMes);
       	  lstPeriodo.add(par);           	           	   
		   }	   		   		   
		   return lstPeriodo;			
		}
	/**
	 * Autor: lmedina 07/09/2007 07:52:38 PM
	 * Descripcion:Ojo suma solo al mes actual
	 * @param cantMeses
	 * @return
	 */
	public static Timestamp addMeses(int cantMeses){
		 Calendar cal=Calendar.getInstance();	
		 cal.add(Calendar.MONTH, cantMeses);		 
		return (new Timestamp(cal.getTime().getTime()));
	}
	
	public static String addMeses(String yyyymm,int cantMeses){
	    int anio=Integer.parseInt(yyyymm.substring(0,4));
	    int mes=Integer.parseInt(yyyymm.substring(4));
	   
	   Calendar cal=Calendar.getInstance();
	   cal.set(Calendar.MONTH, mes-1);
	   cal.set(Calendar.YEAR,anio);
	   cal.add(Calendar.MONTH, cantMeses);
	   
	   return TimestampToStringAAAAMM(new Timestamp(cal.getTime().getTime()));
	}
	
	/**
	 * Este metodo devuelve la fecha inicial o final para el manejo de reporte especifico, dependiendo del tipo de fecha.
	 * La fecha de inicio es igual a la fecha actual con 07:00 horas
	 * La fecha final es igual a un dia despues de la fecha actual con 07:00 horas
	 * 
	 * @author fbustamante
	 * @param fechaActual
	 * @param tipoFecha
	 * @return
	 */
	/*public static Timestamp obtenerFechaProduccion(Timestamp fechaActual, int tipoFecha) throws Exception{
		Timestamp fechaReporte = null, tmsAux, tmsHoraActividades;
				
		Calendar cal = Calendar.getInstance();
		Calendar calActividades = Calendar.getInstance();
		String strHoraInicio;
		
		if(fechaActual != null) {
			//tmsAux = stringToTimestamp(fechaActual);
			tmsAux=fechaActual;
			cal.setTime(new java.util.Date(tmsAux.getTime()));//fecha de busqueda
			
			//07:00
			strHoraInicio = SACFastDomainHelper.getInstance().getParametroSistema(new Long(ConstantesParametro.SISTEMA_HORA_INICIO_ACTIVIDADES)).getValor();			
			tmsHoraActividades = stringToTimestamp4(strHoraInicio);//Ejm 1970-01-01 07:00:00.0
			
			//De acá sólo me interesan las horas y minutos
			calActividades.setTime(new java.util.Date(tmsHoraActividades.getTime()));//Ejm 1970-01-01 07:00:00.0
			
			//A la fecha actual le seteo las horas y minutos leidas del sistema
			cal.set(Calendar.HOUR_OF_DAY,calActividades.get(Calendar.HOUR_OF_DAY));//07
			cal.set(Calendar.MINUTE,calActividades.get(Calendar.MINUTE));//00
			cal.set(Calendar.SECOND,calActividades.get(Calendar.SECOND));//00
			if(tipoFecha == FECHA_INICIO_ACTIVIDADES) {
				//nada
			}
			else if (tipoFecha == FECHA_FIN_ACTIVIDADES) {
				cal.add(Calendar.DAY_OF_MONTH,1);	// se le suma 1 dia a la fecha final
			}
			fechaReporte = new Timestamp(cal.getTime().getTime());
		}		
		
		return fechaReporte;
	}*/
	
}
