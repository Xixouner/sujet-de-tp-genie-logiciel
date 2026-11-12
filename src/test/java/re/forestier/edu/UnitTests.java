package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.player.AvatarClassAvailable;
import re.forestier.edu.rpg.UpdatePlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName ("test de l'ajout des capacités quand on ajoute de l'xp")
    void testAddAbilitiesWhenAddingXp(){
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 11);
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("ALC"), is(5));
    }

    @Test
    @DisplayName ("test de l'ajout d'un item quand on ajoute de l'xp")
    void testAddItemWhenAddingXp(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 11);
        assertThat(p.inventory, hasSize(1));
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
    @DisplayName("newlevel different de currentlevel dans addxp")
    void testAddXpLevelChange(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        boolean changelevel =UpdatePlayer.addXp(p, 11);
        assertThat(changelevel, is(true));
    }
    @Test
    @DisplayName ("test dans addxp quand on passe à un niveau superieur ajout dans l'inventaire")
    void testAddXpLevelChangeInventory(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        ArrayList<String> inventoryBefore = new ArrayList<>(p.inventory);
        UpdatePlayer.addXp(p, 11);
        ArrayList<String> inventoryAfter = new ArrayList<>(p.inventory);
        assertThat(inventoryAfter, is(not(inventoryBefore)));
    }
    @Test
    @DisplayName ("newlevel equal to currentlevel dans addxp")
    void testAddXpNoLevelChange(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        boolean changelevel =UpdatePlayer.addXp(p, 5);
        assertThat(changelevel, is(false));
    }

    @Test
    @DisplayName("ajouter de l'argent")
    void testchangeMoney(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.changeMoney(20);
        assertThat(p.money,is(120));
        p.changeMoney(-20);
        assertThat(p.money,is(100));
    }

    @Test
    @DisplayName("monnaie egale à 0")
    void testchangeMoneyZero(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        p.changeMoney(-100);
        assertThat(p.money, is(0));
    }

    @Test
    @DisplayName("tester les classes")
    void testClass() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is(AvatarClassAvailable.ADVENTURER));
        player p2 = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        assertThat(p2.getAvatarClass(), is(AvatarClassAvailable.ARCHER));
        player p3 = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        assertThat(p3.getAvatarClass(), is(AvatarClassAvailable.DWARF));
    }
    @Test
    @DisplayName ("test l'affichage si le joueur est mort")
    void testDisplayPlayerDead() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        p.currenthealthpoints = 0;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(output));
            UpdatePlayer.majFinDeTour(p);
        } finally {
            System.setOut(originalOut);
        }

        assertThat(output.toString(), containsString("Le joueur est KO !"));
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
    @DisplayName ("test de la fonction majFinDeTour")
    void testMajFinDeTour() {
        testMajFinDeTourKO();
        testMajFinDeTourDwarfMidLife();
    }
    @Test
    @DisplayName("test maj fin de tour avec un aventurier de niveau 1")
    void testMajFinDeTourAdventurerLevel1() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        p.currenthealthpoints = 40;
        p.healthpoints = 100;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(41));
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
    @Test
    @DisplayName ("tester la fonction majFinDeTour avec un aventurier mais à moitié vie")
    void testMajFinDeTourAdventurerMidLife() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 70;
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
    @DisplayName ("tester la fonction majFinDeTour avec un aventurier mais avec plus dev vie actuelle que sa vie max")
    void testMajFinDeTourAdventurerOverHealed() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 160;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName("test maj fin de tour avec un archer à moitié de vie exactement")
    void testMajFinDeTourArcherHalfLife() {
        player p = new player("Florian","Grognak le barbare","ARCHER",100,new ArrayList<>());
        p.currenthealthpoints = 75;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(75));
        p.currenthealthpoints = 74;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(75));
        p.currenthealthpoints = 74;
        p.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(83));
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
    @DisplayName("test maj fin de tour avec un nain à moitié de vie exactement")
    void testMajFinDeTourDwarfHalfLife() {
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        p.currenthealthpoints = 75;
        p.healthpoints = 150;
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(75));
        p.currenthealthpoints = 74;
        p.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(76));
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
    @DisplayName("tester la fonction majFinDeTour avec un joueur mort")
    void testMajFinDeTourKO() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(p);
    }
    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.changeMoney(-200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }
    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }
    @Test
    @DisplayName ("test de la fonction retrieveLevel() pour un joueur qui n'a pas encore gagné d'xp")
    void testRetrieveLevelAtLevelBeforeTransition(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        boolean isLevel1 = p.retrieveLevel() == 1;
        assertThat(isLevel1, is(true));
    }
    @Test
    @DisplayName ("test de la fonction retrieveLevel() pour un joueur passant pile au niveau 1")
    void testRetrieveLevelAtLevelTransition1to2(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        boolean isLevel1 = p.retrieveLevel() == 1;
        assertThat(isLevel1, is(false));
    }
    @Test
    @DisplayName ("test de la fonction retrieveLevel() pour un joueur passant pile au niveau 2")
    void testRetrieveLevelAtLevelTransition2to3(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 27);
        boolean isLevel2 = p.retrieveLevel() == 2;
        assertThat(isLevel2, is(false));
    }
    @Test
    @DisplayName ("test de la fonction retrieveLevel() pour un joueur passant pile au niveau 3")
    void testRetrieveLevelAtLevelTransition3to4(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 57);
        boolean isLevel3 = p.retrieveLevel() == 3;
        assertThat(isLevel3, is(false));
    }
    @Test
    @DisplayName ("test de la fonction retrieveLevel() pour un joueur passant pile au niveau 4")
    void testRetrieveLevelAtLevelTransition4to5(){
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 111);
        boolean isLevel4 = p.retrieveLevel() == 4;
        assertThat(isLevel4, is(false));
    }
    @Test
    @DisplayName ("test d'aventurier level 1 et les abilités qu'il a au départ")
    void testAdventurerLevel1Abilities() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("CHA"), is(2));
    }
    @Test
    @DisplayName ("test d'aventurier level 2 et les abilités qu'il a après avoir gagné de l'xp")
    void testAdventurerLevel2Abilities() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 11);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("CHA"), is(3));
    }
    @Test
    @DisplayName ("test d'aventurier level 3 et les abilités qu'il a après avoir gagné de l'xp")
    void testAdventurerLevel3Abilities() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        assertThat(p.abilities.get("DEF"), is(1));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("ALC"), is(1));
    }
     @Test
    @DisplayName ("test d'aventurier level 4 et les abilités qu'il a après avoir gagné de l'xp")
    void testAdventurerLevel4Abilities() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        UpdatePlayer.addXp(p, 30);
        assertThat(p.abilities.get("DEF"), is(3));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("ALC"), is(1));
    }
    @Test
    @DisplayName ("test d'aventurier level 5 et les abilités qu'il a après avoir gagné de l'xp")
    void testAdventurerLevel5Abilities() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        UpdatePlayer.addXp(p, 30);
        UpdatePlayer.addXp(p, 54);
        assertThat(p.abilities.get("DEF"), is(4));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("ALC"), is(1));
        assertThat(p.abilities.get("VIS"), is(1));
    }
    @Test
    @DisplayName ("test d'archer level 1 et les abilités qu'il a au départ")
    void testArcherLevel1Abilities() {
        player p = new player("Florian","Grognak le barbare","ARCHER",100,new ArrayList<>());
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("CHA"), is(1));
        assertThat(p.abilities.get("VIS"), is(3));
    }
    @Test
    @DisplayName ("test d'archer level 2 et les abilités qu'il a après avoir gagné de l'xp")
    void testArcherLevel2Abilities() {
        player p = new player("Florian","Grognak le barbare","ARCHER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 11);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("CHA"), is(2));
        assertThat(p.abilities.get("VIS"), is(3));
        assertThat(p.abilities.get("DEF"), is(1));
    }
    @Test
    @DisplayName ("test d'archer level 3 et les abilités qu'il a après avoir gagné de l'xp")
    void testArcherLevel3Abilities() {
        player p = new player("Florian","Grognak le barbare","ARCHER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("CHA"), is(2));
        assertThat(p.abilities.get("VIS"), is(3));
        assertThat(p.abilities.get("DEF"), is(1));
    }
    @Test
    @DisplayName ("test d'archer level 4 et les abilités qu'il a après avoir gagné de l'xp")
    void testArcherLevel4Abilities() {
        player p = new player("Florian","Grognak le barbare","ARCHER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        UpdatePlayer.addXp(p, 30);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("CHA"), is(2));
        assertThat(p.abilities.get("VIS"), is(3));
        assertThat(p.abilities.get("DEF"), is(2));
    }
    @Test
    @DisplayName ("test d'archer level 5 et les abilités qu'il a après avoir gagné de l'xp")
    void testArcherLevel5Abilities() {
        player p = new player("Florian","Grognak le barbare","ARCHER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        UpdatePlayer.addXp(p, 30);
        UpdatePlayer.addXp(p, 54);
        assertThat(p.abilities.get("ATK"), is(4));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("CHA"), is(2));
        assertThat(p.abilities.get("VIS"), is(3));
        assertThat(p.abilities.get("DEF"), is(2));
    }
    @Test
    @DisplayName ("test de nain level 1 et les abilités qu'il a au départ")
    void testDwarfLevel1Abilities() {
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("ALC"), is(4));
    }
    @Test
    @DisplayName ("test de nain level 2 et les abilités qu'il a après avoir gagné de l'xp")
    void testDwarfLevel2Abilities() {
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 11);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("ALC"), is(5));
        assertThat(p.abilities.get("DEF"), is(1));
    }
    @Test
    @DisplayName ("test de nain level 3 et les abilités qu'il a après avoir gagné de l'xp")
    void testDwarfLevel3Abilities() {
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        assertThat(p.abilities.get("ATK"), is(4));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("ALC"), is(5));
        assertThat(p.abilities.get("DEF"), is(1));
    }
    @Test
    @DisplayName ("test de nain level 4 et les abilités qu'il a après avoir gagné de l'xp")
    void testDwarfLevel4Abilities() {
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        UpdatePlayer.addXp(p, 30);
        assertThat(p.abilities.get("ATK"), is(4));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("ALC"), is(5));
        assertThat(p.abilities.get("DEF"), is(2));
    }
    @Test
    @DisplayName ("test de nain level 5 et les abilités qu'il a après avoir gagné de l'xp")
    void testDwarfLevel5Abilities() {
        player p = new player("Florian","Grognak le barbare","DWARF",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 10);
        UpdatePlayer.addXp(p, 17);
        UpdatePlayer.addXp(p, 30);
        UpdatePlayer.addXp(p, 54);
        assertThat(p.abilities.get("ATK"), is(4));
        assertThat(p.abilities.get("INT"), is(1));
        assertThat(p.abilities.get("ALC"), is(5));
        assertThat(p.abilities.get("DEF"), is(2));
        assertThat(p.abilities.get("CHA"), is(1));
    }
    @Test
    @DisplayName("Passer plusieurs niveaux d'un coup et vérifier que les abilités sont correctes")
    void testMultipleLevelUpAbilities() {
        player p = new player("Florian","Grognak le barbare","ADVENTURER",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 100); // devrait passer du niveau 1 au niveau 4
        assertThat(p.abilities.get("DEF"), is(3));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("CHA"), is(3));
        assertThat(p.abilities.get("ATK"), is(5));
        assertThat(p.abilities.get("ALC"), is(1));
    }
    @Test
    @DisplayName("Gobelin level 1 abilities")
    void testGoblinLevel1Abilities() {
        player p = new player("Florian","Grognak le barbare","GOBLIN",100,new ArrayList<>());
        assertThat(p.abilities.get("ATK"), is(2));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("ALC"), is(1));
    }
    @Test
    @DisplayName("Gobelin level 2 abilities")
    void testGoblinLevel2Abilities() {
        player p = new player("Florian","Grognak le barbare","GOBLIN",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 11);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("ALC"), is(4));
    }
    @Test
    @DisplayName("Gobelin level 3 abilities")
    void testGoblinLevel3Abilities() {
        player p = new player("Florian","Grognak le barbare","GOBLIN",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 27);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("ALC"), is(4));
        assertThat(p.abilities.get("VIS"), is(1));
    }
    @Test
    @DisplayName("Gobelin level 4 abilities")
    void testGoblinLevel4Abilities() {
        player p = new player("Florian","Grognak le barbare","GOBLIN",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 57);
        assertThat(p.abilities.get("ATK"), is(3));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("ALC"), is(4));
        assertThat(p.abilities.get("VIS"), is(1));
        assertThat(p.abilities.get("DEF"), is(1));
    }
    @Test
    @DisplayName("Gobelin level 5 abilities")
    void testGoblinLevel5Abilities() {
        player p = new player("Florian","Grognak le barbare","GOBLIN",100,new ArrayList<>());
        UpdatePlayer.addXp(p, 111);
        assertThat(p.abilities.get("ATK"), is(4));
        assertThat(p.abilities.get("INT"), is(2));
        assertThat(p.abilities.get("ALC"), is(4));
        assertThat(p.abilities.get("VIS"), is(1));
        assertThat(p.abilities.get("DEF"), is(2));
    }
}
