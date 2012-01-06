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
function grabar() {
	var frm = document.agragarItem;
	frm.metodo.value = 'grabar';
	frm.submit();

}
function lstCancelar() {
	var frm = document.agragarItem;
	frm.metodo.value = 'cargarAction';
	frm.submit();

}
</script>
</head>
<body>

	<html:form action="/gestionarItemCobranza.do" method="post" styleId="agragarItem">
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
						<td width="30%" align="right">Concepto</td>
						<td><input type="text"  size="50" name="txtconcepto"/></td>
					</tr>
					<tr>
						<td width="30%" align="right">Concepto Padre</td>
						<td>
							<select id="cbConceptPadre" name="cbConceptPadre">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstCob}" var="row">
									<option value="${row.codItemcobranza}">${row.strDescripcion}</option>										
								</c:forEach>
							</select>
						</td>
					</tr>					
					<tr>
						<td width="30%" align="right">Costo</td>
						<td><input type="text" size="10" name="txtcosto"/></td>
					</tr>
					<tr>
						<td width="30%" align="right">Moneda</td>
						<td>
							<select id="selMoneda" name="txtmoneda">
								<option value="" selected>Seleccione</option>
								<option value="S">Soles</option>
								<option value="D">Dolares</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="30%" align="right">U/M</td>
						<td>
							<select id="selUM" name="cbmedida">
								<option value="" selected>Seleccione</option>
								<c:forEach items="${lstMedidas}" var="row">
									<option value="${row.codUnimedida}">${row.strNombre}</option>										
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td width="30%" align="right">Tipo de Cobranza</td>
						<td>
							<select id="selCobranza" name="cbtipocob">
								<option value="" selected>Seleccione</option>
								<option value="C">Contable</option>
								<option value="N">No Contable</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="30%" align="right">Estado</td>
						<td>
							<select id="selEstado" name="cbestado">
								<option value="" selected>Seleccione</option>
								<option value="1">Activo</option>
								<option value="0">Inactivo</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center"><input type="button"	value="Grabar" onclick="grabar()" /></td>
						<td align="center"><input type="button"	value="Cancelar" onclick="lstCancelar()" /></td>
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