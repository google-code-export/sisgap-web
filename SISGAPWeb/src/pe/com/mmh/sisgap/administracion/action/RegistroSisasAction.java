package pe.com.mmh.sisgap.administracion.action;

import java.math.BigDecimal;
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
import pe.com.mmh.sisgap.domain.Unidadmedida;
import pe.com.mmh.sisgap.utils.RowSetDynaClass;

public class RegistroSisasAction extends GrandActionAbstract {

	public ActionForward cargarAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ItemcobranzaFacadeLocal facadeLocal = (ItemcobranzaFacadeLocal)
		// lookup(ConstantesJNDI.ITEMCOBRANZAFACADE);
		// List<Itemcobranza> lstCob = facadeLocal.findAll();
		// request.setAttribute("lstCob", lstCob);
		SisasFacadeLocal facadeLocal = (SisasFacadeLocal) lookup(ConstantesJNDI.SISASFACADE);
		String ls = facadeLocal.mostrarPlatilla("2012-03-07");
		String[] calendario =ls.split(","); 

		String columnas = "<table border='1'><tr>";
		for (String fec : calendario) {
			if (fec != null && fec.trim().length() != 0) {
				columnas += "<td><span id='s" + fec + "'>" + fec
						+ "<input type='checkbox' name='o" + fec + "' value='"
						+ fec + "'></span></td>";
			}
		}
		columnas += "</tr></table>";
		request.setAttribute("lstPlan", columnas);
		return mapping.findForward("cargarAction");
	}
}
