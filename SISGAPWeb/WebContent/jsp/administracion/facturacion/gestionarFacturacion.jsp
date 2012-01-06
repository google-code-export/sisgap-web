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

		$("#selectdoc-form").dialog({
					autoOpen : false,
					height : 150,
					width : 300,
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

		$("#anulardoc-form").dialog({
			autoOpen : false,
			height : 280,
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
		
		$('#nuevo-f').click(function() { 

			$("#selectdoc-form").dialog("open");
     

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

	<html:form action="/gestionarFacturacion.do" method="post"
		styleId="formFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoFactura" id="codigoFactura"/>
		<input type="hidden" name="descripanulada" id="descripanulada"/>
		<input type="hidden" name="tipodocumento" id="tipodocumento" value="R"/>
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
					Facturación</td>
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
				<span class="titulo">Listado de Cobranza
				<table>
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
				<display:column title="Nro Documento" property="numNrodoc" sortable="true"></display:column>
				<display:column title="Socio" property="sisgapSocio" sortable="true"></display:column>
				
				<display:column title="Fecha de Creación" property="datFechacred" sortable="true"></display:column>
				<display:column title="Total" property="numTotal" sortable="true"></display:column>
				<display:column title="Estado" sortable="true">
					<c:choose>
						<c:when test="${row.numEstado==1}">Pendiante</c:when>
						<c:when test="${row.numEstado==2}">Cancelada</c:when>
						<c:when test="${row.numEstado==3}">Anulada</c:when>
					</c:choose>
				
				</display:column>
		</display:table>
		</fieldset>
        
	<div id="selectdoc-form" title="Seleccione Tipo de Documento">
		<div align="center">	
			<input type="radio" name="tipodoc" value="R" onclick="setTipoDoc('R')" checked> Recibo<br>
			<input type="radio" name="tipodoc" value="B" onclick="setTipoDoc('B')"> Boleta
		</div>
	</div>
	<div id="anulardoc-form" title="Anular Documento">
		<div align="center">	
			Esta seguro?. favor ingrese el motivo.<br>
			<textarea rows="12" cols="90" id="descanulada"></textarea>
		</div>
	</div>
	</html:form>

</body>
</html>