package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.UpdatePlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
    @Test
    @DisplayName("peut-on correctement enlever la monnaie")
    void testRemoveMoney(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(20);
        assertThat(p.money,is(80));
    }
    @Test
    @DisplayName("ajouter de l'argent")
    void testAddMoney(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(20);
        assertThat(p.money,is(120));
        p.addMoney(-20);
        assertThat(p.money,is(100));
    }
    @Test
    @DisplayName("ajouter de l'xp")
    void testAddXP(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(p,20);
        assertThat(p.getXp(),is(20));
        assertThat(p.retrieveLevel(),is(2));
        UpdatePlayer.addXp(p,20);
        UpdatePlayer.addXp(p,1);
        assertThat(p.retrieveLevel(),is(3));
        UpdatePlayer.addXp(p,60);
        assertThat(p.retrieveLevel(),is(4));
        UpdatePlayer.addXp(p,100);
        assertThat(p.retrieveLevel(),is(5));
    }
    @Test
    @DisplayName ("prendre une classe qui n'existe pas")
    void testInvalidClass() {
        player p = new player("Florian", "Grognak le barbare", "INVALID_CLASS", 100, new ArrayList<>());
        assertThat(p, is(notNullValue()));
        assertThat(p.getAvatarClass(), is(nullValue()));
        assertThat(p.playerName, is(nullValue()));
    }
    @Test
    @DisplayName("tester les classes")
    void testClass() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is("ADVENTURER"));
        player p2 = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        assertThat(p2.getAvatarClass(), is("ARCHER"));
        player p3 = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        assertThat(p3.getAvatarClass(), is("DWARF"));
    }

    @Test
    @DisplayName ("test de la fonction majFinDeTour")
    void testMajFinDeTour() {
        testMajFinDeTourKO();
        testMajFinDeTourDwarfMidLife();
    }

    @Test
    @DisplayName("tester la fonction majFinDeTour avec un joueur mort")
    void testMajFinDeTourKO() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(p);
    }

    @Test
    @DisplayName("tester la fonction majFinDeTour avec un nain mais à moitié vie")
    void testMajFinDeTourDwarfMidLife() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 70;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName("tester la fonction majFinDeTour avec un nain mais à moitié vie et un elixir")
    void testMajFinDeTourDwarfMidLifeWithElixir() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 70;
        p.healthpoints = 150;
        p.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName("tester la fonction majFinDeTour avec un archer mais à moitié vie")
    void testMajFinDeTourArcherMidLife() {
        player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 70;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName ("tester la fonction majFinDeTour avec un archer mais à moitié vie et un arc magique")
    void testMajFinDeTourArcherMidLifeWithMagicBow() {
        player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.currenthealthpoints = 70;
        p.healthpoints = 150;
        p.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName ("tester la fonction majFinDeTour avec un aventurier mais à moitié vie")
    void testMajFinDeTourAdventurerMidLife() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 70;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
    }

    @Test
    @DisplayName ("tester la fonction majFinDeTour avec un aventurier mais avec plus dev vie actuelle que sa vie max")
    void testMajFinDeTourAdventurerOverHealed() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 160;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName ("tester la fonction majFinDeTour avec un aventurier mais avec plus de la moitié de sa vie actuelle que sa vie max")
    void testMajFinDeTourAdventurerOverHalfHealed() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 80;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
    }

    @Test
    @DisplayName("un aventurier niveau 3 récupère deux vies")
    void testMajFinDeTourAdventurerLevel3() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 27); // niveau 3
        p.healthpoints = 150;
        p.currenthealthpoints = 70;

        UpdatePlayer.majFinDeTour(p);

        assertThat(p.currenthealthpoints, is(72));
    }
}
