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

		$("#buscar-ic").button();
		$("#pdf-ic").button();
		$("#imprimir-ic").button();
		//$("#salir-r").button();
		$("#nuevo-ic").button();

		
		//Botón Imprimir
		$('#imprimir-ic').click(function() { 
			if ($('#cbItemCob option:selected').text() == "Seleccione") {
				//alert($('#cbItemCob option:selected').text());
				alert("Seleccione un Item de Cobranza a Buscar");
				//$("#info").fadeOut(800).fadeIn(800).fadeOut(400).fadeIn(400).fadeOut(400).fadeIn(400);
			} else {
				var itmCob = $('#cbItemCob option:selected').val();
				var tipDoc = $("#tipDocx").val();
				var estDoc = $("#estDocx").val();
				var estCan = $("#estCanc").val();
				var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";    
		        nueva=window.open('ReportsServlet?reporte=REPORTE_DOCUMENTOS_FILTRO_ITEM&tipDoc='+tipDoc+'&estDoc='+estDoc+'&itmCob='+itmCob+'&estCan='+estCan, 'Popup', caracteristicas);
			} 			
		});		
	});

	$(".close").click(function(){
        $("#info").animate({left:"+=10px"}).animate({left:"-5000px"});
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
				<table border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td>
						Item :
							<select id="cbItemCob" name="cbItemCob" style="width: 300px">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstCob}" var="row">
									<option value="${row.codItemcobranza}">${row.strDescripcion}</option>										
								</c:forEach>
							</select>
						</td>
						<td align="center"><input type="text" maxlength="6" size="16" /></td>
						<td align="center"><input type="button" id="buscar-ic" name="buscar-ic" value="Busqueda Rápida" onclick="lstBuscar()" /></td>						
						<td align="left"><input type="button" id="pdf-ic" name="pdf-ic"	value="PDF" onclick="lstPdf()" /></td>
						<td align="left"><input type="button" id="imprimir-ic" name="imprimir-ic" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="left"><input type="button" id="nuevo-ic" name="nuevo-ic"	value="Nuevo" onclick="lstNuevo()" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>        

		<fieldset>
			<legend>
				<span class="titulo">Seleccione Filtro para el Reporte 
					<table border="0" cellpadding="4" cellspacing="4">
						<tr>
							<td>Tipos de Documentos :
								<select name="tipDocx" id="tipDocx" style="width: 150px">
									<option value="T" selected>-- Todos --</option>
									<option value="R">Recibos</option>
									<option value="B">Boletas</option>
								</select>
							</td>
							<td>&nbsp;</td>
							<td>Estado de Documentos :
								<select name="estDocx" id="estDocx" style="width: 150px">
									<option value="T" selected>-- Todos --</option>
									<option value="P">Pendiente</option>
									<option value="C">Cancelada</option>
									<option value="A">Anulada</option>
								</select>
							</td>
							<td>&nbsp;</td>
							<td>¿Cancelaron?
								<select name="estCanc" id="estCanc" style="width: 150px">
									<option value="S" selected>Si</option>
									<option value="N">No</option>
								</select>
							</td>
						</tr>
					</table>
				</span>
			</legend>
		</fieldset>
		
		<div id="info" style="display: none">
    		¿Seleccione un Item de Cobranza...?<br> <a href="#" class="close">Cerrar</a>
		</div> 

		

	</html:form>

</body>
</html:html>