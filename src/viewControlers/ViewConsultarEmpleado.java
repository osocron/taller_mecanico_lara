package viewControlers;

import entidades.EmpleadoEntity;
import entityControlers.ControladorEmpleado;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public TableColumn idEmpleado;
    @FXML
    public TableColumn nombre;
    @FXML
    public TableColumn puesto;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void guardarEmpleadoActionEvent(){
        //ControladorEmpleado.guardarEmpleadp(ControladorEmpleado.guardarEmpleadp(idEmpleado.getText(),nombre.getText(),puesto.getText()));
        idEmpleado.setText("");
        nombre.setText("");
        puesto.setText("");
    }
    public void eliminarEmpleadoActionEvent(){
        //ControladorEmpleado.eliminarEmpleado(ControladorEmpleado.eliminarEmpleado(idEmpleado.getTexr()));
        idEmpleado.setText("");
    }
    public void modificarEmpleadoActionEvent(){
        //SControladorEmpleado.modificarEmpleado(ControladorEmpleado.modificarEmpleado(idEmpleado.getText(),puesto.getText()));
        idEmpleado.setText("");
        puesto.setText("");
    }
}
