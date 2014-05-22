<%-- 
    Document   : LogAdministrador
    Created on : 22-may-2014, 9:28:28
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
        <title>Login Administración</title>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        
    </head>
    <body>
         <%//si el parametro usuario no esta vacio (estamos conectados como administrador)%>
        <c:if  test="${usuario != null}">
            <%//redirige a paginaAdministracion%>
            <c:redirect url="/Administracion/Administrador"/>
        </c:if>

        <%//si el parametro error no esta vacio%>
        <c:if test="${error != null}">  
            <%//lanza una alerta con el error)%>
            <script>alert('${error}');</script>
            <%//elimina el error%>
            <c:set var="error" value="${null}" scope="session"/>
        </c:if>

        <%//inicia sesion;
          //Formulario de tipo post que llama a ServletLogin con parametro login=true y el usuario y contrasenia
          //tambien como parametro. Valida que los campos esten bien rellenados con la funcion validar_camposLogin() del js%>
        <form name="formLog"  method="POST" action="/tienda_e.jimenezgr/Administracion/Autenticacion" onSubmit="return validar_camposLogin()">
            <table align="center" width="200px">
                <tr>
                    <td colspan="2" align="center"><h3>Iniciar sesión</h3></td>
                </tr>

                <tr>
                    <td>Usuario</td>
                    <td>
                        <input type="text" name="usuario" maxlength="20" value="poxmaniaco">
                    </td>
                </tr>

                <tr>
                    <td>Password</td>
                    <td>
                        <input type="password" name="pwd" maxlength="15" value="nosomospixmania">
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="right">
                        <input type='submit' value='Entrar'>
                    </td>

                </tr>
            </table>
        </form>
        <a class="boton" href="/tienda_e.jimenezgr/Inicio">Página de poxmania</a>
    </body>
</html>
