package venta;

import cliente.ControladorCliente;
import com.jfoenix.controls.JFXButton;
import entidades.ClienteEntity;
import entidades.VentasEntity;
import javafx.scene.control.*;
import venta.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import viewControlers.DatePickerCell;

import java.net.URL;
import java.util.Optional;
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
        clienteTableColumn.setCellFactory(column -> new TableCell<VentasEntity, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    ClienteEntity clienteEntity = ControladorCliente.getClienteID(item);
                    setText(clienteEntity.getNombre());
                }
            }
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
        if (tablaVentas.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cuidado");
            alert.setHeaderText("Atención!");
            alert.setContentText("¿Seguro que deseas eliminar el elemento?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                VentasEntity ventasEntity = tablaVentas.getSelectionModel().getSelectedItem();
                dataVenta.remove(ventasEntity);
                tablaVentas.setItems(dataVenta);
                ControladorVentas.eliminarVenta(ventasEntity.getIdVenta());
            }
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un elemento!");
            alert.showAndWait();
        }
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) tablaVentas.getScene().getWindow();
        stage.close();
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public void cancelarActionEvent(){
        Stage stage = (Stage)tablaVentas.getScene().getWindow();
        stage.close();
    }

}
