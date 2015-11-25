package venta;

import cliente.ControladorCliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import refaccion.ControladorRefaccion;
import servicio.ControladorServicioRefaccion;
import viewControlers.InputValidator;
import viewControlers.ViewOpener;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarVenta implements Initializable {

    public Label labelIDventa;
    public JFXTextField textfieldIDventa;
    public JFXButton buttonAceptar;
    public JFXButton buttonCancelar;
    public ComboBox<ClienteEntity> comboBoxCliente;
    public DatePicker fechaDatePicker;
    public ListView<ServicioEntity> servicioListView;
    public JFXButton eliminarServicioButton;
    public JFXButton agregarServicioButton;
    public TableView<RefaccionEntity> refaccionTableView;
    public TableColumn<RefaccionEntity,String> refaccionTableColumn;
    public TableColumn<RefaccionEntity,Integer> cantidadTableColumn;
    public JFXButton eliminarRefaccionButton;
    public JFXButton agregarRefaccionActionEvent;
    public JFXTextField totalTextField;

    private ObservableList<VentasEntity> data = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<ServicioEntity> dataServicioParaListView = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccionesParaTabla = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefacciones = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obtenerDatosDeBD();
        comboBoxCliente.setItems(dataCliente);
        preparTablaRefacciones();
        textfieldIDventa.setEditable(false);
        int nextID = ControladorVentas.getLastID() + 1;
        textfieldIDventa.setText(String.valueOf(nextID));
    }

    private void obtenerDatosDeBD() {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        data.addAll(listaVentas);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataCliente.addAll(clienteEntityList);
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        dataRefacciones.addAll(refaccionEntityList);
    }

    private void preparTablaRefacciones() {
        refaccionTableView.setEditable(true);
        refaccionTableColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        cantidadTableColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cantidadTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return String.valueOf(object);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        cantidadTableColumn.setOnEditCommit(event ->
        {
            calcularTotalPorRefaccionModificado(event.getTableView().getItems().get(
                    event.getTablePosition().getRow()),event.getOldValue(),event.getNewValue());
            event.getTableView().getItems().get(
                    event.getTablePosition().getRow()).setCantidad(event.getNewValue());
        });
    }

    public void crearVentaEvent(){

        if ((fechaDatePicker.getValue() != null) && (!comboBoxCliente.getSelectionModel().isEmpty()) &&
                ((!dataRefaccionesParaTabla.isEmpty()) || (!dataServicioParaListView.isEmpty()))) {

            LocalDate date = fechaDatePicker.getValue();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
            cal.set(Calendar.MONTH, date.getMonthValue() - 1);
            cal.set(Calendar.YEAR, date.getYear());
            java.util.Date simpleDate = cal.getTime();
            java.sql.Date sqlDate = new java.sql.Date(simpleDate.getTime());

            int idVenta = Integer.parseInt(textfieldIDventa.getText());

            ControladorVentas.guardarVenta(
                    ControladorVentas.crearVenta(
                            idVenta,
                            sqlDate,
                            comboBoxCliente.getSelectionModel().getSelectedItem().getIdCliente()
                    )
            );

            final int[] contRefacciones = {0};
            if ((!dataRefaccionesParaTabla.isEmpty())) {
                dataRefaccionesParaTabla.forEach(refaccionEntity -> {
                    ControladorVentasRefaccion.guardarVentasRefaccion(
                            ControladorVentasRefaccion.crearVentaRefaccion(
                                    (idVenta * 10) + contRefacciones[0],
                                    Integer.parseInt(
                                            textfieldIDventa.getText()
                                    ),
                                    refaccionEntity.getIdRefaccion()
                            )
                    );
                    contRefacciones[0]++;
                });
            }

            final int[] contServicios = {0};
            if ((!dataServicioParaListView.isEmpty())) {
                dataServicioParaListView.forEach(servicioEntity -> {
                    ControladorVentasServicio.guardarVentaServicio(
                            ControladorVentasServicio.crearVentaServicio(
                                    (idVenta * 10) + contServicios[0],
                                    Integer.parseInt(
                                            textfieldIDventa.getText()
                                    ),
                                    servicioEntity.getIdServicio()
                            )
                    );
                    //Buscar Refacciones dentro del Servicio
                    List<ServicioRefaccionEntity> servicioRefaccionEntityList = ControladorServicioRefaccion.getServicioRefaccion();
                    servicioRefaccionEntityList.forEach(servicioRefaccionEntity -> {
                        if (servicioRefaccionEntity.getIdServicios() == servicioEntity.getIdServicio()){
                            RefaccionEntity refaccionEntity = ControladorRefaccion.getRefaccionPorID(servicioRefaccionEntity.getIdRefacciones());
                            ControladorVentasRefaccion.guardarVentasRefaccion(
                                ControladorVentasRefaccion.crearVentaRefaccion(
                                        (idVenta * 10) + contRefacciones[0],
                                        Integer.parseInt(
                                                textfieldIDventa.getText()
                                        ),
                                        refaccionEntity.getIdRefaccion()
                                )
                            );
                        }
                        contRefacciones[0]++;
                    });
                    contServicios[0]++;
                });
            }

            Alert alert = getWarningAlert("Exitoso", "Atencion", "Venta registrada exitosamente!");
            alert.showAndWait();
            cancelarActionEvent();
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Verifique que los datos sean correctos!");
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

    public void eliminarServicioActionEvent() {
        if (servicioListView.getSelectionModel().getSelectedItem() != null) {
            ServicioEntity servicioEntity = servicioListView.getSelectionModel().getSelectedItem();
            servicioListView.getItems().remove(servicioEntity);
            calcularTotalPorServicioEliminado(servicioEntity);
        }else{
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un elemento!");
            alert.showAndWait();
        }
    }

    public void agregarServicioActionEvent() {
        ViewOpener viewOpener = new ViewOpener();
        if (!comboBoxCliente.getSelectionModel().isEmpty()) {
            viewOpener.openServicioPicker("servicio/SeleccionarServicio.fxml", "Taller Mecanico Lara", this,
                    comboBoxCliente.getSelectionModel().getSelectedItem().getIdCliente());
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un Cliente!");
            alert.showAndWait();
        }
    }

    public void eliminarRefaccionActionEvent() {
        if (refaccionTableView.getSelectionModel().getSelectedItem() != null) {
            RefaccionEntity refaccionEntity = refaccionTableView.getSelectionModel().getSelectedItem();
            refaccionTableView.getItems().remove(refaccionEntity);
            calcularTotalPorRefaccionEliminada(refaccionEntity);
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de seleccionar un elemento!");
            alert.showAndWait();
        }
    }

    public void agregarRefaccionActionEvent() {
        ViewOpener viewOpener = new ViewOpener();
        viewOpener.openRefaccionPicker("refaccion/SeleccionRefacciones.fxml", "Taller Mecanico Lara", this, "2");
    }
    public boolean verificarDatosVenta(String idVenta, String idCliente){
        final boolean[] existe={true};
        dataCliente.forEach(clienteEntity -> {
            if (clienteEntity.getIdCliente().equals(idCliente)){
                existe[0] = false;
            }
        });
        return existe[0];
    }

    public void cancelarActionEvent() {
        int nextID = ControladorVentas.getLastID() + 1;
        textfieldIDventa.setText(String.valueOf(nextID));
        comboBoxCliente.getSelectionModel().clearSelection();
        servicioListView.getItems().clear();
        refaccionTableView.getItems().clear();
        totalTextField.setText("");
    }

    public void setSelectedServicio(ServicioEntity servicioEntity) {
        dataServicioParaListView.add(servicioEntity);
        servicioListView.setItems(dataServicioParaListView);
        calcularTotalPorServicio(servicioEntity);
    }

    public void setSelectedRefaccion(RefaccionEntity refaccionEntity) {
        dataRefaccionesParaTabla.add(refaccionEntity);
        refaccionTableView.setItems(dataRefaccionesParaTabla);
        calcularTotalPorRefaccion(refaccionEntity);
    }

    private void calcularTotalPorRefaccion(RefaccionEntity refaccionEntity) {
        BigDecimal totalPorRefacion = refaccionEntity.getPrecio().multiply(
                BigDecimal.valueOf((double)refaccionEntity.getCantidad()));
        Double valorAnterior = 0.0;
        if(!totalTextField.getText().isEmpty()) {
            valorAnterior = Double.valueOf(totalTextField.getText());
        }
        totalTextField.setText(
                String.valueOf(
                    BigDecimal.valueOf(
                            valorAnterior
                    ).add(
                            totalPorRefacion
                    )
                )
        );
    }

    private void calcularTotalPorRefaccionEliminada(RefaccionEntity refaccionEntity) {
        BigDecimal totalPorRefacion = refaccionEntity.getPrecio().multiply(
                BigDecimal.valueOf((double)refaccionEntity.getCantidad()));
        Double valorAnterior = 0.0;
        if(!totalTextField.getText().isEmpty()) {
            valorAnterior = Double.valueOf(totalTextField.getText());
        }
        totalTextField.setText(
                String.valueOf(
                        BigDecimal.valueOf(
                                valorAnterior
                        ).subtract(
                                totalPorRefacion
                        )
                )
        );
    }

    private void calcularTotalPorServicio(ServicioEntity servicioEntity) {

        final BigDecimal[] total = {BigDecimal.valueOf(0)};
        List<ServicioRefaccionEntity> servicioRefaccionEntityList = ControladorServicioRefaccion.getServicioRefaccion();

        servicioRefaccionEntityList.forEach(servicioRefaccionEntity -> {
            RefaccionEntity refaccionEntity =
                    ControladorRefaccion.getRefaccionPorID(
                            servicioRefaccionEntity.getIdRefacciones()
                    );

            if(servicioEntity.getIdServicio() == servicioRefaccionEntity.getIdServicios()){
                total[0] = total[0].add(
                        BigDecimal.valueOf(
                                servicioRefaccionEntity.getCantidad()
                        ).multiply(
                                refaccionEntity.getPrecio()
                        )
                );
            }
        });

        Double valorAnterior = 0.0;
        if(!totalTextField.getText().isEmpty()) {
            valorAnterior = Double.valueOf(totalTextField.getText());
        }
        totalTextField.setText(
                String.valueOf(
                        BigDecimal.valueOf(
                                valorAnterior
                        ).add(
                        total[0].add(
                                servicioEntity.getCosto()
                        )
                )
        ));
    }

    private void calcularTotalPorServicioEliminado(ServicioEntity servicioEntity) {

        final BigDecimal[] total = {BigDecimal.valueOf(0)};
        List<ServicioRefaccionEntity> servicioRefaccionEntityList = ControladorServicioRefaccion.getServicioRefaccion();

        servicioRefaccionEntityList.forEach(servicioRefaccionEntity -> {
            RefaccionEntity refaccionEntity =
                    ControladorRefaccion.getRefaccionPorID(
                            servicioRefaccionEntity.getIdRefacciones()
                    );
            if(servicioEntity.getIdServicio() == servicioRefaccionEntity.getIdServicios()){
                total[0] = total[0].add(
                        BigDecimal.valueOf(
                                servicioRefaccionEntity.getCantidad()
                        ).multiply(
                                refaccionEntity.getPrecio()
                        )
                );
            }
        });

        Double valorAnterior = 0.0;
        if(!totalTextField.getText().isEmpty()) {
            valorAnterior = Double.valueOf(totalTextField.getText());
        }

        totalTextField.setText(
                String.valueOf(
                        BigDecimal.valueOf(
                                valorAnterior
                        ).subtract(
                                total[0]
                        ).subtract(
                                servicioEntity.getCosto()
                        )
                )
        );
    }

    private void calcularTotalPorRefaccionModificado(RefaccionEntity refaccionEntity,
                                                     Integer oldValue, Integer newValue) {
        Double valorAnterior = 0.0;
        if(!totalTextField.getText().isEmpty()) {
            valorAnterior = Double.valueOf(totalTextField.getText());
        }
        if(oldValue < newValue){
            totalTextField.setText(
                    String.valueOf(
                            BigDecimal.valueOf(valorAnterior).add(
                                    (
                                            refaccionEntity.getPrecio().multiply(
                                                    BigDecimal.valueOf(
                                                            newValue - oldValue
                                                    )
                                            )
                                    )
                            )
                    )
            );
        }else{
            totalTextField.setText(
                    String.valueOf(
                            BigDecimal.valueOf(valorAnterior).subtract(
                                    (
                                            refaccionEntity.getPrecio().multiply(
                                                    BigDecimal.valueOf(
                                                            oldValue - newValue
                                                    )
                                            )
                                    )
                            )
                    )
            );
        }
    }

}
