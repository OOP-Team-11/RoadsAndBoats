package game.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WonderView extends View {

    private AnchorPane anchorPane;

    public WonderView(AnchorPane anchorPane){
        this.anchorPane = anchorPane;

        // for Testing atm, remove later
        Label label = new Label();
        label.setText("WonderView");
        this.anchorPane.getChildren().add(label);
    }

    @Override
    public void render() {

    }
}
