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

		$("#btn-aceptar-item").button();
		$("#btn-cancelar").button();
		$("#registra-f").button();
		$("#regresar-f").button();
		$("#imprimir-f").button();
		$("#cancelar-f").button();
		
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

		$('#imprimir-f').click(function() {
			alert("imprimiendo...");
			var nroDocu =  $('[name=numerodocumento]').val();    
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        /*nueva=window.open('ReportsServlet?reporte=REPORTE_DOCUMENTO_DETALLE&nrodoc=${numerodocumento}', 'Popup', caracteristicas);  
	        nueva=window.open('ReportsServlet?reporte=REPORTE_DOCUMENTO_DETALLE&nroDoc='+ nroDocu , 'Popup', caracteristicas);*/
	        nueva=window.open("gestionarFacturacion.do?metodo=imprimirFactura&nrodocumento="+nroDocu,'Popup', caracteristicas);
	        //window.open("gestionarFacturacion.do?metodo=imprimirFactura&nrodocumento="+nroDocu,"","height=400,width=680,top=120,left=180,scrollbars=yes,resizable=no,toolbar=no,menubar=no,location=no,status=yes");
	        return false;  
		});

		$('#cancelar-f').click(function() {  
			$('[name=metodo]').val('cancelarFactura');
		});
		
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


		$("#buscarsocio-form").dialog({
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

		$("#dialog-form-item").dialog(
		{
			autoOpen : false,
			height : 450,
			width : 350,
			modal : true
		});

		
		$("#dialog-form").dialog(
		{
			autoOpen : false,
			height : 520,
			width : 550,
			modal : true,
			buttons : {
											 
				/*
				"Create an account" : function() {
					var bValid = true;
					allFields.removeClass("ui-state-error");

					bValid = bValid
							&& checkLength(name, "username", 3,
									16);
					bValid = bValid
							&& checkLength(email, "email", 6,
									80);
					bValid = bValid
							&& checkLength(password,
									"password", 5, 16);

					bValid = bValid
							&& checkRegexp(name,
									/^[a-z]([0-9a-z_])+$/i,
									"Username may consist of a-z, 0-9, underscores, begin with a letter.");
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
					bValid = bValid
							&& checkRegexp(
									email,
									/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
									"eg. ui@jquery.com");
					bValid = bValid
							&& checkRegexp(password,
									/^([0-9a-zA-Z])+$/,
									"Password field only allow : a-z 0-9");

					if (bValid) {
						$("#users tbody").append(
								"<tr>" + "<td>" + name.val()
										+ "</td>" + "<td>"
										+ email.val() + "</td>"
										+ "<td>"
										+ password.val()
										+ "</td>" + "</tr>");
						$(this).dialog("close");
						
						$("#dialog-form-item").dialog("open");
					} }*/									
				
				/*
				Cancel : function() {
					$(this).dialog("close");
				}*/
		
			/*close : function() {
				allFields.val("").removeClass("ui-state-error");
			}*/

			Cancel : function() {
				$(this).dialog("close");
			}
		}});

		$("#create-user").button().click(function() {
			$("#dialog-form").dialog("open");
			return false;
		});

		$("#buscar-socio").button().click(function() {
			$("#buscarsocio-form").dialog("open");
			return false;
		});

		$("#btn-buscar-socio").button().click(function() {
			var nombre =  $('[name=nombresocio]').val();
			var nropto =  $('[name=numeropuesto]').val();
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_SOCIO&nombre="+nombre+"&opcion=factura&nropto="+nropto,
			        success: function(datos){
			        	$("#tablesocios").html(datos);
			      }
			});
		});

		$("#btn-aceptar-item").button().click(function() {
			var campos="codigo="+ $("#codigo-p").val() + "&descrip="+ $("#descrip-p").val() + "&codTipCob="+ codTipCob + "&codMon="+ codMon  +
			"&costo="+ $("#costo-p").val() + "&cantidad="+ $("#cantidad-p").val() + "&acuenta="+ $("#acuenta-p").val() + "&total="+ $("#total-p").val();;
					
					$.ajax({
				        type: "POST",
				        url: "/SISGAPWeb/AjaxServlet",
				        data: "action=AGREGAR_ITEM&"+campos,
				        success: function(datos){					        
				        	$("#tableDetalle").html(datos);
				      }
				});
			$("#dialog-form-item").dialog("close");
		});
		

		$("#btn-buscar-producto").button().click(function() {
			var nombre =  $('#nombreProducto').val();
					$.ajax({
				        type: "POST",
				        url: "/SISGAPWeb/AjaxServlet",
				        data: "action=BUSCAR_PRODUCTO&nombre="+nombre,
				        success: function(datos){					        
				        	$("#tableProducto").html(datos);
				      }
				});
		});

		
		$("#registra-f").button().click(function() {
			$('[name=metodo]').val('grabar');
			$('#gestionarFacturacion').submit();
			
		});

		$("#regresar-f").button().click(function() {
			$('[name=metodo]').val('cargarAction');
			$('#gestionarFacturacion').submit();
		});
		

		$("btn-cancelar").button().click(function() {
			$("#dialog-form-item").dialog("close");
			$('#gestionarFacturacion').submit();
		});
		
		
	});


	function agregarItem(codigo, descrip, codTipCobranza, codMoneda , costo, desCodTipCobranza, desCodMoneda) {

		codTipCob = codTipCobranza;
		codMon = codMoneda;
		
		$("#dialog-form").dialog("close");
		$("#codigo-p").val(codigo);
		$("#descrip-p").val(descrip);
		$("#tipoCobranza-p").val(desCodTipCobranza);
		$("#moneda-p").val(desCodMoneda);
		$("#costo-p").val(costo);
		$("#total-p").val(0);
		$("#acuenta-p").val(0);
		$("#cantidad-p").val(0);
		$("#dialog-form-item").dialog("open");
		$("#cantidad-p").focus();
		
	} 

	function eliminarDetalle(codigo) {

		$.ajax({
	        type: "POST",
	        url: "/SISGAPWeb/AjaxServlet",
	        data: "action=ELIMINAR_ITEM&codigoItem="+codigo,
	        success: function(datos){					        
	        	$("#tableDetalle").html(datos);
	      }
		});		
		
	} 

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
		<input type="hidden" name="nrodocumento" value="${numerodocumento}" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración/Facturación</td>
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

		<fieldset>
			<legend>Nuevo registro</legend>		
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
					<td width="80px">Tipo Documento</td>
					<td>
						<select name="cbtipodocx" id="cbtipodoc" class="text ui-widget-content ui-corner-all" disabled="disabled">
							<c:choose>
								<c:when test="${tipodocumento =='R'}">
									<option value="R" selected="selected">Recibo</option>
									<option value="B">Boleta</option>
								</c:when>
								<c:when test="${tipodocumento =='B'}">
									<option value="R">Recibo</option>
									<option value="B" selected="selected">Boleta</option>
								</c:when>
							</c:choose>
						</select>
					</td>
				</tr>
			</table> 
		</fieldset>
		<fieldset>
			<legend>Detalle de Factura</legend>
			<span>
				<div id="users-contain" class="ui-widget" width="100%">
					<c:choose>
						<c:when test="${isDetalle!=1 }">
							<button id="create-user">Agregar Item</button>
						</c:when>
					</c:choose>					
					<div id="tableDetalle">
						<c:choose>
							<c:when test="${isDetalle==1}">
								<table id='detalle-f' class='ui-widget ui-widget-content' width='100%'>
								<thead>
								<tr class='ui-widget-header'>
									<th>Action</th>
									<th>C&oacutedigo</th>
									<th>Descripci&oacuten</th>
									<th>Tipo de Cobranza</th>
									<th>Moneda</th>
									<th>Costo</th>
									<th>Cantidad</th>
									<th>Total</th>
									<th>A Cuenta</th>
								</tr>
								</thead>
								<tbody>
									<c:forEach var="det" items="${lstDetFac}">
										<tr>					
											<td></td>
											<td>${det.id.codItemcobranza}</td>
											<td>${det.strDescripcion}</td>
											<td>${det.strTipocobranzaDescrip}</td>
											<td>${det.strMonedaDescrip}</td>
											<td>${det.numCosto}</td>
											<td>${det.numCantidad}</td>
											<td>${det.numTotal}</td>
											<td>${det.numAcuenta}</td>
										</tr>
									</c:forEach>
									<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td>Total</td>
									<td>${fac.numTotal}</td>
									<td></td>
									</tr>
								</tbody>
								</table>
								<c:if test="${fac.numEstado==3}">
									<div style="color: red;">
									  El Documento fue anulado por el siguiente motivo: ${fac.strDescanulada}
									</div>
								</c:if>				
							</c:when>
						</c:choose>	
					</div>
				</div>
				<c:choose>
					<c:when test="${isDetalle!=1}">
						<button id="registra-f">Registrar Factura</button>
					</c:when>
					<c:when test="${isDetalle==1}">
						<button id="regresar-f">Regresar</button>
						<button id="imprimir-f">Imprimir</button>
						<button id="cancelar-f">Pagar</button>
					</c:when>					
				</c:choose>	
			</span>
		</fieldset>
		<div id="dialog-form" title="Agregar Producto">
			<input type="text" name="nombreProducto" id="nombreProducto" class="text ui-widget-content ui-corner-all" /> 
			<button id="btn-buscar-producto">Buscar Producto</button>
			<div id="tableProducto"></div>
		</div>
		
		<div id="dialog-form-item" title="Agregar Item a Detalle de Factura">
				<fieldset>					
						<!--legend>Registro</legend-->
						<br>
						<table width="300px" border="1">
							<tr>
								<td><label>Codigo:</label></td>
								<td><input type='text' name='codigo-p' id='codigo-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="10" /></td>
							</tr>
							<tr>
								<td><label>Descripcion:</label></td>
								<td><input type='text' name='descrip-p' id='descrip-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="30" /></td>
							</tr>
							<tr>
								<td><label>Tipo de Cobranza:</label></td>
								<td><input type='text' name='tipoCobranza-p' id='tipoCobranza-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="30" /></td>
							</tr>
							<tr>
								<td><label>Moneda:</label></td>
								<td><input type='text' name='moneda-p' id='moneda-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="10" /></td>
							</tr>
							<tr>
								<td><label>Costo:</label></td>
								<td><input type='text' name='costo-p' id='costo-p' class='text ui-widget-content ui-corner-all'  readonly='readonly' size="10"/></td>
							</tr>
							<tr>
								<td><label>Cantidad:</label></td>
								<td><input type='text' name='cantidad-p' id='cantidad-p' class='text ui-widget-content ui-corner-all' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" /></td>
							</tr>
							<tr>
								<td><label>Total:</label></td>
								<td><input type='text' name='total-p' id='total-p' class='text ui-widget-content ui-corner-all' size="10" readonly='readonly'/></td>
							</tr>
							<tr>
								<td><label>A Cuenta:</label></td>
								<td><input type='text' name='acuenta-p' id='acuenta-p' class='text ui-widget-content ui-corner-all' size="10" /></td>
							</tr>
							<tr>
								<td><button id="btn-aceptar-item">Agregar</button></td>
								<td><button id="btn-cancelar">Cancelar</button></td>
							</tr>
						</table>
																										
					</fieldset>
		</div>
				 
		<div id="buscarsocio-form" title="Buscar Socio">		
			Ingrese Nombre Socio:<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" />
			Ingrese Numero Puesto:<input type="text" name="numeropuesto" id="numeropuesto" style="width: 100px" />
			<br>
			<button id="btn-buscar-socio">Buscar Socio</button>
			<div id="tablesocios"></div>
		</div>
		</html:form>
</body>
</html:html>