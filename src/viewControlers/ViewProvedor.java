package viewControlers;

import entities.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewProvedor implements Initializable {
    @FXML
    public Label labelProvedor;
    @FXML
    public Label labelRefaccion;
    @FXML
    public Label labelCosto;
    @FXML
    public Label labelDireccion;
    @FXML
    public Label labelTelefono;
    @FXML
    public TextField textfieldProvedor;
    @FXML
    public TextField textfieldRefaccion;
    @FXML
    public TextField textfieldCosto;
    @FXML
    public TextField textfieldDireccion;
    @FXML
    public TextField textfieldTelefono;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;


    private ObservableList<RefaccionEntity> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        data.addAll(listaRefaccion);
    }
}
