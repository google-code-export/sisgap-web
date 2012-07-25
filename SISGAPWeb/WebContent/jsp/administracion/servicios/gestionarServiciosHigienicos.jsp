<!DOCTYPE html>

<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	.texto {
		background-color:rgb(189,252,152);
		color:rgb(0,0,0);
	}
</style>

<script type="text/javascript">

$(function() {

		$("#nuevo-sh").button();
		$("#salir-sh").button();
		$("#pdf-sh").button();
		$("#imprimir-sh").button();
		$("#buscar-sh").button();
		
		$("#btngrabar").button();
		$("#btncerrar").button();
	
		$('#imprimir-sh').click(function() {
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
					var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
			        nueva=window.open('ReportsServlet?reporte=LISTADO_SERVICIOS_HIGIENICOS&fecDoc='+fecDoc, 'Popup', caracteristicas);  
			        //return false;
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
             
		
		$("#grabar-form").dialog({
					autoOpen : false,
					height : 380,
					width : 850,
					modal : true,		
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
			});
				
		$("#eliminar-form").dialog({
			autoOpen : false,
			height : 0,
			width : 300,
			modal : true,
			buttons : {
				Eliminar : function() {
					var frm = document.formServiciosHigienicos;
					frm.codigoServicio.value = cod;
					frm.metodo.value = 'eliminar';
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
		
	function lstNuevo() {
		//alert("Nuevo...");
		var frm = document.formServiciosHigienicos;
		frm.metodo.value = 'irGrabar';
		frm.submit();
	}

	function grabar() {
		var frm = document.formServiciosHigienicos;
		alert("Grabando...");		
		//frm.submit();
	}

	function cerrarPop() {
		$("#grabar-form").dialog("close");
	}
		
	function setTipoDoc(cod){
		var frm = document.formServiciosHigienicos;
		frm.tipodocumento.value = cod;
	}

	/* Por Johan Muñoz
	/* Funciones de validación de campos */
	function validar(){
		var texto = document.getElementById("ImporTotalRepMan");
		var cant = /^([0-9])*$/;
		if(!texto.match(cant)){
			alert("no es numero...");
		}else{
			alert("es numero...");
		}
	}
	
	function soloNumeros(evt){
		alert(evt);
		if(window.event){
			keynum=evt.keycode;
		}else{
			keynum=evt.which;
		}
		alert(keynum);
		if(keynum>47 && keynum<58){
			return true;
		}else{
			return false;
		}
	}

	function ver(cod) {
		var frm = document.formServiciosHigienicos;
		frm.codigoServicio.value = cod;
		frm.metodo.value = 'ver';
		frm.submit();
	}

	function editar(cod) {
		var frm = document.formServiciosHigienicos;
		frm.codigoServicio.value = cod;
		frm.metodo.value = 'editar';
		frm.submit();
	}

	function eliminar(cod) {
		rpta = confirmar("¿Esta seguro que desea eliminar este registro?");
		if (rpta){
			var frm = document.formServiciosHigienicos;
			frm.codigoServicio.value = cod;
			frm.metodo.value = 'eliminar';
			frm.submit();
		}
	}

	function confirmar( mensaje ){
		return confirm( mensaje );
	}

</script>
<title>Insert title here</title>
</head>
<body>

	<html:form action="/servicioshigienicos.do" styleId="formServiciosHigienicos">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoServicio" id="codigoServicio"/>		
		
		<input type='hidden' name='fecvencimiento' id='fecvencimiento'/>
		<input type='hidden' name='fecemision' id='fecemision'/>


		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Servicios Higienicos</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Cobros de Servicios Higienicos
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
						<td align="center"><input type="button" id="buscar-sh" name="buscar-sh" value="Busqueda Rápida" onclick="lstBuscar()" /></td>
						<td align="center"><input type="button" id="pdf-sh" name="pdf-sh" value="PDF" onclick="lstPdf()" /></td>
						<td align="center"><input type="button" id="imprimir-sh" name="imprimir-sh" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="center"><input type="button" id="nuevo-sh" name="nuevo-sh" value="Nuevo" onclick="lstNuevo()" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>
		
		<fieldset>
		<display:table name="lstSSHH"
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/servicioshigienicos.do?metodo=cargarAction"
						id="row"
						export="false">
				<display:column title="" style="width:60px;">
					<c:choose>
						<c:when test="${row.numEstado==1}">
							<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Anular..." border="0" width="16" height="16" id="" onclick="eliminar('${row.codServicio}');"/>
							<img src="<%=request.getContextPath()%>/imagenes/manto/editar.png" alt="Editar..." border="0" width="16" height="16" id="" onclick=""/>
						</c:when>
					</c:choose>
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" onclick="ver('${row.codServicio}');"/>
				</display:column>
				<display:column title="Cod.Servicio" property="codServicio" sortable="true" style="width:50px;"></display:column>
				<display:column title="Fecha Servicio" format="{0,date,dd-MM-yyyy}" property="datFechaserv" sortable="true" style="width:150px;"></display:column>
				<display:column title="Descripcion" property="strDescripcion" sortable="true" style="width:300px;"></display:column>
				<display:column title="Fecha de Creación" format="{0,date,dd-MM-yyyy}" property="datFechacrea" sortable="true" style="width:150px;"></display:column>
				<display:column title="Total" format="S/. {0,number,###.00}" property="numTotal" sortable="true" style="width:100px;"></display:column>
		</display:table>
		</fieldset>	

		<div id="selerepodia-form" title="Seleccione Tipo de Reporte diario a mostrar...">
			<div align="center">
				<table border="0">
					<tr>
						<td>
							<input type='text' name='fechadocumento' id='fechadocumento' class='text ui-widget-content ui-corner-all' size="20" value="${fechadocumento}" style=" width : 80px;"/>(mes/dia/año)
						</td>
					</tr>
				</table>
			</div>
		</div>
	</html:form>

</body>
</html:html>