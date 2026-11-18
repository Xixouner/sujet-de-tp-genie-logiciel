package re.forestier.edu.rpg;
import java.util.HashMap;

public class Stats {
  int DEF;
  int ATK;
  int CHA;
  int INT;
  int ALC;
  int VIS;

  public Stats(int DEF, int ATK, int CHA, int INT, int ALC, int VIS) {
    this.DEF = DEF;
    this.ATK = ATK;
    this.CHA = CHA;
    this.INT = INT;
    this.ALC = ALC;
    this.VIS = VIS;
  }
  public Stats(Stats other) {
    this(other.DEF, other.ATK, other.CHA, other.INT, other.ALC, other.VIS);
  }
  public HashMap<String, Integer> getStatsAsHashMap() {
    HashMap<String, Integer> statsMap = new HashMap<>();
    statsMap.put("DEF", DEF);
    statsMap.put("ATK", ATK);
    statsMap.put("CHA", CHA);
    statsMap.put("INT", INT);
    statsMap.put("ALC", ALC);
    statsMap.put("VIS", VIS);
    return statsMap;
  }
  public int getDEF() {
    return DEF;
  }
  public int getATK() {
    return ATK;
  }
  public int getCHA() {
    return CHA;
  }
  public int getINT() {
    return INT;
  }
  public int getALC() {
    return ALC;
  }
  public int getVIS() {
    return VIS;
  }
}
