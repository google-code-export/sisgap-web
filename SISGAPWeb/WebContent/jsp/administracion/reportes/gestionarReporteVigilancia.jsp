<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pe.com.mmh.sisgap.system.propiedades.PropiedadesSistema"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>
<%@page import="pe.com.mmh.sisgap.system.domain.Parametro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true"%>



<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.validate.js"></script>



<%
	if (request.getAttribute("total") == null) {
			request.setAttribute("total", 0);
		}
%>

<style type="text/css">
#info {
	border: 1px solid;
	margin: 10px 0px;
	padding: 15px 10px 15px 50px;
	background-repeat: no-repeat;
	background-position: 10px center;
	position: relative;
	color: #00529B;
	background-color: #BDE5F8;
	background-image: url('info.png');
}
</style>


<script type="text/javascript">
	$(function() {

		$("#buscar-rs").button();
		$("#pdf-rs").button();
		$("#imprimir-rs").button();
		//$("#salir-r").button();
		$("#nuevo-rs").button();
		$("#reporte-rs").button();

		$('#pdf-rs')
				.click(
						function() {							
							var fecIni = $('[name=fechaIni]').val();
							var fecFin = $('[name=fechaFin]').val();

							//alert("FechaIni: "+fecIni+"\nfechaFin: "+fecFin);
							//$('[name=fechaIni]').val(fecIni);
							//$('[name=fechaFin]').val(fecFin);
							alert("El siguiente reporte mostrará la última consulta realizada");

							var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";
							nueva = window.open(
									'ReportsServlet?reporte=LISTADO_GENERAL_VIGILANCIA&fechaInicial='
											+ fecIni + '&fechaFinal=' + fecFin,
									'Popup', caracteristicas);
						});

		//Botón Imprimir
		$('#reporte-rs').click(function() {
			$("#selectfecha-form").dialog("open");
		});

		$("#selectfecha-form").dialog(
				{
					autoOpen : false,
					height : 200,
					width : 400,
					modal : true,
					buttons : {
						Generar : function() {
							var codSocio = $('#cbSocio option:selected').val();
							var fecIni = $('#fechaInicial').val();
							var fecFin = $('#fechaFinal').val();
							
							$('[name=fechaIni]').val(fecIni);
							$('[name=fechaFin]').val(fecFin);
							$('[name=codigo]').val(codSocio);

							$.blockUI({ message: "<h3><img src='<%=request.getContextPath()%>/imagenes/busy.gif' /> Espere un momento...procesando</h3>" });
							
							//$.blockUI({ message: "<h3><img src='busy.gif' /> Espere un momento...procesando</h3>" });
							
							$('[name=metodo]').val('grabarVigilancia');
							$('#agregarReporteSisa').submit();
							
							$(this).dialog("close");
		
						},
						Cancel : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
		
				});

		$('.date-picker1').datepicker(
				{
					changeMonth : true,
					changeYear : true,
					showButtonPanel : false,
					dateFormat : 'mm/yy',
					monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril',
							'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre',
							'Octubre', 'Noviembre', 'Diciembre' ],
					monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May',
							'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ]
				//onClose: function(dateText, inst) { 
				//var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
				//var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
				//$(this).datepicker('setDate', new Date(year, month, 1));
				//}
				});

		$("#buscar-rs").click(function() {
			alert("boton [Buscar]... en mantenimiento ");
		});

		$("#imprimir-rs").click(function() {
			alert("boton [Imprimir]... en mantenimiento ");
		});

		$("#nuevo-rs").click(function() {
			alert("boton [Nuevo]... en mantenimiento ");
		});

	});

	$(".close").click(function() {
		$("#info").animate({
			left : "+=10px"
		}).animate({
			left : "-5000px"
		});
	});

	function terminaBloqueo(valor, valor2, valor3) {
		var fecIni = valor2;
		var fecFin = valor3;
		
		if (valor == '1') {
			$.unblockUI();

			var caracteristicas = "height=500,width=800,resizable=1,scrollbars=1,location=0";
			nueva = window.open("ReportsServlet?reporte=LISTADO_GENERAL_VIGILANCIA&fechaInicial=" + fecIni + "&fechaFinal=" + fecFin,	"Popup", caracteristicas);			
			valor = null; fecIni = null; fecFin = null;
		}
	}

	// bad implementation
	function sleep(milliSeconds) {
		var startTime = new Date().getTime(); // get the current time
		while (new Date().getTime() < startTime + milliSeconds)
			; // hog cpu
	}
</script>
<style type="text/css">
.ui-datepicker-calendar {
	display: block;
}
</style>

</head>
<body
	onload="terminaBloqueo(document.getElementById('variable').value, document.getElementById('fechaIni').value, document.getElementById('fechaFin').value);">

	<html:form action="/reporteSisas.do" method="post"
		styleId="agregarReporteSisa">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigo" />
		<input type="hidden" name="fechaIni"
			value="<%=session.getAttribute("fechaIni")%>" />
		<input type="hidden" name="fechaFin"
			value="<%=session.getAttribute("fechaFin")%>" />
		<input type="hidden" id="variable" name="variable"
			value="<%=session.getAttribute("Respuesta")%>" />

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Listado Reporte
					General</td>
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

		<!-- div id="dialogLoading" title="Cargando Datos...">
	           <p>Espere miestras se procesan los datos...</p>
		</div -->

		<fieldset>
			<legend>
				<span class="titulo">Vigilancia
					<table border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td>Socio : <select id="cbSocio" name="cbSocio"
								style="width: 300px">
									<option value="0" selected>Seleccione</option>
									<c:forEach items="${lstSocio}" var="row">
										<option value="${row.tranCodigo}">${row.tranRazonSocial}</option>
									</c:forEach>
							</select>
							</td>
							<td align="center"><input type="text" maxlength="6"
								size="16" /></td>
							<td align="center"><input type="button" id="buscar-rs"
								name="buscar-rs" value="Busqueda Rápida" /></td>
							<td align="left"><input type="button" id="pdf-rs"
								name="pdf-rs" value="PDF" /></td>
							<td align="left"><input type="button" id="imprimir-rs"
								name="imprimir-rs" value="Imprimir" /></td>
							<td align="left"><input type="button" id="nuevo-rs"
								name="nuevo-rs" value="Nuevo" onclick="lstNuevo()" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><input type="button" id="reporte-rs" name="reporte-rs"
								value="Generar Reporte General de socios" /></td>
						</tr>
					</table>
				</span>
			</legend>
		</fieldset>

		<div id="selectfecha-form"
			title="Seleccione rango de fechas del reporte...">
			<div align="center">
				<table border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td><b>Fecha Inicial: </b><input type="text"
							name="fechaInicial" id="fechaInicial" class="date-picker1" />(mes/año)</td>
						<td>&nbsp;</td>
						<td><b>Fecha Final: </b><input type="text" name="fechaFinal"
							id="fechaFinal" class="date-picker1" />(mes/año)</td>
					</tr>
				</table>
			</div>
		</div>

	</html:form>

</body>
</html:html>