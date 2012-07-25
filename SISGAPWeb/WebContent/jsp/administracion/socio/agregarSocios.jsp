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


	$("#fechaconstitucion").datepicker({  
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


function grabar() {
	var frm = document.agragarClientes;
	frm.metodo.value = 'grabar';
	frm.submit();

}
function lstCancelar() {
	var frm = document.agragarClientes;
	frm.metodo.value = 'cargarAction';
	frm.submit();

}
</script>
</head>
<body>

	<html:form action="/gestionarSocios.do" method="post" styleId="agragarClientes">
		<input type="hidden" name="metodo" value="grabar"/>
		<input type="hidden" name="fecconstitucion" value="${fechaconstitucion}" />

		<table border="0" width="885" class="tahoma11" cellpadding="3"
			cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Administración /
					Gestionar Socios</td>
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
				<table>
					<tr>
						<td width="200" align="right">Código</td>
						<td><input type="text"  size="20" name="txtCodigo" disabled="disabled" tabindex="1"/></td>
					</tr>
					<tr>
						<td width="200" align="right">Razón Social (Apellidos y Nombres)</td>
						<td><input type="text" style="text-transform: uppercase;" size="50" name="txtRazonSocial" tabindex="2"/></td>
					</tr>
					<tr>
						<td width="200" align="right">Fecha Constitución</td>
						<td><input type='text' name='fechaconstitucion' id='fechaconstitucion' class='text ui-widget-content ui-corner-all' size="20" value="${fechaconstitucion}" style=" width : 80px;" tabindex="3"/>(mes/dia/año)</td>
					</tr>
					<tr>
						<td width="200" align="right">Actividad</td>
						<td>
							<select id="selActividadSocio" name="cbActividadSocio" style="width: 200px" tabindex="4">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstActividadSocio}" var="row">
									<option value="${row.actiTranIde}">${row.actiTranNombre}</option>										
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Tipo</td>
						<td>
							<select id="selTipoSocio" name="cbTipoSocio" style="width: 100px" tabindex="5">
								<option value="0" selected>Seleccione</option>
								<c:forEach items="${lstTipoSocios}" var="row">
									<option value="${row.tipoTranIde}">${row.tipoTranNombre}</option>										
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Sector</td>
						<td><input type="text" style="text-transform: uppercase;" size="2" name="txtSector" tabindex="6"/></td>
					</tr>
					<tr>
						<td align="right">Puesto</td>
						<td><input type="text" size="4" name="txtPuesto" tabindex="7"/></td>
					</tr>
					<tr>
						<td align="right">Estado</td>
						<td>
							<select id="selEstado" name="cbestado" style="width: 100px" tabindex="8">
								<option value="" selected>Seleccione</option>
								<option value="1">Activo</option>
								<option value="0">Inactivo</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center"><input type="button"	id="btn-Grabar" value="Grabar" onclick="grabar()" /></td>
						<td align="center"><input type="button"	id="btn-Cancelar" value="Cancelar" onclick="lstCancelar()" tabindex="9"/></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>        
	</html:form>

</body>
</html:html>