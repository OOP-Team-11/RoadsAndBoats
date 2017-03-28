package utilities;

public class Assets {

    private static Assets assets = new Assets();

    public static Assets getInstance(){
        return assets;
    }
    private Assets(){
        if(loadAssets()){
            // everything is loaded in succesfully loaded in
        } else {
            System.out.println("ERROR: Failed to load Assets");
        }
    }
    // TODO declare images here
    // for example
    // public Image EXAMPLE;

    public boolean loadAssets(){
        try{
            // TODO load in images here
            // for example
            // EXAMPLE = new Image("resources/image.png");
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
