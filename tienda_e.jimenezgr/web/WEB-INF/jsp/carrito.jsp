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
        <title>Mi cesta</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <div id="contenedor">
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>
            <div class="row">
                <div class="row"><h1>Mi cesta</h1></div>
                <div class="row">
                    <div class="col-lg-9">
                        <c:choose>
                            <c:when test="${carrito!=null}">
                                <c:forEach var="prod" items="${carrito.registroPedidosCollection}">
                                    <div class="row panel panel-default">
                                        <%//imagen principal%>
                                        <c:set var="imagenPrincipal" value="${null}"/>
                                        <c:forEach var="img" items="${prod.producto1.productoTieneImagenCollection}">
                                            <c:if test="${img.principal}">
                                                <c:set var="imagenPrincipal" value="${img.productoTieneImagenPK.uri}"/>
                                            </c:if>
                                        </c:forEach>
                                        <a class="col-lg-2" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.producto1.getNombre()}"><img width="80px" src="<c:url value='${imagenPrincipal}'/>"></a>

                                        <%//nombrec stock y cantidad%>
                                        <div class="col-lg-4">
                                            <a class="nombreS" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.producto1.getNombre()}"><c:out value="${prod.producto1.getNombre()}"/></a>
                                            <c:choose>
                                                <c:when test="${prod.producto1.cantidad>0}">
                                                    <div class="row">
                                                        <span class="glyphicon glyphicon-ok ok"></span>
                                                        <span>En stock. Plazo de entrega 4-6 dias hábiles. Garantia 1 año.</span>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="row">
                                                        <span class="glyphicon glyphicon-remove delete"></span>
                                                        <span>Sin stock. "Entrega en 3 semanas".</span>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <p class="row"><b>Cantidad: </b>${prod.cantidad}</p>
                                            <p class="row"><b>Modificar cantidad: </b></p>
                                            <form method="get" action="/tienda_e.jimenezgr/Inicio/CarritoNuevaCantidad">
                                                <input type="number" min="1" max="${prod.producto1.cantidad}" name="cantidad" value="${prod.cantidad}">
                                                <input type="hidden" name="nombreProd" value="${prod.producto1.nombre}">
                                                <input type="submit" name="actualizar" value="Actualizar cantidad">
                                            </form>
                                            <a href="/tienda_e.jimenezgr/Inicio/CarritoNuevaCantidad?cantidad=0&nombreProd=${prod.producto1.nombre}" class="row"><span class="glyphicon glyphicon-remove delete"></span>Eliminar</a>

                                        </div>
                                        <div class="col-lg-3 col-lg-offset-3">      
                                            <span class="precioM">         
                                                <script>
                                                            var precio =${prod.producto1.precio};
                                                            precio = formatoPrecio(precio);
                                                            document.write(precio);
                                                </script>
                                            </span>
                                            <span class="precioS">IVA incluido</span>
                                        </div>
                                    </div>                                 
                                </c:forEach>

                                <div class="row panel panel-default">
                                    <div class="col-lg-5">
                                        <b>Importe total:</b>(¡Gastos de envío gratuitos!)
                                    </div>
                                    <c:set var="total" value="${carrito.precioTotal()}"></c:set>
                                        <div class="col-lg-4 col-lg-offset-3">
                                            <span class="precioL">         
                                                <script>
                                                    var precio =${total};
                                                    precio = formatoPrecio(precio);
                                                    document.write(precio);
                                            </script>
                                        </span>
                                        <span class="precioS">IVA incluido</span>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <span class="well">No hay productos en la cesta</span>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="col-lg-3 panel panel-default">
                        <a href="" class="btn btn-primary row center-block">Hacer mi pedido</a>
                        <div class="col-lg-5">
                            <b>Importe total: </b>
                        </div>

                        <div class="col-lg-6 col-lg-offset-1">
                            <span class="precioM"><script>document.write(precio);</script></span>
                            <span class="precioS">IVA incluido</span>
                        </div>
                        <div class="row col-lg-11 col-lg-offset-1">(gastos de envío gratis)</div>
                    </div>
                </div>
            </div>

            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
