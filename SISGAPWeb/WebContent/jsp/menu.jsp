<%@ page import="pe.com.mmh.sisgap.comun.constantes.ConstantesSesion"%>
<%@ page import="pe.com.mmh.sisgap.seguridad.domain.UserInfo"%>
<%@ page import="pe.com.mmh.sisgap.comun.constantes.Constantes"%>
<%@ page import="pe.com.mmh.sisgap.util.SeguridadUtil"%>
<%UserInfo user=SeguridadUtil.obtenerUsuarioSesion(request);%>

<html>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/estilos.css" />
<style type="text/css">
.SM_po90023 {
	width: 116;
	position: relative;
	top: 0;
	border-top: ;
	background-image: url(../imagenes/tab.gif);
	font: 11px arial;
	color: white;
	text-align: center;
	padding: 4px;
	cursor: hand;
}

.SM_p90023 {
	width: 116;
	position: relative;
	top: 0;
	border-top: ;
	background-image: url(../imagenes/tab.gif);
	font: 11px arial;
	color: white;
	text-align: center;
	padding: 4px;
}

.SM_co90023 {
	width: 116;
	border-top: ;
	font: 11px arial;
	color: #2D466F;
	text-align: center;
	text-decoration: underline;
	cursor: hand;
}

.SM_c90023 {
	width: 116;
	border-top: ;
	font: 11px arial;
	color: #2D466F;
	text-align: center;
}

.SM_cb90023 {
	background-color: #E7EBF0;
}

.SM_ps90023 {
	width: 116;
	position: relative;
	top: 0;
	border-top: ;
	background-image: url(../imagenes/tab.gif);
	font: 11px arial;
	color: white;
	text-align: center;
	padding: 4px;
}

.SM_cs90023 {
	width: 116;
	border-top: 0;
	font: 11px arial;
	color: #2D466F;
	text-align: center;
}

.SMEmptyDiv90023 {
	overflow: hidden;
	height: 1px;
	width: 100%;
	border-top: ;
}
</style>
<script language="javascript" src="<%=request.getContextPath()%>/js/utils.js"></script>
<script language="javascript">
	var tabMenu = null;
	function menu_onload() {
		tabMenu = document.getElementById("tblMenu");
		if (tabMenu.rows.length <= 0)
			return;
		var firstSlide = tabMenu.rows(0).cells(0).children(0);
		var firstSlideSub = tabMenu.rows(0).cells(0).children(1);
		SMCallerName90023 = firstSlide.id;
		SMinitiallyOpenSub90023 = firstSlideSub.id;
		firstSlideSub.style.display = 'block';
		firstSlideSub.style.position = 'static';
		firstSlideSub.style.top = 'auto';
		firstSlideSub.style.height = 'auto';
		firstSlideSub.style.height = 200;
		tabMenu.height = "100%";
		SMheightToOpen90023 = tabMenu.clientHeight
				- ((firstSlide.clientHeight) * tabMenu.rows.length);
		firstSlideSub.style.height = SMheightToOpen90023;
		for ( var i = 1; i < tabMenu.rows.length; i++) {
			tabMenu.rows(i).cells(0).children(0).className = "SM_p90023";
		}

	}
	function on_resize() {
		if (tabMenu.rows.length <= 0)
			return;
		var firstSlide = (SMCallerOpen90023 != null) ? SMCallerOpen90023
				: document.getElementById(SMCallerName90023);
		var firstSlideSub = (SMobjOpen90023 != null) ? SMobjOpen90023
				: document.getElementById(SMinitiallyOpenSub90023);
		firstSlideSub.style.height = 200;
		tabMenu.height = "100%";
		SMheightToOpen90023 = tabMenu.clientHeight
				- ((firstSlide.clientHeight) * tabMenu.rows.length);
		firstSlideSub.style.height = SMheightToOpen90023;
	}
	runOnload(menu_onload);
</script>
<body topmargin=0 leftmargin=0 bgcolor="#FFFFFF" onresize="on_resize();">
<SCRIPT language="javascript" src="<%=request.getContextPath()%>/js/menu.js">

	</SCRIPT>
<table id="tblMenu"
	style="BORDER-RIGHT: medium none; BORDER-LEFT: medium none; BORDER-BOTTOM: medium none; BORDER-RIGHT: 1px solid #B7B7B7"
	height="100%" cellSpacing="0" cellPadding="0" border="0">
