package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Imparte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImparteDAO {

    public boolean insertar(String idP, String codA, int grupo, String horario) {
        String sql = "INSERT INTO Imparte (id_p, cod_a, grupo, horario) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idP);
            ps.setString(2, codA);
            ps.setInt(3, grupo);
            ps.setString(4, horario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar imparte: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String idP, String codA, int grupo) {
        String sql = "DELETE FROM Imparte WHERE id_p = ? AND cod_a = ? AND grupo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idP);
            ps.setString(2, codA);
            ps.setInt(3, grupo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar imparte: " + e.getMessage());
            return false;
        }
    }

    public List<Imparte> listarTodos() {
        List<Imparte> lista = new ArrayList<>();
        String sql = "SELECT id_p, cod_a, grupo, horario FROM Imparte ORDER BY cod_a, grupo";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Imparte i = new Imparte();
                i.setIdProfesor(rs.getString("id_p"));
                i.setCodAsignatura(rs.getString("cod_a"));
                i.setGrupo(rs.getInt("grupo"));
                i.setHorario(rs.getString("horario"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar imparte: " + e.getMessage());
        }
        return lista;
    }

    public Imparte buscarPorId(String idP, String codA, int grupo) {
        String sql = "SELECT id_p, cod_a, grupo, horario FROM Imparte WHERE id_p = ? AND cod_a = ? AND grupo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idP);
            ps.setString(2, codA);
            ps.setInt(3, grupo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Imparte i = new Imparte();
                i.setIdProfesor(rs.getString("id_p"));
                i.setCodAsignatura(rs.getString("cod_a"));
                i.setGrupo(rs.getInt("grupo"));
                i.setHorario(rs.getString("horario"));
                return i;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar imparte: " + e.getMessage());
        }
        return null;
    }

    public List<Imparte> listarPorProfesor(String idP) {
        List<Imparte> lista = new ArrayList<>();
        String sql = "SELECT id_p, cod_a, grupo, horario FROM Imparte WHERE id_p = ? ORDER BY cod_a, grupo";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Imparte i = new Imparte();
                i.setIdProfesor(rs.getString("id_p"));
                i.setCodAsignatura(rs.getString("cod_a"));
                i.setGrupo(rs.getInt("grupo"));
                i.setHorario(rs.getString("horario"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar imparte por profesor: " + e.getMessage());
        }
        return lista;
    }

    public List<Imparte> listarPorAsignatura(String codA) {
        List<Imparte> lista = new ArrayList<>();
        String sql = "SELECT id_p, cod_a, grupo, horario FROM Imparte WHERE cod_a = ? ORDER BY grupo";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Imparte i = new Imparte();
                i.setIdProfesor(rs.getString("id_p"));
                i.setCodAsignatura(rs.getString("cod_a"));
                i.setGrupo(rs.getInt("grupo"));
                i.setHorario(rs.getString("horario"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar imparte por asignatura: " + e.getMessage());
        }
        return lista;
    }

    public boolean existeImparte(String idP, String codA, int grupo) {
        String sql = "SELECT COUNT(*) FROM Imparte WHERE id_p = ? AND cod_a = ? AND grupo = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idP);
            ps.setString(2, codA);
            ps.setInt(3, grupo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar imparte: " + e.getMessage());
        }
        return false;
    }
}
