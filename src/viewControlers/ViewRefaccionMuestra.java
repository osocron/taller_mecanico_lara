package viewControlers;

import entidades.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRefaccionMuestra implements Initializable {
    @FXML
    public Button buttonAtras;
    @FXML
    public Button buttonBuscar;
    @FXML
    public Label labelRefaccion;
    @FXML
    public TextField textfieldRefaccion;
    @FXML
    public TableView tableviewRefaccion;
    @FXML
    public TableColumn idRefaccion;
    @FXML
    public TableColumn articulo;
    @FXML
    public TableColumn descripcion;
    @FXML
    public TableColumn cantidad;
    @FXML
    public TableColumn idProvedor;
    @FXML
    public TableColumn precio;


    private ObservableList<RefaccionEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        data.addAll(listaRefaccion);
    }
    public void guardarRefaccionEvent(){
        BigDecimal preci = new BigDecimal(precio.getText());
        ControladorRefaccion.guardarRefaccion(ControladorRefaccion.crearRefaccion(idRefaccion.hashCode(),articulo.getText(),preci ,cantidad.hashCode()
        ));
        idRefaccion.setText("");
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("");
        idProvedor.setText("");
        precio.setText("");
    }
    public void eliminarRefaccionEvent(){
        ControladorRefaccion.eliminarRefaccion(Integer.parseInt(textfieldRefaccion.getText()));
    }
    public void modificarRefaccionEvent(){
        int idRefa = Integer.parseInt(idRefaccion.getId());
        BigDecimal monedas = new BigDecimal(precio.getText());
        int canti = Integer.parseInt(cantidad.getId());
        ControladorRefaccion.modificarRefaccion(idRefa,descripcion.getText(),monedas,canti);
        descripcion.setText("");
        cantidad.setText("");
        precio.setText("");
    }
    public void buscarRefaccionEvent(){

    }

    public void cerrarVentanaEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) labelRefaccion.getScene().getWindow();
        stage.close();
    }
    public void cancelarActionEvent(){
        textfieldRefaccion.setText("");
    }
}
