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
		$("#imprime-ls").button();
		$("#btn-buscar-periodo").button();
		$("#btn_Grabar").button();
	
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

		$("#imprime-form").dialog({
			autoOpen : false,
			height : 200,
			width : 250,
			modal : true,
			buttons : {
				Ok : function() {
					var cadena1 = $("#startDateBusca").val();
					var cadena2 = $("#codigoide-f").val();
					//alert(cadena1+"\n"+cadena2);
					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});
		
		$("#calendar-form").dialog({
			autoOpen : false,
			height : 500,
			width : 400,
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



		$("#selectdate-form").dialog({
			autoOpen : false,
			height : 130,
			width : 350,
			modal : true,
			buttons : {
				Generar : function() {
					var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
				    nueva=window.open('ReportsServlet?reporte=REPORTE_SISAS&periodo='+$("#startDate2").val()+'&codigo=0&provider=other', 'Popup', caracteristicas); 
				    $(this).dialog("close");
				    return false;
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
		});	

	    $('.date-picker2').datepicker( {
	        changeMonth: true,
	        changeYear: true,
	        showButtonPanel: false,
	        dateFormat: 'mm/yy',
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


		$("#fechaingreso").datepicker(
	            {
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

		$("#imprime-ls").button().click(function() {
			$("#selectdate-form").dialog("open");
			return false;
		});
		
		$("#btn-buscar-socio").button().click(function() {
			var nombre =  $('[name=nombresocio]').val();
			var nropto =  $('[name=numeropuesto]').val();
				$.ajax({
			        type: "POST",
			        url: "/SISGAPWeb/AjaxServlet",
			        data: "action=BUSCAR_SOCIO&nombre="+nombre+"&opcion=vigilancia&nropto="+nropto,
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

		$( function() {
			$( '.checkAll' ).live( 'change', function() {
				$( '.cb-element' ).attr( 'checked', $( this ).is( ':checked' ) ? false : true );
				//$( this ).next().text( $( this ).is( ':checked' ) ? 'Uncheck All' : 'Check All' );
			});
			$( '.invertSelection' ).live( 'click', function() {
				$( '.cb-element' ).each( function() {
					$( this ).attr( 'checked', $( this ).is( ':checked' ) ? '' : 'checked' );
				}).trigger( 'change' );

			});
			$( '.cb-element' ).live( 'change', function() {
				//$( '.cb-element' ).length == $( '.cb-element:checked' ).length ? $( '.checkAll' ).attr( 'checked', 'checked' ).next().text( 'Uncheck All' ) : $( '.checkAll' ).attr( 'checked', '' ).next().text( 'Check All' );

			});
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
		//alert("Codigo: "+codigo+"\nRazon Social: "+razonSocial+"\nPuesto: "+puesto+"\nCodigoIDE: "+codigoIde);
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
		if($("#socio-f").val() == ""){
			alert("Ingrese los datos del Socio");
			$("#socio-f").focus();
			return false;
		}
		
		if($("#startDate").val() == ""){
			alert("Ingrese el Periodo a generar para el Socio.");
			$('#startDate').focus();
			return false;
		}
		var frm = document.gestionarFacturacion;
		frm.metodo.value = 'findGenerator';
		frm.submit();
	}

	function ver(xperiodo,xcodigo){
		//alert("periodo: "+xperiodo+"\ncodigo: "+xcodigo);
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

		//alert("Antes del calendario...");
		$("#calendar-form").dialog("open");
		//alert("Despues del calendario...");
	}

	function verReporte(xperiodo,xcodigo){
		xperiodo = fentrada(xperiodo);
		$("#codigoide-f").val(xcodigo);
		$("#periodo-f").val(xperiodo);

		var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	    nueva=window.open('ReportsServlet?reporte=REPORTE_DIA_SISAS&periodo='+xperiodo+'&codigo='+xcodigo, 'Popup', caracteristicas);
	} 

	//Donde fentrada es: 
	function fentrada(cambio){ 
	    var uno=cambio.substring(0, 2); 
	    var dos=cambio.substring(3, 5); 
	    var tres=cambio.substring(6, 10); 
	    var resul = (uno+"/"+dos+"/"+tres); 
	    return resul; 
	}  

	
	function updateSisa(){
		$("#fecingreso").val($("#fechaingreso").val());
		$("#recnumero").val($("#recibonumero").val());
		/*alert(document.gestionarFacturacion.fecingreso.value);
		alert(document.gestionarFacturacion.recnumero.value);*/
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

	function OpenWindows(){
		$("#selectdate-form").dialog("open"); 
	}

	function eliminar(xcodigo){
		rpta = confirmar("¿Esta seguro que desea eliminar este registro?");
		if (rpta){
			var frm = document.gestionarFacturacion;
			frm.codigoSisa.value = xcodigo;
			frm.metodo.value = 'eliminar';
			frm.submit();
		}
	}

	function confirmar( mensaje ){
		return confirm( mensaje );
	}
	
</script>
<style type="text/css">
.ui-datepicker-calendar {
    display: block;
    }
</style>
</head>
<body>
<html:form action="/registrosisas.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="codigoSisa" id="codigoSisa"/>
		<input type="hidden" name="valuess" id="valuess" />
	    <input type="hidden" name="fecingreso" id="fecingreso" />
	    <input type="hidden" name="recnumero" id="recnumero" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos/Vigilancia</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Vigilancia
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
		<br/>
		<fieldset>
			<legend><b>Nuevo registro</b></legend>		
			<table border="0" cellpadding="0" cellspacing="0" width="75%">
				<tr>
					<td>Socio</td>
					<td><input type='text' name="socio-f" id="socio-f" size=30 class="text ui-widget-content ui-corner-all" style="width: 300px;" readonly="readonly"/></td>
					<td width="15px">
								<button id="buscar-socio">...</button>
					</td>
					<td width="20px">Código</td>
					<td><input type="text" name="codigo-f" id="codigo-f" size="10" class="text ui-widget-content ui-corner-all" style="width: 100px;" readonly="readonly" /></td>
					<td width="20px">Ide</td>
					<td><input type="text" name="codigoide-f" id="codigoide-f" size="10" class="text ui-widget-content ui-corner-all" style="width: 100px;" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>Puesto</td>
					<td><input type="text" name="direccion-f" id="direccion-f" size="10"  class="text ui-widget-content ui-corner-all" style="width: 100px;" readonly="readonly"/></td>
					<td>&nbsp;</td>
					<td width="80px">Periodo</td>
					<td><input name="startDate" id="startDate" class="date-picker" value="${fecha}"/>(mes/año)</td>
				</tr>
			</table>
			<button type="button" id="buscar-ls" name="buscar-ls" onclick="buscarCalendar();">Generar</button>
			<button type="button" id="imprime-ls" name="imprime-ls">Imprimir Reporte</button> 
		</fieldset>
			
		<display:table name="lstSisa" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/registrosisas.do?metodo=cargarAction"		
						id="row"
						export="false">
				<display:column title="Acción" style="width:60px;">
					<c:choose>
						<c:when test="${row.estado==1}">
							<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Anular..." border="0" width="16" height="16" id="" onclick="eliminar('${row.codigo}');"/>
							<img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16" onclick="ver('<fmt:formatDate pattern="dd-MM-yyyy" value="${row.perido}" />','${row.tranCodigo}');"/>
							<img src="<%=request.getContextPath()%>/imagenes/manto/reporte.png" alt="Reporte Detallado..." border="0" width="16" height="16" onclick="verReporte('<fmt:formatDate pattern="dd-MM-yyyy" value="${row.perido}" />','${row.tranCodigo}');"/>
						</c:when>
					</c:choose>
				</display:column>
				<display:column title="Puesto" property="puesto" sortable="true"/>
				<display:column title="Periodo" property="perido" sortable="true"/>
				<display:column title="Nombre" property="nombre" sortable="true"/>
				<display:column title="Codigo" property="tranCodigo" sortable="true"/>
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
		<div id="calendar-form" title="Calendario" style="border: 1px;">
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td width="30%" align="right"><b>Fecha de Ingreso:</b></td>
					<td width="70%"><input type='text' name='fechaingreso' id='fechaingreso' size="20" style=" width : 80px;" />(dia/mes/año)</td>
				</tr>
				<tr>
					<td align="right"><b>Recibo Nro:</b></td>
					<td><input type='text' name='recibonumero' id='recibonumero' size="20" style=" width : 80px;" /></td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center" class="controls">
						<span><a href="javascript:void(0);" class="invertSelection"></a></span>
						<span><b><font color="blue">Seleccionar Todos</font></b><input type="checkbox" class="checkAll" /></span>				
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="dataCalendar" style="border: 1px; border-color: green;"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" id="btn_Grabar" name="btn_Grabar" onclick="updateSisa();">Actualizar los días seleccionados...</button>
					</td>
				</tr>
			</table>
		</div>
		<div id="buscarsocio-form" title="Buscar Socio">		
			Ingrese Nombre Socio:<input type="text" name="nombresocio" id="nombresocio" class="text ui-widget-content ui-corner-all" />
			Ingrese Numero Puesto:<input type="text" name="numeropuesto" id="numeropuesto" style="width: 100px" />
			<br>
			<button id="btn-buscar-socio">Buscar Socio</button>
			<div id="tablesocios"></div>
		</div>
		<div id="selectdate-form" title="Seleccionar periodo a buscar...">		 
			<input name="startDate2" id="startDate2" class="date-picker2" />(mes/año)
		</div>	
		</html:form>
</body>
</html:html>