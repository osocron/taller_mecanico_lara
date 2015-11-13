package viewControlers;

import com.sun.prism.impl.VertexBuffer;
import entidades.*;
import entityControlers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.servlet.jsp.tagext.TagLibraryInfo;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 11/11/2015.
 */
public class ViewImprimirReporte implements Initializable {

    @FXML
    public TableView<VentasEntity> tablaReporte;
    public TableColumn<VentasEntity,String> tableVenta;
    public TableColumn<RefaccionEntity, String> tableRefaccion;
    public TableColumn<ServicioEntity, String> tableServicio;
    public TableColumn<VentasEntity,Date> tableFecha;
    public TableColumn<ClienteEntity,String> tableCliente;
    public TableColumn<AutomovilesEntity,String> tableAutomovil;
    public TableColumn<VentasEntity, Integer> tableCosto;


    private ObservableList<VentasEntity> dataVenta = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();
    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAuto = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<VentasEntity> ventasEntityList = ControladorVentas.getVentas();
        dataVenta.addAll(ventasEntityList);
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(refaccionEntityList);
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        dataServicio.addAll(servicioEntityList);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataCliente.addAll(clienteEntityList);
        List<AutomovilesEntity>automovilesEntityList = ControladorAutomovil.getAutomoviles();
        dataAuto.addAll(automovilesEntityList);
    }
    public void prepararTableView(){
        tablaReporte.setEditable(true);
        tableVenta.setCellValueFactory(new PropertyValueFactory<>("idventa"));
        tableVenta.setCellFactory(TextFieldTableCell.forTableColumn());
        /*tableVenta.setOnEditCommit(event -> ControladorVentas.modificarIDventa(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdVenta(),event.getNewValue()));*/
        tableCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableCliente.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCliente.setOnEditCommit(event -> ControladorCliente.modificarNombreCliente(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdCliente(),event.getNewValue()));
        tableAutomovil.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableAutomovil.setCellFactory(TextFieldTableCell.forTableColumn());
        tableAutomovil.setOnEditCommit(event -> ControladorAutomovil.modificarMarca(event.getTableView()
        .getSelectionModel().getSelectedItem().getMatricula(), event.getNewValue()));

        tableRefaccion.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableRefaccion.setCellFactory(TextFieldTableCell.forTableColumn());
        tableRefaccion.setOnEditCommit(event -> ControladorRefaccion.modificarMarca(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdRefaccion(), event.getNewValue()));
        tableServicio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableServicio.setCellFactory(TextFieldTableCell.forTableColumn());
        tableServicio.setOnEditCommit(event -> ControladorServicio.modificarDescripcion(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdServicio(),event.getNewValue()));
        tableFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        /*tableFecha.setCellFactory(TextFieldTableCell.forTableColumn());
        tableFecha.setOnEditCommit(event ->ControladorVentas.modificarFecha(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdVenta(),event.getNewValue()) );*/
        tableCosto.setCellValueFactory(new PropertyValueFactory<>("ventaRefaccionsByIdVenta"));
        /*tableCosto.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCosto.setOnEditCommit(event -> ControladorVentas.modificarVenta(event.getTableView().getSelectionModel()
        .getSelectedItem().getIdVenta(),event.getNewValue()));*/

        tablaReporte.setItems(dataVenta);
    }
    public void imprimirActionEvent(){

    }
    public void cancelarActionEvent(){

    }
}
