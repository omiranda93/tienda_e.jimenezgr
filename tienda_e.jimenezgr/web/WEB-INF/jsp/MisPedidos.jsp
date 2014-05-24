<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8″>
        <title>Mis pedidos</title>  
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <c:if  test="${usuario == null}"> 
            <%//redirige a index.jsp%>
            <c:redirect url="/Inicio"/>
        </c:if>
        <div id="contenedor">
            
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>
            
            
            
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
