<%-- 
    Document   : index
    Created on : 19-may-2014, 16:37:30
    Author     : eduas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poxmania: Venta Online de productos en Informática, Foto, Electrodomésticos</title>    </head>
    <body>
        <%//si la sesion es nueva%>
        <c:if test="${pageContext.session.new}">
            <%//establece el tiempo maximo de inactividad en 30 minutos%>
            <c:set target="${pageContext.session}" property="maxInactiveInterval" value="1800" />
        </c:if>
        <c:import url="cabecera.jsp" charEncoding="utf-8"/>

    </body>
</html>
