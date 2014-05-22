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
            <h1>Mi cesta</h1>
            <div>
                <c:choose>
                    <c:when test="${carrito!=null}">
                        <c:forEach var="prod" items="${carrito.registroPedidosCollection}">
                            <div>
                                <%//muestra el producto%>
                                <c:set var="imagenPrincipal" value="${null}"/>
                                <c:forEach var="img" items="${prod.producto1.productoTieneImagenCollection}">
                                    <c:if test="${img.principal}">
                                        <c:set var="imagenPrincipal" value="${img.productoTieneImagenPK.uri}"/>
                                    </c:if>          

                                </c:forEach>
                                <a href="/tienda_e.jimenezgr/Indice/VerProducto"><img width="80px" src="<c:url value='${imagenPrincipal}'/>"></a>
                                    <c:choose>
                                        <c:when test="${prod.producto1.cantidad>0}">
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
                                <a href="/tienda_e.jimenezgr/Indice/VerProducto"><c:out value="${prod.producto1.getNombre()}"/></a>
                                <span>         
                                    <script>
                                        var precio =${prod.producto1.precio};
                                        precio = formatoPrecio(precio);
                                        document.write(precio);
                                    </script>
                                </span>
                            </div>
                        </c:forEach>
                        <div>Importe total:(¡Gastos de envío gratuitos!)
                            <c:set var="total" value="${carrito.precioTotal()}"></c:set>
                            <span>         
                                <script>
                                    var precio =${total};
                                    precio = formatoPrecio(precio);
                                    document.write(precio);
                                </script>
                            </span>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <span class="well">No hay productos en la cesta</span>
                    </c:otherwise>
                </c:choose>



                <a href="" class="btn btn-primary">
                    Hacer mi pedido
                </a>
            </div>
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
