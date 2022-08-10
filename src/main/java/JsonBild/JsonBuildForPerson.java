package JsonBild;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import model.impl.Person;
import model.impl.PersonConfigurationImpl;

import java.util.List;

public class JsonBuildForPerson {

    public List<Person> fromJsonPersons(String jsonString) {
        return new Gson().fromJson(jsonString, PersonConfigurationImpl.class).getPersons();
    }

    public String convertPersonsToJson(List<Person> personList) throws JsonProcessingException {
        PersonConfigurationImpl personConfiguration = new PersonConfigurationImpl();
        personConfiguration.setName("bibib");
        personConfiguration.setPersons(personList);

        return new Gson().toJson(personConfiguration);
    }
}
