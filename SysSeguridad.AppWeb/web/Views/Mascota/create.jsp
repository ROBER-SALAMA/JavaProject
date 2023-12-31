<%-- 		CREAR
 
    Created on : 21 ago. 2023, 15:14:27

    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<%@page import="sysseguridad.accesoadatos.UsuarioDAL"%>

<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Mascota</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Mascota</h5>
            <form action="Mascota" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtSexo" type="text" name="sexo" required class="validate" maxlength="30">
                        <label for="txtSexo">Sexo</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtEdad" type="text" name="edad" required class="validate" maxlength="25">
                        <label for="txtEdad">Edad</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtRaza" type="text" name="raza" required class="validate" minlength="5" maxlength="32">
                        <label for="txtRaza">Raza</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtSeniales particulares" type="Text" name="senialesparticulares" required class="validate" minlength="5" maxlength="32">
                        <label for="txtSeniales particulares">Seniales particulares</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtEspecie" type="text" name="especie" required class="validate" minlength="5" maxlength="32">
                        <label for="txtEspecie">Especie</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPropietario" type="text" name="propietario" required class="validate" minlength="5" maxlength="32">
                        <label for="txtEspecie">Propietario</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Usuario/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slUsuario_error" style="color:red" class="helper-text"></span>
                    </div>
                    
                </div>

                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Mascota" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />   

    </body>
</html>


