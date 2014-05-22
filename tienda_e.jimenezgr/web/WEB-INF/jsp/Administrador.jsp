<%-- 
    Document   : Administrador
    Created on : 22-may-2014, 9:33:56
    Author     : oscarmirandabravo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Administración</title>
    </head>
    <body>
        <%//si el parametro usuario esta vacio (no nos hemos identificado)%>
        <c:if  test="${usuario == null}"> 
            <%//asigna a la variable error el valor correspondiente%>
            <c:set var="error" value="Debe loguearse como administrador para ver esta página" scope="session"/>
            <%//redirige a login.jsp%>
            <c:redirect url="/Administracion"/>
        </c:if>
        
        <%//si el parametro confirmacion no esta vacio%>
        <c:if test="${confirmacion!=null}">
            <%//lanza una alerta con la confirmacion%>
            <script>alert("${confirmacion}");</script>
            <%//elimina la confirmacion%>
            <c:set var="confirmacion" value="${null}" scope="session"/>           
        </c:if>
            
        <div id="contenedor">
            <div id="pedidos">
                <div class="menuBasico">
                    <%//muestra la lista de pedidos pendientes de preparar;
                      //Referencia a ServletGestionarPedidos con el parametro gestionPedidos con valor ver%>
                    <a class="boton" href="ServletGestionarPedidos?gestionPedidos=ver">Ver pedidos pendientes de preparación</a> 
                    <a class="boton" href="index.jsp">Volver</a>
                    <%//cierra la sesion;
                      //Referencia a ServletLogin con el parametro login con valor false%>
                    <a class="boton" href="ServletLogin?login=false">Cerrar sesión</a>
                </div>
                <div id="divPedidosSinPreparar">
                    <%//si el parametro pedidosSinPreparar no esta vacio%>
                    <c:if test="${pedidosSinPreparar != null}">
                        <%//asigna a la variable pedidosSinPreparar la variable de sesion pedidosSinPreparar%>
                        <c:set var="pedidosSinPreparar" value="${pedidosSinPreparar}"/>
                        <%//asigna a la variable pedidiosPreparar la variable pedidosSinPreparar%>
                        <c:set var="pedidosPreparar" value="${pedidosSinPreparar}" scope="session"/>
                        <table class="tabla">
                            <tr>
                                <td><b>Número</b></td>
                                <td><b>Usuario</b></td>
                                <td><b>Productos</b></td>
                                <td><b>¿Preparar?</b></td>
                            </tr>

                            <%//por cada pedido en pedidosSinPreparar%>
                            <c:forEach var="pedido" items="${pedidosSinPreparar}" varStatus="status">
                                <tr>
                                    <%//muestra el pedido%>
                                    <td><c:out value="${pedido.getNumero()}"/></td>
                                    <td><c:out value="${pedido.getUsuario()}"/></td>
                                    <td>
                                        <ul>
                                            <%//por cada producto en el pedido%>
                                            <c:forEach var="prod" items="${pedido.getListaProductos()}">
                                                <%//muestra el producto junto a su cantidad%>
                                                <li><c:out value="${prod.getNombre()} --> (${prod.getCantidad()} uds)"/></li>
                                                </c:forEach>
                                        </ul>
                                    </td>
                                    <td>
                                        <%//prepara el pedido;
                                          //Formulario tipo post a ServletGestionarPedidos con el parametro posicion con la posicion del pedido en la lista y
                                          //con el parametro oculto gestionPedidos con valor preparar%>
                                        <form method="post" action="ServletGestionarPedidos">
                                            <input type="hidden" name="gestionPedidos" value="preparar"/>
                                            <input type="hidden" name="posicion" value="${status.index}"/>
                                            <input type="submit" value="Preparar">
                                        </form>

                                    </td>
                                </tr>


                            </c:forEach>

                        </table>
                        <%//elimina la variable pedidosSinAsignar%>
                        <c:set var="pedidosSinPreparar" value="${null}" scope="session"/>
                    </c:if>
                </div>
            </div>

            <div id="productos">

                <div id="agregarProducto">
                    <%//abre el formulario de adicion de productos;
                      //Referencia a paginaAdministracion.jsp con el parametro agregar con valor true%>
                    <div class="menuBasico"><a class="boton" href="paginaAdministracion.jsp?agregar=true">Añadir nuevo producto</a></div>
                    <%//si el parametro agregar esta vacio%>
                    <c:if test="${param.agregar != null}">
                        <table class='tabla '>
                            <tr><td colspan="4"><h4>Añadir nuevo Producto</h4><td></tr>
                            <tr>
                            <tr>
                                <td>Nombre del Producto</td>
                                <td>Precio</td>
                                <td>Categoria</td>
                                <td>Imagen</td>
                                <td></td>
                            </tr>
                            <tr>
                            <%//aniade el producto a la BD;
                              //Formulario tipo post que llama aServletGestionarProductos con los datos del nuevo producto como parametros;
                              //El formulario es de tipo multipart. Valida que el formato del precio sea correcto con la funcion validar_camposLogin() del js
                              //y comprueba que todos los campos esten rellenados con la funcion validarProducto() del js%>
                            <form name="formAgregar" method='post' action='ServletGestionarProductos' enctype="multipart/form-data" onSubmit="return validarProducto();">
                                <td><input type='text' name='nombreProd'/></td>
                                <td><input type='text' name='precio' onkeypress="soloCaracterPrecioValido()"/></td>
                                <td>
                                    <input type="radio" name="categoria" value="Alimentación"/>Alimentación<br/>
                                    <input type="radio" name="categoria" value="Ferretería"/>Ferretería<br/>
                                    <input type="radio" name="categoria" value="Droguería"/>Droguería<br/>
                                    <input type="radio" name="categoria" value="Prensa"/>Prensa<br/>   
                                </td>
                                <td><input type='file' name='imagen' /></td>
                                <td><input type='submit' name='modificar' value='Agregar'></td>
                            </form>  
                            </tr>
                        </table>
                    </c:if>
                </div>

                <div id="modificarProductos">
                    <div id="menu">
                        <ul>
                            <li>Modificar o eliminar Producto</li>
                            <li>
                                <%//busca productos por nombre;
                                  //Formulario tipo get que llama a ServletProductos con el parametros nombreProd con valor del nombre introducido y los parametros
                                  //ocultos busqueda con valor nombre y esAdmin con valor si%>
                                <form name="formNombreAdmin" method='get' action='ServletProductos'>
                                    <label>Buscar por nombre</label> 
                                    <input type='search' name='nombreProd' />
                                    <input type='hidden' name='busqueda' value='nombre' />
                                    <input type='hidden' name='esAdmin' value='si' />
                                    <input type='submit' name='BuscarNombre' value='Buscar'>
                                </form>  
                            </li>
                        </ul>
                    </div>

                    <div id="muestraProductos">
                        <%//si el parametro lista no esta vacio%>
                        <c:choose>
                            <c:when test="${lista != null}">
                                <%//asigna a la variable productosListados la variable de sesion lista%>
                                <c:set var="productosListados" value="${lista}"/>

                                <table>
                                    <tr>
                                        <td><b>Producto</b></td>
                                        <td><b>Precio</b></td>
                                        <td><b>Categoría</b></td>
                                        <td><b>Imagen</b></td>
                                        <td><b>Modificar</b></td>
                                        <td><b>Eliminar</b></td>
                                    </tr>

                                    <%//para cada producto en productosListados%>
                                    <c:forEach var="prod" items="${productosListados}">
                                        <tr>
                                            <%//muestra el producto%>
                                            <td><c:out value="${prod.getNombre()}"/></td>
                                            <td><c:out value="${prod.formateaPrecio(prod.getPrecio())}"/></td>
                                            <td><c:out value="${prod.getCategoria()}"/></td>
                                            <td><img src="<c:out value="${prod.getImagen()}"/>"/></td>
                                            <td>
                                                <%//muestra el formulario de modificacion de producto;
                                                  //Formulario tipo get que llama a paginaAdministracion.jsp con el parametro oculto nombreProd con valor del nombre del producto elegido%>
                                                <form method="get" action="paginaAdministracion.jsp">
                                                    <input type="hidden" name="nombreProd" value="${prod.getNombre()}"/>
                                                    <input type="submit" name="modificar" value="Modificar">
                                                </form>
                                            </td>  
                                            <td>
                                                <%//elimina el producto;
                                                  //Formulario tipo get que llama a ServletGestionarProductos con el parametro oculto nombreProd con valor del nombre del producto elegido%>
                                                <form method="get" action="ServletGestionarProductos" onSubmit="return confirmar('¿Está seguro de que desea eliminar este producto?')">
                                                    <input type="hidden" name="nombreProd" value="${prod.getNombre()}"/>
                                                    <input type="submit" name="modificar" value="Eliminar">
                                                </form>
                                            </td>
                                        </tr>


                                    </c:forEach>

                                </table>
                                <%//elimina la variable lista%>
                                <c:set var="lista" value="${null}" scope="session"/>
                            </c:when>
                            <c:otherwise>
                                Busca el producto a modificar o eliminar. Si dejas el campo en blanco saldrá toda la lista de productos
                            </c:otherwise>   
                        </c:choose>
                    </div>
                    <%//si el parametro modificar no esta vacio%>
                    <c:if test="${param.modificar != null}">
                        <%//asigna a la variable nombreProd el parametro nombre del producto%>
                        <c:set var="nombreProd" value="${param.nombreProd}"/>
                        <div id="divModificar">
                            <table class="tabla">
                                <tr>
                                    <td colspan="4"><h4>Modificar ${nombreProd}</h4></td>
                                </tr>
                                <tr>
                                    <td>
                                        <%//en todos los casos a continuacion llama a ServletGestionarProductos que modificará el producto
                                          //en funcion del campo rellenado%>
                                        <form method="post" action="ServletGestionarProductos">
                                            <label>Modificar nombre</label><br/>
                                            <input type="text" name="nombreNuevo"/><br/>
                                            <input type="hidden" name="nombreProd" value="${nombreProd}"/>
                                            <input type="submit" name="modificar" value="modificarNombre"/>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="ServletGestionarProductos" onSubmit="return validarPrecio();">
                                            <label>Modificar precio</label><br/>
                                            <input type="text" name="precioNuevo" onkeypress="soloCaracterPrecioValido()"/><br/>
                                            <input type="hidden" name="nombreProd" value="${nombreProd}"/>
                                            <input type="submit" name="modificar" value="modificarPrecio"/>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="ServletGestionarProductos">
                                            <label>Modificar categoria</label><br/>
                                            <input type="radio" name="categoriaNueva" value="Alimentación"/>Alimentación<br/>
                                            <input type="radio" name="categoriaNueva" value="Ferretería"/>Ferretería<br/>
                                            <input type="radio" name="categoriaNueva" value="Droguería"/>Droguería<br/>
                                            <input type="radio" name="categoriaNueva" value="Prensa"/>Prensa<br/>   
                                            <input type="hidden" name="nombreProd" value="${nombreProd}"/>
                                            <input type="submit" name="modificar" value="modificarCategoria"/>
                                        </form>
                                    </td>   
                                    <td>
                                        <form method="post" action="ServletGestionarProductos" enctype="multipart/form-data" action="">
                                            <label>Modificar imagen</label><br/>
                                            <input type="file" name="imagenNueva"/><br/>
                                            <input type="hidden" name="nombreProd" value="${nombreProd}"/>
                                            <input type="submit" name="modificar" value="modificarImagen"/>
                                        </form>
                                    </td>
                                </tr>
                            </table>

                        </div>
                    </c:if>
                </div>

            </div>

        </div>

        
    </body>
</html>
