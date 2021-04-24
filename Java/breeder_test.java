// breedertest.java

public class breeder_test {
    public static void main (String args[]) {
        try {
            engimon daddad = new wild_engimon();
            engimon mommom = new wild_engimon();
            engimon dad = new player_engimon("Dad", daddad, mommom, "Lapras", 32, 100);
            engimon mom = new player_engimon("Mom", daddad, mommom, "Wooper", 33, 200);
            // Water, Ice, Water x Ice, Water x Ground

            dad.learnMove("tackle");
            dad.learnMove("bubble");
            dad.learnMove("ice_beam");
            mom.learnMove("tackle");
            mom.learnMove("bubble");
            mom.learnMove("rock_smash");

            // dad.showAttributes();
            // System.out.println();
            // mom.showAttributes();
            // System.out.println();

            engimon childe = breeder.breed("Childe", dad, mom);
            System.out.println();
            for (int i = 0; i < 4; i++) {
                if (childe.getMove(i) != null) {
                    childe.getMove(i).printAll();
                    System.out.println();
                }
            }

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