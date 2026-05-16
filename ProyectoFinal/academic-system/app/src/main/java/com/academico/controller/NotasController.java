package com.academico.controller;

import com.academico.dao.AsignaturaDAO;
import com.academico.dao.EstudianteDAO;
import com.academico.dao.ImparteDAO;
import com.academico.dao.InscripcionDAO;
import com.academico.dao.OperationResult;
import com.academico.model.Asignatura;
import com.academico.model.Estudiante;
import com.academico.model.Imparte;
import com.academico.model.Inscripcion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.ArrayList;

public class NotasController {

    @FXML
    private ComboBox<Asignatura> comboAsignatura;
    @FXML
    private ComboBox<Integer> comboGrupo;
    @FXML
    private TableView<Inscripcion> tableView;
    @FXML
    private TableColumn<Inscripcion, String> colEstudiante;
    @FXML
    private TableColumn<Inscripcion, Double> colN1;
    @FXML
    private TableColumn<Inscripcion, Double> colN2;
    @FXML
    private TableColumn<Inscripcion, Double> colN3;
    @FXML
    private TableColumn<Inscripcion, Double> colDefinitiva;

    private final AsignaturaDAO asignaturaDAO = new AsignaturaDAO();
    private final EstudianteDAO estudianteDAO = new EstudianteDAO();
    private final ImparteDAO imparteDAO = new ImparteDAO();
    private final InscripcionDAO inscripcionDAO = new InscripcionDAO();
    private final ObservableList<Inscripcion> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        comboAsignatura.setItems(FXCollections.observableArrayList(asignaturaDAO.listarTodos()));
        comboAsignatura.setOnAction(e -> cargarGrupos());

        colEstudiante.setCellValueFactory(cd -> {
            Estudiante e = estudianteDAO.buscarPorId(cd.getValue().getCodEstudiante());
            return new SimpleStringProperty(e != null ? e.getNomE() : "");
        });
        colN1.setCellValueFactory(new PropertyValueFactory<>("n1"));
        colN2.setCellValueFactory(new PropertyValueFactory<>("n2"));
        colN3.setCellValueFactory(new PropertyValueFactory<>("n3"));
        colDefinitiva.setCellValueFactory(new PropertyValueFactory<>("definitiva"));

        tableView.setRowFactory(tv -> {
            TableRow<Inscripcion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    mostrarDialogoNotas(row.getItem());
                }
            });
            return row;
        });
    }

    private void cargarGrupos() {
        Asignatura a = comboAsignatura.getValue();
        if (a != null) {
            List<Imparte> impartes = imparteDAO.listarPorAsignatura(a.getCodA());
            List<Integer> grupos = new ArrayList<>();
            for (Imparte i : impartes) {
                if (!grupos.contains(i.getGrupo())) {
                    grupos.add(i.getGrupo());
                }
            }
            comboGrupo.setItems(FXCollections.observableArrayList(grupos));
        }
    }

    @FXML
    private void filtrar() {
        Asignatura a = comboAsignatura.getValue();
        Integer grupo = comboGrupo.getValue();
        if (a != null && grupo != null) {
            lista.setAll(inscripcionDAO.listarPorGrupo(a.getCodA(), grupo));
            tableView.setItems(lista);
        } else {
            mostrarAlerta("Seleccione asignatura y grupo.");
        }
    }

    private void mostrarDialogoNotas(Inscripcion ins) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Editar Notas - " + ins.getCodEstudiante());

        TextField tfN1 = new TextField(String.valueOf(ins.getN1()));
        TextField tfN2 = new TextField(String.valueOf(ins.getN2()));
        TextField tfN3 = new TextField(String.valueOf(ins.getN3()));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.add(new Label("N1:"), 0, 0);
        grid.add(tfN1, 1, 0);
        grid.add(new Label("N2:"), 0, 1);
        grid.add(tfN2, 1, 1);
        grid.add(new Label("N3:"), 0, 2);
        grid.add(tfN3, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    double n1 = Double.parseDouble(tfN1.getText().trim());
                    double n2 = Double.parseDouble(tfN2.getText().trim());
                    double n3 = Double.parseDouble(tfN3.getText().trim());
                    OperationResult result = inscripcionDAO.actualizarNotas(
                            ins.getCodEstudiante(), ins.getCodAsignatura(),
                            ins.getIdProfesor(), ins.getGrupo(), n1, n2, n3);
                    if (!result.isSuccess()) {
                        mostrarAlerta(result.getMessage());
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Ingrese valores numéricos válidos.");
                }
            }
            return btn;
        });

        dialog.showAndWait();
        filtrar();
    }

    private void mostrarAlerta(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }
}
