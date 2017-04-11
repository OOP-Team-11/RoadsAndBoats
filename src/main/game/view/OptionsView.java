package game.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OptionsView extends View{

    private AnchorPane anchorPane;

    public OptionsView(AnchorPane anchorPane){
        this.anchorPane = anchorPane;

        // for Testing atm, remove later
        Label label = new Label();
        label.setText("OPTIONS VIEW");
        this.anchorPane.getChildren().add(label);
    }

    @Override
    public void render() {

    }
}
