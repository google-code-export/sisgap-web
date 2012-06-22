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

import pe.com.mmh.sisgap.administracion.action.form.ServiciosHigienicosActionForm;

import pe.com.mmh.sisgap.administracion.ejb.ItemcobranzaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SisasFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.UnidadmedidaFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Sisa;
import pe.com.mmh.sisgap.domain.Unidadmedida;
import pe.com.mmh.sisgap.domain.Servicios;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

public class ServiciosHigienicosAction extends GrandActionAbstract {

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - cargarAction");
		
		/*SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);*/

		System.out.println("[ServiciosHigienicos] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}

	public ActionForward blankpage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("[ServiciosHigienicos] Inicio - blankPage");
		
		/*SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		List<Sisa> list = facadeLocal.findAll();
		request.setAttribute("lstSisa", list);*/

		System.out.println("[ServiciosHigienicos] Final - blankPage");
		return mapping.findForward("cargarAction");
	}

}
