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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poxmania: Venta Online de productos en Informática, Foto, Electrodomésticos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
    </head>
    <body class="container">
        <c:import url="cabecera.jsp" charEncoding="utf-8"/>
        <div id="formReg">
            <form method="post" action="/ControlerLogin">
                <p>
                <h4>
                    Tu login
                </h4>
                </p>
                <p>
                    E-mail*
                    <input name="email" type="email" value="${email}" required>
                </p>
                <p>
                    Contraseña* 
                    <input name="password" type="password" required>
                </p>
                <p>
                    Confirmación de contraseña* 
                    <input name="password" type="password" required>
                </p>
                <p>
                    Nombre y Apellidos* 
                    <input name="Nombre" type="text" required>
                </p>
                <p>
                    Usuario* 
                    <input name="Usuario" type="text" required>
                </p>
                <p>
                    Teléfono
                    <input name="Telefono" type="text">
                </p>
                <p>
                    Dirección
                    <input name="Dirección" type="text">
                </p>
                <p>
                    <input name="regBoton" type="submit" value="Continuar">
                </p>
            </form>
        </div>
        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </body>
</html>
