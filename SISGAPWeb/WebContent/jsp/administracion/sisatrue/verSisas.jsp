<!DOCTYPE html>

<%@page import="org.apache.taglibs.standard.tag.el.fmt.ParseNumberTag"%>
<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
/*demo page css*/
body {
	font: 62.5% "Trebuchet MS", sans-serif;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 5px;
	width: 95%;
	padding: .4em;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

ul#icons {
	margin: 0;
	padding: 0;
}

ul#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

ul#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}


h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 70%;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}



.texto {
	background-color:rgb(189,252,152);
	color:rgb(0,0,0);
}

</style>
</head>
<body>
<html:form action="/registrosisas.do" styleId="registrosisas">
		<input type="hidden" name="metodo" />
		<input type="hidden" id="num_campos" name="num_campos" value="0" />
    	<input type="hidden" id="cant_campos" name="cant_campos" value="0" />
    	<input type="hidden" id="hdnCabecera" name="hdnCabecera" value="" />
    	<input type="hidden" id="hdnItem" name="hdnItem" value="" />
    	<input type="hidden" name="fecdocumento" value="${fechadocumento}" />
    		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Sisas</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td id="mensaje" align="center" valign="middle"	style="display: none"></td>
				<td id="error" align="center" valign="middle" class="mensajeError"	style="display: none"></td>
			</tr>
		</table>
		<logic:notEmpty name="error">
			<table align="center">
				<tr>
					<td id="error" align="center" valign="middle" class="mensajeError">${error}</td>
				</tr>
			</table>
		</logic:notEmpty>

		<logic:notEmpty name="mensaje">
			<table align="center" id="tabalMensaje">
				<tr>
					<td id="mensaje" align="center" valign="middle"	class="mensajeExito">${mensaje}</td>
				</tr>
			</table>
		</logic:notEmpty>

<!-- div class="button_div">    
    <input type="reset" id="btnCancel" name="btnCancel" value="Cancelar" class="buttons_CANCEL" onclick="cancelar();" />
    <input type="button" id="btnAgregar" name="btnAgregar" value="Agregar Persona" class="buttons_aplicar" onclick="agregarFila(document.getElementById('cant_campos'));" />
    <input type="button" id="btnAgregar" name="btnAgregar" value="Guardar" class="buttons_OK" onclick="xajax_guardar(xajax.getFormValues('proyecto'));" />
</div -->

<fieldset class="fieldset">
    <legend class="legend"><b>Nuevo registro - Detalle de Sisas</b></legend>
    <div class="clear"></div>

	<div id="cabecera" title="Fecha del Servicio" class="ui-widget ui-widget-content" width="100%">
		<table border="0" cellpadding="2" cellspacing="2" width="800px">
			<tr>
				<td width="120px" align="left" style="color:red;"><b><label>SISAS:</label></b></td>
				<td align="left" width="200px" style="color:red;">
					<input type="text" id="tipoServicio" name="tipoServicio" class='text ui-widget-content ui-corner-all' value="${strDescripcion}" style=" width : 80px; color: red;"/>
				</td>
				<td width="120px" style="color:red;"><b><label>FECHA DE LA SISA:</label></b></td>
				<td width="350px" style="color:red;">
					<input type='text' name='fechadocumento' id='fechadocumento' class='text ui-widget-content ui-corner-all' size="20" value="${datFechaServ}" style=" width : 80px; color: red;" />(año/mes/dia)
				</td>
			</tr>
		</table>
	</div>
    
    <div id="users-contain" class="ui-widget" width="100%">
		<c:choose>
			<c:when test="${isDetalle!=1 }">
				<button id="btn-agregar-item">Agregar Item</button>
			</c:when>
		</c:choose>
	<table width="100%" id="tblDetalle" name="tblDetalle" class="ui-widget ui-widget-content">
		<thead>
			<tr class="ui-widget-header">
				<th>C&oacutedigo</th>
				<th>Descripci&oacuten</th>
				<th>Del</th>
				<th>Al</th>
				<th>Costo</th>
				<th>Cantidad</th>
				<th>Total</th>
				<th>Acci&oacuten</th>
			</tr>
		</thead>
		<tbody id="tbDetalle">
		</tbody>
		<tr>
			<c:forEach items="${lstSrvDet}" var="row">
				<tr>
					<td>${row.codServiciodetalle }</td>
					<td>${row.sisgapServicioItem.strDescripcion }</td>
					<td>${row.numDel }</td>
					<td>${row.numAl }</td>
					<td>${row.numCosto }</td>
					<td>${row.numCantidad }</td>
					<c:set var="subtotal" value="${row.numCosto * row.numCantidad }" />
					<td>
						<fmt:formatNumber pattern="###,###.##" maxIntegerDigits="2" value="${subtotal}" />
						<!-- c:out value="${subtotal}" / -->
					</td>
					<c:set var="total" value="${total + subtotal }" />
				</tr>
			</c:forEach>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td colspan="3" align="right"><font size="2px" color="blue"><b>Total General S/. <c:out value="${total }" /></b></font></td>
		</tr>		
	</table>
		<c:choose>
			<c:when test="${isDetalle!=1}">
				<button id="btn-registra-sh">Registrar Sisas</button>
			</c:when>
		</c:choose>

    </div>
