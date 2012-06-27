<!DOCTYPE html>

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
<script type="text/javascript">

	function pagar(){
		$('[name=metodo]').val('cancelarFactura');	
		$('#gestionarFacturacion').submit();
	}

	function costoSeleccionado(valor){
		//alert(valor+" - "+valor.substring(1));
		var valor1 = valor.substring(1);
		$('[name=costo]').val(valor1);
	}
	
	function calcularTotal(){
		var valor1 = 0;
		var valor2 = 0;
		var valor3 = 0;
		valor1 = $('[name=del]').val();
		valor2 = $('[name=al]').val();
		valor3 = $('[name=costo]').val();
		if(valor2 > valor1){
			$('[name=total]').val((valor2-valor1)*valor3);
		}
	}
	
</script>
</head>
<body>
<html:form action="/gestionarFacturacion.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Servicios Higienicos</td>
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

		<fieldset>
			<legend><b>Nuevo registro - Detalle de Factura</b></legend>
			<span>
				<div id="users-contain" class="ui-widget" width="100%">				
				</div>
			</span>
		</fieldset>
				
		<div id="dialog-form-item" title="Agregar Item a Detalle de Factura">
		<fieldset>					
				<!--legend>Registro</legend-->
				<br>
				<div id="grabarnuevo-form" title="Ingrese los datos de los Servicios...">
					<div align="center">
						<table border="1" cellpadding="0" cellspacing="0" width="250px">
							<tr>
								<td width="100px">Servicio:</td>
								<td width="150px" class="texto">
									<select name="tipoServicio" id="tipoServicio">
										<option value="0" selected="selected">Seleccione</option>
										<option value="1">BAÑO NRO. 1</option>
										<option value="2">BAÑO NRO. 2</option>
										<option value="3">BAÑO NRO. 3</option>
										<option value="4">BAÑO NRO. 4</option>
										<option value="5">BAÑO NRO. 5</option>
										<option value="6">BAÑO NRO. 6</option>
									</select>
								</td>
							</tr>
							<tr>
								<td><label>Item de Cobranza:</label></td>
								<td> 
									<select name="tipoItem" id="tipoItem" onchange="costoSeleccionado(this.value)">
										<option value="" selected="selected">Seleccione</option>
										<c:forEach items="${lstSI}" var="row">
											<option value="${row.codServicioitem }${row.numCosto}">${row.strDescripcion }</option>
										</c:forEach>
									</select>
								</td>
								<td>Costo:</td>
								<td>
									<input type="text" name="costo" id="costo" value="${row.numCosto}" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td>
									<label>Del:</label>
								</td>
								<td>
									<input type='text' name='del' id='del' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 50px;" tabindex="6"/>									
								</td>
								<td>
									<label>Al:</label>
								</td>
								<td>
									<input type='text' name='al' id='al' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 50px;" tabindex="6"/>									
								</td>
								<td>
									<label>Total:</label>
								</td>
								<td>
									<input type='text' name='total' id='total' class='texto' size="10" style=" width : 50px;" tabindex="6"/>									
								</td>

							</tr>
						</table>
					</div>
				</div>

				<br>
				<button id="btn-aceptar-item" tabindex="10">Agregar</button>
				<button id="btn-cancelar" tabindex="11">Cancelar</button>
			</fieldset>
		</div>
		</html:form>
</body>
</html:html>