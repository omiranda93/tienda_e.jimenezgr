<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de categorias</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/estilo.css'/>"/>
        <script src="<c:url value='/js/funciones.js'/>"></script>
        <script src="http://code.jquery.com/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
    </head>
    <body>
        <%//si el parametro admin esta vacio (no nos hemos identificado)%>
        <c:if  test="${admin == null}"> 
            <%//asigna a la variable error el valor correspondiente%>
            <c:set var="error" value="Debe loguearse como administrador para ver esta página" scope="session"/>
            <%//redirige a LogAdministrador.jsp%>
            <c:redirect url="/Administracion"/>
        </c:if>
        <div id="contenedor">
            <c:import url="menuAdmin.jsp" charEncoding="utf-8"/>
            <div class="row">
                <h3 class="panel panel-title blue text-center col-lg-6 col-lg-offset-3">Administrar categorías</h3>
            </div>
            <div class="row">
                <h3>Editar o Añadir Categorías</h3>
                <ul class="list-unstyled">
                    <c:forEach var="categ" items="${categoriasListadas}" varStatus="cont">
                        <c:if test="${categ.essuper==true}">
                            <c:choose>
                                <c:when test="${categ.categoriaCollection1.size()>0}">
                                    <li class="panel panel-default">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#${cont.index}"><h3 class="well">Supercategoría <span class="blue">${categ.nombre}</span></h3></a>
                                        <div id="${cont.index}" class="panel-collapse collapse">
                                            <div class="row">
                                                <span class="col-lg-4">
                                                    <form method="get" action="/tienda_e.jimenezgr/Administracion/EditarCategoria" onsubmit="return confirmar('¿Desea editar el nombre de esta categoría?')">
                                                        <b>Editar nombre</b>
                                                        <input type="text" name="nombreCategoria" value="${categ.nombre}">
                                                        <input type="hidden" name="clave" value="${categ.clave}">
                                                        <input type="submit" class="btn btn-primary" name="editar" value="Editar">
                                                    </form>
                                                </span>
                                                <span class="col-lg-5">
                                                    <form method="get" action='/tienda_e.jimenezgr/Administracion/BorrarCategoria' onsubmit="return confirmar('¿Desea eliminar esta categoría y todas sus subcategorías?')" >
                                                        <input type='hidden' name='clave' value='${categ.clave}'>                                                
                                                        <b>Borrar supercategoría y todas sus subcategorías</b>
                                                        <input type='submit' class="btn btn-primary" name='borrar' value='Borrar'>
                                                    </form>
                                                </span>
                                            </div>

                                            <%--buscar hijos y guardarlos en hijos--%>
                                            <ul class="list-unstyled panel panel-default">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#hijas${cont.index}"><h3 class="row well">Subcategorias</h3></a>
                                                <div id="hijas${cont.index}" class="panel-collapse collapse">
                                                    <c:forEach var="hijo" items="${categ.categoriaCollection1}">
                                                        <li class="row">
                                                            <span class="col-lg-5">
                                                                <form method="get" action="/tienda_e.jimenezgr/Administracion/EditarCategoria" onsubmit="return confirmar('¿Desea editar el nombre de esta categoría?')">
                                                                    <b>Editar nombre</b>
                                                                    <input type="text" name="nombreCategoria" value="${hijo.nombre}">
                                                                    <input type="hidden" name="clave" value="${hijo.clave}">
                                                                    <input type="submit" class="btn btn-primary" name="editar" value="Editar">
                                                                </form>
                                                            </span>
                                                            <span class="col-lg-5">
                                                                <form method="get" action='/tienda_e.jimenezgr/Administracion/BorrarCategoria' onsubmit="return confirmar('¿Desea eliminar esta categoría?')" >
                                                                    <input type='hidden' name='clave' value='${hijo.clave}'>  
                                                                    <input type='hidden' name='clavePadre' value='${categ.clave}'>  
                                                                    <b>Borrar subcategoría</b>
                                                                    <input type='submit' class="btn btn-primary" name='borrar' value='Borrar'>
                                                                </form>
                                                            </span>
                                                        </li>
                                                    </c:forEach>
                                                    <hr>
                                                    <li class="row">
                                                        <span class="col-lg-7">
                                                            <form method="get" action="/tienda_e.jimenezgr/Administracion/InsertarCategoria">
                                                                <b>Añadir nueva subcategoría</b>
                                                                <input type="text" name="nombreCategoria" value="" required>
                                                                <input type="hidden" name="claveSuper" value="${categ.clave}">
                                                                <input type="submit" class="btn btn-primary" name="editar" value="Agregar">
                                                            </form>
                                                        </span>
                                                    </li>
                                                </div>
                                            </ul>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="panel panel-default">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#${cont.index}"><h3 class="well">Supercategoría <span class="blue">${categ.nombre}</span></h3></a>
                                        <div id="${cont.index}" class="panel-collapse collapse">
                                            <div class="row">
                                                <span class="col-lg-4">
                                                    <form method="get" action="/tienda_e.jimenezgr/Administracion/EditarCategoria" onsubmit="return confirmar('¿Desea editar el nombre de esta categoría?')">
                                                        <b>Editar nombre</b>
                                                        <input type="text" name="nombreCategoria" value="${categ.nombre}">
                                                        <input type="hidden" name="clave" value="${categ.clave}">
                                                        <input type="submit" class="btn btn-primary" name="editar" value="Editar">
                                                    </form>
                                                </span>
                                                <span class="col-lg-5">
                                                    <form method="get" action='/tienda_e.jimenezgr/Administracion/BorrarCategoria' onsubmit="return confirmar('¿Desea eliminar esta categoría?')" >
                                                        <input type='hidden' name='clave' value='${categ.clave}'>                                                
                                                        <b>Borrar supercategoría y todas sus subcategorías</b>
                                                        <input type='submit' class="btn btn-primary" name='borrar' value='Borrar'>
                                                    </form>
                                                </span>
                                            </div>
                                            <div class="row">
                                                <form method="get" action="/tienda_e.jimenezgr/Administracion/InsertarCategoria">
                                                    <b>Añadir nueva subcategoría</b>
                                                    <input type="text" name="nombreCategoria" value="" required>
                                                    <input type="hidden" name="claveSuper" value="${categ.clave}">
                                                    <input type="submit" class="btn btn-primary" name="editar" value="Agregar">
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                    <h3>Añadir Supercategoría</h3>
                    <li class="panel panel-default">
                        <h3 class="well">Añadir nueva supercategoría</h3>
                        <form method= "get" action='/tienda_e.jimenezgr/Administracion/InsertarCategoria'>
                            <b>Nombre:</b>
                            <input type='text' name='nombreCategoria' required>
                            <input type='submit' class="btn btn-primary" name='Añadir' value='Agregar' onclick="alert('Has añadido esta categoria.')">
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
