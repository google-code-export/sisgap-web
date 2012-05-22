<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pe.com.mmh.sisgap.system.propiedades.PropiedadesSistema"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>
<%@page import="pe.com.mmh.sisgap.system.domain.Parametro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">
$(function() {

	$("#btn-Grabar").button();
	$("#btn-Cancelar").button();
});

function grabar() {
	var frm = document.agregarItem;
	frm.metodo.value = 'actualizar';
	frm.submit();

}
function lstCancelar() {
	var frm = document.agregarItem;
	frm.metodo.value = 'cargarAction';
	frm.submit();

}
</script>
</head>
<body>

	<html:form action="/gestionarItemCobranza.do" method="post" styleId="agregarItem">
		<input type="hidden" name="metodo" value="grabar"/>

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
					Item de Cobranza</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td id="mensaje" align="center" valign="middle"
					style="display: none"></td>

				<td id="error" align="center" valign="middle" class="mensajeError"
					style="display: none"></td>

			</tr>
		</table>
		<logic:notEmpty name="error">
			<table align="center">
				<tr>

					<td id="error" align="center" valign="middle" class="mensajeError">
						${error}</td>
				</tr>
			</table>
		</logic:notEmpty>

		<logic:notEmpty name="mensaje">
			<table align="center" id="tabalMensaje">
				<tr>
					<td id="mensaje" align="center" valign="middle"
						class="mensajeExito">${mensaje}</td>
				</tr>
			</table>
		</logic:notEmpty>

		<fieldset>
			<legend>
				<span class="titulo">Nuevo Registro
				<table border="0" cellpadding="2" cellspacing="2" width="80%">
					<tr>
						<td width="200" align="right">Concepto</td>
						<td><input type="text"  size="50" name="txtconcepto" value="${objItem.strDescripcion}"/></td>
					</tr>
					<tr>
						<td align="right">Concepto Padre</td>
						<td>
							<select id="cbConceptPadre" name="cbConceptPadre" style="width: 300px">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstCob}" var="row">
									<c:choose>
										<c:when test="${objItem.numCodItemPadre == row.codItemcobranza }">
											<option value="${row.codItemcobranza}" selected="selected">${row.strDescripcion}</option>
										</c:when>
										<c:otherwise>
											<option value="${row.codItemcobranza}">${row.strDescripcion}</option>
										</c:otherwise>
									</c:choose>
																			
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Recibo de Luz</td>
						<td>
							<select id="cbReciboLuz" name="cbReciboLuz" style="width: 150px">
								<option value="0" selected="selected">Seleccione</option>
								<c:forEach items="${lstOrg}" var="reg">
									<c:when test="${objItem.codOrgreciboLuz == reg.codOrgreciboLuz }">
										<option value="${reg.codOrgreciboLuz}" selected="selected">${reg.fecPeriodo}</option>
									</c:when>
									<c:otherwise>
										<option value="${reg.codOrgreciboLuz}">${reg.fecPeriodo}</option>
									</c:otherwise>
								</c:forEach>
							</select>
						</td>
					</tr>				
					<tr>
						<td align="right">Costo</td>
						<td><input type="text" size="10" name="txtcosto" value="${objItem.numCosto}"/></td>
					</tr>
					<tr>
						<td align="right">Moneda</td>
						<td>
							<select id="selMoneda" name="txtmoneda" style="width: 100px">
								<option value="" selected>Seleccione</option>
								<c:choose>
									<c:when test="${objItem.strMoneda =='S' }">
										<option value="S" selected>Soles</option>
										<option value="D">Dolares</option>
									</c:when>
									<c:when test="${objItem.strMoneda =='D' }">
										<option value="S">Soles</option>
										<option value="D" selected >Dolares</option>
									</c:when>																
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">U/M</td>
						<td>
							<select id="selUM" name="cbmedida" style="width: 100px">
								<option value="">Seleccione</option>
								<c:forEach items="${lstMedidas}" var="row">
									<c:choose>
									<c:when test="${row.codUnimedida == objItem.unidadmedida.codUnimedida}">
										<option value="${row.codUnimedida}" selected>${row.strNombre}</option>
									</c:when>
									<c:otherwise>
										<option value="${row.codUnimedida}">${row.strNombre}</option>										
									</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Tipo de Cobranza</td>
						<td>
							<select id="selCobranza" name="cbtipocob" style="width: 100px">
								<option value="" selected>Seleccione</option>
								<c:choose>
									<c:when test="${objItem.strTipocobranza =='C ' }">
										<option value="C" selected>Contable</option>
										<option value="N">No Contable</option>
									</c:when>
									<c:when test="${objItem.strTipocobranza =='N ' }">
										<option value="C">Contable</option>
										<option value="N" selected>No Contable</option>
									</c:when>																
								</c:choose>								
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Estado</td>
						<td>
							<select id="selEstado" name="cbestado" style="width: 100px">
								<option value="" selected>Seleccione</option>
								<c:choose>
									<c:when test="${objItem.numEstado == 1 }">
										<option value="1" selected>Activo</option>
										<option value="0">Inactivo</option>
									</c:when>
									<c:when test="${objItem.numEstado == 0 }">
										<option value="1">Activo</option>
										<option value="0" selected>Inactivo</option>
									</c:when>																
								</c:choose>									
							</select>
						</td>
					</tr>
					<tr>
						<td align="center"><input type="button"	id="btn-Grabar" value="Grabar" onclick="grabar()" /></td>
						<td align="center"><input type="button"	id="btn-Cancelar" value="Cancelar" onclick="lstCancelar()" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>        

	<div style="display: none;">
		<div id="inline1" style="width:600px;height:300px;overflow:auto;">
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam quis mi eu elit tempor facilisis id et neque. Nulla sit amet sem sapien. Vestibulum imperdiet porta ante ac ornare. Nulla et lorem eu nibh adipiscing ultricies nec at lacus. Cras laoreet ultricies sem, at blandit mi eleifend aliquam. Nunc enim ipsum, vehicula non pretium varius, cursus ac tortor. Vivamus fringilla congue laoreet. Quisque ultrices sodales orci, quis rhoncus justo auctor in. Phasellus dui eros, bibendum eu feugiat ornare, faucibus eu mi. Nunc aliquet tempus sem, id aliquam diam varius ac. Maecenas nisl nunc, molestie vitae eleifend vel, iaculis sed magna. Aenean tempus lacus vitae orci posuere porttitor eget non felis. Donec lectus elit, aliquam nec eleifend sit amet, vestibulum sed nunc.
		</div>
	</div>

	</html:form>

</body>
</html:html>