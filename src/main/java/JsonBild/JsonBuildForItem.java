package JsonBild;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import model.ItemConfiguration;
import model.impl.FighterConfiguration;
import model.impl.Item;
import model.impl.ItemConfigurationImpl;

import java.util.List;

public class JsonBuildForItem {


    public List<Item> FromJsonItem(String jsonString){
        return new Gson().fromJson(jsonString, ItemConfigurationImpl.class).getItems();
    }
    public String parserItems(List<Item> itemList) throws JsonProcessingException {
        ItemConfigurationImpl itemConfiguration = new ItemConfigurationImpl();
        itemConfiguration.setName("bibib");
        itemConfiguration.setItems(itemList);

        return new Gson().toJson(itemConfiguration);
}
}
