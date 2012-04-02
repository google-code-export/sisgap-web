package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.action.form.GestionarItemCobranzaActionForm;
import pe.com.mmh.sisgap.administracion.ejb.ItemcobranzaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SisasFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.UnidadmedidaFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Sisa;
import pe.com.mmh.sisgap.domain.Unidadmedida;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

public class RegistroSisasAction extends GrandActionAbstract {

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);

		return mapping.findForward("cargarAction");
	}
	
	public ActionForward updateSisa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);
		
		String codigo = request.getParameter("codigoide");
		String periodo = request.getParameter("periodo");
		String valuess = request.getParameter("valuess");
		
		if(valuess!=null){
			String[] fecha = periodo.split("-");
			periodo = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
			facadeLocal.updateSisa(periodo,codigo,valuess);
		}
		
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward findGenerator(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		String fecha = request.getParameter("startDate");
		String codigo = request.getParameter("codigoide");
		String periodo = request.getParameter("periodo");
		
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
			
			System.out.println(codigo);
			SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
			int valor = facadeLocal.registraFind(getDate(fecha), new Integer(codigo));
			
			if(valor==1){
				
				String ls = facadeLocal.mostrarPlatilla(getDate(fecha));
				String lss = facadeLocal.getSisa(getDate(fecha),new Long(codigo));
				
				request.setAttribute("lstPlan", getTable(ls,fecha,lss));
				return mapping.findForward("blankpage");
				
			}else{
				System.out.println(fecha);
				facadeLocal.registrarSisa(getDate(fecha), new Long(codigo));
//				String ls = facadeLocal.mostrarPlatilla(getDate(fecha));
//				String lss = facadeLocal.getSisa(getDate(fecha),new Long(codigo));
				List<Sisa> list = facadeLocal.findAll();
				request.setAttribute("lstSisa", list);
//				request.setAttribute("lstPlan", getTable(ls,fecha,lss));
			}
			
		}else if(codigo!=null && !codigo.trim().equals("")){
			
		}
		
		request.setAttribute("showCalendar", "true");		
		return mapping.findForward("cargarAction");
	}
	
	
	public String getDate(String date){
		
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
		
		System.out.println(dato);
		
		return dato;
	}
	
	public String getTable(String ls, String fecha,String dta){
		
		String[] calendario =ls.split(","); 
		String[] lss = {}; 
		if(dta!=null){
			lss = dta.split(",");
		}
		String columnas = "<table border='1'><tr>";
		
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
					columnas += "<td><span id='s" + fec + "'>" + fec + "<input type='checkbox' name='fechadia' value='" + fec + "' checked='checked'></span></td>";				
					if(count==size){
						columnas += "</tr><tr>";
						count = 0;
					}
					count++;
				}else{
					columnas += "<td><span id='s" + fec + "'>" + fec + "<input type='checkbox' name='fechadia' value='" + fec + "'></span></td>";				
					if(count==size){
						columnas += "</tr><tr>";
						count = 0;
					}
					count++;
				}

			}
		}
		columnas += "</tr></table>";
		
		return columnas;
	}
	
}
