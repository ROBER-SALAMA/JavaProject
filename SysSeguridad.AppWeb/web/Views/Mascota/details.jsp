<%@page import="sysseguridad.entidadesdenegocio.Mascota" %>
<%Mascota mascota = (Mascota) request.getAttribute("Mascota");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Mascota</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Mascota</h5>
             <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" value="<%=mascota.getNombre()%>" disabled>
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtSexo" type="text" value="<%=mascota.getSexo()%>" disabled>
                        <label for="txtSexo">Sexo</label>
                    </div>
                    <div class="input-field col l4 s12">
                       <input  id="txtEdad" type="text" value="<%=mascota.getEdad()%>" disabled>
                       <label for="txtEdad">Edad</label>
                    </div>
                     <div class="input-field col l4 s12">
                        <input  id="txtRaza" type="text" value="<%=mascota.getRaza()%>" disabled>
                        <label for="txtRaza">Raza</label>
                    </div>
                      <div class="input-field col l4 s12">
                        <input  id="txtSenialesParticulares" type="text" value="<%=mascota.getSenialesParticulares()%>" disabled>
                        <label for="txtSenialesParticulares">Seniales Particulares</label>
                      </div>
                   <div class="input-field col l4 s12">
                       <input  id="txtEspecie" type="text" value="<%=mascota.getEspecie()%>" disabled>
                       <label for="txtEspecie">Especie</label>
                   </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPropietario" type="text" value="<%=mascota.getPropietario()%>" disabled>
                        <label for="txtPropietario">Propietario</label>
                    </div>
                     <div class="input-field col l4 s12">
                        <input id="txtUsuario" type="text" value="<%=mascota.getUsuario().getNombre() %>" disabled>
                        <label for="txtUsuario">Usuario</label>
                    </div> 
                 </div>

                <div class="row">
                    <div class="col l12 s12">
                         <a href="Mascota?accion=edit&id=<%=mascota.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="Mascota" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />                
    </body>
</html>
