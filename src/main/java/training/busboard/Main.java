package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        System.out.println("What Pokemon would you like to see the details about: ");
        String userInput = sc.next();


        String fetchPokemonDataURL = ("https://pokeapi.co/api/v2/pokemon/" + userInput);

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        Pokemon pokemon = client.target(fetchPokemonDataURL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Pokemon.class);

        pokemon.getPokemonDetails();

        /*Evolution evolution = client.target(("https://pokeapi.co/api/v2/evolution-chain/" + pokemon.id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Evolution.class);
        System.out.println(evolution.toString());*/
    }
}	
