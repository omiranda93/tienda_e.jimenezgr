<%-- 
    Document   : VerProducto
    Created on : 20-may-2014, 18:53:55
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
        <title>Ver ${producto.nombre}</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <div id="contenedor">
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>
            <div id="contenido">
                <h1><c:out value="${producto.nombre}"/></h1>
                <div id="infoProd">
                    <div id="infoSup" class="row">
                        <div id="imagenesProd" class="col-lg-3 panel panel-default">
                            <c:set var="imagenPrincipal" value="${null}"/>
                            <c:forEach var="img" items="${producto.productoTieneImagenCollection}">
                                <c:if test="${img.principal}">
                                    <c:set var="imagenPrincipal" value="${img.productoTieneImagenPK.uri}"/>
                                </c:if>        
                            </c:forEach>

                            <img class="row center-block" width="260px" src="<c:url value='${imagenPrincipal}'/>">
                            <div id="thumbs" class="row">                            
                                <c:forEach var="img" items="${producto.productoTieneImagenCollection}">
                                    <a href="#" class="thumbnail col-lg-3">
                                        <img src="<c:url value='${img.productoTieneImagenPK.uri}'/>">  
                                    </a>
                                </c:forEach>                               
                            </div>
                        </div>

                        <div id="precioProd" class="col-lg-6">  
                            <div class="col5">
                                <span class="precioL">         
                                    <script>
                                        var precio =${producto.precio};
                                        precio = formatoPrecio(precio);
                                        document.write(precio);
                                    </script>
                                </span>
                                <span class="precioS">
                                    IVA incluido
                                </span>
                            </div>                    

                            <div id="infoInf" class="row well center-block">
                                <h2>Descripción:</h2>
                                <p>${producto.descripcion }</p>
                            </div>
                        </div>

                        <div id="comprarProd" class="panel panel-default col-lg-3">
                            <a class="btn btn-link" href="/tienda_e.jimenezgr/Inicio/Carrito?nombreProd=${producto.nombre}">
                                <span class="glyphicon glyphicon-shopping-cart center-block"></span>
                                Añadir a la cesta 
                            </a>
                            <div class="availability">
                                <c:choose>
                                    <c:when test="${producto.cantidad>0}">
                                        <span class="glyphicon glyphicon-ok ok"></span>
                                        <span>En stock. Plazo de entrega 4-6 dias hábiles. Garantia 1 año.</span>
                                    </c:when>
                                    <c:otherwise>                                  
                                        <span class="glyphicon glyphicon-remove delete"></span>
                                        <span>Sin stock. "Entrega en 3 semanas".</span>
                                    </c:otherwise>
                                </c:choose>

                                <strong>
                                    <span>Sin gastos de envio</span>
                                </strong>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
