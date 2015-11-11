package viewControlers;

import com.jfoenix.controls.JFXButton;
import entidades.ClienteEntity;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class ViewClienteMuestra implements Initializable{


    public MenuItem menuitemClose;
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
        clienteTableView.setEditable(true);
        prepararTableView();
        clienteTableView.setItems(data);
    }

    private void prepararTableView() {
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
        ClienteEntity clienteEntity = clienteTableView.getSelectionModel().getSelectedItem();
        data.remove(clienteEntity);
        clienteTableView.setItems(data);
        ControladorCliente.eliminarCliente(clienteEntity.getIdCliente());
    }
}
