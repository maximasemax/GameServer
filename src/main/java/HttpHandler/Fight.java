package HttpHandler;

import model.impl.Fighter;
import model.impl.FighterConfiguration;
import model.impl.Item;
import model.impl.Person;

import java.io.IOException;
import java.util.ArrayList;

public class Fight {


    public FighterConfiguration fight(FighterConfiguration fighterConfiguration) throws IOException, InterruptedException {
        ArrayList<Fighter> fighters = fighterConfiguration.getFighters();

        Person person = fighters.get(0).getPerson();
        Item item = fighters.get(0).getItem();
        Person personBot = fighters.get(1).getPerson();
        Item itemBot = fighters.get(1).getItem();

        float damageFromUser = 0;
        float damageFromBot = 0;
        float defenceUser = 0;
        float defenceBot = 0;
        float damageToUser = 0;
        float damageToBot = 0;
        damageFromUser = person.getAttackSkill() * item.getDamageSkill();
        damageFromBot = personBot.getAttackSkill() * itemBot.getDamageSkill();
        defenceUser = person.getDefenceSkill() * item.getDefenceSkill();
        defenceBot = personBot.getDefenceSkill() * itemBot.getDefenceSkill();
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
        return fighterConfigurationExit;
    }
}
