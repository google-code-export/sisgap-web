/*
 * Created on 14/04/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.com.mmh.sisgap.utils;

import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.ArrayUtils;

public class JDBCUtil {
    
    public static Object callSQLFunc(Connection con, String sql, Object[] params)
    throws Exception{
    	
	    CallableStatement _cs = null;	    
	    System.out.println("SQL = " + sql );
	    System.out.println( "params: " + ArrayUtils.toString( params ) );
	    
	    try{

	    _cs = con.prepareCall(sql);
	    for (int i = 1; (params != null) && (i <= params.length); i++) {
		    if (params[i - 1] == null) {
		    	_cs.setObject(i + 1, "");
		    } else {
		    	_cs.setObject(i + 1, params[i - 1]);
		    }
	    }
	    _cs.registerOutParameter(1,OracleTypes.VARCHAR);
	    _cs.execute();
	    return _cs.getObject(1);
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    throw new Exception("Error en callSQLFunc." , ex);
	
	    }finally{
		    //if(_cs!=null)_c 
		    //if(_rs!=null)_r 
		    }
    }    
    
    /**
     * Ejecuta un procedimiento almacenado 
     * @param con
     * @param sql
     * @param params
     * @throws Exception
     */
    public static void callSQLProcExec(Connection con, String sql, Object[] params)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
               
        CallableStatement cs = null;

		//System.out.println("sql: " + sql );
		//System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, Types.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
			cs.execute();
        }catch(Exception ex){
        	
            throw new Exception(ex.getMessage());            
        }finally{            
            if(cs!=null)cs.close();        
        }       
        
    }
    /**
     * Ejecuta un procedimiento almacenado 
     * @param con
     * @param sql
     * @param params
     * @throws Exception
     */
    public static void callSQLProcExecute(Connection con, String sql, Object[] params)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
               
        CallableStatement cs = null;

		System.out.println("sql: " + sql );
		System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, OracleTypes.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
			cs.execute();
        }catch(Exception ex){
        	
            throw new Exception(ex.getMessage());            
        }finally{            
            //if(cs!=null)c        
        }       
        
    }    
	/**
     * Ejecuta un procedimiento almacenado y retorna el resultado en un RowSetDynaClass 
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
	 */
    public static RowSetDynaClass callSQLProc(Connection con, String sql, Object[] params)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
               
        CallableStatement cs = null;
        ResultSet rs = null;

		//System.out.println("SQL = " + sql );
		//System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, Types.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
            rs = cs.executeQuery();
            RowSetDynaClass rdc = new RowSetDynaClass(rs);
           
            return rdc;
        }catch(Exception ex){            
            throw new Exception(ex.getMessage());            
        }finally{            
            //if(cs!=null)c 
            //if(rs!=null)r             
        }       
        
    }	
    
	/**
     * Ejecuta un procedimiento almacenado y retorna el resultado en un RowSetDynaClass 
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
	 */
    public static RowSetDynaClass callSQLProcRSDC(Connection con, String sql, Object[] params)
    throws Exception{  
        
    	       
        CallableStatement _cs = null;
        ResultSet _rs = null;

		System.out.println("SQL = " + sql );
		System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
        	_cs = con.prepareCall(sql);
            for (int i = 1; (params != null) && (i <= params.length); i++) {
                if (params[i - 1] == null) {
                	_cs.setObject(i + 1, "");
                } else {
                	//_cs.setObject(i + 1, params[i]);
                	_cs.setObject(i + 1, params[i - 1]);
                	
                }
            }
            _cs.registerOutParameter(1,OracleTypes.CURSOR);
            _cs.execute();
            _rs = (ResultSet)_cs.getObject(1);
            RowSetDynaClass rdc = new RowSetDynaClass(_rs);
            return rdc;
        }catch(Exception ex){
        	ex.printStackTrace();
            throw new Exception(ex.getMessage());
            
        }finally{            
            //if(_cs!=null)_c 
            //if(_rs!=null)_r             
        }
    }    
    
    
    /**
     * Ejecuta un procedimiento almacenado y retorna el resultado en un RowSetDynaClass 
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
	 */
    public static Object callSQLProcObject(Connection con, String sql, Object[] params)
    throws Exception{  
        
               
        CallableStatement _cs = null;
        Object _obj = null;

		System.out.println("SQL = " + sql );
		System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
        	_cs = con.prepareCall(sql);
            for (int i = 1; (params != null) && (i <= params.length); i++) {
                if (params[i - 1] == null) {
                	_cs.setObject(i + 1, "");
                } else {
                	_cs.setObject(i + 1, params[i - 1]);
                }
            }
            _cs.registerOutParameter(1,OracleTypes.INTEGER);
            _cs.execute();
            _obj = _cs.getObject(1);
            
            return _obj;
        }catch(Exception ex){
        	ex.printStackTrace();
            throw new Exception(ex.getMessage());
            
        }finally{            
            //if(_cs!=null)_c         
        }
    }    
    
    
    
    
    
	/**
     * Ejecuta un procedimiento almacenado y retorna el resultado en un ResultSet 
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
	 */
    public static ResultSet callSQLProcRS(Connection con, String sql, Object[] params)
    throws Exception{  
        
               
        CallableStatement _cs = null;
        ResultSet _rs = null;

		//System.out.println("SQL = " + sql );
		//System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
        	_cs = con.prepareCall(sql);
            for (int i = 1; (params != null) && (i <= params.length); i++) {
                if (params[i - 1] == null) {
                	_cs.setObject(i + 1, "");
                } else {
                	_cs.setObject(i + 1, params[i - 1]);
                }
            }
            _cs.registerOutParameter(1,OracleTypes.CURSOR);
            _cs.execute();
            _rs = (ResultSet)_cs.getObject(1);
            //RowSetDynaClass rdc = new RowSetDynaClass(_rs);
            return _rs;
        }catch(Exception ex){

            throw new Exception(ex.getMessage());
            
        }finally{            
            //if(_cs!=null)_c 
            //if(_rs!=null)_r             
        }
    }    
    
    
    public static String callSQLProcRSString(Connection con, String sql, Object[] params)
    throws Exception{  
        
               
        CallableStatement _cs = null;
        String _rs = null;

		//System.out.println("SQL = " + sql );
		//System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
        	_cs = con.prepareCall(sql);
            for (int i = 1; (params != null) && (i <= params.length); i++) {
                if (params[i - 1] == null) {
                	_cs.setObject(i + 1, "");
                } else {
                	_cs.setObject(i + 1, params[i - 1]);
                }
            }
            _cs.registerOutParameter(1,OracleTypes.VARCHAR);
            _cs.execute();
            _rs = (String)_cs.getObject(1);
            //RowSetDynaClass rdc = new RowSetDynaClass(_rs);
            return _rs;
        }catch(Exception ex){

            throw new Exception(ex.getMessage());
            
        }finally{            
            //if(_cs!=null)_c 
            //if(_rs!=null)_r             
        }
    } 
	/**
     * Ejecuta un procedimiento almacenado y retorna el resultado en un RowSetDynaClass 
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
	 */
    public static ResultSet callSQLProcResultset(Connection con, String sql, Object[] params)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
               
        CallableStatement cs = null;
        ResultSet rs = null;

		//System.out.println("SQL = " + sql );
		//System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, Types.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
            rs = cs.executeQuery();
                       
            return rs;
        }catch(Exception ex){            
            throw new Exception(ex.getMessage());            
        }finally{            
            //if(cs!=null)c 
            //if(rs!=null)r             
        }       
        
    }
    
    public static RowSetDynaClass callSQLProc(Connection con, String sql, Object[] params, Integer from,Integer even)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
               
        CallableStatement cs = null;
        ResultSet rs = null;

		//System.out.println("SQL = " + sql );
		//System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, Types.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
            rs = cs.executeQuery();
            RowSetDynaClass rdc = new RowSetDynaClass(rs,from,even);
           
            return rdc;
        }catch(Exception ex){            
            throw new Exception(ex.getMessage());            
        }finally{            
            //if(cs!=null)c 
            //if(rs!=null)r             
        }       
        
    }	
    
    
	/**
     * Ejecuta un procedimiento almacenado y retorna una Lista de RowSetDynaClass 
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
	 */
	public static ArrayList<RowSetDynaClass> callSQLProcRowSet(Connection con, String sql, Object[] params)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
        ArrayList<RowSetDynaClass> list= new ArrayList<RowSetDynaClass>();       
        CallableStatement cs = null;
        ResultSet rs = null;
		
		System.out.println("SQL = " + sql );
		System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, Types.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
			rs=cs.executeQuery();
			list.add(new RowSetDynaClass(rs));
            //cerrar el resultset, antes de asignar otro resultset
            rs.close();
			while(cs.getMoreResults()){
				rs=cs.getResultSet();
				list.add(new RowSetDynaClass(rs));				
			}
            return list; 
        }catch(Exception ex){
        	ex.printStackTrace();
			throw new Exception(ex.getMessage());            
        }finally{            
            //if(cs!=null)c 
            //if(rs!=null)r             
        }
    }	
    
    /**
     * Ejecuta un procedimiento  almacenado y retorna una Lista de ResultSets
     * @param con
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static ArrayList callSQLProcRs(Connection con, String sql, Object[] params)
    throws Exception{  
        
        int out[] = new int[params.length];
        int outcount=0;
        ArrayList list= new ArrayList();       
        CallableStatement cs = null;
        ResultSet rs = null;
		
		System.out.println("SQL = " + sql );
		System.out.println( "params: " + ArrayUtils.toString( params ) );
        try{            
            cs = con.prepareCall(sql);
            for(int i=0;i<params.length;i++){
            	if(params[i]==null){
            		cs.setNull(i+1, Types.VARCHAR);
            	}
            	else{
            		cs.setObject(i+1,params[i]);    
            	}
            }            
            rs=cs.executeQuery();
            list.add(rs);
            while(cs.getMoreResults(Statement.KEEP_CURRENT_RESULT))
            {
            	list.add(cs.getResultSet());
            	
            }
            return list; 
           
           
        }catch(Exception ex){
        	ex.printStackTrace();
			throw new Exception(ex.getMessage());            
        }finally{            
            //if(cs!=null)c 
            //if(rs!=null)r             
        }       
        
    }
    /**
     * Copia el contenido de una columna de un resultset a un array.
     * @param rs
     * @param colName
     * @return
     * @throws SQLException
     */
    public static Object[] copyToArray(ResultSet rs, String colName)
    throws SQLException{
    	ArrayList list = new ArrayList();
    	while(rs.next()){
    		list.add(rs.getObject(colName));    		
    	}
    	return list.toArray();
    }
    /**
     * Concatena el contenido de una columna de un resultset a un String 
     * @param rs
     * @param colName
     * @param separator
     * @return
     * @throws SQLException
     */
    public static String copyToString(ResultSet rs, String colName, String separator)
    throws SQLException{
       	StringBuilder sb = new StringBuilder();
       	boolean hasRow=rs.next();
       	//int i=0;
    	while(hasRow){
    		sb.append(rs.getObject(colName));
    		hasRow=rs.next();
    		//System.out.println("i = " + i);
    		if(hasRow)sb.append(separator);    		
    	}
    	
    	return sb.toString();
    }
    /**
     * Copia el contenido de un resultset a un array 
     * @param rs
     * @param colNameCabecera
     * @param colNameDetalle
     * @param separator
     * @return
     * @throws SQLException
     */
    public static Object[] copyToStringTwoParams(ResultSet rs, String colNameCabecera, String colNameDetalle, String separator)
    throws SQLException{
    	Object[] objParams= new Object[2];
       	StringBuilder sbC = new StringBuilder();
       	StringBuilder sbD = new StringBuilder();
       	boolean hasRow=rs.next(); 
    	while(hasRow){
    		sbC.append(rs.getObject(colNameCabecera));
    		sbD.append(rs.getObject(colNameDetalle));
    		hasRow=rs.next();
    		if(hasRow){
    			sbC.append(separator);
    			sbD.append(separator);
    			
    		}    		
    	}
    	objParams[0]=sbC.toString();
    	objParams[1]=sbD.toString();
    	return objParams;    	
    }
    /**
     * Cierra los recursos asociados a una lista de ResultSets
     * @param res
     */
    public static void closeResultSets(ArrayList<ResultSet> res){
        if(res==null || res.size()<=0) return;
        try{
            res.get(0).getStatement().close();
            for(int i=0; i<res.size();i++)
                res.get(i).close();
        }catch(SQLException sex){
            //log.error("Error cerrando un recurso JDBC.", sex);
        }
    }
    
    /**
     * Cierra los recursos asociados a una lista de ResultSets
     * @param res
     */
    public static void closeResultSet(ResultSet res){
        if(res==null) return;
        try{
            res.getStatement().close();
            res.close();
        }catch(SQLException sex){
            //log.error("Error cerrando un recurso JDBC.", sex);
        }
    }
 
}
