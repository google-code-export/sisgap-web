<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<%@ page  language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Parametrizador Web</title>

<script language="javascript">
function cerrarSesion() {
	var form;
	
	if(opener != null) {
		form = opener.document.forms[0];
		form.action = "<%=request.getContextPath()%>/login.do";
	}
	else {
		form = document.cerrarSesionForm;
	}
	
	if(form.metodo != null) {
		form.metodo.value = "cerrarSesion";
	}
	else {
		form.action = form.action + '?metodo=cerrarSesion';
	}
	form.target = "_parent";
	form.submit();
	if(opener != null) {
		close();
	}
	
}
</script>
</head>
<body onload="cerrarSesion()">
<form method="post" name="cerrarSesionForm" action="<%=request.getContextPath()%>/login.do">
<input type="hidden" name="metodo" />
</form>
</body>
</html>
