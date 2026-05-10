package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Carrera;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO implements GenericDAO<Carrera> {

    @Override
    public boolean insertar(Carrera carrera) {
        String sql = "INSERT INTO Carreras (id_carr, nom_carr, reg_calif) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, carrera.getId());
            ps.setString(2, carrera.getNombre());
            ps.setString(3, carrera.getRegistroCalificacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar carrera: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Carrera carrera) {
        String sql = "UPDATE Carreras SET nom_carr = ?, reg_calif = ? WHERE id_carr = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, carrera.getNombre());
            ps.setString(2, carrera.getRegistroCalificacion());
            ps.setString(3, carrera.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar carrera: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Carreras WHERE id_carr = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar carrera: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Carrera> listarTodos() {
        List<Carrera> lista = new ArrayList<>();
        String sql = "SELECT id_carr, nom_carr, reg_calif FROM Carreras ORDER BY nom_carr";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Carrera c = new Carrera(
                    rs.getString("id_carr"),
                    rs.getString("nom_carr"),
                    rs.getString("reg_calif")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar carreras: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Carrera buscarPorId(String id) {
        String sql = "SELECT id_carr, nom_carr, reg_calif FROM Carreras WHERE id_carr = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Carrera(
                    rs.getString("id_carr"),
                    rs.getString("nom_carr"),
                    rs.getString("reg_calif")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar carrera: " + e.getMessage());
        }
        return null;
    }
}