package pe.com.mmh.sisgap.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
/**
 * 
 * @modelguid {6A84A6C0-EAD3-423E-835B-EA626C7DDE90}
 * Funciones utilitarias para trabajar con números
 */
public class NumeroUtil
{
	/** @modelguid {35F55D83-A92E-4F3A-AF48-9F0081513DAE} */
	private static Logger flog = Logger.getLogger(NumeroUtil.class);

	/** @modelguid {C3EB7A3D-14C2-4A88-ADB7-F1ADAF38B3AB} */
	public static BigDecimal redondea(BigDecimal numero, int numeroDecimales){
		if(numero == null)
			return null;
		if(numeroDecimales > 0)
			return numero.setScale(numeroDecimales,BigDecimal.ROUND_HALF_UP);
		numeroDecimales = Math.abs(numeroDecimales);
		numero = divideDosNumeros(numero,new BigDecimal(Math.pow(10,numeroDecimales)));
		numero = numero.setScale(0,BigDecimal.ROUND_HALF_UP);
		numero = multiplicaDosNumeros(numero,new BigDecimal(Math.pow(10,numeroDecimales)));
		return numero.setScale(0,BigDecimal.ROUND_HALF_UP);		
	}
	
	//variables básicas
	/** @modelguid {3B0C3180-DC9F-4D74-A19F-3E97B4EEAC92} */
	private static DecimalFormatSymbols dSymbol = null;
	/** @modelguid {48AEC0CA-AEA9-463E-A163-207A2CB9F7E2} */
	private static void init()	
	{
		//INICIALIAR valores por defecto
		if (NumeroUtil.dSymbol==null)
			{
				NumeroUtil.dSymbol = new DecimalFormatSymbols();
				NumeroUtil.dSymbol.setDecimalSeparator('.');  // diferente aquí
				NumeroUtil.dSymbol.setGroupingSeparator(',');
			}	
	}
	
	/** @modelguid {05D0A2C9-DD47-4B93-87D2-EB498567E570} */
	public static double redondea(double d, int posiciones)
	{
		/* Método para redondear un double al número de posiciones
		 * decimales especificado
		 * 
		 * Ejemplo:
		 * 
		 * redondea(2.2375 , 3) --resulta--> 2.238
		 * 
		 */
		long factor = (long)Math.pow(10, posiciones);
		d = d * factor;
		long tmp2 = Math.round(d);
		return ((double)tmp2 / factor);
	}
	
	/**
	 * Convertir un double primitivo a un String
	 * indicando cantidad de cifras decimales
	 * Henry Tong - mayo2005
	 * @modelguid {81032985-3E4F-4A01-913A-4EA621ABD930}
	 */
	public static String redondeaAString(double d, int posiciones)
	{
		d = NumeroUtil.redondea(new BigDecimal(d),posiciones).doubleValue();
		posiciones = Math.abs(posiciones);
		NumeroUtil.init();
		
		StringBuffer patron = new StringBuffer("#0.");
		for (int i = 1; i <=posiciones; i++)
		{
			patron.append("0");
		}
		DecimalFormat myFormatter = new DecimalFormat();
		if (posiciones>0)
			myFormatter.applyPattern(patron.toString());
		else
			myFormatter.applyPattern("#0");
		myFormatter.setDecimalFormatSymbols(NumeroUtil.dSymbol);
		return myFormatter.format(d);
	}
	/** @modelguid {5B03E43C-01D6-4BC1-9075-2B3D4C7FFCA2} */
	public static String redondeaAString(Double D, int posiciones)
	{
		return NumeroUtil.redondeaAString(D.doubleValue(),posiciones);
	}	
	
	/**
	 * Convertir un BigDecimal a un String
	 * indicando cantidad de cifras decimales
	 * Henry Tong - mayo2005
	 * @modelguid {34FB668D-904A-4E3E-9A85-19A9F199ECAE}
	 */
	public static String redondeaAString(BigDecimal bd, int posiciones)
	{
		return ( bd.setScale(posiciones,BigDecimal.ROUND_HALF_UP) ).toString();
	}	
	
	/** @modelguid {E9004B93-50C3-494A-A804-E2EED2252874} */
	public static BigDecimal setScale(BigDecimal numero, int escala)
	{
		if (numero == null)
			return null;
		return numero.setScale(escala, BigDecimal.ROUND_HALF_UP);
	}
	
