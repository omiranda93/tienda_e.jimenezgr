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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poxmania: Venta Online de productos en Informática, Foto, Electrodomésticos</title>    </head>
    <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
    <body class="container">
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
            </div><!--/Carousel--></div>

        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </body>
</html>
