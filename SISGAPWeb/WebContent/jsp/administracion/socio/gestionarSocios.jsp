<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pe.com.mmh.sisgap.system.propiedades.PropiedadesSistema"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>
<%@page import="pe.com.mmh.sisgap.system.domain.Parametro"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

	$(function() {
	
		$("#buscar-s").button();
		$("#pdf-s").button();
		$("#imprimir-s").button();
		$("#salir-s").button();
		$("#nuevo-s").button();
	
		$('#pdf-s').click(function() {      
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=REPORTE_SOCIO', 'Popup', caracteristicas);  
	        return false;  
		});
		
	});

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

		frm.metodo.value = 'cargarArchivo';
		frm.submit();

	}
	function regresar() {
		var frm = document.gestionarClientesActionForm;
		frm.metodo.value = 'cargarAction';
		frm.submit();

	}

	function buscar() {

		var frm = document.gestionarClientesActionForm;
		frm.metodo.value = 'listarClientes';
		frm.submit();
	}

	function lstNuevo() {
		var frm = document.agregarClientesForm;
		frm.metodo.value = 'irGrabar';
		frm.submit();
	}
</script>
</head>
<body>

	<html:form action="/gestionarSocios.do" method="post" styleId="agregarClientesForm"
		enctype="multipart/form-data">
		<input type="hidden" name="metodo" />

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
					Gestionar Socios</td>
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
				<span class="titulo">Búsqueda de Socios
				<table>
					<tr>
						<td>
							<select>
								<option value="0" selected>Todos los campos</option>
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
		<fieldset>
			<display:table name="lstSocios" 
							class="consultanormal"
							excludedParams="metodo" 
							requestURI="/gestionarSocios.do?metodo=cargarAction"		
							id="row"
							export="false">
				<display:column title="Codigo" property="tranCodigo" sortable="true"></display:column>
				<display:column title="Apellidos y Nombres" property="tranRazonSocial" sortable="true"></display:column>
				<display:column title="Puesto" property="tranPuesto" sortable="true"></display:column>
				<display:column title="Actividad" property="sisgapActividadSocio.actiTranNombre" sortable="true"></display:column>
				<display:column title="Estado" property="tranEstado" sortable="true"></display:column>
			</display:table>
		</fieldset>
	</html:form>

</body>
</html:html>