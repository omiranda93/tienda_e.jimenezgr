<%-- 
    Document   : muestraProductos
    Created on : 19-may-2014, 23:25:53
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
    </head>
    <body>
        <c:import url="cabecera.jsp" charEncoding="utf-8"/>

        <table>
            <%//por cada producto en productosListados%>
            <c:forEach var="prod" items="${productosListados}" varStatus="status">
                <tr>
                    <%//muestra el producto%>
                    <td>${status.index()}</td>
                    <td><a href="<%--verProducto.jsp--%>"><img src="<c:out value="${prod.getImagen()}"/>"/></a></td>
                    <td><a href="<%--verProducto.jsp--%>"><c:out value="${prod.getNombre()}"/></a></td>
                    <td><a href="<%--verProducto.jsp--%>"><c:out value="${prod.formateaPrecio(prod.getPrecio())}"/></a></td>                    
                </tr>
            </c:forEach>
        </table>

        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </body>
</html>
