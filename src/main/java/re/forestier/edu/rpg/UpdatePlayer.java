package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;
import java.util.List;

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
        return new Stats(1, 3, 2, 1, 0, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> adventurerLevel2() {
      return new Stats(1, 3, 3, 2, 0, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> adventurerLevel3() {
      return new Stats(1, 5, 3, 2, 1, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> adventurerLevel4() {
      return new Stats(3, 5, 3, 2, 1, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> adventurerLevel5() {
      return new Stats(4, 5, 3, 2, 1, 1).getStatsAsHashMap();
    }

    private static HashMap<String, Integer> archerLevel1() {
        return new Stats(0, 3, 1, 1, 0, 3).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> archerLevel2() {
        return new Stats(1, 3, 2, 1, 0, 3).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> archerLevel3() {
        return new Stats(1, 3, 2, 1, 0, 3).getStatsAsHashMap(); // ne sert à rien, mais c'est pour montrer que l'archer est plus fort que les autres classes
    }
    private static HashMap<String, Integer> archerLevel4() {
        return new Stats(2, 3, 2, 1, 0, 3).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> archerLevel5() {
        return new Stats(2, 4, 2, 1, 0, 3).getStatsAsHashMap();
    }

    private static HashMap<String, Integer> dwarfLevel1() {
        return new Stats(0,3,0,1,4,0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> dwarfLevel2() {
        return new Stats(1, 3, 0, 1, 5, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> dwarfLevel3() {
        return new Stats(1, 4, 0, 1, 5, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> dwarfLevel4() {
        return new Stats(2, 4, 0, 1, 5, 0).getStatsAsHashMap();
    }
    private static HashMap<String, Integer> dwarfLevel5() {
        return new Stats(2, 4, 1, 1, 5, 0).getStatsAsHashMap();
    }

    private static HashMap<String,Integer> goblinLevel1() {
        return new Stats(0, 2, 0, 2, 1, 0).getStatsAsHashMap();
    }
    private static HashMap<String,Integer> goblinLevel2() {
        return new Stats(0, 3, 0, 2, 4, 0).getStatsAsHashMap();
    }
    private static HashMap<String,Integer> goblinLevel3() {
        return new Stats(0, 3, 0, 2, 4, 1).getStatsAsHashMap();
    }
    private static HashMap<String,Integer> goblinLevel4() {
        return new Stats(1, 3, 0, 2, 4, 1).getStatsAsHashMap();
    }
    private static HashMap<String,Integer> goblinLevel5() {
        return new Stats(2, 4, 0, 2, 4, 1).getStatsAsHashMap();
    }

    private static HashMap<Integer, HashMap<String, Integer>> levels(List<HashMap<String,Integer>> abilities) {
        HashMap<Integer, HashMap<String, Integer>> Levels = new HashMap<>();
        for (int i = 1; i <= abilities.size(); i++) {
            Levels.put(i, abilities.get(i - 1));
        }
        return Levels;

    }
    private static HashMap<Integer, HashMap<String, Integer>> adventurerLevels() {
        return levels(List.of(adventurerLevel1(), adventurerLevel2(), adventurerLevel3(), adventurerLevel4(), adventurerLevel5()));
    }
    private static HashMap<Integer, HashMap<String, Integer>> archerLevels() {
        return levels(List.of(archerLevel1(), archerLevel2(), archerLevel3(), archerLevel4(), archerLevel5()));
    }
    private static HashMap<Integer, HashMap<String, Integer>> dwarfLevels() {
        return levels(List.of(dwarfLevel1(), dwarfLevel2(), dwarfLevel3(), dwarfLevel4(), dwarfLevel5()));
    }
    private static HashMap<Integer, HashMap<String, Integer>> goblinLevels() {
        return levels(List.of(goblinLevel1(), goblinLevel2(), goblinLevel3(), goblinLevel4(), goblinLevel5()));
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
