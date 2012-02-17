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
	
		$('#imprimir-sl').click(function() {      
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=RECIBO_LUZ', 'Popup', caracteristicas);  
	        return false;
		});

	
		$("#periodo").datepicker(
	            {   
	                minDate: '+0D',
	                maxDate: '+1Y',
	                changeMonth: false,
	                changeYear: false,
	                numberOfMonths: 1,
	                dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
	                monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo',
	                    'Junio', 'Julio', 'Agosto', 'Septiembre',
	                    'Octubre', 'Noviembre', 'Diciembre'],
	                monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr',
	                    'May', 'Jun', 'Jul', 'Ago',
	                    'Sep', 'Oct', 'Nov', 'Dic'] 
	            });   
             

		
		$("#grabar-form").dialog({
					autoOpen : false,
					height : 380,
					width : 850,
					modal : true,		
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
			});

		
		$('#nuevo-sl').click(function() { 

			$("#grabar-form").dialog("open");
     

		});
		
		
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
		frm.submit();
		//$("#anulardoc-form").dialog("open");
	}

	function ver(cod,lecturaIni,lecturaFin,monto,costoWats,periodo,estado) {

		var frm = document.formFacturacion;
				
		$("#codigoModi").val(cod);	
		$("#lecturaIni").val(lecturaIni);
		$("#lecturaFin").val(lecturaFin);
		$("#monto").val(monto);
		$("#costoWats").val(costoWats);
		$("#periodo").val(periodo);

		if(estado==1){
			$('input[name=estado]').attr('checked', true);
		}else{
			$('input[name=estado]').attr('checked', false);
		}
		$("#grabar-form").dialog("open");

	}

	function grabar() {
		var frm = document.formFacturacion;


		$("#lecturaInix").val($("#lecturaIni").val());
		$("#lecturaFinx").val($("#lecturaFin").val());
		$("#montox").val($("#monto").val());
		$("#costoWatsx").val($("#costoWats").val());
		$("#periodox").val($("#periodo").val());

		if($('input[name=estado]').is(':checked')){
			$("#estadox").val("1");
		}else{
			$("#estadox").val("0");
		}

		if($("#codigoModi").val()!=''){
			frm.metodo.value = 'actualizarReciboLuz';
		}else{
			frm.metodo.value = 'registrarReciboLuz';
		}
		
		
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

	<html:form action="/suministroLuz.do" styleId="formFacturacion">
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
				
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Suministro de Luz</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Recibos de Luz
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
	
		<display:table name="lstRes" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/suministroLuz.do?metodo=cargarAction"		
						id="row"
						export="false">
				<display:column title="" style="width:60px;">
					<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Eliminar..." border="0" width="16" height="16" id="" onclick="eliminar('${row.codOrgreciboLuz}');"/>
					<img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16" onclick="ver(${row.codOrgreciboLuz},${row.numLecturaInicial},${row.numLecturaFinal},${row.numMonto},${row.numCostoWats},'<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecPeriodo}" />',${row.numEstado});"/>
					<img src="<%=request.getContextPath()%>/imagenes/iconos/flecha.png" alt="Ver..." border="0" width="16" height="16" onclick="mostrarItems(${row.codOrgreciboLuz});"/>
				</display:column>
				<display:column title="Periodo" sortable="true">
					<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecPeriodo}" />
				</display:column>
				<display:column title="Lectura Inicial" property="numLecturaInicial" sortable="true"></display:column>
				<display:column title="Lectura Final" property="numLecturaFinal" sortable="true"></display:column>
				<display:column title="Costo por Wats" property="numCostoWats" sortable="true"></display:column>
				<display:column title="Monto" property="numMonto" sortable="true"></display:column>
				<display:column title="Pendiente" property="numPendienteFac" sortable="true"></display:column>				
				<display:column title="Estado" sortable="true">
					<c:choose>
						<c:when test="${row.numEstado==1}">Activo</c:when>
						<c:when test="${row.numEstado==0}">Inactivo</c:when>
					</c:choose>			
				</display:column>
		</display:table>
		
		<div id="grabar-form" title="Agregar Recibo de Luz Original">						
			<table border="1" width="800px" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" width="200px"><font size="1"><b>Energía y Demanda</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Lectura<br>Actual</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Lectura<br>Anterior</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Diferencia</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Factor</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Consumos</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Consumos A<br>Facturar</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Precio<br>Unitario</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Importe<br>Total</b></font></td>
				</tr>
				<tr>
					<td><font size="1">Reposic. y Mant. de Conex</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActRepMan' id='lecturaActRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntRepMan' id='lecturaAntRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaRepMan' id='DiferenciaRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorRepMan' id='FactorRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosRepMan' id='ConsumosRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactRepMan' id='ConsFactRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitRepMan' id='PrecUnitRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalRepMan' id='ImporTotalRepMan' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Cargo Fijo</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActCarFij' id='lecturaActCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntCarFij' id='lecturaAntCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaCarFij' id='DiferenciaCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorCarFij' id='FactorCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosCarFij' id='ConsumosCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactCarFij' id='ConsFactCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitCarFij' id='PrecUnitCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalCarFij' id='ImporTotalCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Energ. Activa Fuera Punta (kwh)</font></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActFuePun' id='lecturaActFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntFuePun' id='lecturaAntFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaFuePun' id='DiferenciaFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorFuePun' id='FactorFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosFuePun' id='ConsumosFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactFuePun' id='ConsFactFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitFuePun' id='PrecUnitFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalFuePun' id='ImporTotalFuePun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Energ. Activa Horas Punta (kwh)</font></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActHorPun' id='lecturaActHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntHorPun' id='lecturaAntHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaHorPun' id='DiferenciaHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorHorPun' id='FactorHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosHorPun' id='ConsumosHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactHorPun' id='ConsFactHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitHorPun' id='PrecUnitHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalHorPun' id='ImporTotalHorPun' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Energía Reactiva (kVARh)</font></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActEneRea' id='lecturaActEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntEneRea' id='lecturaAntEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaEneRea' id='DiferenciaEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorEneRea' id='FactorEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosEneRea' id='ConsumosEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactEneRea' id='ConsFactEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitEneRea' id='PrecUnitEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalEneRea' id='ImporTotalEneRea' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Interés Convenio</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActIntCon' id='lecturaActIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntIntCon' id='lecturaAntIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaIntCon' id='DiferenciaIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorIntCon' id='FactorIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosIntCon' id='ConsumosIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactIntCon' id='ConsFactIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitIntCon' id='PrecUnitIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalIntCon' id='ImporTotalIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Potencia FP (KW)</font></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActPotFP' id='lecturaActPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntPotFP' id='lecturaAntPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaPotFP' id='DiferenciaPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorPotFP' id='FactorPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosPotFP' id='ConsumosPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-- input type='text' name='ConsFactPotFP' id='ConsFactPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='PrecUnitPotFP' id='PrecUnitPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='ImporTotalPotFP' id='ImporTotalPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
				</tr>
				<tr>
					<td><font size="1">Potencia HP (KW)</font></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActPotHP' id='lecturaActPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntPotHP' id='lecturaAntPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaPotHP' id='DiferenciaPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorPotHP' id='FactorPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosPotHP' id='ConsumosPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-- input type='text' name='ConsFactPotHP' id='ConsFactPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='PrecUnitPotHP' id='PrecUnitPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='ImporTotalPotHP' id='ImporTotalPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
				</tr>
				<tr>
					<td><font size="1">Pot. Uso Redes Distrib. F (KW)</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActPotDis' id='lecturaActPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntPotDis' id='lecturaAntPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaPotDis' id='DiferenciaPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorPotDis' id='FactorPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosPotDis' id='ConsumosPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactPotDis' id='ConsFactPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitPotDis' id='PrecUnitPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalPotDis' id='ImporTotalPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Potencia de Generación FP (KW)</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActPotGen' id='lecturaActPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntPotGen' id='lecturaAntPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaPotGen' id='DiferenciaPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorPotGen' id='FactorPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosPotGen' id='ConsumosPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactPotGen' id='ConsFactPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitPotGen' id='PrecUnitPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalPotGen' id='ImporTotalPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Alumbrado Público</font></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActAluPub' id='lecturaActAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntAluPub' id='lecturaAntAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaAluPub' id='DiferenciaAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorAluPub' id='FactorAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosAluPub' id='ConsumosAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactAluPub' id='ConsFactAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center">&nbsp;<!-input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitAluPub' id='PrecUnitAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalAluPub' id='ImporTotalAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1"><b>SUBTOTAL Mes Actual</b></font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActSubTot' id='lecturaActSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntSubTot' id='lecturaAntSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaSubTot' id='DiferenciaSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorSubTot' id='FactorSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosSubTot' id='ConsumosSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactSubTot' id='ConsFactSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitSubTot' id='PrecUnitSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalSubTot' id='ImporTotalSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">I.G.V.</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActIGV' id='lecturaActIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntIGV' id='lecturaAntIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaIGV' id='DiferenciaIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorIGV' id='FactorIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosIGV' id='ConsumosIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactIGV' id='ConsFactIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitIGV' id='PrecUnitIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalIGV' id='ImporTotalIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1"><b>TOTAL Mes Actual</b></font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActTotMes' id='lecturaActTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntTotMes' id='lecturaAntTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaTotMes' id='DiferenciaTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorTotMes' id='FactorTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosTotMes' id='ConsumosTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactTotMes' id='ConsFactTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitTotMes' id='PrecUnitTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalTotMes' id='ImporTotalTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Aporte Ley Nº 28749</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActApoLey' id='lecturaActApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntApoLey' id='lecturaAntApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaApoLey' id='DiferenciaApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorApoLey' id='FactorApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosApoLey' id='ConsumosApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactApoLey' id='ConsFactApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitApoLey' id='PrecUnitApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalApoLey' id='ImporTotalApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Cuota Convenio (10 cuotas)</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActCuoCon' id='lecturaActCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntCuoCon' id='lecturaAntCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaCuoCon' id='DiferenciaCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorCuoCon' id='FactorCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosCuoCon' id='ConsumosCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactCuoCon' id='ConsFactCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitCuoCon' id='PrecUnitCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalCuoCon' id='ImporTotalCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Redondeo Mes Anterior</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActRedAnt' id='lecturaActRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntRedAnt' id='lecturaAntRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaRedAnt' id='DiferenciaRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorRedAnt' id='FactorRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosRedAnt' id='ConsumosRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactRedAnt' id='ConsFactRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitRedAnt' id='PrecUnitRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalRedAnt' id='ImporTotalRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
				<tr>
					<td><font size="1">Redondeo Mes Actual</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActRedAct' id='lecturaActRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntRedAct' id='lecturaAntRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaRedAct' id='DiferenciaRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorRedAct' id='FactorRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosRedAct' id='ConsumosRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactRedAct' id='ConsFactRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitRedAct' id='PrecUnitRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center"><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ImporTotalRedAct' id='ImporTotalRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /></td>
				</tr>
			</table>
				<!-- tr>
					<td><label>Lectura Anterior:</label></td>
					<td><input type='text' name='lecturaIni' id='lecturaIni' class='text ui-widget-content ui-corner-all' size="10" /></td>
					<td><label>Lectura Actual:</label></td>
					<td><input type='text' name='lecturaFin' id='lecturaFin' class='text ui-widget-content ui-corner-all'  size="30" /></td>
				</tr>
				<tr>
					<td><label>Monto:</label></td>
					<td><input type='text' name='monto' id='monto' class='text ui-widget-content ui-corner-all' size="30" /></td>
				</tr>
				<tr>
					<td><label>Costo Wats:</label></td>
					<td><input type='text' name='costoWats' id='costoWats' class='text ui-widget-content ui-corner-all' size="20" /></td>
				</tr>
				<tr>
					<td><label>Periodo:</label></td>
					<td><input type='text' name='periodo' id='periodo' class='text ui-widget-content ui-corner-all'  size="20"/>(mes/dia/año)</td>
				</tr>
				<tr>
					<td><label>Estado:</label></td>
					<td><input type="checkbox" name='estado' id='estado' class='text ui-widget-content ui-corner-all'/></td>
				</tr>
			</table -->
				<button name="btngrabar" id="btngrabar" onclick="grabar();">Grabar</button>
				<button name="btncerrar" id="btncerrar" onclick="cerrarPop();">Cancelar</button>
		</div>
	</html:form>

</body>
</html:html>