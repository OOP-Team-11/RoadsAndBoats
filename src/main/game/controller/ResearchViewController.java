package game.controller;

import game.view.ResearchView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ResearchViewController {

    private ResearchView researchView;

    public ResearchViewController(ResearchView researchView){
        setResearchView(researchView);
        initializeTechnology1Event();
        initializeTechnology2Event();
        initializeTechnology3Event();
        initializeTechnology4Event();
        initializeTechnology5Event();
        initializeTechnology6Event();
        initializeTechnology7Event();
        initializeTechnology8Event();
    }

    private void setResearchView(ResearchView researchView){
        this.researchView = researchView;
    }

    private void initializeTechnology1Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 1 researched");
            }
        };
        this.researchView.addTech1EventFilter(eventHandler);
    }
    private void initializeTechnology2Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 2 researched");
            }
        };
        this.researchView.addTech2EventFilter(eventHandler);
    }
    private void initializeTechnology3Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 3 researched");
            }
        };
        this.researchView.addTech3EventFilter(eventHandler);
    }
    private void initializeTechnology4Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 4 researched");
            }
        };
        this.researchView.addTech4EventFilter(eventHandler);
    }
    private void initializeTechnology5Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 5 researched");
            }
        };
        this.researchView.addTech5EventFilter(eventHandler);
    }
    private void initializeTechnology6Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 6 researched");
            }
        };
        this.researchView.addTech6EventFilter(eventHandler);
    }
    private void initializeTechnology7Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 7 researched");
            }
        };
        this.researchView.addTech7EventFilter(eventHandler);
    }
    private void initializeTechnology8Event(){
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // TODO hook up to model to update when clicked to research a technology
                System.out.println("Technology 8 researched");
            }
        };
        this.researchView.addTech8EventFilter(eventHandler);
    }

}
