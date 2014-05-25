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
        <script src="http://code.jquery.com/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
    </head>
    <body>
        <%//si el parametro admin esta vacio (no nos hemos identificado)%>
        <c:if  test="${admin == null}"> 
            <%//asigna a la variable error el valor correspondiente%>
            <c:set var="error" value="Debe loguearse como administrador para ver esta página" scope="session"/>
            <%//redirige a LogAdministrador.jsp%>
            <c:redirect url="/Administracion"/>
        </c:if>

        <div id="contenedor">

            <c:import url="menuAdmin.jsp" charEncoding="utf-8"/>
            <div class="row">
                <h3 class="panel panel-title blue text-center col-lg-6 col-lg-offset-3">Administrar pedidos</h3>
            </div>
            <div class="row">
                <h3>Cambiar estado o preparar pedido</h3>
                <c:set var="a" value="carrito" ></c:set>
                <c:forEach var="pedido" items="${pedidosListados}" varStatus="contador">
                    <c:if test="${pedido.estado != a && pedido.pendiente == false}">                        
                        <div class="panel panel-default">
                            <a data-toggle="collapse" data-parent="#accordion" href="#${contador.index}">
                                <h3 class="well">Pedido  <span class="blue">nº ${pedido.numero}</span></h3>
                            </a>
                            <div id="${contador.index}" class="row panel-collapse collapse">
                                <span class="col-lg-5">
                                    <form method="get" action='/tienda_e.jimenezgr/Administracion/EstadoPedido' >
                                        <b>Editar estado:</b>
                                        <input type='text' name='estado' value='${pedido.estado}'>
                                        <input type='submit' class="btn btn-primary" name='editar' value='Editar estado' onclick="alert('Has cambiado el estado del pedido ${pedido.numero}')">
                                        <input type='hidden' name='contador' value='${contador.index}'>
                                    </form>
                                </span>
                                <span class="col-lg-5">
                                    <form method="get" action='/tienda_e.jimenezgr/Administracion/PedidoPreparado' >
                                        <b>Preparar pedido:</b>
                                        <input type='text' name='estado' value='${pedido.estado}'>
                                        <input type='submit' class="btn btn-primary" name='Preparado' value='Preparado' onclick="alert('El producto esta preparado y sera enviado')">
                                        <input type='hidden' name='contador' value='${contador.index}'>
                                    </form>
                                </span>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>   
            </div>

        </div>
    </body>
</html>
