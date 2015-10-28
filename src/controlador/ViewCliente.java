package controlador;

import entidades.ClienteAutomovilEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.awt.*;
import java.awt.Label;
import java.net.URL;
import java.util.*;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewCliente implements Initializable{
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

    private ObservableList<ClienteAutomovilEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<ClienteAutomovilEntity> listaCliente = ControladorClienteAutomovil.getAutoCliente();
        data.addAll(listaCliente);
    }
}
