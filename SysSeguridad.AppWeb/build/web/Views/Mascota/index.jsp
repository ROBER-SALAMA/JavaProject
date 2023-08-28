
<%@page import="sysseguridad.entidadesdenegocio.Usuario"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<Mascota> mascotas = (ArrayList<Mascota>) request.getAttribute("mascota");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (mascotas == null) {
        mascotas = new ArrayList();
    } else if (mascotas.size() > numReg) {
        double divNumPage = (double) mascotas.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Mascota</title>
    </head>
    <body>
            <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Mascota</h5>
            <form action="Mascota" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input  id="txtSexo" type="text" name="sexo">
                        <label for="txtSexo">Sexo</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtEdad" type="text" name="edad">
                        <label for="txtEdad">Edad</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtRaza" type="text" name="raza">
                        <label for="txtRaza">Raza</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtSenialesParticulares" type="text" name="senialesparticulares">
                        <label for="txtSenialesParticulares">Seniales Particulares</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtEspecie" type="text" name="especie">
                        <label for="txtEspecie">Especie</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPropietario" type="text" name="propietario">
                        <label for="txtPropietario">Propietario</label>
                    </div>
                    <div class="input-field col l4 s12">   
                       <%-- <jsp:include page="/Views/Mascota/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>                        
                    </div>--%>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Mascota?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>                                     
                                    <th>Nombre</th>  
                                    <th>Sexo</th> 
                                    <th>Edaad</th>  
                                    <th>Raza</th> 
                                    <th>Seniales Particulares</th>
                                    <th>Especie</th>
                                    <th>Propietario</th>
                                    <th>Usuario</th>  
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Mascota mascota : mascotas) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                        
                                %>
                                <tr data-page="<%= tempNumPage%>">                                    
                                    <td><%=mascota.getNombre()%></td>  
                                    <td><%=mascota.getSexo()%></td>
                                    <td><%=mascota.getEdad()%></td>
                                    <td><%=mascota.getRaza()%></td>
                                    <td><%=mascota.getSenialesParticulares()%></td>
                                    <td><%=mascota.getEspecie()%></td>
                                    <td><%=mascota.getPropietario()%></td>
                                    <td><%=mascota.getUsuario().getNombre()%></td> 
                                     
                                    <td>
                                        <div style="display:flex">
                                             <a href="Mascota?accion=edit&id=<%=mascota.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i>
                                        </a>
                                        <a href="Mascota?accion=details&id=<%=mascota.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i>
                                        </a>
                                        <a href="Mascota?accion=delete&id=<%=mascota.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i>
                                        </a>    
                                        </div>                                                                    
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>             
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
        
    </body>
</html>
