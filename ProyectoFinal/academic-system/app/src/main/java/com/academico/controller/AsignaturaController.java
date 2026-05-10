package com.academico.controller;

import com.academico.dao.AsignaturaDAO;
import com.academico.model.Asignatura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class AsignaturaController {

    @FXML
    private TableView<Asignatura> tableView;
    @FXML
    private TableColumn<Asignatura, String> colCodigo;
    @FXML
    private TableColumn<Asignatura, String> colNombre;
    @FXML
    private TableColumn<Asignatura, Integer> colIntensidad;
    @FXML
    private TableColumn<Asignatura, Integer> colCreditos;

    private final AsignaturaDAO dao = new AsignaturaDAO();
    private final ObservableList<Asignatura> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codA"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIntensidad.setCellValueFactory(new PropertyValueFactory<>("intensidadHoraria"));
        colCreditos.setCellValueFactory(new PropertyValueFactory<>("creditos"));
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
        Asignatura a = tableView.getSelectionModel().getSelectedItem();
        if (a != null) {
            mostrarDialogo(a);
        } else {
            mostrarAlerta("Seleccione una asignatura.");
        }
    }

    @FXML
    private void eliminar() {
        Asignatura a = tableView.getSelectionModel().getSelectedItem();
        if (a != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar " + a.getNombre() + "?").showAndWait()
                    .orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.eliminar(a.getCodA());
                cargarDatos();
            }
        } else {
            mostrarAlerta("Seleccione una asignatura.");
        }
    }

    private void mostrarDialogo(Asignatura asig) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(asig == null ? "Agregar Asignatura" : "Editar Asignatura");

        TextField tfCod = new TextField();
        tfCod.setPromptText("Código");
        TextField tfNom = new TextField();
        tfNom.setPromptText("Nombre");
        Spinner<Integer> spIntensidad = new Spinner<>(0, 999, 0);
        spIntensidad.setEditable(true);
        Spinner<Integer> spCreditos = new Spinner<>(0, 99, 0);
        spCreditos.setEditable(true);

        if (asig != null) {
            tfCod.setText(asig.getCodA());
            tfCod.setEditable(false);
            tfNom.setText(asig.getNombre());
            spIntensidad.getValueFactory().setValue(asig.getIntensidadHoraria());
            spCreditos.getValueFactory().setValue(asig.getCreditos());
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("Código:"), 0, 0);
        grid.add(tfCod, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(tfNom, 1, 1);
        grid.add(new Label("Intensidad Horaria:"), 0, 2);
        grid.add(spIntensidad, 1, 2);
        grid.add(new Label("Créditos:"), 0, 3);
        grid.add(spCreditos, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                String cod = tfCod.getText().trim();
                String nom = tfNom.getText().trim();
                if (cod.isEmpty() || nom.isEmpty()) {
                    mostrarAlerta("Código y Nombre son obligatorios.");
                    return null;
                }
                Asignatura a = new Asignatura(cod, nom, spIntensidad.getValue(), spCreditos.getValue());
                if (asig == null) {
                    dao.insertar(a);
                } else {
                    dao.modificar(a);
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
