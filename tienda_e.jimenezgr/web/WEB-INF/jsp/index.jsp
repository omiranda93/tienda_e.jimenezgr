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
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
        <script src="http://code.jquery.com/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
    </head>

    <body>

        <div id="contenedor">
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>            

            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                </ol>

                <!-- Carousel items -->
                <div class="carousel-inner">
                    <div class="active item">
                        <img src="<c:url value='/Recursos/anuncio1.jpg'/>">
                    </div>
                    <div class="item">
                        <img src="<c:url value='/Recursos/anuncio2.gif'/>">
                    </div>

                </div>

                <!-- Carousel nav -->
                <a class="carousel-control left" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>    
            </div>
            <script>
                $(document).ready(function() {
                    $('.carousel').carousel()
                });
            </script>



            <div id="prodAleatorios" class="row">
                <h2 class="row">Algunos de nuestros productos:</h2>
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
                            <a class="nombreS" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}"><small>${prod.nombre}</small></a><br>
                            <a class="precioM" href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}">
                                <script>
                                    var precio =${prod.precio};
                                    precio = formatoPrecio(precio);
                                    document.write(precio);
                                </script>

                            </a><br>
                            <a href="/tienda_e.jimenezgr/Inicio/VerProducto?nombre=${prod.getNombre()}">
                                <c:choose>
                                    <c:when test="${prod.cantidad>0}">
                                        <span class="glyphicon glyphicon-ok ok"></span>
                                        <span class="text-info">En stock.</span>
                                    </c:when>
                                    <c:otherwise>                                  
                                        <span class="glyphicon glyphicon-remove delete"></span>
                                        <span class="text-info">Sin stock. "Entrega en 3 semanas".</span>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
