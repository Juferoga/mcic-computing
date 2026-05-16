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

        ComboBox<String> comboDia = new ComboBox<>(FXCollections.observableArrayList(
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"));
        comboDia.setPromptText("Día");
        comboDia.setPrefWidth(300);

        ComboBox<String> comboHoraInicio = new ComboBox<>(FXCollections.observableArrayList(
                "6:00am", "7:00am", "8:00am", "9:00am", "10:00am", "11:00am",
                "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm",
                "6:00pm", "7:00pm", "8:00pm"));
        comboHoraInicio.setPromptText("Hora inicio");
        comboHoraInicio.setPrefWidth(140);

        ComboBox<String> comboHoraFin = new ComboBox<>(FXCollections.observableArrayList(
                "7:00am", "8:00am", "9:00am", "10:00am", "11:00am", "12:00pm",
                "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "6:00pm",
                "7:00pm", "8:00pm", "9:00pm"));
        comboHoraFin.setPromptText("Hora fin");
        comboHoraFin.setPrefWidth(140);

        javafx.scene.layout.HBox hboxHoras = new javafx.scene.layout.HBox(10, comboHoraInicio, new Label("-"), comboHoraFin);
        hboxHoras.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

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
        grid.add(new Label("Día:"), 0, 3);
        grid.add(comboDia, 1, 3);
        grid.add(new Label("Horario:"), 0, 4);
        grid.add(hboxHoras, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                Profesor p = comboProfesor.getValue();
                Asignatura a = comboAsignatura.getValue();
                String grupoStr = tfGrupo.getText().trim();
                String dia = comboDia.getValue();
                String horaInicio = comboHoraInicio.getValue();
                String horaFin = comboHoraFin.getValue();
                if (p == null || a == null || grupoStr.isEmpty()) {
                    mostrarAlerta("Profesor, Asignatura y Grupo son obligatorios.");
                    return null;
                }
                if (dia == null || horaInicio == null || horaFin == null) {
                    mostrarAlerta("Debe seleccionar día, hora de inicio y hora de fin.");
                    return null;
                }
                try {
                    int grupo = Integer.parseInt(grupoStr);
                    String horario = dia + " " + horaInicio + "-" + horaFin;
                    dao.insertar(p.getId(), a.getCodA(), grupo, horario);
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
