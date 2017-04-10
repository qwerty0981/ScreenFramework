package screenFramework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Caleb on 3/29/2017.
 */
public class ScreenFramework {
    private HashMap<String, ArrayList<Screen>> loadList = new HashMap<>();
    private HashMap<String, HashMap<String, Screen>> screens = new HashMap<>();
    private ScreenController controller;
    private Object Parent;
//    private static ScreenFramework instance = null;

    public ScreenFramework(ScreenController screenController, Object Parent){
        controller = screenController;
        loadList.put("PrimaryLoad", new ArrayList<>());
        this.Parent = Parent;
    }

//    public static ScreenFramework getInstance(){
//        if (instance == null){
//            instance = new ScreenFramework();
//            return instance;
//        }else{
//            return instance;
//        }
//    }


    public boolean add(Screen screen, String group, Boolean Force_group){
        for(String g : screens.keySet()){
            if(g == group){
                screens.get(group).put(screen.getName(), screen);
                return true;
            }
        }
        if(Force_group){
            HashMap<String, Screen> temp = new HashMap<>();
            temp.put(screen.getName(), screen);
            screens.put(group, temp);
            return true;
        }
        return false;
    }

    public void addLoadableScreen(Screen screen) throws Exception {
        for (Screen a : loadList.get("PrimaryLoad")) {
            if (screen == a){
                throw new Exception("Screen: " + screen.getName() + "already exists in the loadable list.");
            }
        }
        loadList.get("PrimaryLoad").add(screen);
    }

    public boolean load(Screen screen){
        if(screen.getControllerName() != "" && screen.getFXML() != ""){
            try{
                FXMLLoader myLoad = new FXMLLoader(Parent.getClass().getResource(screen.getFXML()));
                Scene parent = new Scene(myLoad.load());
                ScreenContol controller = myLoad.getController();
                controller.setScreenParent(this.controller);
                screen.setLoadedScene(parent);
//                if(!add(screen, "LoadedScreens", false)){
//                    throw new Exception("Failed to add " + screen.getName() + " to LoadedScreens Group.");
//                }
                if(controller.getGroup() != ""){
                    if(!add(screen, controller.getGroup(), true)){
                        throw new Exception("Failed to add " + screen.getName() + " to " + controller.getGroup() + ".");
                    }
                }
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Controller/FXML file does not exist");
        }
        return false;
    }

    public ArrayList<Screen> getLoadList(String ListName) throws Exception{
        ArrayList<Screen> temp;
        try{
            temp = loadList.get(ListName);
        }catch (Exception e){
            throw new Exception("List: " + ListName + " does not exist.");
        }
        return temp;
    }

    public Screen getScreen(String Group, String ScreenName){
        return screens.get(Group).get(ScreenName);
    }
}
