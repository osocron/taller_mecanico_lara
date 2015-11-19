package venta;

import cliente.ControladorCliente;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import refaccion.ControladorRefaccion;
import servicio.ControladorServicio;
import viewControlers.DatePickerCell;

import javax.swing.*;
import java.io.FileOutputStream;
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
        tableServicio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        //tableFecha.setCellFactory(DatePickerCell.);
        /*tableFecha.setOnEditCommit(event -> ControladorVentas.modificarFecha(event.getTableView()
        .getSelectionModel().getSelectedItem().getIdVenta(),event.getNewValue()));*/

        //Tenemos que seleccionar el campo de costo del servicio en la tabla servicio
        tableCostoServicio.setCellValueFactory(new PropertyValueFactory<>("idVentas"));
        tableCostoRefaccion.setCellValueFactory(new PropertyValueFactory<>("idRefacciones"));


        tablaCotizacion.setItems(dataVentas);

    }
    public void imprimirActionEvent(){
        Document doc = new Document();
        try{
            PdfWriter.getInstance(doc,new FileOutputStream("reporte.pdf"));

            doc.open();
            PdfPTable tabla = new PdfPTable(6);
            tabla.addCell(tableCliente.getTypeSelector());
            doc.add(tabla);
            doc.close();
        }catch (Exception e){

        }
    }
    public void cancelarActionEvent(){
        textfieldSubtotal.setText("");
        textfieldIVA.setText("");
        textfieldTotal.setText("");
    }

}


