<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/estilos.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/utils.js"></script>

<style type="text/css">
div.sdmenu {
	width: 120px;
	font-family: Arial, sans-serif;
	font-size: 11px;
	padding-bottom: 1px;
	background: url(../imagenes/tab.gif) no-repeat right bottom;
	color: #fff;
}

div.sdmenu div {
	background: url(../imagenes/tab.gif) repeat-x;
	overflow: hidden;
}

div.sdmenu div:first-child {
	background: url(../imagenes/tab.gif) no-repeat;
}

div.sdmenu div.collapsed {
	height: 22px;
}

div.sdmenu div span {
	display: block;
	padding: 5px 25px;
	font-weight: bold;
	color: white;
	/*background:
		url(http://img5.xooimage.com/files/e/3/b/expanded-176e2f.gif)
		no-repeat 10px center;*/
	cursor: default;
	/* 	border-bottom: 1px solid #ddd; */
}

div.sdmenu div.collapsed span { /*background-image:
		url(http://img5.xooimage.com/files/e/b/a/collapsed-176e2c.gif); */
	
}

div.sdmenu div a {
	padding: 5px 10px;
	background: #eee;
	display: block;
	border-bottom: 1px solid #ddd;
	color: #3366CC;
}

div.sdmenu div a.current {
	background: #ccc;
}

div.sdmenu div a:hover {
	background: #FBEE99;
	text-decoration: none;
}

div.sdmenu div {
	font-size: 11px;
	text-align: center;
}

div.sdmenu div a img {
	display: block;
	border: 0px;
	margin: auto;
	width: 45px;
	height: 45px;
}
</style>


<script type="text/javascript">
	function SDMenu(id) {
		if (!document.getElementById || !document.getElementsByTagName)
			return false;
		this.menu = document.getElementById(id);
		this.submenus = this.menu.getElementsByTagName("div");
		this.remember = true;
		this.speed = 5;
		this.markCurrent = true;
		this.oneSmOnly = true;
	}
	SDMenu.prototype.init = function() {
		var mainInstance = this;
		for ( var i = 0; i < this.submenus.length; i++)
			this.submenus[i].getElementsByTagName("span")[0].onclick = function() {
				mainInstance.toggleMenu(this.parentNode);
			};
		if (this.markCurrent) {
			var links = this.menu.getElementsByTagName("a");
			for ( var i = 0; i < links.length; i++)
				if (links[i].href == document.location.href) {
					links[i].className = "current";
					break;
				}
		}
		if (this.remember) {
			var regex = new RegExp("sdmenu_" + encodeURIComponent(this.menu.id)
					+ "=([01]+)");
			var match = regex.exec(document.cookie);
			if (match) {
				var states = match[1].split("");
				for ( var i = 0; i < states.length; i++)
					this.submenus[i].className = (states[i] == 0 ? "collapsed"
							: "");
			}
		}
	};
	SDMenu.prototype.toggleMenu = function(submenu) {
		if (submenu.className == "collapsed")
			this.expandMenu(submenu);
		else
			this.collapseMenu(submenu);
	};
	SDMenu.prototype.expandMenu = function(submenu) {
		var fullHeight = submenu.getElementsByTagName("span")[0].offsetHeight;
		var links = submenu.getElementsByTagName("a");
		for ( var i = 0; i < links.length; i++)
			fullHeight += links[i].offsetHeight;
		var moveBy = Math.round(this.speed * links.length);

		var mainInstance = this;
		var intId = setInterval(function() {
			var curHeight = submenu.offsetHeight;
			var newHeight = curHeight + moveBy;
			if (newHeight < fullHeight)
				submenu.style.height = newHeight + "px";
			else {
				clearInterval(intId);
				submenu.style.height = "";
				submenu.className = "";
				mainInstance.memorize();
			}
		}, 30);
		this.collapseOthers(submenu);
	};
	SDMenu.prototype.collapseMenu = function(submenu) {
		var minHeight = submenu.getElementsByTagName("span")[0].offsetHeight;
		var moveBy = Math.round(this.speed
				* submenu.getElementsByTagName("a").length);
		var mainInstance = this;
		var intId = setInterval(function() {
			var curHeight = submenu.offsetHeight;
			var newHeight = curHeight - moveBy;
			if (newHeight > minHeight)
				submenu.style.height = newHeight + "px";
			else {
				clearInterval(intId);
				submenu.style.height = "";
				submenu.className = "collapsed";
				mainInstance.memorize();
			}
		}, 30);
	};
	SDMenu.prototype.collapseOthers = function(submenu) {
		if (this.oneSmOnly) {
			for ( var i = 0; i < this.submenus.length; i++)
				if (this.submenus[i] != submenu
						&& this.submenus[i].className != "collapsed")
					this.collapseMenu(this.submenus[i]);
		}
	};
	SDMenu.prototype.expandAll = function() {
		var oldOneSmOnly = this.oneSmOnly;
		this.oneSmOnly = false;
		for ( var i = 0; i < this.submenus.length; i++)
			if (this.submenus[i].className == "collapsed")
				this.expandMenu(this.submenus[i]);
		this.oneSmOnly = oldOneSmOnly;
	};
	SDMenu.prototype.collapseAll = function() {
		for ( var i = 0; i < this.submenus.length; i++)
			if (this.submenus[i].className != "collapsed")
				this.collapseMenu(this.submenus[i]);
	};
	SDMenu.prototype.memorize = function() {
		if (this.remember) {
			var states = new Array();
			for ( var i = 0; i < this.submenus.length; i++)
				states.push(this.submenus[i].className == "collapsed" ? 0 : 1);
			var d = new Date();
			d.setTime(d.getTime() + (30 * 24 * 60 * 60 * 1000));
			document.cookie = "sdmenu_" + encodeURIComponent(this.menu.id)
					+ "=" + states.join("") + "; expires=" + d.toGMTString()
					+ "; path=/";
		}
	};
