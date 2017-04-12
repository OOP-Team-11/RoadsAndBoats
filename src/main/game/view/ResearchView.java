package game.view;

import game.utilities.observer.TechRenderInfoObserver;
import game.view.render.TechRenderInfo;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class ResearchView extends View implements TechRenderInfoObserver{

    private AnchorPane anchorPane;
    private TechRenderInfo techRenderInfo;
    private Boolean newData;

    public ResearchView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        drawHeadingTitle();
    }

    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void drawHeadingTitle(){
        // for Testing atm, remove later
        Label label = new Label();
        label.setText("Research");
        label.setFont(new Font(60));
        this.anchorPane.getChildren().add(label);
        this.anchorPane.setLeftAnchor(label,500.0);
    }

    @Override
    public void render() {

    }

    @Override
    public void updateTechInfo(TechRenderInfo techRenderInfo) {
        this.techRenderInfo = techRenderInfo;
        this.newData = true;
    }
}
