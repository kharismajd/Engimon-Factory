// breedertest.java

public class breeder_test {
    public static void main (String args[]) {
        try {
            engimon daddad = new wild_engimon();
            engimon mommom = new wild_engimon();
            player_engimon dad = new player_engimon("Dad", daddad, mommom, "Vulpichu", 4, 100);
            player_engimon mom = new player_engimon("Mom", daddad, mommom, "Charmander", 5, 200);
            // Water, Ice, Water x Ice, Water x Ground

            dad.learnMove("tackle");
            //dad.learnMove("bubble");
            //dad.learnMove("ice_beam");
            mom.learnMove("tackle");
            //mom.learnMove("bubble");
            //mom.learnMove("rock_smash");

            // dad.showAttributes();
            // System.out.println();
            // mom.showAttributes();
            // System.out.println();

            //player_engimon childe = breeder.breed("Childe", dad, mom);
            //System.out.println();
            //for (int i = 0; i < 4; i++) {
            //    if (childe.getMove(i) != null) {
            //        childe.getMove(i).printAll();
            //        System.out.println();
            //    }
            //}
            //engimon childe = breeder.breed("Childe", dad, mom);
            //System.out.println();
            //for (int i = 0; i < 4; i++) {
            //    if (childe.getMove(i) != null) {
            //        childe.getMove(i).printAll();
            //        System.out.println();
            //    }
            //}

            // System.out.println();
            // dad.showAttributes();
            // System.out.println();
            // mom.showAttributes();
            // System.out.println();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}