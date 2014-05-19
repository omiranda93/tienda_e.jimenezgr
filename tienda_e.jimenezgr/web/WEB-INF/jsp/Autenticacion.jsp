<%-- 
    Document   : Autenticacion
    Created on : 19-may-2014, 15:33:24
    Author     : oscarmirandabravo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mi cuenta Poxmania y yo - Poxmania</title>
    </head>
    <body>

        <div id = "registro">
            <div id="formLog">
                <form method="post" action="/ControlerLogin">
                    <p>
                        <strong>¿Eres YA CLIENTE de Poxmania? </strong>
                    </p>
                    <p>
                        Introduce tus identificantes por favor: 
                    </p>
                    <p>
                        Tu dirección de correo electrónico
                        <input id="email" type="email" placeholder="Tu dirección de correo electrónico" value="" required>
                    </p>
                    <p>
                        Tu contraseña 
                        <input id="password" type="password" placeholder="Tu contraseña " required>
                    </p>
                    <p>
                        <intput id="logBoton" type="submit" value="Continuar">
                    </p>
                </form>
            </div>
            <div id ="formRegistro">
                <form method="post" action="/Registro.jsp">
                    <p>
                        <strong>¿Eres un NUEVO cliente? </strong>
                    </p>
                    <p>
                        Vamos a proceder a la creación de tu cuenta     
                    </p>
                    <p>
                        Tu dirección de correo electrónico
                        <input name="email" type="text" placeholder="Tu dirección de correo electrónico" value="" required>
                    </p>
                        <intput id="RegistroBoton" type="submit" value="Como crear una cuenta">
                    </p>
                </form>
            </div>
        </div>
        <label></label>
    </body>
</html>
