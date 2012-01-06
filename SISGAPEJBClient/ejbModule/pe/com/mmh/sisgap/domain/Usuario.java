package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the SISGAP_USUARIO database table.
 * 
 */
@Entity
@Table(name="SISGAP_USUARIO")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SISGAP_USUARIO_CODUSURIO_GENERATOR", sequenceName="SQ_GENERAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SISGAP_USUARIO_CODUSURIO_GENERATOR")
	@Column(name="COD_USURIO")
	private long codUsurio;

	@Column(name="NUM_ESTADO")
	private BigDecimal numEstado;

	@Column(name="STR_LOGIN")
	private String strLogin;

	@Column(name="STR_NOMBRE")
	private String strNombre;

	@Column(name="STR_PWR")
	private String strPwr;

	//bi-directional many-to-one association to SisgapSumistroLuz
	@OneToMany(mappedBy="sisgapUsuario")
	private Set<SumistroLuz> sisgapSumistroLuzs;

    public Usuario() {
    }

	public long getCodUsurio() {
		return this.codUsurio;
	}

	public void setCodUsurio(long codUsurio) {
		this.codUsurio = codUsurio;
	}

	public BigDecimal getNumEstado() {
		return this.numEstado;
	}

	public void setNumEstado(BigDecimal numEstado) {
		this.numEstado = numEstado;
	}

	public String getStrLogin() {
		return this.strLogin;
	}

	public void setStrLogin(String strLogin) {
		this.strLogin = strLogin;
	}

	public String getStrNombre() {
		return this.strNombre;
	}

	public void setStrNombre(String strNombre) {
		this.strNombre = strNombre;
	}

	public String getStrPwr() {
		return this.strPwr;
	}

	public void setStrPwr(String strPwr) {
		this.strPwr = strPwr;
	}

	public Set<SumistroLuz> getSisgapSumistroLuzs() {
		return this.sisgapSumistroLuzs;
	}

	public void setSisgapSumistroLuzs(Set<SumistroLuz> sisgapSumistroLuzs) {
		this.sisgapSumistroLuzs = sisgapSumistroLuzs;
	}
	
}