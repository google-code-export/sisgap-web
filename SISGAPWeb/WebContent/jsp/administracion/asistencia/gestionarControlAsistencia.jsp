<!DOCTYPE html>

<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
.texto {
	background-color: rgb(189, 252, 152);
	color: rgb(0, 0, 0);
}
</style>

<script type="text/javascript">

$(function() {

		$("#nuevo-ca").button();
		$("#salir-ca").button();
		$("#pdf-ca").button();
		$("#imprimir-ca").button();
		$("#buscar-ca").button();
		
		$("#btngrabar").button();
		$("#btncerrar").button();
	
		$('#imprimir-ca').click(function() {      
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=LISTADO_RECIBO_LUZ', 'Popup', caracteristicas);  
	        return false;
		});

	
		$("#fecAsamblea").datepicker(
	            {   
	            	dateFormat: 'dd/mm/yy',
	                changeMonth: true,
	                changeYear: true,
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
					height : 400,
					width : 840,
					modal : true,		
					close : function() {
						$("#fecAsamblea").removeAttr("disabled");
						allFields.val("").removeClass("ui-state-error");
					}
			});

		
		$('#nuevo-ca').click(function() { 
			
			$("#grabar-form").dialog("open");
     		$("#lugarAsamblea").val("");
     		$("#agendaAsamblea").val("");
     		$("#acuerdosAsamblea").val("");
     		$("#observacionesAsamblea").val("");
     		$("#fecAsamblea").focus();     		
		});

		$("#eliminar-form").dialog({
			autoOpen : false,
			height : 0,
			width : 300,
			modal : true,
			buttons : {
				Eliminar : function() {
					var frm = document.formAsistencia;								
					frm.submit();
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});

		$(document).ready(
			function(){/* Aqui podria filtrar que controles necesitará manejar,
						* en el caso de incluir un dropbox $('input,select');
					   */
				   tb=$('input');
				   if($.browser.mozilla){
					   $(tb).keypress(enter2tab);
				   } else {
					   $(tb).keydown(enter2tab);
				   }
			});

			function enter2tab(e){
				if(e.keyCode==13){				
					cb=parseInt($(this).attr('tabindex'));
					if ($(':input[tabindex=\''+ (cb + 1) +'\']') != null){
						$(':input[tabindex=\''+ (cb + 1) +'\']').focus();
						$(':input[tabindex=\''+ (cb + 1) +'\']').select();
						e.preventDefault();
						return false;
					}
				}
			}
 
	});
	
	
	function mostrarItems(cod) {
		var frm = document.formAsistencia;
		frm.codigoModi.value = cod;//$("#codigoModi").val(cod);
		frm.metodo.value = 'mostrarItemsAsamblea';
		frm.submit();
	}
	
	function eliminar(cod) {
		var frm = document.formAsistencia;
		frm.codigoModi.value = cod;//$("#codigoModi").val(cod);
		frm.metodo.value = 'eliminarAsamblea';
		$("#eliminar-form").dialog("open");
		//$("#anulardoc-form").dialog("open");
	}


	function ver(cod) {
		//alert("CodigoModi:"+cod);
		var frm = document.formAsistencia;
		var codigo = cod;
		$.ajax({
	        type: "POST",
	        url: "/SISGAPWeb/AjaxServlet",
	        data: "action=EDITAR_REUNIONES&codigoModi="+cod,
	        success: function(datos){
		        var element = datos.split('</td>');
		        /*for(var i=0;i<element.length-1;i++){
		        	alert(element[i]);
		        }*/
		        $('#fecAsamblea').val(element[0].substring(4));
		        $('#lugarAsamblea').val(element[1].substring(4));
		        $('#agendaAsamblea').val(element[2].substring(4));
		        $('#acuerdosAsamblea').val(element[3].substring(4));
		        $('#observacionesAsamblea').val(element[4].substring(4));
	        }
		});

		$('#codigoModi').val(codigo);
		$("#fecAsamblea").attr("disabled","-1");
		$('#lugarAsamblea').focus();
		$("#grabar-form").dialog("open");
		
	}
	
	function grabar() {
		var frm = document.formAsistencia;

		$("#fecAsambleax").val($("#fecAsamblea").val());
		$("#lugarAsambleax").val($("#lugarAsamblea").val());
		$("#agendaAsambleax").val($("#agendaAsamblea").val());
		$("#acuerdosAsambleax").val($("#acuerdosAsamblea").val());
		$("#observacionesAsambleax").val($("#observacionesAsamblea").val());
		
		/*if($('input[name=estado]').is(':checked')){
			$("#estadox").val("1");
		}else{
			$("#estadox").val("0");
		}*/
		

		alert("El valor que viene es: " + $("#codigoModi").val());
		if($("#codigoModi").val()!=''){
			alert("uno");
			frm.metodo.value = 'actualizarAsamblea';
		}else{
			alert("dos");
			frm.metodo.value = 'registrarAsamblea';
		}
		
		frm.submit();
	}
	

	function cerrarPop() {
		$("#fecAsamblea").removeAttr("disabled");
		$("#grabar-form").dialog("close");
	}	
	
	
</script>
<title>Insert title here</title>
</head>
<body>

	<html:form action="/controlAsistencia.do" styleId="formAsistencia">
		<input type="hidden" name="metodo" />

		<input type="hidden" name="descripanulada" id="descripanulada" />
		<input type="hidden" name="tipodocumento" id="tipodocumento" value="R" />
		
		<input type='hidden' name='fecAsambleax' id='fecAsambleax' />
		<input type='hidden' name='lugarAsambleax' id='lugarAsambleax' />
		<input type='hidden' name='agendaAsambleax' id='agendaAsambleax' />
		<input type='hidden' name='acuerdosAsambleax' id='acuerdosAsambleax' />
		<input type='hidden' name='observacionesAsambleax' id='observacionesAsambleax' />
		
		<input type='hidden' name='codigoModi' id='codigoModi' />


		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Control de
					Asistencia</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Control de Asistencia
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
							<td align="center"><input type="text" maxlength="6"	size="16" /></td>
							<td align="center"><input type="button" id="buscar-ca" name="buscar-ca" value="Busqueda Rápida" /></td>
							<td align="center"><input type="button" id="pdf-ca" name="pdf-ca" value="PDF" /></td>
							<td align="center"><input type="button" id="imprimir-ca" name="imprimir-ca" value="Imprimir" /></td>
							<td align="center"><input type="button" id="nuevo-ca" name="nuevo-ca" value="Nuevo" /></td>
						</tr>
					</table>
				</span>
			</legend>
		</fieldset>

		<display:table name="lstRes" class="consultanormal"
			excludedParams="metodo"
			requestURI="/controlAsistencia.do?metodo=cargarAction" id="row"
			export="false">
			<display:column title="" style="width:60px;">
				<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Eliminar..." border="0" width="16" height="16" onclick="eliminar(${row.numCodReuniones})" />
				<img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16" onclick="ver(${row.numCodReuniones})" />
				<img src="<%=request.getContextPath()%>/imagenes/iconos/flecha.png"
					alt="Ver..." border="0" width="16" height="16"
					onclick="mostrarItems(${row.numCodReuniones});" />
			</display:column>
			<display:column title="Nro" property="numCodReuniones" style="text-align:center; width:20px" sortable="true"></display:column>
			<display:column title="Fecha Asamblea" style="width:100px" sortable="true">
				<fmt:formatDate pattern="dd MMMM yyyy" value="${row.datFechaSesion}" />
			</display:column>
			<display:column title="Lugar" property="strLugar" style="width:150px" sortable="true" />
			<display:column title="Agenda" property="strAgenda" style="width:300px" sortable="true" />
			<display:column title="Acuerdos" property="strAcuerdos" style="width:300px" sortable="true" />
			<display:column title="Observaciones" property="strObservaciones" sortable="true" />
			<display:column title="Estado" style="text-align:center" sortable="true">
				<c:choose>
					<c:when test="${row.numEstado==1}">Activo</c:when>
					<c:when test="${row.numEstado==0}">Inactivo</c:when>
					<c:when test="${row.numEstado==null}">Nada</c:when>
				</c:choose>
			</display:column>
		</display:table>

		<div id="grabar-form" title="Agregar Fecha de Asamblea">
			<table border="0" width="800px" cellpadding="2" cellspacing="4">
				<tr>
					<td><label><b>Fecha Asamblea:</b></label></td>
					<td><input type='text' name='fecAsamblea' id='fecAsamblea' class='text ui-widget-content ui-corner-all' size="20" tabindex="1" />&nbsp;(mes/dia/año)</td>
				</tr>
				<tr>
					<td><label><b>Lugar de la Asamblea:</b></label></td>
					<td><input type='text' name='lugarAsamblea' id='lugarAsamblea' style="text-transform: uppercase;" class='texto' size="100" tabindex="2" /></td>
				</tr>
				<tr>
					<td><label><b>Agenda de la Asamblea:</b></label></td>
					<td>
						<textarea rows="5" cols="100" name="agendaAsamblea" id="agendaAsamblea" style="text-transform: uppercase;" class="texto" tabindex="3"></textarea>
					</td>
				</tr>
				<tr>
					<td><label><b>Acuerdos de la Asamblea:</b></label></td>
					<td>
						<textarea rows="5" cols="100" name="acuerdosAsamblea" id="acuerdosAsamblea" style="text-transform: uppercase;" class="texto" tabindex="4"></textarea>
					</td>
				</tr>
				<tr>
					<td><label><b>Observaciones de la Asamblea:</b></label></td>
					<td>
						<textarea rows="5" cols="100" name="observacionesAsamblea" id="observacionesAsamblea" style="text-transform: uppercase;" class="texto" tabindex="5"></textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
						<input type="button" name="btngrabar" id="btngrabar" onclick="grabar();" value="Grabar">
					</td>
					<td align="center">
						<input type="button" name="btncerrar" id="btncerrar" onclick="cerrarPop();" value="Cancelar">
					</td>					
				</tr>
			</table>
		</div>
		<div id="eliminar-form" title="Esta seguro que desea eliminar...?"></div>
		<div id="tablesocios"></div>
	</html:form>

</body>
</html:html>