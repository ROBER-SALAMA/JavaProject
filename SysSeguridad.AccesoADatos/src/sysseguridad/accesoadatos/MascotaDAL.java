/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sysseguridad.accesoadatos;
import java.util.*;
import java.sql.*;
import sysseguridad.entidadesdenegocio.*; // Agregar la referencia al proyecto de entidades de negocio y poder utilizar las entidades de Categoria y UProducto


/**
 *
 * @author MINEDUCYT
 */
public class MascotaDAL {
   


    static String obtenerCampos() {
        return "m.Id, m.IdUsuario, m.Nombre, m.Sexo, m.Edad, m.Raza, m.SenialesParticulares, m.Especie, m.Propietario";
    }

    // Method to get the SELECT query for the Mascota table
    private static String obtenerSelect(Mascota pMascota) {
        String sql = "SELECT " + obtenerCampos() + " FROM Mascota m";
        return sql;
    }

    // Method to add ORDER BY clause to the SELECT query for the Mascota table
    private static String agregarOrderBy(Mascota pMascota) {
        String sql = " ORDER BY m.Id DESC";
        return sql;
    }

    private static boolean existeNombre(Mascota pMascota) throws Exception {
        boolean existe = false;
        ArrayList<Mascota> mascotas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pMascota);
            sql += " WHERE m.Id<>? AND m.Nombre=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pMascota.getId());
                ps.setString(2, pMascota.getNombre());
                obtenerDatos(ps, mascotas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        if (mascotas.size() > 0) {
            Mascota mascota = mascotas.get(0);
            if (mascota.getId() > 0 && mascota.getNombre().equals(pMascota.getNombre())) {
                existe = true;
            }
        }
        return existe;
    }
    public static int crear(Mascota pMascota) throws Exception {
        int result;
        String sql;
        boolean existe = existeNombre(pMascota);
        if (!existe) {
            try (Connection conn = ComunDB.obtenerConexion();) {
                sql = "INSERT INTO Mascota(IdUsuario,Nombre,Sexo,Edad,Raza,SenialesParticulares,Especie,Propietario) VALUES(?,?,?,?,?,?,?,?)";
                try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                    ps.setInt(1, pMascota.getIdUsuario());
                    ps.setString(2, pMascota.getNombre());
                    ps.setString(3, pMascota.getSexo());
                   ps.setString(4, pMascota.getEdad());
                    ps.setString(5, pMascota.getRaza());
                    ps.setString(6, pMascota.getSenialesParticulares());
                    ps.setString(7, pMascota.getEspecie());
                    ps.setString(8, pMascota.getPropietario());
                    result = ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    throw ex;
                }
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        } else {
            result = 0;
            throw new RuntimeException("Nombre de mascota ya existe");
        }
        return result;
    }
    
