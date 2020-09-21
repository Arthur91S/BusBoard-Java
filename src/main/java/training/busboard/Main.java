package training.busboard;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class Main {
    public static void main(String args[]) {
        // Your code here!

        String URL = "https://pokeapi.co/api/v2/pokemon/1";

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        String name = client.target(URL)
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

        System.out.println(name);

    }
}	
