package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Unidadmedida;
import pe.com.mmh.sisgap.util.NumberToLeterConverter;

public class FacturacionAction extends GrandActionAbstract{
	
	public String codFactura="";
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		System.out.println("[FacturacionAction] Inicio - cargarAction");
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		System.out.println("[FacturacionAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		System.out.println("[FacturacionAction] Inicio - buscar");
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		System.out.println("[FacturacionAction] Final - buscar");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward ver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		System.out.println("[FacturacionAction] Inicio - ver");
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		//String codigoFactura = request.getParameter("codigoFactura");
		codFactura = request.getParameter("codigoFactura");
		Factura fac = null;
		if(codFactura!=null){
			fac = facadeLocal.find(new Long(codFactura));
		}
		
		
		
		List<Detallefactura> lstDetFac=fac.getSisgapDetallefacturas();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaDocumento = "";
		if (fac.getDatFechafac()!=null)
			fechaDocumento = sdf.format(fac.getDatFechafac());
		
		request.setAttribute("tipodocumento", fac.getStrTipodoc().trim());
		request.setAttribute("numerodocumento", fac.getNumNrodoc());
		request.setAttribute("numeroreal", fac.getNroFactura());
		//request.setAttribute("fechadocumento", fac.getDatFechafac());
		request.setAttribute("fechadocumento", fechaDocumento);
		request.setAttribute("numAcuenta", fac.getNumAcuenta());
		request.setAttribute("isDetalle", 1);
		request.setAttribute("fac", fac);
		request.setAttribute("lstDetFac", lstDetFac);
		request.setAttribute("estadoCampos", "true");
		
		System.out.println("[FacturacionAction] Final - ver");
		return mapping.findForward("agregarFacturacion");
	}
	
	public ActionForward cancelarFactura(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
//		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		System.out.println("[FacturacionAction] Inicio - cancelarFactura");
		String codigoFactura = request.getParameter("nrodocumento");		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		facadeLocal.cancelarFactura(codigoFactura);
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		System.out.println("[FacturacionAction] Final - cancelarFactura");
		return mapping.findForward("cargarAction");
	}

	public ActionForward imprimirRecibo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[FacturacionAction] Inicio - imprimirRecibo");
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		//impresaFactura  nrodocumento
		String nroDocReal = request.getParameter("nrodocumentoReal");
		String nroDocInte = request.getParameter("nrodocumentoInterno");
		Factura fac = null;
		if(codFactura!=null){
			fac = facadeLocal.find(new Long(codFactura));
		}
		//Se valida pasarle el mismo numero de documento en caso no viaje el número real
        DecimalFormat formateo = new DecimalFormat("00000");
        if(nroDocReal.equals("")){
        	String nroDocI = formateo.format(Integer.parseInt(nroDocInte));
		
			request.setAttribute("nroDocReal", nroDocI);
			request.setAttribute("nroDocInte", nroDocI);
		} else {
        	String nroDocR = formateo.format(Integer.parseInt(nroDocReal));
        	String nroDocI = formateo.format(Integer.parseInt(nroDocInte));
        	
			request.setAttribute("nroDocReal", nroDocR);
			request.setAttribute("nroDocInte", nroDocI);
			
		}
		List<Detallefactura> lstDetFac=fac.getSisgapDetallefacturas();
		
		double total=0.0;
		for(int i=0; i<lstDetFac.size(); i++){
			total+=Double.parseDouble(lstDetFac.get(i).getNumTotal().toString());
		}
		
		request.setAttribute("texto", NumberToLeterConverter.convertNumberToLetter(total));
		request.setAttribute("total", total);
		request.setAttribute("tipodocumento", fac.getStrTipodoc().trim());
		request.setAttribute("numerodocumento", fac.getNumNrodoc());
		request.setAttribute("numeroreal", fac.getNroFactura());
		request.setAttribute("isDetalle", 1);
		request.setAttribute("fac", fac);
		request.setAttribute("lstDetFac", lstDetFac);
		
