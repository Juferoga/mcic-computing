package com.academico.controller;

import com.academico.dao.CarreraDAO;
import com.academico.dao.EstudianteDAO;
import com.academico.model.Carrera;
import com.academico.model.Estudiante;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;

public class EstudianteController {

    @FXML
    private TableView<Estudiante> tableView;
    @FXML
    private TableColumn<Estudiante, String> colCodigo;
    @FXML
    private TableColumn<Estudiante, String> colNombre;
    @FXML
    private TableColumn<Estudiante, String> colDireccion;
    @FXML
    private TableColumn<Estudiante, String> colTelefono;
    @FXML
    private TableColumn<Estudiante, LocalDate> colFechaNac;
    @FXML
    private TableColumn<Estudiante, String> colCarrera;

    private final EstudianteDAO dao = new EstudianteDAO();
    private final CarreraDAO carreraDAO = new CarreraDAO();
    private final ObservableList<Estudiante> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codE"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nomE"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("dirE"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telE"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechNac"));
        colCarrera.setCellValueFactory(cd -> {
            Carrera c = carreraDAO.buscarPorId(cd.getValue().getIdCarr());
            return new SimpleStringProperty(c != null ? c.getNombre() : "");
        });
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
        Estudiante e = tableView.getSelectionModel().getSelectedItem();
        if (e != null) {
            mostrarDialogo(e);
        } else {
            mostrarAlerta("Seleccione un estudiante.");
        }
    }

    @FXML
    private void eliminar() {
        Estudiante e = tableView.getSelectionModel().getSelectedItem();
        if (e != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar estudiante " + e.getNomE() + "?").showAndWait()
                    .orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.eliminar(e.getCodE());
                cargarDatos();
            }
        } else {
            mostrarAlerta("Seleccione un estudiante.");
        }
    }

    private void mostrarDialogo(Estudiante est) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(est == null ? "Agregar Estudiante" : "Editar Estudiante");

        TextField tfCod = new TextField();
        tfCod.setPromptText("Código");
        TextField tfNom = new TextField();
        tfNom.setPromptText("Nombre");
        TextField tfDir = new TextField();
        tfDir.setPromptText("Dirección");
        TextField tfTel = new TextField();
        tfTel.setPromptText("Teléfono");
        DatePicker dpFech = new DatePicker();
        ComboBox<Carrera> comboCarrera = new ComboBox<>();
        comboCarrera.setItems(FXCollections.observableArrayList(carreraDAO.listarTodos()));
        comboCarrera.setPromptText("Seleccione carrera");

        if (est != null) {
            tfCod.setText(est.getCodE());
            tfCod.setEditable(false);
            tfNom.setText(est.getNomE());
            tfDir.setText(est.getDirE());
            tfTel.setText(est.getTelE());
            dpFech.setValue(est.getFechNac());
            for (Carrera c : comboCarrera.getItems()) {
                if (c.getId().equals(est.getIdCarr())) {
                    comboCarrera.setValue(c);
                    break;
                }
            }
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("Código:"), 0, 0);
        grid.add(tfCod, 1, 0);
        grid.add(new Label("Nombre:"), 0, 1);
        grid.add(tfNom, 1, 1);
        grid.add(new Label("Dirección:"), 0, 2);
        grid.add(tfDir, 1, 2);
        grid.add(new Label("Teléfono:"), 0, 3);
        grid.add(tfTel, 1, 3);
        grid.add(new Label("Fecha Nac.:"), 0, 4);
        grid.add(dpFech, 1, 4);
        grid.add(new Label("Carrera:"), 0, 5);
        grid.add(comboCarrera, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                String cod = tfCod.getText().trim();
                String nom = tfNom.getText().trim();
                if (cod.isEmpty() || nom.isEmpty() || comboCarrera.getValue() == null) {
                    mostrarAlerta("Código, Nombre y Carrera son obligatorios.");
                    return null;
                }
                Estudiante e = new Estudiante(cod, nom, tfDir.getText().trim(),
                        tfTel.getText().trim(), dpFech.getValue(),
                        comboCarrera.getValue().getId());
                if (est == null) {
                    dao.insertar(e);
                } else {
                    dao.modificar(e);
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
