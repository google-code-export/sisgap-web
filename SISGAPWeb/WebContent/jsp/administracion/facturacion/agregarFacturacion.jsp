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



.texto {
	background-color:rgb(189,252,152);
	color:rgb(0,0,0);
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
			$("#codigo-p").val();
			$("#txtNroDoc").val($('[name=numeroreal]').val());
			$("#selectImp-form").dialog("open");
			return false;
		});


		$("#selectImp-form").dialog({
			autoOpen : false,
			height : 120,
			width : 350,
			modal : true,
			buttons : {
				Generar : function() {
					var nroDocReal =  $('[name=txtNroDoc]').val();
					var nroDocInterno = $('[name=numerodocumento]').val();
					var tipDocumento = $('[name=cbtipodocx]').val();
					var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";
					//alert("Tipo Doc. "+tipDocumento);
					if(tipDocumento=='R'){  
			        	nueva=window.open("gestionarFacturacion.do?metodo=imprimirRecibo&nrodocumentoReal="+nroDocReal+"&nrodocumentoInterno="+nroDocInterno,'Popup', caracteristicas);
					}
					if(tipDocumento=='B'){  
			        	nueva=window.open("gestionarFacturacion.do?metodo=imprimirBoleta&nrodocumentoReal="+nroDocReal+"&nrodocumentoInterno="+nroDocInterno,'Popup', caracteristicas);
					}
			        //return false;
			        $(this).dialog("close"); 
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});

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

		$.ajaxSetup({cache: false}); 

		//$('#cancelar-f').click(function() {  
		//	$('[name=metodo]').val('cancelarFactura');
		//});
		
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
			height : 390,
			width : 480,
			modal : true
		});

		
		$("#dialog-form").dialog(
		{
			autoOpen : false,
			height : 520,
			width : 550,
			modal : true,
			buttons : {											 

			Cancel : function() {
				$(this).dialog("close");
			}
		}});

		$("#create-user").button().click(function() {
			var fecdoc = $('[name=fechadocumento]').val();
			var nomsoc = $('[name=socio-f]').val();
			if (fecdoc==""){
				alert("debe ingresar la fecha del documento...");
				$('[name=fechadocumento]').focus();
			}else if (nomsoc==""){
				alert("debe seleccionar el socio...");
				$('[name=buscar-socio]').focus();
			}else{
				$("#registra-f").removeAttr("disabled");
				alert("Antes de abrir ventana...");
				$("#dialog-form").dialog("open");
			}
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
			var campos="codigo="+ $("#codigo-p").val() + "&descrip="+ $("#descrip-p").val() + " " + $("#especif-p").val() + "&codTipCob="+ codTipCob + "&codMon="+ codMon  +
			"&costo="+ $("#costo-p").val() + "&cantidad="+ $("#cantidad-p").val() + "&acuenta="+ $("#acuenta-p").val() + "&total="+ $("#total-p").val();
					
					$.ajax({
				        type: "POST",
				        url: "/SISGAPWeb/AjaxServlet",
				        data: "action=AGREGAR_ITEM&"+campos,
				        success: function(datos){					        
				        	$("#tableDetalle").html(datos);
				      }
				});

			$("#cantidad-p").val(redondea(0,2));
			$("#total-p").val(redondea(0,2));
			$("#acuenta-p").val(redondea(0,2));
			$("#especif-p").val("");					
			$("#dialog-form-item").dialog("close");
		});
		

		$("#btn-buscar-producto").button().click(function() {
			var nombre =  $('#nombreProducto').val();
			var codigo = $("#codigo-f").val();
			var codide = $("#codigoide-f").val();
					$.ajax({
				        type: "POST",
				        url: "/SISGAPWeb/AjaxServlet",
				        data: "action=BUSCAR_PRODUCTO&nombre="+nombre+"&codigo="+codigo+"&codide="+codide,
				        success: function(datos){					        
				        	$("#tableProducto").html(datos);
				      }
				});
		});

		
		$("#registra-f").button().click(function() {
			if (${'#lstDetFac'}.size())
			$('[name=metodo]').val('grabar');
			$('#gestionarFacturacion').submit();
			
		});

		$("#regresar-f").button().click(function() {
			$('[name=metodo]').val('cargarAction');
			$('#gestionarFacturacion').submit();
		});
		

		$("#btn-cancelar").button().click(function() {
    		$("#cantidad-p").val(redondea(0,2));
    		$("#total-p").val(redondea(0,2));
    		$("#acuenta-p").val(redondea(0,2));
    		$("#especif-p").val("");
			$("#dialog-form-item").dialog("close");
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


	function agregarItem(codigo, descrip, codTipCobranza, codMoneda , costo, desCodTipCobranza, desCodMoneda, codReciboLuz) {
		codTipCob = codTipCobranza;
		descriCob = descrip;
		codMon = codMoneda;
		var codide = $("#codigoide-f").val();

		//Verifica Item Seleccionado si es recibo de luz; jala el total del socio
		//alert("codReciboLuz--> "+codReciboLuz);
		if (codReciboLuz=="" || codReciboLuz==0){
			$.ajax({
		        type: "POST",
		        url: "/SISGAPWeb/AjaxServlet",
		        data: "action=BUSCAR_RECIBO_ESPECIAL&descriCob="+descriCob,
		        success: function(datos){
			        //alert(datos);
			        if(datos==""){
				        alert("Item de generacion especial");
				        return false;
				    }else{
						//alert("Fecha: "+datos.substring(0,10)+"\nFlgVar: "+datos.substring(10,11)+"\nCostoAdd: "+datos.substring(11,12));
						var costoAD = datos.substring(11,datos.length);
						//alert("costoAD: "+costoAD);
						var fechaBD = datos.substring(0,10);
						temp=""+fechaBD;
						while (temp.indexOf("-")>-1){
							pos=temp.indexOf("-");
							temp=""+(temp.substring(0,pos)+"/"+temp.substring((pos+1),temp.length));
						}
						fechaBD=temp;
						/*var fecha = new Date();
						var dd = fecha.getDate();
						var mm = fecha.getMonth()+1;
						var yy = fecha.getYear();*/
						var fecha = $("#fechadocumento").val();
						var dd = fecha.substring(8,10);
						var mm = fecha.substring(5,7);
						var yy = fecha.substring(0,4);						
						var fecha_textual = [yy, mm, dd].join("/");
						/*var fecha_final = fecha_textual.split("/"); //Cortas en partes la cadena que obtienes del calendario
						var fecha_textual = $("#fechadocumento").val();
						var fecha_final = fecha_textual.split("/");
						var fhcorrecta = fecha_final[0] + "/" + ( (parseInt(fecha_final[1]) < 10) ? ("0" + fecha_final[1]) : fecha_final[1] ) + "/" + ( (parseInt(fecha_final[2]) < 10) ? ("0" + fecha_final[2]) : fecha_final[2] );*/
						//alert("fechaBD ["+fechaBD+"]\nfecha_textual ["+fecha_textual+"]");
						var datePat =/^(\d{4})(\/)(\d{1,2})\2(\d{1,2})$/;
						var matchArray = fechaBD.match(datePat);
						if (matchArray == null)
						{
							return false;
						}
						mes = matchArray[3];
						dia = matchArray[4];
						ano = matchArray[1];
						FecIni = ano+mes+dia;
						
						var matchArray = fecha_textual.match(datePat);
						if (matchArray == null)
						{
							return false;
						}
						mes = matchArray[3];
						dia = matchArray[4];
						ano = matchArray[1];
						FecFin = ano+mes+dia;
						//alert("valor de FecIni "+FecIni+"\nvalor de FecFin "+FecFin);
						var total = 0;
						var costo = parseFloat($("#costo-p").val());
						var adici = parseFloat(costoAD);
						if(FecIni<FecFin)
						{
							total=costo+adici;
							$("#costo-p").val(total);	
							//alert(total);	
						}
						
						if (datos.substring(10,11)=="S"){
							$("#costo-p").removeAttr("disabled");
							$("#costo-p").val(0);
						}else{
							$("#costo-p").attr("disabled","-1");
						}

					}
		        }
			});
		}else{	
			$.ajax({
		        type: "POST",
		        url: "/SISGAPWeb/AjaxServlet",
		        data: "action=BUSCAR_RECIBO_LUZ_SOCIO&codide="+codide+"&codReciboLuz="+codReciboLuz,
		        success: function(datos){
		        	if(datos==""){
						alert("El Socio no tiene recibo de luz generado.");
						return false;
		        	}else{
		        		$("#cantidad-p").val(redondea(datos,2));
		        		$("#total-p").val(redondea(datos,2));
		        		$("#acuenta-p").val(redondea(datos,2));	        	
			        }
		      	}
			});
		}
		
		
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

	function redondea(sVal, nDec){ 
		var n = parseFloat(sVal); 
		var s = "0.00"; 
		if (!isNaN(n)){ 
			n = Math.round(n * Math.pow(10, nDec)) / Math.pow(10, nDec); 
			s = String(n); 
			s += (s.indexOf(".") == -1? ".": "") + String(Math.pow(10, nDec)).substr(1); 
			s = s.substr(0, s.indexOf(".") + nDec + 1); 
		} 
		return s; 
	}
	
	function compara_fechas(fechaBase, fechaComparar)
	{
		var valIni=Trim(fechaBase);
		var valFin=Trim(fechaComparar);
		alert("validando fechas");
		if(Trim(valIni)!="" && Trim(valFin)!="")
		{
			alert("si tiene datos");
			var datePat =/^(\d{4})(\/)(\d{1,2})\2(\d{1,2})$/;
			var matchArray = valIni.match(datePat);
			if (matchArray == null)
				return false;

			mes = matchArray[3];
			dia = matchArray[4];
			ano = matchArray[1];
			FecIni = ano+mes+dia;
			alert("valor de FecIni "+FecIni);
			
			var matchArray = valFin.match(datePat);
			if (matchArray == null)
				return false;

			mes = matchArray[3];
			dia = matchArray[4];
			ano = matchArray[1];
			FecFin = ano+mes+dia;
			alert("valor de FecFin "+FecFin);
			
			if(fecIni<=fecFin)
			{
				alert("La fecha fecIni es menor o igual que la fecha fecFin");
				return false;
			}else{
				alert("La fecha fecIni es mayor que la fecha fecFin");
				return false;
			}
		}
		
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

		$("#total-p").val(redondea(total,2));
	}

	function pagar(){
		$('[name=metodo]').val('cancelarFactura');	
		$('#gestionarFacturacion').submit();
	}
	
</script>
</head>
<body>
<html:form action="/gestionarFacturacion.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoide" id="codigoide-f" />
		<input type="hidden" name="cbtipodoc" value="${tipodocumento}" />
		<input type="hidden" name="nrodocumento" value="${numerodocumento}" />
		<input type="hidden" name="nroreal" value="${numeroreal}" />
		<input type="hidden" name="fecdocumento" value="${fechadocumento}" />
		<input type="hidden" name="socio" value="${socio-f}" />
		<input type="hidden" name="direccion" value="${direccion-f}" />
		<input type="hidden" name="detfactura" value="${lstDetFac}" />
		<input type="hidden" name="nroacuenta" value="${numeroacuenta}" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración/Recibo de Ingreso/Boleta de Venta</td>
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
			<legend><b>Nuevo registro</b></legend>		
			<table border="0" cellpadding="0" cellspacing="0" width="75%">
				<tr>
					<td width="100px">Nro. Doc</td>
					<td><input type="text" name="numerodocumento" readonly="readonly" size="10" value="${numerodocumento}" class="text ui-widget-content ui-corner-all" style=" width : 80px;"/></td>
					<td width="80px">Nro. Real</td>
					<td><input type="text" name="numeroreal" readonly="readonly" size="10" value="${numeroreal}" class="text ui-widget-content ui-corner-all" style=" width : 80px;"/></td>
					<td width="100px">Fecha Documento</td>
					<td><input type='text' name='fechadocumento' id='fechadocumento' class='text ui-widget-content ui-corner-all' size="20" value="${fechadocumento}" style=" width : 80px;" 
					<% if(request.getAttribute("estadoCampos").equals("true")){ 
						System.out.println("Estado: "+request.getAttribute("estadoCampos"));
					%>
					disabled="disabled"
					<%} %> tabindex="21"/>(año/mes/dia)</td>
				</tr>
				<tr>
					<td>Socio</td>
					<td><input type='text' name="socio-f" id="socio-f" size=30 value="${fac.sisgapSocio}" class="text ui-widget-content ui-corner-all" readonly="readonly" style="width: 200px" tabindex="22"/></td>
					<td width="15px">
						<c:choose>
							<c:when test="${isDetalle!=1 }">
								<button name="buscar-socio" id="buscar-socio" tabindex="23">...</button>
							</c:when>
						</c:choose>
					</td>
					<td>&nbsp;</td>
					<td>Código</td>
					<td><input type="text" name="codigo-f" id="codigo-f" size="10" value="${fac.sisgapSocio.tranCodigo}" class="text ui-widget-content ui-corner-all" readonly="readonly" tabindex="24"/></td>
				</tr>
				<tr>
					<td>Puesto</td>
					<td><input type="text" name="direccion-f" id="direccion-f" size="10" value="${fac.sisgapSocio.tranPuesto}" class="text ui-widget-content ui-corner-all" style=" width : 158px;" readonly="readonly" tabindex="25"/></td>
					<td>Tipo Documento</td>
					<td>
						<select name="cbtipodocx" id="cbtipodoc" class="text ui-widget-content ui-corner-all" disabled="disabled" style=" width : 100px;">
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
							<button id="create-user" tabindex="6">Agregar Item</button>
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
											<td align="right">
												<fmt:formatNumber value="${det.numCosto}" type="number" maxFractionDigits="2" minFractionDigits="2" var="nCosto" />
												<c:out value="${nCosto}" />
											</td>
											<td>${det.numCantidad}</td>
											<td align="right">
												<fmt:formatNumber value="${det.numTotal}" type="number" maxFractionDigits="2" minFractionDigits="2" var="nTotal" />
												<c:out value="${nTotal}" />
											</td>
											<td align="right">
												<fmt:formatNumber value="${det.numAcuenta}" type="number" maxFractionDigits="2" minFractionDigits="2" var="nAcuenta" />
												<c:out value="${nAcuenta}" />
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><font size="4"><b>Total</b></font></td>
										<td align="right">
											<font size="4">
												<fmt:formatNumber value="${fac.numTotal}" type="currency" var="numTotal" />
												<c:out value="${numTotal}" />
											</font>
										</td>
										<td align="right">
											<font size="3">
												<fmt:formatNumber value="${fac.numAcuenta}" type="currency" var="numAcuenta" />
												<c:out value="${numAcuenta}" />
											</font>
										</td>
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
						<button id="registra-f" name="registra-f">Registrar Factura</button>
					</c:when>
					<c:when test="${isDetalle==1}">
						<button id="regresar-f" name="regresar-f">Regresar</button>
						<button id="imprimir-f" name="imprimir-f">Imprimir</button>
						<button id="cancelar-f" name="cancelar-f" onclick="pagar();">Pagar</button>
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
				<table width="450px" border="0">
					<tr>
						<td><label>Codigo:</label></td>
						<td><input type='text' name='codigo-p' id='codigo-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="10" style=" width : 50px;" tabindex="1"/></td>
					</tr>
					<tr>
						<td><label>Descripcion:</label></td>
						<td><input type='text' name='descrip-p' id='descrip-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="30" style=" width : 280px;" tabindex="2"/></td>
					</tr>
					<tr>
						<td><label>Tipo de Cobranza:</label></td>
						<td><input type='text' name='tipoCobranza-p' id='tipoCobranza-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="30" style=" width : 100px;" tabindex="3"/></td>
					</tr>
					<tr>
						<td><label>Moneda:</label></td>
						<td><input type='text' name='moneda-p' id='moneda-p' class='text ui-widget-content ui-corner-all' readonly='readonly' size="10" style=" width : 50px;" tabindex="4"/></td>
					</tr>
					<tr>
						<td><label>Costo:</label></td>
						<td><input type='text' name='costo-p' id='costo-p' class='text ui-widget-content ui-corner-all'  readonly='readonly' size="10" style=" width : 50px;" disabled="disabled" tabindex="5"/></td>
					</tr>
					<tr>
						<td><label>Cantidad:</label></td>
						<td><input type='text' name='cantidad-p' id='cantidad-p' class='texto' size="10" onkeypress="calcularTotal();" onkeyup="calcularTotal();" style=" width : 50px;" tabindex="6"/></td>
					</tr>
					<tr>
						<td><label>Total:</label></td>
						<td><input type='text' name='total-p' id='total-p' class='text ui-widget-content ui-corner-all' size="10" readonly='readonly' style=" width : 50px;" tabindex="7"/></td>
					</tr>
					<tr>
						<td><label>A Cuenta:</label></td>
						<td><input type='text' name='acuenta-p' id='acuenta-p' class='texto' size="10" tabindex="8"/></td>
					</tr>
					<tr>
						<td><label>Especificar:</label></td>
						<td><input type='text' name='especif-p' id='especif-p' class='texto' size="20" style=" width : 200px;" tabindex="9"/></td>
					</tr>
				</table>
				<br>
				<button id="btn-aceptar-item" tabindex="10">Agregar</button>
				<button id="btn-cancelar" tabindex="11">Cancelar</button>
			</fieldset>
		</div>
		<div id="buscarsocio-form" title="Buscar Socio">		
			Ingrese Nombre Socio:<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" tabindex="15"/>
			Ingrese Numero Puesto:<input type="text" name="numeropuesto" id="numeropuesto" style="width: 100px" tabindex="16"/>
			<br>
			<button id="btn-buscar-socio" tabindex="17">Buscar Socio</button>
			<div id="tablesocios"></div>
		</div>
		
		<div id="selectImp-form" title="Ingrese el número del documento a imprimir...">
		<div align="center">
			<table>
				<tr>
					<td>Nro. Documento Real:</td>
					<td><input type="text" name="txtNroDoc" id="txtNroDoc" size="250px"></td>
				</tr>
			</table>
		</div>
	</div>
		</html:form>
</body>
</html:html>