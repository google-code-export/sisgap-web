package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SISGAP_SUMISTRO_LUZ_DET database table.
 * 
 */
@Entity
@Table(name="SISGAP_SUMISTRO_LUZ_DET")
public class SumistroLuzDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_SUMISTRO_LUZ_DET_CODITEMSUMDET_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_SUMISTRO_LUZ_DET_CODITEMSUMDET_GENERATOR")
	@Column(name="COD_ITEMSUM_DET")
	private long codItemsumDet;

	@Column(name="NUM_ESTADO")
	private BigDecimal numEstado;

	@Column(name="NUM_VALOR")
	private BigDecimal numValor;

	@Column(name="STR_NOMBRE")
	private String strNombre;

	//bi-directional many-to-one association to SisgapItemSumistroLuz
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COD_ITEMSUM")
	private ItemSumistroLuz sisgapItemSumistroLuz;

	//bi-directional many-to-one association to SisgapSumistroLuz
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="COD_SUMISTRO_LUZ")
	private SumistroLuz sisgapSumistroLuz;

    public SumistroLuzDet() {
    }

	public long getCodItemsumDet() {
		return this.codItemsumDet;
	}

	public void setCodItemsumDet(long codItemsumDet) {
		this.codItemsumDet = codItemsumDet;
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

	public ItemSumistroLuz getSisgapItemSumistroLuz() {
		return this.sisgapItemSumistroLuz;
	}

	public void setSisgapItemSumistroLuz(ItemSumistroLuz sisgapItemSumistroLuz) {
		this.sisgapItemSumistroLuz = sisgapItemSumistroLuz;
	}
	
	public SumistroLuz getSisgapSumistroLuz() {
		return this.sisgapSumistroLuz;
	}

	public void setSisgapSumistroLuz(SumistroLuz sisgapSumistroLuz) {
		this.sisgapSumistroLuz = sisgapSumistroLuz;
	}
	
}