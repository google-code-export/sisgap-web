package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the SISGAP_UNIDADMEDIDA database table.
 * 
 */
@Entity
@Table(name="SISGAP_UNIDADMEDIDA")
public class Unidadmedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_UNIMEDIDA", unique=true, nullable=false, precision=22)
	private BigDecimal codUnimedida;

	@Column(name="NUM_ESTADO", precision=22)
	private BigDecimal numEstado;

	@Column(name="STR_NOMBRE", length=20)
	private String strNombre;

	//bi-directional many-to-one association to SisgapItemcobranza
	@OneToMany(mappedBy="unidadmedida", cascade={CascadeType.ALL})
	private Set<Itemcobranza> sisgapItemcobranzas;

    public Unidadmedida() {
    }

	public BigDecimal getCodUnimedida() {
		return this.codUnimedida;
	}

	public void setCodUnimedida(BigDecimal codUnimedida) {
		this.codUnimedida = codUnimedida;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public String getStrNombre() {
		return this.strNombre;
	}

	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}

	public Set<Itemcobranza> getSisgapItemcobranzas() {
		return this.sisgapItemcobranzas;
	}

	public void setSisgapItemcobranzas(Set<Itemcobranza> sisgapItemcobranzas) {
		this.sisgapItemcobranzas = sisgapItemcobranzas;
	}
	
}