package model.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.ItemConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ItemConfigurationImpl implements ItemConfiguration {

    private String name;
    private List<Item> items;

    public ItemConfigurationImpl(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public ItemConfigurationImpl() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ItemConfiguration{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }


    @Override
    public void addItem(Item item) throws JsonProcessingException {
        items.add(item);
    }
}