</fieldset>
				
		<div id="dialog-form-item" title="Agregar Item a Detalle de Sisas" style="display: none">
		<fieldset>					
				<!--legend>Registro</legend-->
				<br>
				<div id="grabarnuevo-form" title="Ingrese los datos de las Sisas...">
					<div align="center">
					<table border="0">
						<tr>
							<td colspan="2">
								<table border="0" cellpadding="0" cellspacing="2" width="500px">
									<!-- tr>
										<td width="120px" align="left"><label>SERVICIO:</label></td>
										<td colspan="5" align="left">
											<select name="tipoServicio" id="tipoServicio" tabindex="1">
												<option value="0" selected="selected">Seleccione</option>
												<option value="1">BAÑO NRO. 1</option>
												<option value="2">BAÑO NRO. 2</option>
												<option value="3">BAÑO NRO. 3</option>
												<option value="4">BAÑO NRO. 4</option>
												<option value="5">BAÑO NRO. 5</option>
												<option value="6">BAÑO NRO. 6</option>
											</select>
										</td>
									</tr -->
									<tr>
										<td align="left"><label>ITEM DE COBRANZA:</label></td>
										<td> 
											<select name="tipoItem" id="tipoItem" onchange="costoSeleccionado(this.value)" tabindex="2">
												<option value="" selected="selected">Seleccione</option>
												<c:forEach items="${lstSI}" var="row">
													<option value="${row.codServicioitem }${row.numCosto}">${row.strDescripcion }</option>
												</c:forEach>
											</select>
										</td>
										<td width="80px"><label>COSTO:</label></td>
										<td colspan="3">
											<input type="text" name="costo" id="costo" value="${row.numCosto}" size="10" disabled="disabled"/>
										</td>
									</tr>
									<tr id="rango" style="display: none;">
										<td align="right"><label>DEL:</label></td>
										<td>
											<input type='text' name='del' id='del' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 100px;" tabindex="3"/>									
										</td>
										<td align="right"><label>AL:</label></td>
										<td>
											<input type='text' name='al' id='al' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 100px;" tabindex="4"/>									
										</td>
									</tr>
									<tr id="unidad" style="display: none;">
										<td align="right"><label>Unidades:</label></td>
										<td>
											<input type='text' name='unidades' id='unidades' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 100px;" tabindex="5"/>									
										</td>
									</tr>
									<tr id="final" style="display: none;">
										<td colspan="3" align="right"><label>TOTAL:</label></td>
										<td>
											<input type='text' name='total' id='total' class='texto' size="10" style=" width : 100px;"  tabindex="6"/>									
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					</div>
				</div>
			</fieldset>
		</div>
		
		</html:form>
</body>
</html:html>