package com.academico.controller;

import com.academico.dao.ProfesorDAO;
import com.academico.model.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class ProfesorController {

    @FXML
    private TableView<Profesor> tableView;
    @FXML
    private TableColumn<Profesor, String> colId;
    @FXML
    private TableColumn<Profesor, String> colNombre;
    @FXML
    private TableColumn<Profesor, String> colDireccion;
    @FXML
    private TableColumn<Profesor, String> colTelefono;
    @FXML
    private TableColumn<Profesor, String> colProfesion;

    private final ProfesorDAO dao = new ProfesorDAO();
    private final ObservableList<Profesor> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colProfesion.setCellValueFactory(new PropertyValueFactory<>("profesion"));
        cargarDatos();
    }

    private void cargarDatos() {
        lista.setAll(dao.listarTodos());
        tableView.setItems(lista);
    }

    @FXML
    private void agregar() {
        mostrarDialogo(null);
    }

    @FXML
    private void modificar() {
        Profesor p = tableView.getSelectionModel().getSelectedItem();
        if (p != null) {
            mostrarDialogo(p);
        } else {
            mostrarAlerta("Seleccione un profesor.");
        }
    }

    @FXML
    private void eliminar() {
        Profesor p = tableView.getSelectionModel().getSelectedItem();
        if (p != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar profesor " + p.getNombre() + "?").showAndWait()
                    .orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.eliminar(p.getId());
                cargarDatos();
            }
        } else {
            mostrarAlerta("Seleccione un profesor.");
        }
    }

    private void mostrarDialogo(Profesor prof) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(prof == null ? "Agregar Profesor" : "Editar Profesor");

        TextField tfId = new TextField();
        tfId.setPromptText("ID");
        TextField tfNom = new TextField();
        tfNom.setPromptText("Nombre");
        TextField tfDir = new TextField();
        tfDir.setPromptText("Dirección");
        TextField tfTel = new TextField();
        tfTel.setPromptText("Teléfono");
        TextField tfProf = new TextField();
        tfProf.setPromptText("Profesión");

        if (prof != null) {
            tfId.setText(prof.getId());
            tfId.setEditable(false);
            tfNom.setText(prof.getNombre());
            tfDir.setText(prof.getDireccion());
            tfTel.setText(prof.getTelefono());
            tfProf.setText(prof.getProfesion());
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("ID:"), 0, 0);
        grid.add(tfId, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(tfNom, 1, 1);
        grid.add(new Label("Dirección:"), 0, 2);
        grid.add(tfDir, 1, 2);
        grid.add(new Label("Teléfono:"), 0, 3);
        grid.add(tfTel, 1, 3);
        grid.add(new Label("Profesión:"), 0, 4);
        grid.add(tfProf, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                String id = tfId.getText().trim();
                String nom = tfNom.getText().trim();
                if (id.isEmpty() || nom.isEmpty()) {
                    mostrarAlerta("ID y Nombre son obligatorios.");
                    return null;
                }
                Profesor p = new Profesor(id, nom, tfDir.getText().trim(),
                        tfTel.getText().trim(), tfProf.getText().trim());
                if (prof == null) {
                    dao.insertar(p);
                } else {
                    dao.modificar(p);
                }
            }
            return btn;
        });

        dialog.showAndWait();
        cargarDatos();
    }

    private void mostrarAlerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }
}