</script>



<script type="text/javascript">
	//          
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	//
</script>
</head>
<body>

	<div id="my_menu" class="sdmenu">

		<div>
			<span>General</span> <a
				href="<%=request.getContextPath()%>/gestionarModelos.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/gestionarModelos.png">
				Gestionar modelos</a> <a href="#"
				onclick="loadPage('general/transmitirTramasProduccion.html');">
				<img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/transmitir.png">
				Transmitir tramas en producción</a> <a
				href="<%=request.getContextPath()%>/asociarModelo.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/asociarModeloEquifax.png">
				Asociar modelo Cliente a Equifax </a> <a
				href="<%=request.getContextPath()%>/asociarModeloEquifax.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/asociarModelo.png">
				Asociar modelo Equifax a Cliente</a>
		</div>

		<div class="collapsed">
			<span>Ejecutor</span> <a
				href="<%=request.getContextPath()%>/selConAtr.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/seleccionarConceptosAtributos.png">
				Seleccionar conceptos y atributos </a> <a
				href="<%=request.getContextPath()%>/traTraEje.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/transmitir.png">
				Transmitir Trama </a>


		</div>
		<div class="collapsed">
			<span>Crediticio</span> <a
				href="<%=request.getContextPath()%>/selCon.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/seleccionarConceptos.png">
				Seleccionar conceptos </a> <a
				href="<%=request.getContextPath()%>/traTraRc.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/transmitir.png">
				Transmitir Trama </a>
		</div>
		<div class="collapsed">
			<span>Administración</span> <a
				href="<%=request.getContextPath()%>/gestionarConceptos.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/gestionarConceptos.png" />
				Gestionar conceptos y atributos </a> <a
				href="<%=request.getContextPath()%>/gestionarClientes.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/gestionarClientes.png" />
				Gestionar clientes</a> <a
				href="<%=request.getContextPath()%>/aprobarModelos.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/aprobarModelos.png" />
				Aprobar modelos</a> <a
				href="<%=request.getContextPath()%>/his.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/historialModelos.png">
				Historial de modelos </a> <a
				href="<%=request.getContextPath()%>/auditoria.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/auditoria.png" />
				Auditoria </a>


		</div>





		<div class="collapsed">
			<span>Reportes</span> <a
				href="<%=request.getContextPath()%>/repConAtr.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/reporteConceptoAtributos.png" />
				Reporte de Concepto y Atributos </a> <a
				href="<%=request.getContextPath()%>/repDicGen.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/reporteDiccionarioGeneral.png">
				Reporte del Diccionario en general </a> <a
				href="<%=request.getContextPath()%>/repModGen.do?metodo=cargarAction"
				target="FraMain"> <img alt=""
				src="<%=request.getContextPath()%>/imagenes/menu/buscarModelosGenerales.png">
				Buscar modelos generados </a>

		</div>

	</div>



</body>
</html>