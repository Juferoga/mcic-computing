package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Asignatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO implements GenericDAO<Asignatura> {

    @Override
    public boolean insertar(Asignatura a) {
        String sql = "INSERT INTO Asignaturas (cod_a, nom_a, int_h, creditos) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getCodA());
            ps.setString(2, a.getNombre());
            ps.setInt(3, a.getIntensidadHoraria());
            ps.setInt(4, a.getCreditos());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar asignatura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Asignatura a) {
        String sql = "UPDATE Asignaturas SET nom_a = ?, int_h = ?, creditos = ? WHERE cod_a = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getIntensidadHoraria());
            ps.setInt(3, a.getCreditos());
            ps.setString(4, a.getCodA());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar asignatura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Asignaturas WHERE cod_a = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar asignatura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Asignatura> listarTodos() {
        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT cod_a, nom_a, int_h, creditos FROM Asignaturas ORDER BY nom_a";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Asignatura a = new Asignatura();
                a.setCodA(rs.getString("cod_a"));
                a.setNombre(rs.getString("nom_a"));
                a.setIntensidadHoraria(rs.getInt("int_h"));
                a.setCreditos(rs.getInt("creditos"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar asignaturas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Asignatura buscarPorId(String id) {
        String sql = "SELECT cod_a, nom_a, int_h, creditos FROM Asignaturas WHERE cod_a = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Asignatura a = new Asignatura();
                a.setCodA(rs.getString("cod_a"));
                a.setNombre(rs.getString("nom_a"));
                a.setIntensidadHoraria(rs.getInt("int_h"));
                a.setCreditos(rs.getInt("creditos"));
                return a;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar asignatura: " + e.getMessage());
        }
        return null;
    }
}
