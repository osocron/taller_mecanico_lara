package viewControlers;

import entidades.AutomovilesEntity;
import entityControlers.ControladorAutomovil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 27/10/2015.
 */
public class ViewAutomovil implements Initializable {

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
    public void guardarAutomovilActionEvent() {
        /*ControladorAutomovil.crearAutomovil(ControladorAutomovil.crearAutomovil(textfieldMatricula.getText(),
                textfieldModelo.getText(), textfieldMarca.getText(),
                textfieldIDcliente.getText()));*/
        textfieldMatricula.setText("");
        textfieldModelo.setText("");
        textfieldMarca.setText("");
        textfieldIDcliente.setText("");
    }
    public void eliminarAutomovilEvent(){
        ControladorAutomovil.eliminarAutomovil(textfieldMarca.getText());
        textfieldMatricula.setText("");
    }
    public void modificarAutomovil(){
        /*ControladorAutomovil.modificarAutomovil(textfieldModelo.getText(),textfieldMarca.getText(),
                textfieldIDcliente.getText());*/
        textfieldMarca.setText("");
        textfieldModelo.setText("");
        textfieldIDcliente.setText("");
    }
    public void cerrarVentanaEvent(){
        Stage stage = (Stage) textfieldMatricula.getScene().getWindow();
        stage.close();
    }
    public void cancelarActionEvent(){

    }

    public void eliminarActionEvent(ActionEvent actionEvent) {

    }
}
