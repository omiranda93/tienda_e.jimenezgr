<%-- 
    Document   : carrito
    Created on : 21-may-2014, 16:59:53
    Author     : Shkire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mi cesta</h1>
        <div><c:forEach var="prod" items="${carrito}">
                <div>
                    <%//muestra el producto%>
                    <a href="/tienda_e.jimenezgr/Indice/VerProducto"><img src="<c:out value="${prod}"/>"/></a>
                        <c:choose>
                            <c:when test="${prod.getCantidad()>0}">
                            <ul>
                                <span class="glyphicon glyphicon-ok"></span>
                                <li>En stock. Plazo de entrega 4-6 dias hábiles. Garantia 1 año.</li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <span class="glyphicon glyphicon-remove"></span>
                                <li>Sin stock. "Entrega en 3 semanas".</li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                    <a href="/tienda_e.jimenezgr/Indice/VerProducto"><c:out value="${prod.getNombre()}"/></a>
                    <c:out value="${prod.precio}"/>
                </div>
            </c:forEach>
            <div>Importe total:(¡Gastos de envío gratuitos!)
                <c:set var="total" value="${0}"></c:set>
                <c:forEach var="prod" items="${carro}">
                    <c:set var="total" value="${total+prod.precio}"></c:set>
                </c:forEach>
            </div>
            <a href="" class="btn btn-primary">
                Hacer mi pedido
            </a>
        </div>
    </body>
</html>
