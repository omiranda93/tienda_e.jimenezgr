<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de pedidos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <%//si el parametro admin esta vacio (no nos hemos identificado)%>
        <c:if  test="${admin == null}"> 
            <%//asigna a la variable error el valor correspondiente%>
            <c:set var="error" value="Debe loguearse como administrador para ver esta página" scope="session"/>
            <%//redirige a login.jsp%>
            <c:redirect url="/Administracion"/>
        </c:if>

        <c:import url="menuAdmin.jsp" charEncoding="utf-8"/>

        <c:set var="a" value="carrito" ></c:set>
        <c:forEach var="pedido" items="${pedidosListados}" varStatus="contador">
            <c:if test="${pedido.estado != a && pedido.pendiente == false}">
                ${pedido.numero}
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
