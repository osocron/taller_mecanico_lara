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

    /**
     * Método que se encarga que se encarga de abrir la ventana de Productos con un objeto de tipo Producto
     * y con una instancia de la clase controladora que la mandó a llamar.
     * */
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



}
