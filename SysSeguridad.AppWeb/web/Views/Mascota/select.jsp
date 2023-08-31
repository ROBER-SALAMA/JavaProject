<%-- 
    Document   : select
    Created on : 30 ago. 2023, 16:25:09
    Author     : Rober.salama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<%@page import="sysseguridad.accesoadatos.MascotaDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Mascota> mascotas = MascotaDAL.obtenerTodos();
    int id = 0; 
    String idParam = request.getParameter("id");
    if (idParam != null && !idParam.isEmpty()) {
        id = Integer.parseInt(idParam);
    }

%>
<select id="slMascota" name="idUsuario">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Mascota mascota : mascotas) {%>
    <option <%=(id == mascota.getId()) ? "selected" : ""%>  value="<%=mascota.getId()%>"><%= mascota.getNombre()%></option>
    <%}%>
</select>
<label for="idMascota">Mascota</label>