package training.busboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Pokemon {
    public String name;
    public ArrayList<LinkedHashMap> abilities;
    public int height;
    public int base_experience;
    public int id;
    public ArrayList<LinkedHashMap> forms;
    public ArrayList<LinkedHashMap> game_indices;
    public boolean is_default;
    public ArrayList<LinkedHashMap> moves ;
    public String location_area_encounters;
    public ArrayList<LinkedHashMap> held_items;
    public int order;
    public LinkedHashMap species;
    public LinkedHashMap sprites;
    public ArrayList<LinkedHashMap> stats;
    public ArrayList<LinkedHashMap> types;
    public int weight;

    public void getPokemonDetails(){
        System.out.println("Name: " + name + "\nheight: " + height + "\nAbilities: ");
        for (int i = 0; i < abilities.size(); i++){
            LinkedHashMap obj = (LinkedHashMap) abilities.get(i).get("ability");
            System.out.println(obj.get("name"));
        }
    }
}
