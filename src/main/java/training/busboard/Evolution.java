package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Evolution {
    public int id;
    public LinkedHashMap chain;

}
