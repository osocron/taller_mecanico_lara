package servicio;

import com.jfoenix.controls.JFXButton;
import entidades.AutomovilesEntity;
import entidades.ClienteEntity;
import entidades.ServicioAutomovilEntity;
import entidades.ServicioEntity;
import automovil.ControladorAutomovil;
import cliente.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import venta.ViewRegistrarVenta;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ViewSeleccionarServicio implements Initializable{

    public ListView<ServicioEntity> servicioListView;
    public JFXButton cancelarButton;
    public JFXButton seleccionarServicioButton;
    public Label lableCliente;
    public ComboBox<AutomovilesEntity> comboBoxAutomovil;

    private String idCliente;
    private ViewRegistrarVenta parent;
    private ObservableList<ServicioEntity> dataServicios = FXCollections.observableArrayList();
    private ObservableList<ServicioAutomovilEntity> dataServicioAutomovil = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAutos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<ServicioEntity> servicioEntityList = ControladorServicio.getServicios();
        dataServicios.addAll(servicioEntityList);
        List<ServicioAutomovilEntity> servicioAutomovilEntityList = ControladorServicioAutomovil.getServicioAuto();
        dataServicioAutomovil.addAll(servicioAutomovilEntityList);
        List<AutomovilesEntity> automovilesEntityList = ControladorAutomovil.getAutomoviles();
        dataAutos.addAll(automovilesEntityList);
    }

    public void cancelarActionEvent() {
        Stage stage = (Stage) servicioListView.getScene().getWindow();
        stage.close();
    }

    public void seleccionarServicioActionEvent() {
        ServicioEntity servicioEntity = servicioListView.getSelectionModel().getSelectedItem();
        parent.setSelectedServicio(servicioEntity);
        cancelarActionEvent();
    }

    public void setParent(ViewRegistrarVenta parent, String idCliente) {
        this.parent = parent;
        this.idCliente = idCliente;
        asignarAutos();
        ligarClienteAutomovilComboBox();
        ClienteEntity clienteEntity = ControladorCliente.getClienteID(idCliente);
        lableCliente.setText(lableCliente.getText()+" "+clienteEntity.getNombre());
    }

    private void asignarAutos(){
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
        comboBoxAutomovil.setItems(dataAutosEncontrados);
    }

    private void ligarClienteAutomovilComboBox(){
        comboBoxAutomovil.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ArrayList<ServicioAutomovilEntity> servicioAutomovilEncontrados = new ArrayList<>();
            ArrayList<ServicioEntity> serviciosEncontrados = new ArrayList<>();

            dataServicioAutomovil.forEach(servicioAutomovilEntity -> {
                if (servicioAutomovilEntity.getMatricula().equals(
                        comboBoxAutomovil.getSelectionModel().getSelectedItem().getMatricula())) {
                    servicioAutomovilEncontrados.add(servicioAutomovilEntity);
                }
            });

            servicioAutomovilEncontrados.forEach(servicioAutomovilEntity -> {
                dataServicios.forEach(servicioEntity -> {
                    if (servicioAutomovilEntity.getIdServicios() == servicioEntity.getIdServicio()) {
                        serviciosEncontrados.add(servicioEntity);
                    }
                });
            });

            ObservableList<ServicioEntity> dataServiciosEncontrados = FXCollections.observableArrayList();
            dataServiciosEncontrados.addAll(serviciosEncontrados);
            servicioListView.setItems(dataServiciosEncontrados);
        });
    }
}
