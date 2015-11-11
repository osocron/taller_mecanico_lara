package viewControlers;

import entidades.EmpleadoEntity;
import entityControlers.ControladorEmpleado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    public Button buttonEliminar,buttonEditar,buttonGuardar,buttonBuscar;
    public TableColumn idEmpleado;
    public TableColumn nombre;
    public TableColumn puesto;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void eliminarEmpleadoEvent(){
        EmpleadoEntity empleadoEntity =tableEmpleados.getSelectionModel().getSelectedItem();
        tableEmpleados.getItems().remove(empleadoEntity);
    }
    public void modificarEmpleadoEvent(){
        ControladorEmpleado.modificarEmpleado(nombre.getText(),puesto.getText());
        nombre.getTableView().getItems();
        puesto.getTableView().getItems();
    }
    public void buscarEmpleadoEvent(){

    }
    public void guardarEmpleadoEvent(){
        ControladorEmpleado.guardarEmpleadp(ControladorEmpleado.crearEmpleado(idEmpleado.hashCode(),nombre.getText(),puesto.getText()));
        idEmpleado.hashCode();
        nombre.setText("");
        puesto.setText("");
    }
    public void cerrarVentanaEvent(){
        Stage stage = (Stage) busquedaTextField.getScene().getWindow();
        stage.close();
    }
    public void cancelarActionEvent(){
        busquedaTextField.setText("");
    }
}
