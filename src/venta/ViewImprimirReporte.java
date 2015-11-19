package venta;

import automovil.ControladorAutomovil;
import cliente.ControladorCliente;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import refaccion.ControladorRefaccion;
import servicio.ControladorServicio;
import venta.ControladorVentas;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    public Button buttonImprimir;


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
    public class ImprimirActionEvent extends JFrame{
        public void imprimirActionEvent(){
            JFileChooser archivo = new JFileChooser();
            buttonImprimir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int opcion = archivo.showSaveDialog(null);
                    if (opcion==archivo.APPROVE_OPTION){
                        try {
                            OutputStream salida = new FileOutputStream(archivo.getSelectedFile());
                            Document doc = new Document();
                            PdfWriter.getInstance(doc,salida);
                            doc.open();
                            //doc.add(new Paragraph(tablaReporte));
                            doc.close();
                            salida.close();
                        }
                        catch (Exception e){

                        }
                    }
                }
            });
            this.add(tablaReporte,BorderLayout.NORTH);
        }

        private void add(TableView<VentasEntity> tablaReporte, String north) {
            this.setSize(300,400);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public void cancelarActionEvent(){

    }
}
