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
<script src="jquery.js" type="text/javascript"></script> <script src="jquery.validate.js" type="text/javascript"></script>
<%		
	if (request.getAttribute("total") == null) {
			request.setAttribute("total", 0);
		}

%>

<style type="text/css">
	#info{
	    border: 1px solid;
	    margin: 10px 0px;
	    padding:15px 10px 15px 50px;
	    background-repeat: no-repeat;
	    background-position: 10px center;
	    position:relative;
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

		
		//Botón Imprimir
		$('#pdf-rs').click(function() {

			var codSocio = $('#cbSocio option:selected').val();

			$('[name=codigo]').val(codSocio);
			
			$('[name=metodo]').val('grabarVigilancia');
			$('#agregarReporteSisa').submit();
			
			if ($('#cbSocio option:selected').text() == "Seleccione") {
				alert("El proceso tiene un tiempo de demora de 2 minutos aproximadamente...");
				sleep(10000);
			}
			
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";    
	        nueva=window.open('ReportsServlet?reporte=LISTADO_GENERAL_VIGILANCIA', 'Popup', caracteristicas);
			//alert( Trim($('#cbSocio option:selected').val()) + " - " + $('#cbSocio option:selected').text());

			$('[name=metodo]').val('cargarAction');
			$('#agregarReporteSisa').submit();			 			
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

	$(".close").click(function(){
        $("#info").animate({left:"+=10px"}).animate({left:"-5000px"});
    });



	// bad implementation
	function sleep(milliSeconds){
		var startTime = new Date().getTime(); // get the current time
		while (new Date().getTime() < startTime + milliSeconds); // hog cpu
	}
</script>
</head>
<body>

	<html:form action="/reporteSisas.do" method="post" styleId="agregarReporteSisa">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigo" />

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Listado Reporte General</td>
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
				<span class="titulo">Vigilancia
				<table border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td>
						Socio :
							<select id="cbSocio" name="cbSocio" style="width: 300px">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstSocio}" var="row">
									<option value="${row.tranCodigo}">${row.tranRazonSocial}</option>										
								</c:forEach>
							</select>
						</td>
						<td align="center"><input type="text" maxlength="6" size="16" /></td>
						<td align="center"><input type="button" id="buscar-rs" name="buscar-rs" value="Busqueda Rápida" /></td>						
						<td align="left"><input type="button" id="pdf-rs" name="pdf-rs"	value="PDF" onclick="lstPdf()" /></td>
						<td align="left"><input type="button" id="imprimir-rs" name="imprimir-rs" value="Imprimir" /></td>
						<td align="left"><input type="button" id="nuevo-rs" name="nuevo-rs"	value="Nuevo" onclick="lstNuevo()" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>        
		
	</html:form>

</body>
</html:html>