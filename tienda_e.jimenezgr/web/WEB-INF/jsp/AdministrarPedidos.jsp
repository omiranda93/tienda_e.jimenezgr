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
        <c:forEach var="pedido" items="${pedidosListados}" varStatus="contador">
            <c:if test="${pedido.estado != a && pedido.pendiente == false}">
                ${pedido.nomero}
                <form method="get" action='/tienda_e.jimenezgr/Administracion/EstadoPedido' >
                            <input type='text' name='estado' value='${pedido.estado}'>
                            <input type='submit' name='editar' value='Editar estado' onclick="alert('Has cambiado el estado del pedido ${pedido.numero}')">
                            <input type='hidden' name='contador' value='${contador.index}'>
                </form>
                <form method="get" action='/tienda_e.jimenezgr/Administracion/PedidoPreparado' >
                            <input type='text' name='estado' value='${pedido.estado}'>
                            <input type='submit' name='Preparado' value='Preparado' onclick="alert('El producto esta preparado y sera enviado')">
                            <input type='hidden' name='contador' value='${contador.index}'>
                </form>
            </c:if>
        </c:forEach>    
    </body>
</html>
