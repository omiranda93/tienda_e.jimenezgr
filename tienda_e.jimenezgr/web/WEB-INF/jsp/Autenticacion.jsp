<%-- 
    Document   : Autenticacion
    Created on : 19-may-2014, 15:33:24
    Author     : oscarmirandabravo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv=”Content-Type” content=”text/html; charset=UTF-8″>
        <title>Mi cuenta Poxmania y yo - Poxmania</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
    </head>
    <body>
        <div id="contenedor">
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>

            <div id = "registro" class="row">
                <div id="formLog" class="panel panel-default col-lg-5">
                    <form name="formLog"  method="post" action="/tienda_e.jimenezgr/Login/Logueo" onsubmit="validar_camposLogin()">
                        <p class="row">
                            <strong>¿Eres YA CLIENTE de Poxmania? </strong>
                        </p>
                        <p class="row">
                            Introduce tus identificantes por favor: 
                        </p>
                        <p class="row">
                            <span class="col-lg-4">Tu usuario</span>
                            <input class="col-lg-6" name="usuario" type="text" placeholder="Tu usuario" value="" required>
                        </p>
                        <p class="row">
                            <span class="col-lg-4">Tu contraseña</span>
                            <input class="col-lg-6" name="pwd" type="password" placeholder="Tu contraseña " required>
                        </p>
                        <input class="btn btn-primary row col-lg-offset-4" name="logBoton" type="submit" value="Continuar">
                    </form>
                </div>

                <div id ="formRegistro" class="panel panel-default col-lg-5 col-lg-offset-1">
                    <form method="get" action="/tienda_e.jimenezgr/Inicio/Registro">
                        <p class="row">
                            <strong>¿Eres un NUEVO cliente? </strong>
                        </p>
                        <p class="row">
                            Vamos a proceder a la creación de tu cuenta     
                        </p>
                        <p class="row">
                            <span class="col-lg-4">Tu dirección de correo electrónico</span>
                            <input class="col-lg-6" name="email" type="email" placeholder="Tu dirección de correo electrónico" required>
                        </p>
                        <input class="btn btn-primary row col-lg-offset-4" name="RegistroBoton" type="submit" value="Como crear una cuenta">
                    </form>
                </div>
            </div>
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
