package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("What Pokemon would you like to see the details about: ");
        String userInput = sc.next();

        String fetchPokemonDataURL = ("https://pokeapi.co/api/v2/pokemon/" + userInput);
//
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        Pokemon pokemon = client.target(fetchPokemonDataURL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Pokemon.class);

        getPokemonDetails(pokemon);


        // get evolution chain link
        Evolution evolutionLink = client.target(("https://pokeapi.co/api/v2/pokemon-species/charmander"))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Evolution.class);

        // get evolution cain
        Evolution evolution = client.target(("https://pokeapi.co/api/v2/evolution-chain/" +evolutionLink))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Evolution.class);
        int test = 1;
        System.out.println(evolution.toString());
    }
    public static void getPokemonDetails(Pokemon pokemon){
        System.out.println("Viewing Pokemon: " + pokemon.getName());
         for (int i =0; i < pokemon.getAbilities().size(); i++){
             System.out.println("Ability:" + pokemon.getAbilities().get(i));
         }
    }
}	