	/** @modelguid {44D3707E-3587-488B-A95A-EFAB53727D75} */
	public static BigDecimal calcularPorcentaje(BigDecimal dividendo, BigDecimal divisor)
	{
		return NumeroUtil.multiplicaDosNumeros(NumeroUtil.divideDosNumeros(dividendo, divisor), new BigDecimal(100));
	}
	
	/** @modelguid {5EF64791-06C2-429E-A941-F3A22D13411D} */
	public static BigDecimal multiplicaDosNumeros(String a, String b)
	{
		if (a != null && b != null && (!"".equals(a.trim()) || !"".equals(b.trim())))
		{
			if ("".equals(a.trim()))
				a = "0";
			if ("".equals(b.trim()))
				b = "0";
			BigDecimal producto = new BigDecimal(a).multiply(new BigDecimal(b));
			//producto = producto.setScale(6, BigDecimal.ROUND_HALF_UP);
			return producto;
		}
		else
		{
			return null;
		}
	}
	/** @modelguid {E4B2C283-12DB-4518-9403-532AC3EB7BA1} */
	public static Double stringDouble(String a)
	{
		Double dongs = null;
		if (a != null && !"".equals(a.trim()))
		{
			dongs = new Double(a.trim());
		}
		return dongs;
	}

	/** @modelguid {A0DBBD2B-7697-491D-8B55-3B511099EF78} */
	public static BigDecimal elevaPotencia(BigDecimal numero, int potencia){
		BigDecimal res=new BigDecimal(Math.pow(numero.doubleValue(),potencia));
		//res=res.setScale(6, BigDecimal.ROUND_HALF_UP);
		return res;
	}

	/** @modelguid {F5243DE1-985E-4F0A-90C6-5534C70658AB} */
	public static BigDecimal sacaRaiz(BigDecimal numero, int raiz){
		BigDecimal res=new BigDecimal(Math.pow(numero.doubleValue(),1d/raiz));
		//res=res.setScale(6, BigDecimal.ROUND_HALF_UP);
		return res;
	}

