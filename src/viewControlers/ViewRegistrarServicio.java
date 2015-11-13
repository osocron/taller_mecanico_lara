package viewControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.*;
import entityControlers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarServicio implements Initializable {

    public Label labelIDservicio;
    public Label labelServicio;
    public Label labelDescripcion;
    public Label labelIDempleado;
    public Label labelPrecio;
    public JFXTextField textfieldServicio;
    public JFXTextField textfieldDescripcion;
    public ComboBox<String> comboboxServicio;
    public JFXTextField textfieldIDempleado;
    public JFXTextField textfieldPrecio;
    public JFXButton buttonCancelar;
    public JFXButton eliminarRefaccionButton;
    public JFXButton agregarRefaccionButton;
    public ComboBox<ClienteEntity> comboBoxClientes;
    public ComboBox<AutomovilesEntity> comboBoxAutos;
    public TableView<RefaccionEntity> tablaRefacciones;
    public TableColumn<RefaccionEntity,String> refaccionTableColumn;
    public TableColumn<RefaccionEntity,Integer> cantidadTableColumn;
    public JFXButton buttonRegistrarServicio;

    private ObservableList<ServicioEntity> dataServicios = FXCollections.observableArrayList();
    private ObservableList<ClienteEntity> dataClientes = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAutos = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefacciones = FXCollections.observableArrayList();
    private ObservableList<ServicioAutomovilEntity> dataServicioAutomovil = FXCollections.observableArrayList();
    private ObservableList<ServicioRefaccionEntity> dataServicioRefaccion = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccionesParaTabla = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obtenerDatosDeBD();
        ligarClienteAutomovilComboBox();
        tablaRefacciones.setEditable(true);
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
        textfieldServicio.setEditable(false);
        int nextID = ControladorServicio.getLastID() + 1;
        textfieldServicio.setText(String.valueOf(nextID));
    }


    private void obtenerDatosDeBD(){
        List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicios.addAll(listaServicio);
        List<ClienteEntity> listaClientes = ControladorCliente.getCliente();
        dataClientes.addAll(listaClientes);
        List<AutomovilesEntity> automovilesEntityList = ControladorAutomovil.getAutomoviles();
        dataAutos.addAll(automovilesEntityList);
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        dataRefacciones.addAll(refaccionEntityList);
        List<ServicioAutomovilEntity> servicioAutomovilEntityList = ControladorServicioAutomovil.getServicioAuto();
        dataServicioAutomovil.addAll(servicioAutomovilEntityList);
        List<ServicioRefaccionEntity> servicioRefaccionEntityList = ControladorServicioRefaccion.getServicioRefaccion();
        dataServicioRefaccion.addAll(servicioRefaccionEntityList);
        ObservableList<String> opcionesServicio = FXCollections.observableArrayList("Afinacion","Balanceo",
                "Cambio de Aceite","Alineación","Lavado de Motor","Balatas","Chasis");
        comboboxServicio.setItems(opcionesServicio);
        comboboxServicio.getSelectionModel().select(0);
        comboBoxClientes.setItems(dataClientes);
    }

    private void ligarClienteAutomovilComboBox(){
        comboBoxClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //Obtener id Cliente
            String idCliente = comboBoxClientes.getSelectionModel().getSelectedItem().getIdCliente();
            //Listas auxiliares
            ObservableList<AutomovilesEntity> dataAutosEncontrados = FXCollections.observableArrayList();
            ArrayList<AutomovilesEntity> listaAutosEncontrados = new ArrayList<>();
            //Buscar dentro de la BD los autos que coincidan con el ID del Cliente
            dataAutos.forEach(automovilesEntity -> {
                if(automovilesEntity.getIdClientes().equals(idCliente)) {
                    listaAutosEncontrados.add(automovilesEntity);
                }
            });
            //Agregar los autos encontrados al comboBox de Autos
            dataAutosEncontrados.addAll(listaAutosEncontrados);
            comboBoxAutos.setItems(dataAutosEncontrados);
        });
    }

    public void agregarRefaccionActionEvent() {
        ViewOpener viewOpener = new ViewOpener();
        viewOpener.openRefaccionPicker("vista/SeleccionRefacciones.fxml","Taller Mecanico Lara",this,"1");
    }

    public void setSelectedRefaccion(RefaccionEntity refaccionEntity) {
        dataRefaccionesParaTabla.add(refaccionEntity);
        tablaRefacciones.setItems(dataRefaccionesParaTabla);
    }

    public void cancelarActionEvent() {
        int nextID = ControladorServicio.getLastID() + 1;
        textfieldServicio.setText(String.valueOf(nextID));
        textfieldDescripcion.setText("");
        textfieldIDempleado.setText("");
        textfieldPrecio.setText("");
        tablaRefacciones.getItems().clear();
    }

    public void registrarServicioOnActionEvent() {
        final int[] cont = {0};
        ControladorServicio.guardarServicio(
                ControladorServicio.crearServicio(
                        Integer.parseInt(textfieldServicio.getText()),
                        comboboxServicio.getSelectionModel().getSelectedItem() + " " + textfieldDescripcion.getText(),
                        BigDecimal.valueOf(Double.parseDouble(textfieldPrecio.getText())),
                        Integer.parseInt(textfieldIDempleado.getText())));
        dataRefaccionesParaTabla.forEach(refaccionEntity -> {
            ControladorServicioRefaccion.guardarServicioRefaccion(
                    ControladorServicioRefaccion.crearServicioRefaccion(
                            Integer.parseInt(textfieldServicio.getText())* 100 + cont[0],
                            Integer.parseInt(textfieldServicio.getText()),
                            refaccionEntity.getIdRefaccion(),
                            refaccionEntity.getCantidad()
                    )
            );
            cont[0]++;
        });
        ControladorServicioAutomovil.guardarServicioAutomovil(
                ControladorServicioAutomovil.crearServicioAutomovil(
                        Integer.parseInt(textfieldServicio.getText())* 100,
                        Integer.parseInt(textfieldServicio.getText()),
                        comboBoxAutos.getSelectionModel().getSelectedItem().getMatricula()
                )
        );
        Alert alert = getWarningAlert("Exitoso","Atencion","Servicio guardado Exitosamente!");
        alert.showAndWait();
        cancelarActionEvent();
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) labelDescripcion.getScene().getWindow();
        stage.close();
    }

    public void eliminarRefaccionActionEvent() {
        RefaccionEntity refaccionEntity = tablaRefacciones.getSelectionModel().getSelectedItem();
        dataRefaccionesParaTabla.remove(refaccionEntity);
        tablaRefacciones.setItems(dataRefaccionesParaTabla);
    }
}
