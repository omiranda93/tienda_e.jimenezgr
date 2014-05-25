<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Administración</title>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <%//si el parametro admin no esta vacio (estamos conectados como administrador)%>
        <c:if  test="${admin != null}">
            <%//redirige a Administrador%>
            <c:redirect url="Administracion/Administrador"/>
        </c:if>

        <%//si el parametro confirmacion no esta vacio%>
        <c:if test="${confirmacion!=null}">
            <%//lanza una alerta con la confirmacion%>
            <script>alert("${confirmacion}");</script>
            <%//elimina la confirmacion%>
            <c:set var="confirmacion" value="${null}" scope="session"/>           
        </c:if>

        <%//si el parametro error no esta vacio%>
        <c:if test="${error != null}">  
            <%//lanza una alerta con el error)%>
            <script>alert('${error}');</script>
            <%//elimina el error%>
            <c:set var="error" value="${null}" scope="session"/>
        </c:if>
        <div id="contenedor">
            <div id="header" class="row">
                <div class="col-lg-3">
                    <a href="/tienda_e.jimenezgr/Inicio" title="inicio"  >
                        <img src="<c:url value='/Recursos/poxmania-logo.png'/>">
                    </a>
                </div>


                <div class="col-lg-4 well col-lg-offset-1 text-center">
                    <h2>Entrar como Administrador</h2>
                </div>

                <span class="col-lg-3 col-lg-offset-1 text-center">
                    <a class="btn btn-primary" href="/tienda_e.jimenezgr/Inicio">Volver a inicio</a>
                </span>
            </div>


            <div class="row">
                <form class="panel panel-default col-lg-6 col-lg-offset-3" name="formLog"  method="POST" action="/tienda_e.jimenezgr/Administracion/Autenticacion">
                    <div class="well text-center">
                        <h3>Iniciar sesión</h3>
                        <p class="row">
                            <span class="col-lg-4">Usuario</span>

                            <input class="col-lg-6" type="text" name="usuario" maxlength="20" required>
                        </p>
                        <p class="row">
                            <span class="col-lg-4">Contraseña</span>

                            <input class="col-lg-6" type="password" name="pwd" maxlength="15" required>
                        </p>
                        <input class="btn btn-primary row col-lg-offset-4" type='submit' class="btn btn-primary" value='Entrar'>
                    </div>
                </form>
            </div>

        </div>
    </body>
</html>
