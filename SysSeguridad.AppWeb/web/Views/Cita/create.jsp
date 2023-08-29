<%-- 
    Document   : create
    Created on : 21 ago. 2023, 14:39:07
    Author     : Rober.salama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Cita"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Cita</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
        <h1>Crear Cita</h1>
        <form action="Cita" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtFecha" type="text" name="fecha" required class="validate" maxlength="30">
                        <label for="txtFecha">Fecha</label>
                    </div>
                  <div class="input-field col l4 s12">
                        <input  id="txtDiagnostico" type="text" name="diagnostico" required class="validate" maxlength="30">
                        <label for="txtDiagnostico">Diagnostico</label>
                    </div> 
                     <div class="input-field col l4 s12">
                        <input  id="txtDireccion" type="text" name="direccion" required class="validate" maxlength="30">
                        <label for="txtDireccion">Direccion</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtPropietario" type="text" name="propietario" required class="validate" maxlength="25">
                        <label for="txtPropietario">Propietario</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtTipoCita" type="text" name="tipocita" required class="validate" maxlength="25">
                        <label for="txtTipoCita">TipoCita</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <select id="slEstatus" name="estatus" class="validate">
                            <div><option value="0">SELECCIONAR</option>
                            <option value="<%=Cita.EstatusCita.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Cita.EstatusCita.INACTIVO%>">INACTIVO</option></div>
                        </select>       
                        <label for="slEstatus">Estatus</label>
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
                    </div>
                      <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Mascota/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slMascota_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>  
                
                 <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Cita" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
                 </form>          
             </main>
            <jsp:include page="/Views/Shared/footerBody.jsp" />  
            
            <<script>
                function validarFormulario() {
                var result = true;
                var slEstatus = document.getElementById("slEstatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slRol = document.getElementById("slMascota");
                var slRol_error = document.getElementById("slMascota_error");
                if (slEstatus.value == 0) {
                    slEstatus_error.innerHTML = "El estatus es obligatorio";
                    result = false;
                } else {
                    slEstatus_error.innerHTML = "";
                }
                if (slMascota.value == 0) {
                    slRol_error.innerHTML = "Macota es obligatorio";
                    result = false;
                } else {
                    slRol_error.innerHTML = "";
                }

                return result;
            }
        </script>
            </script>
    </body>
</html>
