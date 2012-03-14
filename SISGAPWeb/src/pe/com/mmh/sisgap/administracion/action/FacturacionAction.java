package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.action.form.GestionarItemCobranzaActionForm;
import pe.com.mmh.sisgap.administracion.ejb.FacturaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.ItemcobranzaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.UnidadmedidaFacadeLocal;
import pe.com.mmh.sisgap.comun.BaseDispatchAction;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Unidadmedida;

public class FacturacionAction extends BaseDispatchAction{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward ver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		String codigoFactura = request.getParameter("codigoFactura");		
		Factura fac = null;
		if(codigoFactura!=null){
			fac = facadeLocal.find(new Long(codigoFactura));
		}
		Set<Detallefactura> lstDetFac=fac.getSisgapDetallefacturas();
		request.setAttribute("tipodocumento", fac.getStrTipodoc().trim());
		request.setAttribute("numerodocumento", fac.getNumNrodoc());
		request.setAttribute("isDetalle", 1);
		request.setAttribute("fac", fac);
		request.setAttribute("lstDetFac", lstDetFac);
		return mapping.findForward("agregarFacturacion");
	}
	
	public ActionForward cancelarFactura(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		

		String codigoFactura = request.getParameter("nrodocumento");		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		facadeLocal.cancelarFactura(codigoFactura);
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		
		
		
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward irGrabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		
		BigDecimal numerodocumento = new BigDecimal(0);
		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		
		Factura facturaBolsa = new Factura();
		facturaBolsa.setSisgapDetallefacturas((Set<Detallefactura>) new HashSet<Detallefactura>());
		HttpSession session = request.getSession(true);
		//Detalle Factura en session
		Set<Detallefactura> listDetallefactura = new HashSet<Detallefactura>();
		
		
		String tipodocumento = request.getParameter("tipodocumento");
		numerodocumento = facadeLocal.generarNrodocumento(tipodocumento);
		
		session.setAttribute("listDetallefactura", listDetallefactura);
		session.setAttribute("facturaBolsa", facturaBolsa);	
		request.setAttribute("numerodocumento", numerodocumento);
		request.setAttribute("tipodocumento", tipodocumento);
		return mapping.findForward("agregarFacturacion");
	}
	
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		Factura facturaBolsa = (Factura) request.getSession().getAttribute("facturaBolsa");
//		facadeLocal.create(facturaBolsa);
		
		Set<Detallefactura> listDetallefactura = (Set<Detallefactura>) session.getAttribute("listDetallefactura");
		
		String totalfac = request.getParameter("txttotal");
		String codigoide = request.getParameter("codigoide");
		String cbtipodoc = request.getParameter("cbtipodoc");
		String numerodocumento = request.getParameter("numerodocumento");
		
		if(listDetallefactura!=null && codigoide!=null){
			facadeLocal.grebarFactura(new Long(numerodocumento),totalfac, codigoide, cbtipodoc, listDetallefactura);
		}
		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		
		return mapping.findForward("cargarAction");
	}
	
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		

		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal) lookup(ConstantesJNDI.FACTURAFACADE);

		String codigoFactura = request.getParameter("codigoFactura");
		String descripanulada = request.getParameter("descripanulada");
		
		
		if(codigoFactura!=null){
			
			facadeLocal.anularFactura(codigoFactura, descripanulada);
//			Factura f= new Factura();
//			f.setCodFactura(new Long(codigoFactura));
//			facadeLocal.remove(f);
			
		}
		
		List<Factura> lstFac = facadeLocal.findAll();
		request.setAttribute("lstFac", lstFac);
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward irActualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		GestionarItemCobranzaActionForm frm=(GestionarItemCobranzaActionForm) form;
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		Itemcobranza obj=facadeLocal.find(new BigDecimal(frm.getCodigo()));
		
		UnidadmedidaFacadeLocal unimed=(UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		List<Unidadmedida> lstMedidas = unimed.findAll();
		request.setAttribute("lstMedidas", lstMedidas);
		
		request.setAttribute("objItem", obj);
		
		return mapping.findForward("editarItem");
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		GestionarItemCobranzaActionForm frm=(GestionarItemCobranzaActionForm) form;
		
		String cbtipocob = request.getParameter("cbtipocob");
		String txtmoneda = request.getParameter("txtmoneda");
		String txtcosto = request.getParameter("txtcosto");
		String txtconcepto = request.getParameter("txtconcepto");
		String cbestado = request.getParameter("cbestado");
		String cbmedida = request.getParameter("cbmedida");
		
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
	
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		
		return mapping.findForward("cargarAction");
	}
	
	// Bolsa
	
	public ActionForward insertarBolsa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		HttpSession session = request.getSession();
		Factura facturaBolsa = (Factura) request.getSession().getAttribute("facturaBolsa");
		
		String var1 = request.getParameter("codigo");
		String var2 = request.getParameter("descrip");
		String var3 = request.getParameter("codTipCob");
		String var4 = request.getParameter("codMon");
		String var5 = request.getParameter("costo");
		String var6 = request.getParameter("cantidad");		
		String var7 = request.getParameter("acuenta");
		
		
		if (facturaBolsa != null) {
			request.setAttribute("lstFacturaBolsa", facturaBolsa.getSisgapDetallefacturas());
		}
		
		
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward eliminarBolsa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Factura facturaBolsa = (Factura) request.getSession().getAttribute("facturaBolsa");
		
		String var1 = request.getParameter("");
		String var2 = request.getParameter("");
		String var3 = request.getParameter("");
		String var4 = request.getParameter("");
		String var5 = request.getParameter("");
		String var6 = request.getParameter("");
		
		if (facturaBolsa != null) {
			request.setAttribute("lstFacturaBolsa", facturaBolsa.getSisgapDetallefacturas());
		}
		
		return mapping.findForward("cargarAction");
	}
		
	public ActionForward actualizarBolsa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Factura facturaBolsa = (Factura) request.getSession().getAttribute("facturaBolsa");
		
		String var1 = request.getParameter("");
		String var2 = request.getParameter("");
		String var3 = request.getParameter("");
		String var4 = request.getParameter("");
		String var5 = request.getParameter("");
		String var6 = request.getParameter("");
		
		if (facturaBolsa != null) {
			request.setAttribute("lstFacturaBolsa", facturaBolsa.getSisgapDetallefacturas());
		}
		
		return mapping.findForward("cargarAction");
	}
		
	
	
}
