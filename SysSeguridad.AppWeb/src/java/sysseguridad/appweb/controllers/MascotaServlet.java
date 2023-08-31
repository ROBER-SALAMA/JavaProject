/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sysseguridad.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import sysseguridad.accesoadatos.MascotaDAL;
import sysseguridad.accesoadatos.UsuarioDAL;
import sysseguridad.appweb.utils.*;
import sysseguridad.entidadesdenegocio.Mascota;
import sysseguridad.entidadesdenegocio.Usuario;

/**
 *
 * @author jacqu
 */
@WebServlet(name = "MascotaServlet", urlPatterns = {"/Mascota"})
public class MascotaServlet extends HttpServlet {
    
    private Mascota obtenerMascota(HttpServletRequest request){
        String accion = Utilidad.getParameter(request, "accion", "index");
        Mascota mascota = new Mascota();
        
        mascota.setNombre(Utilidad.getParameter(request, "nombre", ""));
        mascota.setEdad(Utilidad.getParameter(request, "edad", ""));
        mascota.setSexo(Utilidad.getParameter(request, "sexo", ""));
        mascota.setEdad(Utilidad.getParameter(request, "edad", ""));
        //error
        mascota.setRaza(Utilidad.getParameter(request, "raza", ""));
        mascota.setSenialesParticulares(Utilidad.getParameter(request, "senialesparticulares", ""));
        mascota.setEspecie(Utilidad.getParameter(request, "especie", ""));
        mascota.setPropietario(Utilidad.getParameter(request, "propietario", ""));
        mascota.setIdUsuario(Integer.parseInt(Utilidad.getParameter(request, "IdUsuario", "0")));
        return mascota;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Mascota mascota = new Mascota();
            mascota.setTop_aux(10); 
            
            ArrayList<Mascota> mascotas = MascotaDAL.buscarIncluirUsuario(mascota);
        
            request.setAttribute("mascotas", mascotas);
            request.setAttribute("top_aux", mascota.getTop_aux());
            request.getRequestDispatcher("Views/Mascota/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
    
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Mascota mascota = obtenerMascota(request);
            ArrayList<Mascota> mascotas = MascotaDAL.buscarIncluirUsuario(mascota);
            request.setAttribute("mascotas", mascotas);
            
            request.setAttribute("top_aux", mascota.getTop_aux());
            request.getRequestDispatcher("Views/Mascota/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Mascota/create.jsp").forward(request, response);
    }
    
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Mascota mascota = obtenerMascota(request); 
            int result = MascotaDAL.crear(mascota);
            if (result != 0) { 
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); 
            } else {
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    
    private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Mascota mascota = obtenerMascota(request); 
            Mascota mascota_result = MascotaDAL.obtenerPorId(mascota); 
            if (mascota_result.getId() > 0) { 
                Usuario usuario = new Usuario();
                usuario.setId(mascota_result.getIdUsuario());
      
                mascota_result.setUsuario(UsuarioDAL.obtenerPorId(usuario));
                
                request.setAttribute("mascota", mascota_result);
            } else {
                Utilidad.enviarError("El Id:" + mascota_result.getId() + " no existe en la tabla de Mascota", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        requestObtenerPorId(request, response);
       
        request.getRequestDispatcher("Views/Mascota/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Mascota mascota = obtenerMascota(request); 
            int result = MascotaDAL.modificar(mascota);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); 
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Mascota/details.jsp").forward(request, response);
    }
    
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Mascota/delete.jsp").forward(request, response);
    }
    
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Mascota mascota = obtenerMascota(request); 
            
            int result = MascotaDAL.eliminar(mascota);
            if (result != 0) { 
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); 
            } else {
         
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     //servle mascota terminado
     
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Rol. Todo el codigo que este dentro  expresion Lambda, se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        SessionUser.authorize(request, response, () -> { // Expresion Lambda  
            // Obtener el parámetro accion del request
            String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "index":
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
                    break;
                case "create":
                    // Enviar el atributo accion al jsp de create.
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                    break;
                case "edit":
                    // Enviar el atributo accion al jsp de edit.
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);// Ir al metodo doGetRequestEdit.
                    break;
                case "delete":
                    // Enviar el atributo accion al jsp de delete.
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response); // Ir al metodo doGetRequestDelete.
                    break;
                case "details":
                    // Enviar el atributo accion al jsp de details.
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response); // Ir al metodo doGetRequestDetails.
                    break;
                default:
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });
    }
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Rol. Todo el codigo que este dentro  expresion Lambda,  se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        SessionUser.authorize(request, response, () -> {
            // Obtener el parámetro accion del request.
            String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "index":
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
                    break;
                case "create":
                    // Enviar el atributo accion al jsp de create.
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response); // Ir al metodo doPostRequestCreate.
                    break;
                case "edit":
                    // Enviar el atributo accion al jsp de edit.
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response); // Ir al metodo doPostRequestEdit.
                    break;
                case "delete":
                    // Enviar el atributo accion al jsp de delete.
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response); // Ir al metodo doPostRequestDelete.
                    break;
                default:
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });
    }
}

    
