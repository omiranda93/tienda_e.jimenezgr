<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header" class="row">
    <div class="col-lg-3">
        <a href="/tienda_e.jimenezgr/Inicio" title="inicio"  >
            <img src="<c:url value='/Recursos/poxmania-logo.png'/>">
        </a>
    </div>


    <div class="col-lg-4 well col-lg-offset-1">
        <form name="formNombre" method='get' action='/tienda_e.jimenezgr/Inicio/BuscaProductos'>
            <div class="form-group col-lg-9">
                <input class="form-control" type='search' name='nombreProd' placeholder="¿Qué estás buscando?"/>
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
                <c:if test="${usuario!=null}">
                    <div>
                        ${usuario}  
                    </div>
                </c:if>
            </a>
        </span>
        <span class="col-lg-5">
            <a href="/tienda_e.jimenezgr/Inicio/CarritoVer" type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-shopping-cart"></span>
                <span>Cesta</span>
                <%//si el parametro carro no esta vacio%>
                <c:if test="${carrito!=null}">
                    <span><c:out value="(${carrito.totalProductos()})"/></span>
                </c:if>                
            </a>    
        </span>
    </div>
</div>




<div id="menu" class="collapse navbar-collapse navbar-default ">
    <ul class="nav navbar-nav">
        <li><a href="/tienda_e.jimenezgr/Inicio" class="glyphicon glyphicon-home"></a></li>
            <c:forEach var="categ" items="${categorias}">
                <c:if test="${categ.essuper==true}">
                    <c:choose>
                        <c:when test="${categ.categoriaCollection1.size()>0}">
                        <li class="dropdown">
                            <a href="/tienda_e.jimenezgr/Inicio/MuestraProductos?clave=${categ.clave}" name= "categoriaProd" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${categ.nombre}"/>
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

