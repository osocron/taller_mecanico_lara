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
    public TableColumn moneda;


    private ObservableList<RefaccionEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        data.addAll(listaRefaccion);
    }
    public void guardarRefaccionEvent(){
        /*ControladorRefaccion.guardarRefaccion(ControladorRefaccion.guardarRefaccion(idRefaccion.getTableView(),
                articulo.getTableView(),descripcion.getTableView(),cantidad.getTableView(),idProvedor.getTableView(),
                moneda.getTableView()
        ));*/
        idRefaccion.setText("");
        articulo.setText("");
        descripcion.setText("");
        cantidad.setText("");
        idProvedor.setText("");
        moneda.setText("");
    }
    public void eliminarRefaccionEvent(){

    }
    public void modificarRefaccionEvent(){
        /*ControladorRefaccion.modificarRefaccion(ControladorRefaccion.modificarRefaccion(descripcion.getTableView(),
                cantidad.getTableView(),moneda.getTableView()));*/
        descripcion.setText("");
        cantidad.setText("");
        moneda.setText("");
    }
    public void buscarRefaccionEvent(){

    }
}
