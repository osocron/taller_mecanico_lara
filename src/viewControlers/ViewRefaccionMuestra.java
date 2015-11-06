package viewControlers;

import entities.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        data.addAll(listaRefaccion);
    }
    public void guardarRefaccionEvent(){
        //ControladorRefaccion.guardarRefaccion(ControladorRefaccion.crearRefaccion(idRefaccion.hashCode(),articulo.getText(),precio.hashCode(),cantidad.hashCode()
        //));
        idRefaccion.setText("");
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("");
        idProvedor.setText("");
        precio.setText("");
    }
    public void eliminarRefaccionEvent(){
        ControladorRefaccion.eliminarRefaccion(textfieldRefaccion.getText());
    }
    public void modificarRefaccionEvent(){
        /*ControladorRefaccion.modificarRefaccion(ControladorRefaccion.modificarRefaccion(descripcion.getTableView(),
                cantidad.getTableView(),moneda.getTableView()));*/
        descripcion.setText("");
        cantidad.setText("");
        precio.setText("");
    }
    public void buscarRefaccionEvent(){

    }
}
