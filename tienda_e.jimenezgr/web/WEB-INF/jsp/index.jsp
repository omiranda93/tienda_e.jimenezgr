<%-- 
    Document   : index
    Created on : 19-may-2014, 16:37:30
    Author     : eduas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8″>
        <title>Poxmania: Venta Online de productos en Informática, Foto, Electrodomésticos</title>  
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        <script src="http://code.jquery.com/jquery-1.7.2.js"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>

    <body>
        <div id="contenedor">
            <%//si la sesion es nueva%>
            <c:if test="${pageContext.session.new}">
                <%//establece el tiempo maximo de inactividad en 30 minutos%>
                <c:set target="${pageContext.session}" property="maxInactiveInterval" value="1800" />
            </c:if>


            <c:import url="cabecera.jsp" charEncoding="utf-8"/>

            <div>
                <!--Carousel-->
                <div id="sidebar-carousel-1" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators grey">
                        <li data-target="#sidebar-carousel-1" data-slide-to="0" class="active"></li>
                        <li data-target="#sidebar-carousel-1" data-slide-to="1"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item active">
                            <a href="" data-lightbox="image-1" title="">
                                <img src="<c:url value='/Recursos/anuncio1.jpg'/>">
                            </a>
                        </div>
                        <div class="item">
                            <a href="" data-lightbox="image-2" title="">
                                <img src="<c:url value='/Recursos/anuncio2.gif'/>">
                            </a>                  
                        </div>
                    </div>
                    <!-- Controls -->
                    <a class="left carousel-control" href="#sidebar-carousel-1" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#sidebar-carousel-1" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div><!--/Carousel-->
            </div>

            <div id="prodAleatorios" class="row">
                <h2 class="row">Algunos alguno de nuestros productos:</h2>
                <div class="panel panel-default row">
                    <c:forEach var="prod" items="${productosAleatorios}" varStatus="indice">
                        <div class="col-lg-2">
                            <c:set var="imagenPrincipal" value="${null}"/>
                            <c:forEach var="img" items="${prod.productoTieneImagenCollection}">
                                <c:if test="${img.principal}">
                                    <c:set var="imagenPrincipal" value="${img.productoTieneImagenPK.uri}"/>
                                </c:if>           

                            </c:forEach>
                            <a class="row" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}"><img width="150px" src="<c:url value='${imagenPrincipal}'/>"></a>
                            <a class="row" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}">${prod.nombre}</a>
                            <a class="row" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}">
                                <script>
                                    var precio =${prod.precio};
                                    precio = formatoPrecio(precio);
                                    document.write(precio);
                                </script>

                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
