<%-- 
    Document   : AdministrarCategorias
    Created on : 22-may-2014, 12:59:58
    Author     : oscarmirandabravo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de categorias</title>
    </head>
    <body>
    <ul>
       
        <c:forEach var="categ" items="${categoriasListadas}">
            <c:if test="${categ.essuper==true}">
                <c:choose>
                    <c:when test="${categ.categoriaCollection1.size()>0}">
                        <li>
                            <span><form method="get" action="/tienda_e.jimenezgr/Administracion/EditarCategoria?clave=${categ.clave}">
                                <input type="text" name="nombreCategoria" value="${categ.nombre}">
                                <input type="hidden" name="clave" value="${categ.clave}">
                                <input type="submit" name="editar" value="Editar">
                                </form></span>
                            <span><a href="/tienda_e.jimenezgr/Administracion/BorrarCategoria?clave=${categ.clave}">Borrar</a></span>
                            <%--buscar hijos y guardarlos en hijos--%>
                            <ul>
                                <c:forEach var="hijo" items="${categ.categoriaCollection1}">
                                    <li>
                                         <span><form method="get" action="/tienda_e.jimenezgr/Administracion/EditarCategoria">
                                            <input type="text" name="nombreCategoria" value="${hijo.nombre}">
                                            <input type="hidden" name="clave" value="${hijo.clave}">
                                            <input type="submit" name="editar" value="Editar">
                                            </form></span>
                                        <span><a href="/tienda_e.jimenezgr/Administracion/BorrarCategoria?clave=${hijo.clave}">Borrar</a></span>
                                    </li>
                                </c:forEach>
                                    <li>
                                            <span><form method="get" action="/tienda_e.jimenezgr/Administracion/InsertarCategoria">
                                            <input type="text" name="nombreCategoria" value="">
                                            <input type="hidden" name="claveSuper" value="${categ.clave}">
                                            <input type="submit" name="editar" value="Agregar">
                                            </form></span>
                                    </li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><span><form method="get" action="/tienda_e.jimenezgr/Administracion/EditarCategoria?clave=${categ.clave}">
                                <input type="text" name="nombreCategoria" value="${categ.nombre}">
                                <input type="hidden" name="clave" value="${categ.clave}">
                                <input type="submit" name="editar" value="Editar">
                                </form></span>
                            <span><a href="/tienda_e.jimenezgr/Administracion/BorrarCategoria?clave=${categ.clave}">Borrar</a></span>
                        </li>
                         </c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
        <li>
        <form method= "get" action='/tienda_e.jimenezgr/Administracion/InsertarCategoria'>
            <input type='text' name='nombreCategoria'>
            <input type='submit' name='Añadir' value='Agregar' onclick="alert('Has añadido esta categoria.')">
        </form>
        </li>
        </ul>
        <a href="/tienda_e.jimenezgr/Administracion">Volver</a>
        
    </body>
</html>
