package viewControlers;

import com.jfoenix.controls.JFXButton;
import entidades.VentasEntity;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by osocron on 29/10/15.
 */
public class ViewConsultarVentas implements Initializable {

    public TableView<VentasEntity> tablaVentas;
    public TableColumn<VentasEntity,String> idVentaTableColumn;
    public TableColumn fechaTablecolumn;
    public TableColumn<VentasEntity,String> clienteTableColumn;
    public JFXButton buttonEliminar;


    private ObservableList<VentasEntity> dataVenta = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        dataVenta.addAll(listaVentas);
        tablaVentas.setItems(dataVenta);
        tablaVentas.setEditable(true);

        idVentaTableColumn.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        clienteTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        clienteTableColumn.setCellValueFactory(new PropertyValueFactory<>("idClientes"));
        clienteTableColumn.setOnEditCommit(event -> {
            VentasEntity ventasEntity =
                    event.getTableView().getItems().get(event.getTablePosition().getRow());
            ControladorVentas.modificarCliente(ventasEntity.getIdVenta(),event.getNewValue());
        });

        fechaTablecolumn.setCellValueFactory( new PropertyValueFactory<DatePickerCell, java.sql.Date>("fecha"));
        fechaTablecolumn.setCellFactory(param -> {
            DatePickerCell datePickerCell = new DatePickerCell(dataVenta);
            return datePickerCell;
        });
        fechaTablecolumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<VentasEntity,java.sql.Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<VentasEntity,java.sql.Date> event) {
                ControladorVentas.modificarFecha(event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                ).getIdVenta(), event.getNewValue());
            }
        });

    }

    public void eliminarVentaEvent(){
        VentasEntity ventasEntity = tablaVentas.getSelectionModel().getSelectedItem();
        dataVenta.remove(ventasEntity);
        tablaVentas.setItems(dataVenta);
        ControladorVentas.eliminarVenta(ventasEntity.getIdVenta());
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) tablaVentas.getScene().getWindow();
        stage.close();
    }

    public void cancelarActionEvent(){

    }

}
