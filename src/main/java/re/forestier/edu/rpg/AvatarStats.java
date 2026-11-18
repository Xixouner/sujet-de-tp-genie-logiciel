package re.forestier.edu.rpg;
import re.forestier.edu.rpg.player.AvatarClassAvailable;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public final class AvatarStats {

  private static final Map<AvatarClassAvailable, Map<Integer, Stats>> ALL_STATS =createAllStats();

  public static Stats forLevel(AvatarClassAvailable avatarClass, int level) {
    return new Stats(ALL_STATS.get(avatarClass).get(level));
  }
  private static Map<AvatarClassAvailable, Map<Integer, Stats>> createAllStats() {
    Map<AvatarClassAvailable,Map<Integer, Stats>> map = new HashMap<>();
    map.put(AvatarClassAvailable.ARCHER, archerLevels());
    map.put(AvatarClassAvailable.ADVENTURER, adventurerLevels());
    map.put(AvatarClassAvailable.DWARF, dwarfLevels());
    map.put(AvatarClassAvailable.GOBLIN, goblinLevels());
    return map;
  }

  private static Map<Integer,Stats> levelsStats(List<Stats> abilities) {
      Map<Integer, Stats> Levels = new HashMap<>();
      for (int i = 1; i <= abilities.size(); i++) {
          Levels.put(i, abilities.get(i - 1));
      }
      return Levels;
  }
  private static Map<Integer, Stats> adventurerLevels() {
      return levelsStats(List.of(new Stats(1, 3, 2, 1, 0, 0),
        new Stats(1, 3, 3, 2, 0, 0), new Stats(1, 5, 3, 2, 1, 0),
        new Stats(3, 5, 3, 2, 1, 0), new Stats(4, 5, 3, 2, 1, 1)));
  }
  private static Map<Integer, Stats> archerLevels() {
      return levelsStats(List.of(new Stats(0, 3, 1, 1, 0, 3),
        new Stats(1, 3, 2, 1, 0, 3), new Stats(1, 3, 2, 1, 0, 3),
        new Stats(2, 3, 2, 1, 0, 3), new Stats(2, 4, 2, 1, 0, 3)));
  }
  private static Map<Integer, Stats> dwarfLevels() {
      return levelsStats(List.of(new Stats(0, 3, 0, 1, 4, 0),
        new Stats(1, 3, 0, 1, 5, 0), new Stats(1, 4, 0, 1, 5, 0),
        new Stats(2, 4, 0, 1, 5, 0), new Stats(2, 4, 1, 1, 5, 0)));
  }
  private static Map<Integer, Stats> goblinLevels() {
      return levelsStats(List.of(new Stats(0, 2, 0, 2, 1, 0),
        new Stats(0, 3, 0, 2, 4, 0), new Stats(0, 3, 0, 2, 4, 1),
        new Stats(1, 3, 0, 2, 4, 1), new Stats(2, 4, 0, 2, 4, 1)));
  }

}
