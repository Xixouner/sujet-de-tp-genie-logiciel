package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public int money;

    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF") ) {
            return;
        }

        this.playerName = playerName;
        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
    }

    public String getAvatarClass () {
        return AvatarClass;
    }
    public void changeMoney(int amount){
      if(money + amount < 0){
        throw new IllegalArgumentException("Player can't have a negative money!");
      }
      money += amount;
    }
    // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
    public int retrieveLevel() {
      int level = 1;
         long previousThreshold = 0;
         while (true) {
             int nextLevel = level + 1;
             long nextThreshold =(long) level * 10 + ((long) nextLevel * previousThreshold) / 4;
             if (xp < nextThreshold) {
                 return level;
             }
             level++;
             previousThreshold = nextThreshold;
         }
    }

    public int getXp() {
        return this.xp;
    }
}
