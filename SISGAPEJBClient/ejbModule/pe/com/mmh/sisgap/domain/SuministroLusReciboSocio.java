package pe.com.mmh.sisgap.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SuministroLusReciboSocio {

	private long codigorecibo;
	private long codigosocio;
	private long correlativo;
	private BigDecimal lecturaini;
	private BigDecimal lecturafin;
	private BigDecimal consumomes;
	private BigDecimal cagofijo;
	private BigDecimal alupublic;
	private BigDecimal cargoener;
	private BigDecimal totalmes;
	private BigDecimal igv;
	private BigDecimal subtotalmes;
	private BigDecimal usoequipo;
	private BigDecimal servmanto;
	private BigDecimal aporteley;
	private BigDecimal recargo;
	private BigDecimal redondeo;
	private BigDecimal total;
	private String nombres;
	private BigDecimal estado; 
	private BigDecimal deudaant;
	private Timestamp fechacarga;
	private BigDecimal puesto;
	private BigDecimal impreso;
	
	public long getCodigorecibo() {
		return codigorecibo;
	}

	public void setCodigorecibo(long codigorecibo) {
		this.codigorecibo = codigorecibo;
	}

	public long getCodigosocio() {
		return codigosocio;
	}

	public void setCodigosocio(long codigosocio) {
		this.codigosocio = codigosocio;
	}

	public long getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(long correlativo) {
		this.correlativo = correlativo;
	}

	public BigDecimal getLecturaini() {
		return lecturaini;
	}

	public void setLecturaini(BigDecimal lecturaini) {
		this.lecturaini = lecturaini;
	}

	public BigDecimal getLecturafin() {
		return lecturafin;
	}

	public void setLecturafin(BigDecimal lecturafin) {
		this.lecturafin = lecturafin;
	}

	public BigDecimal getConsumomes() {
		return consumomes;
	}

	public void setConsumomes(BigDecimal consumomes) {
		this.consumomes = consumomes;
	}

	public BigDecimal getCagofijo() {
		return cagofijo;
	}

	public void setCagofijo(BigDecimal cagofijo) {
		this.cagofijo = cagofijo;
	}

	public BigDecimal getAlupublic() {
		return alupublic;
	}

	public void setAlupublic(BigDecimal alupublic) {
		this.alupublic = alupublic;
	}

	public BigDecimal getCargoener() {
		return cargoener;
	}

	public void setCargoener(BigDecimal cargoener) {
		this.cargoener = cargoener;
	}

	public BigDecimal getTotalmes() {
		return totalmes;
	}

	public void setTotalmes(BigDecimal totalmes) {
		this.totalmes = totalmes;
	}

	public BigDecimal getIgv() {
		return igv;
	}

	public void setIgv(BigDecimal igv) {
		this.igv = igv;
	}

	public BigDecimal getUsoequipo() {
		return usoequipo;
	}

	public void setUsoequipo(BigDecimal usoequipo) {
		this.usoequipo = usoequipo;
	}

	public BigDecimal getServmanto() {
		return servmanto;
	}

	public void setServmanto(BigDecimal servmanto) {
		this.servmanto = servmanto;
	}

	public BigDecimal getAporteley() {
		return aporteley;
	}

	public void setAporteley(BigDecimal aporteley) {
		this.aporteley = aporteley;
	}

	public BigDecimal getRecargo() {
		return recargo;
	}

	public void setRecargo(BigDecimal recargo) {
		this.recargo = recargo;
	}

	public BigDecimal getRedondeo() {
		return redondeo;
	}

	public void setRedondeo(BigDecimal redondeo) {
		this.redondeo = redondeo;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getSubtotalmes() {
		return subtotalmes;
	}

	public void setSubtotalmes(BigDecimal subtotalmes) {
		this.subtotalmes = subtotalmes;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public BigDecimal getEstado() {
		return estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public BigDecimal getDeudaant() {
		return deudaant;
	}

	public void setDeudaant(BigDecimal deudaant) {
		this.deudaant = deudaant;
	}

	public Timestamp getFechacarga() {
		return fechacarga;
	}

	public void setFechacarga(Timestamp fechacarga) {
		this.fechacarga = fechacarga;
	}

	public BigDecimal getPuesto() {
		return puesto;
	}

	public void setPuesto(BigDecimal puesto) {
		this.puesto = puesto;
	}

	public BigDecimal getImpreso() {
		return impreso;
	}

	public void setImpreso(BigDecimal impreso) {
		this.impreso = impreso;
	}

}
