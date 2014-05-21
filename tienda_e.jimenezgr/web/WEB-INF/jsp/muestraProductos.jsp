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
    <body class="container">
        <c:import url="cabecera.jsp" charEncoding="utf-8"/>
        <div id="contenido">
            <c:choose>
                <c:when test="${productosListados!=null}">
                    <form id="formOrdenarPor" method="get" action="">
                        <select id="ordenarPor" name="ordenarPor">
                            <option>Precio Creciente </option>
                            <option>Precio Decreciente </option>
                        </select>
                    </form>
                    <table>
                        <%//por cada producto en productosListados%>
                        <c:forEach var="prod" items="${productosListados}" varStatus="status">
                            <tr>
                                <%//muestra el producto%>
                                <td>${status.index()}</td>
                                
                                <td><a href="/tienda_e.jimenezgr/Indice/VerProducto?indice=${status.index}"><img src="<c:out value="${prod}"/>"/></a></td>
                                <td><a href="/tienda_e.jimenezgr/Indice/VerProducto?indice=${status.index}"><c:out value="${prod.getNombre()}"/></a></td>
                                <td><a href="/tienda_e.jimenezgr/Indice/VerProducto?indice=${status.index}"><c:out value="${prod.formateaPrecio(prod.getPrecio())}"/></a></td>                    
                            </tr>
                        </c:forEach>
                    </table>            
                </c:when>

                <c:otherwise>
                    <p>No hay productos para mostrar</p>
                </c:otherwise>
            </c:choose>
        </div>
        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </body>
</html>
