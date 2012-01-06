package pe.com.mmh.sisgap.util;

import java.sql.*;

/**  Métodos utilitarios SQL
 */
public class JDBCUtil
{
	/**
	 * Method close
	 * @param conn
	 */
	public static void close(Connection conn)
	{
		if (conn==null)
			return;
		try
		{
			conn.close();
		}
		catch (Exception sqle)
		{
		}
	}
	
	/**
	 * Method close
	 * @param cstmt
	 */
	public static void close(CallableStatement cstmt)
	{
		if (cstmt==null)	
			return;
		try
		{  
			cstmt.close();
		}
		catch (Exception sqle)
		{
		}
	}
	/**
	 * Method close
	 * @param stmt
	 */
	public static void close(Statement stmt)
	{
		if (stmt==null)
			return;
		try
		{
			stmt.close();
		}
		catch (Exception sqle)
		{
		}
	}
	/**
	 * Method close
	 * @param p
	 */
	public static void close(PreparedStatement p)
	{
		if (p==null)
			return;
		try
		{
			p.close();
		}
		catch (Exception sqle)
		{
		}
	}
	/**
	 * Method close
	 * @param rs
	 */
	public static void close(ResultSet rs)
	{
		if (rs==null)
			return;
		try
		{
			rs.close();
		}
		catch (Exception sqle)
		{
		}
	}
	/**
	 * Method rollback
	 * @param conn
	 */
	public static void rollback(Connection conn)
	{
		if (conn==null)
			return;
		try
		{
			conn.rollback();
		}
		catch (Exception sqle)
		{
		}
	}
	/**
	 * permite hacer un setDouble al PreparedStatement introduciendo un String ya sea esta nulla o double 
	 * asi nos evitamos estar pensando si la cadena tiene un <b>digito</b> un <b>vacio</b> o un <b>null</b>
	 * @param cstmt
	 * @param indice
	 * @param valor
	 * @throws Exception
	 */
	public static void setDouble(PreparedStatement cstmt,int indice, String valor) 
	throws SQLException{
		if (valor != null) valor = valor.trim( ); 
		if (valor == null || "".equals(valor)) {
			cstmt.setNull(indice, Types.DOUBLE);
		} else {
			cstmt.setDouble(indice, Double.parseDouble(valor));
		}
	}
	
	/**
	 * Method setDouble
	 * @param cstmt
	 * @param indice
	 * @param valor
	 * @throws SQLException
	 */
	public static void setDouble(PreparedStatement cstmt,int indice, Double valor) 
	throws SQLException{
		if (valor == null) {
			cstmt.setNull(indice, Types.DOUBLE);
		} else {
			cstmt.setDouble(indice, valor.doubleValue( ));
		}
	}
	
	
	public static void setInteger(PreparedStatement cstmt,int indice, Integer valor) 
	throws SQLException{
		  
		if (valor == null ) {
			cstmt.setNull(indice, Types.INTEGER);
		} else {
			cstmt.setInt(indice, valor.intValue());
		}
	}
	

	/**
	 * permite hacer un setLong al PreparedStatement introduciendo un Long ya sea esta null  o Double 
	 * asi nos evitamos estar pensando si la cadena tiene un <b>digito</b> un <b>vacio</b> o un <b>null</b>
	 * @param cstmt
	 * @param indice
	 * @param valor
	 * @throws Exception
	 */
	public static void setLong(PreparedStatement cstmt,int indice, Long valor) 
	throws SQLException{
		  
		if (valor == null ) {
			cstmt.setNull(indice, Types.BIGINT);
		} else {
			cstmt.setLong(indice, valor.longValue( ));
		}
	}
	
	/**
	 * Method setLong
	 * @param cstmt
	 * @param indice
	 * @param valor
	 * @throws SQLException
	 */
	public static void setLong(PreparedStatement cstmt,int indice, String valor) 
	throws SQLException{
		  
		if (valor != null) valor = valor.trim( ); 
		if (valor == null || "".equals(valor)) {
			cstmt.setNull(indice, Types.BIGINT);
		} else {
			cstmt.setDouble(indice, Long.parseLong(valor));
		}
	}
	/**
	 * obtiene el objeto Double de una columna, si es nulo retorna null
	 * @param rs
	 * @param columna
	 * @return Double
	 * @throws SQLException
	 */
	public static Double getDouble(ResultSet rs, String columna)
	throws SQLException{
		double valor = 0.0;
		Double dbValor = null;
		
		valor = rs.getDouble(columna);
		if(!rs.wasNull( )){
		 dbValor = new Double(valor);
		}
		return dbValor;
	}
	
 
 

	/**
	 * obtiene el valor Long de una columna si es nulo retorna null
	 * @param rs
	 * @param columna
	 * @throws SQLException
	 */
	public static Long getLong(ResultSet rs, String columna)
	throws SQLException{
		long valor = 0;
		Long lValor = null;
		
		valor = rs.getLong(columna);
		if(!rs.wasNull( )){
			lValor = new Long(valor);
		}
		return lValor ;
	}
	
	
	/**
	 * obtiene el valor Integer de una columna si es nulo retorna null
	 * @param rs
	 * @param columna
	 * @throws SQLException
	 */
	public static Integer getInteger(ResultSet rs, String columna)
	throws SQLException{
		long valor = 0;
		Integer lValor = null;
		
		valor = rs.getLong(columna);
		if(!rs.wasNull( )){
			lValor = new Integer((new Long(valor)).intValue());
		}
		return lValor ;
	}
	
	/**
	 * in gresa una fecha del tipo String 
	 * @param cstmt
	 * @param indice
	 * @param valor
	 * @throws Exception
	 */
	public static void setTimestamp(PreparedStatement cstmt,int indice, String valor) 
	throws Exception{
	  
		if (valor != null) valor = valor.trim( ); 
		if (valor == null || "".equals(valor)) {
			cstmt.setNull(indice, Types.TIMESTAMP);
		} else {
			cstmt.setTimestamp(indice, FechaUtil.stringToTimestamp(valor));
		}
	}

	/**
	 * Method setTimestamp
	 * @param cstmt
	 * @param indice
	 * @param valor
	 * @throws Exception
	 */
	public static void setTimestamp(PreparedStatement cstmt,int indice, Timestamp valor) 
	throws Exception{
	  
		if (valor == null ) {
			cstmt.setNull(indice, Types.TIMESTAMP);
		} else {
			cstmt.setTimestamp(indice, valor);
		}
	}
	
	/*
	 * Devuelve el valor formateado para aumentar a un query en la parte de filtro
	 * WHERE campo LIKE valordevuelto
	 * */
	/**
	 * Method getLikeCons
	 * @param fieldValue
	 * @return String
	 */
	public static String getLikeCons(String fieldValue){
		String param="";
		
		if(fieldValue!=null)
			param=fieldValue;
			
		return "%"+param.toUpperCase()+"%";
	}
}