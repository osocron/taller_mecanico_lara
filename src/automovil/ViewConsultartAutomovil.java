package automovil;

import com.jfoenix.controls.JFXButton;
import entidades.AutomovilesEntity;
import automovil.ControladorAutomovil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ViewConsultartAutomovil implements Initializable {

    public TableView<AutomovilesEntity> tablaAutomovil;
    public TableColumn<AutomovilesEntity,String> tableMatricula;
    public TableColumn<AutomovilesEntity,String> tableModelo;
    public TableColumn<AutomovilesEntity,String> tableColor;
    public TableColumn<AutomovilesEntity,String> tableMarca;
    public TableColumn<AutomovilesEntity,String> tableIDcliente;
    public JFXButton eliminiarButton;

    private ObservableList<AutomovilesEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<AutomovilesEntity> listaAutomoviles = ControladorAutomovil.getAutomoviles();
        data.addAll(listaAutomoviles);
        prepararTablaView();
    }

    private void prepararTablaView() {
        tablaAutomovil.setEditable(true);
        tableMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tableModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tableModelo.setCellFactory(TextFieldTableCell.forTableColumn());
        tableModelo.setOnEditCommit(event -> ControladorAutomovil.modificarModelo(event.getTableView().getSelectionModel()
        .getSelectedItem().getMatricula(), event.getNewValue()));
        tableMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableMarca.setCellFactory(TextFieldTableCell.forTableColumn());
        tableMarca.setOnEditCommit(event -> ControladorAutomovil.modificarMarca(event.getTableView().getSelectionModel()
        .getSelectedItem().getMarca(),event.getNewValue()));
        tableColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        tableColor.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColor.setOnEditCommit(event -> ControladorAutomovil.modificarColor(event.getTableView().getSelectionModel()
        .getSelectedItem().getColor(),event.getNewValue()));
        tableIDcliente.setCellValueFactory(new PropertyValueFactory<>("idClientes"));

        tablaAutomovil.setItems(data);
    }

    public void cerrarVentanaEvent(){
        Stage stage = (Stage) tablaAutomovil.getScene().getWindow();
        stage.close();
    }
    public void cancelarActionEvent(){

    }

    public void eliminarActionEvent() {
        AutomovilesEntity automovilesEntity = tablaAutomovil.getSelectionModel().getSelectedItem();
        data.remove(automovilesEntity);
        tablaAutomovil.setItems(data);
        ControladorAutomovil.eliminarAutomovil(automovilesEntity.getMatricula());
    }
}
