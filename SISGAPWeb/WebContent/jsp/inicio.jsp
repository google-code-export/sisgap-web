<html>
<head>
<title>Mercado Modelo de Huaral - SISGAP</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<frameset border="0" cols="124,*" frameborder="no" framespacing="0">

<%
String ua = request.getHeader( "User-Agent" );
boolean isFirefox = ( ua != null && ua.indexOf( "Firefox/" ) != -1 );
boolean isMSIE = ( ua != null && ua.indexOf( "MSIE" ) != -1 );
response.setHeader( "Vary", "User-Agent" );
%>


<% if( isFirefox ){ %>
  <frame name="FraMenu" src="<%=request.getContextPath()%>/jsp/menuFirefox.jsp" scrolling="no" noresize >
<% }else{ %>
  <frame name="FraMenu" src="<%=request.getContextPath()%>/jsp/menu.jsp" scrolling="no" noresize >
<% } %>
  <frameset rows="69,*">
    <frame name="FraSuperior" src="<%=request.getContextPath()%>/jsp/cabecera.jsp" scrolling="no" noresize>
    <frame name="FraMain" src="<%=request.getContextPath()%>/jsp/bienvenida.jsp" scrolling="auto">
  </frameset>
</frameset>
</html>
