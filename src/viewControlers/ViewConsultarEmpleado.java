package viewControlers;

import com.jfoenix.controls.JFXButton;
import entidades.EmpleadoEntity;
import entityControlers.ControladorEmpleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocron on 29/10/15.
 */
public class ViewConsultarEmpleado implements Initializable {

    public TableView<EmpleadoEntity> tableEmpleados;
    public TableColumn<EmpleadoEntity,String> idTableColumn;
    public TableColumn<EmpleadoEntity,String> nombreTableColumn;
    public TableColumn<EmpleadoEntity,String> puestoTableColumn;
    public JFXButton eliminarButton;

    private ObservableList<EmpleadoEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<EmpleadoEntity> empleadoEntityList = ControladorEmpleado.getEmpleados();
        data.addAll(empleadoEntityList);
        prepararTableView();
        tableEmpleados.setItems(data);
    }

    private void prepararTableView() {
        tableEmpleados.setEditable(true);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        nombreTableColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreTableColumn.setOnEditCommit(event -> ControladorEmpleado.modificarNombre(
                event.getTableView().getSelectionModel().getSelectedItem().getIdEmpleado(),
                event.getNewValue()
        ));
        puestoTableColumn.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        puestoTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        puestoTableColumn.setOnEditCommit(event -> ControladorEmpleado.modificarPuesto(
                event.getTableView().getSelectionModel().getSelectedItem().getIdEmpleado(),
                event.getNewValue()
        ));
    }

    public void cerrarVentanaEvent(){
        Stage stage = (Stage) tableEmpleados.getScene().getWindow();
        stage.close();
    }
    public void cancelarActionEvent(){
        Stage stage = (Stage) tableEmpleados.getScene().getWindow();
        stage.close();
    }

    public void eliminarFilaActionEvent( ) {
        EmpleadoEntity empleadoEntity = tableEmpleados.getSelectionModel().getSelectedItem();
        data.remove(empleadoEntity);
        tableEmpleados.setItems(data);
        ControladorEmpleado.eliminarEmpleado(empleadoEntity.getIdEmpleado());
    }
}
