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
        <meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8″>
        <title>Productos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <div id="contenedor">
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>
            <div id="productosListados" class="col-lg-9 col-lg-offset-3">
                <c:choose>
                    <c:when test="${productosListados.size()>0}">
                        <form id="formOrdenarPor" method="get" action="">
                            <select id="ordenarPor" name="ordenarPor" class="col-lg-offset-10">
                                <option>Precio Creciente </option>
                                <option>Precio Decreciente </option>
                            </select>
                        </form>

                        <%//por cada producto en productosListados%>
                        <c:forEach var="prod" items="${productosListados}" varStatus="status">
                            <div class="row panel panel-default">
                                <%//muestra el producto%>
                                <c:set var="imagenPrincipal" value="${null}"/>
                                <c:forEach var="img" items="${prod.productoTieneImagenCollection}">
                                    <c:if test="${img.principal}">
                                        <c:set var="imagenPrincipal" value="${img.productoTieneImagenPK.uri}"/>
                                    </c:if>           

                                </c:forEach>

                                <a class="col-lg-2" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}"><img width="100px" src="<c:url value='${imagenPrincipal}'/>"></a>
                                <a class="col-lg-2" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}"><c:out value="${prod.getNombre()}"/></a>
                                <a class="col-lg-3 col-lg-offset-2" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}">
                                    <span>         
                                        <script>
                                            var precio =${prod.precio};
                                            precio = formatoPrecio(precio);
                                            document.write(precio);
                                        </script>
                                    </span>
                                    <div id="estado">
                                        <c:choose>
                                            <c:when test="${prod.cantidad>0}">
                                                <span class="glyphicon glyphicon-ok"></span>
                                                <span>En stock</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="glyphicon glyphicon-remove"></span>
                                                <span>Sin stock. "Entrega en 3 semanas".</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </a> 
                            </div>
                        </c:forEach>



                    </c:when>

                    <c:otherwise>
                        <p class="well">No hay productos para mostrar</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
