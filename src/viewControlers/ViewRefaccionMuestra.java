package viewControlers;

import entidades.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;


import java.awt.*;
import java.awt.Button;
import java.awt.Label;
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


    private ObservableList<RefaccionEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        data.addAll(listaRefaccion);
    }
}
