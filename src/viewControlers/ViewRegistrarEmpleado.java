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

    private ObservableList<EmpleadoEntity> dataEmpleado = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.List<EmpleadoEntity> listaEmpleado = ControladorEmpleado.getEmpleados();
        dataEmpleado.addAll(listaEmpleado);
        textfieldUsuario.setDisable(true);
        textfieldContrasena.setDisable(true);
        checkBoxUsuario.setOnAction(event -> {
            if (checkBoxUsuario.isSelected()){
                textfieldUsuario.setDisable(false);
                textfieldContrasena.setDisable(false);
            }else {
                textfieldUsuario.setDisable(true);
                textfieldContrasena.setDisable(true);
            }
        });
        textfieldIDempleado.setEditable(false);
        int nextID = ControladorEmpleado.getNextID() + 1;
        textfieldIDempleado.setText(String.valueOf(nextID));
    }


    public void registrarEmpleadoActionEvent() {
        boolean isOK = true;
        if ((textfieldIDempleado.getLength() != 0) && (textfieldNombre.getLength() != 0) &&
                (textfieldPuesto.getLength() != 0)) {
            ControladorEmpleado.guardarEmpleadp(
                    ControladorEmpleado.crearEmpleado(
                            Integer.parseInt(textfieldIDempleado.getText()),
                            textfieldNombre.getText(), textfieldPuesto.getText()));
        }else {
            isOK = false;
        }
        if(checkBoxUsuario.isSelected()){
            if ((textfieldUsuario.getLength() != 0) && (textfieldContrasena.getLength() != 0)) {
                ControladorUsuario.guardarUsuario(
                        ControladorUsuario.crearUsuario(
                                textfieldUsuario.getText(), textfieldContrasena.getText(),
                                Integer.parseInt(textfieldIDempleado.getText())));
            }else {
                isOK = false;
            }
        }
        if (isOK) {
            Alert alert = getWarningAlert("Exitoso", "Atencion", "Empleado registrado exitosamente!");
            alert.showAndWait();
            int nextID = ControladorEmpleado.getNextID() + 1;
            textfieldIDempleado.setText(String.valueOf(nextID));
            textfieldNombre.setText("");
            textfieldPuesto.setText("");
            textfieldUsuario.setText("");
            textfieldContrasena.setText("");
        }else {
            Alert alert = getWarningAlert("Cuidado", "Atencion", "Favor de Ingresar los datos faltantes.");
            alert.showAndWait();
        }
    }

    public boolean validarDatosRegistro(String id, String nombre, String puesto){
        if ((id.length() != 0) && (nombre.length() != 0) &&
                (puesto.length() != 0)){
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

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) labelNombre.getScene().getWindow();
        stage.close();
    }

    public void cancelarActionEvent() {
        cerrarVentanaEvent();
    }

}