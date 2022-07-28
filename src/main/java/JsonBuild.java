import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.impl.Fighter;
import model.impl.FighterConfiguration;
import model.impl.Person;

import java.io.File;

public class JsonBuild {



    public String parserFighter(FighterConfiguration fighterConfiguration) throws JsonProcessingException {
        return new Gson().toJson(fighterConfiguration);
    }

    public FighterConfiguration fromJsonFighter(String jasonString) throws JsonProcessingException {
        return new Gson().fromJson(jasonString, FighterConfiguration.class);
    }
}

