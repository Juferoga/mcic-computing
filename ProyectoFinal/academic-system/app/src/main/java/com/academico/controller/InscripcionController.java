package com.academico.controller;

import com.academico.dao.AsignaturaDAO;
import com.academico.dao.EstudianteDAO;
import com.academico.dao.ImparteDAO;
import com.academico.dao.InscripcionDAO;
import com.academico.dao.ProfesorDAO;
import com.academico.model.Asignatura;
import com.academico.model.Estudiante;
import com.academico.model.Imparte;
import com.academico.model.Inscripcion;
import com.academico.model.Profesor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class InscripcionController {

    @FXML
    private TableView<Inscripcion> tableView;
    @FXML
    private TableColumn<Inscripcion, String> colEstudiante;
    @FXML
    private TableColumn<Inscripcion, String> colAsignatura;
    @FXML
    private TableColumn<Inscripcion, String> colProfesor;
    @FXML
    private TableColumn<Inscripcion, Integer> colGrupo;
    @FXML
    private TableColumn<Inscripcion, Double> colN1;
    @FXML
    private TableColumn<Inscripcion, Double> colN2;
    @FXML
    private TableColumn<Inscripcion, Double> colN3;
    @FXML
    private TableColumn<Inscripcion, Double> colDefinitiva;

    private final InscripcionDAO dao = new InscripcionDAO();
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();
    private final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private final ProfesorDAO profesorDAO = new ProfesorDAO();
    private final ImparteDAO imparteDAO = new ImparteDAO();
    private final ObservableList<Inscripcion> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colEstudiante.setCellValueFactory(cd -> {
            Estudiante e = estudianteDAO.buscarPorId(cd.getValue().getCodEstudiante());
            return new SimpleStringProperty(e != null ? e.getNomE() : "");
        });
        colAsignatura.setCellValueFactory(cd -> {
            Asignatura a = asignaturaDAO.buscarPorId(cd.getValue().getCodAsignatura());
            return new SimpleStringProperty(a != null ? a.getNombre() : "");
        });
        colProfesor.setCellValueFactory(cd -> {
            Profesor p = profesorDAO.buscarPorId(cd.getValue().getIdProfesor());
            return new SimpleStringProperty(p != null ? p.getNombre() : "");
        });
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        colN1.setCellValueFactory(new PropertyValueFactory<>("n1"));
        colN2.setCellValueFactory(new PropertyValueFactory<>("n2"));
        colN3.setCellValueFactory(new PropertyValueFactory<>("n3"));
        colDefinitiva.setCellValueFactory(new PropertyValueFactory<>("definitiva"));
        cargarDatos();
    }

    private void cargarDatos() {
        lista.setAll(dao.listarTodos());
        tableView.setItems(lista);
    }

    @FXML
    private void inscribir() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Inscribir Estudiante");

        ComboBox<Estudiante> comboEst = new ComboBox<>();
        comboEst.setItems(FXCollections.observableArrayList(estudianteDAO.listarTodos()));
        comboEst.setPromptText("Seleccione estudiante");
        comboEst.setPrefWidth(300);

        ComboBox<Asignatura> comboAsig = new ComboBox<>();
        comboAsig.setItems(FXCollections.observableArrayList(asignaturaDAO.listarTodos()));
        comboAsig.setPromptText("Seleccione asignatura");
        comboAsig.setPrefWidth(300);

        ComboBox<Imparte> comboGrupo = new ComboBox<>();
        comboGrupo.setPromptText("Seleccione grupo");
        comboGrupo.setPrefWidth(300);
        comboGrupo.setDisable(true);

        comboAsig.setOnAction(e -> {
            Asignatura a = comboAsig.getValue();
            if (a != null) {
                comboGrupo.setItems(FXCollections.observableArrayList(
                        imparteDAO.listarPorAsignatura(a.getCodA())));
                comboGrupo.setDisable(false);
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("Estudiante:"), 0, 0);
        grid.add(comboEst, 1, 0);
        grid.add(new Label("Asignatura:"), 0, 1);
        grid.add(comboAsig, 1, 1);
        grid.add(new Label("Grupo:"), 0, 2);
        grid.add(comboGrupo, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Estudiante e = comboEst.getValue();
                Asignatura a = comboAsig.getValue();
                Imparte i = comboGrupo.getValue();
                if (e == null || a == null || i == null) {
                    mostrarAlerta("Todos los campos son obligatorios.");
                    return null;
                }
                dao.inscribir(e.getCodE(), a.getCodA(), i.getIdProfesor(), i.getGrupo());
            }
            return btn;
        });

        dialog.showAndWait();
        cargarDatos();
    }

    @FXML
    private void eliminar() {
        Inscripcion i = tableView.getSelectionModel().getSelectedItem();
        if (i != null) {
            if (new Alert(Alert.AlertType.CONFIRMATION, "¿Eliminar inscripción?")
                    .showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                dao.eliminar(i.getCodEstudiante(), i.getCodAsignatura(),
                        i.getIdProfesor(), i.getGrupo());
                cargarDatos();
            }
        } else {
            mostrarAlerta("Seleccione una inscripción.");
        }
    }

    private void mostrarAlerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }
}
