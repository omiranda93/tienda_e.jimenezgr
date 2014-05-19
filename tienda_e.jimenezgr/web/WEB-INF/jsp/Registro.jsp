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
    </head>
    <body>
        <div id="formReg">
                <form method="post" action="/ControlerLogin">
                    <p>
                        <h4>
                            Tu login
                        </h4>
                    </p>
                    <p>
                        E-mail*
                        <input id="email" type="email" value="${email}" required>
                    </p>
                    <p>
                        Contraseña* 
                        <input id="password" type="password" required>
                    </p>
                    <p>
                        Confirmación de contraseña* 
                        <input id="password" type="password" required>
                    </p>
                    <p>
                        Nombre y Apellidos* 
                        <input id="Nombre" type="text" required>
                    </p>
                    <p>
                        Usuario* 
                        <input id="Usuario" type="text" required>
                    </p>
                    <p>
                        Teléfono
                        <input id="Telefono" type="text">
                    </p>
                    <p>
                        Dirección
                        <input id="Dirección" type="text">
                    </p>
                    <p>
                        <intput id="regBoton" type="submit" value="Continuar">
                    </p>
                </form>
            </div>
                    <label></label>
    </body>
</html>
