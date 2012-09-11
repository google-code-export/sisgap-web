package pe.com.mmh.sisgap.administracion.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import pe.com.mmh.sisgap.administracion.ejb.SisasFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Sisa;

public class RegistroSisasAction extends GrandActionAbstract {
	
	String per;
	String cod;

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisasAction] Inicio - cargarAction");
		SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);
		
		for(int a=0; a<list.size(); a++){
			System.out.println(list.get(a).getCodigo()+ " - " + list.get(a).getTranCodigo().trim() + " - " + list.get(a).getEstado());
		}
		
		System.out.println("[RegistroSisasAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward blankpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisasAction] Inicio - blankPage");

		System.out.println("[RegistroSisasAction] Final - blankPage");
		return mapping.findForward("blankpage");
	}

	
	public ActionForward updateSisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[RegistroSisasAction] Inicio - updateSisa");
		SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);

		//Antes
		/*String codigo = request.getParameter("codigoide");
		String periodo = request.getParameter("periodo");*/
		String valuess = request.getParameter("valuess");
		//Despues 28/07/2012
		String codigo = cod;
		String periodo = per;
		
		String fecIngreso = request.getParameter("fecingreso");
		String recNumero = request.getParameter("recnumero");
		
		if(valuess!=null){
			String[] fecha = periodo.split("-");
			periodo = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
			facadeLocal.updateSisa(periodo,codigo,valuess,fecIngreso,recNumero);
		}
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);
		System.out.println("[RegistroSisasAction] Final - updateSisa");		
		return mapping.findForward("cargarAction");
	}
	
	//SP_UPD_SISAANULADA
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[RegistroSisasAction] Inicio - eliminar");
		SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);

		String codigoSisa = request.getParameter("codigoSisa");
		
		if(codigoSisa!=null){
			
			facadeLocal.eliminarSisa(codigoSisa);
			
		}
		
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);
		System.out.println("[RegistroSisasAction] Final - eliminar");
		return mapping.findForward("cargarAction");
	}

	
	public ActionForward findGenerator(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("[RegistroSisasAction] Inicio - findGenerator");
		String fecha = request.getParameter("startDate");
		String codigo = request.getParameter("codigo-f"); //codigoide (antes)
		String periodo = request.getParameter("periodo");
		
		if(codigo==null){
			codigo = request.getParameter("codigoide");
		}
		cod = codigo;
		per = periodo;
		
		if(periodo!=null && fecha==null){
			String[] dt = periodo.split("-");
			String mm = dt[1];
			String yy = dt[2];
			
			HashMap<String, String> model= new HashMap<String, String>();
			model.put("01","Enero");
			model.put("02","Febrero"); 
			model.put("03","Marzo");
			model.put("04","Abril");
			model.put("05","Mayo");
			model.put("06","Junio");
			model.put("07","Julio"); 
			model.put("08","Agosto");
			model.put("09","Septiembre"); 
			model.put("10","Octubre");
			model.put("11","Noviembre");
			model.put("12","Diciembre");

			fecha = model.get(mm)+" "+yy;
			
		}
		
		
		if(fecha!=null && !fecha.trim().equals("")){
			
			//System.out.println(codigo);
			//System.out.println(getDate(fecha));
			SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
			int valor = facadeLocal.registraFind(getDate(fecha), codigo.trim());
			
			if(valor==1){
				
				String ajax = request.getParameter("ajax");
				
				if(ajax!=null){
					System.out.println("[RegistroSisasAction] Inicio - Ajax - findGenerator");
					String ls = facadeLocal.mostrarPlatilla(getDate(fecha));
					String lss = facadeLocal.getSisa(getDate(fecha), codigo.trim());				
					request.setAttribute("lstPlan", getTable(ls,fecha,lss));
					
					System.out.println(getTable(ls,fecha,lss));
					
					System.out.println("[RegistroSisasAction] Final - Ajax - findGenerator");
					return mapping.findForward("blankpage");
				}
				
			}else{
				System.out.println(fecha);
				//facadeLocal.registrarSisa(getDate(fecha), new Long(codigo)); //reemplazando registro
				facadeLocal.registrarSisa(getDate(fecha), codigo.trim());
//				String ls = facadeLocal.mostrarPlatilla(getDate(fecha));
//				String lss = facadeLocal.getSisa(getDate(fecha),new Long(codigo));
				List<Sisa> list = facadeLocal.findAll();
				request.setAttribute("lstSisa", list);
//				request.setAttribute("lstPlan", getTable(ls,fecha,lss));
			}
			
			//List<Sisa> list = facadeLocal.findSisa(getDate(fecha), codigo.trim());
			//request.setAttribute("lstSisa", list);
			
		}else if(codigo!=null && !codigo.trim().equals("")){
			
		}
		
		request.setAttribute("showCalendar", "true");
		System.out.println("[RegistroSisasAction] Final - findGenerator");
		return mapping.findForward("cargarAction");
	}
	
	
	public String getDate(String date){
		System.out.println("[RegistroSisasAction] Inicio - getDate");		
		String[] dates=date.split(" ");
		
		HashMap<String, String> model= new HashMap<String, String>();
		
		model.put("Enero", "01");
		model.put("Febrero", "02");
		model.put("Marzo", "03");
		model.put("Abril", "04");
		model.put("Mayo", "05");
		model.put("Junio", "06");
		model.put("Julio", "07");
		model.put("Agosto", "08");
		model.put("Septiembre", "09");
		model.put("Octubre", "10");
		model.put("Noviembre", "11");
		model.put("Diciembre", "12");
		
		String dato = dates[1] + "-" + model.get(dates[0]) + "-" + "01";
		
		System.out.println("[RegistroSisasAction] Final - getDate "+dato);
		return dato;
	}
	
	public String getTable(String ls, String fecha,String dta){
		System.out.println("[RegistroSisasAction] Inicio - getTable");
		String[] calendario =ls.split(","); 
		String[] lss = {}; 
		if(dta!=null){
			lss = dta.split(",");
		}
		String columnas = "<center><table border='0'><tr>";
		
		int size = calendario.length/5;
		int count = 1;
		for (String fec : calendario) {
			if (fec != null && fec.trim().length() != 0) {
				boolean flag = false;
				
				for (String string : lss) {
					if(fec.equals(string)){
						flag = true;break;
					}
				}
				if(flag){
					columnas += "<td style='background-color: #33CC33;'><span id='s" + fec + "'>" + fec + "<input type='checkbox' class='cb-element' name='fechadia' value='" + fec + "' checked='checked'></span></td>";				
					if(count==size){
						columnas += "</tr><tr>";
						count = 0;
					}
					count++;
				}else{
					columnas += "<td style='background-color: red;'><span id='s" + fec + "'>" + fec + "<input type='checkbox' class='cb-element' name='fechadia' value='" + fec + "'></span></td>";				
					if(count==size){
						columnas += "</tr><tr>";
						count = 0;
					}
					count++;
				}

			}
		}
		columnas += "</tr></table><br/></center>";
		System.out.println("[RegistroSisasAction] Final - getTable");
		return columnas;
	}
	
	
}
