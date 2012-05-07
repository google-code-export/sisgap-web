package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the SISGAP_SOCIO database table.
 * 
 */
@Entity
@Table(name="SISGAP_SOCIO")
public class Socio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_SOCIO_TRANIDE_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_SOCIO_TRANIDE_GENERATOR")
	@Column(name="TRAN_IDE", unique=true, nullable=false, precision=10)
	private long tranIde;

    @Temporal( TemporalType.DATE)
	private Date creacion;

	@Column(name="LOCA_IDE", precision=10)
	private BigDecimal locaIde;

	@Column(length=60)
	private String lrc;

	@Column(name="TRAN_CODIGO", length=12)
	private String tranCodigo;

	@Column(name="TRAN_CORREO", length=60)
	private String tranCorreo;

	@Column(name="TRAN_DIRECCION", length=60)
	private String tranDireccion;

	@Column(name="TRAN_ESTADO", length=8)
	private String tranEstado;

	@Column(name="TRAN_FAX", length=15)
	private String tranFax;

    @Temporal( TemporalType.DATE)
	@Column(name="TRAN_FECHA_CONSTITUCION")
	private Date tranFechaConstitucion;

    @Temporal( TemporalType.DATE)
	@Column(name="TRAN_FECHAINAC")
	private Date tranFechainac;

	@Column(name="TRAN_MATERNO", length=20)
	private String tranMaterno;

	@Column(name="TRAN_NOMBRE", length=20)
	private String tranNombre;

	@Column(name="TRAN_PATERNO", length=20)
	private String tranPaterno;

	@Column(name="TRAN_PUESTO", length=10)
	private String tranPuesto;

	@Column(name="TRAN_RAZON_SOCIAL", length=60)
	private String tranRazonSocial;

	@Column(name="TRAN_RUC", length=15)
	private String tranRuc;

	@Column(name="TRAN_TELEFONO1", length=15)
	private String tranTelefono1;

	@Column(name="TRAN_TELEFONO2", length=15)
	private String tranTelefono2;

	@Column(precision=10)
	private BigDecimal veces;

	@Column(name="STR_SECTOR", length=2)
	private String strSector;
	
	//bi-directional many-to-one association to SisgapFactura
	@OneToMany(mappedBy="sisgapSocio", cascade={CascadeType.ALL})
	private Set<Factura> sisgapFacturas;

	//bi-directional many-to-one association to SisgapActividadSocio
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="ACTI_TRAN_IDE")
	private ActividadSocio sisgapActividadSocio;

	//bi-directional many-to-one association to SisgapSectorSocio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SECT_TRAN_IDE")
	private SectorSocio sisgapSectorSocio;

	//bi-directional many-to-one association to SisgapTipoSocio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TIPO_TRAN_IDE")
	private TipoSocio sisgapTipoSocio;

    public Socio() {
    }

	public long getTranIde() {
		return this.tranIde;
	}

	public void setTranIde(long tranIde) {
		this.tranIde = tranIde;
	}

	public Date getCreacion() {
		return this.creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public BigDecimal getLocaIde() {
		return this.locaIde;
	}

	public void setLocaIde(BigDecimal locaIde) {
		this.locaIde = locaIde;
	}

	public String getLrc() {
		return this.lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	public String getTranCodigo() {
		return this.tranCodigo;
	}

	public void setTranCodigo(String tranCodigo) {
		this.tranCodigo = tranCodigo;
	}

	public String getTranCorreo() {
		return this.tranCorreo;
	}

	public void setTranCorreo(String tranCorreo) {
		this.tranCorreo = tranCorreo;
	}

	public String getTranDireccion() {
		return this.tranDireccion;
	}

	public void setTranDireccion(String tranDireccion) {
		this.tranDireccion = tranDireccion;
	}

	public String getTranEstado() {
		return this.tranEstado;
	}

	public void setTranEstado(String tranEstado) {
		this.tranEstado = tranEstado;
	}

	public String getTranFax() {
		return this.tranFax;
	}

	public void setTranFax(String tranFax) {
		this.tranFax = tranFax;
	}

	public Date getTranFechaConstitucion() {
		return this.tranFechaConstitucion;
	}

	public void setTranFechaConstitucion(Date tranFechaConstitucion) {
		this.tranFechaConstitucion = tranFechaConstitucion;
	}

	public Date getTranFechainac() {
		return this.tranFechainac;
	}

	public void setTranFechainac(Date tranFechainac) {
		this.tranFechainac = tranFechainac;
	}

	public String getTranMaterno() {
		return this.tranMaterno;
	}

	public void setTranMaterno(String tranMaterno) {
		this.tranMaterno = tranMaterno;
	}

	public String getTranNombre() {
		return this.tranNombre;
	}

	public void setTranNombre(String tranNombre) {
		this.tranNombre = tranNombre;
	}

	public String getTranPaterno() {
		return this.tranPaterno;
	}

	public void setTranPaterno(String tranPaterno) {
		this.tranPaterno = tranPaterno;
	}

	public String getTranPuesto() {
		return this.tranPuesto;
	}

	public void setTranPuesto(String tranPuesto) {
		this.tranPuesto = tranPuesto;
	}

	public String getTranRazonSocial() {
		return this.tranRazonSocial;
	}

	public void setTranRazonSocial(String tranRazonSocial) {
		this.tranRazonSocial = tranRazonSocial;
	}

	public String getTranRuc() {
		return this.tranRuc;
	}

	public void setTranRuc(String tranRuc) {
		this.tranRuc = tranRuc;
	}

	public String getTranTelefono1() {
		return this.tranTelefono1;
	}

	public void setTranTelefono1(String tranTelefono1) {
		this.tranTelefono1 = tranTelefono1;
	}

	public String getTranTelefono2() {
		return this.tranTelefono2;
	}

	public void setTranTelefono2(String tranTelefono2) {
		this.tranTelefono2 = tranTelefono2;
	}

	public BigDecimal getVeces() {
		return this.veces;
	}

	public void setVeces(BigDecimal veces) {
		this.veces = veces;
	}

	public Set<Factura> getSisgapFacturas() {
		return this.sisgapFacturas;
	}

	public void setSisgapFacturas(Set<Factura> sisgapFacturas) {
		this.sisgapFacturas = sisgapFacturas;
	}
	
	public ActividadSocio getSisgapActividadSocio() {
		return this.sisgapActividadSocio;
	}

	public void setSisgapActividadSocio(ActividadSocio sisgapActividadSocio) {
		this.sisgapActividadSocio = sisgapActividadSocio;
	}
	
	public SectorSocio getSisgapSectorSocio() {
		return this.sisgapSectorSocio;
	}

	public void setSisgapSectorSocio(SectorSocio sisgapSectorSocio) {
		this.sisgapSectorSocio = sisgapSectorSocio;
	}
	
	public TipoSocio getSisgapTipoSocio() {
		return this.sisgapTipoSocio;
	}

	public void setSisgapTipoSocio(TipoSocio sisgapTipoSocio) {
		this.sisgapTipoSocio = sisgapTipoSocio;
	}

	public String toString() {
		return getTranRazonSocial();
	}
	
	public String getStrSector() {
		return strSector;
	}

	public void setStrSector(String strSector) {
		this.strSector = strSector;
	}
	
	
}