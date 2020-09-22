package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
    public String name;
    public ArrayList<LinkedHashMap> abilities;
    public int height;
    public int id;
    public LinkedHashMap sprites;
    public int weight;

    public void getPokemonDetails(){
        System.out.println("Name: " + name + "\nheight: " + height + "\nAbilities: ");
        for (int i = 0; i < abilities.size(); i++){
            LinkedHashMap obj = (LinkedHashMap) abilities.get(i).get("ability");
            System.out.println(obj.get("name"));
        }
    }
}
