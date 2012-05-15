<!DOCTYPE html>

<%@include file="../../../taglibs.jsp"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	.texto {
		background-color:rgb(189,252,152);
		color:rgb(0,0,0);
	}
</style>

<script type="text/javascript">

$(function() {

		$("#nuevo-sl").button();
		$("#salir-sl").button();
		$("#pdf-sl").button();
		$("#imprimir-sl").button();
		$("#buscar-sl").button();
		
		$("#btngrabar").button();
		$("#btncerrar").button();
	
		$('#imprimir-sl').click(function() {      
			var caracteristicas = "height=500,width=800,scrollTo,resizable=1,scrollbars=1,location=0";  
	        nueva=window.open('ReportsServlet?reporte=LISTADO_RECIBO_LUZ', 'Popup', caracteristicas);  
	        return false;
		});

	
		$("#periodo").datepicker(
	            {   
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
             
		$("#fecvencimientox").datepicker(
	            {   
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

		$("#fecemisionx").datepicker(
	            {   
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
		
		$("#grabar-form").dialog({
					autoOpen : false,
					height : 380,
					width : 850,
					modal : true,		
					close : function() {
						allFields.val("").removeClass("ui-state-error");
					}
			});

		
		$('#nuevo-sl').click(function() { 

			$("#grabar-form").dialog("open");
     

		});

		$("#eliminar-form").dialog({
			autoOpen : false,
			height : 0,
			width : 300,
			modal : true,
			buttons : {
				Eliminar : function() {
					var frm = document.formFacturacion;								
					frm.submit();
				},
				Cancel : function() {
					$(this).dialog("close");
				}},
			close : function() {
				allFields.val("").removeClass("ui-state-error");
			}
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
	
	
	function lstNuevo() {

	}

	function mostrarItems(cod) {
		var frm = document.formFacturacion;
		frm.codigoModi.value = cod;//$("#codigoModi").val(cod);
		frm.metodo.value = 'mostrarItemsSuministro';
		frm.submit();
	}
	
	function eliminar(cod) {
		var frm = document.formFacturacion;
		$("#codigoModi").val(cod);
		frm.metodo.value = 'eliminarReciboLuz';
		$("#eliminar-form").dialog("open");
		//$("#anulardoc-form").dialog("open");
	}

	function ver(cod,lecturaIni,lecturaFin,monto,costoWats,periodo,estado,
			vrepomancnx,
			vcargofijo,
			valumpublic,
			vsubtotalmes,
			vigv,
			vtotalmesact,
			vaporteley,
			vcuotaconv,
			vredonmesact,
			vredonmesant,
			vinteresconvenio,
			venergactfraptaactual,
			venergactfraptaanteri,
			venergactfraptadifer,
			venergactfraptafactor,
			venergactfraptaconsu,
			venergactfraptaconfa,
			venergactfraptapreuni,
			venergactfraptatotal,
			venergacthorptaactu,
			venergacthorptaant,
			venergacthorptadif,
			venergacthorptafac,
			venergacthorptacons,
			venergacthorptaconfac,
			venergacthorptapreuni,
			venergacthorptatotal,
			venergreacinicial,
			venergreacanteri,
			venergreacdifere,
			venergreacfactor,
			venergreacconsu,
			venergreacfaccons,
			venergreacpreuni,
			venergreactotal,
			vpotenciafpini,
			vpotenciafpante,
			vpotenciafpdif,
			vpotenciafpfac,
			vpotenciafpcons,
			vpotenciahpact,
			vpotenciahpant,
			vpotenciahpdif,
			vpotenciahpfac,
			vpotenciahpcons,
			vpotusoreddistconfac,
			vpotusoreddistpreuni,
			vpotusoreddisttotal,
			vpotgenfpconfac,
			vpotgenfppreuni,
			vpotgenfptotal,
			vfecvencimiento,
			vfecemision) {

		var frm = document.formFacturacion;
				
		$("#codigoModi").val(cod);	
		$("#lecturaIni").val(lecturaIni);
		$("#lecturaFin").val(lecturaFin);
		$("#monto").val(monto);
		$("#costoWats").val(costoWats);
		$("#periodo").val(periodo);

		$('#repomancnx').val(vrepomancnx);
		$('#cargofijo').val(vcargofijo);
		$('#alumpublic').val(valumpublic);
		$('#subtotalmes').val(vsubtotalmes);
		$('#igv').val(vigv);
		$('#totalmesact').val(vtotalmesact);
		$('#aporteley').val(vaporteley);
		$('#cuotaconv').val(vcuotaconv);
		$('#redonmesact').val(vredonmesact);
		$('#redonmesant').val(vredonmesant);
		$('#interesconvenio').val(vinteresconvenio);
		$('#energactfraptaactual').val(venergactfraptaactual);
		$('#energactfraptaanteri').val(venergactfraptaanteri);
		$('#energactfraptadifer').val(venergactfraptadifer);
		$('#energactfraptafactor').val(venergactfraptafactor);
		$('#energactfraptaconsu').val(venergactfraptaconsu);
		$('#energactfraptaconfa').val(venergactfraptaconfa);
		$('#energactfraptapreuni').val(venergactfraptapreuni);
		$('#energactfraptatotal').val(venergactfraptatotal);
		$('#energacthorptaactu').val(venergacthorptaactu);
		$('#energacthorptaant').val(venergacthorptaant);
		$('#energacthorptadif').val(venergacthorptadif);
		$('#energacthorptafac').val(venergacthorptafac);
		$('#energacthorptacons').val(venergacthorptacons);
		$('#energacthorptaconfac').val(venergacthorptaconfac);
		$('#energacthorptapreuni').val(venergacthorptapreuni);
		$('#energacthorptatotal').val(venergacthorptatotal);
		$('#energreacinicial').val(venergreacinicial);
		$('#energreacanteri').val(venergreacanteri);
		$('#energreacdifere').val(venergreacdifere);
		$('#energreacfactor').val(venergreacfactor);
		$('#energreacconsu').val(venergreacconsu);
		$('#energreacfaccons').val(venergreacfaccons);
		$('#energreacpreuni').val(venergreacpreuni);
		$('#energreactotal').val(venergreactotal);
		$('#potenciafpini').val(vpotenciafpini);
		$('#potenciafpante').val(vpotenciafpante);
		$('#potenciafpdif').val(vpotenciafpdif);
		$('#potenciafpfac').val(vpotenciafpfac);
		$('#potenciafpcons').val(vpotenciafpcons);
		$('#potenciahpact').val(vpotenciahpact);
		$('#potenciahpant').val(vpotenciahpant);
		$('#potenciahpdif').val(vpotenciahpdif);
		$('#potenciahpfac').val(vpotenciahpfac);
		$('#potenciahpcons').val(vpotenciahpcons);
		$('#potusoreddistconfac').val(vpotusoreddistconfac);
		$('#potusoreddistpreuni').val(vpotusoreddistpreuni);
		$('#potusoreddisttotal').val(vpotusoreddisttotal);
		$('#potgenfpconfac').val(vpotgenfpconfac);
		$('#potgenfppreuni').val(vpotgenfppreuni);
		$('#potgenfptotal').val(vpotgenfptotal);
		
		$('#fecvencimiento').val(vfecvencimiento);
		$('#fecemision').val(vfecemision);

		
		if(estado==1){
			$('input[name=estado]').attr('checked', true);
		}else{
			$('input[name=estado]').attr('checked', false);
		}
		$("#grabar-form").dialog("open");

	}

	function grabar() {
		var frm = document.formFacturacion;


		$("#lecturaInix").val($("#lecturaIni").val());
		$("#lecturaFinx").val($("#lecturaFin").val());
		$("#montox").val($("#monto").val());
		$("#costoWatsx").val($("#costoWats").val());
		$("#periodox").val($("#periodo").val());

		$('#repomancnxx').val($('#repomancnx').val());
		$('#cargofijox').val($('#cargofijo').val());
		$('#alumpublicx').val($('#alumpublic').val());
		$('#subtotalmesx').val($('#subtotalmes').val());
		$('#igvx').val($('#igv').val());
		$('#totalmesactx').val($('#totalmesact').val());
		$('#aporteleyx').val($('#aporteley').val());
		$('#cuotaconvx').val($('#cuotaconv').val());
		$('#redonmesactx').val($('#redonmesact').val());
		$('#redonmesantx').val($('#redonmesant').val());
		$('#interesconveniox').val($('#interesconvenio').val());
		$('#energactfraptaactualx').val($('#energactfraptaactual').val());
		$('#energactfraptaanterix').val($('#energactfraptaanteri').val());
		$('#energactfraptadiferx').val($('#energactfraptadifer').val());
		$('#energactfraptafactorx').val($('#energactfraptafactor').val());
		$('#energactfraptaconsux').val($('#energactfraptaconsu').val());
		$('#energactfraptaconfax').val($('#energactfraptaconfa').val());
		$('#energactfraptapreunix').val($('#energactfraptapreuni').val());
		$('#energactfraptatotalx').val($('#energactfraptatotal').val());
		$('#energacthorptaactux').val($('#energacthorptaactu').val());
		$('#energacthorptaantx').val($('#energacthorptaant').val());
		$('#energacthorptadifx').val($('#energacthorptadif').val());
		$('#energacthorptafacx').val($('#energacthorptafac').val());
		$('#energacthorptaconsx').val($('#energacthorptacons').val());
		$('#energacthorptaconfacx').val($('#energacthorptaconfac').val());
		$('#energacthorptapreunix').val($('#energacthorptapreuni').val());
		$('#energacthorptatotalx').val($('#energacthorptatotal').val());
		$('#energreacinicialx').val($('#energreacinicial').val());
		$('#energreacanterix').val($('#energreacanteri').val());
		$('#energreacdiferex').val($('#energreacdifere').val());
		$('#energreacfactorx').val($('#energreacfactor').val());
		$('#energreacconsux').val($('#energreacconsu').val());
		$('#energreacfacconsx').val($('#energreacfaccons').val());
		$('#energreacpreunix').val($('#energreacpreuni').val());
		$('#energreactotalx').val($('#energreactotal').val());
		$('#potenciafpinix').val($('#potenciafpini').val());
		$('#potenciafpantex').val($('#potenciafpante').val());
		$('#potenciafpdifx').val($('#potenciafpdif').val());
		$('#potenciafpfacx').val($('#potenciafpfac').val());
		$('#potenciafpconsx').val($('#potenciafpcons').val());
		$('#potenciahpactx').val($('#potenciahpact').val());
		$('#potenciahpantx').val($('#potenciahpant').val());
		$('#potenciahpdifx').val($('#potenciahpdif').val());
		$('#potenciahpfacx').val($('#potenciahpfac').val());
		$('#potenciahpconsx').val($('#potenciahpcons').val());
		$('#potusoreddistconfacx').val($('#potusoreddistconfac').val());
		$('#potusoreddistpreunix').val($('#potusoreddistpreuni').val());
		$('#potusoreddisttotalx').val($('#potusoreddisttotal').val());
		$('#potgenfpconfacx').val($('#potgenfpconfac').val());
		$('#potgenfppreunix').val($('#potgenfppreuni').val());
		$('#potgenfptotalx').val($('#potgenfptotal').val());

		$('#fecvencimiento').val($('#fecvencimientox').val());
		$('#fecemision').val($('#fecemisionx').val());
		

		
		if($('input[name=estado]').is(':checked')){
			$("#estadox").val("1");
		}else{
			$("#estadox").val("0");
		}

		if($("#codigoModi").val()!=''){
			frm.metodo.value = 'actualizarReciboLuz';
		}else{
			frm.metodo.value = 'registrarReciboLuz';
		}
		
		
		frm.submit();
	}

	function cerrarPop() {
		$("#grabar-form").dialog("close");
	}
		
	function setTipoDoc(cod){
		var frm = document.formFacturacion;
		frm.tipodocumento.value = cod;
	}

	/* Por Johan Muñoz
	/* Funciones de validación de campos */
	function validar(){
		var texto = document.getElementById("ImporTotalRepMan");
		var cant = /^([0-9])*$/;
		if(!texto.match(cant)){
			alert("no es numero...");
		}else{
			alert("es numero...");
		}
	}
	
	function soloNumeros(evt){
		alert(evt);
		if(window.event){
			keynum=evt.keycode;
		}else{
			keynum=evt.which;
		}
		alert(keynum);
		if(keynum>47 && keynum<58){
			return true;
		}else{
			return false;
		}
	}
	
	/* Funciones de cálculo para el recibo de luz */
	function calcular_Diferencia(lecAct,lecAnt) {
		alert("Actual="+lecAct+" Anterior="+lecAnt);
		var dif = lecAnt.val()-lecAct.val();
		alert("Diferencia="+dif);
		return dif;
	}


	function calculaCampos() {
		var subTotaldelMes = 0;
		var igvTotaldelMes = 0;
		var TotaldelMes = 0;
		var montoTotal = 0;

		//alert( redondear( parseFloat($('#repomancnx').val()) + parseFloat($('#cargofijo').val()) ) );
		//subTotaldelMes = redondear( parseFloat($('#repomancnx').val()) + parseFloat($('#cargofijo').val()) );
		subTotaldelMes = redondear( parseFloat($('#repomancnx').val()) + parseFloat($('#cargofijo').val()) + parseFloat($('#energactfraptatotal').val()) + parseFloat($('#energacthorptatotal').val()) + parseFloat($('#energreactotal').val()) + parseFloat($('#interesconvenio').val()) + parseFloat($('#potusoreddisttotal').val()) + parseFloat($('#potgenfptotal').val()) + parseFloat($('#alumpublic').val()) ); 
		$("#subtotalmes").val(subTotaldelMes);
		igvTotaldelMes = redondear( ( parseFloat(subTotaldelMes) * 18 ) / 100 );
		$("#igv").val(igvTotaldelMes);
		TotaldelMes = redondear( parseFloat($("#subtotalmes").val()) + parseFloat($("#igv").val()) );
		$("#totalmesact").val(TotaldelMes);

		//Monto Total
		montoTotal = redondear( parseFloat($("#totalmesact").val()) + parseFloat($("#aporteley").val()) + parseFloat($("#cuotaconv").val()) + parseFloat($("#redonmesant").val()) + parseFloat($("#redonmesact").val()) );
		$("#monto").val(montoTotal);
	}
	
</script>
<title>Insert title here</title>
</head>
<body>

	<html:form action="/suministroLuz.do" styleId="formFacturacion">
		<input type="hidden" name="metodo" />
		
		<input type="hidden" name="descripanulada" id="descripanulada"/>
		<input type="hidden" name="tipodocumento" id="tipodocumento" value="R"/>
		
		<input type="hidden" name="codigoModi" id="codigoModi"/>
		<input type="hidden" name="lecturaInix" id="lecturaInix"/>
		<input type="hidden" name="lecturaFinx" id="lecturaFinx"/>
		<input type="hidden" name="montox" id="montox"/>
		<input type="hidden" name="costoWatsx" id="costoWatsx"/>
		<input type="hidden" name="periodox" id="periodox"/>
		<input type="hidden" name="estadox" id="estadox"/>

		<input type='hidden' name='repomancnxx' id='repomancnxx'/>
		<input type='hidden' name='cargofijox' id='cargofijox'/>
		<input type='hidden' name='alumpublicx' id='alumpublicx'/>
		<input type='hidden' name='subtotalmesx' id='subtotalmesx'/>
		<input type='hidden' name='igvx' id='igvx'/>
		<input type='hidden' name='totalmesactx' id='totalmesactx'/>
		<input type='hidden' name='aporteleyx' id='aporteleyx'/>
		<input type='hidden' name='cuotaconvx' id='cuotaconvx'/>
		<input type='hidden' name='redonmesactx' id='redonmesactx'/>
		<input type='hidden' name='redonmesantx' id='redonmesantx'/>
		<input type='hidden' name='interesconveniox' id='interesconveniox'/>
		<input type='hidden' name='energactfraptaactualx' id='energactfraptaactualx'/>
		<input type='hidden' name='energactfraptaanterix' id='energactfraptaanterix'/>
		<input type='hidden' name='energactfraptadiferx' id='energactfraptadiferx'/>
		<input type='hidden' name='energactfraptafactorx' id='energactfraptafactorx'/>
		<input type='hidden' name='energactfraptaconsux' id='energactfraptaconsux'/>
		<input type='hidden' name='energactfraptaconfax' id='energactfraptaconfax'/>
		<input type='hidden' name='energactfraptapreunix' id='energactfraptapreunix'/>
		<input type='hidden' name='energactfraptatotalx' id='energactfraptatotalx'/>
		<input type='hidden' name='energacthorptaactux' id='energacthorptaactux'/>
		<input type='hidden' name='energacthorptaantx' id='energacthorptaantx'/>
		<input type='hidden' name='energacthorptadifx' id='energacthorptadifx'/>
		<input type='hidden' name='energacthorptafacx' id='energacthorptafacx'/>
		<input type='hidden' name='energacthorptaconsx' id='energacthorptaconsx'/>
		<input type='hidden' name='energacthorptaconfacx' id='energacthorptaconfacx'/>
		<input type='hidden' name='energacthorptapreunix' id='energacthorptapreunix'/>
		<input type='hidden' name='energacthorptatotalx' id='energacthorptatotalx'/>
		<input type='hidden' name='energreacinicialx' id='energreacinicialx'/>
		<input type='hidden' name='energreacanterix' id='energreacanterix'/>
		<input type='hidden' name='energreacdiferex' id='energreacdiferex'/>
		<input type='hidden' name='energreacfactorx' id='energreacfactorx'/>
		<input type='hidden' name='energreacconsux' id='energreacconsux'/>
		<input type='hidden' name='energreacfacconsx' id='energreacfacconsx'/>
		<input type='hidden' name='energreacpreunix' id='energreacpreunix'/>
		<input type='hidden' name='energreactotalx' id='energreactotalx'/>
		<input type='hidden' name='potenciafpinix' id='potenciafpinix'/>
		<input type='hidden' name='potenciafpantex' id='potenciafpantex'/>
		<input type='hidden' name='potenciafpdifx' id='potenciafpdifx'/>
		<input type='hidden' name='potenciafpfacx' id='potenciafpfacx'/>
		<input type='hidden' name='potenciafpconsx' id='potenciafpconsx'/>
		<input type='hidden' name='potenciahpactx' id='potenciahpactx'/>
		<input type='hidden' name='potenciahpantx' id='potenciahpantx'/>
		<input type='hidden' name='potenciahpdifx' id='potenciahpdifx'/>
		<input type='hidden' name='potenciahpfacx' id='potenciahpfacx'/>
		<input type='hidden' name='potenciahpconsx' id='potenciahpconsx'/>
		<input type='hidden' name='potusoreddistconfacx' id='potusoreddistconfacx'/>
		<input type='hidden' name='potusoreddistpreunix' id='potusoreddistpreunix'/>
		<input type='hidden' name='potusoreddisttotalx' id='potusoreddisttotalx'/>
		<input type='hidden' name='potgenfpconfacx' id='potgenfpconfacx'/>
		<input type='hidden' name='potgenfppreunix' id='potgenfppreunix'/>
		<input type='hidden' name='potgenfptotalx' id='potgenfptotalx'/>
		
		<input type='hidden' name='fecvencimiento' id='fecvencimiento'/>
		<input type='hidden' name='fecemision' id='fecemision'/>


		<table border="0" width="885" class="tahoma11" cellpadding="3"	cellspacing="1">
			<tr bgcolor="#EFF3F9">
				<td width=885 align="left" class="titulo">Ingresos / Suministro de Luz</td>
			</tr>
		</table>
		<fieldset>
			<legend>
				<span class="titulo">Listado de Recibos de Luz
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
						<td align="center"><input type="button" id="buscar-sl" name="buscar-sl" value="Busqueda Rápida" onclick="lstBuscar()" /></td>
						<td align="center"><input type="button" id="pdf-sl" name="pdf-sl" value="PDF" onclick="lstPdf()" /></td>
						<td align="center"><input type="button" id="imprimir-sl" name="imprimir-sl" value="Imprimir" onclick="lstImprimir()" /></td>
						<td align="center"><input type="button" id="nuevo-sl" name="nuevo-sl" value="Nuevo" /></td>
					</tr>
				</table>
				</span>
			</legend>
		</fieldset>
	
		<display:table name="lstRes" 
						class="consultanormal"
						excludedParams="metodo" 
						requestURI="/suministroLuz.do?metodo=cargarAction"		
						id="row"
						export="false">
				<display:column title="" style="width:60px;">
					<img src="<%=request.getContextPath()%>/imagenes/manto/eliminar.png" alt="Eliminar..." border="0" width="16" height="16" id="" onclick="eliminar('${row.codOrgreciboLuz}');"/>
					<img src="<%=request.getContextPath()%>/imagenes/iconos/edit.png" alt="Editar..." border="0" width="16" height="16" onclick="ver(${row.codOrgreciboLuz},${row.numLecturaInicial},${row.numLecturaFinal},${row.numMonto},${row.numCostoWats},'<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecPeriodo}" />',${row.numEstado},${row.repomancnx},${row.cargofijo},${row.alumpublic},${row.subtotalmes},${row.igv},${row.totalmesact},${row.aporteley},${row.cuotaconv},${row.redonmesact},${row.redonmesant},${row.interesconvenio},${row.energactfraptaactual},${row.energactfraptaanteri},${row.energactfraptadifer},${row.energactfraptafactor},${row.energactfraptaconsu},${row.energactfraptaconfa},${row.energactfraptapreuni},${row.energactfraptatotal},${row.energacthorptaactu},${row.energacthorptaant},${row.energacthorptadif},${row.energacthorptafac},${row.energacthorptacons},${row.energacthorptaconfac},${row.energacthorptapreuni},${row.energacthorptatotal},${row.energreacinicial},${row.energreacanteri},${row.energreacdifere},${row.energreacfactor},${row.energreacconsu},${row.energreacfaccons},${row.energreacpreuni},${row.energreactotal},${row.potenciafpini},${row.potenciafpante},${row.potenciafpdif},${row.potenciafpfac},${row.potenciafpcons},${row.potenciahpact},${row.potenciahpant},${row.potenciahpdif},${row.potenciahpfac},${row.potenciahpcons},${row.potusoreddistconfac},${row.potusoreddistpreuni},${row.potusoreddisttotal},${row.potgenfpconfac},${row.potgenfppreuni},${row.potgenfptotal},'<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecVencimiento}" />','<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecEmision}" />');"/>
					<img src="<%=request.getContextPath()%>/imagenes/iconos/flecha.png" alt="Ver..." border="0" width="16" height="16" onclick="mostrarItems(${row.codOrgreciboLuz});"/>
				</display:column>
				<display:column title="Periodo" sortable="true">
					<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecPeriodo}" />
				</display:column>
				<display:column title="Fecha de Emisión" sortable="true">
					<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecEmision}" />
				</display:column>
				<display:column title="Vencimiento" sortable="true">
					<fmt:formatDate pattern="MM/dd/yyyy" value="${row.fecVencimiento}" />
				</display:column>				
				<display:column title="Total a Pagar" property="numMonto" sortable="true"></display:column>			
				<display:column title="Estado" sortable="true">
					<c:choose>
						<c:when test="${row.numEstado==1}">Activo</c:when>
						<c:when test="${row.numEstado==0}">Inactivo</c:when>
					</c:choose>			
				</display:column>
		</display:table>
		
		<div id="grabar-form" title="Agregar Recibo de Luz Original">						
			<table border="0" width="800px" cellpadding="0" cellspacing="0">
				<tr>
					<td><label><b>Periodo:</b></label></td>
					<td><input type='text' name='periodo' id='periodo' class='text ui-widget-content ui-corner-all' size="20" tabindex="1"/>(mes/dia/año)</td>
					<td>&nbsp;</td>
					<td><label><b>Fecha de Vencimiento:</b></label></td>
					<td><input type='text' name='fecvencimientox' id='fecvencimientox' class='text ui-widget-content ui-corner-all' size="20" tabindex="2"/>(mes/dia/año)</td>
				</tr>
				<tr>
					<td><label><b>Fecha de Emision:</b></label></td>
					<td><input type='text' name='fecemisionx' id='fecemisionx' class='text ui-widget-content ui-corner-all' size="20" tabindex="3"/>(mes/dia/año)</td>
					<td>&nbsp;</td>
					<td><label><b>Costo Wats:</b></label></td>	
					<td><input type='text' name='costoWats' id='costoWats' class='text ui-widget-content ui-corner-all' size="20" value="0" tabindex="4"/></td>
				</tr>
				<tr>
					<td><label><b>Estado:</b></label></td>	
					<td><input type="checkbox" name='estado' id='estado' class='text ui-widget-content ui-corner-all' tabindex="5"/></td>			
				</tr>
			</table>
			<table border="1" width="800px" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" width="200px"><font size="1"><b>Energía y Demanda</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Lectura<br>Actual</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Lectura<br>Anterior</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Diferencia</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Factor</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Consumos</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Consumos A<br>Facturar</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Precio<br>Unitario</b></font></td>
					<td align="center" width="70px"><font size="1"><b>Importe<br>Total</b></font></td>
				</tr>
				<tr>
					<td><font size="1">Reposic. y Mant. de Conex</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActRepMan' id='lecturaActRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntRepMan' id='lecturaAntRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaRepMan' id='DiferenciaRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorRepMan' id='FactorRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosRepMan' id='ConsumosRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactRepMan' id='ConsFactRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitRepMan' id='PrecUnitRepMan' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='repomancnx' id='repomancnx' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="6"/></td>
				</tr>
				<tr>
					<td><font size="1">Cargo Fijo</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActCarFij' id='lecturaActCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntCarFij' id='lecturaAntCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaCarFij' id='DiferenciaCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorCarFij' id='FactorCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosCarFij' id='ConsumosCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactCarFij' id='ConsFactCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitCarFij' id='PrecUnitCarFij' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='cargofijo' id='cargofijo' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="7"/></td>
				</tr>
				<tr>
					<td><font size="1">Energ. Activa Fuera Punta (kwh)</font></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptaactual' id='energactfraptaactual' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="8"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptaanteri' id='energactfraptaanteri' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="9"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptadifer' id='energactfraptadifer' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="10"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptafactor' id='energactfraptafactor' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="11"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptaconsu' id='energactfraptaconsu' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="12"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptaconfa' id='energactfraptaconfa' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="13"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptapreuni' id='energactfraptapreuni' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="14"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energactfraptatotal' id='energactfraptatotal' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="15"/></td>
				</tr>
				<tr>
					<td><font size="1">Energ. Activa Horas Punta (kwh)</font></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptaactu' id='energacthorptaactu' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="16"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptaant' id='energacthorptaant' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="17"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptadif' id='energacthorptadif' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="18"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptafac' id='energacthorptafac' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="19"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptacons' id='energacthorptacons' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="20"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptaconfac' id='energacthorptaconfac' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="21"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptapreuni' id='energacthorptapreuni' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="22"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energacthorptatotal' id='energacthorptatotal' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="23"/></td>
				</tr>
				<tr>
					<td><font size="1">Energía Reactiva (kVARh)</font></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacinicial' id='energreacinicial' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="24"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacanteri' id='energreacanteri' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="25"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacdifere' id='energreacdifere' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="26"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacfactor' id='energreacfactor' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="27"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacconsu' id='energreacconsu' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="28"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacfaccons' id='energreacfaccons' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="29"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreacpreuni' id='energreacpreuni' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="30"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='energreactotal' id='energreactotal' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="31"/></td>
				</tr>
				<tr>
					<td><font size="1">Interés Convenio</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActIntCon' id='lecturaActIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntIntCon' id='lecturaAntIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaIntCon' id='DiferenciaIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorIntCon' id='FactorIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosIntCon' id='ConsumosIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactIntCon' id='ConsFactIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitIntCon' id='PrecUnitIntCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='interesconvenio' id='interesconvenio' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="32"/></td>
				</tr>
				<tr>
					<td><font size="1">Potencia FP (KW)</font></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciafpini' id='potenciafpini' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="33"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciafpante' id='potenciafpante' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="34"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciafpdif' id='potenciafpdif' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="35"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciafpfac' id='potenciafpfac' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="36"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciafpcons' id='potenciafpcons' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="37"/></td>
					<td align="center">&nbsp;<!-- input type='text' name='ConsFactPotFP' id='ConsFactPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='PrecUnitPotFP' id='PrecUnitPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='ImporTotalPotFP' id='ImporTotalPotFP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
				</tr>
				<tr>
					<td><font size="1">Potencia HP (KW)</font></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciahpact' id='potenciahpact' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="38"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciahpant' id='potenciahpant' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="39"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciahpdif' id='potenciahpdif' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="40"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciahpfac' id='potenciahpfac' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="41"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potenciahpcons' id='potenciahpcons' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="42"/></td>
					<td align="center">&nbsp;<!-- input type='text' name='ConsFactPotHP' id='ConsFactPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='PrecUnitPotHP' id='PrecUnitPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' name='ImporTotalPotHP' id='ImporTotalPotHP' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
				</tr>
				<tr>
					<td><font size="1">Pot. Uso Redes Distrib. F (KW)</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActPotDis' id='lecturaActPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntPotDis' id='lecturaAntPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaPotDis' id='DiferenciaPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorPotDis' id='FactorPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosPotDis' id='ConsumosPotDis' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potusoreddistconfac' id='potusoreddistconfac' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="43"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potusoreddistpreuni' id='potusoreddistpreuni' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="44"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potusoreddisttotal' id='potusoreddisttotal' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="45"/></td>
				</tr>
				<tr>
					<td><font size="1">Potencia de Generación FP (KW)</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActPotGen' id='lecturaActPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntPotGen' id='lecturaAntPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaPotGen' id='DiferenciaPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorPotGen' id='FactorPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosPotGen' id='ConsumosPotGen' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potgenfpconfac' id='potgenfpconfac' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="46"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potgenfppreuni' id='potgenfppreuni' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" tabindex="47"/></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='potgenfptotal' id='potgenfptotal' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="48"/></td>
				</tr>
				<tr>
					<td><font size="1">Alumbrado Público</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActAluPub' id='lecturaActAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntAluPub' id='lecturaAntAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaAluPub' id='DiferenciaAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorAluPub' id='FactorAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosAluPub' id='ConsumosAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactAluPub' id='ConsFactAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitAluPub' id='PrecUnitAluPub' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='alumpublic' id='alumpublic' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="49"/></td>
				</tr>
				<tr>
					<td><font size="1"><b>SUBTOTAL Mes Actual</b></font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActSubTot' id='lecturaActSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntSubTot' id='lecturaAntSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaSubTot' id='DiferenciaSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorSubTot' id='FactorSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosSubTot' id='ConsumosSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactSubTot' id='ConsFactSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitSubTot' id='PrecUnitSubTot' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='subtotalmes' id='subtotalmes' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" readonly="readonly" tabindex="50"/></td>
				</tr>
				<tr>
					<td><font size="1">I.G.V.</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActIGV' id='lecturaActIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntIGV' id='lecturaAntIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaIGV' id='DiferenciaIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorIGV' id='FactorIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosIGV' id='ConsumosIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactIGV' id='ConsFactIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitIGV' id='PrecUnitIGV' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='igv' id='igv' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" readonly="readonly" tabindex="51"/></td>
				</tr>
				<tr>
					<td><font size="1"><b>TOTAL Mes Actual</b></font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActTotMes' id='lecturaActTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntTotMes' id='lecturaAntTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaTotMes' id='DiferenciaTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorTotMes' id='FactorTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosTotMes' id='ConsumosTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactTotMes' id='ConsFactTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitTotMes' id='PrecUnitTotMes' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='totalmesact' id='totalmesact' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" readonly="readonly" tabindex="52"/></td>
				</tr>
				<tr>
					<td><font size="1">Aporte Ley Nº 28749</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActApoLey' id='lecturaActApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntApoLey' id='lecturaAntApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaApoLey' id='DiferenciaApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorApoLey' id='FactorApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosApoLey' id='ConsumosApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactApoLey' id='ConsFactApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitApoLey' id='PrecUnitApoLey' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='aporteley' id='aporteley' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="53"/></td>
				</tr>
				<tr>
					<td><font size="1">Cuota Convenio (10 cuotas)</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActCuoCon' id='lecturaActCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntCuoCon' id='lecturaAntCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaCuoCon' id='DiferenciaCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorCuoCon' id='FactorCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosCuoCon' id='ConsumosCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactCuoCon' id='ConsFactCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitCuoCon' id='PrecUnitCuoCon' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='cuotaconv' id='cuotaconv' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="54"/></td>
				</tr>
				<tr>
					<td><font size="1">Redondeo Mes Anterior</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActRedAnt' id='lecturaActRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntRedAnt' id='lecturaAntRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaRedAnt' id='DiferenciaRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorRedAnt' id='FactorRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosRedAnt' id='ConsumosRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactRedAnt' id='ConsFactRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitRedAnt' id='PrecUnitRedAnt' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='redonmesant' id='redonmesant' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="55"/></td>
				</tr>
				<tr>
					<td><font size="1">Redondeo Mes Actual</font></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaActRedAct' id='lecturaActRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='lecturaAntRedAct' id='lecturaAntRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='DiferenciaRedAct' id='DiferenciaRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='FactorRedAct' id='FactorRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsumosRedAct' id='ConsumosRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='ConsFactRedAct' id='ConsFactRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align="center">&nbsp;<!-- input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='PrecUnitRedAct' id='PrecUnitRedAct' class='text ui-widget-content ui-corner-all' size="10" maxlength="10" /--></td>
					<td align='center'><input type='text' class='texto' style='font-family:verdana;font-size:8px;' name='redonmesact' id='redonmesact' class='text ui-widget-content ui-corner-all' size='10' maxlength='10' value="0" onChange="calculaCampos()" tabindex="56"/></td>
				</tr>
				<tr>
					<td><label>Total:</label></td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>	
					<td align="center"><input type='text' name='monto' class='texto' style='font-family:verdana;font-size:8px;' id='monto' size="10" value="0" readonly="readonly" tabindex="57"/></td>
				</tr>
			</table>
			<table style="visibility: hidden;">
				<tr>
					<td><label>Lectura Anterior:</label></td>
					<td><input type='text' name='lecturaIni' id='lecturaIni' class='text ui-widget-content ui-corner-all' size="10" value="0"/></td>
					<td><label>Lectura Actual:</label></td>
					<td><input type='text' name='lecturaFin' id='lecturaFin' class='text ui-widget-content ui-corner-all'  size="30" value="0"/></td>
				</tr>
			</table>
				<button name="btngrabar" id="btngrabar" onclick="grabar();">Grabar</button>
				<button name="btncerrar" id="btncerrar" onclick="cerrarPop();">Cancelar</button>
		</div>
		<div id="eliminar-form" title="Esta seguro que desea eliminar...?">
			<div align="center">
			</div>
		</div>
	</html:form>

</body>
</html:html>