<!DOCTYPE html>

<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	.texto {
		background-color:rgb(189,252,152);
		color:rgb(0,0,0);
	}
</style>

<script type="text/javascript">

$(function() {

		$("#nuevo-sl").button();
		$("#salir-sl").button();
		$("#pdf-sl").button();
		$("#imprimir-sl").button();
		$("#buscar-sl").button();
		
		$("#btngrabar").button();
		$("#btncerrar").button();
	

	});
	
	
	function lstNuevo() {

	}

	function mostrarItems(cod) {
		var frm = document.formFacturacion;
		frm.codigoModi.value = cod;//$("#codigoModi").val(cod);
		frm.metodo.value = 'mostrarItemsSuministro';
		frm.submit();
	}
	
	function eliminar(cod) {
		var frm = document.formFacturacion;
		$("#codigoModi").val(cod);
		frm.metodo.value = 'eliminarReciboLuz';
		$("#eliminar-form").dialog("open");
		//$("#anulardoc-form").dialog("open");
	}

	function grabar() {
		var frm = document.formFacturacion;
		
		frm.submit();
	}

	function cerrarPop() {
		$("#grabar-form").dialog("close");
	}
		
	function setTipoDoc(cod){
		var frm = document.formFacturacion;
		frm.tipodocumento.value = cod;
	}

	/* Por Johan Muñoz
	/* Funciones de validación de campos */
	function validar(){
		var texto = document.getElementById("ImporTotalRepMan");
		var cant = /^([0-9])*$/;
		if(!texto.match(cant)){
			alert("no es numero...");
		}else{
			alert("es numero...");
		}
	}
	
	function soloNumeros(evt){
		alert(evt);
		if(window.event){
			keynum=evt.keycode;
		}else{
			keynum=evt.which;
		}
		alert(keynum);
		if(keynum>47 && keynum<58){
			return true;
		}else{
			return false;
		}
	}
	
	/* Funciones de cálculo para el recibo de luz */
	function calcular_Diferencia(lecAct,lecAnt) {
		alert("Actual="+lecAct+" Anterior="+lecAnt);
		var dif = lecAnt.val()-lecAct.val();
		alert("Diferencia="+dif);
		return dif;
	}
	
</script>
<title>Insert title here</title>
</head>
<body>

	<html:form action="/registrosisas.do" styleId="formFacturacion">
		<input type="hidden" name="metodo" />
		
		<input type="hidden" name="descripanulada" id="descripanulada"/>
		<input type="hidden" name="tipodocumento" id="tipodocumento" value="R"/>
		
		<input type="hidden" name="codigoModi" id="codigoModi"/>
		<input type="hidden" name="lecturaInix" id="lecturaInix"/>
		<input type="hidden" name="lecturaFinx" id="lecturaFinx"/>
		<input type="hidden" name="montox" id="montox"/>
		<input type="hidden" name="costoWatsx" id="costoWatsx"/>
		<input type="hidden" name="periodox" id="periodox"/>
		<input type="hidden" name="estadox" id="estadox"/>
		
		<input type='hidden' name='fecvencimiento' id='fecvencimiento'/>
		<input type='hidden' name='fecemision' id='fecemision'/>


		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Sisas</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Sisas
				<table border="0">
					<tr>
						<td>
							<select>
								<option value="0" selected>Todos los campos</option>
								<option value="1">Nro Factura</option>
								<option value="2">Codigo Socio</option>
								<option value="3">Monto</option>
								<option value="4">Moneda</option>
								<option value="5">Estado</option>
							</select>
						</td>
						<td align="center"><input type="text" maxlength="6" size="16" /></td>
						<td align="center"><input type="button" id="buscar-sl" name="buscar-sl" value="Busqueda Rápida" onclick="lstBuscar()" /></td>
						<td align="center"><input type="button" id="pdf-sl" name="pdf-sl" value="PDF" onclick="lstPdf()" /></td>
						<td align="center"><input type="button" id="imprimir-sl" name="imprimir-sl" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="center"><input type="button" id="nuevo-sl" name="nuevo-sl" value="Nuevo" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>
	${lstPlan}
		<display:table name="lstPlanx" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/registrosisas.do?metodo=cargarAction"		
						id="row"
						export="false">
		</display:table>

	</html:form>

</body>
</html:html>