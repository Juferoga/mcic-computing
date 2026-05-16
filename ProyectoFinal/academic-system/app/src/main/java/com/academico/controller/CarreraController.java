package com.academico.controller;

import com.academico.dao.CarreraDAO;
import com.academico.model.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class CarreraController {

    @FXML
    private TableView<Carrera> tableView;
    @FXML
    private TableColumn<Carrera, String> colId;
    @FXML
    private TableColumn<Carrera, String> colNombre;
    @FXML
    private TableColumn<Carrera, String> colRegistroCalif;

    private final CarreraDAO dao = new CarreraDAO();
    private final ObservableList<Carrera> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRegistroCalif.setCellValueFactory(new PropertyValueFactory<>("registroCalificacion"));
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
        Carrera selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            mostrarDialogo(selected);
        } else {
            mostrarAlerta("Seleccione una carrera para modificar.");
        }
    }

    @FXML
    private void eliminar() {
        Carrera selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar carrera " + selected.getNombre() + "?");
            if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.eliminar(selected.getId());
                cargarDatos();
            }
        } else {
            mostrarAlerta("Seleccione una carrera para eliminar.");
        }
    }

    private void mostrarDialogo(Carrera c) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(c == null ? "Agregar Carrera" : "Editar Carrera");

        TextField tfId = new TextField();
        tfId.setPromptText("ID");
        TextField tfNombre = new TextField();
        tfNombre.setPromptText("Nombre");
        TextField tfRegistro = new TextField();
        tfRegistro.setPromptText("Registro Calificación");

        if (c != null) {
            tfId.setText(c.getId());
            tfNombre.setText(c.getNombre());
            tfRegistro.setText(c.getRegistroCalificacion());
            tfId.setEditable(false);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("ID:"), 0, 0);
        grid.add(tfId, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(tfNombre, 1, 1);
        grid.add(new Label("Reg. Calificación:"), 0, 2);
        grid.add(tfRegistro, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                String id = tfId.getText().trim();
                String nombre = tfNombre.getText().trim();
                String reg = tfRegistro.getText().trim();
                if (id.isEmpty() || nombre.isEmpty()) {
                    mostrarAlerta("ID y Nombre son obligatorios.");
                    return null;
                }
                if (c == null) {
                    dao.insertar(new Carrera(id, nombre, reg));
                } else {
                    dao.modificar(new Carrera(id, nombre, reg));
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
