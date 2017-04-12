package game.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class OptionsView extends View{

    private AnchorPane anchorPane;

    public OptionsView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        drawHeadingTitle();

    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void drawHeadingTitle(){
        // for Testing atm, remove later
        Label label = new Label();
        label.setText("Game Settings");
        label.setFont(new Font(60));
        this.anchorPane.getChildren().add(label);
        this.anchorPane.setLeftAnchor(label,450.0);
    }

    @Override
    public void render() {

    }
}
