package training.busboard;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {

        String URL = "https://pokeapi.co/api/v2/pokemon/1";

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        Object name = client.target(URL)
                .request(MediaType.APPLICATION_JSON)
                .get(Object.class);

        int test = 1;


        System.out.println(name);
    }
}	
