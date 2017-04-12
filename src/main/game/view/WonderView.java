package game.view;

import game.utilities.observer.WonderRenderInfoObserver;
import game.view.render.WonderRenderInfo;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class WonderView extends View implements WonderRenderInfoObserver{

    private AnchorPane anchorPane;
    private WonderRenderInfo wonderRenderInfo;
    private Boolean newData;

    public WonderView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        drawHeadingTitle();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }
    private void drawHeadingTitle(){
        // for Testing atm, remove later
        Label label = new Label();
        label.setText("Wonder");
        label.setFont(new Font(60));
        this.anchorPane.getChildren().add(label);
        this.anchorPane.setLeftAnchor(label,500.0);
    }

    @Override
    public void render() {

    }

    @Override
    public void updateWonderInfo(WonderRenderInfo wonderRenderInfo) {
        this.wonderRenderInfo = wonderRenderInfo;
        this.newData = true;
    }
}
