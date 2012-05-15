<!DOCTYPE html>
<%@page import="java.text.Format"%>
<%@page import="oracle.sql.DATE"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.util.NumberToLeterConverter"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Impresión de Documento...</title>
		<link type="text/css" rel="stylesheet"  href="<%=request.getContextPath()%>/css/cupertino/jquery-ui-1.8.16.custom.css"  />	
			
		<!--  JavaScript  -->
		<script language="javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.min.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.16.custom.min.js"></script>
		<script type="text/javascript">
			$(function(){

				$("#btnPrint").button();
				$("#btnCerrar").button();
				
				// Dialog			
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() { 
							$(this).dialog("close"); 
						}, 
						"Cancel": function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
				// Dialog Link
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});

				//hover states on the static widgets
				$('#dialog_link, ul#icons li').hover(
					function() { $(this).addClass('ui-state-hover'); }, 
					function() { $(this).removeClass('ui-state-hover'); }
				);

				$("#btnPrint").button().click(function() {
					alert("Pruebas");
					var nrodocReal =  ${nroDocReal};
					var nrodocInte =  ${nroDocInte};
					//var nrodocReal =  $("{#nroDocReal}").val();
					//var nrodocInte =  $("{#nroDocInte}").val();
					$.ajax({
					        type: "POST",
					        url: "/SISGAPWeb/AjaxServlet",
					        //data: "action=ACTUALIZA_NRO_DOCUMENTO&nrodocReal="+nrodocReal+"&opcion=factura&nrodocInte="+nrodocInte,
					        data: "action=ACTUALIZA_NRO_DOCUMENTO&nrodocReal="+nrodocReal+"&nrodocInte="+nrodocInte
					});
				});
			});
		</script>
		<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; margin: 50px;}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
		</style>	
		
		<style type="text/css">
			.font11n   {font:normal 11px Arial;}
			.font11b   {font:bold   11px Arial;}
			.font12n   {font:normal 12px Arial;}
			.font12b   {font:bold   12px Arial;}
			.font16b   {font:bold   16px Arial;}
			.font13t   {font:bold   13px Arial; color:#FFFFFF}
			.font14n   {font:normal 14px Arial;}
			.boton     {font:normal 11px Arial; width:80px}
		</style>
		<style type="text/css" media="print">
			.noprint  {display:none}
		</style>
		
	</head>
	<body>
		<c-rt:set var="now" value="<%= new java.util.Date() %>" />
		<table width="650" class="font12n" border="0" cellspacing="0">
		   <tr>
		      <td width="100" align="center" valign="center" class="noprint"><img src="imagenes/Logo_Reporte_MMH.png" width="94" height="66" alt="" /></td>
		      <td width="350" align="center">
		      	<font face="arial" size="3"><b>ASOCIACION DE COMERCIANTES<br />DEL MERCADO MODELO DE HUARAL</b></font><br /><br />
		      	<font face="arial" size="1">Fundado el 13 de Noviembre de 1996<br />Ficha Registral Nº 7214</font>
		      </td>
		      <td width="220" align="center">
		         <table cellspacing="0" cellpadding="0" border="0">
		            <tr>
		               <td><img src="imagenes/b11.gif" width="6" height="6" alt="" align="bottom" /></td>
		               <td><img src="imagenes/b12.gif" width="200" height="6" alt="" align="bottom" /></td>
		               <td><img src="imagenes/b13.gif" width="6" height="6" alt="" align="bottom" /></td>
		            </tr>
		            <tr>
		               <td><img src="imagenes/b21.gif" width="6" height="72" alt="" /></td>
		               <td>
		                  <table width="100%" class="font16b" cellpadding="1" cellspacing="0">
		                     <tr>
		                        <td colspan="2" align="center"><b>R.U.C. Nº 20530606334</b></td>
		                     </tr>
		                     <tr>
		                        <td colspan="2" align="center" bgcolor="black"><font color="white"><b>RECIBO DE INGRESO</b></font></td>
		                     </tr>
		                     <tr>
		                        <td align="Right">Nº 001-</td><td align="left" id="txtnroReal" name="txtnroReal">${nroDocReal}</td>
		                     </tr>
		                  </table>
		               </td>
		               <td><img src="imagenes/b23.gif" width="6" height="70" alt="" /></td>
		            </tr>
		            <tr>
		               <td><img src="imagenes/b31.gif" width="6" height="6" alt="" align="top" /></td>
		               <td><img src="imagenes/b32.gif" width="200" height="6" alt="" align="top" /></td>
		               <td><img src="imagenes/b33.gif" width="6" height="6" alt="" align="top" /></td>
		            </tr>
		         </table>
		      </td>
		   </tr>
		   <tr>
		      <td colspan="3">
		         <table class="font11n" border="0" width="550">
		            <tr>
		               <td width="80"><b>Asociado(a): </b></td>
		               <td colspan="5">${fac.sisgapSocio}</td>
		            </tr>
		            <tr>
		               <td><b>Sector Nº: </b></td>
		               <td width="100">${fac.sisgapSocio.strSector}</td>
		               <td align="right" width="80"><b>Giro: </b></td>
		               <td width="100" colspan="2">${fac.sisgapSocio.sisgapActividadSocio.actiTranNombre}</td>
		            </tr>
		            <tr>
		               <td><b>Nº de Puesto: </b></td>
		               <td colspan="2">${fac.sisgapSocio.tranPuesto}</td>
		               <td>&nbsp;</td>
		               <td><b>Huaral, <fmt:formatDate value="${now}" type="both" timeStyle="long" dateStyle="long"/></b></td>
		            </tr>
		         </table>
		      </td>
		   </tr>
		   <tr>
		      <td colspan="3">
		        <table width="100%" class="font12n" cellspacing="1" border="0">
		            <tr style="background-color:#999999; height:20px">
		               <th class="font13t" width="40">ITEM</th>
		               <th class="font13t" width="50">CANT.</th>
		               <th class="font13t">DESCRIPCIÓN</th>
		               <th class="font13t" width="80">P.UNIT.</th>
		               <th class="font13t" width="80">IMPORTE</th>
		            </tr>
		            <c:set var="i" value="1" />
		            <c:set var="total" value="0" />
					<c:forEach var="det" items="${lstDetFac}">
						<tr>					
							<td>|&nbsp;<c:out value="${i}"/></td>
							<!-- td>${det.id.codItemcobranza}</td -->
							<!-- td>${det.strTipocobranzaDescrip}</td -->
							<!-- td>${det.strMonedaDescrip}</td -->
							<!-- td>${det.numAcuenta}</td -->
							<td align="center">${det.numCantidad}</td>
							<td>${det.strDescripcion}</td>
							<td align="center">${det.numCosto}</td>								
							<td align="center">${det.numTotal}</td>
							<td>|</td>
						</tr>
						<tr><td>|</td><td colspan="4">&nbsp;</td><td>|</td></tr>
						<tr><td>|</td><td colspan="4">&nbsp;</td><td>|</td></tr>
						<tr><td>|</td><td colspan="4">&nbsp;</td><td>|</td></tr>
						<tr><td>|</td><td colspan="4">&nbsp;</td><td>|</td></tr>
						<tr><td>|</td><td colspan="4">&nbsp;</td><td>|</td></tr>
						<c:set var="total" value="${total+det.numTotal}" />
						<c:set var="i" value="${i+1}" />
					</c:forEach>
		            <tr>
		               <td colspan="5"><b>Son: </b>${texto}</td>
		            </tr>
		            <tr>
		               <td colspan="5"><hr size="1" color="#999999" /></td>
		            </tr>
		            <tr>
		            	<td colspan="3"></td>
		                <td align="center">TOTAL <font face="Courier New">==></font></td>
		        		<fmt:formatNumber value="${total}" type="currency" var="total" />
					    <td><c:out value="${total}" /></td>
		                <td align="right" class="font14n"></td>
		            </tr>
		         </table>
		      </td>
		   </tr>
		</table>
		<form name="frmPrint" method="post" action="">
		<table width="720" class="noprint">
		   <tr>
		      <td width="200"></td>
		      <td align="center"><input type="button" id="btnPrint" name="btnPrint" value="Imprimir" onclick="window.print()" /></td>
		      <td align="center"><input type="button" id="btnCerrar" name="btnCerrar" value="Cerrar" onclick="window.close()" /></td>
		      <td width="200"></td>
		   </tr>
		</table>
		</form>
	</body>
</html>


