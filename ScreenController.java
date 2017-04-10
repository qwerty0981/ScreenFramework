package screenFramework;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Caleb on 3/29/2017.
 */
public class ScreenController {
    private Stage Root;
    private ScreenFramework SF;
    private Application Parent;

    public ScreenController(Stage Root, Application Parent){
        this.Root = Root;
        SF = new ScreenFramework(this, Parent);
        this.Parent = Parent;
    }

    public Stage getRoot(){
        return Root;
    }

    public void setScene(String screen) throws Exception{
            Screen s = SF.getScreen("Primary", screen);
            try{
                Root.setScene(s.getLoadedScene());
            } catch (Exception e){
                System.out.println(e.getMessage());
                throw new Exception("ScreenController: Failed to set screen");
            }

    }

    public void addScreen(String screenController, String fxml, String Name) throws Exception {
        Screen screen = new Screen(screenController, fxml);
        screen.setName(Name);
        SF.addLoadableScreen(screen);
    }

    public boolean loadPrimaryScreens(){
        try {
            for (Screen s : SF.getLoadList("PrimaryLoad")) {
                SF.load(s);
            }
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void show(){
        Root.show();
    }

    public Application getParent(){return Parent;}

    public void printLoadedScreens(){
        try {
            ArrayList<Screen> a = SF.getLoadList("PrimaryLoad");
            for (Screen s : a){
                System.out.println(s.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
