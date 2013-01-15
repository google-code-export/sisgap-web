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
			$('#mostrarVigilancia').submit();
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
<html:form action="/registrovigilancia.do" styleId="mostrarVigilancia">
		<input type="hidden" name="metodo" />
		
		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Listado de Vigilancia por Asociado</td>
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
			<tr><button id="btnRegresar" name="btnRegresar">Regresar</button></tr>
		</table>
		<br/>
		<display:table name="lstVigilancia" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/mostrarvigilancia.do?metodo=cargarAction"		
						id="row"
						defaultsort="1"
						decorator="org.displaytag.decorator.TotalTableDecorator"
						export="false">
			<display:column title="Código" property="codigo" sortable="false" style="width:50px"/>
			<display:column title="Puesto" property="puesto" sortable="false" style="width:50px"/>
			<display:column title="Nombre" property="nombre" sortable="false"/>
			<display:column title="Giro Comercial" property="girocomer" sortable="false"/>
			<display:column title="Sector" property="sector" sortable="false" style="width:50px"/>
			<display:column title="Periodo" property="perido" sortable="false"/>			
			<display:column title="Dias" property="totaldias" sortable="false" />
			<display:column title="Nro. Recibo" property="numrecibo" sortable="false" style="width:60px"/>
			<display:column title="Valor" style="width:50px;" sortable="false">
				<c:if test="${row.valor==0.0}">
					<font color="red" ><b><c:out value="${row.valor}"/></b></font>
				</c:if>
				<c:if test="${row.valor>0.0}">
					<font color="green" ><b><c:out value="${row.valor}"/></b></font>
				</c:if>
			</display:column>
			<display:column title="Fecha Ingreso" property="fechaIngreso" sortable="false" style="width:100px">
			</display:column>
			<display:column title="Deuda" style="width:50px;" sortable="false">
				<c:if test="${row.deuda==0.0}">
					<font color="red" ><b><c:out value="${row.deuda}"/></b></font>
				</c:if>
				<c:if test="${row.deuda>0.0}">
					<font color="green" ><b><c:out value="${row.deuda}"/></b></font>
				</c:if>
			</display:column>
		</display:table>	
		<table><tr><td>Total : <c:out value="totalValor" /></td></tr></table>
		</html:form>
</body>
</html:html>