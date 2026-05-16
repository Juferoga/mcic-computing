package com.academico.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static ConexionDB instancia;
    private Connection conexion;

    private static final String URL = "jdbc:postgresql://localhost:5432/db_sistema_academico?currentSchema=gestion_academica";
    private static final String USER = "app_admin";
    private static final String PASSWORD = "Admin_Secret2026*";

    private ConexionDB() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Connection getConexion() {
        if (instancia == null || isClosed()) {
            instancia = new ConexionDB();
        }
        return instancia.conexion;
    }

    private static boolean isClosed() {
        try {
            return instancia == null || instancia.conexion == null || instancia.conexion.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    public static void cerrar() {
        if (instancia != null && instancia.conexion != null) {
            try {
                instancia.conexion.close();
                instancia = null;
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}