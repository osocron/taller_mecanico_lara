package viewControlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import refaccion.ViewSeleccionarRefaccion;
import servicio.ViewSeleccionarServicio;
import venta.ViewRegistrarVenta;

import java.io.IOException;

/**
 * Created by osocron on 26/10/15.
 */
public class ViewOpener {

    public ViewOpener(){}

    public int openView(String pathToFXML, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public void openRefaccionPicker(String pathToFXML, String title, Object parent, String id){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        ViewSeleccionarRefaccion controller = loader.<ViewSeleccionarRefaccion>getController();
        controller.setParent(parent,id);
        stage.show();
    }

    public void openServicioPicker(String pathToFXML, String title, ViewRegistrarVenta parent, String idCliente){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        Parent root = null;
        try {
            root = loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600,400));
        ViewSeleccionarServicio controller = loader.<ViewSeleccionarServicio>getController();
        controller.setParent(parent,idCliente);
        stage.show();
    }


}
