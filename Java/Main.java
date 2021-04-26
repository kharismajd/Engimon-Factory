import java.awt.*;
import java.util.Scanner;

public class Main   {
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
        if (g.isTileOccupied(P.getPlayerPosX(),P.getPlayerPosY()))
        {
            System.out.println("Battle");

            Battle b = new Battle(P.getActiveEngimon(), new wild_engimon(g.getTileEngimon(P.getPlayerPosX(),P.getPlayerPosY())) , P);
            winBattle = b.battleConfirmation();
            if (winBattle == 1)
            {
                g.deleteTileEngimon(P.getPlayerPosX(), P.getPlayerPosY());
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

    public static void main(String[] args) {
        //Deklarasi
        String userInput;
        String starter_engimon_name;
        player_engimon starter_engimon = null;
        player tempP;
        gameMap g;
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

        player P = new player("James", starter_engimon, 50, g.getMapLength()/2, g.getMapWidth()/2, g.getMapLength()/2, g.getMapWidth()/2 + 1);


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

        map_visualizer m = new map_visualizer(g);
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
                tempP = new player(P);
                P.moveUp(g.getMapLength(), g.getMapWidth());
                resolveMove(P, g, tempP);

            }
            else if ("a".equals(userInput))
            {
                tempP = new player(P);
                P.moveLeft(g.getMapLength(), g.getMapWidth());
                resolveMove(P, g, tempP);
            }
            else if ("s".equals(userInput))
            {
                tempP = new player(P);
                P.moveDown(g.getMapLength(), g.getMapWidth());
                resolveMove(P, g, tempP);
            }
            else if ("d".equals(userInput))
            {
                tempP = new player(P);
                P.moveRight(g.getMapLength(), g.getMapWidth());
                resolveMove(P, g, tempP);
            }
            else if ("z".equals(userInput))
            {
                P.Interact();
            }
            else if ("m".equals(userInput))
            {
                g.printMap();
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
}
