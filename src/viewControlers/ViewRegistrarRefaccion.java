package viewControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
    public JFXTextField textfieldIDArticulo;
    @FXML
    public JFXTextField textfieldDescripcion;
    @FXML
    public JFXTextField textfieldPrecio, textfieldCantidad;
    @FXML
    public JFXButton buttonAceptar;
    @FXML
    public JFXButton buttonCancelar;


    private ObservableList<RefaccionEntity> dataRefaccion = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        dataRefaccion.addAll(listaRefaccion);
    }
    public void crearRefaccionEvent(){
        ControladorRefaccion.guardarRefaccion(ControladorRefaccion.crearRefaccion(
                Integer.parseInt(textfieldIDArticulo.getText()),textfieldDescripcion.getText(),
                BigDecimal.valueOf(Double.valueOf(textfieldPrecio.getText())),
                Integer.parseInt(textfieldCantidad.getText())));
        Alert alert = getWarningAlert("Exitoso","Atencion","Refaccion agregada exitosamente!");
        alert.showAndWait();
        cancelarActionEvent();
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public void cancelarActionEvent(){
        textfieldIDArticulo.setText("");
        textfieldDescripcion.setText("");
        textfieldPrecio.setText("");
        textfieldCantidad.setText("");
    }


}
