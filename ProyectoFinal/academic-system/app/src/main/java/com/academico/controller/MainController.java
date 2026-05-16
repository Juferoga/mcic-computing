package com.academico.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class MainController {

    @FXML
    private StackPane panelContenido;

    @FXML
    public void initialize() {
        mostrarCarreras();
    }

    private void cargarVista(String fxml) {
        try {
            Node vista = FXMLLoader.load(getClass().getResource("/com/academico/view/" + fxml));
            panelContenido.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarCarreras() {
        cargarVista("CarreraView.fxml");
    }

    @FXML
    private void mostrarEstudiantes() {
        cargarVista("EstudianteView.fxml");
    }

    @FXML
    private void mostrarAsignaturas() {
        cargarVista("AsignaturaView.fxml");
    }

    @FXML
    private void mostrarProfesores() {
        cargarVista("ProfesorView.fxml");
    }

    @FXML
    private void mostrarImparte() {
        cargarVista("ImparteView.fxml");
    }

    @FXML
    private void mostrarInscripciones() {
        cargarVista("InscripcionView.fxml");
    }

    @FXML
    private void mostrarNotas() {
        cargarVista("NotasView.fxml");
    }

    @FXML
    private void mostrarPrerrequisitos() {
        cargarVista("PrerrequisitoView.fxml");
    }

    @FXML
    private void mostrarPensum() {
        cargarVista("PensumView.fxml");
    }

    @FXML
    private void salir() {
        Platform.exit();
    }

    @FXML
    private void acercaDe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Sistema de Gestión Académica");
        alert.setContentText("Versión 1.0\nDesarrollado para la gestión académica universitaria.");
        alert.showAndWait();
    }
}
