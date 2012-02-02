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

	
		//$("#registra-f").button().click(function() {
		//	$('[name=metodo]').val('grabar');
			//$('#gestionarFacturacion').submit();
			
		//});
		

		
		
	});

	function agregarSocio(codigo, razonSocial , puesto, codigoIde) {
		
		$("#codigo-f").val(codigo);
		$("#socio-f").val(razonSocial);
		$("#direccion-f").val(puesto);
		$("#codigoide-f").val(codigoIde);
		$("#buscarsocio-form").dialog("close");
	}

	function calcularTotal(){

		var total = 0;
		var costo = $("#costo-p").val();
		var cant =  $("#cantidad-p").val();
		total = costo*cant;

		$("#total-p").val( total );
	}
	
</script>
</head>
<body>
<html:form action="/gestionarFacturacion.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoide" id="codigoide-f" />
		<input type="hidden" name="cbtipodoc" value="${tipodocumento}" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
Asociar Recibo</td>
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

		<fieldset>
			<legend>Datos del Recibo</legend>
		
			<table border="0" cellpadding="0" cellspacing="0" width="75%">
				<tr>
					<td width="40px">Nro. Doc</td>
					<td width="40%"><input type="text" name="numerorecibo" readonly="readonly" size="10" value="${numerodocumento}" class="text ui-widget-content ui-corner-all" style=" width : 155px;"/></td>
				</tr>
				<tr>
					<td>Socio</td>
					<td colspan="3" ><input type='text' name="lecturaanterior" id="socio-f" size=30 value="${fac.sisgapSocio}" class="text ui-widget-content ui-corner-all" readonly="readonly"/></td>
					<td width="15px">
					
					<c:choose>
						<c:when test="${isDetalle!=1 }">
							<button id="buscar-socio">...</button>
						</c:when>
					</c:choose>
						
					</td>
					<td width="20px">Código</td>
					<td><input type="text" name="lecturaactual" id="codigo-f" size="10" value="${fac.sisgapSocio.tranCodigo}" class="text ui-widget-content ui-corner-all" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>Puesto</td>
					<td colspan="3"><input type="text" name="fechavencimiento" id="direccion-f" size="10" value="${fac.sisgapSocio.tranPuesto}" class="text ui-widget-content ui-corner-all" style=" width : 158px;" readonly="readonly"/></td>
					<td width="15px"></td>
					<td width="80px">Tipo </td>
					<td><input type="text" name="saldoactual" id="direccion-f" size="10" value="${fac.sisgapSocio.tranPuesto}" class="text ui-widget-content ui-corner-all" style=" width : 158px;" readonly="readonly"/></td></td>
				</tr>
			</table> 
	</fieldset>
		<fieldset>
			<legend>Datos del Socio</legend>
		
			<table border="0" cellpadding="0" cellspacing="0" width="75%">
				<tr>
					<td width="40px">Nro. Doc</td>
					<td width="40%"><input type="text" name="numerodocumento" readonly="readonly" size="10" value="${numerodocumento}" class="text ui-widget-content ui-corner-all" style=" width : 155px;"/></td>
				</tr>
				<tr>
					<td>Socio</td>
					<td colspan="3" ><input type='text' name="socio-f" id="socio-f" size=30 value="${fac.sisgapSocio}" class="text ui-widget-content ui-corner-all" readonly="readonly"/></td>
					<td width="15px">
					
					<c:choose>
						<c:when test="${isDetalle!=1 }">
							<button id="buscar-socio">...</button>
						</c:when>
					</c:choose>
						
					</td>
					<td width="20px">Código</td>
					<td><input type="text" name="codigo-f" id="codigo-f" size="10" value="${fac.sisgapSocio.tranCodigo}" class="text ui-widget-content ui-corner-all" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>Puesto</td>
					<td colspan="3"><input type="text" name="direccion-f" id="direccion-f" size="10" value="${fac.sisgapSocio.tranPuesto}" class="text ui-widget-content ui-corner-all" style=" width : 158px;" readonly="readonly"/></td>
					<td width="15px"></td>
					<td width="80px">Tipo </td>
					<td>&nbsp;</td>
				</tr>
			</table> 
	</fieldset>
	<div id="buscarsocio-form" title="Buscar Socio">		
		<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" /> 
		<button id="btn-buscar-socio">Buscar Socio</button>
		<div id="tablesocios"></div>
	</div>
</html:form>
</body>
</html:html>