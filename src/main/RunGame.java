import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import startApplication.WelcomeView;
import startApplication.WelcomeViewController;

public class RunGame extends Application{


    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(anchorPane);
        Scene scene = new Scene(stackPane,1400 , 800);
        primaryStage.setTitle("roads and Boats ");
        primaryStage.setScene(scene);
        primaryStage.show();

        WelcomeView welcomeView = new WelcomeView(anchorPane, stackPane, primaryStage);
        WelcomeViewController welcomeViewController = new WelcomeViewController(welcomeView, primaryStage);
    }
}
