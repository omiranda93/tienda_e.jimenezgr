<%-- 
    Document   : AdministrarProductos
    Created on : 22-may-2014, 12:59:47
    Author     : oscarmirandabravo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de productos</title>
    </head>
    <body>
        <table>
            <tr><td><b>Productos</b></td></tr>
                <c:forEach var="producto" items="${productosListados}" varStatus="contador">
                <tr><td><form method= "post" action='/tienda_e.jimenezgr/Administracion/EditarProducto' enctype="multipart/form-data">
                            <input type='hidden' name='accion' value='editar'>
                            <input type='hidden' name='contador' value='${contador.index}'>
                            ${producto.nombre}
                            <input type='text' name='precio' value="${producto.precio}">
                            <input type='text' name='cantidad' value="${producto.cantidad}">
                            <input type='textarea' rows="4" cols="50" name='descripcion' value="${producto.descripcion}">
                            <label>Añadir categoria</label>
                            <select name='categorias' > 
                                <option value="">Seleccione una categoria</option>
                                <c:forEach var="categoria" items="${categoriasListadas}">
                                    
                                            <option value='${categoria.clave}'>${categoria.nombre}</option>
                                        
                                    
                                </c:forEach>
                            </select>
                            <label>Eliminar categoria</label>
                                 <select name='categorias2' > 
                                     <option value="">Seleccione una categoria</option>
                                <c:forEach var="categoria" items="${categoriasListadas}">
                                    <c:choose>
                                        <c:when test="${producto.categoriaCollection.contains(categoria)}">
                                            <option value='${categoria.clave}'>${categoria.nombre}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <br /> 
                             Imagen: <input type="file" name="file">
                             Nombre de la imagen: <input type="text" name="name"><br /> <br /> 
                            <input type='submit' name='editar' value='Editar' onclick="alert('Has editado este producto')">
                        </form>
                    </td><td><form method="get" action='/tienda_e.jimenezgr/Administracion/EliminarProducto' >
                            <input type='hidden' name='busqueda' value='borrar'>
                            <input type='submit' name='borrar' value='Borrar' onclick="alert('Has borrado este producto')">
                            <input type='hidden' name='contador' value='${contador.index}'>
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <form method= "post" action='/tienda_e.jimenezgr/Administracion/InsertarProducto' enctype="multipart/form-data">
            <b>Nombre:</b>
            <input type='text' name='nombre'>
            <b>Precio:</b>
            <input type='text' name='precio'>
            <b>Cantidad:</b>
            <input type='text' name='cantidad'>
            <b>Descripción:</b>
            <input type='textarea' name='descripcion'>
            <b>Categoria:</b>
            <select MULTIPLE NAME='categorias' SIZE='4'> 
                                <c:forEach var="categoria" items="${categoriasListadas}" varStatus="contador">
                                    <option value='${categoria.clave}'>${categoria.nombre}</option>
                                </c:forEach>
            </select>
            <b>Imagen:</b>
            Imagen: <input type="file" name="file"><br /> 
            Nombre: <input type="text" name="name"><br /> <br /> 
            <input type='submit' name='Añadir' value='Agregar' onclick="alert('Has añadido este producto a los disponibles.')">
        </form>
        <a href="/tienda_e.jimenezgr/Administracion">Volver</a>
    </body>
</html>
