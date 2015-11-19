package venta;

import automovil.ControladorAutomovil;
import cliente.ControladorCliente;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.oracle.webservices.internal.api.message.PropertySet;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import com.itextpdf.text.pdf.PdfWriter;
import refaccion.ControladorRefaccion;
import servicio.ControladorServicio;
import venta.ControladorVentas;
import viewControlers.DatePickerCell;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;
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
    public TableColumn tableFecha;
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
        /*PdfPTable venta = new PdfPTable(1);
        PdfPTable cliente = new PdfPTable(1);
        PdfPTable automovil = new PdfPTable(1);
        PdfPTable refaccion = new PdfPTable(1);
        PdfPTable servicio = new PdfPTable(1);
        PdfPTable fechas = new PdfPTable(1);*/

        tablaReporte.setEditable(true);
        tableVenta.setCellValueFactory(new PropertyValueFactory<>("idventa"));

        tableCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableAutomovil.setCellValueFactory(new PropertyValueFactory<>("marca"));

        tableRefaccion.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tableServicio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableFecha.setCellValueFactory(new PropertyValueFactory<DatePickerCell, java.sql.Date>("fecha"));
        tableFecha.setCellFactory(param -> {
            DatePickerCell fecha = new DatePickerCell(dataVenta);
            return fecha;
        });
        tableCosto.setCellValueFactory(new PropertyValueFactory<>("ventaRefaccionsByIdVenta"));
        tablaReporte.setItems(dataVenta);
    }
        public void imprimirActionEvent(){

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            buttonImprimir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        try {
                            PdfWriter.getInstance(document , new FileOutputStream("reporte.pdf"));
                            document.open();
                            document.add(tablaPDF());
                            document.close();
                        }
                        catch (Exception e){

                        }
                    }
            });
        }
    private PdfPTable tablaPDF() {
        PdfPTable tabla = new PdfPTable(6);

        PdfPCell celda, celdaVenta, celdaRefaccion, celdaCosto;

        celda = new PdfPCell(new Phrase("Reporte"));
        celda.setColspan(6);
        tabla.addCell(celda);

        celdaVenta = new PdfPCell(tabla);
        celdaVenta.setColspan(6);
        tabla.addCell(celdaVenta);

        celdaRefaccion = new PdfPCell(tabla);
        celdaRefaccion.setColspan(6);
        tabla.addCell(celdaRefaccion);

        celdaCosto = new PdfPCell(tabla);
        celdaCosto.setColspan(6);
        tabla.addCell(celdaCosto);

        return null;
    }


    public void cancelarActionEvent(){
    }
}
