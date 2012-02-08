package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import pe.com.mmh.sisgap.administracion.ejb.FacturaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SuministroLuzFacadeLocal;
import pe.com.mmh.sisgap.comun.BaseDispatchAction;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ReciboluzOrg;
import pe.com.mmh.sisgap.domain.SuministroLusReciboSocio;

public class SuministroLuzAction extends GrandActionAbstract{//GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		List<ReciboluzOrg> lst = facadeLocal.ListReciboluzOrg();		
		request.setAttribute("lstRes", lst);		
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		return mapping.findForward("cargarAction");
	}
	

	@SuppressWarnings("deprecation")
	public ActionForward registrarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String lecturaIni = request.getParameter("lecturaInix");
		String lecturaFin = request.getParameter("lecturaFinx");
		String monto = request.getParameter("montox");
		String costoWats = request.getParameter("costoWatsx");
		String periodo = request.getParameter("periodox");
		String estado = request.getParameter("estadox");
		
		res.setNumLecturaInicial(new BigDecimal(lecturaIni));
		res.setNumLecturaFinal(new BigDecimal(lecturaFin));
		res.setNumMonto(new BigDecimal(monto));
		res.setNumCostoWats(new BigDecimal(costoWats));
		res.setFecPeriodo(new Date(periodo));
		res.setNumEstado(new BigDecimal(estado));
		
		facadeLocal.createResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	public ActionForward actualizarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String codigoModi = request.getParameter("codigoModi");
		String lecturaIni = request.getParameter("lecturaInix");
		String lecturaFin = request.getParameter("lecturaFinx");
		String monto = request.getParameter("montox");
		String costoWats = request.getParameter("costoWatsx");
		String periodo = request.getParameter("periodox");
		String estado = request.getParameter("estadox");

		res.setCodOrgreciboLuz(new Long(codigoModi));
		res.setNumLecturaInicial(new BigDecimal(lecturaIni));
		res.setNumLecturaFinal(new BigDecimal(lecturaFin));
		res.setNumMonto(new BigDecimal(monto));
		res.setNumCostoWats(new BigDecimal(costoWats));
		res.setFecPeriodo(new Date(periodo));
		res.setNumEstado(new BigDecimal(estado));
		
		facadeLocal.updateResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	
	public ActionForward eliminarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String codigoModi = request.getParameter("codigoModi");
		res.setCodOrgreciboLuz(new Long(codigoModi));
		facadeLocal.deleteResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	
	public ActionForward listarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		List<ReciboluzOrg> lst = facadeLocal.ListReciboluzOrg();		
		request.setAttribute("lstRes", lst);		
		return mapping.findForward("cargarAction");

	}
	
	public ActionForward mostrarItemsSuministro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		String codigoModi = request.getParameter("codigoModi");
		
		List<SuministroLusReciboSocio> lstSum = facadeLocal.listarItemReciboLuzSocio(new Long(codigoModi));
		//request.setAttribute("lstSum", lstSum);
		
		ReciboluzOrg res= new ReciboluzOrg();		
		res.setCodOrgreciboLuz(new Long(codigoModi));
		ReciboluzOrg res2 = facadeLocal.buscarRecibo(res); 
		
		request.setAttribute("resori", res2);
		request.setAttribute("ListaSuministroLuz", lstSum);
		return mapping.findForward("agrebarSuministroluz");
	}
	
	public ActionForward grabarItemReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);
		ReciboluzOrg res= new ReciboluzOrg();		
		String codigoModi = request.getParameter("codigoModi");
		res.setCodOrgreciboLuz(new Long(codigoModi));
		res = facadeLocal.buscarRecibo(res); 
		request.setAttribute("resori", res);
		return mapping.findForward("agrebarSuministroluz");
	}
	
	public ActionForward grabarItemReciboLuzSocio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);
		SuministroLusReciboSocio srs= new SuministroLusReciboSocio();		

		String codigoModi = request.getParameter("codigoModi");
		String codigoSocio = request.getParameter("codigoide");
		
		String txtLecturaIni = request.getParameter("txtLecturaIni");
		String txtLecturaFin = request.getParameter("txtLecturaFin");
		String txtConsumomes = request.getParameter("txtConsumomes");
		String txtCagofijo = request.getParameter("txtCagofijo");
		String txtAlupublic = request.getParameter("txtAlupublic");
		String txtCargoener = request.getParameter("txtCargoener");
		String txtTotalMes = request.getParameter("txtTotalMes");
		String txtIgv = request.getParameter("txtIgv");
		String txtSubTotalMes = request.getParameter("txtSubTotalMes");
		String txtUsoEquipo = request.getParameter("txtUsoEquipo");
		String txtServmanto = request.getParameter("txtServmanto");
		String txtAporteley = request.getParameter("txtAporteley");
		String txtRecargo = request.getParameter("txtRecargo");
		String txtRedondeo = request.getParameter("txtRedondeo");
		String txtTotal = request.getParameter("txtTotal");
		
		srs.setCodigorecibo(new Long(codigoModi));
		srs.setCodigosocio(new Long(codigoSocio));
		srs.setLecturaini(new BigDecimal(txtLecturaIni));
		srs.setLecturafin(new BigDecimal(txtLecturaFin));
		srs.setConsumomes(new BigDecimal(txtConsumomes));
		srs.setCagofijo(new BigDecimal(txtCagofijo));
		srs.setAlupublic(new BigDecimal(txtAlupublic));
		srs.setCargoener(new BigDecimal(txtCargoener));
		srs.setSubtotalmes(new BigDecimal(txtSubTotalMes));
		srs.setIgv(new BigDecimal(txtIgv));
		srs.setTotalmes(new BigDecimal(txtTotalMes));
		srs.setUsoequipo(new BigDecimal(txtUsoEquipo));
		srs.setServmanto(new BigDecimal(txtServmanto));
		srs.setAporteley(new BigDecimal(txtAporteley));
		srs.setRecargo(new BigDecimal(txtRecargo));
		srs.setRedondeo(new BigDecimal(txtRedondeo));
		srs.setTotal(new BigDecimal(txtTotal));

		
		facadeLocal.grabarItemReciboLuzSocio(srs);
		
		
		List<SuministroLusReciboSocio> lstSum = facadeLocal.listarItemReciboLuzSocio(new Long(codigoModi));
	
		ReciboluzOrg res= new ReciboluzOrg();		
		res.setCodOrgreciboLuz(new Long(codigoModi));
		ReciboluzOrg res2 = facadeLocal.buscarRecibo(res); 
		
		request.setAttribute("resori", res2);
		
		request.setAttribute("ListaSuministroLuz", lstSum);
		
		return mapping.findForward("agrebarSuministroluz");
	}
	
	public ActionForward eliminarItemReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);
		
		String codigoModi = request.getParameter("codigoModi");
		String codigoSocio = request.getParameter("codigoide");
		String correlativo = request.getParameter("correlativo");
		
		facadeLocal.eliminarItemReciboLuzSocio(new Long(correlativo),new Long(codigoSocio),new Long(codigoModi));
		List<SuministroLusReciboSocio> lstSum = facadeLocal.listarItemReciboLuzSocio(new Long(codigoModi));
		
		ReciboluzOrg res= new ReciboluzOrg();		
		res.setCodOrgreciboLuz(new Long(codigoModi));
		ReciboluzOrg res2 = facadeLocal.buscarRecibo(res); 
		
		request.setAttribute("resori", res2);
		
		request.setAttribute("ListaSuministroLuz", lstSum);
		
		
		
		
		return mapping.findForward("agrebarSuministroluz");
	}
	
	public ActionForward actualizarItemReciboLuzSocio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);
		SuministroLusReciboSocio srs= new SuministroLusReciboSocio();		

		String codigoModi = request.getParameter("codigoModi");
		String codigoSocio = request.getParameter("codigoide");
		String correlativo = request.getParameter("correlativo");
		
		
		String txtLecturaIni = request.getParameter("txtLecturaIni");
		String txtLecturaFin = request.getParameter("txtLecturaFin");
		String txtConsumomes = request.getParameter("txtConsumomes");
		String txtCagofijo = request.getParameter("txtCagofijo");
		String txtAlupublic = request.getParameter("txtAlupublic");
		String txtCargoener = request.getParameter("txtCargoener");
		String txtTotalMes = request.getParameter("txtTotalMes");
		String txtIgv = request.getParameter("txtIgv");
		String txtSubTotalMes = request.getParameter("txtSubTotalMes");
		String txtUsoEquipo = request.getParameter("txtUsoEquipo");
		String txtServmanto = request.getParameter("txtServmanto");
		String txtAporteley = request.getParameter("txtAporteley");
		String txtRecargo = request.getParameter("txtRecargo");
		String txtRedondeo = request.getParameter("txtRedondeo");
		String txtTotal = request.getParameter("txtTotal");
		
		srs.setCorrelativo(new Long(correlativo));
		srs.setCodigorecibo(new Long(codigoModi));
		srs.setCodigosocio(new Long(codigoSocio));
		srs.setLecturaini(new BigDecimal(txtLecturaIni));
		srs.setLecturafin(new BigDecimal(txtLecturaFin));
		srs.setConsumomes(new BigDecimal(txtConsumomes));
		srs.setCagofijo(new BigDecimal(txtCagofijo));
		srs.setAlupublic(new BigDecimal(txtAlupublic));
		srs.setCargoener(new BigDecimal(txtCargoener));
		srs.setTotalmes(new BigDecimal(txtSubTotalMes));
		srs.setIgv(new BigDecimal(txtIgv));
		srs.setTotalmes(new BigDecimal(txtTotalMes));
		srs.setUsoequipo(new BigDecimal(txtUsoEquipo));
		srs.setServmanto(new BigDecimal(txtServmanto));
		srs.setAporteley(new BigDecimal(txtAporteley));
		srs.setRecargo(new BigDecimal(txtRecargo));
		srs.setRedondeo(new BigDecimal(txtRedondeo));
		srs.setTotal(new BigDecimal(txtTotal));

		
		facadeLocal.actualizarItemReciboLuzSocio(srs);
		
		
		List<SuministroLusReciboSocio> lstSum = facadeLocal.listarItemReciboLuzSocio(new Long(codigoModi));
	
		ReciboluzOrg res= new ReciboluzOrg();		
		res.setCodOrgreciboLuz(new Long(codigoModi));
		ReciboluzOrg res2 = facadeLocal.buscarRecibo(res); 
		
		request.setAttribute("resori", res2);
		
		request.setAttribute("ListaSuministroLuz", lstSum);
		
		return mapping.findForward("agrebarSuministroluz");
	}
	
	public Object lookup(String JNDIName){
		try {
			return new InitialContext().lookup(JNDIName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JNDIName;
	}
}
