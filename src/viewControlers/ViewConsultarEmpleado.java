package viewControlers;

import entities.EmpleadoEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
