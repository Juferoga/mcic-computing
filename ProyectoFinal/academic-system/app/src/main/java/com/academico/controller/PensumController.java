package com.academico.controller;

import com.academico.dao.AsignaturaDAO;
import com.academico.dao.CarreraDAO;
import com.academico.dao.IncluyeDAO;
import com.academico.model.Asignatura;
import com.academico.model.Carrera;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class PensumController {

    @FXML
    private ComboBox<Carrera> comboCarrera;
    @FXML
    private TableView<Asignatura> tableView;
    @FXML
    private TableColumn<Asignatura, String> colCodigo;
    @FXML
    private TableColumn<Asignatura, String> colNombre;

    private final CarreraDAO carreraDAO = new CarreraDAO();
    private final IncluyeDAO incluyeDAO = new IncluyeDAO();
    private final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();

    @FXML
    public void initialize() {
        comboCarrera.setItems(FXCollections.observableArrayList(carreraDAO.listarTodos()));
        comboCarrera.setOnAction(e -> cargarPensum());
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codA"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    private void cargarPensum() {
        Carrera c = comboCarrera.getValue();
        if (c != null) {
            tableView.setItems(FXCollections.observableArrayList(
                    incluyeDAO.listarAsignaturasPorCarrera(c.getId())));
        }
    }

    @FXML
    private void agregarAsignatura() {
        Carrera carrera = comboCarrera.getValue();
        if (carrera == null) {
            mostrarAlerta("Seleccione una carrera.");
            return;
        }

        List<Asignatura> existentes = incluyeDAO.listarAsignaturasPorCarrera(carrera.getId());

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Agregar Asignatura al Pensum");

        ComboBox<Asignatura> combo = new ComboBox<>();
        List<Asignatura> disponibles = new ArrayList<>();
        for (Asignatura a : asignaturaDAO.listarTodos()) {
            boolean yaIncluida = false;
            for (Asignatura e : existentes) {
                if (e.getCodA().equals(a.getCodA())) {
                    yaIncluida = true;
                    break;
                }
            }
            if (!yaIncluida) {
                disponibles.add(a);
            }
        }
        combo.setItems(FXCollections.observableArrayList(disponibles));
        combo.setPromptText("Seleccione asignatura");
        combo.setPrefWidth(300);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("Asignatura:"), 0, 0);
        grid.add(combo, 1, 0);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Asignatura sel = combo.getValue();
                if (sel != null) {
                    incluyeDAO.insertar(carrera.getId(), sel.getCodA());
                } else {
                    mostrarAlerta("Seleccione una asignatura.");
                }
            }
            return btn;
        });

        dialog.showAndWait();
        cargarPensum();
    }

    @FXML
    private void eliminarAsignatura() {
        Carrera carrera = comboCarrera.getValue();
        Asignatura sel = tableView.getSelectionModel().getSelectedItem();
        if (carrera != null && sel != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION,
                    "¿Eliminar " + sel.getNombre() + " del pensum?")
                    .showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                incluyeDAO.eliminar(carrera.getId(), sel.getCodA());
                cargarPensum();
            }
        } else {
            mostrarAlerta("Seleccione una asignatura para eliminar.");
        }
    }

    private void mostrarAlerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }
}
