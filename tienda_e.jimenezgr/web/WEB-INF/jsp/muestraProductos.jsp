<%-- 
    Document   : muestraProductos
    Created on : 19-may-2014, 23:25:53
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <c:import url="cabecera.jsp" charEncoding="utf-8"/>

        <table class="table table-hover">
            <%//por cada producto en productosListados%>
            <c:forEach var="prod" items="${productosListados}" varStatus="status">
                <tr>
                    <%//muestra el producto%>
                    <td><c:out value="${prod.getNombre()}"/></td>
                    <td><c:out value="${prod.formateaPrecio(prod.getPrecio())}"/></td>
                    <td><c:out value="${prod.getCategoria()}"/></td>
                    <td><img src="<c:out value="${prod.getImagen()}"/>"/></td>
                    <td>
                        <%//agrega el producto al carro;
                            //Formulario de tipo get que llama a ServletAgregarProductos con el parametro cantidad con el valor introducido
                            //y el parametro oculto posicion con valor posicion del producto en la lista%>
                        <form method="get" action="ServletAgregarAlCarro">
                            <label>Cantidad</label>
                            <input type="number" size="5" min="0" name="cantidad" value="0" onkeypress="validarCantidad()" />
                            <input type="hidden" name="posicion" value="${status.index}"/>
                            <input type="submit" name="comprarProducto" value="Añadir">
                        </form>
                    </td>   
                </tr>
            </c:forEach>

        </table>

        <c:import url="footer.jsp" charEncoding="utf-8"/>
    </body>
</html>
