<!DOCTYPE html>

<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

$(function() {

		$("#nuevo-f").button();
		$("#salir-f").button();
		$("#pdf-f").button();
		$("#imprimir-f").button();
		$("#buscar-f").button();
		//$("#btncerrar").button();
		//$("#btngrabar").button();
	
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
					height : 250,
					width : 320,
					modal : true,		
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
			});

		
		$('#nuevo-f').click(function() { 

			$("#grabar-form").dialog("open");
     

		});
		
		
	});

	function lstNuevo() {

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
		}else{$
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
		
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
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
						<td align="center"><input type="button" id="buscar-f" name="buscar-f" value="Busqueda Rápida" onclick="lstBuscar()" /></td>
						<td align="center"><input type="button" id="pdf-f" name="pdf-f" value="PDF" onclick="lstPdf()" /></td>
						<td align="center"><input type="button" id="imprimir-f" name="imprimir-f" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="center"><input type="button" id="salir-f" name="salir-f" value="Salir" onclick="lstSalir()" /></td>
						<td align="center"><input type="button" id="nuevo-f" name="nuevo-f" value="Nuevo" /></td>
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
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" onclick="ver(${row.codOrgreciboLuz},${row.numLecturaInicial},${row.numLecturaFinal},${row.numMonto},${row.numCostoWats},'<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecPeriodo}" />',${row.numEstado});"/>
				</display:column>
				<display:column title="Periodo" sortable="true">
					<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecPeriodo}" />
				</display:column>
				<display:column title="Lectura Inicial" property="numLecturaInicial" sortable="true"></display:column>
				<display:column title="Lectura Final" property="numLecturaFinal" sortable="true"></display:column>
				<display:column title="Costo por Wats" property="numCostoWats" sortable="true"></display:column>
				<display:column title="Monto" property="numMonto" sortable="true"></display:column>
				<display:column title="Pendiante" property="numPendienteFac" sortable="true"></display:column>				
				<display:column title="Estado" sortable="true">
					<c:choose>
						<c:when test="${row.numEstado==1}">Activo</c:when>
						<c:when test="${row.numEstado==0}">Inactivo</c:when>
					</c:choose>
				
				</display:column>
		</display:table>
		
		<div id="grabar-form" title="Agregar Recibo de Luz">						
				<br/>
				<table width="300px">
					<tr>
					<td><label>Lectura Inicial:</label></td>
					<td><input type='text' name='lecturaIni' id='lecturaIni' class='text ui-widget-content ui-corner-all' size="10" /></td>
					</tr>
					<tr>
					<td><label>Lectura Final:</label></td>
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
				</table>
				<P/>
			<button name="btngrabar" id="btngrabar" onclick="grabar();">Grabar</button>
			<button name="btncerrar" id="btncerrar" onclick="cerrarPop();">Cancelar</button>
		</div>
	</html:form>

</body>
</html:html>