package game.view;

import game.utilities.observer.TransportRenderInfoObserver;
import game.view.render.TransportRenderInfo;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class TransportView extends View implements TransportRenderInfoObserver {

    private AnchorPane anchorPane;
    private TransportRenderInfo transportRenderInfo;
    private Boolean newData;

    public TransportView(AnchorPane anchorPane)
    {
        setAnchorPane(anchorPane);
        drawHeadingTitle();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void drawHeadingTitle(){
        // for Testing atm, remove later
        Label label = new Label();
        label.setText("Transport Overview");
        label.setFont(new Font(60));
        this.anchorPane.getChildren().add(label);
        this.anchorPane.setLeftAnchor(label,400.0);
    }

    @Override
    public void render() {

    }

    @Override
    public void updateTransportInfo(TransportRenderInfo transportRenderInfo) {
        this.transportRenderInfo = transportRenderInfo;
        this.newData = true;
    }
}
