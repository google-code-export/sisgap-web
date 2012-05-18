package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SISGAP_FACTURA database table.
 * 
 */
@Entity
@Table(name="SISGAP_FACTURA")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_FACTURA_CODFACTURA_GENERATOR")
	@SequenceGenerator(name="SISGAP_FACTURA_CODFACTURA_GENERATOR", sequenceName="SQ_GENERAL")
	@Column(name="COD_FACTURA")
	private long codFactura;

	@Column(name="NRO_FACTURA", length=100)
	private String nroFactura;

	@Column(name="NUM_ESTADO", precision=22)
	private BigDecimal numEstado;

	@Column(name="NUM_NRODOC", precision=22)
	private BigDecimal numNrodoc;

	@Column(name="NUM_TOTAL", precision=11, scale=2)
	private BigDecimal numTotal;

	@Column(name="STR_TIPODOC", length=1)
	private String strTipodoc;
	
    @Temporal( TemporalType.DATE)
	@Column(name="DAT_FECHACRED")
	private Date datFechacred;
    
    @Temporal( TemporalType.DATE)
	@Column(name="DAT_FECHAFAC")
	private Date datFechafac;
    
	@Column(name="STR_DESC_ANULADA", length=2000)
	private String strDescanulada;
	//bi-directional many-to-one association to SisgapDetallefactura
	@OneToMany(mappedBy="sisgapFactura", fetch=FetchType.EAGER)
	private List<Detallefactura> sisgapDetallefacturas;
	
	//bi-directional many-to-one association to SisgapSocio
	@ManyToOne( fetch=FetchType.EAGER)
	@JoinColumn(name="TRAN_IDE")
	private Socio sisgapSocio; 

	@Transient
	private String actiTranNombre;
	
	@Transient
	private String tranPuesto;
	
	@Transient
	private String nombresocio;
	
	public Factura() {
    }

	public long getCodFactura() {
		return this.codFactura;
	}

	public void setCodFactura(long codFactura) {
		this.codFactura = codFactura;
	}

	public String getNroFactura() {
		return this.nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public BigDecimal getNumNrodoc() {
		return this.numNrodoc;
	}

	public void setNumNrodoc(BigDecimal numNrodoc) {
		this.numNrodoc = numNrodoc;
	}

	public BigDecimal getNumTotal() {
		return this.numTotal;
	}

	public void setNumTotal(BigDecimal numTotal) {
		this.numTotal = numTotal;
	}

	public String getStrTipodoc() {
		return this.strTipodoc;
	}

	public void setStrTipodoc(String strTipodoc) {
		this.strTipodoc = strTipodoc;
	}

	public List<Detallefactura> getSisgapDetallefacturas() {
		return (List<Detallefactura>) this.sisgapDetallefacturas;
	}

	public void setSisgapDetallefacturas(List<Detallefactura> listDetallefactura) {
		this.sisgapDetallefacturas = listDetallefactura;
	}
	
	public Socio getSisgapSocio() {
		return this.sisgapSocio;
	}

	public void setSisgapSocio(Socio sisgapSocio) {
		this.sisgapSocio = sisgapSocio;
	}

	public Date getDatFechacred() {
		return datFechacred;
	}

	public void setDatFechacred(Date datFechacred) {
		this.datFechacred = datFechacred;
	}

	public String getStrDescanulada() {
		return strDescanulada;
	}

	public void setStrDescanulada(String strDescanulada) {
		this.strDescanulada = strDescanulada;
	}



	public String getActiTranNombre() {
		return actiTranNombre;
	}

	public void setActiTranNombre(String actiTranNombre) {
		this.actiTranNombre = actiTranNombre;
	}

	public String getTranPuesto() {
		return tranPuesto;
	}

	public void setTranPuesto(String tranPuesto) {
		this.tranPuesto = tranPuesto;
	}

	public String getNombresocio() {
		return nombresocio;
	}

	public void setNombresocio(String nombresocio) {
		this.nombresocio = nombresocio;
	}

	public Date getDatFechafac() {
		return datFechafac;
	}

	public void setDatFechafac(Date datFechafac) {
		this.datFechafac = datFechafac;
	}
	
}