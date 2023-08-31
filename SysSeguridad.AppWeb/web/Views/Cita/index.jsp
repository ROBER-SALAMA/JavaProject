<%-- 
    Document   : index
    Created on : 21 ago. 2023, 14:37:15
    Author     : Rober.salama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Cita"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("citas");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (citas == null) {
        citas = new ArrayList();
    } else if (citas.size() > numReg) {
        double divNumPage = (double) citas.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Usuario</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar cita</h5>
            <form action="Cita" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input  id="txtApellido" type="text" name="apellido">
                        <label for="txtApellido">Apellido</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtLogin" type="text" name="login">
                        <label for="txtLogin">Login</label>
                    </div>                    
                    <div class="input-field col l4 s12">   
                        <select id="slEstatus" name="estatus">
                            <option value="0">SELECCIONAR</option>
                            <option value="<%=Cita.EstatusCita.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Cita.EstatusCita.INACTIVO%>">INACTIVO</option>
                        </select>       
                        <label for="slEstatus">Estatus</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Mascota/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>                        
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Cita?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <% for (Cita cita : citas) {%>
                        <div class="row">
                            <div class="col s12 m6">
                                <div class="card">
                                    <div class="card-content">
                                        <span class="card-title">Cita</span>
                                        <p><strong>Fecha:</strong> <%= cita.getFecha()%></p>
                                        <p><strong>Diagnóstico:</strong> <%= cita.getDiagnostico()%></p>
                                        <p><strong>Dirección:</strong> <%= cita.getDireccion()%></p>
                                        <p><strong>Propietario:</strong> <%= cita.getPropietario()%></p>
                                        <p><strong>Tipo cita:</strong> <%= cita.getTipoCita()%></p>
                                        <p><strong>Estatus:</strong> <%= cita.getEstatus() == 0 ? "ACTIVO" : "INACTIVO"%></p>
                                        <p><strong>Mascota:</strong> <%= cita.getMascota()%></p>
                                    </div>
                                    <div class="card-action">
                                        <a href="Cita?accion=edit&id=<%= cita.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i> Modificar
                                        </a>
                                        <a href="Cita?accion=details&id=<%= cita.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i> Ver
                                        </a>
                                        <a href="Cita?accion=delete&id=<%= cita.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i> Eliminar
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <% }%>
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
