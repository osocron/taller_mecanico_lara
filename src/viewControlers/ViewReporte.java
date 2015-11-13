package viewControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import entidades.*;
import entityControlers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
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
        Alert alert = getWarningAlert("Reporte", "Atencion", "Imprimiendo reporte...");
        alert.showAndWait();
    }

    public void cerrarVentanaEvent(){
        Stage stage = (Stage) tableviewReporte.getScene().getWindow();
        stage.close();
    }

    public void generarReporteActionEvent() {
        if (checkBoxCliente.isSelected()) {
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
                        LocalDate date = fechaDatePicker.getValue();
                        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                        if (sqlDate.equals(ventasEntity.getFecha())) {
                            ventasEncontradas.add(ventasEntity);
                        }
                    }else {
                        ventasEncontradas.add(ventasEntity);
                    }
                }
            }));
            ObservableList<VentasEntity> observableVentasEncontradas = FXCollections.observableArrayList();
            observableVentasEncontradas.addAll(ventasEncontradas);
            tableviewReporte.getItems().clear();
            tableviewReporte.setItems(observableVentasEncontradas);
        }else if (checkBoxFecha.isSelected()){
            ArrayList<VentasEntity> ventasEncontradas = new ArrayList<>();
            dataVentas.forEach(ventasEntity -> {
                LocalDate date = fechaDatePicker.getValue();
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                if (sqlDate.equals(ventasEntity.getFecha())) {
                    ventasEncontradas.add(ventasEntity);
                }
            });
            ObservableList<VentasEntity> observableVentasEncontradas = FXCollections.observableArrayList();
            observableVentasEncontradas.addAll(ventasEncontradas);
            tableviewReporte.getItems().clear();
            tableviewReporte.setItems(observableVentasEncontradas);
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
