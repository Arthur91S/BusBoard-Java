package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Evolution {
    public LinkedHashMap chain;
    private LinkedHashMap evolution_chain;

    public  String getEvolution_chain() {
        return evolution_chain.get("url").toString();
    }

    public List<List<String>> getEvolutions(){
        List<String> fistEvolutions = new ArrayList<>();
        List<String> secondEvolutions = new ArrayList<>();
        List<List<String>> finalEvolutionsList = new ArrayList<>();

        ArrayList<LinkedHashMap> evolvesToArray = (ArrayList<LinkedHashMap>) chain.get("evolves_to");
        ArrayList<LinkedHashMap> evolvesToArraySecond = (ArrayList<LinkedHashMap>) evolvesToArray.get(0).get("evolves_to");
        try{
            for(int i = 0; i <evolvesToArray.size(); i++){
                LinkedHashMap evolvesToArrayAccess =  evolvesToArray.get(i);
                LinkedHashMap firstEvolution = (LinkedHashMap) evolvesToArrayAccess.get("species");
                fistEvolutions.add(firstEvolution.get("name").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            for(int i = 0; i <evolvesToArraySecond.size(); i++){
                LinkedHashMap evolvesToArrayAccessSecond =  evolvesToArraySecond.get(i);
                LinkedHashMap secondEvolution = (LinkedHashMap) evolvesToArrayAccessSecond.get("species");
                secondEvolutions.add(secondEvolution.get("name").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        finalEvolutionsList.add(fistEvolutions);
        finalEvolutionsList.add(secondEvolutions);
        return  finalEvolutionsList;
    }
}
