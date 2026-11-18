package re.forestier.edu.rpg;

import java.util.ArrayList;

public class player {
  public enum AvatarClassAvailable {
      ARCHER,
      ADVENTURER,
      DWARF,
      GOBLIN
  }
    public String playerName;
    public String Avatar_name;
    private  AvatarClassAvailable AvatarClass;

    public int money;

    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;
    public Stats abilities;
    public ArrayList<ObjectClass> inventory;
    /**
     * Constructeur de la classe player.
     *
     * @param playerName  Le nom du joueur.
     * @param avatar_name Le nom de l'avatar du joueur.
     * @param avatarClass La classe de l'avatar (doit être une valeur valide de AvatarClassAvailable).
     * @param money       La quantité d'argent du joueur (doit être positive).
     * @param inventory   L'inventaire du joueur (doit être une liste d'objets ObjectClass).
     * @throws IllegalArgumentException si la classe de l'avatar est invalide.
     */
    public player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<ObjectClass> inventory) {
      AvatarClassAvailable avatar;
      if (avatarClass == null) {
        return;
      }
      try{
        avatar = AvatarClassAvailable.valueOf(avatarClass);
      }
      catch(IllegalArgumentException e) {
        return;
      }
        this.playerName = playerName;
        this.Avatar_name = avatar_name;
        this.AvatarClass = avatar;
        if(money < 0){
          this.money = 0;
        }
        else{
          this.money = money;
        }
        this.inventory = inventory;
        this.abilities = AvatarStats.forLevel(avatar, 1);
    }

    public AvatarClassAvailable getAvatarClass () {
        return AvatarClass;
    }
    /**
     * Change la quantité d'argent du joueur.
     *
     * @param amount La quantité à ajouter (peut être négative pour retirer de l'argent).
     * @throws IllegalArgumentException si le montant total d'argent devient négatif.
     */
    public void changeMoney(int amount){
      if(money + amount < 0){
        throw new IllegalArgumentException("Player can't have a negative money!");
      }
      money += amount;
    }
    /**
     * Récupère le niveau actuel du joueur en fonction de son expérience (XP).
     * La methode de calcul (lvl-1) * 10 + round((lvl * xplvl-1)/4)
     * @return Le niveau actuel du joueur.
     */
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
    /**
     * Vend un objet du joueur.
     *
     * @param object L'objet à vendre.
     * @return true si l'objet a été vendu, false sinon.
     */
    public boolean sell(ObjectClass object) {
        if(!inventory.contains(object)) {
            return false;
        }
        inventory.remove(object);
        changeMoney(object.getValue());
        return true;
    }
    public boolean sell(String objectName) {
        for (ObjectClass object : inventory) {
            if (object.getName().equals(objectName)) {
                inventory.remove(object);
                changeMoney(object.getValue());
                return true;
            }
        }
        return false;
    }
    /**
     * Vérifie si le joueur possède un objet.
     *
     * @param objectName Le nom de l'objet à vérifier.
     * @return true si le joueur possède l'objet, false sinon.
     */
    public boolean hasObject(String objectName) {
        return inventory.stream()
                .anyMatch(object -> object.getName().equals(objectName));
    }

}
