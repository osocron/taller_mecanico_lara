package cliente;

import com.jfoenix.controls.JFXButton;
import entidades.ClienteEntity;
import cliente.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ViewConsultarClientes implements Initializable{


    public TableView<ClienteEntity> clienteTableView;
    public TableColumn<ClienteEntity,String> idClienteTableColumn;
    public TableColumn<ClienteEntity,String> nombreTableColumn;
    public TableColumn<ClienteEntity,String> direccionTableColumn;
    public TableColumn<ClienteEntity,String> telefonoTableColumn;
    public JFXButton eliminarButton;

    private ObservableList<ClienteEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ClienteEntity> listaClientes = ControladorCliente.getCliente();
        data.addAll(listaClientes);
        prepararTableView();
        clienteTableView.setItems(data);
    }

    private void prepararTableView() {
        clienteTableView.setEditable(true);
        idClienteTableColumn.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombreTableColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreTableColumn.setOnEditCommit(event -> ControladorCliente.modificarNombre(
                event.getTableView().getSelectionModel().getSelectedItem().getIdCliente(),
                event.getNewValue()));
        direccionTableColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        direccionTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        direccionTableColumn.setOnEditCommit(event -> ControladorCliente.modificarDireccion(
                event.getTableView().getSelectionModel().getSelectedItem().getIdCliente(),
                event.getNewValue()));
        telefonoTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        telefonoTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonoTableColumn.setOnEditCommit(event -> ControladorCliente.modificarTelefono(
                        event.getTableView().getSelectionModel().getSelectedItem().getIdCliente(),
                        event.getNewValue())
        );
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) clienteTableView.getScene().getWindow();
        stage.close();
    }

    public void eliminarFilaActionEvent() {
        if (clienteTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cuidado");
            alert.setHeaderText("Atención!");
            alert.setContentText("¿Seguro que deseas eliminar el elemento?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ClienteEntity clienteEntity = clienteTableView.getSelectionModel().getSelectedItem();
                data.remove(clienteEntity);
                clienteTableView.setItems(data);
                ControladorCliente.eliminarCliente(clienteEntity.getIdCliente());
            }
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un elemento!");
            alert.showAndWait();
        }
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

}
