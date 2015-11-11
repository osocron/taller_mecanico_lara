package viewControlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import entidades.EmpleadoEntity;
import entityControlers.ControladorEmpleado;
import entityControlers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarEmpleado implements Initializable {

    public Label labelIDempleado;
    public Label labelNombre;
    public Label labelPuesto;
    public Label labelUsuario;
    public Label labelContrasena;
    public JFXTextField textfieldIDempleado;
    public JFXTextField textfieldNombre;
    public JFXTextField textfieldPuesto;
    public JFXTextField textfieldUsuario;
    public JFXTextField textfieldContrasena;
    public JFXCheckBox checkBoxUsuario;
    public JFXButton buttonEmpleado;
    public MenuItem menuItemClose;

    private ObservableList<EmpleadoEntity> dataEmpleado = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<EmpleadoEntity> listaEmpleado = ControladorEmpleado.getEmpleados();
        dataEmpleado.addAll(listaEmpleado);
    }


    public void registrarEmpleadoActionEvent() {
        ControladorEmpleado.guardarEmpleadp(
                ControladorEmpleado.crearEmpleado(
                        Integer.parseInt(textfieldIDempleado.getText()),
                        textfieldNombre.getText(),textfieldPuesto.getText()));
        if(checkBoxUsuario.isSelected()){
            ControladorUsuario.guardarUsuario(
                    ControladorUsuario.crearUsuario(
                            textfieldUsuario.getText(),textfieldContrasena.getText(),
                            Integer.parseInt(textfieldIDempleado.getText())));
        }
        Alert alert = getWarningAlert("Exitoso","Atencion","Cliente registrado exitosamente!");
        alert.showAndWait();
        textfieldIDempleado.setText("");
        textfieldNombre.setText("");
        textfieldPuesto.setText("");
        textfieldUsuario.setText("");
        textfieldContrasena.setText("");
    }

    private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }

    public void cerrarVentanaEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) labelNombre.getScene().getWindow();
        stage.close();
    }

    public void cancelarActionEvent(ActionEvent actionEvent) {

    }
}