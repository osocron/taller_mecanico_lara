package viewControlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
            stage.setScene(new Scene(root, 600, 400));
            //ProductViewController controller = loader.<ProductViewController>getController();
            //controller.setResourceObject(resourceObject, inventarioProductosController);
            stage.show();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    public void openRefaccionPicker(String pathToFXML, String title, ViewRegistrarServicio parent){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 500, 400));
        ViewSeleccionarRefaccion controller = loader.<ViewSeleccionarRefaccion>getController();
        controller.setParent(parent);
        stage.show();
    }


}