		System.out.println("[FacturacionAction] Final - imprimirRecibo");
		return mapping.findForward("imprimirRecibo");
	}

	public ActionForward imprimirBoleta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[FacturacionAction] Inicio - imprimirBoleta");
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		//impresaFactura  nrodocumento
		String nroDocReal = request.getParameter("nrodocumentoReal");
		String nroDocInte = request.getParameter("nrodocumentoInterno");
		Factura fac = null;
		if(codFactura!=null){
			fac = facadeLocal.find(new Long(codFactura));
		}
		//Se valida pasarle el mismo numero de documento en caso no viaje el número real
        DecimalFormat formateo = new DecimalFormat("00000");
        if(nroDocReal.equals("")){
        	String nroDocI = formateo.format(Integer.parseInt(nroDocInte));
		
			request.setAttribute("nroDocReal", nroDocI);
			request.setAttribute("nroDocInte", nroDocI);
		} else {
        	String nroDocR = formateo.format(Integer.parseInt(nroDocReal));
        	String nroDocI = formateo.format(Integer.parseInt(nroDocInte));
        	
			request.setAttribute("nroDocReal", nroDocR);
			request.setAttribute("nroDocInte", nroDocI);
			
		}
		List<Detallefactura> lstDetFac=fac.getSisgapDetallefacturas();
		
		double total=0.0;
		for(int i=0; i<lstDetFac.size(); i++){
			total+=Double.parseDouble(lstDetFac.get(i).getNumTotal().toString());
		}
		
		request.setAttribute("texto", NumberToLeterConverter.convertNumberToLetter(total));
		request.setAttribute("total", total);
		request.setAttribute("tipodocumento", fac.getStrTipodoc().trim());
		request.setAttribute("numerodocumento", fac.getNumNrodoc());
		request.setAttribute("numeroreal", fac.getNroFactura());
		request.setAttribute("isDetalle", 1);
		request.setAttribute("fac", fac);
		request.setAttribute("lstDetFac", lstDetFac);
		
		System.out.println("[FacturacionAction] Final - imprimirBoleta");
		return mapping.findForward("imprimirBoleta");
	}
	
	
	public ActionForward irGrabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[FacturacionAction] Inicio - irGrabar");
		BigDecimal numerodocumento = new BigDecimal(0);
		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		
		Factura facturaBolsa = new Factura();
		//facturaBolsa.setSisgapDetallefacturas((List<Detallefactura>) new HashSet<Detallefactura>());
		facturaBolsa.setSisgapDetallefacturas((List<Detallefactura>) new ArrayList<Detallefactura>());
		HttpSession session = request.getSession(true);
		//Detalle Factura en session
		List<Detallefactura> listDetallefactura = (List<Detallefactura>) new ArrayList<Detallefactura>();
		
		
		String tipodocumento = request.getParameter("tipodocumento");
		numerodocumento = facadeLocal.generarNrodocumento(tipodocumento); 
		
		session.setAttribute("listDetallefactura", listDetallefactura);
		session.setAttribute("facturaBolsa", facturaBolsa);	
		request.setAttribute("numerodocumento", numerodocumento);
		request.setAttribute("tipodocumento", tipodocumento);
		request.setAttribute("estadoCampos", "false");
		System.out.println("[FacturacionAction] Final - irGrabar");
		return mapping.findForward("agregarFacturacion");
	}
	
	public ActionForward selectGrabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[FacturacionAction] Inicio - selectGrabar");
		BigDecimal numerodocumento = new BigDecimal(0);
		
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		
		Factura facturaBolsa = new Factura();
		//facturaBolsa.setSisgapDetallefacturas((List<Detallefactura>) new HashSet<Detallefactura>());
		facturaBolsa.setSisgapDetallefacturas((List<Detallefactura>) new ArrayList<Detallefactura>());
		HttpSession session = request.getSession(true);
		//Detalle Factura en session
		//List<Detallefactura> listDetallefactura = (List<Detallefactura>) new HashSet<Detallefactura>();
		List<Detallefactura> listDetallefactura = (List<Detallefactura>) new ArrayList<Detallefactura>();
		
		
		String tipodocumento = request.getParameter("tipodocumento");
		numerodocumento = facadeLocal.generarNrodocumento(tipodocumento);
		
		session.setAttribute("listDetallefactura", listDetallefactura);
		session.setAttribute("facturaBolsa", facturaBolsa);	
		request.setAttribute("numerodocumento", numerodocumento);
		request.setAttribute("tipodocumento", tipodocumento);
		System.out.println("[FacturacionAction] Final - selectGrabar");
		return mapping.findForward("agregarFacturacion");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[FacturacionAction] Inicio - grabar");
		HttpSession session = request.getSession();
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		Factura facturaBolsa = (Factura) request.getSession().getAttribute("facturaBolsa");
//		facadeLocal.create(facturaBolsa);
		
		List<Detallefactura> listDetallefactura = (List<Detallefactura>) session.getAttribute("listDetallefactura");
		
		String totalfac = request.getParameter("txttotal");
		String codigoide = request.getParameter("codigoide");
		String cbtipodoc = request.getParameter("cbtipodoc");
		String numerodocumento = request.getParameter("numerodocumento");
		String montoacuenta = request.getParameter("txtacuenta");
	
		// Se procede a preparar el parametro fecha para luego ser convertido a fecha SQL y poder enviarlo a la BD al campo de tipo DATE
		String fechadocumento = request.getParameter("fechadocumento"); //  dd/mm/yyyy
		/*SimpleDateFormat fechadocumento = new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date d = (Date) sdf.parse(fechadocumento);*/
		
		if(listDetallefactura!=null && codigoide!=null){
			//facadeLocal.grebarFactura(new Long(numerodocumento), fecha, totalfac, codigoide, cbtipodoc, listDetallefactura);
			facadeLocal.grebarFactura(new Long(numerodocumento), fechadocumento, totalfac, codigoide, cbtipodoc, listDetallefactura, montoacuenta);
			//facadeLocal.grebarFactura(numerodocumento, totalfac, codigoide, cbtipodoc, listDetallefactura);
		}
		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		System.out.println("[FacturacionAction] Final - grabar");
		return mapping.findForward("cargarAction");
	}
	
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[FacturacionAction] Inicio - eliminar");
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
		System.out.println("[FacturacionAction] Final - eliminar");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward irActualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[FacturacionAction] Inicio - irActualizar");
		GestionarItemCobranzaActionForm frm=(GestionarItemCobranzaActionForm) form;
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		Itemcobranza obj=facadeLocal.find(new BigDecimal(frm.getCodigo()));
		
		UnidadmedidaFacadeLocal unimed=(UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		List<Unidadmedida> lstMedidas = unimed.findAll();
		request.setAttribute("lstMedidas", lstMedidas);
		
		request.setAttribute("objItem", obj);
		System.out.println("[FacturacionAction] Final - irActualizar");
		return mapping.findForward("editarItem");
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[FacturacionAction] Inicio - actualizar");
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
		System.out.println("[FacturacionAction] Final - actualizar");
		return mapping.findForward("cargarAction");
	}
	
	// Bolsa
	
	public ActionForward insertarBolsa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
//		HttpSession session = request.getSession();
		System.out.println("[FacturacionAction] Inicio - insertarBolsa");
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
		
		System.out.println("[FacturacionAction] Final - insertarBolsa");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward eliminarBolsa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[FacturacionAction] Inicio - eliminarBolsa");
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
		System.out.println("[FacturacionAction] Final - eliminarBolsa");
		return mapping.findForward("cargarAction");
	}
		
	public ActionForward actualizarBolsa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[FacturacionAction] Inicio - actualizarBolsa");
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
		System.out.println("[FacturacionAction] Final - actualizarBolsa");
		return mapping.findForward("cargarAction");
	}
		
	
	
}
