package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SISGAP_SUMISTRO_LUZ database table.
 * 
 */
@Entity
@Table(name="SISGAP_SUMISTRO_LUZ")
public class SumistroLuz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_SUMISTRO_LUZ_CODSUMISTROLUZ_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_SUMISTRO_LUZ_CODSUMISTROLUZ_GENERATOR")
	@Column(name="COD_SUMISTRO_LUZ")
	private long codSumistroLuz;

    @Temporal( TemporalType.DATE)
	@Column(name="FEC_SUMISTRO")
	private Date fecSumistro;

	@Column(name="NUM_CONSUMO_MES")
	private BigDecimal numConsumoMes;

	@Column(name="NUM_DEU_ANTE")
	private BigDecimal numDeuAnte;

	@Column(name="NUM_ESTADO")
	private BigDecimal numEstado;

	@Column(name="NUM_IGV")
	private BigDecimal numIgv;

	@Column(name="NUM_LEC_ACTUAL")
	private BigDecimal numLecActual;

	@Column(name="NUM_LEC_ANTERIOR")
	private BigDecimal numLecAnterior;

	@Column(name="NUM_TOTAL")
	private BigDecimal numTotal;

	//bi-directional many-to-one association to SisgapSocio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TRAN_IDE")
	private Socio sisgapSocio;

	//bi-directional many-to-one association to SisgapUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COD_USURIO")
	private Usuario sisgapUsuario;

	//bi-directional many-to-one association to SisgapSumistroLuzDet
	@OneToMany(mappedBy="sisgapSumistroLuz")
	private Set<SumistroLuzDet> sisgapSumistroLuzDets;

    public SumistroLuz() {
    }

	public long getCodSumistroLuz() {
		return this.codSumistroLuz;
	}

	public void setCodSumistroLuz(long codSumistroLuz) {
		this.codSumistroLuz = codSumistroLuz;
	}

	public Date getFecSumistro() {
		return this.fecSumistro;
	}

	public void setFecSumistro(Date fecSumistro) {
		this.fecSumistro = fecSumistro;
	}

	public BigDecimal getNumConsumoMes() {
		return this.numConsumoMes;
	}

	public void setNumConsumoMes(BigDecimal numConsumoMes) {
		this.numConsumoMes = numConsumoMes;
	}

	public BigDecimal getNumDeuAnte() {
		return this.numDeuAnte;
	}

	public void setNumDeuAnte(BigDecimal numDeuAnte) {
		this.numDeuAnte = numDeuAnte;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public BigDecimal getNumIgv() {
		return this.numIgv;
	}

	public void setNumIgv(BigDecimal numIgv) {
		this.numIgv = numIgv;
	}

	public BigDecimal getNumLecActual() {
		return this.numLecActual;
	}

	public void setNumLecActual(BigDecimal numLecActual) {
		this.numLecActual = numLecActual;
	}

	public BigDecimal getNumLecAnterior() {
		return this.numLecAnterior;
	}

	public void setNumLecAnterior(BigDecimal numLecAnterior) {
		this.numLecAnterior = numLecAnterior;
	}

	public BigDecimal getNumTotal() {
		return this.numTotal;
	}

	public void setNumTotal(BigDecimal numTotal) {
		this.numTotal = numTotal;
	}

	public Socio getSisgapSocio() {
		return this.sisgapSocio;
	}

	public void setSisgapSocio(Socio sisgapSocio) {
		this.sisgapSocio = sisgapSocio;
	}
	
	public Usuario getSisgapUsuario() {
		return this.sisgapUsuario;
	}

	public void setSisgapUsuario(Usuario sisgapUsuario) {
		this.sisgapUsuario = sisgapUsuario;
	}
	
	public Set<SumistroLuzDet> getSisgapSumistroLuzDets() {
		return this.sisgapSumistroLuzDets;
	}

	public void setSisgapSumistroLuzDets(Set<SumistroLuzDet> sisgapSumistroLuzDets) {
		this.sisgapSumistroLuzDets = sisgapSumistroLuzDets;
	}
	
}