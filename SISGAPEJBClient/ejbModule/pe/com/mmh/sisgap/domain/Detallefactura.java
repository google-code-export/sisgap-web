package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SISGAP_DETALLEFACTURA database table.
 * 
 */
@Entity
@Table(name="SISGAP_DETALLEFACTURA")
public class Detallefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetallefacturaPK id;

	@Column(name="COD_ITEMPADRE", precision=22)
	private BigDecimal codItempadre;

	@Column(name="COD_UNIMEDIDA", precision=22)
	private BigDecimal codUnimedida;

	@Column(name="NUM_ACUENTA", precision=11, scale=2)
	private BigDecimal numAcuenta;

	@Column(name="NUM_CANTIDAD", precision=22)
	private BigDecimal numCantidad;

	@Column(name="NUM_COSTO", precision=11, scale=2)
	private BigDecimal numCosto;

	@Column(name="NUM_ESTADO", precision=11, scale=2)
	private BigDecimal numEstado;

	@Column(name="STR_DESCRIPCION", length=60)
	private String strDescripcion;

	@Column(name="STR_MONEDA", length=20)
	private String strMoneda;

	@Column(name="STR_TIPOCOBRANZA", length=2)
	private String strTipocobranza;

	//bi-directional many-to-one association to SisgapFactura
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="COD_FACTURA", nullable=false, insertable=false, updatable=false)
	private Factura sisgapFactura;

	//bi-directional many-to-one association to SisgapItemcobranza
	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinColumn(name="COD_ITEMCOBRANZA", nullable=false, insertable=false, updatable=false)
	private Itemcobranza sisgapItemcobranza;
	
	@Transient
	private String strTipocobranzaDescrip;
	@Transient
	private String strMonedaDescrip;
	@Transient
	private BigDecimal numTotal;
	

    public Detallefactura() {
    }

	public DetallefacturaPK getId() {
		return this.id;
	}

	public void setId(DetallefacturaPK id) {
		this.id = id;
	}
	
	public BigDecimal getCodItempadre() {
		return this.codItempadre;
	}

	public void setCodItempadre(BigDecimal codItempadre) {
		this.codItempadre = codItempadre;
	}

	public BigDecimal getCodUnimedida() {
		return this.codUnimedida;
	}

	public void setCodUnimedida(BigDecimal codUnimedida) {
		this.codUnimedida = codUnimedida;
	}

	public BigDecimal getNumAcuenta() {
		return this.numAcuenta;
	}

	public void setNumAcuenta(BigDecimal numAcuenta) {
		this.numAcuenta = numAcuenta;
	}

	public BigDecimal getNumCantidad() {
		return this.numCantidad;
	}

	public void setNumCantidad(BigDecimal numCantidad) {
		this.numCantidad = numCantidad;
	}

	public BigDecimal getNumCosto() {
		return this.numCosto;
	}

	public void setNumCosto(BigDecimal numCosto) {
		this.numCosto = numCosto;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public String getStrDescripcion() {
		return this.strDescripcion;
	}

	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}

	public String getStrMoneda() {
		return this.strMoneda;
	}

	public void setStrMoneda(String strMoneda) {
		this.strMoneda = strMoneda;
	}

	public String getStrTipocobranza() {
		return this.strTipocobranza;
	}

	public void setStrTipocobranza(String strTipocobranza) {
		this.strTipocobranza = strTipocobranza;
	}

	public Factura getSisgapFactura() {
		return this.sisgapFactura;
	}

	public void setSisgapFactura(Factura sisgapFactura) {
		this.sisgapFactura = sisgapFactura;
	}
	
	public Itemcobranza getSisgapItemcobranza() {
		return this.sisgapItemcobranza;
	}

	public void setSisgapItemcobranza(Itemcobranza sisgapItemcobranza) {
		this.sisgapItemcobranza = sisgapItemcobranza;
	}

	
	/** transient */

	public String getStrTipocobranzaDescrip() {
		
		String valor = "";
		if (strTipocobranza != null) {
			if (strTipocobranza.trim().equals("C")) {
				valor = "Contable";
			} else if (strTipocobranza.trim().equals("N")) {
				valor = "No Contable";
			}
		}
		
		return valor;
	}

	public void setStrTipocobranzaDescrip(String strTipocobranzaDescrip) {
		this.strTipocobranzaDescrip = strTipocobranzaDescrip;
	}

	public String getStrMonedaDescrip() {
		
		String valor = "";
		if (strMoneda != null) {
			if (strMoneda.trim().equals("S")) {
				valor = "Soles";
			} else if (strMoneda.trim().equals("D")) {
				valor = "Dolares";
			}
		}
		
		return valor;
	}

	public void setStrMonedaDescrip(String strMonedaDescrip) {
		this.strMonedaDescrip = strMonedaDescrip;
	}

	public BigDecimal getNumTotal() {
		return numCantidad.multiply(numCosto);
	}

	public void setNumTotal(BigDecimal numTotal) {
		this.numTotal = numTotal;
	}

	
}