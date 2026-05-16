package com.academico.controller;

import com.academico.dao.AsignaturaDAO;
import com.academico.dao.RequiereDAO;
import com.academico.model.Asignatura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class PrerrequisitoController {

    @FXML
    private ComboBox<Asignatura> comboAsignatura;
    @FXML
    private ListView<Asignatura> listView;

    private final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private final RequiereDAO requiereDAO = new RequiereDAO();

    @FXML
    public void initialize() {
        comboAsignatura.setItems(FXCollections.observableArrayList(asignaturaDAO.listarTodos()));
        comboAsignatura.setOnAction(e -> cargarPrerrequisitos());
    }

    private void cargarPrerrequisitos() {
        Asignatura a = comboAsignatura.getValue();
        if (a != null) {
            List<String> codigos = requiereDAO.listarPrerrequisitos(a.getCodA());
            List<Asignatura> prereqs = new ArrayList<>();
            for (String cod : codigos) {
                Asignatura asig = asignaturaDAO.buscarPorId(cod);
                if (asig != null) {
                    prereqs.add(asig);
                }
            }
            listView.setItems(FXCollections.observableArrayList(prereqs));
        }
    }

    @FXML
    private void agregarPrerrequisito() {
        Asignatura actual = comboAsignatura.getValue();
        if (actual == null) {
            mostrarAlerta("Seleccione una asignatura.");
            return;
        }

        List<String> existentes = requiereDAO.listarPrerrequisitos(actual.getCodA());

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Agregar Prerrequisito");

        ComboBox<Asignatura> combo = new ComboBox<>();
        List<Asignatura> disponibles = new ArrayList<>();
        for (Asignatura a : asignaturaDAO.listarTodos()) {
            if (!a.getCodA().equals(actual.getCodA()) && !existentes.contains(a.getCodA())) {
                disponibles.add(a);
            }
        }
        combo.setItems(FXCollections.observableArrayList(disponibles));
        combo.setPromptText("Seleccione prerrequisito");
        combo.setPrefWidth(300);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("Prerrequisito:"), 0, 0);
        grid.add(combo, 1, 0);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Asignatura sel = combo.getValue();
                if (sel != null) {
                    requiereDAO.insertar(actual.getCodA(), sel.getCodA());
                } else {
                    mostrarAlerta("Seleccione un prerrequisito.");
                }
            }
            return btn;
        });

        dialog.showAndWait();
        cargarPrerrequisitos();
    }

    @FXML
    private void eliminarPrerrequisito() {
        Asignatura actual = comboAsignatura.getValue();
        Asignatura sel = listView.getSelectionModel().getSelectedItem();
        if (actual != null && sel != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar prerrequisito " + sel.getNombre() + "?")
                    .showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                requiereDAO.eliminar(actual.getCodA(), sel.getCodA());
                cargarPrerrequisitos();
            }
        } else {
            mostrarAlerta("Seleccione un prerrequisito para eliminar.");
        }
    }

    private void mostrarAlerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }
}
