<!DOCTYPE html>

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

</style>
<script type="text/javascript">

var codTipCob = "";
var codMon = "";

	$(function() {
		$("#nuevo-sls").button();
		$("#btngrabar").button();
		$("#btncerrar").button();
		$("#btnpagar").button();

		$("#grabar-form").dialog({
			autoOpen : false,
			height : 380,
			width : 600,
			modal : true,		
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
	     });

		$('#nuevo-sls').click(function() {
			if ($("#codigo-sls").val()=="" || $("#socio-sls").val()=="" || $("#direccion-sls").val()=="" ){
				alert("Debe buscar y seleccionar el Socio antes de ingresar los datos del recibo de luz");
				return false;
			}else{
				$("#correlativo").val("");
				if ($("#lecfin-sls").val() > 0){
					$("#txtLecturaInih").val($("#lecfin-sls").val());
				} else {
					$("#txtLecturaInih").val("");
				}
				$("#txtLecturaFinh").val("");
				$("#txtConsumomesh").val("");
				$("#txtCagofijoh").val("");
				$("#txtAlupublich").val("");
				$("#txtCargoenerh").val("");
				$("#txtSubTotalMesh").val("");
				$("#txtIgvh").val("");
				$("#txtTotalMesh").val("");
				$("#txtUsoEquipoh").val("");
				$("#txtServmantoh").val("");
				$("#txtAporteleyh").val("");
				$("#txtRecargoh").val("");
				$("#txtRedondeoh").val("");
				$("#txtTotalh").val("");
				$("#txtDeudaAnth").val("");
				
				$("#grabar-form").dialog("open");
				$("#btnpagar").hide();
				
	     		return false;
			}

		});
		
		$('#dialog').dialog({
			autoOpen : false,
			width : 600,
			buttons : {
				"Ok" : function() {
					$(this).dialog("close");
				},
				"Cancel" : function() {
					$(this).dialog("close");
				}
			}
		});

		$('#dialog_link').click(function() {
			$('#dialog').dialog('open');
			return false;
		});

		$('#dialog_link, ul#icons li').hover(function() {
			$(this).addClass('ui-state-hover');
		}, function() {
			$(this).removeClass('ui-state-hover');
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


	$(function() {

		
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$("#dialog:ui-dialog").dialog("destroy");

		var name = $("#name"), email = $("#email"), password = $("#password"), allFields = $(
				[]).add(name).add(email).add(password), tips = $(".validateTips");

		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}

		function checkLength(o, n, min, max) {
			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Length of " + n + " must be between " + min
						+ " and " + max + ".");
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}


		$("#buscarsocio-form")
				.dialog({
							autoOpen : false,
							height : 300,
							width : 550,
							modal : true,
							buttons : {
								Cancel : function() {
									$(this).dialog("close");
								}},
							close : function() {
								allFields.val("").removeClass("ui-state-error");
							}
					});


		$("#buscar-socio").button().click(function() {
			$("#buscarsocio-form").dialog("open");
			return false;
		});

		$("#btn-buscar-socio").button().click(function() {
			var nombre =  $('[name=nombresocio]').val();
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_SOCIO&nombre="+nombre,
			        success: function(datos){
			        	$("#tablesocios").html(datos);
			      }
			});
		});	

		$("#eliminar-form").dialog({
			autoOpen : false,
			height : 0,
			width : 300,
			modal : true,
			buttons : {
				Eliminar : function() {
					var frm = document.gestionarFacturacion;								
					frm.submit();
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});
		

	});

	function grabar() {
		var frm = document.gestionarFacturacion;
		
		$("#txtLecturaIni").val($("#txtLecturaInih").val());
		$("#txtLecturaFin").val($("#txtLecturaFinh").val());
		$("#txtConsumomes").val($("#txtConsumomesh").val());
		$("#txtCagofijo").val($("#txtCagofijoh").val());
		$("#txtAlupublic").val($("#txtAlupublich").val());
		$("#txtCargoener").val($("#txtCargoenerh").val());
		$("#txtSubTotalMes").val($("#txtSubTotalMesh").val());
		$("#txtIgv").val($("#txtIgvh").val());
		$("#txtTotalMes").val($("#txtTotalMesh").val());
		$("#txtUsoEquipo").val($("#txtUsoEquipoh").val());
		$("#txtServmanto").val($("#txtServmantoh").val());
		$("#txtAporteley").val($("#txtAporteleyh").val());
		$("#txtRecargo").val($("#txtRecargoh").val());
		$("#txtRedondeo").val($("#txtRedondeoh").val());
		$("#txtTotal").val($("#txtTotalh").val());
		$("#txtDeudaAnt").val($("#txtDeudaAnth").val());

			
		if($("#correlativo").val()==''){
			alert("Grabará la información...");
			$("#metodo").val("grabarItemReciboLuzSocio");
		}else{
			alert("Actualizará la información...");
			$("#metodo").val("actualizarItemReciboLuzSocio");
		}

		
		frm.submit();
	}


	function eliminarRes(corr,soc,res){
		
		var frm = document.gestionarFacturacion;
		
		$("#codigoModi").val(res);
		$("#codigoide").val(soc);
		$("#correlativo").val(corr);

		$("#metodo").val("eliminarItemReciboLuz");

		$("#eliminar-form").dialog("open");
	}
	
	function agregarSocio(codigo, razonSocial , puesto, estado, deudaant, codigoIde, lecFin) {
		$("#codigo-sls").val(codigo);
		$("#socio-sls").val(razonSocial);
		$("#direccion-sls").val(puesto);
		if (estado == 1){
			$("#estado-sls").val("PENDIENTE");
		}else if (estado == 2){
			$("#estado-sls").val("PAGADO");
		}else{
			$("#estado-sls").val("-----");
		}
		$("#deudaant-sls").val(deudaant);
		$("#codigoide").val(codigoIde);
		$("#lecfin-sls").val(lecFin);
		$("#buscarsocio-form").dialog("close");
	}

	function editarRes(codigorecibo,
			codigosocio,
			correlativo,
			lecturaini,
			lecturafin,
			consumomes,
			cagofijo,
			alupublic,
			cargoener,
			totalmes,
			igv,
			subtotalmes,
			usoequipo,
			servmanto,
			aporteley,
			recargo,
			redondeo,
			total,
			deudaant
	){
		$("#codigoide").val(codigosocio);
		$("#correlativo").val(correlativo);
		$("#codigoModi").val(codigorecibo);
		
		$("#txtLecturaInih").val(lecturaini);
		$("#txtLecturaFinh").val(lecturafin);
		$("#txtConsumomesh").val(consumomes);
		$("#txtCagofijoh").val(cagofijo);
		$("#txtAlupublich").val(alupublic);
		$("#txtCargoenerh").val(cargoener);
		$("#txtSubTotalMesh").val(subtotalmes);
		$("#txtIgvh").val(igv);
		$("#txtTotalMesh").val(totalmes);
		$("#txtUsoEquipoh").val(usoequipo);
		$("#txtServmantoh").val(servmanto);
		$("#txtAporteleyh").val(aporteley);
		$("#txtRecargoh").val(recargo);
		$("#txtRedondeoh").val(redondeo);
		$("#txtTotalh").val(total);
		$("#txtDeudaAnth").val(deudaant);
		
		$("#grabar-form").dialog("open");
		$("#btnpagar").show();
	}
	
	function calcularTotal(){

		var total = 0;
		var costo = $("#costo-p").val();
		var cant =  $("#cantidad-p").val();
		total = costo*cant;

		$("#total-p").val( total );
	}
	
	function cerrarPop() {
		$("#grabar-form").dialog("close");
	}

	// Funciones generadas - Johan Muñoz
	// llamará a DIV para seleccionar el reporte... 
	function mostrarRep(valor1, valor2) {
		var codRec = 0;
		codRec = valor1;
		var codSoc = 0;
		codSoc = valor2;
		
		$("#reciboLuz-form")
		.dialog({
					autoOpen : true,
					height : 200,
					width : 550,
					modal : true,
					buttons : {
						Aceptar : function() {
							//var frm = document.gestionarFacturacion;								
							//frm.submit();							
							if ($('input:radio[name=recluz]:checked').val()=="1"){
								//alert("Reporte Luz 1");
								var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
						        nueva=window.open('ReportsServlet?reporte=RECIBO_LUZ&codRec='+ codRec +'&codSoc='+ codSoc , 'Popup', caracteristicas);  
						        return false;
							}else if ($('input:radio[name=recluz]:checked').val()=="2"){
								//alert("Reporte Luz 2");
								var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
						        nueva=window.open('ReportsServlet?reporte=RECIBO_LUZ_DOBLE&codRec='+ codRec +'&codSoc='+ codSoc , 'Popup', caracteristicas);  
						        return false;
							}else if ($('input:radio[name=recluz]:checked').val()=="3"){
								//alert("Reporte Luz 3");
								var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
						        nueva=window.open('ReportsServlet?reporte=RECIBO_LUZ_DOBLE_A4&codRec='+ codRec +'&codSoc='+ codSoc , 'Popup', caracteristicas);  
						        return false;
							}
							$(this).dialog("close");
						},
						Cancelar : function() {
							$(this).dialog("close");
						}},
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
			});
        
		//var frm = document.formFacturacion;
		//frm.codigoModi.value = cod;//$("#codigoModi").val(cod);
		//frm.metodo.value = 'mostrarItemsSuministro';
		//frm.submit();
	}

	function calculaConsumoMes(dato1, dato2) {
		var total = 0; 
		var carEne = 0;
		var deuda_sls = 0;
		total = $("#txtLecturaFinh").val() - $("#txtLecturaInih").val();
		deuda_sls = $("#deudaant-sls").val();


		//alert("LecturaIni: "+$("#txtLecturaInih").val()+"\nLecturaFin: "+$("#txtLecturaFinh").val());
		if ($("#txtLecturaInih").val()=="" || $("#txtLecturaFinh").val()=="") {
			$("#txtConsumomesh").val(0);
			$("#txtCargoenerh").val(0);	
		}else{
			$("#txtConsumomesh").val(total);
			carEne = total * 0.5;		
			$("#txtCargoenerh").val(carEne);			
		}

		//Valores temporales por defecto
		$("#txtCagofijoh").val("2.50");
		$("#txtAlupublich").val("1.00");
		$("#txtUsoEquipoh").val("1.50");
		$("#txtServmantoh").val("2.30");
		$("#txtAporteleyh").val("0.20");
		$("#txtRecargoh").val("0.10");
		$("#txtRedondeoh").val("0");
		$("#txtDeudaAnth").val(deuda_sls);
		
		calculaTotalMes();
		calculaTotal();				
	}

	function calculaTotalMes() {
		var subTotalMes = 0; var igv = 0; var totalMes = 0;		
		subTotalMes = redondear( parseFloat($("#txtCagofijoh").val()) ) + redondear( parseFloat($("#txtAlupublich").val()) ) + redondear( parseFloat($("#txtCargoenerh").val()) );
		igv = redondear( parseFloat(((subTotalMes * 18.00)/100.00)) );
		totalMes = redondear( parseFloat(igv + subTotalMes) );
		
		$("#txtSubTotalMesh").val(subTotalMes);
		$("#txtIgvh").val(igv);
		$("#txtTotalMesh").val(totalMes);
		calculaTotal();
	}

	function calculaTotal() {
		var totalMes = 0;
		totalMes = parseFloat($("#txtTotalMesh").val()) + parseFloat($("#txtUsoEquipoh").val()) + parseFloat($("#txtServmantoh").val()) + parseFloat($("#txtAporteleyh").val()) + parseFloat($("#txtRecargoh").val());
		totalMes = redondear(parseFloat(totalMes) + parseFloat($("#txtRedondeoh").val())); 

		if ($("#txtDeudaAnth").val()=="" || ($("#txtDeudaAnth").val())==0){
			$("#txtTotalh").val(totalMes);
		}else{
			totalMes = redondear(totalMes + + parseFloat($("#txtDeudaAnth").val())); 
			$("#txtTotalh").val(totalMes);
		}
	}

	function redondear(cantidad, decimales) {
		var cantidad = parseFloat(cantidad);
		var decimales = parseFloat(decimales);
		decimales = (!decimales? 2 : decimales);
		
		return Math.round(cantidad*Math.pow(10, decimales))/Math.pow(10,decimales);		
	}

	function pagar(){
		var frm = document.gestionarFacturacion;
		$("#metodo").val("pagarItemReciboLuzSocio");
		frm.submit();
	}

	/*function Enter(Evento,Campo){
		var keyCode = Evento.keyCode? Evento.keyCode : Evento.which? Evento.which: Evento.charCode;
		if (keyCode == 13){
			var i;
			for(i=0; i<Campo.form.elements.length; i++)
				break;
			i=Campo.form.elements[i].tabIndex + 1;
			for(j=0; j<Campo.form.elements.length; j++){
				if(Campo.form.elements[j].tabindex == i)
					break;
			}
			Campo.form.elements[j].focus();
			return false;
		} else
			return true;
	}*/
</script>
</head>
<body>
<html:form action="/suministroLuz.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" id="metodo"/>
		<input type="hidden" name="codigoModi" id="codigoModi" value="${resori.codOrgreciboLuz}"/>
		<input type="hidden" name="codigoide" id="codigoide"/>
		<input type="hidden" name="correlativo" id="correlativo"/>
		
		<input type="hidden" name="txtLecturaIni" id="txtLecturaIni" />
		<input type="hidden" name="txtLecturaFin" id="txtLecturaFin" />
		<input type="hidden" name="txtConsumomes" id="txtConsumomes" />
		<input type="hidden" name="txtCagofijo" id="txtCagofijo" />
		<input type="hidden" name="txtAlupublic" id="txtAlupublic" />
		<input type="hidden" name="txtCargoener" id="txtCargoener" />
		<input type="hidden" name="txtSubTotalMes" id="txtSubTotalMes" />
		<input type="hidden" name="txtIgv" id="txtIgv" />
		<input type="hidden" name="txtTotalMes" id="txtTotalMes" />
		<input type="hidden" name="txtUsoEquipo" id="txtUsoEquipo" />
		<input type="hidden" name="txtServmanto" id="txtServmanto" />
		<input type="hidden" name="txtAporteley" id="txtAporteley" />
		<input type="hidden" name="txtRecargo" id="txtRecargo" />
		<input type="hidden" name="txtRedondeo" id="txtRedondeo" />
		<input type="hidden" name="txtTotal" id="txtTotal" />
		<input type="hidden" name="txtDeudaAnt" id="txtDeudaAnt" />

		
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración / Asociar Recibo</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td id="mensaje" align="center" valign="middle"	style="display: none"></td>
				<td id="error" align="center" valign="middle" class="mensajeError" style="display: none"></td>
			</tr>
		</table>

		<fieldset>
			<legend>Datos del Recibo Original</legend>
			<table border="0" cellpadding="2" cellspacing="2" width="75%">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="120px" align="right"><b>Cod. Recibo</b></td>
					<td colspan="7">${resori.codOrgreciboLuz}</td>
				</tr>
				<!-- tr>
					<td><b>Lectura Inicial</b></td>
					<td>${resori.numLecturaInicial}</td>
					<td width="120px"><b>Lectura Final</b></td>
					<td>${resori.numLecturaFinal}</td>
				</tr -->
				<tr>
					<td align="right"><b>Reposic. y Manto. Conexion</b></td>
					<td>${resori.repomancnx}</td>
					<td align="right"><b>Cargo Fijo</b></td>
					<td>${resori.cargofijo}</td>
					<td align="right"><b>Energ.Act.Fuer.Hor.Pta.</b></td>
					<td>${resori.energactfraptatotal}</td>
					<td align="right"><b>Energ.Act.Hor.Pta.</b></td>
					<td>${resori.energacthorptatotal}</td>
				</tr>
				<tr>
					<td align="right"><b>Total Monto Recibo</b></td>
					<td>${resori.numMonto}</td>
					<td align="right"><b>Pendiente Cancelación</b></td>
					<td>${resori.numMonto-resori.numPendienteFac}</td>
				</tr>
			</table> 
		</fieldset>
		<br>
		<fieldset>
			<legend>Datos del Socio</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td width="5%">Socio</td>
					<td width="20%"><input type='text' name="socio-sls" id="socio-sls" size=30 value="" style="width:200px;" readonly="readonly"/></td>
					<td width="10%">
						<c:choose>
							<c:when test="${isDetalle!=1 }">
								<button id="buscar-socio">...</button>
							</c:when>
						</c:choose>	
					</td>
					<td width="5%">Código</td>
					<td width="20%"><input type="text" name="codigo-sls" id="codigo-sls" size="10" value="" style="width:100px;" readonly="readonly" /></td>
					<td width="5%">Puesto</td>
					<td width="20%"><input type="text" name="direccion-sls" id="direccion-sls" size="10" value="" style="width:100px;" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>Estado</td>
					<td><input type="text" name="estado-sls" id="estado-sls" size="10" value="" style="width:100px;" readonly="readonly"/></td>
					<td>&nbsp;</td>
					<td>Deuda Anterior</td>
					<td><input type="text" name="deudaant-sls" id="deudaant-sls" size="10" value="" style="width:100px;" readonly="readonly"/></td>
					<td>Lectura Anterior</td>
					<td><input type="text" name="lecfin-sls" id="lecfin-sls" size="10" value="" style="width:100px;" readonly="readonly"/></td>
				</tr>
				<tr>
					<td colspan="2"><button id="nuevo-sls">Agregar</button></td>
					<td>&nbsp;</td>
				</tr>
			</table> 
	</fieldset>
	<display:table name="ListaSuministroLuz" 
				class="consultanormal"
				excludedParams="metodo" 
				requestURI="/suministroLuz.do?metodo=mostrarItemsSuministro"		
				id="row"
				export="false">
			<display:column title="" style="width:60px;">
			<c:choose>
				<c:when test="${row.estado==2}">
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" onclick="mostrarRep(${row.codigorecibo},${row.codigosocio});"/>
				</c:when>
				<c:otherwise>
					<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Eliminar..." border="0" width="16" height="16" id="" onclick="eliminarRes(${row.correlativo},${row.codigosocio},${row.codigorecibo});"/>
					<img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16" onclick="editarRes(${row.codigorecibo},${row.codigosocio},${row.correlativo},${row.lecturaini},${row.lecturafin},${row.consumomes},${row.cagofijo},${row.alupublic},${row.cargoener},${row.totalmes},${row.igv},${row.subtotalmes},${row.usoequipo},${row.servmanto},${row.aporteley},${row.recargo},${row.redondeo},${row.total},${row.deudaant});"/>
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" onclick="mostrarRep(${row.codigorecibo},${row.codigosocio});"/>
				</c:otherwise>
			</c:choose>
			</display:column>
			<display:column title="Nombres" property="nombres" sortable="true"></display:column>	
			<display:column title="Lectura Inicial" property="lecturaini" sortable="true"></display:column>
			<display:column title="Lectura Final" property="lecturafin" sortable="true"></display:column>
			<display:column title="Cargo por Energia" property="cargoener" sortable="true"></display:column>
			<display:column title="Total" property="total" sortable="true"></display:column>
				<display:column title="Estado" sortable="true">
					<c:choose>
						<c:when test="${row.estado==1}">Pendiente</c:when>
						<c:when test="${row.estado==2}">Pagado</c:when>
					</c:choose>
			</display:column>
			<display:column title="Fecha" property="fechacarga" sortable="true"></display:column>
	</display:table>
	<div id="buscarsocio-form" title="Buscar Socio">		
		<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" /> 
		<button id="btn-buscar-socio">Buscar Socio</button>
		<div id="tablesocios"></div>
	</div>
	<div id="grabar-form" title="Agregar Recibo de Luz">						
				<table width="500px">
					<tr>
						<td width="200px"><label>Lectura Inicial:</label></td>
						<td width="200px"><input type='text' name='txtLecturaInih' id='txtLecturaInih' class='text ui-widget-content ui-corner-all' size="10" onchange="calculaConsumoMes(this,txtLecturaFinh)" tabindex="1"/></td>
						<td>&nbsp;</td>
						<td width="200px"><label>Lectura Final:</label></td>
						<td width="200px"><input type='text' name='txtLecturaFinh' id='txtLecturaFinh' class='text ui-widget-content ui-corner-all'  size="30" onchange="calculaConsumoMes(txtLecturaInih,this)" tabindex="2" /></td>
					</tr>
					<tr>
						<td><label>Consumo del Mes:</label></td>
						<td><input type='text' name='txtConsumomesh' id='txtConsumomesh' class='text ui-widget-content ui-corner-all' size="30" readonly="readonly"tabindex="3" /></td>
						<td>&nbsp;</td>
						<td><label>Cargo Fijo:</label></td>
						<td><input type='text' name='txtCagofijoh' id='txtCagofijoh' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotalMes()" tabindex="4"/></td>
					</tr>
					<tr>
						<td><label>Alumbrado Público:</label></td>
						<td><input type='text' name='txtAlupublich' id='txtAlupublich' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotalMes()" tabindex="5"/></td>
						<td>&nbsp;</td>
						<td><label>Cargo Por Energia:</label></td>
						<td><input type='text' name='txtCargoenerh' id='txtCargoenerh' class='text ui-widget-content ui-corner-all' size="20" readonly="readonly" tabindex="6" /></td>
					</tr>
					<tr>
						<td><label><b>Sub Total del Mes:</b></label></td>
						<td><input type='text' name='txtSubTotalMesh' id='txtSubTotalMesh' class='text ui-widget-content ui-corner-all' size="20" readonly="readonly" tabindex="7"/></td>
						<td>&nbsp;</td>
						<td><label><b>Igv:</b></label></td>
						<td><input type='text' name='txtIgvh' id='txtIgvh' class='text ui-widget-content ui-corner-all' size="20" readonly="readonly"tabindex="8"/></td>
					</tr>
					<tr>
						<td><label><b>Total del Mes:</b></label></td>
						<td><input type='text' name='txtTotalMesh' id='txtTotalMesh' class='text ui-widget-content ui-corner-all' size="20" readonly="readonly" tabindex="9"/></td>
						<td>&nbsp;</td>
						<td><label>Cargo por uso de Equipo:</label></td>
						<td><input type='text' name='txtUsoEquipoh' id='txtUsoEquipoh' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotal()" tabindex="10"/></td>
					</tr>
					<tr>
						<td><label>Reposic., Mant. y Servicio Tecnico:</label></td>
						<td><input type='text' name='txtServmantoh' id='txtServmantoh' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotal()" tabindex="11"/></td>
						<td>&nbsp;</td>
						<td><label>Aporte Ley Nro 284449:</label></td>
						<td><input type='text' name='txtAporteleyh' id='txtAporteleyh' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotal()" tabindex="12"/></td>
					</tr>
					<tr>
						<td><label>Recargo:</label></td>
						<td><input type='text' name='txtRecargoh' id='txtRecargoh' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotal()" tabindex="13"/></td>
						<td>&nbsp;</td>
						<td><label>Redondeo del Mes:</label></td>
						<td><input type='text' name='txtRedondeoh' id='txtRedondeoh' class='text ui-widget-content ui-corner-all' size="20" onchange="calculaTotal()" tabindex="14"/></td>
					</tr>
					<tr>
						<td><label>Deuda Anterior:</label></td>
						<td><input type='text' name='txtDeudaAnth' id='txtDeudaAnth' class='text ui-widget-content ui-corner-all' size="10" onchange="calculaTotal()" readonly style="color:red; font-style: bold" tabindex="15"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><label><b>Total:</b></label></td>
						<td colspan="3"><input type='text' name='txtTotalh' id='txtTotalh' class='text ui-widget-content ui-corner-all' size="10" readonly tabindex="16"/></td>
					</tr>
				</table>
			<button name="btngrabar" id="btngrabar" onclick="grabar();">Grabar</button>
			<button name="btncerrar" id="btncerrar" onclick="cerrarPop();">Cancelar</button>
			<button name="btnpagar" id="btnpagar" onclick="pagar();" >Pagar</button>
	</div>
	<div id="eliminar-form" title="Esta seguro que desea eliminar...?">
		<div align="center">
		</div>
	</div>
	
	<div id="reciboLuz-form" title="Seleccione el recibo a imprimir..." style="display: none">
		<table border="1" cellpadding="0" cellspacing="0" width="400px">
			<tr><td><input type="radio" id="recluz" name="recluz" value="1">Recibo de Luz Pequeño</td></tr>
			<tr><td><input type="radio" id="recluz" name="recluz" value="2" checked="checked">Recibo de Luz Mediano</td></tr>
			<tr><td><input type="radio" id="recluz" name="recluz" value="3">Recibo de Luz A4</td></tr>
		</table>
	</div>
</html:form>
</body>
</html:html>