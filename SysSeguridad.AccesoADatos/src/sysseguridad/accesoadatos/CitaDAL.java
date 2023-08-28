package sysseguridad.accesoadatos;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import static sysseguridad.accesoadatos.UsuarioDAL.encriptarMD5;
import static sysseguridad.accesoadatos.UsuarioDAL.obtenerCampos;
import static sysseguridad.accesoadatos.UsuarioDAL.querySelect;
import sysseguridad.entidadesdenegocio.*;

/**
 *
 * @author rober.salama
 */
public class CitaDAL {

    //metodo para obtener datos de la tabal cita
    static String obtenerCampos() {
        return "u.Id, u.IdMascota, u.IdUsuario, u.fecha, u.Diagnostico, u.Direccion, u.Propietario, u.TipoCita";
    }

    //metodo para obtener el SELECT a la tabla cita
    private static String obtenerSelect(Cita pcita) {
        String sql = "SELECT ";
        if (pcita.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pcita.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Cita c ");
        // Agregar información de tablas relacionadas
//    sql += "JOIN Usuario u ON c.IdUsuario = u.id ";
//    sql += "JOIN Mascota m ON c.IdMascota = m.id ";
        return sql;
    }

    //metodo para obtener order by a la consulta select
    private static String agregarOrderBy(Cita pCita) {
        String sql = " ORDER BY u.Id DESC";
        if (pCita.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pCita.getTop_aux() + " ";  // Corregido aquí
        }
        return sql;
    }
    
    //metodo para verificar si existe mascota
   private static boolean existeMAscota(Cita pCita) throws Exception {
    boolean existe = false;
    ArrayList<Cita> citas = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = obtenerSelect(pCita);  // Obtener la consulta SELECT de la tabla Usuario
        // Concatenar a la consulta SELECT de la tabla Usuario el WHERE y el filtro para saber si existe el login
        sql += " WHERE u.IdMascota=?"; // Corregir aquí para verificar por IdMascota
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setInt(1, pCita.getIdMascota());  // Corregir aquí para obtener el IdMascota
            obtenerDatos(ps, citas);
        } catch (SQLException ex) {
            throw ex;
        }
    } catch (SQLException ex) {
        throw ex;
    }

    if (!citas.isEmpty()) { // Verificar si el ArrayList de Usuario trae más de un registro en tal caso solo debe de traer uno
        existe = true;
    }
    return existe;
}

  
    // Metodo para poder insertar un nuevo registro en la tabla de Usuario
    public static int crear(Cita pCita) throws Exception {
        int result;
        String sql;
        boolean existe = existeMAscota(pCita); // verificar si el usuario que se va a crear ya existe en nuestra base de datos
        if (existe == false) {
            try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
                 //Definir la consulta INSERT a la tabla de Usuario utilizando el simbolo "?" para enviar parametros
                sql = "INSERT INTO Usuario(IdRol,Nombre,Apellido,Login,Password,Estatus,FechaRegistro) VALUES(?,?,?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                    ps.setInt(1, pCita.getId()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                    ps.setString(2, pCita.getDiagnostico()); // Agregar el parametro a la consulta donde estan el simbolo "?" #2 
                    ps.setString(3, pCita.getDireccion()); // agregar el parametro a la consulta donde estan el simbolo "?" #3 
                    ps.setString(4, pCita.getTipoCita()); // agregar el parametro a la consulta donde estan el simbolo "?" #4 
                    
                    ps.setByte(5, pCita.getEstatus()); // agregar el parametro a la consulta donde estan el simbolo "?" #6 
                    ps.setDate(6, java.sql.Date.valueOf(LocalDate.now())); // agregar el parametro a la consulta donde estan el simbolo "?" #7 
                    result = ps.executeUpdate(); // ejecutar la consulta INSERT en la base de datos
                    ps.close(); // cerrar el PreparedStatement
                } catch (SQLException ex) {
                    throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
                }
                conn.close();
            } // Handle any errors that may have occurred.
            catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al obtener la conexion en el caso que suceda
            }
        } else {
            result = 0;
            throw new RuntimeException("Login ya existe"); // enviar una exception para notificar que el login existe
        }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }
    
    // Metodo para ejecutar el ResultSet de la consulta SELECT a la tabla de cita
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Cita> pCitas) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS)) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de las filas que regresa la consulta SELECT a la tabla de Usuario
                Cita cita = new Cita();
                // Llenar las propiedades de la Entidad Cita con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(cita, resultSet, 0);
                pCitas.add(cita); // agregar la entidad Cita al ArrayList de Cita
            }
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB en el caso que suceda 
        }
    }

    
    // Metodo para poder actualizar un registro en la tabla de cita
   // Metodo para poder actualizar un registro en la tabla de cita
