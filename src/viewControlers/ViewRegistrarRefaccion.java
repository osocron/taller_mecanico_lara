package viewControlers;

import entidades.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarRefaccion implements Initializable {
    @FXML
    public Label labelArticulo;
    @FXML
    public Label labelDescripcion;
    @FXML
    public Label labelCantidad;
    @FXML
    public Label labelPrecio;
    @FXML
    public Label labelIDprovedor;
    @FXML
    public Label labelMoneda;
    @FXML
    public TextField textfieldArticulo;
    @FXML
    public TextField textfieldDescripcion;
    @FXML
    public TextField textfieldPrecio;
    @FXML
    public ComboBox comboboxIDprovedor;
    @FXML
    public ComboBox comboboxMoneda;
    @FXML
    public Button buttonAceptar;
    @FXML
    public Button buttonCancelar;

    private ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(listaRefaccion);
    }
    public void crearRefaccionEvent(){
        /*ControladorRefaccion.crearRefaccion(textfieldArticulo.getLength(),textfieldDescripcion.getText(),
                textfieldPrecio.getLength(),comboboxIDprovedor.getVisibleRowCount(),comboboxMoneda.getVisibleRowCount()
                );*/
        textfieldArticulo.setText("");
        textfieldDescripcion.setText("");
        textfieldPrecio.setText("");
        //comboboxIDprovedor.setOnAction();
        //comboboxMoneda.setOnAction();
    }
}
