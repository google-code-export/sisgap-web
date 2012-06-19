<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="pe.com.mmh.sisgap.system.propiedades.PropiedadesSistema"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>
<%@page import="pe.com.mmh.sisgap.system.domain.Parametro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">

$(function() {

	$("#btn-Grabar").button();
	$("#btn-Cancelar").button();


	$("#fechacaducidad").datepicker({  
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

	$.ajaxSetup({cache: false}); 

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

	/*$("#btn-Grabar").click(function() {
		var concepto = $('[name=txtconcepto]').val().trim();
		var tipoitem = $('[name=cbtipoitem]').val();
		if(concepto=="") {
			alert("Debe ingresar el Concepto del Item");
			$('[name=txtconcepto]').focus();
			return false;
		}
		alert(tipoitem);

		return false;
	});*/
	
});


function grabar() {
	var frm = document.agragarItem;
	//Validando ingreso
	if (document.getElementById("txtconcepto").value.trim()=="") {
		alert("Debe ingresar el Concepto del Item");
		document.getElementById("txtconcepto").focus();
		return false;
	}
	if (((document.getElementById("cbTipoItem")).selectedIndex)==0) {
		alert("Debe seleccionar un Tipo de Item");
		document.getElementById("cbTipoItem").focus();
		return false;		
	}
	if (document.getElementById("txtcosto").value.trim()=="") {
		alert("Debe ingresar el costo del Item");
		document.getElementById("txtcosto").focus();
		return false;		
	}
	if (((document.getElementById("cbmoneda")).selectedIndex)==0) {
		alert("Debe seleccionar el Tipo de Moneda");
		document.getElementById("cbmoneda").focus();
		return false;		
	}
	if (((document.getElementById("cbmedida")).selectedIndex)==0) {
		alert("Debe seleccionar el Tipo de Medida");
		document.getElementById("cbmedida").focus();
		return false;		
	}
	if (((document.getElementById("cbtipocobranza")).selectedIndex)==0) {
		alert("Debe seleccionar el Tipo de Cobranza");
		document.getElementById("cbtipocobranza").focus();
		return false;		
	}
	//fechacaducidad no es obligatorio
	//txtcobroadicional no es obligatorio
	if (((document.getElementById("cbcostovariable")).selectedIndex)==0) {
		alert("Debe seleccionar el Tipo de Costo");
		document.getElementById("cbcostovariable").focus();
		return false;		
	}
	if (((document.getElementById("cbcobrosocio")).selectedIndex)==0) {
		alert("Debe seleccionar el Tipo de Cobro");
		document.getElementById("cbcobrosocio").focus();
		return false;		
	}	
	if (((document.getElementById("cbestado")).selectedIndex)==0) {
		alert("Debe seleccionar el Estado");
		document.getElementById("cbestado").focus();
		return false;		
	}

	
	frm.metodo.value = 'grabar';
	frm.submit();

}

function lstCancelar() {
	var frm = document.agragarItem;
	frm.metodo.value = 'cargarAction';
	frm.submit();

}

function isNumber(obj){
	var s=obj.value;
	flag=true;
	if(isEmpty(s))
		if(isNumber.arguments.length==1)
			return defaultEmptyOK;
		else
			return (isNumber.arguments[1]==true);

	for(var i=0;i<s.length;i++){
		var c=s.charAt(i);
		if(!isDigit(c)){
			if(c!="."){
				alert("Sólo se admiten dígitos");
				obj.value="";
				obj.focus();
				flag=false;
				return false;			
			}
			return false;
		}
	}
	if(flag){
		if(obj.value<1){
			alert("Se debe ingresar un monto mayor a cero");
			obj.value="";
			return false;
		}
	}

	return true;
}
</script>
</head>
<body>

	<html:form action="/gestionarItemCobranza.do" method="post" styleId="agragarItem" focus="document.forms[0].txtconcepto.focus();">
		<input type="hidden" name="metodo" value="grabar"/>

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
					Item de Cobranza</td>
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
		<logic:notEmpty name="error">
			<table align="center">
				<tr>

					<td id="error" align="center" valign="middle" class="mensajeError">
						${error}</td>
				</tr>
			</table>
		</logic:notEmpty>

		<logic:notEmpty name="mensaje">
			<table align="center" id="tabalMensaje">
				<tr>
					<td id="mensaje" align="center" valign="middle"
						class="mensajeExito">${mensaje}</td>
				</tr>
			</table>
		</logic:notEmpty>

		<fieldset>
			<legend>
				<span class="titulo">Nuevo Registro
				<table border="0" cellpadding="2" cellspacing="2" width="350px">
					<tr>
						<td width="200" align="right">Concepto</td>
						<td><input type="text"  size="50" id="txtconcepto" name="txtconcepto" style="text-transform: uppercase;" tabindex="1" /><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right">Concepto Padre</td>
						<td>
							<select id="cbConceptPadre" name="cbConceptPadre" style="width: 300px" tabindex="2">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstCob}" var="row">
									<option value="${row.codItemcobranza}">${row.strDescripcion}</option>										
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Tipo de Item</td>
						<td>
							<select id="cbTipoItem" name="cbTipoItem" style="width: 100px" tabindex="3">
								<option value="" selected>Seleccione</option>
								<option value="I">Ingresos</option>
								<option value="E">Egresos</option>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">Recibo de Luz</td>
						<td>
							<select id="cbReciboLuz" name="cbReciboLuz" style="width: 150px" tabindex="4">
								<option value="0" selected="selected">Seleccione</option>
								<c:forEach items="${lstOrg}" var="reg">
									<option value="${reg.codOrgreciboLuz}">${reg.fecPeriodo}</option>										
								</c:forEach>
							</select><br><font color="green">(-) Solo para crear el item de cobranza de los Recibos de Luz</font>
						</td>
					</tr>
					<tr>
						<td align="right">Costo</td>
						<td><input type="text" size="10" id="txtcosto" name="txtcosto" onchange="isNumber(this)" tabindex="5"/><font color="red"> * </font></td>
					</tr>
					<tr>
						<td align="right">Moneda</td>
						<td>
							<select id="cbmoneda" name="cbmoneda" style="width: 100px" tabindex="6">
								<option value="" selected>Seleccione</option>
								<option value="S">Soles</option>
								<option value="D">Dolares</option>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td align="right">U/M</td>
						<td>
							<select id="cbmedida" name="cbmedida" style="width: 100px" tabindex="7">
								<option value="" selected>Seleccione</option>
								<c:forEach items="${lstMedidas}" var="row">
									<option value="${row.codUnimedida}">${row.strNombre}</option>										
								</c:forEach>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td align="right">Tipo de Cobranza</td>
						<td>
							<select id="cbtipocobranza" name="cbtipocobranza" style="width: 100px" tabindex="8">
								<option value="" selected>Seleccione</option>
								<option value="C">Contable</option>
								<option value="N">No Contable</option>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td align="right">Fecha Caducidad</td>
						<td><input type="text" size="50" id="fechacaducidad" name="fechacaducidad" style="width: 70px;" tabindex="9"/></td>
					</tr>
					<tr>
						<td align="right">Cobro Adicional</td>
						<td><input type="text"  size="50" id="txtcobroadicional" name="txtcobroadicional" onchange="isNumber(this)" style="width: 50px;" tabindex="10"/></td>
					</tr>
					<tr>
						<td align="right">Costo Variable</td>
						<td>
							<select id="cbcostovariable" name="cbcostovariable" style="width: 100px" tabindex="11">
								<option value="" selected>Seleccione</option>
								<option value="S">Si</option>
								<option value="N">No</option>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td align="right">Cobro Socio</td>
						<td>
							<select id="cbcobrosocio" name="cbcobrosocio" style="width: 100px" tabindex="12">
								<option value="" selected>Seleccione</option>
								<option value="G">General</option>
								<option value="I">Individual</option>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td align="right">Estado</td>
						<td>
							<select id="cbestado" name="cbestado" style="width: 100px" tabindex="13">
								<option value="" selected>Seleccione</option>
								<option value="1">Activo</option>
								<option value="0">Inactivo</option>
							</select><font color="red"> * </font>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right"><font color="red">Los campos con (*) son obligatorios</font></td>
					</tr>
					<tr>
						<td align="center"><input type="button"	id="btn-Grabar" value="Grabar" onclick="grabar()" /></td>
						<td align="center"><input type="button"	id="btn-Cancelar" value="Cancelar" onclick="lstCancelar()" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>        
	</html:form>
</body>
</html:html>