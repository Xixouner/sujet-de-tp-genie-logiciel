package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherJoueur(player player) {
        final String[] finalString = {"Joueur " + player.Avatar_name + " joué par " + player.playerName};
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        player.inventory.forEach(item -> {
            finalString[0] += "\n   " + item;
        });

        return finalString[0];
    }
    public static String afficherJoueurMarkdown(player player) {
        final String[] finalString = {"# Joueur " + player.Avatar_name + " joué par " + player.playerName};
        finalString[0] += "\n\n## Niveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\n## Capacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n- **" + name + "** : " + level;
        });
        finalString[0] += "\n\n## Inventaire :";
        player.inventory.forEach(item -> {
            finalString[0] += "\n- **" + item.getName() + "** : " + item.getDescription() + " (Poids : " + item.getWeight() + ", Valeur : " + item.getValue() + ")";
        });

        return finalString[0];
    }
}
