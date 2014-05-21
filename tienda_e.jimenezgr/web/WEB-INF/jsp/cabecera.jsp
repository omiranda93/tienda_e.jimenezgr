<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header" class="row">
    <div class="col-lg-3">
        <a href="/tienda_e.jimenezgr/Inicio" title="inicio"  >
            <img src="<c:url value='/Recursos/poxmania-logo.png'/>">
        </a>
    </div>


    <div class="col-lg-4 well  col-lg-offset-1">
        <form name="formNombre" method='get' action='/tienda_e.jimenezgr/Inicio/BuscaProductos'>
            <div class="form-group col-lg-9">
                <input class="form-control" type='search' name='nombreProd' value="¿Qué estás buscando?"/>
            </div>
            <input type='hidden' name='busqueda' value='nombre' />
            <input type='submit' class="btn btn-primary" name='BuscarNombre' value='Buscar'>
        </form> 	
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
            <a href="/tienda_e.jimenezgr/Inicio/Autenticacion" type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-user"></span>
                <span>Mi cuenta</span>
            </a>
        </span>
        <span class="col-lg-5">
            <a href="/tienda_e.jimenezgr/Inicio/CarritoVer" type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-shopping-cart"></span>
                <span>Cesta</span>
                <%//si el parametro carro no esta vacio%>
                <c:if test="${carro.size() > 0}">
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
        </span>
    </div>
</div>
                
                
  

<div id="menu">
    <ul>
        <c:forEach var="categ" items="${categorias}">
            <c:if test="${categ.essuper==true}">
                <c:choose>
                    <c:when test="${categ.categoriaCollection1.size()>0}">
                        <li class="btn-group">
                            <a href="/tienda_e.jimenezgr/Inicio/MuestraProductos?clave=${categ.clave}" name= "categoriaProd" class="btn-default dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"><c:out value="${categ.nombre}"/>
                            </a>            
                            <%--buscar hijos y guardarlos en hijos--%>
                            <ul class="dropdown-menu">
                                <c:forEach var="hijo" items="${categ.categoriaCollection1}">
                                    <li>
                                        <a href="/tienda_e.jimenezgr/Inicio/MuestraProductos?clave=${hijo.clave}"><c:out value="${hijo.nombre}"/></a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="/tienda_e.jimenezgr/Inicio/MuestraProductos?clave=${categ.clave}" name= "categoriaProd"><c:out value="${categ.nombre}"/>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
    </ul>
</div>

