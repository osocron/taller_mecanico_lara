package viewControlers;

import entidades.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osocron on 5/11/15.
 */
public class ViewSeleccionarRefaccion implements Initializable{

    public ListView<RefaccionEntity> refaccionesListView;
    public Button cancelarButton;
    public Button sleccionarButton;

    private ViewRegistrarServicio parent;
    private ObservableList<RefaccionEntity> dataRefacciones = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<RefaccionEntity> refaccionEntityList = ControladorRefaccion.getRefacciones();
        dataRefacciones.addAll(refaccionEntityList);
        refaccionesListView.setItems(dataRefacciones);
    }


    public void cancelarActionEvent() {
        Stage stage = (Stage)refaccionesListView.getScene().getWindow();
        stage.close();
    }

    public void seleccionarRefaccionActionEvent() {
        RefaccionEntity refaccionEntity = refaccionesListView.getSelectionModel().getSelectedItem();
        parent.setSelectedRefaccion(refaccionEntity);
        Stage stage = (Stage)refaccionesListView.getScene().getWindow();
        stage.close();
    }

    public void setParent(ViewRegistrarServicio parent){
        this.parent = parent;
    }

}