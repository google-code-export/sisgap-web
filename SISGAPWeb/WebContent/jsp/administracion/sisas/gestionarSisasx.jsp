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

		$("#nuevo-ls").button();
		$("#salir-ls").button();
		$("#pdf-ls").button();
		$("#imprimir-ls").button();
		$("#buscarR-ls").button();
		$("#buscar-ls").button();
		$("#btngrabar").button();
		$("#btncerrar").button();
	
		$('#imprimir-ls').click(function() {      
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=LISTADO_SISAS', 'Popup', caracteristicas);  
	        return false;
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


	$(function() {

		$("#btn-aceptar-item").button();
		$("#btn-cancelar").button();
		$("#registra-f").button();
		$("#regresar-f").button();
		$("#imprimir-f").button();
		$("#cancelar-f").button();


	    $('.date-picker').datepicker( {
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: false,
	        dateFormat: 'MM yy',
            monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo',
 	                    'Junio', 'Julio', 'Agosto', 'Septiembre',
 	                    'Octubre', 'Noviembre', 'Diciembre'],
            monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr',
      	                    'May', 'Jun', 'Jul', 'Ago',
      	                    'Sep', 'Oct', 'Nov', 'Dic'],
	        onClose: function(dateText, inst) { 
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	            $(this).datepicker('setDate', new Date(year, month, 1));
	        }
	    });
		
		
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
			var nroDocu =  $('[name=numerodocumento]').val();    
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=REPORTE_DOCUMENTO_DETALLE&nrodoc=${numerodocumento}', 'Popup', caracteristicas);  
	        nueva=window.open('ReportsServlet?reporte=REPORTE_DOCUMENTO_DETALLE&nroDoc='+ nroDocu , 'Popup', caracteristicas);  
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


		$("#calendar-form").dialog({
			autoOpen : false,
			height : 400,
			width : 300,
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
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_SOCIO&nombre="+nombre,
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

	function buscarCalendar(){
		var frm = document.gestionarFacturacion;
		frm.metodo.value = 'findGenerator';
		frm.submit();
	}

	function ver(xperiodo,xcodigo){
		$("#codigoide-f").val(xcodigo);
		$("#periodo-f").val(xperiodo);

		$.ajax({
	        type: "POST",
	        url: "/SISGAPWeb/registrosisas.do",
	        data: "metodo=findGenerator&periodo="+xperiodo+"&codigoide="+xcodigo+"&ajax=true",
	        success: function(datos){					        
	        	$("#dataCalendar").html(datos);
	      }
		});	
		
		$("#calendar-form").dialog("open");
	}

	function updateSisa(){
		
		var frm = document.gestionarFacturacion;
		frm.metodo.value = 'updateSisa';
		$("#valuess").val("");
		$("#valuess").val($("#calendar-form input[name=fechadia]:checked").map(
			     function () {return this.value;}).get().join(","));	
	    if($("#valuess").val()==''){
	    	$("#valuess").val('-1');
		}	
		frm.submit();
		
	}
	
</script>
<style type="text/css">
.ui-datepicker-calendar {
    display: none;
    }
</style>
</head>
<body>
<html:form action="/registrosisas.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoide" id="codigoide-f"/>
		<input type="hidden" name="periodo" id="periodo-f" />
		<input type="hidden" name="valuess" id="valuess" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos/Sisas</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Sisas
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
						<td align="center"><input type="button" id="buscarR-ls" name="buscarR-ls" value="Busqueda Rápida" onclick="lstBuscar()" /></td>
						<td align="center"><input type="button" id="pdf-ls" name="pdf-ls" value="PDF" onclick="lstPdf()" /></td>
						<td align="center"><input type="button" id="imprimir-ls" name="imprimir-ls" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="center"><input type="button" id="nuevo-ls" name="nuevo-ls" value="Nuevo" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>
		<table align="center">
			<tr>
				<td id="mensaje" align="center" valign="middle"	style="display: none"></td>
				<td id="error" align="center" valign="middle" class="mensajeError"	style="display: none"></td>
			</tr>
		</table>

		<fieldset>
			<legend>Nuevo registro</legend>		
			<table border="0" cellpadding="0" cellspacing="0" width="75%">
				<tr>
					<td>Socio</td>
					<td colspan="3" ><input type='text' name="socio-f" id="socio-f" size=30 class="text ui-widget-content ui-corner-all" readonly="readonly"/></td>
					<td width="15px">
								<button id="buscar-socio">...</button>
					</td>
					<td width="20px">Código</td>
					<td><input type="text" name="codigo-f" id="codigo-f" size="10" class="text ui-widget-content ui-corner-all" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>Puesto</td>
					<td colspan="3"><input type="text" name="direccion-f" id="direccion-f" size="10"  class="text ui-widget-content ui-corner-all" style=" width : 158px;" readonly="readonly"/></td>
					<td width="15px"></td>
					<td width="80px">Periodo</td>
					<td>
					<input name="startDate" id="startDate" class="date-picker" value="${fecha}"/>(mes/año)					
					</td>
				</tr>
			</table>
			<button type="button" id="buscar-ls" name="buscar-ls" onclick="buscarCalendar();">Buscar</button> 
		</fieldset>
			
		<display:table name="lstSisa" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/registrosisas.do?metodo=cargarAction"		
						id="row"
						export="false">
				<display:column title="" style="width:60px;">
					<img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16" onclick="ver('<fmt:formatDate pattern="dd-MM-yyyy" value="${row.perido}" />',${row.codigo});"/>
				</display:column>
				<display:column title="Puesto" property="puesto" sortable="true"/>
				<display:column title="Periodo" property="perido" sortable="true"/>
				<display:column title="Nombre" property="nombre" sortable="true"/>
				<display:column title="Total Dias" sortable="true"><center>${row.totaldias}</center></display:column>
				<display:column title="Total Pagos" sortable="true"><center>${row.totalpagos}</center></display:column>
				<display:column title="Estado" style="width:150px;">
					<c:choose>
						<c:when test="${row.totaldias==row.totalpagos}">
							<div style="background-color: #33CC33;width: 150px;">&nbsp;</div>
						</c:when>
						<c:when test="${row.totalpagos>0 && row.totaldias!=row.totalpagos}">
							<div style="background-color: orange;width: 150px;">&nbsp;</div>
						</c:when>
						<c:when test="${row.totalpagos==0}">
							<div style="background-color: red;width: 150px;">&nbsp;</div>
						</c:when>
					</c:choose>
				</display:column>			
		</display:table>	
		
		<div id="calendar-form" title="Calendario">		
			<div id='dataCalendar'></div>
			<button type="button"  onclick="updateSisa();">Grabar</button>
		</div>
		<div id="buscarsocio-form" title="Buscar Socio">		
			<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" /> 
			<button id="btn-buscar-socio">Buscar Socio</button>
			<div id="tablesocios"></div>
		</div>
		</html:form>
</body>
</html:html>