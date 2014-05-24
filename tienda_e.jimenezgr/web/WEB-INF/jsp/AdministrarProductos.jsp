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
            <div class="row">
                <h3>Editar o Eliminar Productos</h3>
                <c:forEach var="producto" items="${productosListados}" varStatus="contador">
                    <div class="row panel panel-default">
                        <h3>${producto.nombre}</h3>
                        <form class="panel panel-default" method= "post" action='/tienda_e.jimenezgr/Administracion/EditarProducto' enctype="multipart/form-data" onsubmit="return confirmar('¿Desea editar el producto?')">
                            <input class="col-lg-3" type='hidden' name='accion' value='editar'>
                            <input class="col-lg-3" type='hidden' name='contador' value='${contador.index}'>
                            <div class="row well">
                                <span class="col-lg-2">
                                    <b>Precio:</b>
                                    <input type='text' name='precio' value="${producto.precio}" onkeypress="soloCaracterPrecioValido()">
                                </span>
                                <span class="col-lg-2">
                                    <b>Stock:</b>
                                    <input type='number' min="0" name='cantidad' value="${producto.cantidad}">
                                </span>
                                <span class="col-lg-4">
                                    <b>Añadir Categoría:</b>
                                    <select name='categorias'> 
                                        <c:forEach var="categoria" items="${categoriasListadas}">
                                            <c:choose>
                                                <c:when test="${producto.categoriaCollection.contains(categoria)}">
                                                    <option value='${categoria.clave}'>${categoria.nombre}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </span>
                                <span class="col-lg-4">
                                    <b>Eliminar Categoría:</b>
                                    <select name='categorias2'> 
                                        <c:forEach var="categoria2" items="${categoriasListadas}">
                                            <c:choose>
                                                <c:when test="${!producto.categoriaCollection.contains(categoria2)}">
                                                    <option value='${categoria2.clave}'>${categoria2.nombre}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </span>
                            </div>
                            <div class="row well">
                                <b>Descripción:</b>
                                <textarea class="form-control" name='descripcion' rows="5" style="width: 450px;" maxlength="1000">${producto.descripcion}</textarea>
                            </div>
                            <div class="row well">
                                <span class="col-lg-4">
                                    Imagen: <input type="file" name="file">
                                </span>
                                <span class="col-lg-3">
                                    Nombre para la nueva imagen: <input type="text" name="name"> 
                                </span>
                            </div>
                            <div class="row well">
                                <b>Editar producto</b>
                                <input type='submit' class="btn btn-primary" name='editar' value='Editar'>
                            </div>
                        </form>
                        <div class="row well">
                            <form method="get" action='/tienda_e.jimenezgr/Administracion/EliminarProducto' onsubmit="return confirmar('¿Desea eliminar el producto?')" >
                                <input type='hidden' name='busqueda' value='borrar'>
                                <b>Eliminar producto</b>
                                <input type='submit' class="btn btn-primary" name='borrar' value='Borrar'>
                                <input type='hidden' name='contador' value='${contador.index}'>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="row">
                <h3>Agregar nuevo producto</h3>
                <form class="panel panel-default" method= "post" action='/tienda_e.jimenezgr/Administracion/InsertarProducto' enctype="multipart/form-data">
                    <div class="row well">
                        <span class="col-lg-2">
                            <b>Nombre:</b>
                            <input type='text' name='nombre'>
                        </span>
                        <span class="col-lg-2">

                            <b>Precio:</b>
                            <input type='text' name='precio' onkeypress="soloCaracterPrecioValido()">
                        </span>
                        <span class="col-lg-2">
                            <b>Stock:</b>
                            <input type='number' min="0" name='cantidad'>
                        </span>
                        <span class="col-lg-2">
                            <b>Categoria:</b>
                            <select MULTIPLE NAME='categorias' SIZE='4'> 
                                <c:forEach var="categoria" items="${categoriasListadas}" varStatus="contador">
                                    <option value='${categoria.clave}'>${categoria.nombre}</option>
                                </c:forEach>
                            </select>
                        </span>
                    </div>
                    <div class="row well">
                        <b>Descripción:</b>
                        <textarea class="form-control" name='descripcion' rows="5" style="width: 450px;" maxlength="1000">${producto.descripcion}</textarea>
                    </div>
                    <div class="row well">
                        <b>Imagen:</b>
                        Imagen: <input type="file" name="file"><br /> 
                        Nombre: <input type="text" name="name"><br /> <br /> 
                        <input type='submit' name='Añadir' value='Agregar' onclick="alert('Has añadido este producto a los disponibles.')">
                    </div>
                </form>
                <a href="/tienda_e.jimenezgr/Administracion">Volver</a>
            </div>
        </div>
    </body>
</html>
