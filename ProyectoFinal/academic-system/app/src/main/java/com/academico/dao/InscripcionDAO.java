package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Inscripcion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {

    public OperationResult inscribir(String codE, String codA, String idP, int grupo) {
        String sql = "CALL sp_inscribir_estudiante(?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codE);
            ps.setString(2, codA);
            ps.setString(3, idP);
            ps.setInt(4, grupo);
            ps.execute();
            return OperationResult.ok("Inscripción realizada con éxito");
        } catch (SQLException e) {
            return OperationResult.error(e.getMessage());
        }
    }

    public OperationResult actualizarNotas(String codE, String codA, String idP, int grupo, double n1, double n2, double n3) {
        String sql = "UPDATE Inscribe SET n1 = ?, n2 = ?, n3 = ? WHERE cod_e = ? AND cod_a = ? AND id_p = ? AND grupo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, n1);
            ps.setDouble(2, n2);
            ps.setDouble(3, n3);
            ps.setString(4, codE);
            ps.setString(5, codA);
            ps.setString(6, idP);
            ps.setInt(7, grupo);
            int rows = ps.executeUpdate();
            return rows > 0
                    ? OperationResult.ok("Notas actualizadas con éxito")
                    : OperationResult.error("No se encontró la inscripción para actualizar");
        } catch (SQLException e) {
            return OperationResult.error(e.getMessage());
        }
    }

    public OperationResult eliminar(String codE, String codA, String idP, int grupo) {
        String sql = "DELETE FROM Inscribe WHERE cod_e = ? AND cod_a = ? AND id_p = ? AND grupo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codE);
            ps.setString(2, codA);
            ps.setString(3, idP);
            ps.setInt(4, grupo);
            int rows = ps.executeUpdate();
            return rows > 0
                    ? OperationResult.ok("Inscripción eliminada con éxito")
                    : OperationResult.error("No se encontró la inscripción para eliminar");
        } catch (SQLException e) {
            return OperationResult.error(e.getMessage());
        }
    }

    public List<Inscripcion> listarPorGrupo(String codA, int grupo) {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT cod_e, cod_a, id_p, grupo, n1, n2, n3 FROM Inscribe WHERE cod_a = ? AND grupo = ? ORDER BY cod_e";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codA);
            ps.setInt(2, grupo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion i = new Inscripcion();
                i.setCodEstudiante(rs.getString("cod_e"));
                i.setCodAsignatura(rs.getString("cod_a"));
                i.setIdProfesor(rs.getString("id_p"));
                i.setGrupo(rs.getInt("grupo"));
                i.setN1(rs.getDouble("n1"));
                i.setN2(rs.getDouble("n2"));
                i.setN3(rs.getDouble("n3"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar inscripciones por grupo: " + e.getMessage());
        }
        return lista;
    }

    public List<Inscripcion> listarTodos() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT cod_e, cod_a, id_p, grupo, n1, n2, n3 FROM Inscribe ORDER BY cod_a, grupo, cod_e";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Inscripcion i = new Inscripcion();
                i.setCodEstudiante(rs.getString("cod_e"));
                i.setCodAsignatura(rs.getString("cod_a"));
                i.setIdProfesor(rs.getString("id_p"));
                i.setGrupo(rs.getInt("grupo"));
                i.setN1(rs.getDouble("n1"));
                i.setN2(rs.getDouble("n2"));
                i.setN3(rs.getDouble("n3"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar inscripciones: " + e.getMessage());
        }
        return lista;
    }
}
