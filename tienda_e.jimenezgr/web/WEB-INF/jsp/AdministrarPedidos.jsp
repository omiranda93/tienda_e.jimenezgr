<%-- 
    Document   : AdministrarPedidos
    Created on : 22-may-2014, 13:00:08
    Author     : oscarmirandabravo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de pedidos</title>
    </head>
    <body>
        <c:set var="a" value="carrito" ></c:set>
        <c:forEach var="pedido" items="${pedidosListados}">
            <c:if test="${pedido.estado != a && pedido.pendiente == false}">
                ${pedido.numero}
            </c:if>
        </c:forEach>    
    </body>
</html>
