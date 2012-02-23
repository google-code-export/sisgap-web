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
		System.out.println("[SuministroLuzAction] cargarAction");
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		List<ReciboluzOrg> lst = facadeLocal.ListReciboluzOrg();		
		request.setAttribute("lstRes", lst);		
		return mapping.findForward("cargarAction");
	}
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] buscar");
		FacturaFacadeLocal facadeLocal = (FacturaFacadeLocal)lookup(ConstantesJNDI.FACTURAFACADE);		
		List<Factura> lstCob = facadeLocal.findAll();
		request.setAttribute("lstFac", lstCob);
		return mapping.findForward("cargarAction");
	}
	

	@SuppressWarnings("deprecation")
	public ActionForward registrarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] registrarReciboLuz");
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String lecturaIni = request.getParameter("lecturaInix");
		String lecturaFin = request.getParameter("lecturaFinx");
		String monto = request.getParameter("montox");
		String costoWats = request.getParameter("costoWatsx");
		String periodo = request.getParameter("periodox");
		String estado = request.getParameter("estadox");
		
		String repomancnx = request.getParameter("repomancnxx");
		String cargofijo = request.getParameter("cargofijox");
		String alumpublic = request.getParameter("alumpublicx");
		String subtotalmes = request.getParameter("subtotalmesx");
		String igv = request.getParameter("igvx");
		String totalmesact = request.getParameter("totalmesactx");
		String aporteley = request.getParameter("aporteleyx");
		String cuotaconv = request.getParameter("cuotaconvx");
		String redonmesact = request.getParameter("redonmesactx");
		String redonmesant = request.getParameter("redonmesantx");
		String interesconvenio = request.getParameter("interesconveniox");
		String energactfraptaactual = request.getParameter("energactfraptaactualx");
		String energactfraptaanteri = request.getParameter("energactfraptaanterix");
		String energactfraptadifer = request.getParameter("energactfraptadiferx");
		String energactfraptafactor = request.getParameter("energactfraptafactorx");
		String energactfraptaconsu = request.getParameter("energactfraptaconsux");
		String energactfraptaconfa = request.getParameter("energactfraptaconfax");
		String energactfraptapreuni = request.getParameter("energactfraptapreunix");
		String energactfraptatotal = request.getParameter("energactfraptatotalx");
		String energacthorptaactu = request.getParameter("energacthorptaactux");
		String energacthorptaant = request.getParameter("energacthorptaantx");
		String energacthorptadif = request.getParameter("energacthorptadifx");
		String energacthorptafac = request.getParameter("energacthorptafacx");
		String energacthorptacons = request.getParameter("energacthorptaconsx");
		String energacthorptaconfac = request.getParameter("energacthorptaconfacx");
		String energacthorptapreuni = request.getParameter("energacthorptapreunix");
		String energacthorptatotal = request.getParameter("energacthorptatotalx");
		String energreacinicial = request.getParameter("energreacinicialx");
		String energreacanteri = request.getParameter("energreacanterix");
		String energreacdifere = request.getParameter("energreacdiferex");
		String energreacfactor = request.getParameter("energreacfactorx");
		String energreacconsu = request.getParameter("energreacconsux");
		String energreacfaccons = request.getParameter("energreacfacconsx");
		String energreacpreuni = request.getParameter("energreacpreunix");
		String energreactotal = request.getParameter("energreactotalx");
		String potenciafpini = request.getParameter("potenciafpinix");
		String potenciafpante = request.getParameter("potenciafpantex");
		String potenciafpdif = request.getParameter("potenciafpdifx");
		String potenciafpfac = request.getParameter("potenciafpfacx");
		String potenciafpcons = request.getParameter("potenciafpconsx");
		String potenciahpact = request.getParameter("potenciahpactx");
		String potenciahpant = request.getParameter("potenciahpantx");
		String potenciahpdif = request.getParameter("potenciahpdifx");
		String potenciahpfac = request.getParameter("potenciahpfacx");
		String potenciahpcons = request.getParameter("potenciahpconsx");
		String potusoreddistconfac = request.getParameter("potusoreddistconfacx");
		String potusoreddistpreuni = request.getParameter("potusoreddistpreunix");
		String potusoreddisttotal = request.getParameter("potusoreddisttotalx");
		String potgenfpconfac = request.getParameter("potgenfpconfacx");
		String potgenfppreuni = request.getParameter("potgenfppreunix");
		String potgenfptotal = request.getParameter("potgenfptotalx");
		
		String fecvencimiento = request.getParameter("fecvencimiento");
		String fecemision = request.getParameter("fecemision");
				
		res.setNumLecturaInicial(new BigDecimal(lecturaIni));
		res.setNumLecturaFinal(new BigDecimal(lecturaFin));
		res.setNumMonto(new BigDecimal(monto));
		res.setNumCostoWats(new BigDecimal(costoWats));
		res.setFecPeriodo(new Date(periodo));
		res.setNumEstado(new BigDecimal(estado));
		
		res.setRepomancnx(new BigDecimal(repomancnx));
		res.setCargofijo(new BigDecimal(cargofijo));
		res.setAlumpublic(new BigDecimal(alumpublic));
		res.setSubtotalmes(new BigDecimal(subtotalmes));
		res.setIgv(new BigDecimal(igv));
		res.setTotalmesact(new BigDecimal(totalmesact));
		res.setAporteley(new BigDecimal(aporteley));
		res.setCuotaconv(new BigDecimal(cuotaconv));
		res.setRedonmesact(new BigDecimal(redonmesact));
		res.setRedonmesant(new BigDecimal(redonmesant));
		res.setInteresconvenio(new BigDecimal(interesconvenio));
		res.setEnergactfraptaactual(new BigDecimal(energactfraptaactual));
		res.setEnergactfraptaanteri(new BigDecimal(energactfraptaanteri));
		res.setEnergactfraptadifer(new BigDecimal(energactfraptadifer));
		res.setEnergactfraptafactor(new BigDecimal(energactfraptafactor));
		res.setEnergactfraptaconsu(new BigDecimal(energactfraptaconsu));
		res.setEnergactfraptaconfa(new BigDecimal(energactfraptaconfa));
		res.setEnergactfraptapreuni(new BigDecimal(energactfraptapreuni));
		res.setEnergactfraptatotal(new BigDecimal(energactfraptatotal));
		res.setEnergacthorptaactu(new BigDecimal(energacthorptaactu));
		res.setEnergacthorptaant(new BigDecimal(energacthorptaant));
		res.setEnergacthorptadif(new BigDecimal(energacthorptadif));
		res.setEnergacthorptafac(new BigDecimal(energacthorptafac));
		res.setEnergacthorptacons(new BigDecimal(energacthorptacons));
		res.setEnergacthorptaconfac(new BigDecimal(energacthorptaconfac));
		res.setEnergacthorptapreuni(new BigDecimal(energacthorptapreuni));
		res.setEnergacthorptatotal(new BigDecimal(energacthorptatotal));
		res.setEnergreacinicial(new BigDecimal(energreacinicial));
		res.setEnergreacanteri(new BigDecimal(energreacanteri));
		res.setEnergreacdifere(new BigDecimal(energreacdifere));
		res.setEnergreacfactor(new BigDecimal(energreacfactor));
		res.setEnergreacconsu(new BigDecimal(energreacconsu));
		res.setEnergreacfaccons(new BigDecimal(energreacfaccons));
		res.setEnergreacpreuni(new BigDecimal(energreacpreuni));
		res.setEnergreactotal(new BigDecimal(energreactotal));
		res.setPotenciafpini(new BigDecimal(potenciafpini));
		res.setPotenciafpante(new BigDecimal(potenciafpante));
		res.setPotenciafpdif(new BigDecimal(potenciafpdif));
		res.setPotenciafpfac(new BigDecimal(potenciafpfac));
		res.setPotenciafpcons(new BigDecimal(potenciafpcons));
		res.setPotenciahpact(new BigDecimal(potenciahpact));
		res.setPotenciahpant(new BigDecimal(potenciahpant));
		res.setPotenciahpdif(new BigDecimal(potenciahpdif));
		res.setPotenciahpfac(new BigDecimal(potenciahpfac));
		res.setPotenciahpcons(new BigDecimal(potenciahpcons));
		res.setPotusoreddistconfac(new BigDecimal(potusoreddistconfac));
		res.setPotusoreddistpreuni(new BigDecimal(potusoreddistpreuni));
		res.setPotusoreddisttotal(new BigDecimal(potusoreddisttotal));
		res.setPotgenfpconfac(new BigDecimal(potgenfpconfac));
		res.setPotgenfppreuni(new BigDecimal(potgenfppreuni));
		res.setPotgenfptotal(new BigDecimal(potgenfptotal));

		res.setFecEmision(new Date(fecemision));
		res.setFecVencimiento(new Date(fecvencimiento));
		
		facadeLocal.createResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	public ActionForward actualizarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] actualizarReciboLuz");
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String codigoModi = request.getParameter("codigoModi");
		String lecturaIni = request.getParameter("lecturaInix");
		String lecturaFin = request.getParameter("lecturaFinx");
		String monto = request.getParameter("montox");
		String costoWats = request.getParameter("costoWatsx");
		String periodo = request.getParameter("periodox");
		String estado = request.getParameter("estadox");

		String repomancnx = request.getParameter("repomancnxx");
		String cargofijo = request.getParameter("cargofijox");
		String alumpublic = request.getParameter("alumpublicx");
		String subtotalmes = request.getParameter("subtotalmesx");
		String igv = request.getParameter("igvx");
		String totalmesact = request.getParameter("totalmesactx");
		String aporteley = request.getParameter("aporteleyx");
		String cuotaconv = request.getParameter("cuotaconvx");
		String redonmesact = request.getParameter("redonmesactx");
		String redonmesant = request.getParameter("redonmesantx");
		String interesconvenio = request.getParameter("interesconveniox");
		String energactfraptaactual = request.getParameter("energactfraptaactualx");
		String energactfraptaanteri = request.getParameter("energactfraptaanterix");
		String energactfraptadifer = request.getParameter("energactfraptadiferx");
		String energactfraptafactor = request.getParameter("energactfraptafactorx");
		String energactfraptaconsu = request.getParameter("energactfraptaconsux");
		String energactfraptaconfa = request.getParameter("energactfraptaconfax");
		String energactfraptapreuni = request.getParameter("energactfraptapreunix");
		String energactfraptatotal = request.getParameter("energactfraptatotalx");
		String energacthorptaactu = request.getParameter("energacthorptaactux");
		String energacthorptaant = request.getParameter("energacthorptaantx");
		String energacthorptadif = request.getParameter("energacthorptadifx");
		String energacthorptafac = request.getParameter("energacthorptafacx");
		String energacthorptacons = request.getParameter("energacthorptaconsx");
		String energacthorptaconfac = request.getParameter("energacthorptaconfacx");
		String energacthorptapreuni = request.getParameter("energacthorptapreunix");
		String energacthorptatotal = request.getParameter("energacthorptatotalx");
		String energreacinicial = request.getParameter("energreacinicialx");
		String energreacanteri = request.getParameter("energreacanterix");
		String energreacdifere = request.getParameter("energreacdiferex");
		String energreacfactor = request.getParameter("energreacfactorx");
		String energreacconsu = request.getParameter("energreacconsux");
		String energreacfaccons = request.getParameter("energreacfacconsx");
		String energreacpreuni = request.getParameter("energreacpreunix");
		String energreactotal = request.getParameter("energreactotalx");
		String potenciafpini = request.getParameter("potenciafpinix");
		String potenciafpante = request.getParameter("potenciafpantex");
		String potenciafpdif = request.getParameter("potenciafpdifx");
		String potenciafpfac = request.getParameter("potenciafpfacx");
		String potenciafpcons = request.getParameter("potenciafpconsx");
		String potenciahpact = request.getParameter("potenciahpactx");
		String potenciahpant = request.getParameter("potenciahpantx");
		String potenciahpdif = request.getParameter("potenciahpdifx");
		String potenciahpfac = request.getParameter("potenciahpfacx");
		String potenciahpcons = request.getParameter("potenciahpconsx");
		String potusoreddistconfac = request.getParameter("potusoreddistconfacx");
		String potusoreddistpreuni = request.getParameter("potusoreddistpreunix");
		String potusoreddisttotal = request.getParameter("potusoreddisttotalx");
		String potgenfpconfac = request.getParameter("potgenfpconfacx");
		String potgenfppreuni = request.getParameter("potgenfppreunix");
		String potgenfptotal = request.getParameter("potgenfptotalx");

		String fecvencimiento = request.getParameter("fecvencimiento");
		String fecemision = request.getParameter("fecemision");
				
		
		res.setCodOrgreciboLuz(new Long(codigoModi));
		res.setNumLecturaInicial(new BigDecimal(lecturaIni));
		res.setNumLecturaFinal(new BigDecimal(lecturaFin));
		res.setNumMonto(new BigDecimal(monto));
		res.setNumCostoWats(new BigDecimal(costoWats));
		res.setFecPeriodo(new Date(periodo));
		res.setNumEstado(new BigDecimal(estado));
		
		res.setRepomancnx(new BigDecimal(repomancnx));
		res.setCargofijo(new BigDecimal(cargofijo));
		res.setAlumpublic(new BigDecimal(alumpublic));
		res.setSubtotalmes(new BigDecimal(subtotalmes));
		res.setIgv(new BigDecimal(igv));
		res.setTotalmesact(new BigDecimal(totalmesact));
		res.setAporteley(new BigDecimal(aporteley));
		res.setCuotaconv(new BigDecimal(cuotaconv));
		res.setRedonmesact(new BigDecimal(redonmesact));
		res.setRedonmesant(new BigDecimal(redonmesant));
		res.setInteresconvenio(new BigDecimal(interesconvenio));
		res.setEnergactfraptaactual(new BigDecimal(energactfraptaactual));
		res.setEnergactfraptaanteri(new BigDecimal(energactfraptaanteri));
		res.setEnergactfraptadifer(new BigDecimal(energactfraptadifer));
		res.setEnergactfraptafactor(new BigDecimal(energactfraptafactor));
		res.setEnergactfraptaconsu(new BigDecimal(energactfraptaconsu));
		res.setEnergactfraptaconfa(new BigDecimal(energactfraptaconfa));
		res.setEnergactfraptapreuni(new BigDecimal(energactfraptapreuni));
		res.setEnergactfraptatotal(new BigDecimal(energactfraptatotal));
		res.setEnergacthorptaactu(new BigDecimal(energacthorptaactu));
		res.setEnergacthorptaant(new BigDecimal(energacthorptaant));
		res.setEnergacthorptadif(new BigDecimal(energacthorptadif));
		res.setEnergacthorptafac(new BigDecimal(energacthorptafac));
		res.setEnergacthorptacons(new BigDecimal(energacthorptacons));
		res.setEnergacthorptaconfac(new BigDecimal(energacthorptaconfac));
		res.setEnergacthorptapreuni(new BigDecimal(energacthorptapreuni));
		res.setEnergacthorptatotal(new BigDecimal(energacthorptatotal));
		res.setEnergreacinicial(new BigDecimal(energreacinicial));
		res.setEnergreacanteri(new BigDecimal(energreacanteri));
		res.setEnergreacdifere(new BigDecimal(energreacdifere));
		res.setEnergreacfactor(new BigDecimal(energreacfactor));
		res.setEnergreacconsu(new BigDecimal(energreacconsu));
		res.setEnergreacfaccons(new BigDecimal(energreacfaccons));
		res.setEnergreacpreuni(new BigDecimal(energreacpreuni));
		res.setEnergreactotal(new BigDecimal(energreactotal));
		res.setPotenciafpini(new BigDecimal(potenciafpini));
		res.setPotenciafpante(new BigDecimal(potenciafpante));
		res.setPotenciafpdif(new BigDecimal(potenciafpdif));
		res.setPotenciafpfac(new BigDecimal(potenciafpfac));
		res.setPotenciafpcons(new BigDecimal(potenciafpcons));
		res.setPotenciahpact(new BigDecimal(potenciahpact));
		res.setPotenciahpant(new BigDecimal(potenciahpant));
		res.setPotenciahpdif(new BigDecimal(potenciahpdif));
		res.setPotenciahpfac(new BigDecimal(potenciahpfac));
		res.setPotenciahpcons(new BigDecimal(potenciahpcons));
		res.setPotusoreddistconfac(new BigDecimal(potusoreddistconfac));
		res.setPotusoreddistpreuni(new BigDecimal(potusoreddistpreuni));
		res.setPotusoreddisttotal(new BigDecimal(potusoreddisttotal));
		res.setPotgenfpconfac(new BigDecimal(potgenfpconfac));
		res.setPotgenfppreuni(new BigDecimal(potgenfppreuni));
		res.setPotgenfptotal(new BigDecimal(potgenfptotal));

		res.setFecEmision(new Date(fecemision));
		res.setFecVencimiento(new Date(fecvencimiento));
		
		facadeLocal.updateResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	
	public ActionForward eliminarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] eliminarReciboLuz");
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		

		ReciboluzOrg res= new ReciboluzOrg();
		
		String codigoModi = request.getParameter("codigoModi");
		res.setCodOrgreciboLuz(new Long(codigoModi));
		facadeLocal.deleteResOri(res);
		
		return listarReciboLuz(mapping, form, request, response);
	}
	
	
	public ActionForward listarReciboLuz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] listarReciboLuz");
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);		
		List<ReciboluzOrg> lst = facadeLocal.ListReciboluzOrg();		
		request.setAttribute("lstRes", lst);		
		return mapping.findForward("cargarAction");

	}
	
	public ActionForward mostrarItemsSuministro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] mostrarItemsSuministro");
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
		System.out.println("[SuministroLuzAction] grabarItemReciboLuz");
		SuministroLuzFacadeLocal facadeLocal = (SuministroLuzFacadeLocal)lookup(ConstantesJNDI.SUMINISTROLUZ);
		ReciboluzOrg res= new ReciboluzOrg();		
		String codigoModi = request.getParameter("codigoModi");
		res.setCodOrgreciboLuz(new Long(codigoModi));
		res = facadeLocal.buscarRecibo(res); 
		request.setAttribute("resori", res);
		return mapping.findForward("agrebarSuministroluz");
	}
	
	public ActionForward grabarItemReciboLuzSocio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		System.out.println("[SuministroLuzAction] grabarItemReciboLuzSocio");
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
		System.out.println("[SuministroLuzAction] eliminarItemReciboLuz");
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
		System.out.println("[SuministroLuzAction] actualizarItemReciboLuzSocio");
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
