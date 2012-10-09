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




table.staticheader {
	text-decoration: none;
	border: 1px solid #CCC;
	width: 98%;
}

table.staticheader th {
	padding: 3px 3px 3px 3px !important;
	text-align:center;
}

table.staticheader td {
	padding: 3px 3px 3px 3px !important;
}

table.staticheader thead tr {
	position: relative;
	height: 10px;
	background-color: #D7E5F3;
}

table.staticheader tbody {
	height:150px;
	overflow-x:hidden;
	overflow-y: auto; 
	overflow:scroll;
}

table.staticheader tbody tr {
	height: auto;
	white-space: nowrap;
}

table.staticheader tbody tr.odd {
	background-color: #eee
}

table.staticheader tbody tr.tableRowEven,tr.even {
	background-color: #ddd
}

table.staticheader tbody tr td:last-child {
	padding-right: 20px;
}

table.staticheader tbody td {
	padding: 2px 4px 2px 4px !important;            
}

div.TableContainer {
	height: 300px; 
	overflow-x:hidden; 
	overflow-y:auto;
}
 
</style>


<script type="text/javascript">

var codTipCob = "";
var codMon = "";

	$(function() {
		$("#nuevo-as").button();
		$("#reporte-as").button();
		
		$("#btngrabar").button();
		$("#btncerrar").button();
		$("#btnpagar").button();

		$('#nuevo-as').click(function() {
			if ($("#codigo-as").val()=="" || $("#socio-as").val()=="" || $("#direccion-as").val()=="" ){
				alert("Debe buscar y seleccionar el Socio para marcar su asistencia");
				return false;
			}else{
				var recibo =  $('[codigoasamblea]').val();    // ${lstAsambleaRes1.numCodReuniones};
				var socio =  $('[name=codigo-as]').val();
				var puesto =  $('[name=direccion-as]').val();
				//alert("recibo "+recibo+"\nsocio "+socio+"\npuesto "+puesto);
				
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_EXISTE_SOCIO_ASAMBLEA&recibo="+recibo+"&socio="+socio/*+"&puesto="+puesto*/,
					success: function(datos){
			        	//$("#tablesocios").html(datos);
			        //	$("#respuesta").val();
			        	
			        	if(datos=="true"){
							alert("El Socio que intenta ingresar, ya fue registrado");
							return false;
						}else{
							//alert("Antes de grabar");
							grabar();
						}
				    }
				});
	     		return false;
			}

		});

		$("#reporte-as").button().click(function() {
			alert("Enviando reporte");
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
		    nueva=window.open('ReportsServlet?reporte=REPORTE_SOCIOS_ASAMBLEAS&periodo=0&codigo=0&provider=other', 'Popup', caracteristicas); 
		    $(this).dialog("close");
		    return false;
		});
		
		$("#grabar-form").dialog({
			autoOpen : false,
			height : 380,
			width : 600,
			modal : true,		
			close : function() {
				allFields.val("").removeClass("ui-state-error");
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

	
	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore
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
				height : 350,
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
			var nropto =  $('[name=numeropuesto]').val();
			var nrodni =  $('[name=numerodni]').val();
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_SOCIO&nombre="+nombre+"&opcion=reciboluz&nropto="+nropto+"&nrodni="+nrodni,
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
					var frm = document.gestionarAsistencia;								
					frm.submit();
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});

		$("table#person").createScrollableTable({  
			width: '800px',  
			height: '400px' 
		}); 
					

	});

	
	function grabar() {
		var frm = document.gestionarAsistencia;
		
		$("#codigosociox").val($("#codigo-as").val());
		$("#sociox").val($("#socio-as").val());
		$("#direccionx").val($("#direccion-as").val());
		$("#estadox").val($("#estado-as").val());
		$("#observacionesx").val($("#observaciones-as").val());
			
		if($("#correlativo").val()==''){
			$("#metodo").val("grabarAsambleaSocio");
		}else{
			$("#metodo").val("actualizarAsambleaSocio");
		}

		
		frm.submit();
	}


	function eliminarRes(creu,csoc,ccor){
		var frm = document.gestionarAsistencia;
		
		$("#codigoModi").val(creu);
		$("#codigoAsoc").val(csoc);
		$("#codigoCorr").val(ccor);

		$("#metodo").val("eliminarAsociadoReunion");

		$("#eliminar-form").dialog("open");
	}
	
	function agregarSocio(codigo, razonSocial , puesto, estado, deudaant, codigoIde, lecFin) {
		$("#codigo-as").val(codigo);
		$("#socio-as").val(razonSocial);
		$("#direccion-as").val(puesto);
		if (estado == 1){
			$("#estado-as").val("PENDIENTE");
		}else if (estado == 2){
			$("#estado-as").val("PAGADO");
		}else{
			$("#estado-as").val("-----");
		}
		if (deudaant=="---"){
			$("#deudaant-as").val(0);
		} else {
			$("#deudaant-as").val(deudaant);
		}
		$("#codigoide").val(codigoIde);
		$("#lecfin-as").val(lecFin);
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
	
	
	function cerrarPop() {
		$("#grabar-form").dialog("close");
	}

	function pagar(){
		var frm = document.gestionarAsistencia;
		$("#metodo").val("pagarItemReciboLuzSocio");
		frm.submit();
	}

</script>
</head>
<body>
<html:form action="/controlAsistencia.do" styleId="gestionarAsistencia">
		<input type="hidden" name="metodo" id="metodo"/>
		<input type="hidden" name="codigoModi" id="codigoModi"/>
		<input type="hidden" name="codigoAsoc" id="codigoAsoc"/>
		<input type="hidden" name="codigoCorr" id="codigoCorr"/>
		
		<input type="hidden" name="codigoide" id="codigoide"/>
		<input type="hidden" name="codigocod" id="codigocod" />
		<input type="hidden" name="correlativo" id="correlativo"/>
		
		
		<input type="hidden" name="codigoasamblea" id="codigoasamblea" value="${lstAsambleaRes1.numCodReuniones}" />
		<input type="hidden" name="fechaasamblea" id="fechaasamblea" value="${lstAsambleaRes1.datFechaSesion}" />
		<input type="hidden" name="codigosociox" id="codigosociox" />
		<input type="hidden" name="sociox" id="sociox" />
		<input type="hidden" name="direccionx" id="direccionx" />
		<input type="hidden" name="estadox" id="estadox" />
		<input type="hidden" name="observacionesx" id="observacionesx" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración / Agregar Asistencia</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td id="mensaje" align="center" valign="middle"	style="display: none"></td>
				<td id="error" align="center" valign="middle" class="mensajeError" style="display: none"></td>
			</tr>
		</table>

		<fieldset>
			<legend>Datos de la Asamblea</legend>
			<table border="0" cellpadding="2" cellspacing="2" width="75%">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td width="150px" align="right"><b>Codigo Asamblea :</b></td>
					<td>&nbsp;${lstAsambleaRes1.numCodReuniones}</td>
					<td width="250px" align="right"><b>Fecha Asamblea :</b></td>
					<td>&nbsp;${lstAsambleaRes1.datFechaSesion}</td>
				</tr>
				<tr>
					<td align="right"><b>Lugar de la Asamblea :</b></td>
					<td colspan="3">&nbsp;${lstAsambleaRes1.strLugar}</td> 
				</tr>
			</table> 
		</fieldset>
		<br>
		<fieldset>
			<legend>Datos del Socio</legend>
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
				<tr>
					<td width="5%">Socio</td>
					<td width="20%"><input type='text' name="socio-as" id="socio-as" size=30 value="" style="width:200px;" readonly="readonly"/></td>
					<td width="10%">
						<c:choose>
							<c:when test="${isDetalle!=1 }">
								<button id="buscar-socio">...</button>
							</c:when>
						</c:choose>	
					</td>
					<td width="5%">Código</td>
					<td width="20%"><input type="text" name="codigo-as" id="codigo-as" size="10" value="" style="width:100px;" readonly="readonly" /></td>
					<td width="5%">Puesto</td>
					<td width="20%"><input type="text" name="direccion-as" id="direccion-as" size="10" value="" style="width:100px;" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>Estado</td>
					<td><input type="text" name="estado-as" id="estado-as" size="10" value="" style="width:100px;" readonly="readonly"/></td>
					<td>Observaciones</td>
					<td colspan="4"><input type="text" name="estado-as" id="observaciones-as" size="10" value="" style="width:350px; text-transform: uppercase;" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><button id="nuevo-as">Agregar</button><button id="reporte-as">Imprimir Reporte</button></td>
				</tr>
			</table> 
	</fieldset>

	<div class="TableContainer" style="">	 
	<display:table name="lstAsambleaSocio" 
				class="staticheader"
				excludedParams="metodo" 
				requestURI="/controlAsistencia.do?metodo=mostrarItemsAsamblea"		
				id="row"
				export="false">
			<display:setProperty name="basic.empty.showtable" value="true" />
			<display:column title="" style="width:60px;">
			<c:choose>
				<c:when test="${row.estado==2}">
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" > <!-- onclick="mostrarRep(${row.codigoreuniones},${row.codigosocios},${row.codigocorrelativo});"/ -->
				</c:when>
				<c:otherwise>
					<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Eliminar..." border="0" width="16" height="16" onclick="eliminarRes(${row.codigoreuniones},'${row.codigosocios}',${row.codigocorrelativo});"/>
					<!-- img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16"/>
					<img src="<%=request.getContextPath()%>/imagenes/manto/ver.png" alt="Ver..." border="0" width="16" height="16" /><!-- mostrarRep(${row.codigoreuniones},${row.codigosocios},${row.codigocorrelativo});"/-->
				</c:otherwise>
			</c:choose>
			</display:column>
			<display:column title="Nro" property="codigocorrelativo" sortable="true" style="width:20px; text-align:center"></display:column>
			<display:column title="Cod.Asamb." property="codigoreuniones" sortable="true" style="width:80px; text-align:center"></display:column>
			<display:column title="Cod.Socio" property="codigosocios" sortable="true" style="width:70px; text-align:center"></display:column>
			<display:column title="Nombre Socios" property="nombres" sortable="true" style="width:280px;"></display:column>
			<display:column title="Sector" property="sector" sortable="true" style="width:20px"></display:column>
			<display:column title="Fec.Asamb." sortable="true" style="width:80px; text-align:center">
				<fmt:formatDate pattern="dd MMM yyyy" value="${row.fechaasamblea}" />
			</display:column>
			<display:column title="Observaciones" property="observaciones" sortable="true" style="width:300px"></display:column>
			<display:column title="Estado" sortable="true" style="width:80px; text-align:center;">
				<c:choose>
					<c:when test="${row.estado==1}"><font color="green">Presente</font></c:when>
					<c:when test="${row.estado==2}"><font color="red"><b>Falto</b></font></c:when>
				</c:choose>
			</display:column>
			<display:column title="Fec.Ing." sortable="true">
				<fmt:formatDate pattern="dd MMM yyyy" value="${row.fechaingreso}" />
			</display:column>
	</display:table>
	
	</div>
	
	<div id="buscarsocio-form" title="Buscar Socio">		
		Ingrese Nombre Socio:<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" />
		Ingrese Numero Puesto:<input type="text" name="numeropuesto" id="numeropuesto" style="width: 100px" />
		Ingrese Numero DNI:<input type="text" name="numerodni" id="numerodni" style="width: 100px" />
		<br> 
		<button id="btn-buscar-socio">Buscar Socio</button>
		<div id="tablesocios"></div>
	</div>
	<div id="eliminar-form" title="Esta seguro que desea eliminar...?">
		<div align="center">
		</div>
	</div>
</html:form>
</body>
</html:html>