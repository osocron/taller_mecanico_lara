package viewControlers;

import entidades.*;
import entityControlers.ControladorCliente;
import entityControlers.ControladorServicioRefaccion;
import entityControlers.ControladorVentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarVenta implements Initializable {

    public Label labelIDventa;
    public TextField textfieldIDventa;
    public Button buttonAceptar;
    public Button buttonCancelar;
    public MenuItem menuItemClose;
    public ComboBox comboBoxCliente;
    public DatePicker fechaDatePicker;
    public ListView<ServicioEntity> servicioListView;
    public Button eliminarServicioButton;
    public Button agregarServicioButton;
    public TableView<RefaccionEntity> refaccionTableView;
    public TableColumn<RefaccionEntity,String> refaccionTableColumn;
    public TableColumn<RefaccionEntity,Integer> cantidadTableColumn;
    public Button eliminarRefaccionButton;
    public Button agregarRefaccionActionEvent;
    public TextField totalTextField;

    private ObservableList<VentasEntity> data = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<ServicioEntity> dataServicioParaListView = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccionesParaTabla = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<VentasEntity> listaVentas = ControladorVentas.getVentas();
        data.addAll(listaVentas);
        List<ClienteEntity> clienteEntityList = ControladorCliente.getCliente();
        dataCliente.addAll(clienteEntityList);
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
        cantidadTableColumn.setOnEditCommit(event -> event.getTableView().getItems().get(
                event.getTablePosition().getRow()).setCantidad(event.getNewValue()));

    }
    public void crearVentaEvent(){
        textfieldIDventa.setText("");
    }

    public void cerrarVentanaClose(ActionEvent actionEvent) {

    }

    public void eliminarServicioActionEvent(ActionEvent actionEvent) {

    }

    public void agregarServicioActionEvent(ActionEvent actionEvent) {
        ViewOpener viewOpener = new ViewOpener();
        viewOpener.openServicioPicker("vista/SeleccionarServicio.fxml","Taller Mecanico Lara",this);
    }


    public void eliminarRefaccionActionEvent(ActionEvent actionEvent) {

    }

    public void agregarRefaccionActionEvent(ActionEvent actionEvent) {
        ViewOpener viewOpener = new ViewOpener();
        viewOpener.openRefaccionPicker("vista/SeleccionRefacciones.fxml","Taller Mecanico Lara",this,"2");
    }

    public void cancelarActionEvent(ActionEvent actionEvent) {

    }

    public void setSelectedServicio(ServicioEntity servicioEntity) {
        dataServicioParaListView.add(servicioEntity);
        servicioListView.setItems(dataServicioParaListView);
        calcularTotalPorServicio(servicioEntity);
    }

    private void calcularTotalPorServicio(ServicioEntity servicioEntity) {
        final BigDecimal[] total = {BigDecimal.valueOf(0)};
        List<ServicioRefaccionEntity> servicioRefaccionEntityList = ControladorServicioRefaccion.getServicioRefaccion();
        servicioRefaccionEntityList.forEach(servicioRefaccionEntity -> {
            if(servicioEntity.getIdServicio() == servicioRefaccionEntity.getIdServicios()){
                total[0] = total[0].add(BigDecimal.valueOf(servicioRefaccionEntity.getCantidad()));
            }
        });
        Double valorAnterior = 0.0;
        if(!totalTextField.getText().isEmpty()) {
            valorAnterior = Double.valueOf(totalTextField.getText());
        }
        totalTextField.setText(String.valueOf(valorAnterior+
                total[0].doubleValue()+
                servicioEntity.getCosto().doubleValue()));
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
        totalTextField.setText(String.valueOf(valorAnterior + totalPorRefacion.doubleValue()));
    }
}
