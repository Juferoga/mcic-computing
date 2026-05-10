package com.academico.controller;

import com.academico.dao.AsignaturaDAO;
import com.academico.dao.ImparteDAO;
import com.academico.dao.ProfesorDAO;
import com.academico.model.Asignatura;
import com.academico.model.Imparte;
import com.academico.model.Profesor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class ImparteController {

    @FXML
    private TableView<Imparte> tableView;
    @FXML
    private TableColumn<Imparte, String> colProfesor;
    @FXML
    private TableColumn<Imparte, String> colAsignatura;
    @FXML
    private TableColumn<Imparte, Integer> colGrupo;
    @FXML
    private TableColumn<Imparte, String> colHorario;

    private final ImparteDAO dao = new ImparteDAO();
    private final ProfesorDAO profesorDAO = new ProfesorDAO();
    private final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private final ObservableList<Imparte> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colProfesor.setCellValueFactory(cd -> {
            Profesor p = profesorDAO.buscarPorId(cd.getValue().getIdProfesor());
            return new SimpleStringProperty(p != null ? p.getNombre() : "");
        });
        colAsignatura.setCellValueFactory(cd -> {
            Asignatura a = asignaturaDAO.buscarPorId(cd.getValue().getCodAsignatura());
            return new SimpleStringProperty(a != null ? a.getNombre() : "");
        });
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        cargarDatos();
    }

    private void cargarDatos() {
        lista.setAll(dao.listarTodos());
        tableView.setItems(lista);
    }

    @FXML
    private void agregar() {
        mostrarDialogo();
    }

    @FXML
    private void eliminar() {
        Imparte i = tableView.getSelectionModel().getSelectedItem();
        if (i != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar carga académica?")
                    .showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.eliminar(i.getIdProfesor(), i.getCodAsignatura(), i.getGrupo());
                cargarDatos();
            }
        } else {
            mostrarAlerta("Seleccione un registro para eliminar.");
        }
    }

    private void mostrarDialogo() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Agregar Carga Académica");

        ComboBox<Profesor> comboProfesor = new ComboBox<>();
        comboProfesor.setItems(FXCollections.observableArrayList(profesorDAO.listarTodos()));
        comboProfesor.setPromptText("Seleccione profesor");
        comboProfesor.setPrefWidth(300);

        ComboBox<Asignatura> comboAsignatura = new ComboBox<>();
        comboAsignatura.setItems(FXCollections.observableArrayList(asignaturaDAO.listarTodos()));
        comboAsignatura.setPromptText("Seleccione asignatura");
        comboAsignatura.setPrefWidth(300);

        TextField tfGrupo = new TextField();
        tfGrupo.setPromptText("Grupo");
        TextField tfHorario = new TextField();
        tfHorario.setPromptText("Horario");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("Profesor:"), 0, 0);
        grid.add(comboProfesor, 1, 0);
        grid.add(new Label("Asignatura:"), 0, 1);
        grid.add(comboAsignatura, 1, 1);
        grid.add(new Label("Grupo:"), 0, 2);
        grid.add(tfGrupo, 1, 2);
        grid.add(new Label("Horario:"), 0, 3);
        grid.add(tfHorario, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Profesor p = comboProfesor.getValue();
                Asignatura a = comboAsignatura.getValue();
                String grupoStr = tfGrupo.getText().trim();
                if (p == null || a == null || grupoStr.isEmpty()) {
                    mostrarAlerta("Profesor, Asignatura y Grupo son obligatorios.");
                    return null;
                }
                try {
                    int grupo = Integer.parseInt(grupoStr);
                    dao.insertar(p.getId(), a.getCodA(), grupo, tfHorario.getText().trim());
                } catch (NumberFormatException e) {
                    mostrarAlerta("Grupo debe ser un número válido.");
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
