<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pe.com.mmh.sisgap.system.propiedades.PropiedadesSistema"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>
<%@page import="pe.com.mmh.sisgap.system.domain.Parametro"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

	$(function() {

		$("#nuevo-f").button();
		$("#salir-f").button();
		$("#pdf-f").button();
		$("#imprimir-f").button();
		$("#buscar-f").button();

		$("#impdoc-f").button();
		$("#cancel-f").button();

		//Botón Nuevo		
		$('#nuevo-f').click(function() { 
			$("#selectdoc-form").dialog("open");
		});

		$("#selectdoc-form").dialog({
			autoOpen : false,
			height : 130,
			width : 350,
			modal : true,
			buttons : {
				Generar : function() {
					var frm = document.formFacturacion;
					frm.metodo.value = 'irGrabar';
					frm.submit();
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});		
		
		//Botón Reporte PDF
		$('#pdf-f').click(function() { 
			$("#selectrep-form").dialog("open");
		});
		
		$("#selectrep-form").dialog({
			autoOpen : false,
			height : 150,
			width : 350,
			modal : true,
			buttons : {
				Generar : function() {
					var tipDoc = $("#tipDocx").val();
					var estDoc = $("#estDocx").val();
					var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";    
			        nueva=window.open('ReportsServlet?reporte=REPORTE_DOCUMENTOS_FILTRO&tipDoc='+tipDoc+'&estDoc='+estDoc, 'Popup', caracteristicas);  
					//return false; 
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});


		$('#imprimir-f').click(function() {
			$("#selerepodia-form").dialog("open");
		});				

		$("#selerepodia-form").dialog({
			autoOpen : false,
			height : 150,
			width : 350,
			modal : true,
			buttons : {
				Generar : function() {
					var fecDoc = $("#fechadocumento").val();
					var tipDoc = $("#tipDocY").val();
					var estDoc = $("#estDocY").val();
					//alert("FecDoc: "+fecDoc+"\nTipDoc: "+tipDoc+"\nEstDoc: "+estDoc);
					var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";    
			        nueva=window.open('ReportsServlet?reporte=REPORTE_DIARIO_DOCUMENTOS&fecDoc='+fecDoc+'&tipDoc='+tipDoc+'&estDoc='+estDoc, 'Popup', caracteristicas);  
					//return false; 
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});


		
		//Imagen Anular
		$("#anulardoc-form").dialog({
			autoOpen : false,
			height : 230,
			width : 600,
			modal : true,
			buttons : {
				Anular : function() {
					var frm = document.formFacturacion;									
					$("#descripanulada").val($("#descanulada").val());	
					frm.submit();
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});

		$("#fechadocumento").datepicker({  
			dateFormat: 'dd/mm/yy', 
            changeMonth: true,
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
		
	});

	function lstNuevo() {

	}

	function eliminar(cod) {
		var frm = document.formFacturacion;
		frm.codigoFactura.value = cod;
		frm.metodo.value = 'eliminar';
		$("#anulardoc-form").dialog("open");
	}

	function ver(cod) {
		var frm = document.formFacturacion;
		frm.codigoFactura.value = cod;
		frm.metodo.value = 'ver';
		frm.submit();
	}
	
	function setTipoDoc(cod){
		var frm = document.formFacturacion;
		frm.tipodocumento.value = cod;
	}
</script>
<title>Insert title here</title>
</head>
<body>

	<html:form action="/gestionarFacturacion.do" method="post"	styleId="formFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoFactura" id="codigoFactura"/>
		<input type="hidden" name="descripanulada" id="descripanulada"/>
		<input type="hidden" name="fecdocumento" value="${fechadocumento}" />
		<input type="hidden" name="tipodocumento" id="tipodocumento" value="R"/>
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración/Recibo de Ingreso/Boleta de Venta</td>
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
			<legend>
				<span class="titulo">Listado de Cobranza
					<table>
						<tr>
							<td>
								<select>
									<option value="0" selected>Todos los campos</option>
									<option value="1">Nro. Factura</option>
									<option value="2">Código Socio</option>
									<option value="3">Monto</option>
									<option value="4">Moneda</option>
									<option value="5">Estado</option>
								</select>
							</td>
							<td align="center"><input type="text" maxlength="6" size="16" /></td>
							<td align="center"><input type="button" id="buscar-f" name="buscar-f" value="Busqueda Rápida" onclick="lstBuscar()" /></td>
							<!-- td align="center"><input type="button" id="pdf-f" name="pdf-f" value="PDF" onclick="lstPdf()" /></td -->
							<td align="center"><input type="button" id="pdf-f" name="pdf-f" value="PDF" /></td>
							<td align="center"><input type="button" id="imprimir-f" name="imprimir-f" value="Imprimir" onclick="lstImprimir()" /></td>
							<td align="center"><input type="button" id="nuevo-f" name="nuevo-f" value="Nuevo" /></td>
						</tr>
					</table>
				</span>
			</legend>
		</fieldset>
	
		<fieldset>
		<display:table name="lstFac" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/gestionarFacturacion.do?metodo=cargarAction"		
						id="row"
						export="false">
				<display:column title="" style="width:60px;">
					<c:choose>
						<c:when test="${row.numEstado==1}">
							<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Anular..." border="0" width="16" height="16" id="" onclick="eliminar('${row.codFactura}');"/>
						</c:when>
					</c:choose>
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" onclick="ver('${row.codFactura}');"/>
				</display:column>
				<display:column title="Tipo Documento" sortable="true">
					<c:choose>
						<c:when test="${row.strTipodoc=='R'}">Recibo</c:when>
						<c:when test="${row.strTipodoc=='B'}">Boleta</c:when>
					</c:choose>
				</display:column>
				<display:column title="Nro.Doc.Interno" property="numNrodoc" sortable="true"></display:column>
				<display:column title="Nro.Doc.Impreso" property="nroFactura" sortable="true"></display:column>
				<display:column title="Socio" property="sisgapSocio" sortable="true"></display:column>
				<display:column title="Puesto" sortable="true">${row.sisgapSocio.tranPuesto}</display:column>
				<display:column title="Actividad" sortable="true">${row.sisgapSocio.sisgapActividadSocio.actiTranNombre}</display:column>
				<display:column title="Fecha de Creación" property="datFechacred" sortable="true"></display:column>
				<display:column title="Total" property="numTotal" sortable="true"></display:column>
				<display:column title="Estado" sortable="true">
					<c:choose>
						<c:when test="${row.numEstado==1}">Pendiente</c:when>
						<c:when test="${row.numEstado==2}">Pagada</c:when>
						<c:when test="${row.numEstado==3}">Anulada</c:when>
						<c:when test="${row.numEstado==4}">Impresa</c:when>
					</c:choose>
				</display:column>
		</display:table>
		</fieldset>
        
	<div id="selectdoc-form" title="Seleccione Tipo de Documento a utilizar...">
		<div align="center">	
			<input type="radio" name="tipodoc" value="R" onclick="setTipoDoc('R')" checked>con Recibo<br>
			<input type="radio" name="tipodoc" value="B" onclick="setTipoDoc('B')">con Boleta
		</div>
	</div>
	<div id="anulardoc-form" title="Esta seguro que desea anular el Documento...?  Por favor ingrese el motivo...">
		<div align="center">
			<textarea rows="10" cols="100" id="descanulada" style="BACKGROUND-COLOR: #fdecc4;"></textarea>
		</div>
	</div>
	<div id="selectrep-form" title="Seleccione Tipo de Reporte a mostrar...">
		<div align="center">
			<table>
				<tr>
					<td>Tipos de Documentos :
						<select name="tipDocx" id="tipDocx">
							<option value="T" selected>-- Todos --</option>
							<option value="R" selected="selected">Recibos</option>
							<option value="B">Boletas</option>
						</select>
					</td>
					<td>&nbsp;</td>
					<td>Estado de Documentos :
						<select name="estDocx" id="estDocx">
							<option value="T" selected>-- Todos --</option>
							<option value="P">Pendiente</option>
							<option value="C">Cancelada</option>
							<option value="A">Anulada</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="selerepodia-form" title="Seleccione Tipo de Reporte diario a mostrar...">
		<div align="center">
			<table border="0">
				<tr>
					<td>
						<input type='text' name='fechadocumento' id='fechadocumento' class='text ui-widget-content ui-corner-all' size="20" value="${fechadocumento}" style=" width : 80px;"/>(mes/dia/año)
					</td>
					<td>Tipos de Documentos :
						<select name="tipDocY" id="tipDocY">
							<option value="R">Recibos</option>
							<option value="B">Boletas</option>
						</select>
					</td>
					<td>&nbsp;</td>
					<td>Estado de Documentos :
						<select name="estDocY" id="estDocY">
							<option value="T" selected>-- Todos --</option>
							<option value="P">Pendiente</option>
							<option value="C">Cancelada</option>
							<option value="A">Anulada</option>
						</select>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</html:form>
</body>
</html>