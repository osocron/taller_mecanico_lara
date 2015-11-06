package viewControlers;

import entities.EmpleadoEntity;
import entityControlers.ControladorEmpleado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by osocron on 29/10/15.
 */
public class ViewConsultarEmpleado implements Initializable {

    @FXML
    public TextField busquedaTextField;
    @FXML
    public TableView<EmpleadoEntity> tableEmpleados;
    @FXML
    public TableColumn puesto,nombre,idEmpleado;
    @FXML
    public Button buttonEliminar,buttonEditar,buttonGuardar;
    @FXML
    public Button buttonBuscar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void eliminarEmpleadoEvent(){
        ControladorEmpleado.eliminarEmpleado(busquedaTextField.getText());
    }
    public void modificarEmpleadoEvent(){
        ControladorEmpleado.modificarEmpleado(nombre.getText(),puesto.getText());
        nombre.setText("");
        puesto.setText("");
    }
    public void buscarEmpleadoEvent(){

    }
    public void guardarEmpleadoEvent(){
        ControladorEmpleado.guardarEmpleadp(ControladorEmpleado.crearEmpleado(idEmpleado.hashCode(), nombre.getText(), puesto.getText()));
    }
}
