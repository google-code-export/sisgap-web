/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ANDREA
 */
@Entity
@Table(name = "SISGAP_ITEMCOBRANZA", catalog = "", schema = "SISGAP")
@NamedQueries({
		@NamedQuery(name = "Itemcobranza.findAll", query = "SELECT i FROM Itemcobranza i"),
		@NamedQuery(name = "Itemcobranza.findByCodItemcobranza", query = "SELECT i FROM Itemcobranza i WHERE i.codItemcobranza = :codItemcobranza"),
		@NamedQuery(name = "Itemcobranza.findByNumCosto", query = "SELECT i FROM Itemcobranza i WHERE i.numCosto = :numCosto"),
		@NamedQuery(name = "Itemcobranza.findByStrMoneda", query = "SELECT i FROM Itemcobranza i WHERE i.strMoneda = :strMoneda"),
		@NamedQuery(name = "Itemcobranza.findByStrTipocobranza", query = "SELECT i FROM Itemcobranza i WHERE i.strTipocobranza = :strTipocobranza"),
		@NamedQuery(name = "Itemcobranza.findByNumEstado", query = "SELECT i FROM Itemcobranza i WHERE i.numEstado = :numEstado") })
public class Itemcobranza implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id @SequenceGenerator(name="ITEMCOBRANZA_CODITEMCOBRANZA_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEMCOBRANZA_CODITEMCOBRANZA_GENERATOR")
	@Column(name="COD_ITEMCOBRANZA")
    private BigDecimal codItemcobranza;
    @Column(name = "NUM_COSTO", precision = 8, scale = 2)
    private BigDecimal numCosto;
    @Basic(optional = false)
    @Column(name = "STR_MONEDA", nullable = false)
    private String strMoneda;
    @Column(name = "STR_TIPOCOBRANZA", length = 2)
    private String strTipocobranza;
    @Column(name = "NUM_ESTADO")
    private Short numEstado;
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @Column(name = "COD_RECIBOLUZ")
    private BigDecimal codReciboLuz;
    
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="COD_UNIMEDIDA")
	private Unidadmedida unidadmedida;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Detallefactura> detallefacturaSet;

    @Column(name = "COD_ITEMPADRE")
    private long numCodItemPadre;
    
    @Transient
    private String tipocobdes;
    
    @Transient
    private String tipomondes;
    
    public Itemcobranza() {
    }

    public Itemcobranza(BigDecimal codItemcobranza) {
        this.codItemcobranza = codItemcobranza;
    }

    public Itemcobranza(BigDecimal codItemcobranza, String strMoneda) {
        this.codItemcobranza = codItemcobranza;
        this.strMoneda = strMoneda;
    }

    public BigDecimal getCodItemcobranza() {
        return codItemcobranza;
    }

    public void setCodItemcobranza(BigDecimal codItemcobranza) {
        this.codItemcobranza = codItemcobranza;
    }

    public BigDecimal getNumCosto() {
        return numCosto;
    }

    public void setNumCosto(BigDecimal numCosto) {
        this.numCosto = numCosto;
    }

    public String getStrMoneda() {
        return strMoneda;
    }

    public void setStrMoneda(String strMoneda) {
        this.strMoneda = strMoneda;
    }

    public String getStrTipocobranza() {
        return strTipocobranza;
    }

    public void setStrTipocobranza(String strTipocobranza) {
        this.strTipocobranza = strTipocobranza;
    }

    public Short getNumEstado() {
        return numEstado;
    }

    public void setNumEstado(Short numEstado) {
        this.numEstado = numEstado;
    }

	public BigDecimal getCodReciboLuz() {
		return codReciboLuz;
	}

	public void setCodReciboLuz(BigDecimal codReciboLuz) {
		this.codReciboLuz = codReciboLuz;
	}
    
    public Unidadmedida getUnidadmedida() {
		return unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Set<Detallefactura> getDetallefacturaSet() {
        return detallefacturaSet;
    }

    public void setDetallefacturaSet(Set<Detallefactura> detallefacturaSet) {
        this.detallefacturaSet = detallefacturaSet;
    }

    public String getStrDescripcion() {
		return strDescripcion;
	}

	public void setStrDescripcion(String strDescripcion) {
		this.strDescripcion = strDescripcion;
	}
	
	

	public long getNumCodItemPadre() {
		return numCodItemPadre;
	}

	public void setNumCodItemPadre(long numCodItemPadre) {
		this.numCodItemPadre = numCodItemPadre;
	}

	public String getTipocobdes() {
	
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

	public void setTipocobdes(String tipocobdes) {
		this.tipocobdes = tipocobdes;
	}

	public String getTipomondes() {
		
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

	public void setTipomondes(String tipomondes) {
		this.tipomondes = tipomondes;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (codItemcobranza != null ? codItemcobranza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemcobranza)) {
            return false;
        }
        Itemcobranza other = (Itemcobranza) object;
        if ((this.codItemcobranza == null && other.codItemcobranza != null) || (this.codItemcobranza != null && !this.codItemcobranza.equals(other.codItemcobranza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.com.mmh.sisgap.domain.Itemcobranza[codItemcobranza=" + codItemcobranza + "]";
    }
    
    

}
