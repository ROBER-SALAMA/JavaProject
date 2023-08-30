<%-- 
    Document   : select
    Created on : 30 ago. 2023, 16:25:09
    Author     : Rober.salama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<%@page import="sysseguridad.accesoadatos.MascotaDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Mascota> roles = MascotaDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slRol" name="idRol">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Mascota mascota : roles) {%>
    <option <%=(id == mascota.getId()) ? "selected" : ""%>  value="<%=mascota.getId()%>"><%= mascota.getNombre()%></option>
    <%}%>
</select>
<label for="idMascota">Mascota</label>