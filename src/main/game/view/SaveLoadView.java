package game.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SaveLoadView extends View {

    private AnchorPane anchorPane;

    public SaveLoadView(AnchorPane anchorPane){
        this.anchorPane = anchorPane;

        // for Testing atm, remove later
        Label label = new Label();
        label.setText("SaveLoadView");
        this.anchorPane.getChildren().add(label);
    }

    @Override
    public void render() {

    }
}
