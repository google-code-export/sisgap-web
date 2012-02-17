package pe.com.mmh.sisgap.reportes.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.action.form.GestionarItemCobranzaActionForm;
import pe.com.mmh.sisgap.administracion.ejb.ItemcobranzaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.UnidadmedidaFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.Unidadmedida;

public class ReportesItemCobranzaAction extends GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		return mapping.findForward("cargarAction");
	}
	
	
	public ActionForward irGrabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		UnidadmedidaFacadeLocal facadeLocal=(UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		List<Unidadmedida> lstMedidas = facadeLocal.findAll();
		request.setAttribute("lstMedidas", lstMedidas);
		
		ItemcobranzaFacadeLocal facadeLocalCob = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		List<Itemcobranza> lstCob = facadeLocalCob.findAll();
		request.setAttribute("lstCob", lstCob);		
		
		return mapping.findForward("agregarCobranzas");
	}
	
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String cbtipocob = request.getParameter("cbtipocob");
		String txtmoneda = request.getParameter("txtmoneda");
		String txtcosto = request.getParameter("txtcosto");
		String txtconcepto = request.getParameter("txtconcepto");
		String cbestado = request.getParameter("cbestado");
		String cbmedida = request.getParameter("cbmedida");
		String  cbconceptpadre = request.getParameter("cbConceptPadre");
		
		Itemcobranza ic = new Itemcobranza();
		ic.setNumCosto(new BigDecimal(txtcosto));
		ic.setNumEstado(new Short(cbestado));
		ic.setStrDescripcion(txtconcepto);
		ic.setStrTipocobranza(cbtipocob);
		ic.setNumCodItemPadre(new Long(cbconceptpadre));
		char a= txtmoneda.charAt(0);
		ic.setStrMoneda(txtmoneda.trim());

		UnidadmedidaFacadeLocal unidadmedidaFacadeLocal = (UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		Unidadmedida uni= unidadmedidaFacadeLocal.find(new BigDecimal(cbmedida));
		ic.setUnidadmedida(uni);
		
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		facadeLocal.create(ic);
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		
		return mapping.findForward("cargarAction");
	}
	
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		GestionarItemCobranzaActionForm frm	= (GestionarItemCobranzaActionForm) form;
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		facadeLocal.remove(new BigDecimal(frm.getCodigo()));		
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward irActualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		GestionarItemCobranzaActionForm frm=(GestionarItemCobranzaActionForm) form;
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		Itemcobranza obj=facadeLocal.find(new BigDecimal(frm.getCodigo()));
		
		ItemcobranzaFacadeLocal facadeLocalCob = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		List<Itemcobranza> lstCob = facadeLocalCob.findAll();
		request.setAttribute("lstCob", lstCob);	
		
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
		String  cbconceptpadre = request.getParameter("cbConceptPadre");
		
		Itemcobranza ic = new Itemcobranza();
		ic.setCodItemcobranza(new BigDecimal(frm.getCodigo()));
		ic.setNumCosto(new BigDecimal(txtcosto));
		ic.setNumEstado(new Short(cbestado));
		ic.setStrDescripcion(txtconcepto);
		ic.setStrTipocobranza(cbtipocob);
		ic.setStrMoneda(txtmoneda.trim());
		ic.setNumCodItemPadre(new Long(cbconceptpadre));

		UnidadmedidaFacadeLocal unidadmedidaFacadeLocal = (UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		Unidadmedida uni= unidadmedidaFacadeLocal.find(new BigDecimal(cbmedida));
		ic.setUnidadmedida(uni);
		
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		facadeLocal.edit(ic);
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		
		return mapping.findForward("cargarAction");
	}	
	
}