    public static int modificar(Mascota pMascota) throws Exception {
    int result;
    String sql;
    boolean existe = existeNombre(pMascota); // Verificar si la mascota que se va a modificar ya existe en la base de datos
    if (!existe) {
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Mascota SET IdUsuario=?, Nombre=?, Sexo=?, Edad=?, Raza=?, SenialesParticulares=?, Especie=?, Propietario=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pMascota.getIdUsuario());
                ps.setString(2, pMascota.getNombre());
                ps.setString(3, pMascota.getSexo());
                ps.setString(4, pMascota.getEdad()); 
                ps.setString(5, pMascota.getRaza());
                ps.setString(6, pMascota.getSenialesParticulares());
                ps.setString(7, pMascota.getEspecie());
                ps.setString(8, pMascota.getPropietario());
                ps.setInt(9, pMascota.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
    } else {
        result = 0;
        throw new RuntimeException("Nombre de mascota ya existe");
    }
    return result;
}
    public static int eliminar(Mascota pMascota) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) {
        sql = "DELETE FROM Mascota WHERE Id=?";
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setInt(1, pMascota.getId());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }
        conn.close();
    } catch (SQLException ex) {
        throw ex;
    }
    return result;
}
static int asignarDatosResultSet(Mascota pMascota, ResultSet pResultSet, int pIndex) throws Exception {
    //  SELECT m.Id(indice 1), m.IdUsuario(indice 2), m.Nombre(indice 3), m.Sexo(indice 4), m.Edad(indice 5), 
    // m.Raza(indice 6), m.SenialesParticulares(indice 7), m.Especie(indice 8), m.Propietario(indice 9) * FROM Mascota
    pIndex++;
    pMascota.setId(pResultSet.getInt(pIndex)); // index 1
    pIndex++;
    pMascota.setIdUsuario(pResultSet.getInt(pIndex)); // index 2
    pIndex++;
    pMascota.setNombre(pResultSet.getString(pIndex)); // index 3
    pIndex++;
    pMascota.setSexo(pResultSet.getString(pIndex)); // index 4
    pIndex++;
    pMascota.setEdad(pResultSet.getString(pIndex)); // index 5
    pIndex++;
    pMascota.setRaza(pResultSet.getString(pIndex)); // index 6
    pIndex++;
    pMascota.setSenialesParticulares(pResultSet.getString(pIndex)); // index 7
    pIndex++;
    pMascota.setEspecie(pResultSet.getString(pIndex)); // index 8
    pIndex++;
    pMascota.setPropietario(pResultSet.getString(pIndex)); // index 9
    return pIndex;
}

private static void obtenerDatos(PreparedStatement pPS, ArrayList<Mascota> pMascotas) throws Exception {
    try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
        while (resultSet.next()) {
            Mascota mascota = new Mascota();
            asignarDatosResultSet(mascota, resultSet, 0);
            pMascotas.add(mascota);
        }
        resultSet.close();
    } catch (SQLException ex) {
        throw ex;
    }
}

  // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Usuario y JOIN a la tabla de Rol
    private static void obtenerDatosIncluirUsuario(PreparedStatement pPS, ArrayList<Mascota> pMascotas) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { 
            HashMap<Integer, Usuario> usuarioMap = new HashMap(); 
            while (resultSet.next()) { 
                Mascota mascota = new Mascota();
                 
                int index = asignarDatosResultSet(mascota, resultSet, 0);
                if (usuarioMap.containsKey(mascota.getIdUsuario()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Usuario usuario = new Usuario();
                  
                    UsuarioDAL.asignarDatosResultSet(usuario, resultSet, index);
                    usuarioMap.put(usuario.getId(), usuario); // agregar el Rol al  HashMap
                    mascota.setUsuario(usuario); // agregar el Rol al Usuario
                } else {
                   
                    mascota.setUsuario(usuarioMap.get(mascota.getIdUsuario())); 
                }
                pMascotas.add(mascota); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    public static Mascota obtenerPorId(Mascota pMascota) throws Exception {
    Mascota mascota = new Mascota();
    ArrayList<Mascota> mascotas = new ArrayList<>();
    
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = obtenerSelect(pMascota); // Obtener la consulta SELECT de la tabla Mascota
        sql += " WHERE m.Id=?";
        
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            ps.setInt(1, pMascota.getId()); // Agregar el parámetro a la consulta donde está el símbolo ? #1 
            obtenerDatos(ps, mascotas); // Llenar el ArrayList de Mascota con las filas que devuelve la consulta SELECT a la tabla de Mascota
            ps.close(); // Cerrar el PreparedStatement
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente método el error al ejecutar PreparedStatement en caso de que ocurra
        }
        
        conn.close(); // Cerrar la conexión a la base de datos
    } catch (SQLException ex) {
        throw ex; // Enviar al siguiente método el error al obtener la conexión de la clase ComunDB en caso de que ocurra
    }
    
    if (!mascotas.isEmpty()) {
        mascota = mascotas.get(0); // Si el ArrayList de Mascota trae un registro o más, obtenemos solo el primero
    }
    
    return mascota; // Devolver la mascota encontrada por su ID
}
    
public static ArrayList<Mascota> obtenerTodos() throws Exception {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(new Mascota());
            sql += agregarOrderBy(new Mascota());

            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                obtenerDatos(ps, mascotas);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }

            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }

        return mascotas;
    }

    static void querySelect(Mascota pMascota, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pMascota.getId() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pMascota.getId());
            }
        }
        if (pMascota.getIdUsuario() > 0) {
            pUtilQuery.AgregarWhereAnd(" m.IdUsuario=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pMascota.getIdUsuario());
            }
        }
        if (pMascota.getNombre() != null && !pMascota.getNombre().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.Nombre LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMascota.getNombre() + "%");
            }
        }
        if (pMascota.getSexo() != null && !pMascota.getSexo().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.Sexo=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMascota.getSexo());
            }
        }
        if (pMascota.getEdad() != null && !pMascota.getEdad().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.Edad=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMascota.getEdad());
            }
        }
        if (pMascota.getRaza() != null && !pMascota.getRaza().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.Raza=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMascota.getRaza());
            }
        }
        if (pMascota.getSenialesParticulares() != null && !pMascota.getSenialesParticulares().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.SenialesParticulares LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMascota.getSenialesParticulares() + "%");
            }
        }
        if (pMascota.getEspecie() != null && !pMascota.getEspecie().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.Especie=? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), pMascota.getEspecie());
            }
        }
        if (pMascota.getPropietario() != null && !pMascota.getPropietario().trim().isEmpty()) {
            pUtilQuery.AgregarWhereAnd(" m.Propietario LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pMascota.getPropietario() + "%");
            }
        }
    }
   
    // Metodo para obtener todos los registros de la tabla de Mascota que cumplan con los filtros agregados
    // a la consulta SELECT de la tabla de Mascota
    public static ArrayList<Mascota> buscar(Mascota pMascota) throws Exception {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = obtenerSelect(pMascota); // obtener la consulta SELECT de la tabla Mascota
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pMascota, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Mascota
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pMascota); // Concatenar a la consulta SELECT de la tabla Mascota el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pMascota, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Mascota
                obtenerDatos(ps, mascotas); // Llenar el ArrayList de Mascota con las filas que devolvera la consulta SELECT a la tabla de Mascota
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return mascotas; // Devolver el ArrayList de Mascotas
    }
    
    // Metodo para obtener todos los registros de la tabla de Mascota que cumplan con los filtros agregados
