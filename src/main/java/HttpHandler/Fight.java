package HttpHandler;

import DataBase.RecordToBD;
import model.impl.Fighter;
import model.impl.FighterConfiguration;
import model.impl.Item;
import model.impl.Person;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fight {

    //TODO Сделать из одного метода , много методов!!!!!!!!!!!
    public FighterConfiguration fight(FighterConfiguration fighterConfiguration) throws IOException, SQLException {
        RecordToBD recordToBD = new RecordToBD();
        ArrayList<Fighter> fighters = fighterConfiguration.getFighters();
        Person person = fighters.get(0).getPerson();
        Item item = fighters.get(0).getItem();
        Person personBot = fighters.get(1).getPerson();
        Item itemBot = fighters.get(1).getItem();
        float damageToUser = 0;
        float damageToBot = 0;
        float damageFromUser = person.getAttackSkill() * item.getDamageSkill();
        float damageFromBot = personBot.getAttackSkill() * itemBot.getDamageSkill();
        float defenceUser = person.getDefenceSkill() * item.getDefenceSkill();
        float defenceBot = personBot.getDefenceSkill() * itemBot.getDefenceSkill();
        if (damageFromUser > defenceBot) {
            damageToBot = damageFromUser - defenceBot;
            personBot.setHp(personBot.getHp() - damageToBot);
        } else {
            damageToBot = 0;
        }
        if (damageFromBot > defenceUser) {
            damageToUser = damageFromBot - defenceUser;
            person.setHp(person.getHp() - damageToUser);
        } else {
            damageToUser = 0;
        }
        FighterConfiguration fighterConfigurationExit = new FighterConfiguration();
        ArrayList<Fighter> fightersExit = new ArrayList<>();
        fightersExit.add(new Fighter(0, person, item));
        fightersExit.add(new Fighter(1, personBot, itemBot));
        fighterConfigurationExit.setFighters(fightersExit);
        recordToBD.recordResultToDB(person.getName(), personBot.getName(), (int) person.getHp()
                , (int) personBot.getHp(), item.getName(), itemBot.getName(), (int) damageToUser, (int) damageToBot);
        return fighterConfigurationExit;
    }
}
