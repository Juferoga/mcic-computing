package com.academico.dao;

import com.academico.config.ConexionDB;
import com.academico.model.Estudiante;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO implements GenericDAO<Estudiante> {

    @Override
    public boolean insertar(Estudiante e) {
        String sql = "INSERT INTO Estudiantes (cod_e, nom_e, dir_e, tel_e, fech_nac, id_carr) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getCodE());
            ps.setString(2, e.getNomE());
            ps.setString(3, e.getDirE());
            ps.setString(4, e.getTelE());
            ps.setDate(5, e.getFechNac() != null ? Date.valueOf(e.getFechNac()) : null);
            ps.setString(6, e.getIdCarr());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar estudiante: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Estudiante e) {
        String sql = "UPDATE Estudiantes SET nom_e=?, dir_e=?, tel_e=?, fech_nac=?, id_carr=? WHERE cod_e=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNomE());
            ps.setString(2, e.getDirE());
            ps.setString(3, e.getTelE());
            ps.setDate(4, e.getFechNac() != null ? Date.valueOf(e.getFechNac()) : null);
            ps.setString(5, e.getIdCarr());
            ps.setString(6, e.getCodE());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al modificar estudiante: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Estudiantes WHERE cod_e = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar estudiante: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT cod_e, nom_e, dir_e, tel_e, fech_nac, id_carr FROM Estudiantes ORDER BY nom_e";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setCodE(rs.getString("cod_e"));
                e.setNomE(rs.getString("nom_e"));
                e.setDirE(rs.getString("dir_e"));
                e.setTelE(rs.getString("tel_e"));
                Date d = rs.getDate("fech_nac");
                e.setFechNac(d != null ? d.toLocalDate() : null);
                e.setIdCarr(rs.getString("id_carr"));
                lista.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Estudiante buscarPorId(String id) {
        String sql = "SELECT cod_e, nom_e, dir_e, tel_e, fech_nac, id_carr FROM Estudiantes WHERE cod_e = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Estudiante e = new Estudiante();
                e.setCodE(rs.getString("cod_e"));
                e.setNomE(rs.getString("nom_e"));
                e.setDirE(rs.getString("dir_e"));
                e.setTelE(rs.getString("tel_e"));
                Date d = rs.getDate("fech_nac");
                e.setFechNac(d != null ? d.toLocalDate() : null);
                e.setIdCarr(rs.getString("id_carr"));
                return e;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiante: " + e.getMessage());
        }
        return null;
    }
}