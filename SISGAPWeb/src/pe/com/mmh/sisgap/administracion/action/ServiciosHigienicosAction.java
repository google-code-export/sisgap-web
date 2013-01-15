package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

	@SuppressWarnings("static-access")
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[ServiciosHigienicos] Inicio - grabar");
		
		String codServicio = request.getParameter("hdnCabecera");
		String descripcion = request.getParameter("hdnItem");
		String montoTotal = request.getParameter("totalGral");
		String fechadoc = request.getParameter("fechadocumento");
		
		ServiciosHigienicosFacadeLocal SSHHfacadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		
		int cantElementos = Integer.parseInt(request.getParameter("cant_campos"));
		int numeroCampo = Integer.parseInt(request.getParameter("num_campos"));
		List<ServicioDetalle> lst = new ArrayList<ServicioDetalle>();
		
		for(int a=1; a<=cantElementos; a++){
			if (numeroCampo==a) a++;
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
			SSHHfacadeLocal.grabarServicios(new Long(codServicio), descripcion, new BigDecimal(montoTotal), fechadoc, lst);
		}
		
		codServicio = null;
		lst = null;
		
		//Thread.currentThread().sleep(5000);
		
		List<Servicios> list = SSHHfacadeLocal.findAll();
		request.setAttribute("lstSSHH", list);
		
		System.out.println("[ServiciosHigienicos] Final - grabar");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward ver(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[ServiciosHigienicos] Inicio - ver");
		
		ServiciosHigienicosFacadeLocal facadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		String codServicio = request.getParameter("codigoServicio");
		
		Servicios srv = null;
		
		if(codServicio!=null){
			srv = facadeLocal.find(new BigDecimal(codServicio));
		}
		
		List<ServicioDetalle> lstSrvDet = srv.getSisgapServicioDetalle();
		
		
		
		
		request.setAttribute("numCodServicio", srv.getCodServicio());
		request.setAttribute("strDescripcion", srv.getStrDescripcion().trim());
		request.setAttribute("datFechaServ", srv.getDatFechaserv());
		request.setAttribute("numTotal", srv.getNumTotal());
		
		request.setAttribute("srv", srv);
		request.setAttribute("lstSrvDet", lstSrvDet);
		request.setAttribute("estadoCampos", "true");
		

		for(int a=0; a<lstSrvDet.size(); a++){
			System.out.println(lstSrvDet.get(a).getCodServicioitem()+" : "+lstSrvDet.get(a).getNumDel()+"-"+lstSrvDet.get(a).getNumAl()+" : "+lstSrvDet.get(a).getNumCosto()+" : "+lstSrvDet.get(a).getNumCantidad());
		}
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaDocumento = "";
		if (fac.getDatFechafac()!=null)
			fechaDocumento = sdf.format(fac.getDatFechafac());*/
		
		request.setAttribute("isDetalle", 1);
		
		System.out.println("[ServiciosHigienicos] Final - ver");
		return mapping.findForward("verServicios");
	}

	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[ServiciosHigienicos] Inicio - eliminar");

		ServiciosHigienicosFacadeLocal facadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		String codServicio = request.getParameter("codigoServicio");
			
		if(codServicio!=null){
			facadeLocal.anularServicio(codServicio);
		}
		
		ServiciosHigienicosFacadeLocal SSHHfacadeLocal = (ServiciosHigienicosFacadeLocal) lookup(ConstantesJNDI.SERVICIOSHIGIENICOS);
		List<Servicios> list = SSHHfacadeLocal.findAll();
		request.setAttribute("lstSSHH", list);
		
		System.out.println("[ServiciosHigienicos] Final - eliminar");
		return mapping.findForward("cargarAction");
	}
	
	
}
