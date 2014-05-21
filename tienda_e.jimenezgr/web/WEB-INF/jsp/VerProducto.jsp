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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
    </head>
    <body class="container">
        
        <c:import url="cabecera.jsp" charEncoding="utf-8"/>
        <div id="contenido">
            <h1><c:out value="${producto.nombre}"/></h1>
            <div id="infoProd">
                <div id="infoSup">
                    <div class="col4">
                        ${producto}
                    </div>
                    <div class="col5">
                        ${producto.precio}
                    </div>
                    <div class="col12">
                        <p>Información de entrega:</p>
                        <c:choose>
                            <c:when test="${producto.cantidad>0}">
                                <ul>
                                    <li>En stock. Plazo de entrega 4-6 dias hábiles. Garantia 1 año.</li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <ul>
                                    <li>"Entrega en 3 semanas".</li>
                                </ul>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div>

                        <a>
                            <span class="glyphicon glyphicon-shopping-cart"></span>
                            Añadir a la cesta 
                        </a>
                        <div class="availability">
                            <c:choose>
                                <c:when test="${producto.cantidad>0}">
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

                            <strong>
                                Sin gastos de envio
                            </strong>
                        </div>
                    </div>
                </div>
                <div id="infoInf">
                    <h2>Descripción:</h2>
                    <p>${producto.descripcion }</p>
                </div>
            </div>
        </div>
                
        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </body>
</html>
