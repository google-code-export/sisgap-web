package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the SISGAP_RECIBOLUZ_ORG database table.
 * 
 */
@Entity
@Table(name = "SISGAP_RECIBOLUZ_ORG")
public class ReciboluzOrg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SISGAP_RECIBOLUZ_ORG_CODORGRECIBOLUZ_GENERATOR", sequenceName = "SQ_GENERAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SISGAP_RECIBOLUZ_ORG_CODORGRECIBOLUZ_GENERATOR")
	@Column(name = "COD_ORGRECIBO_LUZ")
	private long codOrgreciboLuz;

	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_PERIODO")
	private Date fecPeriodo;

	@Column(name = "NUM_COSTO_WATS")
	private BigDecimal numCostoWats;

	@Column(name = "NUM_ESTADO")
	private BigDecimal numEstado;

	@Column(name = "NUM_LECTURA_FINAL")
	private BigDecimal numLecturaFinal;

	@Column(name = "NUM_LECTURA_INICIAL")
	private BigDecimal numLecturaInicial;

	@Column(name = "NUM_MONTO")
	private BigDecimal numMonto;

	@Column(name = "NUM_PENDIENTE_FAC")
	private BigDecimal numPendienteFac;

	// bi-directional many-to-one association to SisgapSumistroLuz
	@OneToMany(mappedBy = "sisgapReciboluzOrg")
	private Set<SumistroLuz> sisgapSumistroLuzs;

	@Transient
	private BigDecimal repomancnx;
	@Transient
	private BigDecimal cargofijo;
	@Transient
	private BigDecimal alumpublic;
	@Transient
	private BigDecimal subtotalmes;
	@Transient
	private BigDecimal igv;
	@Transient
	private BigDecimal totalmesact;
	@Transient
	private BigDecimal aporteley;
	@Transient
	private BigDecimal cuotaconv;
	@Transient
	private BigDecimal redonmesact;
	@Transient
	private BigDecimal redonmesant;
	@Transient
	private BigDecimal interesconvenio;
	@Transient
	private BigDecimal energactfraptaactual;
	@Transient
	private BigDecimal energactfraptaanteri;
	@Transient
	private BigDecimal energactfraptadifer;
	@Transient
	private BigDecimal energactfraptafactor;
	@Transient
	private BigDecimal energactfraptaconsu;
	@Transient
	private BigDecimal energactfraptaconfa;
	@Transient
	private BigDecimal energactfraptapreuni;
	@Transient
	private BigDecimal energactfraptatotal;
	@Transient
	private BigDecimal energacthorptaactu;
	@Transient
	private BigDecimal energacthorptaant;
	@Transient
	private BigDecimal energacthorptadif;
	@Transient
	private BigDecimal energacthorptafac;
	@Transient
	private BigDecimal energacthorptacons;
	@Transient
	private BigDecimal energacthorptaconfac;
	@Transient
	private BigDecimal energacthorptapreuni;
	@Transient
	private BigDecimal energacthorptatotal;
	@Transient
	private BigDecimal energreacinicial;
	@Transient
	private BigDecimal energreacanteri;
	@Transient
	private BigDecimal energreacdifere;
	@Transient
	private BigDecimal energreacfactor;
	@Transient
	private BigDecimal energreacconsu;
	@Transient
	private BigDecimal energreacfaccons;
	@Transient
	private BigDecimal energreacpreuni;
	@Transient
	private BigDecimal energreactotal;
	@Transient
	private BigDecimal potenciafpini;
	@Transient
	private BigDecimal potenciafpante;
	@Transient
	private BigDecimal potenciafpdif;
	@Transient
	private BigDecimal potenciafpfac;
	@Transient
	private BigDecimal potenciafpcons;
	@Transient
	private BigDecimal potenciahpact;
	@Transient
	private BigDecimal potenciahpant;
	@Transient
	private BigDecimal potenciahpdif;
	@Transient
	private BigDecimal potenciahpfac;
	@Transient
	private BigDecimal potenciahpcons;
	@Transient
	private BigDecimal potusoreddistconfac;
	@Transient
	private BigDecimal potusoreddistpreuni;
	@Transient
	private BigDecimal potgenfpconfac;
	@Transient
	private BigDecimal potgenfppreuni;
	@Transient
	private BigDecimal potusoreddisttotal;
	@Transient
	private BigDecimal potgenfptotal;
	@Transient
	private Date fecEmision;
	@Transient
	private Date fecVencimiento;

	
	public ReciboluzOrg() {
	}

	public long getCodOrgreciboLuz() {
		return this.codOrgreciboLuz;
	}

	public void setCodOrgreciboLuz(long codOrgreciboLuz) {
		this.codOrgreciboLuz = codOrgreciboLuz;
	}

	public Date getFecPeriodo() {
		return this.fecPeriodo;
	}

	public void setFecPeriodo(Date fecPeriodo) {
		this.fecPeriodo = fecPeriodo;
	}

	public BigDecimal getNumCostoWats() {
		return this.numCostoWats;
	}

	public void setNumCostoWats(BigDecimal numCostoWats) {
		this.numCostoWats = numCostoWats;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public BigDecimal getNumLecturaFinal() {
		return this.numLecturaFinal;
	}

	public void setNumLecturaFinal(BigDecimal numLecturaFinal) {
		this.numLecturaFinal = numLecturaFinal;
	}

	public BigDecimal getNumLecturaInicial() {
		return this.numLecturaInicial;
	}

	public void setNumLecturaInicial(BigDecimal numLecturaInicial) {
		this.numLecturaInicial = numLecturaInicial;
	}

	public BigDecimal getNumMonto() {
		return this.numMonto;
	}

	public void setNumMonto(BigDecimal numMonto) {
		this.numMonto = numMonto;
	}

	public BigDecimal getNumPendienteFac() {
		return this.numPendienteFac;
	}

	public void setNumPendienteFac(BigDecimal numPendienteFac) {
		this.numPendienteFac = numPendienteFac;
	}

	public Set<SumistroLuz> getSisgapSumistroLuzs() {
		return this.sisgapSumistroLuzs;
	}

	public void setSisgapSumistroLuzs(Set<SumistroLuz> sisgapSumistroLuzs) {
		this.sisgapSumistroLuzs = sisgapSumistroLuzs;
	}

	public BigDecimal getRepomancnx() {
		return repomancnx;
	}

	public void setRepomancnx(BigDecimal repomancnx) {
		this.repomancnx = repomancnx;
	}

	public BigDecimal getCargofijo() {
		return cargofijo;
	}

	public void setCargofijo(BigDecimal cargofijo) {
		this.cargofijo = cargofijo;
	}

	public BigDecimal getAlumpublic() {
		return alumpublic;
	}

	public void setAlumpublic(BigDecimal alumpublic) {
		this.alumpublic = alumpublic;
	}

	public BigDecimal getSubtotalmes() {
		return subtotalmes;
	}

	public void setSubtotalmes(BigDecimal subtotalmes) {
		this.subtotalmes = subtotalmes;
	}

	public BigDecimal getIgv() {
		return igv;
	}

	public void setIgv(BigDecimal igv) {
		this.igv = igv;
	}

	public BigDecimal getTotalmesact() {
		return totalmesact;
	}

	public void setTotalmesact(BigDecimal totalmesact) {
		this.totalmesact = totalmesact;
	}

	public BigDecimal getAporteley() {
		return aporteley;
	}

	public void setAporteley(BigDecimal aporteley) {
		this.aporteley = aporteley;
	}

	public BigDecimal getCuotaconv() {
		return cuotaconv;
	}

	public void setCuotaconv(BigDecimal cuotaconv) {
		this.cuotaconv = cuotaconv;
	}

	public BigDecimal getRedonmesact() {
		return redonmesact;
	}

	public void setRedonmesact(BigDecimal redonmesact) {
		this.redonmesact = redonmesact;
	}

	public BigDecimal getRedonmesant() {
		return redonmesant;
	}

	public void setRedonmesant(BigDecimal redonmesant) {
		this.redonmesant = redonmesant;
	}

	public BigDecimal getInteresconvenio() {
		return interesconvenio;
	}

	public void setInteresconvenio(BigDecimal interesconvenio) {
		this.interesconvenio = interesconvenio;
	}

	public BigDecimal getEnergactfraptaactual() {
		return energactfraptaactual;
	}

	public void setEnergactfraptaactual(BigDecimal energactfraptaactual) {
		this.energactfraptaactual = energactfraptaactual;
	}

	public BigDecimal getEnergactfraptaanteri() {
		return energactfraptaanteri;
	}

	public void setEnergactfraptaanteri(BigDecimal energactfraptaanteri) {
		this.energactfraptaanteri = energactfraptaanteri;
	}

	public BigDecimal getEnergactfraptadifer() {
		return energactfraptadifer;
	}

	public void setEnergactfraptadifer(BigDecimal energactfraptadifer) {
		this.energactfraptadifer = energactfraptadifer;
	}

	public BigDecimal getEnergactfraptafactor() {
		return energactfraptafactor;
	}

	public void setEnergactfraptafactor(BigDecimal energactfraptafactor) {
		this.energactfraptafactor = energactfraptafactor;
	}

	public BigDecimal getEnergactfraptaconsu() {
		return energactfraptaconsu;
	}

	public void setEnergactfraptaconsu(BigDecimal energactfraptaconsu) {
		this.energactfraptaconsu = energactfraptaconsu;
	}

	public BigDecimal getEnergactfraptaconfa() {
		return energactfraptaconfa;
	}

	public void setEnergactfraptaconfa(BigDecimal energactfraptaconfa) {
		this.energactfraptaconfa = energactfraptaconfa;
	}

	public BigDecimal getEnergactfraptapreuni() {
		return energactfraptapreuni;
	}

	public void setEnergactfraptapreuni(BigDecimal energactfraptapreuni) {
		this.energactfraptapreuni = energactfraptapreuni;
	}

	public BigDecimal getEnergactfraptatotal() {
		return energactfraptatotal;
	}

	public void setEnergactfraptatotal(BigDecimal energactfraptatotal) {
		this.energactfraptatotal = energactfraptatotal;
	}

	public BigDecimal getEnergacthorptaactu() {
		return energacthorptaactu;
	}

	public void setEnergacthorptaactu(BigDecimal energacthorptaactu) {
		this.energacthorptaactu = energacthorptaactu;
	}

	public BigDecimal getEnergacthorptaant() {
		return energacthorptaant;
	}

	public void setEnergacthorptaant(BigDecimal energacthorptaant) {
		this.energacthorptaant = energacthorptaant;
	}

	public BigDecimal getEnergacthorptadif() {
		return energacthorptadif;
	}

	public void setEnergacthorptadif(BigDecimal energacthorptadif) {
		this.energacthorptadif = energacthorptadif;
	}

	public BigDecimal getEnergacthorptafac() {
		return energacthorptafac;
	}

	public void setEnergacthorptafac(BigDecimal energacthorptafac) {
		this.energacthorptafac = energacthorptafac;
	}

	public BigDecimal getEnergacthorptacons() {
		return energacthorptacons;
	}

	public void setEnergacthorptacons(BigDecimal energacthorptacons) {
		this.energacthorptacons = energacthorptacons;
	}

	public BigDecimal getEnergacthorptaconfac() {
		return energacthorptaconfac;
	}

	public void setEnergacthorptaconfac(BigDecimal energacthorptaconfac) {
		this.energacthorptaconfac = energacthorptaconfac;
	}

	public BigDecimal getEnergacthorptapreuni() {
		return energacthorptapreuni;
	}

	public void setEnergacthorptapreuni(BigDecimal energacthorptapreuni) {
		this.energacthorptapreuni = energacthorptapreuni;
	}

	public BigDecimal getEnergacthorptatotal() {
		return energacthorptatotal;
	}

	public void setEnergacthorptatotal(BigDecimal energacthorptatotal) {
		this.energacthorptatotal = energacthorptatotal;
	}

	public BigDecimal getEnergreacinicial() {
		return energreacinicial;
	}

	public void setEnergreacinicial(BigDecimal energreacinicial) {
		this.energreacinicial = energreacinicial;
	}

	public BigDecimal getEnergreacanteri() {
		return energreacanteri;
	}

	public void setEnergreacanteri(BigDecimal energreacanteri) {
		this.energreacanteri = energreacanteri;
	}

	public BigDecimal getEnergreacdifere() {
		return energreacdifere;
	}

	public void setEnergreacdifere(BigDecimal energreacdifere) {
		this.energreacdifere = energreacdifere;
	}

	public BigDecimal getEnergreacfactor() {
		return energreacfactor;
	}

	public void setEnergreacfactor(BigDecimal energreacfactor) {
		this.energreacfactor = energreacfactor;
	}

	public BigDecimal getEnergreacconsu() {
		return energreacconsu;
	}

	public void setEnergreacconsu(BigDecimal energreacconsu) {
		this.energreacconsu = energreacconsu;
	}

	public BigDecimal getEnergreacfaccons() {
		return energreacfaccons;
	}

	public void setEnergreacfaccons(BigDecimal energreacfaccons) {
		this.energreacfaccons = energreacfaccons;
	}

	public BigDecimal getEnergreacpreuni() {
		return energreacpreuni;
	}

	public void setEnergreacpreuni(BigDecimal energreacpreuni) {
		this.energreacpreuni = energreacpreuni;
	}

	public BigDecimal getEnergreactotal() {
		return energreactotal;
	}

	public void setEnergreactotal(BigDecimal energreactotal) {
		this.energreactotal = energreactotal;
	}

	public BigDecimal getPotenciafpini() {
		return potenciafpini;
	}

	public void setPotenciafpini(BigDecimal potenciafpini) {
		this.potenciafpini = potenciafpini;
	}

	public BigDecimal getPotenciafpante() {
		return potenciafpante;
	}

	public void setPotenciafpante(BigDecimal potenciafpante) {
		this.potenciafpante = potenciafpante;
	}

	public BigDecimal getPotenciafpdif() {
		return potenciafpdif;
	}

	public void setPotenciafpdif(BigDecimal potenciafpdif) {
		this.potenciafpdif = potenciafpdif;
	}

	public BigDecimal getPotenciafpfac() {
		return potenciafpfac;
	}

	public void setPotenciafpfac(BigDecimal potenciafpfac) {
		this.potenciafpfac = potenciafpfac;
	}

	public BigDecimal getPotenciafpcons() {
		return potenciafpcons;
	}

	public void setPotenciafpcons(BigDecimal potenciafpcons) {
		this.potenciafpcons = potenciafpcons;
	}

	public BigDecimal getPotenciahpact() {
		return potenciahpact;
	}

	public void setPotenciahpact(BigDecimal potenciahpact) {
		this.potenciahpact = potenciahpact;
	}

	public BigDecimal getPotenciahpant() {
		return potenciahpant;
	}

	public void setPotenciahpant(BigDecimal potenciahpant) {
		this.potenciahpant = potenciahpant;
	}

	public BigDecimal getPotenciahpdif() {
		return potenciahpdif;
	}

	public void setPotenciahpdif(BigDecimal potenciahpdif) {
		this.potenciahpdif = potenciahpdif;
	}

	public BigDecimal getPotenciahpfac() {
		return potenciahpfac;
	}

	public void setPotenciahpfac(BigDecimal potenciahpfac) {
		this.potenciahpfac = potenciahpfac;
	}

	public BigDecimal getPotenciahpcons() {
		return potenciahpcons;
	}

	public void setPotenciahpcons(BigDecimal potenciahpcons) {
		this.potenciahpcons = potenciahpcons;
	}

	public BigDecimal getPotusoreddistconfac() {
		return potusoreddistconfac;
	}

	public void setPotusoreddistconfac(BigDecimal potusoreddistconfac) {
		this.potusoreddistconfac = potusoreddistconfac;
	}

	public BigDecimal getPotusoreddistpreuni() {
		return potusoreddistpreuni;
	}

	public void setPotusoreddistpreuni(BigDecimal potusoreddistpreuni) {
		this.potusoreddistpreuni = potusoreddistpreuni;
	}

	public BigDecimal getPotgenfpconfac() {
		return potgenfpconfac;
	}

	public void setPotgenfpconfac(BigDecimal potgenfpconfac) {
		this.potgenfpconfac = potgenfpconfac;
	}

	public BigDecimal getPotgenfppreuni() {
		return potgenfppreuni;
	}

	public void setPotgenfppreuni(BigDecimal potgenfppreuni) {
		this.potgenfppreuni = potgenfppreuni;
	}

	public BigDecimal getPotusoreddisttotal() {
		return potusoreddisttotal;
	}

	public void setPotusoreddisttotal(BigDecimal potusoreddisttotal) {
		this.potusoreddisttotal = potusoreddisttotal;
	}

	public BigDecimal getPotgenfptotal() {
		return potgenfptotal;
	}

	public void setPotgenfptotal(BigDecimal potgenfptotal) {
		this.potgenfptotal = potgenfptotal;
	}

	public Date getFecEmision() {
		return fecEmision;
	}

	public void setFecEmision(Date fecEmision) {
		this.fecEmision = fecEmision;
	}

	public Date getFecVencimiento() {
		return fecVencimiento;
	}

	public void setFecVencimiento(Date fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}
	
	

}