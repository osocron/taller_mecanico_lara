package refaccion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entidades.RefaccionEntity;
import refaccion.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import viewControlers.InputValidator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


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
        if (verificarDatosRegistro(textfieldDescripcion.getText(),textfieldPrecio.getText(),textfieldCantidad.getText(),dataRefaccion)) {
            ControladorRefaccion.guardarRefaccion(ControladorRefaccion.crearRefaccion(
                    Integer.parseInt(textfieldIDArticulo.getText()), textfieldDescripcion.getText(),
                    BigDecimal.valueOf(Double.valueOf(textfieldPrecio.getText())),
                    Integer.parseInt(textfieldCantidad.getText())));
            getWarningAlert("Exitoso", "Atencion", "Refaccion agregada exitosamente!");
            cancelarActionEvent();
        }else {
            getWarningAlert("Cuidado", "Atencion", "Verifique que los datos sean correctos!");
        }
    }

    public boolean verificarDatosRegistro(String descripcion, String precio, String cantidad, ObservableList<RefaccionEntity> dataRefaccion){
        boolean isDecimal = InputValidator.textIsDecimalOnly(precio, "2");
        boolean isNumeric = InputValidator.textIsNumericOnly(cantidad);
        return (descripcion.length() != 0) && (precio.length() != 0)
                && isDecimal && (cantidad.length() != 0) && isNumeric;
    }

    public void getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void cancelarActionEvent(){
        int nextID = ControladorRefaccion.getLastID() + 1;
        textfieldIDArticulo.setText(String.valueOf(nextID));
        textfieldDescripcion.setText("");
        textfieldPrecio.setText("");
        textfieldCantidad.setText("");
    }


}
