package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

import re.forestier.edu.rpg.player.AvatarClassAvailable;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };


    public static HashMap<AvatarClassAvailable, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        HashMap<AvatarClassAvailable, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();

        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.ADVENTURER, adventurerLevels());
        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.ARCHER, archerLevels());
        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.DWARF, dwarfLevels());

        return abilitiesPerTypeAndLevel;
    }
    private static HashMap<String, Integer> adventurerLevel1() {
        HashMap<String, Integer> adventurerLevel1 = new HashMap<>();
        adventurerLevel1.put("INT", 1);
        adventurerLevel1.put("DEF", 1);
        adventurerLevel1.put("ATK", 3);
        adventurerLevel1.put("CHA", 2);
        return adventurerLevel1;
    }
    private static HashMap<String, Integer> adventurerLevel2() {
      HashMap<String, Integer> adventurerLevel2 = new HashMap<>();
      adventurerLevel2.put("INT", 2);
      adventurerLevel2.put("CHA", 3);
      return adventurerLevel2;
    }
    private static HashMap<String, Integer> adventurerLevel3() {
      HashMap<String, Integer> adventurerLevel3 = new HashMap<>();
      adventurerLevel3.put("ATK", 5);
      adventurerLevel3.put("ALC", 1);
      return adventurerLevel3;
    }
    private static HashMap<String, Integer> adventurerLevel4() {
      HashMap<String, Integer> adventurerLevel4 = new HashMap<>();
      adventurerLevel4.put("DEF", 3);
      return adventurerLevel4;
    }
    private static HashMap<String, Integer> adventurerLevel5() {
      HashMap<String, Integer> adventurerLevel5 = new HashMap<>();
      adventurerLevel5.put("VIS", 1);
      adventurerLevel5.put("DEF", 4);
      return adventurerLevel5;
    }

    private static HashMap<String, Integer> archerLevel1() {
        HashMap<String, Integer> archerLevel1 = new HashMap<>();
        archerLevel1.put("INT", 1);
        archerLevel1.put("ATK", 3);
        archerLevel1.put("CHA", 1);
        archerLevel1.put("VIS", 3);
        return archerLevel1;
    }
    private static HashMap<String, Integer> archerLevel2() {
        HashMap<String, Integer> archerLevel2 = new HashMap<>();
        archerLevel2.put("DEF", 1);
        archerLevel2.put("CHA", 2);
        return archerLevel2;
    }
    private static HashMap<String, Integer> archerLevel3() {
        HashMap<String, Integer> archerLevel3 = new HashMap<>();
        archerLevel3.put("ATK", 3);// ne sert à rien, mais c'est pour montrer que l'archer est plus fort que les autres classes
        return archerLevel3;
    }
    private static HashMap<String, Integer> archerLevel4() {
        HashMap<String, Integer> archerLevel4 = new HashMap<>();
        archerLevel4.put("DEF", 2);
        return archerLevel4;
    }
    private static HashMap<String, Integer> archerLevel5() {
        HashMap<String, Integer> archerLevel5 = new HashMap<>();
        archerLevel5.put("ATK", 4);
        return archerLevel5;
    }

    private static HashMap<String, Integer> dwarfLevel1() {
        HashMap<String, Integer> dwarfLevel1 = new HashMap<>();
        dwarfLevel1.put("ALC", 4);
        dwarfLevel1.put("INT", 1);
        dwarfLevel1.put("ATK", 3);
        return dwarfLevel1;
    }
    private static HashMap<String, Integer> dwarfLevel2() {
        HashMap<String, Integer> dwarfLevel2 = new HashMap<>();
        dwarfLevel2.put("DEF", 1);
        dwarfLevel2.put("ALC", 5);
        return dwarfLevel2;
    }
    private static HashMap<String, Integer> dwarfLevel3() {
        HashMap<String, Integer> dwarfLevel3 = new HashMap<>();
        dwarfLevel3.put("ATK", 4);
        return dwarfLevel3;
    }
    private static HashMap<String, Integer> dwarfLevel4() {
        HashMap<String, Integer> dwarfLevel4 = new HashMap<>();
        dwarfLevel4.put("DEF", 2);
        return dwarfLevel4;
    }
    private static HashMap<String, Integer> dwarfLevel5() {
        HashMap<String, Integer> dwarfLevel5 = new HashMap<>();
        dwarfLevel5.put("CHA", 1);
        return dwarfLevel5;
    }

    private static HashMap<Integer, HashMap<String, Integer>> adventurerLevels() {
        HashMap<Integer, HashMap<String, Integer>> adventurerLevels = new HashMap<>();
        adventurerLevels.put(1, adventurerLevel1());
        adventurerLevels.put(2, adventurerLevel2());
        adventurerLevels.put(3, adventurerLevel3());
        adventurerLevels.put(4, adventurerLevel4());
        adventurerLevels.put(5, adventurerLevel5());
        return adventurerLevels;
    }
    private static HashMap<Integer, HashMap<String, Integer>> archerLevels() {
        HashMap<Integer, HashMap<String, Integer>> archerLevels = new HashMap<>();
        archerLevels.put(1, archerLevel1());
        archerLevels.put(2, archerLevel2());
        archerLevels.put(3, archerLevel3());
        archerLevels.put(4, archerLevel4());
        archerLevels.put(5, archerLevel5());
        return archerLevels;
    }
    private static HashMap<Integer, HashMap<String, Integer>> dwarfLevels() {
        HashMap<Integer, HashMap<String, Integer>> dwarfLevels = new HashMap<>();
        dwarfLevels.put(1, dwarfLevel1());
        dwarfLevels.put(2, dwarfLevel2());
        dwarfLevels.put(3, dwarfLevel3());
        dwarfLevels.put(4, dwarfLevel4());
        dwarfLevels.put(5, dwarfLevel5());
        return dwarfLevels;
    }

    public static boolean addXp(player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();
        boolean leveledUp = false;
        while (newLevel > currentLevel) {
            leveledUp = true;
            currentLevel++;
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length)]);
            HashMap<String, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass()).get(currentLevel);
            abilities.forEach((ability, level) -> {
                player.abilities.put(ability, abilities.get(ability));
            });
            ;
        }
        return leveledUp;
    }

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(player player) {
        if(player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }
        if(isUnderHalfHealth(player)) {
          healPlayer(player);
        }
        if(player.currenthealthpoints >= player.healthpoints) {
             player.currenthealthpoints = player.healthpoints;
            return;
        }
    }

    public static boolean isUnderHalfHealth(player player) {
        return player.currenthealthpoints < player.healthpoints/2;
    }

    public static void healPlayer(player player) {
        switch(player.getAvatarClass()) {
            case ADVENTURER:
              player.currenthealthpoints+=2;
              if(player.retrieveLevel() < 3) {
                player.currenthealthpoints-=1;
              }
                break;
            case ARCHER:
                player.currenthealthpoints++;
                if(player.inventory.contains("Magic Bow")) {
                    player.currenthealthpoints += player.currenthealthpoints/8-1;
                }
                break;
            case DWARF:
                if(player.inventory.contains("Holy Elixir")) {
                  player.currenthealthpoints+=1;
                }
                player.currenthealthpoints+=1;
                break;
            default:
                break;
        }
    }
}
