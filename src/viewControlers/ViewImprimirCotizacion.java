package viewControlers;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entidades.*;
import entityControlers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 11/11/2015.
 */
public class ViewImprimirCotizacion implements Initializable {
    @FXML
    public TableView<VentasEntity> tablaCotizacion;
    public TableColumn<ClienteEntity, String> tableCliente;
    public TableColumn<RefaccionEntity, String> tableRefaccion;
    public TableColumn<ServicioEntity, String> tableServicio;
    public TableColumn<VentasEntity,String> tableFecha;
    public TableColumn<VentaServicioEntity,String> tableCostoServicio;
    public TableColumn<VentaRefaccionEntity,String> tableCostoRefaccion;
    public JFXButton buttonImprimir;
    public JFXButton buttonCancelar;
    public TextField textfieldSubtotal;
    public TextField textfieldIVA;
    public TextField textfieldTotal;


    private ObservableList<VentasEntity> dataVentas = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccion =FXCollections.observableArrayList();
    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
    private ObservableList<VentaRefaccionEntity> dataVentaRefaccion = FXCollections.observableArrayList();
    private ObservableList<VentaServicioEntity> dataVentaServicio = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<VentasEntity> ventasEntityList = ControladorVentas.getVentas();
        dataVentas.addAll(ventasEntityList);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataCliente.addAll(clienteEntityList);
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(refaccionEntityList);
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        dataServicio.addAll(servicioEntityList);
        List<VentaServicioEntity> ventaServicioEntityList = ControladorVentasServicio.getVentaServicio();
        dataVentaServicio.addAll(ventaServicioEntityList);
        List<VentaRefaccionEntity> ventaRefaccionEntityList = ControladorVentasRefaccion.getVentasRefaccion();
        dataVentaRefaccion.addAll(ventaRefaccionEntityList);
        tablaCotizacion.setItems(dataVentas);

    }

    private void prepararTableView(){
        tablaCotizacion.setEditable(true);
        tableCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableRefaccion.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableRefaccion.setCellFactory(TextFieldTableCell.forTableColumn());
        tableRefaccion.setOnEditCommit(event -> ControladorRefaccion.modificarMarca(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdRefaccion(),event.getNewValue()));
        tableServicio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableServicio.setCellFactory(TextFieldTableCell.forTableColumn());
        tableServicio.setOnEditCommit(event -> ControladorServicio.modificarDescripcion(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdServicio(),event.getNewValue()));
        tableFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tableFecha.setCellFactory(TextFieldTableCell.forTableColumn());
        /*tableFecha.setOnEditCommit(event -> ControladorVentas.modificarFecha(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdVenta(),event.getNewValue()));*/
        //Tenemos que seleccionar el campo de costo del servicio en la tabla servicio
        tableCostoServicio.setCellValueFactory(new PropertyValueFactory<>("idVentas"));
        tableCostoServicio.setCellFactory(TextFieldTableCell.forTableColumn());
        /*tableCostoServicio.setOnEditCommit(event -> ControladorVentasServicio.modificarIDventas(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdVentas(), event.getNewValue()));*/
        tableCostoRefaccion.setCellValueFactory(new PropertyValueFactory<>("idRefacciones"));
        tableCostoRefaccion.setCellFactory(TextFieldTableCell.forTableColumn());
        /*tableCostoRefaccion.setOnEditCommit(event -> ControladorVentasRefaccion.modificarIDrefaccion(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdVentaRefaccion(), event.getNewValue()));*/

        tablaCotizacion.setItems(dataVentas);

    }
    public class ImprimirActionEvent extends JFrame{
        public void imprimirActionEvent() {
            PdfPTable tabla = new PdfPTable(5);
            JFileChooser archivo = new JFileChooser();
            buttonImprimir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int opcion = archivo.showSaveDialog(null);
                    if (opcion==archivo.APPROVE_OPTION){
                        try {
                            OutputStream salida = new FileOutputStream(archivo.getSelectedFile());
                            Document doc = new Document();
                            PdfWriter.getInstance(doc, salida);
                            doc.open();
                            doc.add(tabla);
                            doc.close();
                            salida.close();
                        }
                        catch (Exception e){

                        }
                    }
                }
            });
            //this.add(tablaCotizacion,BorderLayout.CENTER);

        }
    }
    public void cancelarActionEvent(){
        textfieldSubtotal.setText("");
        textfieldIVA.setText("");
        textfieldTotal.setText("");
    }

}


