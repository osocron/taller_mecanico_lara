package controlador;

import entidades.RefaccionEntity;
import entidades.ServicioEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarServicio implements Initializable {
    @FXML
    private Button button;
    @FXML
    private BorderPane bordePane;
    @FXML
    private HBox hbox;
    @FXML
    private Checkbox checkbox;
    @FXML
    private Label label;
    @FXML
    private javafx.scene.control.TextField textField;
    @FXML
    private VBox vBox;

    private ObservableList<ServicioEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ServicioEntity> listaServicio = ControladorServicio.getServicios();
        data.addAll(listaServicio);
    }
}