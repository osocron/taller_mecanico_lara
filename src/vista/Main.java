package vista;

import entityControlers.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Taller mecánico");
        primaryStage.setScene(new Scene(root,700,500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ConexionBD.conectar();
        launch(args);
    }
}
