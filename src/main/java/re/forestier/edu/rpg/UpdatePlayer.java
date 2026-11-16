package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

import re.forestier.edu.rpg.player.AvatarClassAvailable;

public class UpdatePlayer {

    private final static ObjectClass[] objectList = {new ObjectClass("Lookout Ring", "Prevents surprise attacks", 50, 100), new ObjectClass("Scroll of Stupidity", "INT-2 when applied to an enemy", 25, 50), new ObjectClass("Draupnir", "Increases XP gained by 100%", 100, 200), new ObjectClass("Magic Charm", "Magic +10 for 5 rounds", 75, 150), new ObjectClass("Rune Staff of Curse", "May burn your ennemies... Or yourself. Who knows?", 125, 250), new ObjectClass("Combat Edge", "Well, that's an edge", 60, 120), new ObjectClass("Holy Elixir", "Recover your HP", 80, 160)
    };

    /**
     * Retourne un HashMap contenant les capacités disponibles pour chaque classe d'avatar et chaque niveau.
     *
     * @return Un HashMap où la clé est une instance de AvatarClassAvailable représentant la classe d'avatar,
     * et la valeur est un autre HashMap où la clé est le niveau (Integer) et la valeur est un HashMap des capacités
     * (String) et de leurs niveaux (Integer).
     */
    public static HashMap<AvatarClassAvailable, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        HashMap<AvatarClassAvailable, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();

        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.ADVENTURER, adventurerLevels());
        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.ARCHER, archerLevels());
        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.DWARF, dwarfLevels());
        abilitiesPerTypeAndLevel.put(AvatarClassAvailable.GOBLIN, goblinLevels());

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

    private static HashMap<String,Integer> goblinLevel1() {
        HashMap<String, Integer> goblinLevel1 = new HashMap<>();
        goblinLevel1.put("INT", 2);
        goblinLevel1.put("ATK", 2);
        goblinLevel1.put("ALC", 1);
        return goblinLevel1;
    }
    private static HashMap<String,Integer> goblinLevel2() {
        HashMap<String, Integer> goblinLevel2 = new HashMap<>();
        goblinLevel2.put("ATK", 3);
        goblinLevel2.put("ALC", 4);
        return goblinLevel2;
    }
    private static HashMap<String,Integer> goblinLevel3() {
        HashMap<String, Integer> goblinLevel3 = new HashMap<>();
        goblinLevel3.put("VIS", 1);
        return goblinLevel3;
    }
    private static HashMap<String,Integer> goblinLevel4() {
        HashMap<String, Integer> goblinLevel4 = new HashMap<>();
        goblinLevel4.put("DEF", 1);
        return goblinLevel4;
    }
    private static HashMap<String,Integer> goblinLevel5() {
        HashMap<String, Integer> goblinLevel5 = new HashMap<>();
        goblinLevel5.put("ATK", 4);
        goblinLevel5.put("DEF", 2);
        return goblinLevel5;
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
    private static HashMap<Integer, HashMap<String, Integer>> goblinLevels() {
        HashMap<Integer, HashMap<String, Integer>> goblinLevels = new HashMap<>();
        goblinLevels.put(1, goblinLevel1());
        goblinLevels.put(2, goblinLevel2());
        goblinLevels.put(3, goblinLevel3());
        goblinLevels.put(4, goblinLevel4());
        goblinLevels.put(5, goblinLevel5());
        return goblinLevels;
    }

    /**
     * Ajoute de l'expérience (XP) à un joueur et met à jour son niveau et ses capacités si nécessaire.
     *
     * @param player Le joueur auquel ajouter de l'expérience.
     * @param xp     La quantité d'expérience à ajouter.
     * @return true si le joueur a monté de niveau, false sinon.
     */
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

    /**
     * Met à jour l'état du joueur à la fin d'un tour.
     * Si le joueur est KO (points de vie à 0), un message est affiché.
     * Si le joueur a moins de la moitié de ses points de vie, il est soigné en fonction de sa classe.
     * Si les points de vie du joueur dépassent sa santé maximale, ils sont ramenés à la santé maximale.
     *
     * @param player Le joueur dont l'état doit être mis à jour.
     */
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

    /**
     * Vérifie si le joueur a moins de la moitié de ses points de vie.
     *
     * @param player Le joueur à vérifier.
     * @return true si le joueur a moins de la moitié de ses points de vie, false sinon.
     */
    public static boolean isUnderHalfHealth(player player) {
        return player.currenthealthpoints < player.healthpoints/2;
    }

    /**
     * Soigne le joueur en fonction de sa classe.
     * Les aventuriers récupèrent 2 points de vie (1 si leur niveau est inférieur à 3).
     * Les archers récupèrent 1 point de vie, et un bonus si ils possèdent l'objet "Magic Bow".
     * Les nains récupèrent 1 point de vie, et un bonus si ils possèdent l'objet "Holy Elixir".
     *
     * @param player Le joueur à soigner.
     */
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
                if(player.hasObject("Magic Bow")) {
                    player.currenthealthpoints += player.currenthealthpoints/8-1;
                }
                break;
            case DWARF:
                if(player.hasObject("Holy Elixir")) {
                  player.currenthealthpoints+=1;
                }
                player.currenthealthpoints+=1;
                break;
            default:
                break;
        }
    }
}
