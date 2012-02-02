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
@Table(name="SISGAP_RECIBOLUZ_ORG")
public class ReciboluzOrg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_RECIBOLUZ_ORG_CODORGRECIBOLUZ_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_RECIBOLUZ_ORG_CODORGRECIBOLUZ_GENERATOR")
	@Column(name="COD_ORGRECIBO_LUZ")
	private long codOrgreciboLuz;

    @Temporal( TemporalType.DATE)
	@Column(name="FEC_PERIODO")
	private Date fecPeriodo;

	@Column(name="NUM_COSTO_WATS")
	private BigDecimal numCostoWats;

	@Column(name="NUM_ESTADO")
	private BigDecimal numEstado;

	@Column(name="NUM_LECTURA_FINAL")
	private BigDecimal numLecturaFinal;

	@Column(name="NUM_LECTURA_INICIAL")
	private BigDecimal numLecturaInicial;

	@Column(name="NUM_MONTO")
	private BigDecimal numMonto;

	@Column(name="NUM_PENDIENTE_FAC")
	private BigDecimal numPendienteFac;

	//bi-directional many-to-one association to SisgapSumistroLuz
	@OneToMany(mappedBy="sisgapReciboluzOrg")
	private Set<SumistroLuz> sisgapSumistroLuzs;

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
	
}