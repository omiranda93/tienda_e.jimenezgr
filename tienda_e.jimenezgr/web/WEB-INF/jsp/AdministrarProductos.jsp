<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de productos</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
    </head>
    <body>
        <%//si el parametro admin esta vacio (no nos hemos identificado)%>
        <c:if  test="${admin == null}"> 
            <%//asigna a la variable error el valor correspondiente%>
            <c:set var="error" value="Debe loguearse como administrador para ver esta página" scope="session"/>
            <%//redirige a login.jsp%>
            <c:redirect url="/Administracion"/>
        </c:if>
        <div id="contenedor">
            <c:import url="menuAdmin.jsp" charEncoding="utf-8"/>
            <div>
                <b>Productos</b>
                <c:forEach var="producto" items="${productosListados}" varStatus="contador">
                    <div class="row panel panel-default">
                        <h3>${producto.nombre}</h3>
                        <form method= "post" action='/tienda_e.jimenezgr/Administracion/EditarProducto' enctype="multipart/form-data">
                            <input type='hidden' name='accion' value='editar'>
                            <input type='hidden' name='contador' value='${contador.index}'>
                            <b>Precio:</b>
                            <input type='text' name='precio' value="${producto.precio}">
                            <b>Stock:</b>
                            <input type='text' name='cantidad' value="${producto.cantidad}">
                            <b>Descripción:</b>
                            <input type='textarea' maxlength="1000" name='descripcion' value="${producto.descripcion}">
                            <b>Categoría:</b>
                            <select name='categorias'> 
                                <c:forEach var="categoria" items="${categoriasListadas}">
                                    <c:choose>
                                        <c:when test="${producto.categoriaCollection.contains(categoria)}">
                                            <option value='${categoria.clave}'>${categoria.nombre}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            Imagen: <input type="file" name="file"><br /> 
                            Nombre: <input type="text" name="name"><br /> <br /> 
                            <input type='submit' name='editar' value='Editar' onclick="alert('Has editado este producto')">
                        </form>
                        <form method="get" action='/tienda_e.jimenezgr/Administracion/EliminarProducto' >
                            <input type='hidden' name='busqueda' value='borrar'>
                            <input type='submit' name='borrar' value='Borrar' onclick="alert('Has borrado este producto')">
                            <input type='hidden' name='contador' value='${contador.index}'>
                        </form>
                    </div>
                </c:forEach>
            </div>

            <form method= "post" action='/tienda_e.jimenezgr/Administracion/InsertarProducto' enctype="multipart/form-data">
                <b>Nombre:</b>
                <input type='text' name='nombre'>
                <b>Precio:</b>
                <input type='text' name='precio'>
                <b>Stock:</b>
                <input type='text' name='cantidad'>
                <b>Descripción:</b>
                <input type='textarea' maxlength="1000" name='descripcion'>
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
        </div>
    </body>
</html>
