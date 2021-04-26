import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main   {
    player_engimon starter_engimon = null;
    player tempP;
    gameMap g;
    player P;
    MainVisualizer m;

    static void printHelp()
    {
        System.out.println("Commands: ");
        System.out.println("Move: w/a/s/d");
        System.out.println("Legend: l");
        System.out.println("Interact: z");
        System.out.println("Show Map: m");
        System.out.println("Breed: b");
        System.out.println("Show Engimon: e");
        System.out.println("Show Item: i");
        System.out.println("Use Skill Item: u");
        System.out.println("Switch Active Engimon: x");
        System.out.println("Inspect Engimon: n");
        System.out.println("Generate Engimon Manually: g");
    }

    static void resolveMove(player P, gameMap g, player tempP)
    {
        Integer winBattle;
        wild_engimon.setPlayerHighestPokemon(P.getHighestLevelEngimon());
        //spesies, move, element, level, power

        if (g.isTileOccupied(P.getPlayerPosX(),P.getPlayerPosY()))
        {
            wild_engimon enemy = new wild_engimon(g.getTileEngimon(P.getPlayerPosX(),P.getPlayerPosY()));
            Battle b = new Battle(P.getActiveEngimon(), new wild_engimon(g.getTileEngimon(P.getPlayerPosX(),P.getPlayerPosY())) , P);
            double[] power = b.power();

            JPanel panel = new JPanel();
            JPanel engimon1 = new JPanel();
            JPanel engimon2 = new JPanel();
            JPanel engimon1Move = skill_icons.showIcons(P.getActiveEngimon());
            JPanel engimon2Move = skill_icons.showIcons(enemy);
            JLabel engimon1Lbl = new JLabel();
            JLabel engimon2Lbl = new JLabel();
            JLabel moveLabel1 = new JLabel("<html>Move: </html>");
            JLabel moveLabel2 = new JLabel("<html>Move: </html>");

            String engimon1Desc = "Your engimon:<br/>";
            engimon1Desc += "Species: " + P.getActiveEngimon().getSpecies() + "<br/>";
            engimon1Desc += "Element: " + P.getActiveEngimon().getElmt1();
            if (P.getActiveEngimon().getElmt2() != null)
            {
                engimon1Desc += "/" + P.getActiveEngimon().getElmt2();
            }
            engimon1Desc += "       <br/>";
            engimon1Desc += "Level: " + P.getActiveEngimon().getLevel().toString() + "<br/>";
            engimon1Desc += "Power: " + power[0];

            String engimon2Desc = "Enemy engimon:<br/>";
            engimon2Desc += "Species: " + enemy.getSpecies() + "<br/>";
            engimon2Desc += "Element: " + enemy.getElmt1();
            if (enemy.getElmt2() != null)
            {
                engimon2Desc += "/" + enemy.getElmt2();
            }
            engimon2Desc += "       <br/>";
            engimon2Desc += "Level: " + enemy.getLevel().toString() + "<br/>";
            engimon2Desc += "Power: " + power[1];

            engimon1Lbl.setText("<html>" + engimon1Desc + "</html>");
            engimon2Lbl.setText("<html>" + engimon2Desc + "</html>");

            engimon1Lbl.setForeground(Color.white);
            moveLabel1.setForeground(Color.white);
            engimon2Lbl.setForeground(Color.white);
            moveLabel2.setForeground(Color.white);

            engimon1.setLayout(new GridLayout(1, 0));
            engimon1.add(engimon1Lbl, BorderLayout.EAST);
            engimon1.add(moveLabel1, BorderLayout.CENTER);
            engimon1.add(engimon1Move, BorderLayout.WEST);

            engimon2.setLayout(new GridLayout(1, 0));
            engimon2.add(engimon2Lbl, BorderLayout.EAST);
            engimon2.add(moveLabel2, BorderLayout.CENTER);
            engimon2.add(engimon2Move, BorderLayout.WEST);

            GridLayout layout = new GridLayout(2,1);
            layout.setVgap(20);
            panel.setLayout(layout);

            panel.add(engimon1);
            panel.add(engimon2);
            panel.setSize(600, 800);

            int LVbefore = P.getActiveEngimon().getLevel();
            int diffLv = enemy.getLevel() - P.getActiveEngimon().getLevel();
            int exp = (int)Math.floor((10*diffLv + 600)/(80-diffLv)+5);

            JLabel winmsglbl = new JLabel();
            String winmsg = "Kamu menang (^v^)b <br/>";
            winmsg += P.getActiveEngimon().getName() + " telah mendapatkan " + Integer.toString(exp) + "exp<br/>";
            int LVafter = P.getActiveEngimon().getLevel();
            if (LVafter > LVbefore){
                winmsg += P.getActiveEngimon().getName() + " telah naik level!<br/>";
            }
            String skill_item_get = P.getActiveEngimon().getMove(0).getSkillName();
            winmsg += "Anda mendapatkan skill item " + skill_item_get + "<br/>";
            if (P.getActiveEngimon().getCummulativeExp()>100000){
                winmsg += P.getActiveEngimon().getName() + " telah mencapai batas usia, ucapkan selamat tinggal (T_T)/<br/>";
            }
            winmsglbl.setText("<html><center>" + winmsg + "</html>");
            winmsglbl.setForeground(Color.white);

            JLabel losemsglbl = new JLabel();
            int life_after = P.getActiveEngimon().getLife()-1;
            String losemsg = "Kamu K4L4H!!<br/>";
            if (life_after == 0){
                losemsg += P.getActiveEngimon().getName() + " telah banyak menderita, kini saatnya dia untuk pergi (T_T)/<br/>";
            } else {
                losemsg += "Berhati-hatilah, Life " + P.getActiveEngimon().getName() + " tersisa " + life_after + "<br/>";
            }
            losemsglbl.setText("<html><center>" + losemsg + "</html>");
            losemsglbl.setForeground(Color.white);

            Object[] options = {"Battle!", "Nope"};
            int n = JOptionPane.showOptionDialog(null,
                    panel, "Battle",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            int win;
            if (n == 0)
            {
                win = b.initBattle();
            }
            else
            {
                win = -1;
            }

            if (win == 1)
            {
                Object[] options1 = {"OK"};
                int m = JOptionPane.showOptionDialog(null,
                        winmsglbl, "Win",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options1,
                        options1[0]);
                g.deleteTileEngimon(P.getPlayerPosX(), P.getPlayerPosY());
            }
            else if (win == 0)
            {
                Object[] options2 = {"OK"};
                int m = JOptionPane.showOptionDialog(null,
                        losemsglbl, "Lose",
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options2,
                        options2[0]);
                P.setPlayerPosX(tempP.getPlayerPosX());
                P.setPlayerPosY(tempP.getPlayerPosY());
                P.setActivePetPosX(tempP.getActivePetPosX());
                P.setActivePetPosY(tempP.getActivePetPosY());
            }
            else
            {
                P.setPlayerPosX(tempP.getPlayerPosX());
                P.setPlayerPosY(tempP.getPlayerPosY());
                P.setActivePetPosX(tempP.getActivePetPosX());
                P.setActivePetPosY(tempP.getActivePetPosY());
            }
        }



        try
        {
            g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY(), P);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        g.printMap();
    }

    public Main() {
        //Deklarasi
        String userInput;
        String starter_engimon_name;

        try
        {
             g = new gameMap("inputMapFile.txt");
//            g = new gameMap();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

//        System.out.println("Pilih engimon pertama anda");
//        System.out.println("- Charmander");
//        System.out.println("- Pikachu");
//        System.out.println("- Glastrier");
//        System.out.println("");
//        System.out.print("Engimon: ");
//
//        Scanner sc = new Scanner(System.in);
//        userInput = sc.nextLine();
//        System.out.println();
//
//        while (!("Charmander".equals(userInput) || "Pikachu".equals(userInput) || "Glastrier".equals(userInput)))
//        {
//            System.out.println("Species engimon tidak tepat, silahkan coba lagi");
//            System.out.println("- Charmander");
//            System.out.println("- Pikachu");
//            System.out.println("- Glastrier");
//            System.out.println("");
//            System.out.print("Engimon: ");
//
//            userInput = sc.nextLine();
//            System.out.println();
//        }
//
//        System.out.println("Please name your engimon:");
//        starter_engimon_name = sc.nextLine();
//
//        try
//        {
//            starter_engimon = new player_engimon(starter_engimon_name, null,null, userInput, 30, 0);
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("This is your starting engimon: ");
//        starter_engimon.showAttributes();
//
//        System.out.println("");
//        System.out.println("Now, please tell us your name");
//        System.out.print("Name: ");
//        userInput = sc.nextLine();
//        System.out.println();

        //player P = new player(userInput, starter_engimon, 50, g.getMapLength()/2, g.getMapWidth()/2, g.getMapLength()/2, g.getMapWidth()/2 + 1);
        try
        {
            starter_engimon = new player_engimon("Gab", null,null, "Pikachu", 30, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        P = new player("James", starter_engimon, 50, g.getMapLength()/2, g.getMapWidth()/2, g.getMapLength()/2, g.getMapWidth()/2 + 1);

        player_engimon starter_engimon2 = null;
        try
        {
            starter_engimon2 = new player_engimon("Breeder Tester", null, null, "Glastrier", 30, 0);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        P.addInventoryContent(starter_engimon2);
        System.out.println();
        System.out.println(P.getName()+ ", you are now ready, step in to the world of engimon");
        try
        {
            g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY(), P);
            g.generateEngimon();
            g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY(), P);
            g.printMap();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        m = new MainVisualizer(this);
        try
        {
            g.updateMap(P.getPlayerPosX(),P.getPlayerPosY(),P.getActivePetPosX(),P.getActivePetPosY(), P);
        }
        catch (Exception e)
        {

        }
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextLine();
        while (userInput !="exit")
        {
            //Lower userInput
            // cout <<"Player Position" << P.getPlayerPosX() <<"," << P.getPlayerPosY() << endl;
            // cout <<"Active Engimon Position" << P.getActivePetPosX() <<"," << P.getActivePetPosY() << endl;
            userInput = userInput.toLowerCase();
            if ("c".equals(userInput) || "command".equals(userInput) || "commands".equals(userInput) ||"h".equals(userInput) || "help".equals(userInput))
            {
                printHelp();
            }
            else if ("l".equals(userInput))
            {
                g.printLegend();
            }
            else if ("w".equals(userInput))
            {
                this.moveUp();

            }
            else if ("a".equals(userInput))
            {
                this.moveLeft();

            }
            else if ("s".equals(userInput))
            {
                this.moveDown();

            }
            else if ("d".equals(userInput))
            {
                this.moveRight();

            }
            else if ("z".equals(userInput))
            {
                P.Interact();
            }
            else if ("m".equals(userInput))
            {
                g.printMap();
                if (m.getState() != MainVisualizer.VISUALIZER_MAP)
                {
                    m.openMap();
                }
            }
            else if ("b".equals(userInput))
            {
                try
                {
                    P.breeding();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }

            }
            else if ("e".equals(userInput))
            {
                P.showEngimonList();
            }
            else if ("i".equals(userInput))
            {

                P.showSkillItemList();
            }
            else if ("u".equals(userInput))
            {
                try
                {
                    P.useSkillItem();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if ("x".equals(userInput))
            {
                P.switchOutEngimon();
                g.tile_map.elementAt(P.activeEngimon_y).elementAt(P.activeEngimon_x).setEngimon(P.getActiveEngimon());
            }
            else if ("n".equals(userInput))
            {
                P.showEngimonDetails();
            }
            else if ("g".equals(userInput))
            {
                g.generateEngimon();
            }
            else
            {
                System.out.println("Invalid Command, type 'h' to list all commands" );
            }
            userInput = sc.nextLine();
        }
    }

    public void moveUp() {
        tempP = new player(P);
        P.moveUp(g.getMapLength(), g.getMapWidth());
        resolveMove(P, g, tempP);
    }

    public void moveDown() {
        tempP = new player(P);
        P.moveDown(g.getMapLength(), g.getMapWidth());
        resolveMove(P, g, tempP);
    }

    public void moveLeft() {
        tempP = new player(P);
        P.moveLeft(g.getMapLength(), g.getMapWidth());
        resolveMove(P, g, tempP);
    }

    public void moveRight() {
        tempP = new player(P);
        P.moveRight(g.getMapLength(), g.getMapWidth());
        resolveMove(P, g, tempP);
    }

    public void openMenu()
    {
        if (m.getState() == MainVisualizer.VISUALIZER_MAP)
        {
            m.openInventory();
        }
        else if (m.getState() == MainVisualizer.VISUALIZER_INVENTORY)
        {
            m.openMap();
        }
        else
        {
            m.openMap();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
