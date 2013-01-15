<!DOCTYPE html>

<%@page import="org.apache.taglibs.standard.tag.el.fmt.ParseNumberTag"%>
<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
/*demo page css*/
body {
	font: 62.5% "Trebuchet MS", sans-serif;
}

label,input {
	display: block;
}

input.text {
	margin-bottom: 5px;
	width: 95%;
	padding: .4em;
}

.demoHeaders {
	margin-top: 2em;
}

#dialog_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}

ul#icons {
	margin: 0;
	padding: 0;
}

ul#icons li {
	margin: 2px;
	position: relative;
	padding: 4px 0;
	cursor: pointer;
	float: left;
	list-style: none;
}

ul#icons span.ui-icon {
	float: left;
	margin: 0 4px;
}


h1 {
	font-size: 1.2em;
	margin: .6em 0;
}

div#users-contain {
	width: 70%;
	margin: 20px 0;
}

div#users-contain table {
	margin: 1em 0;
	border-collapse: collapse;
	width: 100%;
}

div#users-contain table td,div#users-contain table th {
	border: 1px solid #eee;
	padding: .6em 10px;
	text-align: left;
}

.ui-dialog .ui-state-error {
	padding: .3em;
}

.validateTips {
	border: 1px solid transparent;
	padding: 0.3em;
}



.texto {
	background-color:rgb(189,252,152);
	color:rgb(0,0,0);
}


