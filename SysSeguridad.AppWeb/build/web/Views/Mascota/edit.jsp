<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<% Mascota mascota = (Mascota) request.getAttribute("Mascota");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Mascota</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Usuario</h5>
            <form action="Mascota" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=mascota.getId()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=mascota.getNombre()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtSexo" type="text" name="Sexo" value="<%=mascota.getSexo()%>" required class="validate" maxlength="30">
                        <label for="txtSexo">Sexo</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtEdad" type="text" name="Edad" value="<%=mascota.getEdad()%>" required  class="validate" maxlength="25">
                        <label for="txtEdad">Edad</label>
                    </div>                     
                    <div class="input-field col l4 s12">
                        <input  id="txtRaza" type="text" name="Raza" value="<%=mascota.getRaza()%>" required  class="validate" maxlength="25">
                        <label for="txtRaza">Raza</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtSenialesParticulares" type="text" name="SenialesParticulares" value="<%=mascota.getSenialesParticulares()%>" required  class="validate" maxlength="25">
                        <label for="txtSenialesParticulares">SenialesParticulares</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtEspecie" type="text" name="Especie" value="<%=mascota.getEspecie()%>" required  class="validate" maxlength="25">
                        <label for="txtEspecie">Especie</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPropietario" type="text" name="Propietario" value="<%=mascota.getPropietario()%>" required  class="validate" maxlength="25">
                        <label for="txtPropietario">Propietario</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Usuario/select.jsp">                           
                            <jsp:param name="id" value="<%=mascota.getIdUsuario() %>" />  
                        </jsp:include>  
                        <span id="slUsuario_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Masota" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slEstatus = document.getElementById("slEstatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slUsuario = document.getElementById("slUsuario");
                var slUsuario_error = document.getElementById("slUsuario_error");
                if (slEstatus.value == 0) {
                    slEstatus_error.innerHTML = "El estatus es obligatorio";
                    result = false;
                } else {
                    slEstatus_error.innerHTML = "";
                }
                if (slUsuario.value == 0) {
                    slUsuario_error.innerHTML = "El Usuario es obligatorio";
                    result = false;
                } else {
                    slUsuario_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
