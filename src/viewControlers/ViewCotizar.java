package viewControlers;

import entidades.RefaccionEntity;
import entidades.ServicioEntity;
import entityControlers.ControladorRefaccion;
import entityControlers.ControladorServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewCotizar implements Initializable {

    public Label labelCliente;
    public Label labelRefaccion;
    public Label labelAutomovil;
    public Label labelServicio;
    public Label labelCosto;
    public TextField textfieldCliente;
    public TextField textfieldAutomovil;
    public TextField textfieldCosto;
    public ComboBox comboboxRefaccion;
    public ComboBox comboboxServicio;

    private ObservableList<ServicioEntity> dataServicio = FXCollections.observableArrayList();
    private ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        dataServicio.addAll(listaServicio);
        List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(listaRefaccion);
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) textfieldAutomovil.getScene().getWindow();
        stage.close();
    }

}
