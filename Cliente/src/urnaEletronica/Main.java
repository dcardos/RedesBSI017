package urnaEletronica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage mPrimaryStage;

    public Stage getPrimaryStage() {
        return mPrimaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.mPrimaryStage = primaryStage;
        mainWindow();       // calling mainWindow
    }

    public void mainWindow() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("window.fxml"));
        AnchorPane pane = loader.load();

        MainWindowController mainWindowMainWindowController = loader.getController();
        mainWindowMainWindowController.setMain(this);

        Scene scene = new Scene(pane);
        mPrimaryStage.setTitle("Urna Eletrônica");
        mPrimaryStage.setScene(scene);
        mPrimaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
