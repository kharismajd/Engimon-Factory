public class player_test {
    public static void main (String args[]) {
        try {
            engimon null_engimon = new wild_engimon();
            player_engimon dad = new player_engimon("Dad", null_engimon, null_engimon, "Lapras", 32, 100);
            player_engimon test = new player_engimon("test1", null_engimon, null_engimon, "Vulpichu", 32, 100);
            player_engimon test2 = new player_engimon("test2", null_engimon, null_engimon, "Lapras", 40, 100);
            player_engimon test3 = new player_engimon("test3", null_engimon, null_engimon, "Lapras", 80, 100);
            player newp = new player("Test", dad, 50, 0, 0, 0, 1);

            newp.addInventoryContent(test);
            newp.addInventoryContent(test2);
            newp.addInventoryContent(test3);
            newp.showEngimonList();
            newp.deleteActiveEngimon();
            newp.showEngimonList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}