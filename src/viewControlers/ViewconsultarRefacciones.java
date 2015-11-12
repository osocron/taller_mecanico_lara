package viewControlers;

import com.jfoenix.controls.JFXButton;
import entidades.RefaccionEntity;
import entityControlers.ControladorRefaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ValdemarRamos on 28/10/2015.
 */
public class ViewconsultarRefacciones implements Initializable {


    public TableView<RefaccionEntity> tableviewRefaccion;
    public TableColumn<RefaccionEntity,String> idRefaccion;
    public TableColumn<RefaccionEntity,String> descripcion;
    public TableColumn<RefaccionEntity,Integer> cantidad;
    public TableColumn<RefaccionEntity,BigDecimal> costo;
    public MenuItem menuItemClose;
    public JFXButton buttonEliminar;


    private ObservableList<RefaccionEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<RefaccionEntity> listaRefaccion = ControladorRefaccion.getRefacciones();
        data.addAll(listaRefaccion);
        prepararTableView();
    }

    private void prepararTableView() {
        tableviewRefaccion.setEditable(true);
        idRefaccion.setCellValueFactory(new PropertyValueFactory<>("idRefaccion"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("marca"));
        descripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcion.setOnEditCommit(event -> ControladorRefaccion.modificarMarca(
                event.getTableView().getSelectionModel().getSelectedItem().getIdRefaccion(),
                event.getNewValue()
        ));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        cantidad.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return String.valueOf(object);
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        cantidad.setOnEditCommit(event -> ControladorRefaccion.modificarCantidad(
                event.getTableView().getSelectionModel().getSelectedItem().getIdRefaccion(),
                event.getNewValue()
        ));
        costo.setCellValueFactory(new PropertyValueFactory<>("precio"));
        costo.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<BigDecimal>() {
            @Override
            public String toString(BigDecimal object) {
                return String.valueOf(object);
            }

            @Override
            public BigDecimal fromString(String string) {
                return new BigDecimal(string);
            }
        }));
        costo.setOnEditCommit(event -> ControladorRefaccion.modificarPrecio(
                event.getTableView().getSelectionModel().getSelectedItem().getIdRefaccion(),
                event.getNewValue()
        ));
        tableviewRefaccion.setItems(data);
    }

    public void eliminarRefaccionEvent(){
        RefaccionEntity refaccionEntity = tableviewRefaccion.getSelectionModel().getSelectedItem();
        data.remove(refaccionEntity);
        tableviewRefaccion.setItems(data);
        ControladorRefaccion.eliminarRefaccion(refaccionEntity.getIdRefaccion());
    }

    public void cerrarVentanaEvent() {
        Stage stage = (Stage) tableviewRefaccion.getScene().getWindow();
        stage.close();
    }

}
