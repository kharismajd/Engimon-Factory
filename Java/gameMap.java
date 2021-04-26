import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class gameMap {
    /*Gunakan Vector agar thread safe*/
    protected Vector<Vector<tile>> tile_map;

    private int mapLength = 12;
    private int mapWidth = 10;

    protected int engimon_count = 0;
    private int map_move_count = MAP_MOVE;
    private int map_generate_count = MAP_GENERATE;
    private int map_wild_engimon_levelup = MAP_WILD_ENGIMON_LEVELUP;

    public static final int MAP_ENGIMON_COUNT = 5;
    public static final int MAP_MOVE = 5;
    public static final int MAP_GENERATE = 10;
    public static final int MAP_WILD_ENGIMON_LEVELUP = 30;

    public gameMap()
    {
        tile_map = new Vector<Vector<tile>>();
        for (int i = 0; i < this.mapWidth; i++) {
            Vector<tile> r = new Vector<tile>();
            for (int j = 0; j < this.mapLength; j++) {
                if (i < this.mapLength/2 && j < this.mapWidth/2)
                {
                    r.add(new tile(j,i,"sea"));
                }
			    else if (i >= this.mapLength/2 && j < this.mapWidth/2)
                {
                    r.add(new tile(j,i,"grassland"));
                }
			    else if (i < this.mapLength/2 && j >= this.mapWidth/2)
                {
                    r.add(new tile(j,i,"mountains"));
                }
			    else
                {
                    r.add(new tile(j,i,"tundra"));
                }
            }
            tile_map.add(r);
        }
    }
    public gameMap(String externalFile) throws Exception
    {
        File f;
        Scanner fileReader;
        try
        {
            f = new File(externalFile);
            fileReader = new Scanner(f);

        }
        catch (Exception e)
        {
            throw e;
        }

        String line = fileReader.nextLine();
        String[] firstLine= line.split(" ");
        Integer baris = Integer.parseInt(firstLine[0]);
        Integer kolom = Integer.parseInt(firstLine[1]);

        Integer count = 0;
        while (fileReader.hasNextLine())
        {
            line = fileReader.nextLine();
            count++;
            if (line.split(" ").length != kolom)
            {
                throw new Exception("Pastikan input file benar");
            }
        }

        if (count != baris)
        {
            throw new Exception("Pastikan input file benar");
        }
        fileReader.close();

        fileReader = new Scanner(f);
        line = fileReader.nextLine();
        this.mapWidth = baris;
        this.mapLength = kolom;
        tile_map = new Vector<Vector<tile>>();
        for (int i = 0; i < this.mapWidth; i++) {
            line = fileReader.nextLine();
            String[] lineArray = line.split(" ");
            Vector<tile> r = new Vector<tile>();
            for (int j = 0; j < this.mapLength; j++) {
                if ("o".equals(lineArray[j]))
                {
                    r.add(new tile(j,i,"sea"));
                }
                else if (".".equals(lineArray[j]))
                {
                    r.add(new tile(j,i,"tundra"));
                }
                else if ("^".equals(lineArray[j]))
                {
                    r.add(new tile(j,i,"mountains"));
                }
                else
                {
                    r.add(new tile(j,i,"grassland"));
                }
            }
            tile_map.add(r);
        }
        fileReader.close();
    }

    public void printMap()
    {
        for (Vector<tile> i :tile_map){
            for (tile j:i) {
                j.printTile();
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public void printLegend()
    {
        System.out.println("Legend:");
        System.out.println("W/w: Water engimon");
        System.out.println("I/i : Ice engimon");
        System.out.println("F/f: Fire engimon");
        System.out.println("G/g: Ground engimon");
        System.out.println("E/e: Electric engimon");
        System.out.println("L/l: Fire/Electric engimon");
        System.out.println("S/s: Water/Ice engimon");
        System.out.println("N/n: Water/Ground engimon");

        System.out.println();
        System.out.println("P: Player");
        System.out.println("X: Active Engimon");

        System.out.println();
        System.out.println("- : Grassland");
        System.out.println("o : Sea");
        System.out.println("^ : Mountains>");
        System.out.println(". : Tundra");
    }

    private int randomInteger(int start, int end)
    {
        Random rand = new Random();

        int rand_int = rand.nextInt(end-start+1);
        return rand_int + start;
    }

    public void moveTileEngimon(tile tile1, tile tile2)
    {
        /*Menggerakan pokemon dari tile1 ke tile2*/
        if (tile2.haveWildEngimon() == false && tile2.isTileValid(tile1.getEngimon()) && tile2.isPlayerHere() == false && tile2.isactiveEngimonHere() == false)
        {
            engimon nullEngimon;

            tile2.setEngimon(tile1.getEngimon());
            tile1.setEngimon(null);
        }
    }

    public void moveWildEngimon()
    {
        for (Vector<tile> i :tile_map){
            for (tile j:i) {
                if (j.haveWildEngimon() == false)
                {
                    j.passed();
                }
            }
        }

        Integer randomDirection = (randomInteger(0,10000) % 4);

        for (Vector<tile> i :tile_map){
            for (tile j:i) {
                if (j.haveWildEngimon() && j.isPass() == false)
                {
                    if (randomDirection == 0 && j.y > 0) //up
                    {
                        moveTileEngimon(j, tile_map.elementAt(j.y-1).elementAt(j.x));
                        // cout << "move up" << endl;
                    }
                    else if (randomDirection == 1 && j.x > 0)//left
                    {
                        moveTileEngimon(j, tile_map.elementAt(j.y).elementAt(j.x - 1));
                        // cout << "move left" << endl;
                    }
                    else if (randomDirection == 2 && j.x < this.mapLength - 1)//right
                    {
                        moveTileEngimon(j, tile_map.elementAt(j.y).elementAt(j.x+1));
                        // cout << "move right" << endl;
                    }
				    else
                    {
                        if (randomDirection == 3 && j.y < this.mapWidth - 1)//down
                        {
//                            System.out.println(randomDirection);
//                            assert (randomDirection == 3);
                            moveTileEngimon(j, tile_map.elementAt(j.y+1).elementAt(j.x));
                            // cout << "move down" << endl;
                        }
                    }
                    j.passed();
                    randomDirection = (randomInteger(0,10000) % 4);
                }
            }
        }

        for (Vector<tile> i :tile_map){
            for (tile j:i) {
                j.resetPass();

            }
        }
    }

    public void generateEngimon()
    {
        while (engimon_count < MAP_ENGIMON_COUNT)
        {
            int randomX = randomInteger(0,this.mapLength - 1);
            int randomY = randomInteger(0,this.mapWidth - 1);

            if (this.tile_map.elementAt(randomY).elementAt(randomX).haveWildEngimon() == false)
            {
                this.tile_map.elementAt(randomY).elementAt(randomX).spawn();
                this.engimon_count++;
            }
        }
    }

    public void levelUpEngimon()
    {
        for (Vector<tile> i :tile_map){
            for (tile j:i) {
                if (j.haveWildEngimon())
                {
                    j.getEngimon().gainExp(100);
                    if (j.getEngimon().getCummulativeExp() >= 10000)
                    {
                        deleteTileEngimon(j.x,j.y);
                    }
                }
            }
        }
    }


    public void updateMap(int player_x, int player_y, int active_x, int active_y) throws Exception
    {
        for (Vector<tile> i :tile_map){
            for (tile j:i) {
                j.playerIsNotHere();
                j.activeEngimonIsNotHere();
            }
        }
        if (this.tile_map.elementAt(active_y).elementAt(active_x).haveWildEngimon())
        {
            throw new Exception("Active Engimon menabrak Wild Engimon");
        }

        this.tile_map.elementAt(player_y).elementAt(player_x).playerIsHere();
        this.tile_map.elementAt(active_y).elementAt(active_x).activeEngimonIsHere();

        this.map_move_count = this.map_move_count - 1;
        if (this.map_move_count <= 0)
        {
            moveWildEngimon();
            map_move_count = MAP_MOVE;
        }

        this.map_generate_count = this.map_generate_count - 1;
        if (this.map_generate_count <= 0)
        {
            generateEngimon();
            map_generate_count = MAP_GENERATE;
        }

        this.map_wild_engimon_levelup = this.map_wild_engimon_levelup - 1;
        if (this.map_wild_engimon_levelup <= 0)
        {
            levelUpEngimon();
            map_wild_engimon_levelup = MAP_WILD_ENGIMON_LEVELUP;
        }

        this.tile_map.elementAt(player_y).elementAt(player_x).playerIsHere();
        this.tile_map.elementAt(active_y).elementAt(active_x).activeEngimonIsHere();
    }

    public void updateMap(int player_x, int player_y, int active_x, int active_y, player P) throws Exception
    {
        this.updateMap(player_x, player_y, active_x, active_y);
        this.tile_map.elementAt(active_y).elementAt(active_x).activeEngimonIsHere(P.getActiveEngimon());
    }

    public void deleteTileEngimon(int x, int y)
    {
        if (!(tile_map.elementAt(y).elementAt(x).isPlayerHere() || tile_map.elementAt(y).elementAt(x).isactiveEngimonHere())
                && tile_map.elementAt(y).elementAt(x).haveWildEngimon())
        {
            this.tile_map.elementAt(y).elementAt(x).setEngimon(null);
            engimon_count = engimon_count - 1;
            if (engimon_count < 3)
            {
                generateEngimon();
            }
        }
    }
    public engimon getTileEngimon(int x, int y)
    {
        return tile_map.elementAt(y).elementAt(x).getEngimon();
    }
    public String findNearbyEngimon(int x, int y)
    {
        if (y > 0 && tile_map.elementAt(y-1).elementAt(x).haveWildEngimon())
        {
            return "up";
        }
        else if (x > 0 && tile_map.elementAt(y).elementAt(x-1).haveWildEngimon())
        {
            return "left";
        }
        else if (y < this.mapWidth-1 &&tile_map.elementAt(y+1).elementAt(x).haveWildEngimon())
        {
            return "up";
        }
        else if (x < this.mapLength-1 && tile_map.elementAt(y).elementAt(x+1).haveWildEngimon())
        {
            return "right";
        }
        else if (tile_map.elementAt(y).elementAt(x).haveWildEngimon())
        {
            return "center";
        }
        else
        {
            return "null";
        }

    }
    public Boolean isTileOccupied(int x, int y)
    {
        return (tile_map.elementAt(y).elementAt(x).haveWildEngimon() && !tile_map.elementAt(y).elementAt(x).getEngimon().isActive());
    }

    public int getMapLength(){ return this.mapLength; }
    public int getMapWidth(){ return this.mapWidth; }
}
