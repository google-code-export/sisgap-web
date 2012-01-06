package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the SISGAP_ITEM_SUMISTRO_LUZ database table.
 * 
 */
@Entity
@Table(name="SISGAP_ITEM_SUMISTRO_LUZ")
public class ItemSumistroLuz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_ITEM_SUMISTRO_LUZ_CODITEMSUM_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_ITEM_SUMISTRO_LUZ_CODITEMSUM_GENERATOR")
	@Column(name="COD_ITEMSUM")
	private long codItemsum;

	@Column(name="FLG_TOTAL")
	private BigDecimal flgTotal;

	@Column(name="NUM_ESTADO")
	private BigDecimal numEstado;

	@Column(name="NUM_VALOR")
	private BigDecimal numValor;

	@Column(name="STR_NOMBRE")
	private String strNombre;

	//bi-directional many-to-one association to SisgapSumistroLuzDet
	@OneToMany(mappedBy="sisgapItemSumistroLuz")
	private Set<SumistroLuzDet> sisgapSumistroLuzDets;

    public ItemSumistroLuz() {
    }

	public long getCodItemsum() {
		return this.codItemsum;
	}

	public void setCodItemsum(long codItemsum) {
		this.codItemsum = codItemsum;
	}

	public BigDecimal getFlgTotal() {
		return this.flgTotal;
	}

	public void setFlgTotal(BigDecimal flgTotal) {
		this.flgTotal = flgTotal;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public BigDecimal getNumValor() {
		return this.numValor;
	}

	public void setNumValor(BigDecimal numValor) {
		this.numValor = numValor;
	}

	public String getStrNombre() {
		return this.strNombre;
	}

	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}

	public Set<SumistroLuzDet> getSisgapSumistroLuzDets() {
		return this.sisgapSumistroLuzDets;
	}

	public void setSisgapSumistroLuzDets(Set<SumistroLuzDet> sisgapSumistroLuzDets) {
		this.sisgapSumistroLuzDets = sisgapSumistroLuzDets;
	}
	
}