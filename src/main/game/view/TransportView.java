package game.view;

import game.utilities.observer.TransportRenderInfoObserver;
import game.view.render.TransportRenderInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TransportView extends View implements TransportRenderInfoObserver {

    private AnchorPane anchorPane;
    private TransportRenderInfo transportRenderInfo;
    private Canvas canvas;
    private GraphicsContext gc;
    private Boolean newData;
    private ListView donkeyTable;
    private ListView wagonTable;
    private ListView truckTable;
    private ListView raftTable;
    private ListView rowBoatTable;
    private ListView steamShipTable;

    public TransportView(AnchorPane anchorPane)
    {
        setAnchorPane(anchorPane);
       // drawHeadingTitle();
        initializeCanvas();
        setCanvasBackground();
        drawTitle();
        setBoxRenderingSize();
        drawDonkeyBox();
        drawWagonBox();
        drawTruckBox();
        drawRaftBox();
        drawRowboatBox();
        drawSteamShipBox();
        drawPlayer2Images();
        setDonkeyTable();
        TESTING();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void initializeCanvas(){
        this.canvas = new Canvas(1300,800);
        this.gc = this.canvas.getGraphicsContext2D();
        this.anchorPane.getChildren().add(canvas);
    }

    private void drawTitle(){
        this.gc.setFont(new Font(60));
        this.gc.setLineWidth(3.0);
        this.gc.strokeText("Transport OverView",390.0,65.0);
    }

    private void clearCanvas(){
        this.gc.clearRect(0,0,1300,800);
    }

    private void setCanvasBackground(){
        this.gc.setFill(Color.LIGHTGREY);
        this.gc.fillRect(0,0,1300,800);
    }

    private void setBoxRenderingSize(){
        this.gc.setFill(Color.TEAL);
        this.gc.setLineWidth(1.5);
        this.gc.setFont(new Font(20));
    }

    private void drawDonkeyBox(){
        this.gc.fillRoundRect(120,150,500,200,20,20);
        this.gc.strokeText("Donkeys", 130,180);
        this.donkeyTable = new ListView();
        this.donkeyTable.setPrefHeight(170.0);
        this.donkeyTable.setPrefWidth(350.0);
        this.donkeyTable.setMaxWidth(350.0);
        this.anchorPane.getChildren().add(donkeyTable);
        this.anchorPane.setLeftAnchor(donkeyTable,235.0);
        this.anchorPane.setTopAnchor(donkeyTable,165.0);
    }

    private void drawWagonBox(){
        this.gc.fillRoundRect(120,360,500,200,20,20);
        this.gc.strokeText("Wagons", 130,390);
        this.wagonTable = new ListView();
        this.wagonTable.setPrefHeight(170.0);
        this.wagonTable.setPrefWidth(350.0);
        this.wagonTable.setMaxWidth(350.0);
        this.anchorPane.getChildren().add(wagonTable);
        this.anchorPane.setLeftAnchor(wagonTable,235.0);
        this.anchorPane.setTopAnchor(wagonTable,375.0);
    }

    private void drawTruckBox(){
        this.gc.fillRoundRect(120,570,500,200,20,20);
        this.gc.strokeText("Trucks", 130,600);
        this.truckTable = new ListView();
        this.truckTable.setPrefHeight(170.0);
        this.truckTable.setPrefWidth(350.0);
        this.truckTable.setMaxWidth(350.0);
        this.anchorPane.getChildren().add(truckTable);
        this.anchorPane.setLeftAnchor(truckTable,235.0);
        this.anchorPane.setTopAnchor(truckTable,585.0);
    }

    private void drawRaftBox(){
        this.gc.fillRoundRect(720,150,500,200,20,20);
        this.gc.strokeText("Rafts", 730,180);
        this.raftTable = new ListView();
        this.raftTable.setPrefHeight(170.0);
        this.raftTable.setPrefWidth(350.0);
        this.raftTable.setMaxWidth(350.0);
        this.anchorPane.getChildren().add(raftTable);
        this.anchorPane.setLeftAnchor(raftTable,835.0);
        this.anchorPane.setTopAnchor(raftTable,165.0);
    }

    private void drawRowboatBox(){
        this.gc.fillRoundRect(720,360,500,200,20,20);
        this.gc.strokeText("RowBoats", 730,390);
        this.rowBoatTable = new ListView();
        this.rowBoatTable.setPrefHeight(170.0);
        this.rowBoatTable.setPrefWidth(350.0);
        this.rowBoatTable.setMaxWidth(350.0);
        this.anchorPane.getChildren().add(rowBoatTable);
        this.anchorPane.setLeftAnchor(rowBoatTable, 835.0);
        this.anchorPane.setTopAnchor(rowBoatTable,375.0);
    }
    private void drawSteamShipBox(){
        this.gc.fillRoundRect(720,570,500,200,20,20);
        this.gc.strokeText("Steam-Ships", 730,600);
        this.steamShipTable = new ListView();
        this.steamShipTable.setPrefHeight(170.0);
        this.steamShipTable.setPrefWidth(350.0);
        this.steamShipTable.setMaxWidth(350.0);
        this.anchorPane.getChildren().add(steamShipTable);
        this.anchorPane.setLeftAnchor(steamShipTable,835.0);
        this.anchorPane.setTopAnchor(steamShipTable, 585.0);
    }

    private void setDonkeyList(ObservableList<String> data){
        this.donkeyTable.setItems(data);
    }
    private void setWagonList(ObservableList<String> data){
        this.wagonTable.setItems(data);
    }
    private void setTruckList(ObservableList<String> data){
        this.truckTable.setItems(data);
    }
    private void setRaftList(ObservableList<String> data){
        this.raftTable.setItems(data);
    }
    private void setRowBoatList(ObservableList<String> data){
        this.rowBoatTable.setItems(data);
    }
    private void setSteamShipList(ObservableList<String> data){
        this.steamShipTable.setItems(data);
    }

    private void TESTING(){
        ObservableList<String> items =FXCollections.observableArrayList (
                "# | Move | Carry | Followers |                      Resources                 |",
                "1","2");
        setDonkeyList(items);
        setWagonList(items);
        setTruckList(items);
        setRaftList(items);
        setRowBoatList(items);
        setSteamShipList(items);
    }

    private void drawPlayer1Images(){
        this.gc.drawImage(assets.DONKEY_BLUE,130,200, 100,100);
        this.gc.drawImage(assets.WAGON_BLUE,130,410, 100,100);
        this.gc.drawImage(assets.TRUCK_BLUE,130,620, 100,100);
        this.gc.drawImage(assets.RAFT_BLUE,730,200, 100,100);
        this.gc.drawImage(assets.ROWBOAT_BLUE,730,410, 100,100);
        this.gc.drawImage(assets.STEAMSHIP_BLUE,730,620, 100,100);

    }

    private void setDonkeyTable(){


    }



    private void drawPlayer2Images(){
        this.gc.drawImage(assets.DONKEY_RED,130,200, 100,100);
        this.gc.drawImage(assets.WAGON_RED,130,410, 100,100);
        this.gc.drawImage(assets.TRUCK_RED,130,620, 100,100);
        this.gc.drawImage(assets.RAFT_RED,730,200, 100,100);
        this.gc.drawImage(assets.ROWBOAT_RED,730,410, 100,100);
        this.gc.drawImage(assets.STEAMSHIP_RED,730,620, 100,100);
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

