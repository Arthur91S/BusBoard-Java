package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    private String name;
    private List<LinkedHashMap> abilities;
    private int height;
    private  int id;
    private LinkedHashMap sprites;
    private int weight;

    public List<String> getAbilities(){
        List<String> abilitiesList = new ArrayList<>();
        for (int i = 0; i < abilities.size(); i++){
            LinkedHashMap obj = (LinkedHashMap) abilities.get(i).get("ability");
            abilitiesList.add(obj.get("name").toString());
        }
        return abilitiesList;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public LinkedHashMap getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }

}
