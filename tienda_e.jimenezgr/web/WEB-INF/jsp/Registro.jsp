<%-- 
    Document   : Registro
    Created on : 19-may-2014, 16:20:21
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
        <title>Poxmania: Venta Online de productos en Informática, Foto, Electrodomésticos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
    </head>
    <body>
        <div id="contenedor">
            <c:import url="cabecera.jsp" charEncoding="utf-8"/>
            <div id="formReg">
                <form method="post" action="/tienda_e.jimenezgr/Inicio/Registrarse">
                    <div class="row panel panel-default">

                        <h4 class="col-lg-1">
                            Tu login
                        </h4>
                        <div class="col-lg-10 col-lg-offset-1">
                            <p>
                                E-mail*
                                <input name="email" size="40" type="email" value="${email}" required>
                            </p>
                            <p>
                                Contraseña* 
                                <input name="password" size="40" type="password" required>
                            </p>
                            <p>
                                Confirmación de contraseña* 
                                <input name="password2" size="40" type="password" required>
                            </p>
                            <p>
                                Usuario* 
                                <input name="usuario" size="40" type="text" required>
                            </p>
                        </div>
                    </div>
                    <div class="row panel panel-default">
                        <h4 class="col-lg-1">
                            Datos personales
                        </h4>
                        <div class="col-lg-10 col-lg-offset-1">
                            <p>
                                Nombre y Apellidos* 
                                <input name="nomape" size="40" type="text" required>
                            </p>

                            <p>
                                Teléfono
                                <input name="telefono" size="40" type="text">
                            </p>
                            <p>
                                Dirección
                                <input name="direccion" size="40" type="text">
                            </p>
                            <p>
                                <input class="btn btn-primary" name="regBoton" type="submit" value="Continuar">
                            </p>
                        </div>
                    </div>
                </form>
            </div>
            <c:import url="footer.jsp" charEncoding="utf-8"/>
        </div>
    </body>
</html>
