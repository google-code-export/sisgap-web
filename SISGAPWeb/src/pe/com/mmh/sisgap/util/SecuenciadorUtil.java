package pe.com.mmh.sisgap.util;

//import pe.bat.cpr.common.DaoFactory;

public class SecuenciadorUtil {
	
	private static SecuenciadorUtil unico=null;
	
	public static SecuenciadorUtil getInstance(){
		if(unico==null){
		  unico=new SecuenciadorUtil();
		}
		return unico;
	}
	
	/*
	public String getCurrentKeyFormula() throws Exception{
		 return  DaoFactory.getSystemDAO().getCurrentKeyFormula();
	}

	public String getCurrentKeyGrupo() throws Exception{
		 return  DaoFactory.getSystemDAO().getCurrentKeyGrupo();
	}
	
	public String getCurrentKeyUsuario() throws Exception{
		 return  DaoFactory.getSystemDAO().getCurrentKeyUsuario();
	}
	
	*/
}
