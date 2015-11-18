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
        textfieldIDArticulo.setEditable(false);
        int nextID = ControladorRefaccion.getLastID() + 1;
        textfieldIDArticulo.setText(String.valueOf(nextID));
    }
    public void crearRefaccionEvent(){
        if (verificarDatosRegistro(textfieldDescripcion.getText(),textfieldPrecio.getText(),textfieldCantidad.getText())) {
            ControladorRefaccion.guardarRefaccion(ControladorRefaccion.crearRefaccion(
                    Integer.parseInt(textfieldIDArticulo.getText()), textfieldDescripcion.getText(),
                    BigDecimal.valueOf(Double.valueOf(textfieldPrecio.getText())),
                    Integer.parseInt(textfieldCantidad.getText())));
            Alert alert = getWarningAlert("Exitoso", "Atencion", "Refaccion agregada exitosamente!");
            alert.showAndWait();
            cancelarActionEvent();
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Verifique que los datos sean correctos!");
            alert.showAndWait();
        }
    }

    public boolean verificarDatosRegistro(String descripcion, String precio, String cantidad){
        boolean isDecimal = InputValidator.textIsDecimalOnly(precio,"2");
        boolean isNumeric = InputValidator.textIsNumericOnly(cantidad);
        if ((descripcion.length() != 0) && (precio.length() != 0)
                && isDecimal && (cantidad.length() != 0) && isNumeric) {
            return true;
        }else {
            return false;
        }
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public void cancelarActionEvent(){
        int nextID = ControladorRefaccion.getLastID() + 1;
        textfieldIDArticulo.setText(String.valueOf(nextID));
        textfieldDescripcion.setText("");
        textfieldPrecio.setText("");
        textfieldCantidad.setText("");
    }


}
