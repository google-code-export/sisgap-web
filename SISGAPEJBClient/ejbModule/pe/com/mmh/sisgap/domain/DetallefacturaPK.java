package pe.com.mmh.sisgap.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SISGAP_DETALLEFACTURA database table.
 * 
 */
@Embeddable
public class DetallefacturaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_ITEMCOBRANZA", unique=true, nullable=false, precision=22)
	private long codItemcobranza;

	@Column(name="COD_FACTURA", unique=true, nullable=false, precision=22)
	private long codFactura;

    public DetallefacturaPK() {
    }
	public long getCodItemcobranza() {
		return this.codItemcobranza;
	}
	public void setCodItemcobranza(long codItemcobranza) {
		this.codItemcobranza = codItemcobranza;
	}
	public long getCodFactura() {
		return this.codFactura;
	}
	public void setCodFactura(long codFactura) {
		this.codFactura = codFactura;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallefacturaPK)) {
			return false;
		}
		DetallefacturaPK castOther = (DetallefacturaPK)other;
		return 
			(this.codItemcobranza == castOther.codItemcobranza)
			&& (this.codFactura == castOther.codFactura);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codItemcobranza ^ (this.codItemcobranza >>> 32)));
		hash = hash * prime + ((int) (this.codFactura ^ (this.codFactura >>> 32)));
		
		return hash;
    }
}