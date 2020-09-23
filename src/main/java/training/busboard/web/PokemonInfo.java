package training.busboard.web;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.glassfish.jersey.jackson.JacksonFeature;
import training.busboard.Evolution;
import training.busboard.Pokemon;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class PokemonInfo {
    private final String name;
    Pokemon pokemon;

    public PokemonInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getDetails(){

        try {
            String fetchPokemonDataURL = ("https://pokeapi.co/api/v2/pokemon/" + name);

            Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
            Pokemon pokemon = client.target(fetchPokemonDataURL)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(Pokemon.class);

            Evolution evolutionLink = client.target("https://pokeapi.co/api/v2/pokemon-species/" + pokemon.getName())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(Evolution.class);

            Evolution evolution = client.target(evolutionLink.getEvolution_chain())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(Evolution.class);

            return getPokemonDetails(pokemon);
        } catch (Exception e){
            List<String> error = new ArrayList<String>();
            return error;
        }
    }

    public List<String> getPokemonDetails(Pokemon pokemon){

        List<String> details = new ArrayList<>();

        details.add("Name: " + pokemon.getName());
        details.add("Height: " +pokemon.getHeight());
        details.add("Weight: " +pokemon.getWeight() + "");
        details.add("Id: " + pokemon.getId() + "");

       return details;
    }

    public static void getEvolutions(Evolution evolution){
        List<List<String>> evolutionList = evolution.getEvolutions();
        for(int i = 0; i < evolutionList.size(); i++){
            for (int j = 0; j < evolutionList.get(i).size(); j++){
                if(i == 0){
                    System.out.println("First evolution: "+ evolutionList.get(i).get(j));
                }
                if(i == 1){
                    System.out.println("Second evolution: "+ evolutionList.get(i).get(j));
                }
            }
        }
    }

    public static void getAbilities(Pokemon pokemon){
        List<String> abilitiesList = pokemon.getAbilities();
        System.out.println("Abilities: ");
        for(int i = 0; i < abilitiesList.size(); i++){
            System.out.println("- " + abilitiesList.get(i));
        }
    }



}
