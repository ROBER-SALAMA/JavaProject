/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sysseguridad.appweb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import sysseguridad.accesoadatos.MascotaDAL;
import sysseguridad.accesoadatos.CitaDAL;


import sysseguridad.appweb.utils.*;
import sysseguridad.entidadesdenegocio.Mascota;
import sysseguridad.entidadesdenegocio.Cita;


@WebServlet(name = "CitaServlet", urlPatterns = {"/Cita"})
public class CitaServlet extends HttpServlet {

    private Cita obtenerCita(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Cita cita = new Cita();
        
        cita.setFecha(Utilidad.getParameter(request, "fecha", ""));
        cita.setDiagnostico(Utilidad.getParameter(request, "diagnostico", ""));
        cita.setDireccion(Utilidad.getParameter(request, "direccion", ""));
        cita.setPropietario(Utilidad.getParameter(request, "propietario", ""));
        cita.setTipoCita(Utilidad.getParameter(request, "tipocita", ""));
        cita.setIdMascota(Integer.parseInt(Utilidad.getParameter(request, "IdMascota", "")));
//        cita.setIdUsuario(Integer.parseInt(Utilidad.getParameter(request, "IdUsuario", accion)));
        cita.setEstatus(Byte.parseByte(Utilidad.getParameter(request, "estatus", "0")));
        if (accion.equals("index")) {
            cita.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            cita.setTop_aux(cita.getTop_aux() == 0 ? Integer.MAX_VALUE : cita.getTop_aux());
        }
      /*  if (accion.equals("login") || accion.equals("create") || accion.equals("cambiarpass")) 
            cita.setPassword(Utilidad.getParameter(request, "password", ""));
            
            usuario.setConfirmPassword_aux(Utilidad.getParameter(request, "confirmPassword_aux", ""));
            if (accion.equals("cambiarpass")) {
             
                usuario.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
            }*/
         else {
            
            cita.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }   
         
         return cita;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cita cita = new Cita(); 
            cita.setTop_aux(10); 
            
            ArrayList<Cita> citas = CitaDAL.buscarIncluirMascota(cita);
            
           
            request.setAttribute("citas", citas);
            
            request.setAttribute("top_aux", cita.getTop_aux());
            request.getRequestDispatcher("Views/Cita/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
    
     private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cita cita = obtenerCita(request); 
            ArrayList<Cita> citas = CitaDAL.buscarIncluirMascota(cita);
            
            request.setAttribute("citas", citas);
            
            request.setAttribute("top_aux", cita.getTop_aux());
            request.getRequestDispatcher("Views/Cita/index.jsp").forward(request, response); 
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); 
        }
    }
     
     private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        request.getRequestDispatcher("Views/Cita/create.jsp").forward(request, response);
    }

     private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cita cita = obtenerCita(request); 
           
            int result = CitaDAL.crear(cita);
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
            Cita cita = obtenerCita(request); 
            Cita cita_result = CitaDAL.obtenerPorId(cita); 
            if (cita_result.getId() > 0) { 
                Mascota mascota = new Mascota();
                mascota.setId(cita_result.getIdMascota());
                
                cita_result.setMascota(MascotaDAL.obtenerPorId(mascota));
                
                request.setAttribute("cita", cita_result);
            } else {
                
                Utilidad.enviarError("El Id:" + cita_result.getId() + " no existe en la tabla de Cita", request, response);
            }
        } catch (Exception ex) {
            
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
      
      private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        requestObtenerPorId(request, response);
       
        request.getRequestDispatcher("Views/Cita/edit.jsp").forward(request, response);
    }
      
       private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cita cita = obtenerCita(request); 
            int result = CitaDAL.modificar(cita);
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
        
        request.getRequestDispatcher("Views/Cita/details.jsp").forward(request, response);
    }
        
         private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        requestObtenerPorId(request, response);
      
        request.getRequestDispatcher("Views/Cita/delete.jsp").forward(request, response);
    }
         
         private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cita cita = obtenerCita(request); 
            int result = CitaDAL.eliminar(cita);
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
        
        SessionUser.authorize(request, response, () -> { 
            String accion = Utilidad.getParameter(request, "accion", "index");
            
            switch (accion) {
                case "index":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); 
                    break;
                case "create":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response); 
                    break;
                case "edit":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response); 
                    break;
                case "details":
                    
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response); 
                    break;
                default:
                   
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); 
            }
        });
    }

    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones post que se realice al Servlet Rol
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Rol
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Rol que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
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

