<%-- 
    Document   : index
    Created on : 21 ago. 2023, 14:37:15
    Author     : Rober.salama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Cita"%>
<%@page import="sysseguridad.entidadesdenegocio.Mascota"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Cita> citas = (ArrayList<Cita>) request.getAttribute("usuarios");
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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <jsp:include page="/Views/Shared/title.jsp" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <title>Cita</title>
</head>

<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <center>
        <h2>Historial de consultas</h2>
    </center>
    <hr />
    <div class="container text-center">
        <div class="row">
            <div class="col">
                <div class="container-input">
                    <input type="text" placeholder="Buscar" name="text" class="input">
                    <!-- <svg fill="#000000" width="20px" height="20px" viewBox="0 0 1920 1920" xmlns="http://www.w3.org/2000/svg"> -->
                    <path
                        d="M790.588 1468.235c-373.722 0-677.647-303.924-677.647-677.647 0-373.722 303.925-677.647 677.647-677.647 373.723 0 677.647 303.925 677.647 677.647 0 373.723-303.924 677.647-677.647 677.647Zm596.781-160.715c120.396-138.692 193.807-319.285 193.807-516.932C1581.176 354.748 1226.428 0 790.588 0S0 354.748 0 790.588s354.748 790.588 790.588 790.588c197.647 0 378.24-73.411 516.932-193.807l516.028 516.142 79.963-79.963-516.142-516.028Z"
                        fill-rule="evenodd"></path>
                    </svg>
                </div>
            </div>
            <div class="col">
                <select class="form-select" aria-label="Default select example">
                    <option selected>Raza</option>
                    <option value="1">perro</option>
                    <option value="2">gato</option>
                    <option value="3">reptil</option>
                </select>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col l12 s12">
                        <a id="crear" href="Cita?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br><br>
    <div class="container text-center">
        <div class="row">
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <img src="https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg"
                        class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">PERRO</h5>
                        <p class="card-text">El perro es un mamífero leal y social, conocido por su inteligencia y
                            capacidad de adaptación. Ha sido el mejor amigo del hombre durante miles de años.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <img src="https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg"
                        class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">PERRO</h5>
                        <p class="card-text">El perro es un mamífero leal y social, conocido por su inteligencia y
                            capacidad de adaptación. Ha sido el mejor amigo del hombre durante miles de años.</p>
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <img src="https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg"
                        class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">PERRO</h5>
                        <p class="card-text">El perro es un mamífero leal y social, conocido por su inteligencia y
                            capacidad de adaptación. Ha sido el mejor amigo del hombre durante miles de años.</p>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#staticBackdrop">
                            Ver Historial
                        </button>


                        <!-- Modal -->
                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false"
                            tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Historias de Mascotas en
                                            la Clínica Veterinaria</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Fecha</th>
                                                    <th>Descripción</th>
                                                    <th>Tipo de Cita</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>15 de abril de 2023</td>
                                                    <td>Esto es una descripcion Esto es una descripcion Esto es una
                                                        descripcion.</td>
                                                    <td>
                                                        <!-- Modal -->
                                                        <div class="modal fade" id="staticBackdrop"
                                                            data-bs-backdrop="static" data-bs-keyboard="false"
                                                            tabindex="-1" aria-labelledby="staticBackdropLabel"
                                                            aria-hidden="true">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h1 class="modal-title fs-5"
                                                                            id="staticBackdropLabel">Historias de
                                                                            Mascotas en la Clínica Veterinaria</h1>
                                                                        <button type="button" class="btn-close"
                                                                            data-bs-dismiss="modal"
                                                                            aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <table class="table table-striped">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Fecha</th>
                                                                                    <th>Descripción</th>
                                                                                    <th>Tipo de Cita</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <tr>
                                                                                    <td>15 de abril de 2023</td>
                                                                                    <td>Esto es una descripcion</td>
                                                                                    <td>Aventura / Drama</td>
                                                                                </tr>
                                                                                <!-- Agrega más filas con otras historias de mascotas en la clínica veterinaria aquí -->
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary"
                                                                            data-bs-dismiss="modal">Close</button>
                                                                        <button type="button"
                                                                            class="btn btn-primary">Understood</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>Revision
                                                    </td>
                                                </tr>
                                                <!-- Agrega más filas con otras historias de mascotas en la clínica veterinaria aquí -->
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Understood</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <style> 
    .container-input {
    position: relative;
  }
  
  .input {
    width: 150px;
    padding: 10px 0px 10px 40px;
    border-radius: 9999px;
    border: solid 1px #333;
    transition: all .2s ease-in-out;
    outline: none;
    opacity: 0.8;
  }
  
  .container-input svg {
    position: absolute;
    top: 50%;
    left: 10px;
    transform: translate(0, -50%);
  }
  
  .input:focus {
    opacity: 1;
    width: 250px;
  }

  .form-select{
    border-radius: 30px;
  }
  
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
</body>

</html>
