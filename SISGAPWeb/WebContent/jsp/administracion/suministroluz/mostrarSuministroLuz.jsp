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

	$(function() {

		$("#btnRegresar").button();

		$("#btnRegresar").button().click(function() {
			$('[name=metodo]').val('cargarAction');
			$('#mostrarSuministroLuz').submit();
		});

	});
</script>
<style type="text/css">
.ui-datepicker-calendar {
    display: block;
    }
</style>
</head>
<body>
<html:form action="/suministroLuz.do" styleId="mostrarSuministroLuz">
		<input type="hidden" name="metodo" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Listado de Recibo de Luz por Asociado por Periodo</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Asociado(a):
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
		<display:table name="lstSuministroAsociado" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/suministroLuz.do?metodo=verReporteSuministroLuz"		
						id="row"
						defaultsort="1"
						decorator="org.displaytag.decorator.TotalTableDecorator"
						export="false">
			<display:column title="Periodo" property="periodo" sortable="true"/>
			<display:column title="Código Recibo Original" property="codigoRecibo" sortable="true" style="width:150px"/>
			<display:column title="Recibo Original" property="total" sortable="true" style="width:100px"/>
			<display:column title="Código Asociado" property="codigoSocio" sortable="true"/>
			<display:column title="Recibo Asociado" property="totalReciboOrg" sortable="true"/>						
			<display:column title="Estado" property="estado" sortable="true" />
		</display:table>	
		<table><tr><td>Total : <c:out value="totalValor" /></td></tr></table>
		</html:form>
</body>
</html:html>