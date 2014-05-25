<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8″>
        <title>Mis pedidos</title>  
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
    </head>
    <body>
        <c:if  test="${usuario == null}"> 
            <%//redirige a index.jsp%>
            <c:redirect url="/Inicio"/>
        </c:if>
        <div id="contenedor">

            <c:import url="cabecera.jsp" charEncoding="utf-8"/>
            <div>
                <c:forEach var="pedido" items="${pedidosUser}">                    
                    <c:if test="${pedido.estado != 'Carrito'}">
                        <div class="well">
                            <h3>Pedido nº:${pedido.numero}</h3>
                            <c:forEach var="prod" items="${pedido.registroPedidosCollection}">
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
                                        <p class="row"><b>Cantidad: </b>${prod.cantidad}</p>
                                    </div>
                                    <div class="col-lg-3 col-lg-offset-3">      
                                        <span class="precioS">         
                                            <script>
                                                var precio =${prod.producto1.precio};
                                                precio = formatoPrecio(precio);
                                                document.write(precio);
                                            </script>
                                        </span>
                                        <span class="precioS">IVA incluido</span>
                                        <div class="nombreS">
                                            x ${prod.cantidad} =     
                                            <span class="precioS">
                                                <script>
                                                    var precio =${prod.producto1.precio};
                                                    precio *= ${prod.cantidad};
                                                    precio = formatoPrecio(precio);
                                                    document.write(precio);
                                                </script>
                                            </span>
                                        </div>
                                    </div>
                                </div>                                 
                            </c:forEach>

                            <div class="row">
                                <span class="nombreL col-lg-2 col-lg-offset-7">Total: </span>
                                <c:set var="total" value="${pedido.precioTotal()}"></c:set>
                                    <div class="col-lg-3">
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
                        </div>
                    </c:if>
                </c:forEach>
            </div>


            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
