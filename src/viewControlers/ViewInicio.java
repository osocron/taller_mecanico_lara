package viewControlers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by NoeAlejandro on 10/28/2015.
 */
public class ViewInicio implements Initializable {

    @FXML
    public Button registroButton,ventaButton,cotizacionButton,controlButton;
    public BorderPane rootBorderPane;
    public ImageView imageView;

    public Button registrarAutomovil,registrarCliente,registrarEmpleado,registrarProveedor,
            registrarRefaccion,registrarServicio,registrarVenta;
    public VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeElements();
    }


    public void displayRegistroOptions() {
        rootBorderPane.getChildren().remove(imageView);
        rootBorderPane.setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(registrarAutomovil,registrarCliente,registrarEmpleado,registrarProveedor,
                registrarRefaccion,registrarServicio,registrarVenta);
    }

    public void displayVentasOptions() {

    }

    public void displayCotizacionesOptions() {

    }


    public void displayControlOptions() {

    }

    private void initializeElements(){
        vBox = new VBox(20);
        registrarAutomovil = new Button("Registrar Automovil");
        registrarCliente = new Button("Registrar Clientes");
        registrarEmpleado = new Button("Registrar Empleados");
        registrarProveedor = new Button("Registrar Proveedores");
        registrarRefaccion = new Button("Registrar Refacciones");
        registrarServicio = new Button("Registrar Servicios");
        registrarVenta = new Button("Registrar Ventas");
    }

}