<% if (user.tienePermiso(Constantes.MENU_REPORTE_CREDITICIO)){%>	
	<tr>
		<td>
		<div class="SM_ps90023" id="SM900233"
			onmouseover='SMcs90023(this, "SM_po90023", "")'
			onclick='SMpoc90023("SM900233Sub", this, 1)'
			onmouseout='SMcs90023(this, "SM_p90023", "")'>
			Ingresos</div>
		<div class="SM_cb90023" id="SM900233Sub"
			style="DISPLAY: none; OVERFLOW: auto; WIDTH: 120px; POSITION: relative; TOP: 0px; HEIGHT: 0px;">
		<div class="menuv">
			<ul>
				<li>
					<div class="botonDelMenu">			 			   
						<a href="<%=request.getContextPath()%>/gestionarFacturacion.do?metodo=cargarAction" target="FraMain">						
							<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/seleccionarConceptos.png">
							Facturación
					    </a>
					</div>
				</li>
				<li>
					<div class="botonDelMenu">
						<a href="<%=request.getContextPath()%>/suministroLuz.do?metodo=cargarAction" target="FraMain">						
							<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/transmitir.png"> 
							Suministro de Luz 
				    	</a>
					</div>
				</li>
				<li>
					<div class="botonDelMenu">		
						<a href="<%=request.getContextPath()%>/registrosisas.do?metodo=cargarAction" target="FraMain">						
							<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/seleccionarConceptosAtributos.png"> 
							Vigilancia
					    </a>
					</div>
				</li>
				<li>
					<div class="botonDelMenu">		
						<a href="<%=request.getContextPath()%>/servicioshigienicos.do?metodo=cargarAction" target="FraMain">						
							<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/servicios.png"> 
							Servicios Higienicos
					    </a>
					</div>
				</li>
			</ul>
		</div>
		</div>
		</td>
	</tr>
<%}%>	
<% if (user.tienePermiso(Constantes.MENU_ADMINISTRACION)){%>		
	<tr>
		<td>
		<div class="SM_ps90023" id="SM900234"
			onmouseover='SMcs90023(this, "SM_po90023", "")'
			onclick='SMpoc90023("SM900234Sub", this, 1)'
			onmouseout='SMcs90023(this, "SM_p90023", "")'>Administración</div>
		<div class="SM_cb90023" id="SM900234Sub"
			style="DISPLAY: none; OVERFLOW: auto; WIDTH: 120px; POSITION: relative; TOP: 0px; HEIGHT: 0px;">
		<div class="menuv">
			<ul>
				<li>
					<div class="botonDelMenu">
						<a href="<%=request.getContextPath()%>/gestionarSocios.do?metodo=cargarAction" target="FraMain" >
						<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/gestionarClientes.png"/>
						Gestionar Socios</a>
					</div>
				</li>
				<li>
					<div class="botonDelMenu">
						<a href="<%=request.getContextPath()%>/gestionarItemCobranza.do?metodo=cargarAction" target="FraMain">
						<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/gestionarConceptos.png"/>
						Gestionar Items de Cobranza</a>
					</div>			
				</li>
				<li>
					<div class="botonDelMenu" style="visibility: hidden;">
						<a href="<%=request.getContextPath()%>/aprobarModelos.do?metodo=cargarAction" target="FraMain" >
						<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/aprobarModelos.png"/> 
						Aprobar	modelos</a>
					</div>
				</li>
				<li>
					<div class="botonDelMenu" style="visibility: hidden;">
						<a href="<%=request.getContextPath()%>/his.do?metodo=cargarAction" target="FraMain">						
				        <img alt="" src="<%=request.getContextPath()%>/imagenes/menu/historialModelos.png"> 
				        Historial de modelos</a>
					</div>
				</li>
				<li>
					<div class="botonDelMenu" style="visibility: hidden;">
						<a href="<%=request.getContextPath()%>/auditoria.do?metodo=cargarAction" target="FraMain">
						<img alt="" src="<%=request.getContextPath()%>/imagenes/menu/auditoria.png"/>
						Auditoria</a>
					</div>
				</li>
			</ul>
		</div>
		</div>
		</td>
	</tr>
<%}%>	
<% if (user.tienePermiso(Constantes.MENU_REPORTES)){%>	
	<tr>
		<td>
		<div class="SM_ps90023" id="SM900235"
			onmouseover='SMcs90023(this, "SM_po90023", "")'
			onclick='SMpoc90023("SM900235Sub", this, 1)'
			onmouseout='SMcs90023(this, "SM_p90023", "")'>Reportes</div>
		<div class="SM_cb90023" id="SM900235Sub"
			style="DISPLAY: none; OVERFLOW: auto; WIDTH: 120px; POSITION: relative; TOP: 0px; HEIGHT: 0px;">


		<div class="menuv">
			<ul>
				<li>
					<div class="botonDelMenu">
				   		<a href="<%=request.getContextPath()%>/repConAtr.do?metodo=cargarAction" target="FraMain">
					    <img alt="" src="<%=request.getContextPath()%>/imagenes/menu/reporteConceptoAtributos.png"/>
					    Reporte de Socios</a>			
					</div>
				</li>
				<li>
					<div class="botonDelMenu">
				   		<a href="<%=request.getContextPath()%>/reporteItemsCobranza.do?metodo=cargarAction" target="FraMain">
					    <img alt="" src="<%=request.getContextPath()%>/imagenes/menu/reporteItemsCobranza.png"/>
					    Reporte Items de Cobranza</a>			
					</div>
				</li>
				<li>
					<div class="botonDelMenu" style="visibility: hidden;">		
						<a href="<%=request.getContextPath()%>/repDicGen.do?metodo=cargarAction" target="FraMain">						
				        <img alt="" src="<%=request.getContextPath()%>/imagenes/menu/reporteDiccionarioGeneral.png"> 
				        Reporte del Diccionario en general</a>	
					</div>
				</li>
				<li>
					<div class="botonDelMenu" style="visibility: hidden;">
						 <a href="<%=request.getContextPath()%>/repModGen.do?metodo=cargarAction" target="FraMain">						
						 <img alt="" src="<%=request.getContextPath()%>/imagenes/menu/buscarModelosGenerales.png"> 
						 Buscar modelos generados</a>	
					</div>
				</li>			
			</ul>
		</div>
		</div>
		</td>
	</tr>
<%}%>	
</table>
</body>
</html>
