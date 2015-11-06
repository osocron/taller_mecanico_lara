package viewControlers;

import entidades.EmpleadoEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public Button buttonEliminar,buttonEditar,buttonGuardar,buttonBuscar;
    public TableColumn idEmpleado;
    public TableColumn nombre;
    public TableColumn puesto;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void eliminarEmpleadoEvent(){

    }
    public void modificarEmpleadoEvent(){

    }
    public void buscarEmpleadoEvent(){

    }
    public void guardarEmpleadoEvent(){

    }
}
