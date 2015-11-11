package viewControlers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by NoeAlejandro on 10/28/2015.
 */
public class ViewInicio implements Initializable {

    @FXML
    public JFXButton registroButton,ventaButton,controlButton;
    public BorderPane rootBorderPane;
    public ImageView imageView;

    private JFXButton registrarAutomovil,registrarCliente,registrarEmpleado,registrarProveedor,
            registrarRefaccion,registrarServicio,registrarVenta,consultarCliente,consultarEmpleado,
            consultarRefacion,consultarServicio,consultarVentas,reporte;
    private VBox vBox;
    private ArrayList<Button> buttonList;
    private ViewOpener viewOpener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeElements();
        viewOpener = new ViewOpener();
    }


    public void displayRegistroOptions() {
        removeElements();
        try{
            vBox.getChildren().addAll(registrarAutomovil,registrarCliente,registrarEmpleado,registrarProveedor,
                    registrarRefaccion,registrarServicio);
        }catch (Exception ignored){}
    }

    public void displayVentasOptions() {
        removeElements();
        try{
            vBox.getChildren().addAll(registrarVenta,consultarVentas,reporte);
        }catch (Exception ignored){}
    }


    public void displayControlOptions() {
        removeElements();
        try{
            vBox.getChildren().addAll(consultarEmpleado,consultarCliente,
                    consultarVentas,consultarServicio,consultarRefacion);
        }catch (Exception ignored){}
    }

    private void initializeElements(){
        vBox = new VBox(20);
        registrarAutomovil = new JFXButton("Registrar Automovil");
        registrarCliente = new JFXButton("Registrar Clientes");
        registrarEmpleado = new JFXButton("Registrar Empleados");
        registrarProveedor = new JFXButton("Registrar Proveedores");
        registrarRefaccion = new JFXButton("Registrar Refacciones");
        registrarServicio = new JFXButton("Registrar Servicios");
        registrarVenta = new JFXButton("Registrar Ventas");
        consultarCliente = new JFXButton("Consultar Clientes");
        consultarEmpleado = new JFXButton("Consultar Empleados");
        consultarRefacion = new JFXButton("Consultar Refaccion");
        consultarServicio = new JFXButton("Consultar Servicios");
        consultarVentas  = new JFXButton("Consultar Ventas");
        reporte = new JFXButton("Reporte de Ventas");
        buttonList = new ArrayList<>();
        buttonList.add(registrarAutomovil);
        buttonList.add(registrarCliente);
        buttonList.add(registrarEmpleado);
        buttonList.add(registrarProveedor);
        buttonList.add(registrarRefaccion);
        buttonList.add(registrarServicio);
        buttonList.add(registrarVenta);
        buttonList.add(consultarCliente);
        buttonList.add(consultarEmpleado);
        buttonList.add(consultarRefacion);
        buttonList.add(consultarServicio);
        buttonList.add(consultarVentas);
        buttonList.add(reporte);
        buttonList.forEach(button -> {
            button.setPrefWidth(175);
            button.setStyle("-fx-background-color: lightgray");
        });
        setActionEvents();
    }

    private void setActionEvents() {
        registrarAutomovil.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarAutomovil.fxml", "Taller Mecánico Lara");
        });
        registrarCliente.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarCliente.fxml", "Taller Mecánico Lara");
        });
        registrarEmpleado.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarEmpleado.fxml", "Taller Mecánico Lara");
        });
        registrarProveedor.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarProveedor.fxml", "Taller Mecánico Lara");
        });
        registrarRefaccion.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarRefaccion.fxml", "Taller Mecánico Lara");
        });
        registrarServicio.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarServicios.fxml", "Taller Mecánico Lara");
        });
        registrarVenta.setOnAction(event -> {
            viewOpener.openView("vista/RegistrarVenta.fxml", "Taller Mecánico Lara");
        });
        consultarCliente.setOnAction(event -> {
            viewOpener.openView("vista/ConsultarCliente.fxml", "Taller Mecánico Lara");
        });
        consultarEmpleado.setOnAction(event -> {
            viewOpener.openView("vista/ConsultarEmpleado.fxml", "Taller Mecánico Lara");
        });
        consultarRefacion.setOnAction(event -> {
            viewOpener.openView("vista/ConsultarRefaccion.fxml", "Taller Mecánico Lara");
        });
        consultarServicio.setOnAction(event -> {
            viewOpener.openView("vista/ConsultarServicio.fxml", "Taller Mecánico Lara");
        });
        consultarVentas.setOnAction(event -> {
            viewOpener.openView("vista/ConsultarVentas.fxml", "Taller Mecánico Lara");
        });
        reporte.setOnAction(event -> {
            viewOpener.openView("vista/Reporte.fxml", "Taller Mecánico Lara");
        });
    }

    private void removeElements(){
        try{
            rootBorderPane.getChildren().remove(imageView);
        }catch (Exception ignored){}
        buttonList.forEach(boton -> {
            try {
                vBox.getChildren().remove(boton);
            }catch (Exception ignored){}
        });
        try{
            rootBorderPane.setCenter(vBox);
            vBox.setAlignment(Pos.CENTER);
        }catch (Exception ignored){}
    }



}