</style>
<script type="text/javascript">
$(function() {
	$("#btn-agregar-item").button();
	$("#btn-registra-sh").button();
	$("#btnAgregar").button();
	
	
	$('#btn-agregar-item').click(function() {
		if($('#tipoServicio').val()==0){
			alert("Debe seleccionar un tipo de servicio");
			$('#tipoServicio').focus();
			return false;
		}
		if($('#fechadocumento').val()==""){
			alert("Debe seleccionar una fecha");
			$('#fechadocumento').focus();
			return false;
		}
		
		$("#tipoItem option[value='']").attr("selected","selected");
		//Inicializa campos
		$("#costo").val('');
		$("#del").val('');
		$("#al").val('');
		$("#unidades").val('');
		$("#total").val('');
		//Inicializa atributos
		$('#rango').attr({'style':'display:none'});
		$('#unidad').attr({'style':'display:none'});
		$('#final').attr({'style':'display:none'});
		//Abre ventana dialog
		$("#dialog-form-item").dialog("open");
	}); 
	
	$("#dialog-form-item").dialog({
		autoOpen : false,
		height : 220,
		width : 560,
		modal : true,
		buttons : {
			Agregar : function() {
				/*if($('#tipoServicio').val()==0){
					alert("debe seleccionar el Tipo de Servicio");
					$('#tipoServicio').focus();
					return false;
				}*/
				if($('#tipoItem').val()==0){
					alert("debe seleccionar el Tipo de Item");
					$('#tipoItem').focus();
					return false;
				}

				var e=document.getElementById("rango");
				if(e.style.display=="block"){
					if($('#del').val().trim()==""){
						alert("debe ingresar un valor numérico");
						$('#del').focus();
						return false;
					}
					if($('#al').val().trim()==""){
						alert("debe ingresar un valor numérico");
						$('#al').focus();
						return false;
					}
					var v1 = parseInt($('#del').val());
					var v2 = parseInt($('#al').val());
					//alert(v1+" - "+v2);
					if(v1 > v2){
						alert("el valor del campo [AL] debe ser mayor que el valor del campo [DEL]");
						$('#al').focus();
						return false;
					}
				}

				var e=document.getElementById("unidad");
				if(e.style.display=="block"){
					if($('#unidades').val().trim()==""){
						alert("debe ingresar un valor numérico");
						$('#unidades').focus();
						return false;
					}
				}
				
				agregarFila(document.getElementById('cant_campos'));
 
				$("#hdnCabecera").val($('#tipoServicio :selected').val());
				$("#hdnItem").val($('#tipoServicio :selected').text());
				
				$(this).dialog("close");
					
				/*var codigo =  $('#tipoItem').val().trim().substring(0,1);
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_ITEM_SERVICIO&codigo="+codigo,
			        success: function(datos){					        
			        	$("#tableProducto").html(datos);
			      	}
				});*/

			},
			Cancelar : function() {
				$(this).dialog("close");
			}},
		close : function() {
			allFields.val("").removeClass("ui-state-error");
		}
	});

	$("#btn-registra-sh").button().click(function() {
		$('[name=metodo]').val('grabar');
		$('#sisas').submit();
		
	});
	
	$(document).ready(
			function(){/* Aqui podria filtrar que controles necesitará manejar,
						* en el caso de incluir un dropbox $('input,select');
					   */
				   tb=$('input,select');
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


			$("#fechadocumento").datepicker({  
				dateFormat: 'yy/mm/dd', 
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

function agregarFila(obj){
    $("#cant_campos").val(parseInt($("#cant_campos").val()) + 1);
	var oId = $("#cant_campos").val();
    var codigo = $("#tipoItem").val().substring(0,1);
	var descri = $('#tipoItem :selected').text();
	var costos = $("#costo").val();
	var del = $("#del").val();
	var al = $("#al").val();
	
	var e=document.getElementById("rango");
	var cantid = 0;
	if(e.style.display=="block"){
		cantid = ($("#al").val()-$("#del").val())+1;	
	}
	var e=document.getElementById("unidad");
	if(e.style.display=="block"){
		cantid = $("#unidades").val();
	}
	var totale = $("#total").val();

	//var strHtml1 = "<td>" + oId + "</td>";
    var strHtml1 = "<td>" + codigo + '<input type="hidden" id="hdnCodigo_' + oId + '" name="hdnCodigo_' + oId + '" value="' + codigo + '"/></td>';
	var strHtml2 = "<td>" + descri + '<input type="hidden" id="hdnDescri_' + oId + '" name="hdnDescri_' + oId + '" value="' + descri + '"/></td>' ;
	var strHtml3 = "<td>" + del + '<input type="hidden" id="hdnDel_' + oId + '" name="hdnDel_' + oId + '" value="' + del + '"/></td>' ;
	var strHtml4 = "<td>" + al + '<input type="hidden" id="hdnAl_' + oId + '" name="hdnAl_' + oId + '" value="' + al + '"/></td>' ;
	var strHtml5 = "<td>" + costos + '<input type="hidden" id="hdnCostos_' + oId + '" name="hdnCostos_' + oId + '" value="' + costos + '"/></td>' ;
	var strHtml6 = "<td>" + cantid + '<input type="hidden" id="hdnCantid_' + oId + '" name="hdnCantid_' + oId + '" value="' + cantid + '"/></td>' ;
	var strHtml7 = "<td id='uno'>" + totale + '<input type="hidden" id="hdnTotale_' + oId + '" name="hdnTotale_' + oId + '" value="' + totale + '"/></td>' ;
	var strHtml8 = '<td><img src="imagenes/delete.png" width="16" height="16" alt="Eliminar" onclick="if(confirm(\'Realmente desea eliminar este detalle?\')){eliminarFila(' + oId + ');}"/>';
    strHtml8 += '<input type="hidden" id="hdnIdCampos_' + oId +'" name="hdnIdCampos[]" value="' + oId + '" /></td>';
    //var strHtmlTr = "<tr id='rowDetalle_" + oId + "'></tr>";
    //var strHtmlFinal = strHtml1 + strHtml2 + strHtml3 + strHtml4 + strHtml5 + strHtml6;
    //tambien se puede agregar todo el HTML de una sola vez.
    var strHtmlTr = "<tr id='rowDetalle_" + oId + "'>" + strHtml1 + strHtml2 + strHtml3 + strHtml4 + strHtml5 + strHtml6 + strHtml7 + strHtml8 + "</tr>";
    $("#tbDetalle").append(strHtmlTr);
    //si se agrega el HTML de una sola vez se debe comentar la linea siguiente.
    //$("#rowDetalle_" + oId).html(strHtmlFinal);
    var totales = 0.0;
    var subtotal;
    var a;
    for(a=1; a<=oId; a++){
        datos = eval("document.forms[0].hdnTotale_"+a);
        subtotal = parseFloat(datos.value);
        totales = parseFloat(totales) + parseFloat(subtotal);
    }
	//alert("Totales:"+totales);
	$("#totalGral").val(totales);
	
    return false;
}

function eliminarFila(oId){
    $("#rowDetalle_" + oId).remove();	
	return false;
}

function cancelar(){
    $("#tbDetalle").html("");	
	return false;
}



function pagar(){
	$('[name=metodo]').val('cancelarFactura');	
	$('#sisas').submit();
}

	function costoSeleccionado(valor){
		//alert(valor+" - "+valor.substring(1));
		var valor1 = valor.substring(1);
		$('[name=costo]').val(valor1);

		var valor2 = parseInt($('#tipoItem :selected').val().substr(0,1));
		switch (valor2)	{
			case 1:
			case 2:
			case 3:
			case 4:
				$('#rango').attr({'style':'display:block'});
				$('#unidad').attr({'style':'display:none'});
				$('#final').attr({'style':'display:block'});
				break;
			case 5:
			case 6:
			case 7:
				$('#rango').attr({'style':'display:none'});
				$('#unidad').attr({'style':'display:block'});
				$('#final').attr({'style':'display:block'});
				break;
			default:
				break;
			
		}
		//alert($('#tipoItem :selected').val().substr(0,1));
		
	}
	
	function calcularTotal(){
		var valor1 = 0;
		var valor2 = 0;
		var valor3 = 0;
		var valor4 = 0;
		valor1 = $('[name=del]').val();
		valor2 = $('[name=al]').val();
		valor3 = $('[name=costo]').val();
		valor4 = $('[name=unidades]').val();
		if(valor2 > valor1){
			$('[name=total]').val(redondear(((valor2-valor1)+1)*valor3,2));
		}
		if(valor4 != '')
			$('[name=total]').val(redondear((valor4*valor3),2));
	}

	function redondear(cantidad, decimales) {
		var cantidad = parseFloat(cantidad);
		var decimales = parseFloat(decimales);
		decimales = (!decimales? 2 : decimales);
		
		return Math.round(cantidad*Math.pow(10, decimales))/Math.pow(10,decimales);		
	}
</script>
</head>
<body>
<html:form action="/registrosisas.do" styleId="registrosisas">
		<input type="hidden" name="metodo" />
		<input type="hidden" id="num_campos" name="num_campos" value="0" />
    	<input type="hidden" id="cant_campos" name="cant_campos" value="0" />
    	<input type="hidden" id="hdnCabecera" name="hdnCabecera" value="" />
    	<input type="hidden" id="hdnItem" name="hdnItem" value="" />
    	<input type="hidden" name="fecdocumento" value="${fechadocumento}" />
    		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Sisas</td>
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

<!-- div class="button_div">    
    <input type="reset" id="btnCancel" name="btnCancel" value="Cancelar" class="buttons_CANCEL" onclick="cancelar();" />
    <input type="button" id="btnAgregar" name="btnAgregar" value="Agregar Persona" class="buttons_aplicar" onclick="agregarFila(document.getElementById('cant_campos'));" />
    <input type="button" id="btnAgregar" name="btnAgregar" value="Guardar" class="buttons_OK" onclick="xajax_guardar(xajax.getFormValues('proyecto'));" />
</div -->

<fieldset class="fieldset">
    <legend class="legend"><b>Nuevo registro - Detalle de Sisas</b></legend>
    <div class="clear"></div>

	<div id="cabecera" title="Fecha del Servicio" class="ui-widget ui-widget-content" width="100%">
		<table border="0" cellpadding="2" cellspacing="2" width="800px">
			<tr>
				<td width="120px" align="left" style="color:red;"><b><label>SISAS:</label></b></td>
				<td align="left" width="200px" style="color:red;">
					<input type="text" id="tipoServicio" name="tipoServicio" class='text ui-widget-content ui-corner-all' value="${strDescripcion}" style=" width : 80px; color: red;"/>
				</td>
				<td width="120px" style="color:red;"><b><label>FECHA DE LA SISA:</label></b></td>
				<td width="350px" style="color:red;">
					<input type='text' name='fechadocumento' id='fechadocumento' class='text ui-widget-content ui-corner-all' size="20" value="${datFechaServ}" style=" width : 80px; color: red;" />(año/mes/dia)
				</td>
			</tr>
		</table>
	</div>
    
    <div id="users-contain" class="ui-widget" width="100%">
		<c:choose>
			<c:when test="${isDetalle!=1 }">
				<button id="btn-agregar-item">Agregar Item</button>
			</c:when>
		</c:choose>
	<table width="100%" id="tblDetalle" name="tblDetalle" class="ui-widget ui-widget-content">
		<thead>
			<tr class="ui-widget-header">
				<th>C&oacutedigo</th>
				<th>Descripci&oacuten</th>
				<th>Del</th>
				<th>Al</th>
				<th>Costo</th>
				<th>Cantidad</th>
				<th>Total</th>
				<th>Acci&oacuten</th>
			</tr>
		</thead>
		<tbody id="tbDetalle">
		</tbody>
		<tr>
			<c:forEach items="${lstSrvDet}" var="row">
				<tr>
					<td>${row.codServiciodetalle }</td>
					<td>${row.sisgapServicioItem.strDescripcion }</td>
					<td>${row.numDel }</td>
					<td>${row.numAl }</td>
					<td>${row.numCosto }</td>
					<td>${row.numCantidad }</td>
					<c:set var="subtotal" value="${row.numCosto * row.numCantidad }" />
					<td><c:out value="${subtotal}" /></td>
					<c:set var="total" value="${total + subtotal }" />
				</tr>
			</c:forEach>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td colspan="3" align="right"><font size="2px" color="blue"><b>Total General S/. <c:out value="${total }" /></b></font></td>
		</tr>		
	</table>
		<c:choose>
			<c:when test="${isDetalle!=1}">
				<button id="btn-registra-sh">Registrar Sisas</button>
			</c:when>
		</c:choose>

    </div>
</fieldset>
				
		<div id="dialog-form-item" title="Agregar Item a Detalle de Factura" style="display: none">
		<fieldset>					
				<!--legend>Registro</legend-->
				<br>
				<div id="grabarnuevo-form" title="Ingrese los datos de las Sisas...">
					<div align="center">
					<table border="0">
						<tr>
							<td colspan="2">
								<table border="0" cellpadding="0" cellspacing="2" width="500px">
									<!-- tr>
										<td width="120px" align="left"><label>SERVICIO:</label></td>
										<td colspan="5" align="left">
											<select name="tipoServicio" id="tipoServicio" tabindex="1">
												<option value="0" selected="selected">Seleccione</option>
												<option value="1">BAÑO NRO. 1</option>
												<option value="2">BAÑO NRO. 2</option>
												<option value="3">BAÑO NRO. 3</option>
												<option value="4">BAÑO NRO. 4</option>
												<option value="5">BAÑO NRO. 5</option>
												<option value="6">BAÑO NRO. 6</option>
											</select>
										</td>
									</tr -->
									<tr>
										<td align="left"><label>ITEM DE COBRANZA:</label></td>
										<td> 
											<select name="tipoItem" id="tipoItem" onchange="costoSeleccionado(this.value)" tabindex="2">
												<option value="" selected="selected">Seleccione</option>
												<c:forEach items="${lstSI}" var="row">
													<option value="${row.codServicioitem }${row.numCosto}">${row.strDescripcion }</option>
												</c:forEach>
											</select>
										</td>
										<td width="80px"><label>COSTO:</label></td>
										<td colspan="3">
											<input type="text" name="costo" id="costo" value="${row.numCosto}" size="10" disabled="disabled"/>
										</td>
									</tr>
									<tr id="rango" style="display: none;">
										<td align="right"><label>DEL:</label></td>
										<td>
											<input type='text' name='del' id='del' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 100px;" tabindex="3"/>									
										</td>
										<td align="right"><label>AL:</label></td>
										<td>
											<input type='text' name='al' id='al' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 100px;" tabindex="4"/>									
										</td>
									</tr>
									<tr id="unidad" style="display: none;">
										<td align="right"><label>Unidades:</label></td>
										<td>
											<input type='text' name='unidades' id='unidades' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 100px;" tabindex="5"/>									
										</td>
									</tr>
									<tr id="final" style="display: none;">
										<td colspan="3" align="right"><label>TOTAL:</label></td>
										<td>
											<input type='text' name='total' id='total' class='texto' size="10" style=" width : 100px;"  tabindex="6"/>									
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					</div>
				</div>
			</fieldset>
		</div>
		
		</html:form>
</body>
</html:html>