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

<%		
	if (request.getAttribute("total") == null) {
			request.setAttribute("total", 0);
		}

%>

<script type="text/javascript">

	$(function() {

		$("#buscar-r").button();
		$("#pdf-r").button();
		$("#imprimir-r").button();
		$("#salir-r").button();
		$("#nuevo-r").button();
		
	});

	function eliminar(codigo){
		
		var frm = document.agregarItemForm;
		frm.codigo.value = codigo;
		frm.metodo.value = 'eliminar';
		frm.submit();

	}

	function actualizar(codigo){
		
		var frm = document.agregarItemForm;
		frm.codigo.value = codigo;
		frm.metodo.value = 'irActualizar';
		frm.submit();

	}
	
	function cargarArchivo() {
		var frm = document.gestionarClientesActionForm;

		var archivoN = document.getElementsByName("file");
		var archivo = archivoN[0];

		if (frm.file.value == "") {
			pintarError('error', 'Seleccione un archivo de texto');
			return false;
		} else {
			limpiarError('error');
		}

		frm.metodo.value = 'cargarArchivo';s
		frm.submit();

	}
	function regresar() {
		var frm = document.gestionarClientesActionForm;
		frm.metodo.value = 'cargarAction';
		frm.submit();

	}

	function lstNuevo() {
		var frm = document.agregarItemForm;
		frm.metodo.value = 'irGrabar';
		frm.submit();
	}

</script>
</head>
<body>

	<html:form action="/reporteItemsCobranza.do" method="post" styleId="agregarReporteItemForm">
		<input type="hidden" name="metodo" />

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Reportes /
					Items de Cobranza</td>
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
				<span class="titulo">Seleccione Item de Cobranza
				<table>
					<tr>
						<td>
						Item :
							<select>
								<option value="0" selected>Todos los campos</option>
								<option value="1">Id</option>
								<option value="2">Descripción</option>
								<option value="3">Costo</option>
								<option value="4">Moneda</option>
								<option value="5">U/M</option>
								<option value="6">Tipo cobranza</option>
							</select>
						</td>
						<td align="center"><input type="text" maxlength="6" size="16" /></td>
						<td align="center"><input type="button" id="buscar-s" name="buscar-s" value="Busqueda Rápida" onclick="lstBuscar()" /></td>						
						<td align="left"><input type="button" id="pdf-s" name="pdf-s"	value="PDF" onclick="lstPdf()" /></td>
						<td align="left"><input type="button" id="imprimir-s" name="imprimir-s" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="left"><input type="button"	id="salir-s" name="salir-s" value="Salir" onclick="lstSalir()" /></td>
						<td align="left"><input type="button" id="nuevo-s" name="nuevo-s"	value="Nuevo" onclick="lstNuevo()" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>        

	</html:form>

</body>
</html:html>