public static int modificar(Cita pCita) throws Exception {
    int result;
    String sql;
    
//        boolean existe = existeMascota(pCita);
    
//    if (!existe) {
        try (Connection conn = ComunDB.obtenerConexion()) {
            sql = "UPDATE Cita SET IdMascota=?, IdUsuario=?, Fecha=?, Diagnostico=?, Direccion=?, Propietario=?, TipoCita=? WHERE Id=?";
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, pCita.getIdMascota());
                ps.setInt(2, pCita.getIdUsuario());
                ps.setString(3, pCita.getFecha());
                ps.setString(4, pCita.getDiagnostico());
                ps.setString(5, pCita.getDireccion());
                ps.setString(6, pCita.getPropietario());
                ps.setByte(7, Byte.parseByte(pCita.getTipoCita()));
                ps.setInt(8, pCita.getId());
                
                result = ps.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
//    } else {
//        throw new RuntimeException("Cita ya existe");
//    }
    
    return result;
}


    // Método para poder eliminar un registro en la tabla de Cita
    public static int eliminar(Cita pCita) throws SQLException {
        int result;
        String sql;

        try (Connection conn = ComunDB.obtenerConexion()) { // Obtener la conexión desde la clase ComunDB y encerrarla en try-with-resources para cierre automático
            sql = "DELETE FROM Cita WHERE Id=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) { // Obtener el PreparedStatement desde la conexión
                ps.setInt(1, pCita.getId()); // Agregar el parámetro a la consulta donde está el símbolo ? #1 
                result = ps.executeUpdate(); // Ejecutar la consulta DELETE en la base de datos
            } catch (SQLException ex) {
                throw new SQLException("Error al eliminar la cita", ex); // Lanzar una excepción más específica con un mensaje claro
            }
        } catch (SQLException ex) {
            throw new SQLException("Error al obtener la conexión para eliminar la cita", ex); // Lanzar una excepción más específica con un mensaje claro
        }
        return result; // Retornar el número de filas afectadas en el DELETE en la base de datos
    }

    // El método asignará los datos del ResultSet a un objeto Cita y retornará el índice actualizado
    static int asignarDatosResultSet(Cita pCita, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pCita.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pCita.setIdMascota(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pCita.setIdUsuario(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pCita.setFecha(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pCita.setDiagnostico(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pCita.setDireccion(pResultSet.getString(pIndex)); // index 6
        pIndex++;
        pCita.setPropietario(pResultSet.getString(pIndex)); // index 7
        pIndex++;
        pCita.setTipoCita(pResultSet.getString(pIndex)); // index 8
        return pIndex;
    }


// Metodo para ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario y JOIN a la tabla de mascota
    private static void obtenerDatosIncluirMascota(PreparedStatement pPS, ArrayList<Cita> pcitas) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS)) {
            HashMap<Integer, Mascota> mascotaMap = new HashMap();
            while (resultSet.next()) {
                Cita cita = new Cita();

                int index = asignarDatosResultSet(cita, resultSet, 0);
                if (mascotaMap.containsKey(cita.getIdMascota()) == false) { // verificar que el HashMap aun no contenga la Mascota actual
                    Mascota mascota = new Mascota();

                    MascotaDAL.asignarDatosResultSet(mascota, resultSet, index);
                    mascotaMap.put(mascota.getId(), mascota);
                    cita.setMascota(mascota);
                } else {

                    cita.setMascota(mascotaMap.get(cita.getIdMascota()));
                }
                pcitas.add(cita); // Agregar la Cita de la fila actual al ArrayList de Cita
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
        // Metodo para obtener por Id un registro de la tabla de Cita 

        // Metodo para obtener por Id un registro de la tabla de Cita 
public static Cita obtenerPorId(Cita pCita) throws Exception {
    Cita cita = null;
    ArrayList<Cita> citas = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion()) {
        String sql = obtenerSelect(pCita); // obtener la consulta SELECT de la tabla Cita
        sql += " WHERE c.Id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pCita.getId());
            obtenerDatos(ps, citas);
        } catch (SQLException ex) {
            throw ex;
        }
    } catch (SQLException ex) {
        throw ex;
    }

    if (!citas.isEmpty()) { // Corregir la verificación, debe ser si NO está vacío
        cita = citas.get(0); // Obtener el primer registro de la lista
    }

    return cita;
}



// Metodo para obtener todos los registro de la tabla de cita
    public static ArrayList<Cita> obtenerTodos() throws Exception {
        ArrayList<Cita> citas;
        citas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Cita()); // obtener la consulta SELECT de la tabla Usuario
            sql += agregarOrderBy(new Cita()); // concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, citas); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return citas; // devolver el ArrayList de Usuario
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de cita de forma dinamica
    static void querySelect(Cita pCita, ComunDB.UtilQuery pUtilQuery) throws SQLException {
    PreparedStatement statement = pUtilQuery.getStatement();

    if (pCita.getId() > 0) {
        pUtilQuery.AgregarWhereAnd("u.Id = ?");
        if (statement != null) {
            statement.setInt(pUtilQuery.getNumWhere(), pCita.getId());
        }
    }

    if (pCita.getIdMascota() > 0) {
        pUtilQuery.AgregarWhereAnd("u.IdMascota = ?");
        if (statement != null) {
            statement.setInt(pUtilQuery.getNumWhere(), pCita.getIdMascota());
        }
    }

    if (pCita.getDiagnostico() != null && !pCita.getDiagnostico().trim().isEmpty()) {
    pUtilQuery.AgregarWhereAnd("u.Diagnostico LIKE ?");
    if (statement != null) {
        statement.setString(pUtilQuery.getNumWhere(), "%" + pCita.getDiagnostico() + "%");
    }
}


    if (pCita.getDireccion() != null && !pCita.getDireccion().trim().isEmpty()) {
    pUtilQuery.AgregarWhereAnd("u.Direccion LIKE ?");
    if (statement != null) {
        statement.setString(pUtilQuery.getNumWhere(), "%" + pCita.getDireccion() + "%");
    }
}


    if (pCita.getFecha() != null && !pCita.getFecha().trim().isEmpty()) {
    pUtilQuery.AgregarWhereAnd("u.Fecha = ?");
    if (statement != null) {
        statement.setString(pUtilQuery.getNumWhere(), pCita.getFecha());
    }
}


    if (pCita.getEstatus() > 0) {
        pUtilQuery.AgregarWhereAnd("u.Estatus = ?");
        if (statement != null) {
            statement.setInt(pUtilQuery.getNumWhere(), pCita.getEstatus());
        }
    }
}

    // Metodo para obtener todos los registro de la tabla de Cita que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de cita 
    public static ArrayList<Cita> buscar(Cita pCita) throws Exception {
        ArrayList<Cita> citas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pCita); // obtener la consulta SELECT de la tabla Usuario
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pCita, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pCita); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pCita, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatos(ps, citas); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return citas; // Devolver el ArrayList de Usuario
    }
    
    // Metodo para obtener todos los registro de la tabla de Cita que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Cita 
    public static ArrayList<Cita> buscarIncluirMascota(Cita pCita) throws Exception {
        ArrayList<Cita> citas = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
            if (pCita.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pCita.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
            }
            sql += obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
            sql += ",";
            sql += RolDAL.obtenerCampos(); // Obtener los campos de la tabla de Rol que iran en el SELECT
            sql += " FROM Cita u";
            sql += " JOIN Mascota r on (u.IdMascota=u.IdMascota)"; // agregar el join para unir la tabla de Usuario con Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pCita, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pCita); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pCita, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirMascota(ps, citas);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return citas; // Devolver el ArrayList de Usuario
    }

//    private static String obtenerSelect(Mascota pMascota) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    private static boolean existeCita(Cita pCita) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    private static boolean existeMascota(Cita pCita) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
