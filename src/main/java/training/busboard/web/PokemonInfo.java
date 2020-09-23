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
        List<String> allDetails = new ArrayList<String>();
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

            List<String> pokemonDetails = getPokemonDetails(pokemon);
            for (int i = 0; i < pokemonDetails.size(); i++) {
                allDetails.add(pokemonDetails.get(i));
            }

            List<String> pokemonEvolutions = getEvolutions(evolution);
            for (int i = 0; i < pokemonEvolutions.size(); i++) {
                allDetails.add(pokemonEvolutions.get(i));
            }

            List<String> pokemonAbilities = getAbilities(pokemon);
            for (int i = 0; i < pokemonAbilities.size(); i++) {
                allDetails.add(pokemonAbilities.get(i));
            }

            return allDetails;
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

    public static List<String> getEvolutions(Evolution evolution){
        List<String> evolutions = new ArrayList<String>();
        List<List<String>> evolutionList = evolution.getEvolutions();
        for(int i = 0; i < evolutionList.size(); i++){
            for (int j = 0; j < evolutionList.get(i).size(); j++){
                if(i == 0){
                    evolutions.add("First evolution: "+ evolutionList.get(i).get(j));
                }
                if(i == 1){
                    evolutions.add("Second evolution: "+ evolutionList.get(i).get(j));
                }
            }
        }
        return evolutions;
    }

    public static List<String> getAbilities(Pokemon pokemon){
        List<String> abilities = new ArrayList<String>();
        List<String> abilitiesList = pokemon.getAbilities();
        abilities.add("Abilities: ");
        for(int i = 0; i < abilitiesList.size(); i++){
            abilities.add("- " + abilitiesList.get(i));
        }
        return abilities;
    }



}
