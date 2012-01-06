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

		$("#buscar-s").button();
		$("#pdf-s").button();
		$("#imprimir-s").button();
		$("#salir-s").button();
		$("#nuevo-s").button();

		$('#pdf-s').click(function() {      
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=REPORTE_ITEMSCOB', 'Popup', caracteristicas);  
	        return false;  
		});
		
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

	<html:form action="/gestionarItemCobranza.do" method="post" styleId="agregarItemForm">
		<input type="hidden" name="metodo" />
		<html:hidden property="codigo"/>
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
					Item de Cobranza</td>
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


		<logic:present name="lstCob">

		<logic:empty name="lstCob"> No existen registros</logic:empty>
			<logic:notEmpty name="lstCob">
		


		<fieldset>
        <!-- table width="200" border="0">
          <tr>
            <td><a id="various1" href="#inline1" title="Nuevo" style="border: 0px"><img src="<%=request.getContextPath()%>/imagenes/nuevo.png" style="width: 50px"/></a></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table -->		
		<display:table name="lstCob" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/gestionarItemCobranza.do"		
						id="row"
						export="false">
				<display:column title="Acciones" style="width:100px;">
					<a href="javascript:eliminar('${row.codItemcobranza}')">
						<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Eliminar..." border="0" width="16" height="16"/>
					</a>
					<a href="javascript:actualizar('${row.codItemcobranza}')">					
						<img src="<%=request.getContextPath()%>/imagenes/manto/editar.png" alt="Editar..." border="0" width="16" height="16"/>
					</a>
				</display:column>
				<display:column title="Id"></display:column>
				<display:column title="Concepto" property="strDescripcion"></display:column>
				<display:column title="Costo" property="numCosto"></display:column>
				<display:column title="Moneda">
							<c:if test="${row.strMoneda == 'S'}">Soles</c:if>
							<c:if test="${row.strMoneda == 'D'}">Dolares</c:if>
				</display:column>
				<display:column title="Unidad de Medida">${row.unidadmedida.strNombre}</display:column>
				<display:column title="Tipo de Cobranza">
							<c:if test="${row.strTipocobranza == 'C '}">Contable</c:if>
							<c:if test="${row.strTipocobranza == 'N '}">No Contable</c:if>
				</display:column>
				<display:column title="Estado">
							<c:if test="${row.numEstado == 1}">Activo</c:if>
							<c:if test="${row.numEstado == 0}">Inactivo</c:if>
				</display:column>
								
		</display:table>
		</fieldset>
			</logic:notEmpty>
		</logic:present>
        

	<div style="display: none;">
		<div id="inline1" style="width:600px;height:300px;overflow:auto;">
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam quis mi eu elit tempor facilisis id et neque. Nulla sit amet sem sapien. Vestibulum imperdiet porta ante ac ornare. Nulla et lorem eu nibh adipiscing ultricies nec at lacus. Cras laoreet ultricies sem, at blandit mi eleifend aliquam. Nunc enim ipsum, vehicula non pretium varius, cursus ac tortor. Vivamus fringilla congue laoreet. Quisque ultrices sodales orci, quis rhoncus justo auctor in. Phasellus dui eros, bibendum eu feugiat ornare, faucibus eu mi. Nunc aliquet tempus sem, id aliquam diam varius ac. Maecenas nisl nunc, molestie vitae eleifend vel, iaculis sed magna. Aenean tempus lacus vitae orci posuere porttitor eget non felis. Donec lectus elit, aliquam nec eleifend sit amet, vestibulum sed nunc.
		</div>
	</div>

	</html:form>

</body>
</html:html>