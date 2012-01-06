package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SISGAP_ACTIVIDAD_SOCIO database table.
 * 
 */
@Entity
@Table(name="SISGAP_ACTIVIDAD_SOCIO")
public class ActividadSocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_ACTIVIDAD_SOCIO_ACTITRANIDE_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_ACTIVIDAD_SOCIO_ACTITRANIDE_GENERATOR")
	@Column(name="ACTI_TRAN_IDE", unique=true, nullable=false, precision=10)
	private long actiTranIde;

	@Column(name="ACTI_TRAN_CODIGO", length=4)
	private String actiTranCodigo;

	@Column(name="ACTI_TRAN_ESTADO", length=8)
	private String actiTranEstado;

    @Temporal( TemporalType.DATE)
	@Column(name="ACTI_TRAN_FECHAINAC")
	private Date actiTranFechainac;

	@Column(name="ACTI_TRAN_NOMBRE", length=40)
	private String actiTranNombre;

    @Temporal( TemporalType.DATE)
	private Date creacion;

	@Column(precision=10)
	private BigDecimal veces;

	//bi-directional many-to-one association to SisgapSocio
	@OneToMany(mappedBy="sisgapActividadSocio", cascade={CascadeType.ALL})
	private Set<Socio> sisgapSocios;

    public ActividadSocio() {
    }

	public long getActiTranIde() {
		return this.actiTranIde;
	}

	public void setActiTranIde(long actiTranIde) {
		this.actiTranIde = actiTranIde;
	}

	public String getActiTranCodigo() {
		return this.actiTranCodigo;
	}

	public void setActiTranCodigo(String actiTranCodigo) {
		this.actiTranCodigo = actiTranCodigo;
	}

	public String getActiTranEstado() {
		return this.actiTranEstado;
	}

	public void setActiTranEstado(String actiTranEstado) {
		this.actiTranEstado = actiTranEstado;
	}

	public Date getActiTranFechainac() {
		return this.actiTranFechainac;
	}

	public void setActiTranFechainac(Date actiTranFechainac) {
		this.actiTranFechainac = actiTranFechainac;
	}

	public String getActiTranNombre() {
		return this.actiTranNombre;
	}

	public void setActiTranNombre(String actiTranNombre) {
		this.actiTranNombre = actiTranNombre;
	}

	public Date getCreacion() {
		return this.creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
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