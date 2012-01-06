package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SISGAP_TIPO_SOCIO database table.
 * 
 */
@Entity
@Table(name="SISGAP_TIPO_SOCIO")
public class TipoSocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_TIPO_SOCIO_TIPOTRANIDE_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_TIPO_SOCIO_TIPOTRANIDE_GENERATOR")
	@Column(name="TIPO_TRAN_IDE", unique=true, nullable=false, precision=10)
	private long tipoTranIde;

    @Temporal( TemporalType.DATE)
	private Date creacion;

	@Column(name="TIPO_TRAN_CODIGO", length=4)
	private String tipoTranCodigo;

	@Column(name="TIPO_TRAN_ESTADO", length=8)
	private String tipoTranEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="TIPO_TRAN_FECHAINAC")
	private Date tipoTranFechainac;

	@Column(name="TIPO_TRAN_NOMBRE", length=40)
	private String tipoTranNombre;

	@Column(precision=10)
	private BigDecimal veces;

	//bi-directional many-to-one association to SisgapSocio
	@OneToMany(mappedBy="sisgapTipoSocio")
	private Set<Socio> sisgapSocios;

    public TipoSocio() {
    }

	public long getTipoTranIde() {
		return this.tipoTranIde;
	}

	public void setTipoTranIde(long tipoTranIde) {
		this.tipoTranIde = tipoTranIde;
	}

	public Date getCreacion() {
		return this.creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public String getTipoTranCodigo() {
		return this.tipoTranCodigo;
	}

	public void setTipoTranCodigo(String tipoTranCodigo) {
		this.tipoTranCodigo = tipoTranCodigo;
	}

	public String getTipoTranEstado() {
		return this.tipoTranEstado;
	}

	public void setTipoTranEstado(String tipoTranEstado) {
		this.tipoTranEstado = tipoTranEstado;
	}

	public Date getTipoTranFechainac() {
		return this.tipoTranFechainac;
	}

	public void setTipoTranFechainac(Date tipoTranFechainac) {
		this.tipoTranFechainac = tipoTranFechainac;
	}

	public String getTipoTranNombre() {
		return this.tipoTranNombre;
	}

	public void setTipoTranNombre(String tipoTranNombre) {
		this.tipoTranNombre = tipoTranNombre;
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