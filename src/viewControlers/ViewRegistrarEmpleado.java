package viewControlers;

import entities.EmpleadoEntity;
import entityControlers.ControladorEmpleado;
import entityControlers.ControladorUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewRegistrarEmpleado implements Initializable {
    @FXML
    public Label labelIDempleado;
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelPuesto;
    @FXML
    public Label labelUsuario;
    @FXML
    public Label labelContrasena;
    @FXML
    public TextField textfieldIDempleado;
    @FXML
    public TextField textfieldNombre;
    @FXML
    public TextField textfieldPuesto;
    @FXML
    public TextField textfieldUsuario;
    @FXML
    public TextField textfieldContrasena;
    @FXML
    public CheckBox checkBoxUsuario;
    @FXML
    public Button buttonEmpleado;

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
        /*Alert alert = getWarningAlert("Exitoso","Atencion","Cliente gregistrado exitosamente!");
        alert.showAndWait();*/
        textfieldIDempleado.setText("");
        textfieldNombre.setText("");
        textfieldPuesto.setText("");
        textfieldUsuario.setText("");
        textfieldContrasena.setText("");
    }

    /*private Alert getWarningAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert;
    }*/

}