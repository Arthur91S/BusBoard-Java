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
        boolean invalidPokemon = false;
        boolean mainLoopContinue = true;
        Pokemon pokemon = new Pokemon();
        Evolution evolution = new Evolution();

        do{
            do{
                System.out.println("What Pokemon would you like to see the details about: ");
                String userInput = sc.next();

                try {
                    String fetchPokemonDataURL = ("https://pokeapi.co/api/v2/pokemon/" + userInput);

                    Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
                    pokemon = client.target(fetchPokemonDataURL)
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .get(Pokemon.class);

                    Evolution evolutionLink = client.target("https://pokeapi.co/api/v2/pokemon-species/" + pokemon.getName())
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .get(Evolution.class);

                    evolution = client.target(evolutionLink.getEvolution_chain())
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .get(Evolution.class);

                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid Pokemon");
                    invalidPokemon = true;
                }
            }while (invalidPokemon);

            boolean exitInfo = false;

            do {
                System.out.println("What infomation would you like to view about " +pokemon.getName() + ":\n(1)Information " +
                        "about pokemon\n(2)Pokemon evolutions\n(3)Pokemon abilities\n(4)Output All\n(5)Exit");
                String userInput = sc.next();
                switch (userInput){
                    case "1":
                        getPokemonDetails(pokemon);
                        break;
                    case "2":
                        getEvolutions(evolution);
                        break;
                    case "3":
                        getAbilities(pokemon);
                        break;
                    case "4":
                        getPokemonDetails(pokemon);
                        getAbilities(pokemon);
                        getEvolutions(evolution);
                        break;
                    case "5":
                        exitInfo = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }while(!exitInfo);

            System.out.println("Would you like to Exit (1) Yes  (Any Key) No");
            String userInput = sc.next();
            if (userInput.equals("1")){
                mainLoopContinue = false;
            }

        }while (!mainLoopContinue);

    }


    public static void getPokemonDetails(Pokemon pokemon){
        System.out.println("Name: " + pokemon.getName());
        System.out.println("Height: " + pokemon.getHeight());
        System.out.println("Weight: " + pokemon.getWeight());
        System.out.println("Id: " + pokemon.getId());
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