// a la consulta SELECT de la tabla de Mascota y JOIN a la tabla de Usuario
public static ArrayList<Mascota> buscarIncluirUsuario(Mascota pMascota) throws Exception {
    ArrayList<Mascota> mascotas = new ArrayList<>();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = "SELECT "; // Iniciar la variables para el String de la consulta SELECT
        if (pMascota.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pMascota.getTop_aux() + " "; // Agregar el TOP en el caso que se este utilizando SQL SERVER
        }
        sql += obtenerCampos(); // Obtener los campos de la tabla de Mascota que iran en el SELECT
        sql += ",";
        sql += UsuarioDAL.obtenerCampos(); // Obtener los campos de la tabla de Usuario que iran en el SELECT
        sql += " FROM Mascota m";
        sql += " JOIN Usuario u on (m.IdUsuario=u.Id)"; // agregar el join para unir la tabla de Mascota con Usuario
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
        querySelect(pMascota, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Mascota
        sql = utilQuery.getSQL();
        sql += agregarOrderBy(pMascota); // Concatenar a la consulta SELECT de la tabla Mascota el ORDER BY por Id
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
            utilQuery.setStatement(ps);
            utilQuery.setSQL(null);
            utilQuery.setNumWhere(0);
            querySelect(pMascota, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Mascota
            obtenerDatosIncluirUsuario(ps, mascotas); // Llenar el ArrayList de Mascota con las filas que devolvera la consulta SELECT a la tabla de Mascota
            ps.close(); // Cerrar el PreparedStatement
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
        }
        conn.close(); // Cerrar la conexion a la base de datos
    } catch (SQLException ex) {
        throw ex; // Enviar al siguiente metodo el error al obtener la conexion de la clase ComunDB en el caso que suceda
    }
    return mascotas; // Devolver el ArrayList de Mascotas
}
}
    
    
