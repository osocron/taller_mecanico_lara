package controlador;

import entidades.AutomovilesEntity;
import entidades.UsuarioEntity;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ViewAutomovil implements Initializable {
    @FXML
    private BorderPane bordePane;
    @FXML
    private HBox hbox;
    @FXML
    private Checkbox checkbox;
    @FXML
    private Label label;
    @FXML
    private TextField textField;
    @FXML
    private VBox vBox;

    private ObservableList<AutomovilesEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<AutomovilesEntity> listaAutomoviles = ControladorAutomovil.getAutomoviles();
        data.addAll(listaAutomoviles);
    }
}
