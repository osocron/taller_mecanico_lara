package viewControlers;

import entidades.AutomovilesEntity;
import entityControlers.ControladorAutomovil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
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
    public CheckBox checkboxAutomovil;
    @FXML
    public CheckBox checkboxCoche;
    @FXML
    public CheckBox checkboxAuto;
    @FXML
    public Label labelMatricula;
    @FXML
    public Label labelModelo;
    @FXML
    public Label labelMarca;
    @FXML
    public Label labelIDcliente;
    @FXML
    public TextField textfieldMatricula;
    @FXML
    public TextField textfieldModelo;
    @FXML
    public TextField textfieldMarca;
    @FXML
    public TextField textfieldIDcliente;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;

    private ObservableList<AutomovilesEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<AutomovilesEntity> listaAutomoviles = ControladorAutomovil.getAutomoviles();
        data.addAll(listaAutomoviles);
    }
}
