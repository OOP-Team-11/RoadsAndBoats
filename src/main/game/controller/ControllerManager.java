package game.controller;


import game.view.ViewHandler;


public class ControllerManager {

    private ViewHandler viewHandler;
    private MainViewController mainViewController;
    private WonderViewController wonderViewController;
    private ResearchViewController researchViewController;
    private TransportViewController transportViewController;
    private OptionsViewController optionsViewController;
    private SaveLoadViewController saveLoadViewController;


    public ControllerManager(ViewHandler viewHandler){
        setUpViewHandler(viewHandler);
        setupMainViewController();
        setupOptionsViewController();
        setupResearchViewController();
        setupSaveLoadViewController();
        setupTrasportViewController();
        setupWonderViewController();
    }

    private void setUpViewHandler(ViewHandler viewHandler){
        this.viewHandler = viewHandler;
    }
    private void setupMainViewController(){
        this.mainViewController = new MainViewController(viewHandler.getMainViewReference());
    }
    private void setupTrasportViewController(){
        this.transportViewController = new TransportViewController(viewHandler.getTransportViewReference());
    }
    private void setupResearchViewController(){
        this.researchViewController = new ResearchViewController(viewHandler.getResearchViewReference());
    }
    private void setupWonderViewController(){
        this.wonderViewController = new WonderViewController(viewHandler.getWonderViewReference());
    }
    private void setupSaveLoadViewController(){
        this.saveLoadViewController = new SaveLoadViewController(viewHandler.getSaveLoadViewReference());
    }
    private void setupOptionsViewController(){
        this.optionsViewController = new OptionsViewController(viewHandler.getOptionsViewReference());
    }

    public MainViewController getMainViewController() {
        return mainViewController;
    }
}
