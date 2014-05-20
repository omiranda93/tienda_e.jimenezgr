<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <a href="index.html" title="inicio">
        <img src="<c:url value='/Recursos/poxmania-logo.png'/>">
    </a>

    <form name="formNombre" method='get' action='ServletProductos'>
        <input type='search' name='nombreProd' />
        <input type='hidden' name='busqueda' value='nombre' />
        <input type='submit' class="btn btn-primary" name='BuscarNombre' value='Buscar'>
    </form> 	

    <div>
        <c:choose>
            <c:when test="logueado"> <%--falta comprobar logueo--%>
                <c:set var="direccion" value="miPedido.jsp"/>
            </c:when>
            <c:otherwise>
                <c:set var="direccion" value="Autenticacion.jsp"/>
            </c:otherwise>
        </c:choose>

        <a href="${direccion}" type="button" class="btn btn-primary">
            Mi cuenta
        </a>
        <a href="" type="button" class="btn btn-primary">
            <span>Cesta</span>
            <%//si el parametro carro no esta vacio%>
            <c:if test="${carro!= null}">
                <%//asigna a la variable carro el valor del parametro de sesion carro%>
                <c:set var="carro" value="${carro}"/>
                <%//asigna a la variable total el valor 0%>
                <c:set var="total" value="${0}"/>
                <%//por cada producto en carro%>
                <c:forEach var="prod" items="${carro}">
                    <%//aumenta el total de productos%>
                    <c:set var="total" value="${total+prod.cantidad}"/>
                </c:forEach>
                <span><c:out value="(${total})"/></span>
            </c:if>                
        </a>        
    </div>
</div>

<div id="menu">
    <ul>
        <c:forEach var="categ" items="${categorias}">
            <li><c:out value="${categ}"/></li>
            </c:forEach>
    </ul>
</div>
