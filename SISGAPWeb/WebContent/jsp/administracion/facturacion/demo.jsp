<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<title>jQuery UI Example Page</title>
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
		<table width="720" class="font12n" border="0" cellspacing="0">
		   <tr>
		      <td colspan="3"><hr size="1" color="#003399" /></td>
		   </tr>
		   <tr>
		      <td colspan="3" align="center" style="font-size:24px">Mercado Modelo Huaral</td>
		   </tr>
		   <tr>
		      <td colspan="3"><hr size="1" color="#003399" /></td>
		   </tr>
		   <tr>
		      <td width="150"><img src="imagenes/Logo_Reporte_MMH.png" width="94" height="66" alt="" /></td>
		      <td width="350" align="center">Av. Camino viejo a Jesús del Valle s/n<br />Huaral</td>
		      <td width="220" align="right">
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
		                        <td align="center">R.U.C. 20265681299</td>
		                     </tr>
		                     <tr>
		                        <td align="center">R E C I B O</td>
		                     </tr>
		                     <tr>
		                        <td align="center">N° 0004 - 00123</td>
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
		         <table class="font11n" border="0">
		            <tr>
		               <td width="60"><b>Señor(es): </b></td>
		               <td width="400">LUNA TRANCA CARLOTA MAGNA</td>
		               <td width="60"><b>Puesto: </b></td>
		               <td width="50">0005</td>
		               <td width="50"><b>Fecha: </b></td>
		               <td width="100">02/05/2012 15:32</td>
		            </tr>
		            <tr>
		               <td><b>Dirección: </b></td>
		               <td>Calle Chinchón 180 - Piso 13 - San Isidro</td>
		               <td><b>Actividad: </b></td>
		               <td colspan="3">GRANOS SECOS</td>
		            </tr>
		         </table>
		      </td>
		   </tr>
		   <tr>
		      <td colspan="3">
		        <table width="100%" class="font12n" cellspacing="1">
		            <tr style="background-color:#999999; height:20px">
		               <th class="font13t" width="40">ITEM</th>
		               <th class="font13t" width="50">CANT.</th>
		               <th class="font13t">DESCRIPCIÓN</th>
		               <th class="font13t" width="80">P.UNIT.</th>
		               <th class="font13t" width="80">IMPORTE</th>
		            </tr>
		            <tr>
		                <td align="center">1</td>
		                <td align="center">01</td>
		                <td style="padding-left:4px">AUTOVALUO</td>
		                <td align="right" style="padding-right:4px">$ 140.00</td>
		                <td align="right" style="padding-right:4px">$ 140.00</td>
		            </tr>
		            <tr>
		                <td align="center">2</td>
		                <td align="center">01</td>
		                <td style="padding-left:4px">PREDIAL</td>
		                <td align="right" style="padding-right:4px">$ 120.00</td>
		                <td align="right" style="padding-right:4px">$ 120.00</td>
		            </tr>
		            <tr>
		               <td colspan="5"><hr size="1" color="#999999" /></td>
		            </tr>
		            <tr>
		               <td colspan="3"></td>
		               <td align="center">TOTAL <font face="Courier New">==></font></td>
		               <td align="right" class="font14n">$ 260.00</td>
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


