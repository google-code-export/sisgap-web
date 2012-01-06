package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SISGAP_SECTOR_SOCIO database table.
 * 
 */
@Entity
@Table(name="SISGAP_SECTOR_SOCIO")
public class SectorSocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_SECTOR_SOCIO_SECTTRANIDE_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_SECTOR_SOCIO_SECTTRANIDE_GENERATOR")
	@Column(name="SECT_TRAN_IDE", unique=true, nullable=false, precision=10)
	private long sectTranIde;

    @Temporal( TemporalType.DATE)
	private Date creacion;

	@Column(name="SECT_TRAN_CODIGO", length=4)
	private String sectTranCodigo;

	@Column(name="SECT_TRAN_ESTADO", length=8)
	private String sectTranEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="SECT_TRAN_FECHAINAC")
	private Date sectTranFechainac;

	@Column(name="SECT_TRAN_NOMBRE", length=40)
	private String sectTranNombre;

	@Column(precision=10)
	private BigDecimal veces;

	//bi-directional many-to-one association to SisgapSocio
	@OneToMany(mappedBy="sisgapSectorSocio")
	private Set<Socio> sisgapSocios;

    public SectorSocio() {
    }

	public long getSectTranIde() {
		return this.sectTranIde;
	}

	public void setSectTranIde(long sectTranIde) {
		this.sectTranIde = sectTranIde;
	}

	public Date getCreacion() {
		return this.creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public String getSectTranCodigo() {
		return this.sectTranCodigo;
	}

	public void setSectTranCodigo(String sectTranCodigo) {
		this.sectTranCodigo = sectTranCodigo;
	}

	public String getSectTranEstado() {
		return this.sectTranEstado;
	}

	public void setSectTranEstado(String sectTranEstado) {
		this.sectTranEstado = sectTranEstado;
	}

	public Date getSectTranFechainac() {
		return this.sectTranFechainac;
	}

	public void setSectTranFechainac(Date sectTranFechainac) {
		this.sectTranFechainac = sectTranFechainac;
	}

	public String getSectTranNombre() {
		return this.sectTranNombre;
	}

	public void setSectTranNombre(String sectTranNombre) {
		this.sectTranNombre = sectTranNombre;
	}

	public BigDecimal getVeces() {
		return this.veces;
	}

	public void setVeces(BigDecimal veces) {
		this.veces = veces;
	}

	public Set<Socio> getSisgapSocios() {
		return this.sisgapSocios;
	}

	public void setSisgapSocios(Set<Socio> sisgapSocios) {
		this.sisgapSocios = sisgapSocios;
	}
	
}