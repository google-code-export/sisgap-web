package pe.com.mmh.sisgap.domain;

import java.math.BigDecimal;

public class ReciboLuzAsociado {
	
	public BigDecimal codigoSocio;
	public BigDecimal codigoRecibo;
	public BigDecimal correlativo;
	public BigDecimal total;
	public BigDecimal totalReciboOrg;
	public String periodo;
	public String estado;
	
	public BigDecimal getCodigoSocio() {
		return codigoSocio;
	}
	
	public void setCodigoSocio(BigDecimal codigoSocio) {
		this.codigoSocio = codigoSocio;
	}
	
	public BigDecimal getCodigoRecibo() {
		return codigoRecibo;
	}
	
	public void setCodigoRecibo(BigDecimal codigoRecibo) {
		this.codigoRecibo = codigoRecibo;
	}
	
	public BigDecimal getCorrelativo() {
		return correlativo;
	}
	
	public void setCorrelativo(BigDecimal correlativo) {
		this.correlativo = correlativo;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getTotalReciboOrg() {
		return totalReciboOrg;
	}
	
	public void setTotalReciboOrg(BigDecimal totalReciboOrg) {
		this.totalReciboOrg = totalReciboOrg;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
