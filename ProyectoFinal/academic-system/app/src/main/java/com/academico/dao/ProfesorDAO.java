package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements GenericDAO<Profesor> {

    @Override
    public boolean insertar(Profesor p) {
        String sql = "INSERT INTO Profesores (id_p, nom_p, dir_p, tel_p, profesion) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDireccion());
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getProfesion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar profesor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Profesor p) {
        String sql = "UPDATE Profesores SET nom_p = ?, dir_p = ?, tel_p = ?, profesion = ? WHERE id_p = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDireccion());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getProfesion());
            ps.setString(5, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar profesor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Profesores WHERE id_p = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar profesor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Profesor> listarTodos() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT id_p, nom_p, dir_p, tel_p, profesion FROM Profesores ORDER BY nom_p";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Profesor p = new Profesor();
                p.setId(rs.getString("id_p"));
                p.setNombre(rs.getString("nom_p"));
                p.setDireccion(rs.getString("dir_p"));
                p.setTelefono(rs.getString("tel_p"));
                p.setProfesion(rs.getString("profesion"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar profesores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Profesor buscarPorId(String id) {
        String sql = "SELECT id_p, nom_p, dir_p, tel_p, profesion FROM Profesores WHERE id_p = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Profesor p = new Profesor();
                p.setId(rs.getString("id_p"));
                p.setNombre(rs.getString("nom_p"));
                p.setDireccion(rs.getString("dir_p"));
                p.setTelefono(rs.getString("tel_p"));
                p.setProfesion(rs.getString("profesion"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar profesor: " + e.getMessage());
        }
        return null;
    }
}
