package game.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TransportView extends View {

    private AnchorPane anchorPane;

    public TransportView(AnchorPane anchorPane)
    {
        this.anchorPane = anchorPane;

        // for Testing atm, remove later
        Label label = new Label();
        label.setText("Transport View");
        this.anchorPane.getChildren().add(label);
    }

    @Override
    public void render() {

    }
}
