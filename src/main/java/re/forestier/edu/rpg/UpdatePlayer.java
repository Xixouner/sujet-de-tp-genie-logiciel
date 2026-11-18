package re.forestier.edu.rpg;

import java.util.Random;

public class UpdatePlayer {

    private final static ObjectClass[] objectList = {new ObjectClass("Lookout Ring", "Prevents surprise attacks", 50, 100), new ObjectClass("Scroll of Stupidity", "INT-2 when applied to an enemy", 25, 50), new ObjectClass("Draupnir", "Increases XP gained by 100%", 100, 200), new ObjectClass("Magic Charm", "Magic +10 for 5 rounds", 75, 150), new ObjectClass("Rune Staff of Curse", "May burn your ennemies... Or yourself. Who knows?", 125, 250), new ObjectClass("Combat Edge", "Well, that's an edge", 60, 120), new ObjectClass("Holy Elixir", "Recover your HP", 80, 160)
    };

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
            Stats abilities = AvatarStats.forLevel(player.getAvatarClass(), currentLevel);
            player.abilities = new Stats(abilities);
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
