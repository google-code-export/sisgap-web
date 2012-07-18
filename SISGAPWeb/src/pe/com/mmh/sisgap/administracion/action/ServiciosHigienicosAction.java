package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.ejb.FacturaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.ServiciosHigienicosFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.ServiciosItemFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Detallefactura;
import pe.com.mmh.sisgap.domain.Factura;
import pe.com.mmh.sisgap.domain.ServicioDetalle;
import pe.com.mmh.sisgap.domain.ServicioItem;
import pe.com.mmh.sisgap.domain.Servicios;

public class ServiciosHigienicosAction extends GrandActionAbstract {

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - cargarAction");
		
		ServiciosHigienicosFacadeLocal SSHHfacadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		List<Servicios> list = SSHHfacadeLocal.findAll();
		request.setAttribute("lstSSHH", list);

		System.out.println("[ServiciosHigienicos] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}

	
	public ActionForward irGrabar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - irGrabar");
		
		ServiciosItemFacadeLocal SIfacadeLocal = (ServiciosItemFacadeLocal) lookup(ConstantesJNDI.SERVICIOSITEM);
		List<ServicioItem> list = SIfacadeLocal.findAll();
		request.setAttribute("lstSI", list);
		
		System.out.println("[ServiciosHigienicos] Final - irGrabar");
		return mapping.findForward("agregarServicios");
	}
	
	public ActionForward blankpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - blankPage");

		System.out.println("[ServiciosHigienicos] Final - blankPage");
		return mapping.findForward("cargarAction");
	}

	@SuppressWarnings("rawtypes")
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ServiciosHigienicos] Inicio - grabar");
		
		String codServicio = request.getParameter("hdnCabecera");
		String descripcion = request.getParameter("hdnItem");
		String montoTotal = request.getParameter("totalGral");
		String fechadoc = request.getParameter("fechadocumento");
		
		ServiciosHigienicosFacadeLocal SSHHfacadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		
		int cantElementos = Integer.parseInt(request.getParameter("cant_campos"));
		List<ServicioDetalle> lst = new ArrayList<ServicioDetalle>();
		
		for(int a=1; a<=cantElementos; a++){
			ServicioDetalle sd = new ServicioDetalle();
			sd.setCodServicio(new BigDecimal(request.getParameter("hdnCabecera")));
			sd.setCodServiciodetalle(new BigDecimal(request.getParameter("hdnCodigo_"+a)));
			sd.setNumCosto(new BigDecimal(request.getParameter("hdnCostos_"+a)));
			sd.setNumCantidad(new BigDecimal(request.getParameter("hdnCantid_"+a)));
			String _del = request.getParameter("hdnDel_"+a);
			String _al = request.getParameter("hdnAl_"+a);
			if(!_del.equals("")){
				sd.setNumDel(new BigDecimal(request.getParameter("hdnDel_"+a)));
			}else{
				sd.setNumDel(new BigDecimal(0));
			}
			if(!_al.equals("")){
				sd.setNumAl(new BigDecimal(request.getParameter("hdnAl_"+a)));
			}else{
				sd.setNumAl(new BigDecimal(0));
			}
			sd.setNumEstado(true);
			sd.setStrMoneda("S");
			lst.add(sd);
		}

		if(lst!=null && codServicio!=null){
			SSHHfacadeLocal.grabarFactura(new Long(codServicio), descripcion, new BigDecimal(montoTotal), fechadoc, lst);
		}
		
		List<Servicios> list = SSHHfacadeLocal.findAll();
		request.setAttribute("lstSSHH", list);
		
	/*	FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);
		Factura facturaBolsa = (Factura) request.getSession().getAttribute("facturaBolsa");
//		facadeLocal.create(facturaBolsa);
		
		List<Detallefactura> listDetallefactura = (List<Detallefactura>) session.getAttribute("listDetallefactura");
		
		String totalfac = request.getParameter("txttotal");
		String numerodocumento = request.getParameter("numerodocumento");
		String montoacuenta = request.getParameter("txtacuenta");
	
		// Se procede a preparar el parametro fecha para luego ser convertido a fecha SQL y poder enviarlo a la BD al campo de tipo DATE
		String fechadocumento = request.getParameter("fechadocumento"); //  dd/mm/yyyy
		/*SimpleDateFormat fechadocumento = new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date d = (Date) sdf.parse(fechadocumento);*/
		
		/*if(listDetallefactura!=null && codigoide!=null){
			//facadeLocal.grebarFactura(new Long(numerodocumento), fecha, totalfac, codigoide, cbtipodoc, listDetallefactura);
			facadeLocal.grebarFactura(new Long(numerodocumento), fechadocumento, totalfac, codigoide, cbtipodoc, listDetallefactura, montoacuenta);
			//facadeLocal.grebarFactura(numerodocumento, totalfac, codigoide, cbtipodoc, listDetallefactura);
		}
		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);*/
		System.out.println("[ServiciosHigienicos] Final - grabar");
		return mapping.findForward("cargarAction");
	}
	
}
