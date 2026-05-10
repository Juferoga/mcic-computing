package com.academico.dao;

import com.academico.config.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequiereDAO {

    public boolean insertar(String codA, String codAR) {
        String sql = "INSERT INTO Requiere (cod_a, cod_a_r) VALUES (?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codA);
            ps.setString(2, codAR);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar requiere: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String codA, String codAR) {
        String sql = "DELETE FROM Requiere WHERE cod_a = ? AND cod_a_r = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codA);
            ps.setString(2, codAR);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar requiere: " + e.getMessage());
            return false;
        }
    }

    public List<String> listarPrerrequisitos(String codA) {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT cod_a_r FROM Requiere WHERE cod_a = ? ORDER BY cod_a_r";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("cod_a_r"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar prerrequisitos: " + e.getMessage());
        }
        return lista;
    }
}
