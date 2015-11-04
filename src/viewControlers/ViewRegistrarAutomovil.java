package viewControlers;

import entities.AutomovilesEntity;
import entities.ClienteEntity;
import entityControlers.ControladorAutomovil;
import entityControlers.ControladorCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewRegistrarAutomovil implements Initializable {

    @FXML
    public TextField matriculaTextField,marcaTextField,modeloTextField,colorTextField;
    public ComboBox<ClienteEntity> clienteEntityComboBox;
    public Button registrarButton;

    private ObservableList<ClienteEntity> dataCliente = FXCollections.observableArrayList();
    private ObservableList<AutomovilesEntity> dataAtuomoviles = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataCliente.addAll(ControladorCliente.getCliente());
        dataAtuomoviles.addAll(ControladorAutomovil.getAutomoviles());
        clienteEntityComboBox.getItems().addAll(dataCliente);
    }

}
