package controlador;

import entidades.EmpleadoEntity;
import entidades.VentasEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewEmpleadoRegistra implements Initializable {
    @FXML
    public Label labelIDempleado;
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelPuesto;
    @FXML
    public Label labelCliente;
    @FXML
    public Label labelUsuario;
    @FXML
    public Label labelContrase�a;
    @FXML
    public TextField textfieldIDempleado;
    @FXML
    public TextField textfieldNombre;
    @FXML
    public TextField textfieldPuesto;
    @FXML
    public TextField textfieldUsuario;
    @FXML
    public TextField textfieldContrase�a;
    @FXML
    public ComboBox comboboxCliente;
    @FXML
    public RadioButton radiobuttonUsuario;
    @FXML
    public Button buttonEmpleado;

    private ObservableList<EmpleadoEntity> dataEmpleado = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<EmpleadoEntity> listaEmpleado = ControladorEmpleado.getEmpleados();
        dataEmpleado.addAll(listaEmpleado);
    }
}