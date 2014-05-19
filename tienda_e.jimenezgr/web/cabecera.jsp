<div id="header">
    <a href="index.html" title="inicio">
        <img src="Recursos/poxmania-logo.png">
    </a>

    <form name="formNombre" method='get' action='ServletProductos'>
        <input type='search' name='nombreProd' />
        <input type='hidden' name='busqueda' value='nombre' />
        <input type='submit' name='BuscarNombre' value='Buscar'>
    </form> 	

    <div>
        <ul>
            <li id="miCuenta">
                <a>Mi cuenta </a>
            </li>
            <li id="carro">
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
                <span>(<c:out value="${total}"/>)</span>
            </c:if>                
            </li>
        </ul>
    </div>
</div>

