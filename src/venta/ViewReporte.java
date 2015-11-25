package venta;

import automovil.ControladorAutomovil;
import cliente.ControladorCliente;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import refaccion.ControladorRefaccion;
import servicio.ControladorServicio;
import servicio.ControladorServicioAutomovil;
import viewControlers.DatePickerCell;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ViewReporte implements Initializable{

    public TableView<VentasEntity> tableviewReporte;
    public JFXCheckBox checkBoxCliente;
    public ComboBox<ClienteEntity> comboBoxClientes;
    public ComboBox<AutomovilesEntity> comboboxAutomovil;
    public JFXCheckBox checkBoxFecha;
    public DatePicker fechaDatePicker;
    public TableColumn<VentasEntity,String> tableColumnIDVenta;
    public TableColumn tableColumnFecha;
    public TableColumn<VentasEntity,String> tableColumnIDCliente;
    public JFXButton buttonReporte;
    public JFXButton generarReporteButton;

    private ObservableList<VentasEntity> dataVentas = FXCollections.observableArrayList();
    private ObservableList<VentaServicioEntity> dataVentaServicio = FXCollections.observableArrayList();
    private ObservableList<VentaRefaccionEntity> dataVentaRefaccion = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataClientes = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAutomoviles = FXCollections.observableArrayList();
    private ObservableList<ServicioAutomovilEntity> dataServicioAutomoviles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        obtenerDatosDeLaBD();
        prepararCheckBoxes();
        ligarClienteAutomovilComboBox();

        comboBoxClientes.setDisable(true);
        comboBoxClientes.setItems(dataClientes);
        comboboxAutomovil.setDisable(true);
        fechaDatePicker.setDisable(true);

        tableColumnIDVenta.setCellValueFactory(new PropertyValueFactory<>("idVenta"));
        tableColumnIDCliente.setCellValueFactory(new PropertyValueFactory<>("idClientes"));
        tableColumnFecha.setCellValueFactory(new PropertyValueFactory<DatePickerCell, java.sql.Date>("fecha"));
        tableColumnFecha.setCellFactory(param -> {
            DatePickerCell datePickerCell = new DatePickerCell(dataVentas);
            return datePickerCell;
        });

    }

    private void obtenerDatosDeLaBD() {
        java.util.List<VentasEntity> listaVenta = ControladorVentas.getVentas();
        dataVentas.addAll(listaVenta);
        List<VentaServicioEntity> ventaServicioEntityList = ControladorVentasServicio.getVentaServicio();
        dataVentaServicio.addAll(ventaServicioEntityList);
        List<VentaRefaccionEntity> ventasRefaccionList = ControladorVentasRefaccion.getVentasRefaccion();
        dataVentaRefaccion.addAll(ventasRefaccionList);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataClientes.addAll(clienteEntityList);
        List<AutomovilesEntity> automovilesEntityList = ControladorAutomovil.getAutomoviles();
        dataAutomoviles.addAll(automovilesEntityList);
        List<ServicioAutomovilEntity> servicioAutomovilEntityList = ControladorServicioAutomovil.getServicioAuto();
        dataServicioAutomoviles.addAll(servicioAutomovilEntityList);
    }

    private void ligarClienteAutomovilComboBox(){
        comboBoxClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //Obtener id Cliente
            String idCliente = comboBoxClientes.getSelectionModel().getSelectedItem().getIdCliente();
            //Listas auxiliares
            ObservableList<AutomovilesEntity> dataAutosEncontrados = FXCollections.observableArrayList();
            ArrayList<AutomovilesEntity> listaAutosEncontrados = new ArrayList<>();
            //Buscar dentro de la BD los autos que coincidan con el ID del Cliente
            dataAutomoviles.forEach(automovilesEntity -> {
                if (automovilesEntity.getIdClientes().equals(idCliente)) {
                    listaAutosEncontrados.add(automovilesEntity);
                }
            });
            //Agregar los autos encontrados al comboBox de Autos
            dataAutosEncontrados.addAll(listaAutosEncontrados);
            comboboxAutomovil.setItems(dataAutosEncontrados);
        });
    }

    private void prepararCheckBoxes() {
        checkBoxCliente.setOnAction(event -> {
            if (checkBoxCliente.isSelected()){
                comboBoxClientes.setDisable(false);
                comboboxAutomovil.setDisable(false);
            }else{
                comboBoxClientes.setDisable(true);
                comboboxAutomovil.setDisable(true);
            }
        });
        checkBoxFecha.setOnAction(event -> {
            if(checkBoxFecha.isSelected()){
                fechaDatePicker.setDisable(false);
            }else{
                fechaDatePicker.setDisable(true);
            }
        });
    }

    public void crearReporteEvent(){
        Alert alert = getWarningAlert("Reporte", "Atencion", "Generando reporte...");
        alert.showAndWait();
        try {
            ArrayList<ReporteEntity> dataReporte = getDataReporte();
            createPdf("reporte.pdf",dataReporte);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        try {
            Runtime.getRuntime().exec("evince reporte.pdf");
        } catch (IOException e) {
            e.getCause();
            Desktop desktop;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    File f = new File("reporte.pdf");
                    try {
                        desktop.open(f);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

    }

    private ArrayList<ReporteEntity> getDataReporte() {
        ObservableList<VentasEntity> ventasFromTable = null;
        ArrayList<ReporteEntity> dataReporte = new ArrayList<>();
        if (!tableviewReporte.getItems().isEmpty()) {
            ventasFromTable = tableviewReporte.getItems();
        }
        if (ventasFromTable != null) {

            ArrayList<VentaServicioEntity> dataVentasServicioEncontrados = new ArrayList<>();
            ArrayList<VentaRefaccionEntity> dataVentasRefaccionEncontrados = new ArrayList<>();

            ventasFromTable.forEach(ventasEntity -> {
                dataVentaServicio.forEach(ventaServicioEntity -> {
                    if (ventaServicioEntity.getIdVentas() == ventasEntity.getIdVenta()) {
                        dataVentasServicioEncontrados.add(ventaServicioEntity);
                    }
                });
                dataVentaRefaccion.forEach(ventaRefaccionEntity -> {
                    if (ventaRefaccionEntity.getIdVentas() == ventasEntity.getIdVenta()) {
                        dataVentasRefaccionEncontrados.add(ventaRefaccionEntity);
                    }
                });
            });

            if (!dataVentasServicioEncontrados.isEmpty()) {
                dataVentasServicioEncontrados.forEach(ventaServicioEntity -> {
                    VentasEntity ventasEntity = ControladorVentas.getVentaPorID(ventaServicioEntity.getIdVentas());
                    ServicioEntity servicioEntity = ControladorServicio.getServicioPorID(ventaServicioEntity.getIdServicios());
                    Date date = ventasEntity.getFecha();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String fecha = df.format(date);
                    ReporteEntity reporteEntity = new ReporteEntity(
                            String.valueOf(ventasEntity.getIdVenta()),
                            fecha,
                            servicioEntity.getDescripcion(),
                            "1",
                            String.valueOf(servicioEntity.getCosto())
                    );
                    dataReporte.add(reporteEntity);
                });
            }

            if (!dataVentasRefaccionEncontrados.isEmpty()){
                dataVentasRefaccionEncontrados.forEach(ventaRefaccionEntity -> {
                    VentasEntity ventasEntity = ControladorVentas.getVentaPorID(ventaRefaccionEntity.getIdVentas());
                    RefaccionEntity refaccionEntity = ControladorRefaccion.getRefaccionPorID(ventaRefaccionEntity.getIdRefacciones());
                    Date date = ventasEntity.getFecha();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String fecha = df.format(date);
                    ReporteEntity reporteEntity = new ReporteEntity(
                            String.valueOf(ventasEntity.getIdVenta()),
                            fecha,
                            refaccionEntity.getMarca(),
                            String.valueOf(refaccionEntity.getCantidad()),
                            String.valueOf(refaccionEntity.getPrecio())
                    );
                    dataReporte.add(reporteEntity);
                });
            }

        }
        return dataReporte;
    }

    public void createPdf(String filename, ArrayList<ReporteEntity> dataReporte)
            throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        document.addAuthor("osocron");
        document.addTitle("Reporte de Ventas");
        com.itextpdf.text.Font font = new Font(Font.FontFamily.HELVETICA,22,Font.BOLD);
        Chunk chunk = new Chunk("Reporte de Ventas - Taller Mecanico Lara",font);
        Paragraph paragraph = new Paragraph();
        paragraph.add(chunk);
        paragraph.setSpacingBefore(30);
        paragraph.setSpacingAfter(50);
        document.add(paragraph);
        document.add(createPDFTable(dataReporte));
        document.close();
    }

    public static PdfPTable createPDFTable(ArrayList<ReporteEntity> dataReporte) {
        PdfPTable table = new PdfPTable(5);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Reporte de Ventas"));
        cell.setColspan(5);
        table.addCell(cell);
        //Titulos
        table.addCell("ID VENTA");
        table.addCell("FECHA");
        table.addCell("CONCEPTO");
        table.addCell("CANTIDAD");
        table.addCell("COSTO");
        //Contenido
        final double[] total = {0.0};
        dataReporte.forEach(reporteEntity -> {
            table.addCell(reporteEntity.getIdVenta());
            table.addCell(reporteEntity.getFecha());
            table.addCell(reporteEntity.getConcepto());
            table.addCell(reporteEntity.getCantidad());
            table.addCell(reporteEntity.getCosto());
            total[0] = total[0] + (Double.valueOf(reporteEntity.getCantidad()) * Double.valueOf(reporteEntity.getCosto()));
        });
        //Total
        cell = new PdfPCell(new Phrase("TOTAL"));
        cell.setColspan(4);
        table.addCell(cell);
        BigDecimal roundedTotal = BigDecimal.valueOf(total[0]);
        roundedTotal = roundedTotal.setScale(2,BigDecimal.ROUND_HALF_EVEN);
        table.addCell(String.valueOf(roundedTotal));
        return table;
    }

    public void cerrarVentanaEvent(){
        Stage stage = (Stage) tableviewReporte.getScene().getWindow();
        stage.close();
    }

    public void generarReporteActionEvent() {
        if (checkBoxCliente.isSelected()) {
            if ((comboBoxClientes.getSelectionModel().getSelectedIndex() >= 0)
                    &&(comboboxAutomovil.getSelectionModel().getSelectedIndex() >= 0)) {
                ArrayList<ServicioAutomovilEntity> servicioAutomovilEncontrados = new ArrayList<>();
                dataServicioAutomoviles.forEach(servicioAutomovilEntity -> {
                    if (servicioAutomovilEntity.getMatricula().equals(
                            comboboxAutomovil.getSelectionModel().getSelectedItem().getMatricula())) {
                        servicioAutomovilEncontrados.add(servicioAutomovilEntity);
                    }
                });
                ArrayList<VentaServicioEntity> ventaServicioEncontrados = new ArrayList<>();
                dataVentaServicio.forEach(ventaServicioEntity -> servicioAutomovilEncontrados.forEach(servicioAutomovilEntity -> {
                    if (servicioAutomovilEntity.getIdServicios() == ventaServicioEntity.getIdServicios()) {
                        ventaServicioEncontrados.add(ventaServicioEntity);
                    }
                }));
                ArrayList<VentasEntity> ventasEncontradas = new ArrayList<>();
                dataVentas.forEach(ventasEntity -> ventaServicioEncontrados.forEach(ventaServicioEntity -> {
                    if (ventaServicioEntity.getIdVentas() == ventasEntity.getIdVenta()) {
                        if (checkBoxFecha.isSelected()) {
                            if (fechaDatePicker.getValue() != null) {
                                LocalDate date = fechaDatePicker.getValue();
                                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                                if (sqlDate.equals(ventasEntity.getFecha())) {
                                    ventasEncontradas.add(ventasEntity);
                                }
                            }else {
                                Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar una Fecha!");
                                alert.showAndWait();
                            }
                        } else {
                            ventasEncontradas.add(ventasEntity);
                        }
                    }
                }));
                ObservableList<VentasEntity> observableVentasEncontradas = FXCollections.observableArrayList();
                observableVentasEncontradas.addAll(ventasEncontradas);
                tableviewReporte.getItems().clear();
                tableviewReporte.setItems(observableVentasEncontradas);
            }else{
                if (comboBoxClientes.getSelectionModel().getSelectedIndex() >= 0) {
                    ArrayList<VentasEntity> ventasEncontradas = new ArrayList<>();
                    dataVentas.forEach(ventasEntity -> {
                        if (ventasEntity.getIdClientes().equals(
                                comboBoxClientes.getSelectionModel().getSelectedItem().getIdCliente())) {
                            if (checkBoxFecha.isSelected()) {
                                if (fechaDatePicker.getValue() != null) {
                                    LocalDate date = fechaDatePicker.getValue();
                                    java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                                    if (sqlDate.equals(ventasEntity.getFecha())) {
                                        ventasEncontradas.add(ventasEntity);
                                    }
                                }else {
                                    Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar una Fecha!");
                                    alert.showAndWait();
                                }
                            } else {
                                ventasEncontradas.add(ventasEntity);
                            }
                        }
                    });
                    ObservableList<VentasEntity> observableVentasEncontradas = FXCollections.observableArrayList();
                    observableVentasEncontradas.addAll(ventasEncontradas);
                    tableviewReporte.getItems().clear();
                    tableviewReporte.setItems(observableVentasEncontradas);
                }else {
                    Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un Cliente!");
                    alert.showAndWait();
                }
            }
        }else if (checkBoxFecha.isSelected()){
            if (fechaDatePicker.getValue() != null) {
                ArrayList<VentasEntity> ventasEncontradas = new ArrayList<>();
                LocalDate date = fechaDatePicker.getValue();
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                dataVentas.forEach(ventasEntity -> {
                    if (ventasEntity.getFecha().equals(sqlDate)) {
                        ventasEncontradas.add(ventasEntity);
                    }
                });
                ObservableList<VentasEntity> observableVentasEncontradas = FXCollections.observableArrayList();
                observableVentasEncontradas.addAll(ventasEncontradas);
                tableviewReporte.getItems().clear();
                tableviewReporte.setItems(observableVentasEncontradas);
            }else {
                Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar una Fecha!");
                alert.showAndWait();
            }
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
