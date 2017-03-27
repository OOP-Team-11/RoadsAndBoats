
import controller.MapMakerController;
import model.Map;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends  Application{
    // main entry point of the application
    private MapMakerController mapMakerController;
    private Map gameMap;

    public static void main(String args[]){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // initialize the mapMakerController and main Game Map
        this.gameMap = new Map();
        this.mapMakerController = new MapMakerController(primaryStage, gameMap);
    }
}