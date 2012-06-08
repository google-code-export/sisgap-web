package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.com.mmh.sisgap.administracion.action.form.GestionarItemCobranzaActionForm;
import pe.com.mmh.sisgap.administracion.ejb.ItemcobranzaFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.SuministroLuzFacadeLocal;
import pe.com.mmh.sisgap.administracion.ejb.UnidadmedidaFacadeLocal;
import pe.com.mmh.sisgap.comun.GrandActionAbstract;
import pe.com.mmh.sisgap.comun.constantes.ConstantesJNDI;
import pe.com.mmh.sisgap.domain.Itemcobranza;
import pe.com.mmh.sisgap.domain.ReciboluzOrg;
import pe.com.mmh.sisgap.domain.SumistroLuz;
import pe.com.mmh.sisgap.domain.Unidadmedida;

public class GestionarItemCobranzaAction extends GrandActionAbstract{
	
	public ActionForward cargarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarItemCobranzaAction] Inicio - cargarAction");
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		List<Itemcobranza> lstCob = facadeLocal.findAll(); 
		request.setAttribute("lstCob", lstCob);
		//request.setAttribute("lstOrg", lstOrg);
		
		/*for(int i=0; i<lstOrg.size(); i++){
			System.out.println(lstOrg.get(i).getCodOrgreciboLuz());
			System.out.println(lstOrg.get(i).getFecPeriodo());
		}*/
		
		System.out.println("[GestionarItemCobranzaAction] Final - cargarAction");
		return mapping.findForward("cargarAction");
	}
	
	
	public ActionForward irGrabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarItemCobranzaAction] Inicio - irGrabar");
		UnidadmedidaFacadeLocal facadeLocal=(UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		List<Unidadmedida> lstMedidas = facadeLocal.findAll();
		request.setAttribute("lstMedidas", lstMedidas);
		
		ItemcobranzaFacadeLocal facadeLocalCob = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		List<Itemcobranza> lstCob = facadeLocalCob.findAll();
		request.setAttribute("lstCob", lstCob);		

		SuministroLuzFacadeLocal luzfacadeLocal = (SuministroLuzFacadeLocal) lookup(ConstantesJNDI.SUMINISTROLUZ);
		List<ReciboluzOrg> lstOrg = luzfacadeLocal.ListReciboluzOrg();
		request.setAttribute("lstOrg", lstOrg);
		
		System.out.println("[GestionarItemCobranzaAction] Final - irGrabar");
		return mapping.findForward("agregarCobranzas");
	}
	
	public ActionForward grabar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarItemCobranzaAction] Inicio - grabar");
		String cbtipocob = request.getParameter("cbtipocob");
		String txtmoneda = request.getParameter("txtmoneda");
		String txtcosto = request.getParameter("txtcosto");
		String txtconcepto = request.getParameter("txtconcepto");
		String cbestado = request.getParameter("cbestado");
		String cbmedida = request.getParameter("cbmedida");
		String  cbconceptpadre = request.getParameter("cbConceptPadre");
		String cbReciboLuz = request.getParameter("cbReciboLuz");
		
		Itemcobranza ic = new Itemcobranza();
		ic.setNumCosto(new BigDecimal(txtcosto));
		ic.setNumEstado(new Short(cbestado));
		ic.setStrDescripcion(txtconcepto);
		ic.setStrTipocobranza(cbtipocob);
		ic.setNumCodItemPadre(new Long(cbconceptpadre));
		ic.setCodReciboLuz(new BigDecimal(cbReciboLuz));
		char a= txtmoneda.charAt(0);
		ic.setStrMoneda(txtmoneda.trim());

		UnidadmedidaFacadeLocal unidadmedidaFacadeLocal = (UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		Unidadmedida uni= unidadmedidaFacadeLocal.find(new BigDecimal(cbmedida));
		ic.setUnidadmedida(uni);
		
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		facadeLocal.create(ic);
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		System.out.println("[GestionarItemCobranzaAction] Final - grabar");
		return mapping.findForward("cargarAction");
	}
	
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarItemCobranzaAction] Inicio - eliminar");
		GestionarItemCobranzaActionForm frm	= (GestionarItemCobranzaActionForm) form;
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		facadeLocal.remove(new BigDecimal(frm.getCodigo()));		
		List<Itemcobranza> lstCob = facadeLocal.findAll();
		request.setAttribute("lstCob", lstCob);
		System.out.println("[GestionarItemCobranzaAction] Final - eliminar");
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward irActualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarItemCobranzaAction] Inicio - irActualizar");
		GestionarItemCobranzaActionForm frm=(GestionarItemCobranzaActionForm) form;
		ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		Itemcobranza obj=facadeLocal.find(new BigDecimal(frm.getCodigo()));
		
		ItemcobranzaFacadeLocal facadeLocalCob = (ItemcobranzaFacadeLocal) lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		List<Itemcobranza> lstCob = facadeLocalCob.findAll();
		request.setAttribute("lstCob", lstCob);	
		
		SuministroLuzFacadeLocal luzfacadeLocal = (SuministroLuzFacadeLocal) lookup(ConstantesJNDI.SUMINISTROLUZ);
		List<ReciboluzOrg> lstOrg = luzfacadeLocal.ListReciboluzOrg();
		request.setAttribute("lstOrg", lstOrg);
		
		UnidadmedidaFacadeLocal unimed=(UnidadmedidaFacadeLocal) lookup(ConstantesJNDI.UNIDADMEDIDAFACADE);
		List<Unidadmedida> lstMedidas = unimed.findAll();
		request.setAttribute("lstMedidas", lstMedidas);
		
		request.setAttribute("objItem", obj);
		System.out.println("[GestionarItemCobranzaAction] Final - irActualizar");
		return mapping.findForward("editarItem");
	}

	public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("[GestionarItemCobranzaAction] Inicio - actualizar");
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
		System.out.println("[GestionarItemCobranzaAction] Final - actualizar");
		return mapping.findForward("cargarAction");
	}	
	
}
