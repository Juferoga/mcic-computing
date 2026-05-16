package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Asignatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncluyeDAO {

    public boolean insertar(String icD, String codA) {
        String sql = "INSERT INTO Incluye (ic_d, cod_a) VALUES (?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, icD);
            ps.setString(2, codA);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar incluye: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String icD, String codA) {
        String sql = "DELETE FROM Incluye WHERE ic_d = ? AND cod_a = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, icD);
            ps.setString(2, codA);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar incluye: " + e.getMessage());
            return false;
        }
    }

    public List<Asignatura> listarAsignaturasPorCarrera(String icD) {
        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT a.cod_a, a.nom_a, a.int_h, a.creditos FROM Asignaturas a "
                   + "INNER JOIN Incluye i ON a.cod_a = i.cod_a WHERE i.ic_d = ? ORDER BY a.nom_a";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, icD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Asignatura a = new Asignatura();
                a.setCodA(rs.getString("cod_a"));
                a.setNombre(rs.getString("nom_a"));
                a.setIntensidadHoraria(rs.getInt("int_h"));
                a.setCreditos(rs.getInt("creditos"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar asignaturas por carrera: " + e.getMessage());
        }
        return lista;
    }

    public boolean existeIncluye(String icD, String codA) {
        String sql = "SELECT COUNT(*) FROM Incluye WHERE ic_d = ? AND cod_a = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, icD);
            ps.setString(2, codA);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar incluye: " + e.getMessage());
        }
        return false;
    }
}
