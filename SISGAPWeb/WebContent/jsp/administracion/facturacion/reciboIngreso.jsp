<!DOCTYPE html>
<%@page import="java.text.Format"%>
<%@page import="oracle.sql.DATE"%>
<%@include file="../../../taglibs.jsp"%>
<%@page import="pe.com.mmh.sisgap.util.NumberToLeterConverter"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>Impresi�n de Documento...</title>
		<!-- link type="text/css" rel="stylesheet"  href="<%=request.getContextPath()%>/css/cupertino/jquery-ui-1.8.16.custom.css"  / -->	
			
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

				$('#btnPrint').click(function() {
					var nrodocReal = $("#docreal").val();
					var nrodocInte = $("#docinte").val();
					//alert("DocReal: "+nrodocReal+"\nDocInte: "+nrodocInte);

					alert("Antes de imprimir todo desde el recibo");
					$('[name=metodo]').val('print');

					window.close();				
					$('#gestionarFacturacion').submit();

				});

				
			});

/*			function imprime(){
				alert("Antes de imprimir todo");
				$('[name=metodo]').val('print');
				window.close();
				$('#gestionarFacturacion').submit();
			}*/
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
		
		<style type="text/css" media="print">
			.noprint  {display:none}
		</style>
		
	</head>
	<body style="padding-bottom: 100px;">
	<html:form action="/gestionarFacturacion.do" styleId="gestionarFacturacion">
		<input type="hidden" name="metodo" />
		<input type="hidden" name="docreal" id="docreal" value="${nroDocReal}" />
		<input type="hidden" name="docinte" id="docinte" value="${nroDocInte}" />
		<input type="hidden" id="variable" name="variable"
			value="<%=session.getAttribute("Respuesta")%>" />
	
		<c-rt:set var="now" value="<%= new java.util.Date() %>" />
		<table width="550" border="0" cellspacing="0" style="padding-left: 50px;">
		   <tr>
		      <td width="80" align="center" valign="middle">
		      	<img src="imagenes/Logo_Reporte_MMH.png" width="94" height="66" alt="" />
		      </td>
		      <td width="250" align="center">
		      	<font face="arial" size="2"><b>ASOCIACION DE COMERCIANTES<br />DEL MERCADO MODELO DE HUARAL</b></font><br /><br />
		      	<font face="arial" size="1">Fundado el 13 de Noviembre de 1996<br />Ficha Registral N� 7214</font>
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
		                  <table width="100%" cellpadding="0" cellspacing="0">
		                     <tr valign="middle"><td colspan="2" align="center"><font face="arial" size="4"><b>R.U.C. N� 20530606334</b></font></td></tr>
		                     <tr valign="middle"><td colspan="2" align="center" bgcolor="black"><font face="arial" size="3" color="white"><b>RECIBO DE INGRESO</b></font></td></tr>
		                     <tr valign="middle"><td align="Right"><font face="arial" size="4">N� 001-</font></td><td align="left" id="txtnroReal" name="txtnroReal"><font face="arial" size="3">${nroDocReal}</font></td></tr>
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
		         <table border="0" width="530" cellpadding="0" cellspacing="3">
		         	<tr>
		         		<td width="100">&nbsp;</td>
		         		<td width="100">&nbsp;</td>
		         		<td width="60">&nbsp;</td>
		         		<td width="140">&nbsp;</td>
		         		<td rowspan="3" width="170" align="right">
		         			<!--  fmt:setLocale value="es_DO"/-->
		         			<fmt:formatNumber value="${total}" var="total1"/> 
		         			<font face="Courier New,Courier" size="4"><b><c:out value="${total1}"/>&nbsp;&nbsp;</b></font>
		         		</td>
		         	</tr>
		            <tr>
		               <td><b>Asociado(a):�</b></td>
		               <td align="left" colspan="3"><font face="Courier New,Courier">${fac.sisgapSocio}</font></td>
		            </tr>
		            <tr>
		               <td><b>Sector N�: </b></td>
		               <td align="left"><font face="Courier New,Courier">${fac.sisgapSocio.strSector}</font></td>
		               <td align="right"><b>Giro:�</b></td>
		               <td align="left"><font face="Courier New,Courier">${fac.sisgapSocio.sisgapActividadSocio.actiTranNombre}</font></td>
		            </tr>
		            <tr>
		               <td><b>N� de Puesto:�</b></td>
		               <td align="left"><font face="Courier New,Courier">${fac.sisgapSocio.tranPuesto}</font></td>
		               <td>&nbsp;</td>
		               <td colspan="2" align="right"><font face="Courier New,Courier" size="1"><b><fmt:formatDate value="${now}" type="both" timeStyle="short" dateStyle="long"/></b></font></td>
		            </tr>
		         </table>
		      </td>
		   </tr>
		   <tr>
		      <td colspan="3">
		        <table width="500" cellspacing="0" cellpadding="0" border="0"  style="padding-left: 10px;">
		            <c:set var="i" value="1" />
		            <c:set var="total" value="0" />
		            <tr>
		               <td><img src="imagenes/b11.gif" width="6" height="6" alt="" align="bottom" /></td>
		               <td><img src="imagenes/b12.gif" width="500" height="6" alt="" align="bottom" /></td>
		               <td><img src="imagenes/b13.gif" width="6" height="6" alt="" align="bottom" /></td>
		            </tr>
		            <tr>
		            	<td><img src="imagenes/b21.gif" width="6" height="140" alt="" /></td>
		        		<td valign="top">
							<table width="500px" border="0" cellpadding="1" cellspacing="1">
								<tr style="background-color:#999999; height:15px">
					               <th class="font13t" width="50">CANT.</th>
					               <th class="font13t" width="300">DESCRIPCI�N</th>
					               <th class="font13t" width="70">P.UNIT.</th>
					               <th class="font13t" width="70">IMPORTE</th>
					            </tr>
								<c:forEach var="det" items="${lstDetFac}">
									<tr>
										<td align="center"><font face="Courier New,Courier">${det.numCantidad}</font></td>
										<td><font face="Courier New,Courier">${det.strDescripcion}</font></td>
										<fmt:formatNumber value="${det.numCosto}" type="currency" var="numCosto" />
										<fmt:formatNumber value="${det.numTotal}" type="currency" var="numTotal" />
										<td align="center"><font face="Courier New,Courier"><c:out value="${numCosto}" /></font></td>								
										<td align="center"><font face="Courier New,Courier"><c:out value="${numTotal}" /></font></td>
										<c:set var="total" value="${total+det.numTotal}" />
										<c:set var="i" value="${i+1}" />
									</tr>
								</c:forEach>
							</table>
						</td>
						<td><img src="imagenes/b23.gif" width="6" height="140" alt="" /></td>
					</tr>
		            <tr>
		            	<td><img src="imagenes/b21.gif" width="6" height="17" alt="" /></td>
		                <td style="background-color:#999999;">Son: <font face="Courier New,Courier"><b>${texto}</b></font></td>
		                <td><img src="imagenes/b23.gif" width="6" height="17" alt="" /></td>
		            </tr>
		            <tr>
		            	<td><img src="imagenes/b21.gif" width="6" height="17" alt="" /></td>
		                <td><hr size="1" color="#999999" /></td>
		                <td><img src="imagenes/b23.gif" width="6" height="17" alt="" /></td>
		            </tr>
		            <tr>
		            	<td><img src="imagenes/b21.gif" width="6" height="17" alt="" /></td>
		                <td align="right">
		                	TOTAL&nbsp;<font face="Courier New">&nbsp;&nbsp;&nbsp;&nbsp;</font>
		        			<fmt:formatNumber value="${total}" type="currency" var="total" />
					    	<font face="Courier New,Courier"><c:out value="${total}" /></font>
					    </td>
		                <td><img src="imagenes/b23.gif" width="6" height="17" alt="" /></td>
		            </tr>
		            <tr>
		                <td><img src="imagenes/b31.gif" width="6" height="6" alt="" align="top" /></td>
		                <td><img src="imagenes/b32.gif" width="500" height="6" alt="" align="top" /></td>
		                <td><img src="imagenes/b33.gif" width="6" height="6" alt="" align="top" /></td>
		            </tr>
		         </table>
		      </td>
		   </tr>
		</table>
		    <input type="hidden" name="guess" value="Imprimir">

			<input type="hidden" name="nroDocReal" id="nroDocReal" value="${nroDocReal}">
			<input type="hidden" name="nroDocInte" id="nroDocInte" value="${nroDocInte}">
			<input type="hidden" name="asociado" id="asociado" value="${fac.sisgapSocio}">
			<input type="hidden" name="giro" id="giro" value="${fac.sisgapSocio.sisgapActividadSocio.actiTranNombre}">
			<input type="hidden" name="puesto" id="puesto" value="${fac.sisgapSocio.tranPuesto}">
			<input type="hidden" name="total" id="total" value="${total1}">
			<input type="hidden" name="fecha" id="fecha" value="${now}">
			<input type="hidden" name="detalle" id="detalle" value="${lstDetFac}">


		<table border="1">
			<tr>
				<td><button id="btnPrint" name="btnPrint">Imprimir</button></td>
			</tr>
		</table>
		</html:form>

	</body>
</html>