	/** @modelguid {956A8F47-FB5F-4826-87E7-ADDF32C63B34} */
	public static BigDecimal multiplicaDosNumeros(BigDecimal a, BigDecimal b)
	{
		if (a != null && b != null)
		{
			BigDecimal producto = a;
			producto = producto.multiply(b);
			//producto = producto.setScale(6, BigDecimal.ROUND_HALF_UP);
			return producto;
		}
		else
		{
			return null;
		}
	}
	/** @modelguid {8D74EDD5-C6BA-4F97-9465-F1091E57D099} */
	public static BigDecimal multiplicaDosNumeros(String a, BigDecimal b)
	{
		BigDecimal producto = null;
		if (a != null && !"".equals(a.trim()) && b != null)
		{
			producto = b;
			producto = producto.multiply(new BigDecimal(a.trim()));
			//  producto = producto.setScale(6, BigDecimal.ROUND_HALF_UP);
		}
		return producto;
	}
	/** @modelguid {63746192-05ED-4630-9CED-362CBBC73F0C} */
	public static BigDecimal sumaDosNumeros(String a, String b)
	{
		if (a != null)
			a = a.trim();
		if (b != null)
			b = b.trim();
		if ((a != null || b != null) && (!"".equals(a) || !"".equals(b)))
		{
			if (a == null)
				a = "0";
			if (b == null)
				b = "0";
			if ("".equals(a))
				a = "0";
			if ("".equals(b))
				b = "0";
			BigDecimal suma = new BigDecimal(a).add(new BigDecimal(b));
			// suma = suma.setScale(6, BigDecimal.ROUND_HALF_UP);
			return suma;
		}
		else
			return null;
	}
	/** @modelguid {5F737270-CF53-4ACE-910E-02A164003142} */
	public static BigDecimal sumaDosNumeros(BigDecimal a, BigDecimal b)
	{
		if (a != null || b != null)
		{
			if (a == null)
				a = new BigDecimal("0");
			if (b == null)
				b = new BigDecimal("0");
			BigDecimal suma = a;
			suma = suma.add(b);
			// suma = suma.setScale(6, BigDecimal.ROUND_HALF_UP);
			return suma;
		}
		else
			return null;
	}
	/** @modelguid {D01BEF86-4766-4D6A-8801-0B0FF12EC9AF} */
	public static BigDecimal sumaDosNumeros(String a, BigDecimal b)
	{
		if (a != null)
			a = a.trim();
		if ((a != null || b != null) && (!"".equals(a) || b != null))
		{
			if (a == null)
				a = "0";
			if ("".equals(a))
				a = "0";
			if (b == null)
				b = new BigDecimal("0");
			BigDecimal suma = new BigDecimal(a).add(b);
			// suma = suma.setScale(6, BigDecimal.ROUND_HALF_UP);
			return suma;
		}
		else
			return null;
	}
	/** @modelguid {DB9BD1E8-BEBF-422E-B2EA-AFC345884CD2} */
	public static BigDecimal restaDosNumeros(String minuendo, String substraendo)
	{
		if (minuendo != null)
			minuendo = minuendo.trim();
		if (substraendo != null)
			substraendo = substraendo.trim();
		if ((minuendo != null || substraendo != null) && (!"".equals(minuendo) || !"".equals(substraendo)))
		{
			if (minuendo == null)
				minuendo = "0";
			if (substraendo == null)
				substraendo = "0";
			if ("".equals(minuendo))
				minuendo = "0";
			if ("".equals(substraendo))
				substraendo = "0";
			BigDecimal resta = new BigDecimal(minuendo).subtract(new BigDecimal(substraendo));
			//resta = resta.setScale(6, BigDecimal.ROUND_HALF_UP);
			return resta;
		}
		else
			return null;
	}
	/** @modelguid {EF7E667B-50A2-4488-9474-C08757478D47} */
	public static BigDecimal restaDosNumeros(BigDecimal minuendo, BigDecimal substraendo)
	{
		if (minuendo != null || substraendo != null)
		{
			if (minuendo == null)
				minuendo = new BigDecimal("0");
			if (substraendo == null)
				substraendo = new BigDecimal("0");
			BigDecimal resta = minuendo;
			resta = resta.subtract(substraendo);
			//	   resta = resta.setScale(6, BigDecimal.ROUND_HALF_UP);
			return resta;
		}
		else
			return null;
	}
	/** @modelguid {9474ECC5-0922-4E6E-B9BB-E3084B4B3840} */
	public static BigDecimal divideDosNumeros(BigDecimal dividendo, BigDecimal divisor)
	{
		BigDecimal cociente = null;
		if (dividendo != null && divisor != null)
		{
			cociente = dividendo.divide(divisor, 20, BigDecimal.ROUND_HALF_EVEN);
		}
		return cociente;
	}
	/** @modelguid {42F817F3-DD23-42A6-B1C1-352995FC3AFA} */
	public static BigDecimal divideDosNumeros(String dividendo, BigDecimal divisor)
	{
		BigDecimal cociente = null;
		if (dividendo != null && !dividendo.trim().equals("") && divisor != null)
		{
			cociente = new BigDecimal(dividendo);
			cociente = cociente.divide(divisor, 10, BigDecimal.ROUND_HALF_EVEN);
		}
		return cociente;
	}
	/** @modelguid {86D37170-70D2-4341-B142-0DBE96A615C0} */
	public static boolean esMenor(BigDecimal primero, BigDecimal segundo)
	{
		if (primero == null)
			return true;
		if (segundo == null)
			return false;
		switch (primero.compareTo(segundo))
		{
			case -1 :
				return true;
			default :
				return false;
		}
	}
	/** @modelguid {E8F68F4C-8F44-482C-9970-F57EDCAD7BED} */
	public static boolean esMenor(BigDecimal primero, long segundo)
	{
		if (primero == null)
			return true;
		BigDecimal segundoB = new BigDecimal(segundo);
		switch (primero.compareTo(segundoB))
		{
			case -1 :
				return true;
			default :
				return false;
		}
	}
	/** @modelguid {F2FC2CD8-DD75-44C9-905B-29BCA6701518} */
	public static boolean esMayor(BigDecimal primero, BigDecimal segundo)
	{
		if (primero == null)
			return false;
		if (segundo == null)
			return true;
		switch (primero.compareTo(segundo))
		{
			case 1 :
				return true;
			default :
				return false;
		}
	}
	/** @modelguid {1E24B8A5-4DD5-48DA-B0FA-FA2695A0E73F} */
	public static boolean esMayor(double primero, BigDecimal segundo)
	{
		BigDecimal primero1 = new BigDecimal(primero);
		if (segundo == null)
			return true;
		switch (primero1.compareTo(segundo))
		{
			case 1 :
				return true;
			default :
				return false;
		}
	}
	/** @modelguid {A3417FDB-EDDA-4BCA-B00D-23E4E2D60822} */
	public static boolean esMayor(BigDecimal primero, int segundo)
	{
		BigDecimal segundoB = new BigDecimal(segundo);
		switch (primero.compareTo(segundoB))
		{
			case 1 :
				return true;
			default :
				return false;
		}
	}
	/** @modelguid {6B9F3CB0-2561-4749-A434-8DABB3605859} */
	public static Long stringToLong(String string)
	{
		Long longs = null;
		if (string != null && !"".equals(string.trim()))
		{
			longs = new Long(string.trim());
		}
		return longs;
	}
	/** @modelguid {50E60BDC-A99A-4BD9-891A-1A29ECF8BDAB} */
	public static BigDecimal stringToBigDecimal(String string)
	{
		BigDecimal longs = null;
		if (string != null && !"".equals(string.trim()))
		{
			longs = new BigDecimal(string.trim());
		}
		return longs;
	}
	/**
	 * @param string
	 * @return
	 * @modelguid {FCBEE713-05C3-493C-B385-3D8A71F2BE74}
	 */
	public static String eliminaDecimales(String string)
	{
		if (string != null && string.indexOf(".") > 0)
			return string.substring(0, string.indexOf("."));
		return string;
	}
	/** @modelguid {F32B02E6-BBCA-4A21-B02A-BC331C8E9DC5} */
	public static String eliminaDecimales(double d)
	{
		return NumeroUtil.eliminaDecimales(String.valueOf(d));
	}
	/** @modelguid {C45AABA3-4E72-4867-8D16-356101CF2C97} */
	public static boolean isInt(String string)
	{
		try
		{
			Integer.parseInt(string);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	/** @modelguid {18F20FA6-EE88-4EFC-8DEE-F7C180DA9344} */
	public static Long sumaDosNumeros(Long long1, Long long2)
	{
		long l1 = 0;
		long l2 = 0;
		if (long1 != null)
			l1 = long1.longValue();
		if (long2 != null)
			l2 = long2.longValue();
		return new Long(l1 + l2);
	}
	/** @modelguid {473BEEC4-DDA8-4B74-B266-2DA2EF824BEE} */
	public static Long restaDosNumeros(Long long1, Long long2)
	{
		long l1 = 0;
		long l2 = 0;
		if (long1 != null)
			l1 = long1.longValue();
		if (long2 != null)
			l2 = long2.longValue();
		return new Long(l1 - l2);
	}
	/** @modelguid {C9755209-D5E9-40D9-90EC-2E8A083CFD51} */
	public static Double sumaDosNumeros(Double double1, Double double2)
	{
		double d1 = 0;
		double d2 = 0;
		if (double1 != null)
			d1 = double1.doubleValue();
		if (double2 != null)
			d2 = double2.doubleValue();
		return new Double(d1 + d2);
	}
	/** @modelguid {7284DB69-0EF9-4D4E-9AD9-2151CCD3D3FF} */
	public static Double restaDosNumeros(Double double1, Double double2)
	{
		double d1 = 0;
		double d2 = 0;
		if (double1 != null)
			d1 = double1.doubleValue();
		if (double2 != null)
			d2 = double2.doubleValue();
		return new Double(d1 - d2);
	}
	

	/**
	 * obtiene el maximo común divisor de varios números
	 * Henry Tong - marzo 2005
	 * @modelguid {31E0EC96-375A-46DD-8381-EFE7A014BAA3}
	 */
	public static int maximoComunDivisor(int[] numeros)
	{
		/*  Este método obtiene el máximo común divisor (MCD) de dos números
		 *  Primero hay que comprender la teoría matemática, que es la 
		 *  siguiente: El MCD de varios números es igual a la intersercción
		 *  de los factores comunes de dichos números, después de haberlos
		 *  descompuesto en sus factores primos.
		 * 
		 *  Ejemplo: Hallar el MCD de (10, 50, 160)
		 * 
		 *  Resolución:
		 * 
		 *    Factores de 10  = 5 * 2
		 *    Factores de 50  = 5 * 5 * 2
		 *    Factores de 160 = 2 * 2 * 2 * 2 * 2 * 5
		 * 
		 *  Entonces el MCD es: 2 * 5 = 10
		 * 
		 */
		java.util.ArrayList alis = new java.util.ArrayList();
		//primero paso: reducir cada número en sus factores primos,
		//cada grupo de factores se guarda
		for (int i = 0; i < numeros.length; i++)
		{
			int[] fac = NumeroUtil.factorizar(numeros[i]);
			if (flog.isDebugEnabled())
			{
				StringBuffer sb = new StringBuffer();
				sb.append(" Factores de ").append(numeros[i]).append(" :");
				for (int j = 0; j < fac.length; j++)
					sb.append(" ").append(fac[j]);
				flog.debug(sb.toString());
			}
			alis.add(fac);
		}
		java.util.ArrayList lisFactoresFinales = new java.util.ArrayList();
		//segundo paso: buscar los factores comunes
		//para esto se toma cualquier grupo de factores de
		//cualquier número (tomaremos el primero por conveniencia)
		//y buscaremos factores comunes en el resto de grupos
		HashMap hm = null;
		//tomar primer grupo
		int[] grupouno = (int[])alis.get(0);
		//barrer cada elemento del primer grupo
		for (int i = 0; i < grupouno.length; i++)
		{
			//tomar un factor
			int factor = grupouno[i];
			hm = new HashMap();
			//buscar ese factor en cada uno de los otros grupos
			boolean esFactorFinal = true;
			for (int j = 1; j < alis.size(); j++)
			{
				//tomar otro grupo
				int[] otrogrupo = (int[])alis.get(j);
				//buscar el número en ese grupo
				int resultadoBusqueda = NumeroUtil.buscaEnArreglo(factor, otrogrupo);
				if (resultadoBusqueda < 0)
				{
					//el número no existe en ese grupo, no es necesario
					//seguir buscando
					esFactorFinal = false;
					break;
				}
				else
				{
					//el número fue encontrado en ese grupo; pero hay que buscar
					//en los otros grupos también, mientras tanto vamos guardando
					//en qué posición lo encontramos
					//->El factor "factor" fue encontrado en el grupo "j" en la posición "resultadoBusqueda"
					hm.put(new Integer(j), new Integer(resultadoBusqueda));
				}
			} //for j
			if (esFactorFinal)
			{
				//si entra aquí es porque el número fue encontrado en TO_DOS los grupos
				//por lo tanto es un factor del resultado final
				//hay que guardar el factor final en el arreglo final
				lisFactoresFinales.add(new Integer(factor));
				//además hay que "borrar" ese factor de todos los grupos, para que ya no sea
				//tomado en la siguiente búsqueda
				Iterator ite = hm.keySet().iterator();
				while (ite.hasNext())
				{
					Integer grpx = (Integer)ite.next();
					Integer posx = (Integer)hm.get(grpx);
					//"borrar" el factor del grupo "grpx" en la posicíon "posx"
					int[] grupox = (int[])alis.get(grpx.intValue());
					grupox[posx.intValue()] = -9999;
				}
			}
		} //for i
		//el resultado final es la multiplicación de los factores comunes
		int resultado = 1;
		if (flog.isDebugEnabled())
		{
			if (lisFactoresFinales.size() == 0)
			{
				flog.debug(" no nay factores finales");
			}
			else
			{
				StringBuffer sb = new StringBuffer();
				sb.append(" Factores finales :");
				for (int j = 0; j < lisFactoresFinales.size(); j++)
					sb.append(" ").append(lisFactoresFinales.get(j));
				flog.debug(sb.toString());
			}
		}
		if (lisFactoresFinales.size() == 0)
			return resultado;
		for (int i = 0; i < lisFactoresFinales.size(); i++)
		{
			Integer f = (Integer)lisFactoresFinales.get(i);
			resultado = resultado * f.intValue();
		}
		return resultado;
	}
	/**
	 * busca un número en un arreglo de números
	 * 
	 * devuelve la posición si encuentra el número (la posición será un número
	 * mayor o igual a cERO)
	 * 
	 * devuelve un número negativo si no lo encuentra.
	 * 
	 * Henry Tong - marzo 2005
	 * @modelguid {5495FD8B-3305-4D68-9662-7EF16BDAD4C8}
	 */
	public static int buscaEnArreglo(int numero, int[] arreglo)
	{
		for (int i = 0; i < arreglo.length; i++)
		{
			if (arreglo[i] == numero)
				return i;
		}
		return -9;
	}
	/**
	 * factorizar un número en sus números primos
	 * Henry Tong - marzo 2005
	 * @modelguid {4E7987C9-C0BE-41E6-B116-BA023D98974F}
	 */
	public static int[] factorizar(int numero)
	{
		//obtener los factores de un número
		//ejemplo: 60 = 2*2*5*3
		boolean flag1 = true;
		int w;
		java.util.ArrayList alisf = new java.util.ArrayList();
		while (flag1)
		{
			for (int i = 2; i <= numero; i++)
			{
				w = numero % i;
				if (w == 0)
				{
					//encontré un factor
					alisf.add(new Integer(i));
					numero = (numero / i);
					if (numero == 1)
					{
						//romper while
						flag1 = false;
					}
					//romper for
					break;
				}
			}
		}
		int[] resultado = new int[alisf.size()];
		for (int i = 0; i < alisf.size(); i++)
		{
			Integer iii = (Integer)alisf.get(i);
			resultado[i] = iii.intValue();
		}
		return resultado;
	}
	/**
	 * 
	 * @return la desviación estándar de un conjunto de valores
	 * @exception error
	 * @modelguid {4E885F38-27DC-465D-A5C1-97E776B77211}
	 */
	public static BigDecimal desviacionEstandar(BigDecimal[] numeros) {
		BigDecimal promedio = NumeroUtil.promedio(numeros);
		int elementos = NumeroUtil.contarElementos(numeros);
		if(elementos == 1)
			return new BigDecimal(0);
		elementos--;	
		BigDecimal inversa = NumeroUtil.divideDosNumeros(new BigDecimal(1),new BigDecimal(elementos));
		BigDecimal desviacion = null;
		if(numeros==null)
			return desviacion;
		BigDecimal sumaCuadrados = null;	
		for(int i = 0; i < numeros.length; i++){
			sumaCuadrados = NumeroUtil.sumaDosNumeros(sumaCuadrados,NumeroUtil.elevaPotencia(NumeroUtil.restaDosNumeros(numeros[i],promedio),2));
		}
		desviacion = NumeroUtil.sacaRaiz(NumeroUtil.multiplicaDosNumeros(inversa,sumaCuadrados),2);
		return desviacion;	
	}

	/**
	 * 
	 * @return el coeficiente de variación de un conjunto de números
	 * @exception error
	 * @modelguid {29A35621-AB3C-430E-8F85-907C3BAE34CE}
	 */
	public static BigDecimal coeficienteVariacion(BigDecimal[] numeros){
		if(numeros==null)
			return null;
		if(NumeroUtil.promedio(numeros).compareTo(new BigDecimal(0))==0)
			return null;	
		BigDecimal coeficienteVariacion = NumeroUtil.divideDosNumeros(NumeroUtil.desviacionEstandar(numeros),NumeroUtil.promedio(numeros));	
		return coeficienteVariacion;
	}

	/**
	 * 
	 * @return devuelve el valor mínimo de un conjunto de números
	 * @exception error
	 * @modelguid {E3658D59-7463-4AB3-BBA3-77CFFD35AAFE}
	 */
	public static BigDecimal valorMinimo(BigDecimal[] numeros) {
		if(numeros==null)
			return null;
		BigDecimal temp = numeros[0];
		for(int i = 1 ; i < numeros.length; i++){
			if(NumeroUtil.esMenor(numeros[i],temp)){
				temp = numeros[i];
			}
		}
		return temp;
	}

	/**
	 * 
	 * @return devuelve número mayor de un conjunto de números
	 * @exception error
	 * 
	 * @modelguid {964AC030-7E94-49C1-86F1-A74D7BB40804}
	 */
	public static BigDecimal valorMaximo(BigDecimal[] numeros) {
		if(numeros==null)
			return null;
		BigDecimal temp = numeros[0];
		for(int i = 1 ; i < numeros.length; i++){
			if(NumeroUtil.esMayor(numeros[i],temp)){
				temp = numeros[i];
			}
		}
		return temp;
	}

	/**
	 * 
	 * @return devuelve el promedio de un conjunto de números
	 * @exception error
	 * @modelguid {067D1D53-41DE-4366-8846-F87339CB1855}
	 */
	public static BigDecimal promedio(BigDecimal[] numeros) {
		if(numeros==null)
			return null;
		BigDecimal temp = null;
		for(int i = 0 ; i < numeros.length; i++){
			temp = NumeroUtil.sumaDosNumeros(temp,numeros[i]);
		}
		temp = NumeroUtil.divideDosNumeros(temp,new BigDecimal(numeros.length));
		return temp;
	}

	/**
	 * @return número de elementos del array
	 * @exception error
	 * 
	 * @modelguid {966CD70B-D6BF-4C8D-8FDC-5261DC59BB88}
	 */
	public static int contarElementos(BigDecimal[] numeros) {
		if(numeros==null)
			return 0;
		return numeros.length;	
	}

}

