<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header" class="row">
    <div class="col-lg-3">
        <a href="/tienda_e.jimenezgr/Inicio" title="inicio"  >
            <img src="<c:url value='/Recursos/poxmania-logo.png'/>">
        </a>
    </div>
        
        
    <div class="col-lg-4 well col-lg-offset-1">
        <span class="h1">Página de Administración</span>
    </div>

    <div class="col-lg-3  col-lg-offset-1">
        <c:choose>
            <c:when test="logueado"> <%--falta comprobar logueo--%>
                <c:set var="direccion" value="miPedido.jsp"/>
            </c:when>
            <c:otherwise>
                <c:set var="direccion" value="Autenticacion.jsp"/>
            </c:otherwise>
        </c:choose>

        <span class="col-lg-5">
            <a href="/tienda_e.jimenezgr/Administracion/cerrarSesionAdmin" type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-user"></span>
                <span>Cerrar Sesión</span>
            </a>
        </span>
    </div>
</div>




<div id="menu" class="collapse navbar-collapse navbar-default ">
    <ul class="nav navbar-nav">
        <li><a href="/tienda_e.jimenezgr/Inicio" class="glyphicon glyphicon-home"></a></li>

            <li>
            <a href="/tienda_e.jimenezgr/Administracion/AdminProductos">Administrar productos
            </a>
        </li>
        <li>
            <a href="/tienda_e.jimenezgr/Administracion/AdminCategorias">Administrar categorías
            </a>
        </li>
        
            <li>
            <a href="/tienda_e.jimenezgr/Administracion/AdminPedidos">Administrar pedidos
            </a>
        </li>
        

    </ul>
</div>

