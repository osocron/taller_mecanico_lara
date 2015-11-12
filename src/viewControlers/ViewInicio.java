package viewControlers;

import com.jfoenix.controls.JFXButton;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private JFXButton registrarAutomovil,registrarCliente,registrarEmpleado,
            registrarRefaccion,registrarServicio,registrarVenta,consultarCliente,consultarEmpleado,
            consultarRefacion,consultarServicio,consultarVentas,consultarAutomovil,reporte;
    private VBox vBox;
    private ArrayList<JFXButton> buttonList;
    private ViewOpener viewOpener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeElements();
        viewOpener = new ViewOpener();
    }


    public void displayRegistroOptions() {
        removeElements();
        try{
            vBox.getChildren().addAll(registrarAutomovil,registrarCliente,registrarEmpleado,
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
            vBox.getChildren().addAll(consultarEmpleado,consultarCliente,consultarAutomovil,
                    consultarVentas,consultarServicio,consultarRefacion);
        }catch (Exception ignored){}
    }

    private void initializeElements(){
        vBox = new VBox(20);
        registrarAutomovil = new JFXButton("REGISTRAR AUTOMOVIL");
        registrarCliente = new JFXButton("REGISTRAR CLIENTE");
        registrarEmpleado = new JFXButton("REGISTRAR EMPLEADO");
        registrarRefaccion = new JFXButton("REGISTRAR REFACCION");
        registrarServicio = new JFXButton("REGISTRAR SERVICIO");
        registrarVenta = new JFXButton("REGISTRAR VENTA");
        consultarCliente = new JFXButton("CONSULTAR CLIENTES");
        consultarAutomovil = new JFXButton("CONSULTAR AUTOMOVIL");
        consultarEmpleado = new JFXButton("CONSULTAR EMPLEADOS");
        consultarRefacion = new JFXButton("CONSULTAR REFACCION");
        consultarServicio = new JFXButton("CONSULTAR SERVICIOS");
        consultarVentas  = new JFXButton("CONSULTAR VENTAS");
        reporte = new JFXButton("REPORTE DE VENTAS");
        buttonList = new ArrayList<>();
        buttonList.add(registrarAutomovil);
        buttonList.add(registrarCliente);
        buttonList.add(registrarEmpleado);
        buttonList.add(registrarRefaccion);
        buttonList.add(registrarServicio);
        buttonList.add(registrarVenta);
        buttonList.add(consultarCliente);
        buttonList.add(consultarAutomovil);
        buttonList.add(consultarEmpleado);
        buttonList.add(consultarRefacion);
        buttonList.add(consultarServicio);
        buttonList.add(consultarVentas);
        buttonList.add(reporte);
        buttonList.forEach(button -> {
            button.setPrefWidth(200);
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setRipplerFill(Color.rgb(132,189,0));
            button.setTextFill(Color.rgb(184,182,182));
        });
        registroButton.setRipplerFill(Color.rgb(132, 189, 0));
        ventaButton.setRipplerFill(Color.rgb(132, 189, 0));
        controlButton.setRipplerFill(Color.rgb(132,189,0));
        setActionEvents();
    }

    private void setActionEvents() {
        registrarAutomovil.setOnAction(event -> viewOpener.openView("vista/RegistrarAutomovil.fxml", "Taller Mecánico Lara"));
        registrarCliente.setOnAction(event -> viewOpener.openView("vista/RegistrarCliente.fxml", "Taller Mecánico Lara"));
        registrarEmpleado.setOnAction(event -> viewOpener.openView("vista/RegistrarEmpleado.fxml", "Taller Mecánico Lara"));
        registrarRefaccion.setOnAction(event -> viewOpener.openView("vista/RegistrarRefaccion.fxml", "Taller Mecánico Lara"));
        registrarServicio.setOnAction(event -> viewOpener.openView("vista/RegistrarServicios.fxml", "Taller Mecánico Lara"));
        registrarVenta.setOnAction(event -> viewOpener.openView("vista/RegistrarVenta.fxml", "Taller Mecánico Lara"));
        consultarCliente.setOnAction(event -> viewOpener.openView("vista/ConsultarCliente.fxml", "Taller Mecánico Lara"));
        consultarAutomovil.setOnAction(event -> viewOpener.openView("vista/ConsultarAutomovil.fxml","Taller Mecánico Lara"));
        consultarEmpleado.setOnAction(event -> viewOpener.openView("vista/ConsultarEmpleado.fxml", "Taller Mecánico Lara"));
        consultarRefacion.setOnAction(event -> viewOpener.openView("vista/ConsultarRefaccion.fxml", "Taller Mecánico Lara"));
        consultarServicio.setOnAction(event -> viewOpener.openView("vista/ConsultarServicio.fxml", "Taller Mecánico Lara"));
        consultarVentas.setOnAction(event -> viewOpener.openView("vista/ConsultarVentas.fxml", "Taller Mecánico Lara"));
        reporte.setOnAction(event -> viewOpener.openView("vista/Reporte.fxml", "Taller Mecánico Lara"));
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
