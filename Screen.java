package screenFramework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Caleb on 3/29/2017.
 */
public class Screen {
    private String controllerName = "";
    private String FXML = "";
    private String Name = "";
    private ArrayList<String> groups = new ArrayList<>();
    private Scene loadedScene;

    public Screen(String Controller, String FXML){
        controllerName = Controller;
        this.FXML = FXML;
    }

    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }
    public ArrayList<String> getGroups(){
        return groups;
    }
    public boolean inGroup(String Group){
        for(String g : groups){
            if(g == Group){
                return true;
            }
        }
        return false;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getFXML() {
        return FXML;
    }

    public void setFXML(String FXML) {
        this.FXML = FXML;
    }

    public Scene getLoadedScene() {
        return loadedScene;
    }

    public void setLoadedScene(Scene LoadedScene) {
        this.loadedScene = LoadedScene;
    }
}
