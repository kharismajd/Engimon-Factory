import java.io.*;
import java.util.Vector;
import java.util.Scanner;
import java.lang.*;

public class Load {
    private player character;
    private gameMap map;
    private Vector<Vector<player_engimon>> families;

    public Load(gameMap map){
        File load, load2;
        Scanner scan = null;
        Scanner scan2 = null;
        String line, line2;
        String[] dataline, dataline2;

        //LOAD PLAYER
        try {
            load = new File ("save_data_player.txt");
            scan = new Scanner(load);
            load2 = new File ("save_data_engimon_aktif.txt");
            scan2 = new Scanner(load2);
        } catch (Exception e){
            System.out.println("Error on read player data");
            e.printStackTrace();
        }

        line = scan.nextLine();
        dataline = line.split(".");
        line2 = scan2.nextLine();
        dataline2 = line2.split(".");
        player_engimon pr1 = null;
        player_engimon pr2 =null;
        player_engimon active = null;
        String[] datapr;
        if (scan2.hasNextLine()){
            datapr = scan2.nextLine().split(".");
            try{
                if(dataline[1]!="null"){
                    pr1 = new player_engimon (datapr[0],datapr[3],datapr[1],Integer.parseInt(datapr[10]),Integer.parseInt(datapr[11]),datapr[2]);
                }
                if (dataline[2]!="null"){
                    datapr = scan2.nextLine().split(".");
                    pr2 = new player_engimon(datapr[0],datapr[3],datapr[1],Integer.parseInt(datapr[10]),Integer.parseInt(datapr[11]),datapr[2]);
                }
            } catch (Exception e){
                System.out.println("Error on generating parent engimon");
                e.printStackTrace();
            }
        } else {
            try {
                active = new player_engimon(dataline2[0], pr1, pr2, dataline2[3], Integer.parseInt(dataline2[10]),Integer.parseInt(dataline2[11]));
                database_skill ds = new database_skill();
                for (int i =0; i<4;i++){
                    if (dataline2[4+i]=="null"){
                        active.setMove(i, new skill());
                    } else {
                        skill newskill = ds.find(dataline2[4+(i*2)]);
                        newskill.setMasteryLv(Integer.parseInt(dataline2[4+(i*2)+1]));
                        active.setMove(i, newskill);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error on generating active engimon");
                e.printStackTrace();
            }
        }
        player character = new player(dataline[0],active,Integer.parseInt(dataline[1]),Integer.parseInt(dataline[2]),Integer.parseInt(dataline[3]),Integer.parseInt(dataline[4]),Integer.parseInt(dataline[5]));
        scan.close();
        scan2.close();

        //Load Game map
        try {
            load = new File("save_data_map.txt");
            load2 = new File("save_data_wild_map.txt");
            scan = new Scanner(load);
            scan2 = new Scanner(load2);
        } catch (Exception e){
            System.out.println("Error on loading game map");
            e.printStackTrace();
        }

        wild_engimon wild;
        while (scan.hasNextLine() && scan2.hasNextLine()){
            line = scan.nextLine();
            dataline = line.split(".");
            line2 = scan2.nextLine();
            dataline2 = line2.split(".");
            try {
                wild = new wild_engimon(dataline[3], Integer.parseInt(dataline[10]), 0);
                map.setTilesMap(Integer.parseInt(dataline[1]), Integer.parseInt(dataline[2]), Character.valueOf(dataline[0].charAt(0)), wild);
            } catch (Exception e) {
                System.out.println("Error on loading wild engimon");
                e.printStackTrace();
            }
            line2 = scan2.nextLine();
        }
        scan.close();
        scan2.close();

        //LOAD ENGIMON INVENTORY
        try {
            load = new File("save_data_engimon_inventory.txt");
            scan = new Scanner(load);
        } catch (Exception e){
            System.out.println("Error on reading engimon inventory data");
            e.printStackTrace();
        }

        engimonInventory ei = new engimonInventory();
        boolean haveParent1 = false; 
        boolean haveParent2 = false;
        pr1 = null;
        pr2 = null;
        Vector<Vector<player_engimon>> families = new Vector<Vector<player_engimon>>();
        Vector<player_engimon> family = new Vector<player_engimon>();
        player_engimon item;
        while (scan.hasNextLine()){
            line = scan.nextLine();
            if (!line.equals("")){
                
                dataline = line.split(".");
                try {
                    if (haveParent1 || haveParent2){
                        item = new player_engimon(dataline[0],dataline[3],dataline[1],Integer.parseInt(dataline[10]),Integer.parseInt(dataline[11]),dataline[2]);
                    } else {
                        item = new player_engimon(dataline[0],null,null,dataline[3],Integer.parseInt(dataline[10]),Integer.parseInt(dataline[11]));
                    }
                    family.add(item);
                } catch (Exception e){
                    System.out.println("Error on loading engimon inventory");
                    e.printStackTrace();
                }
                

                if (haveParent1){
                    haveParent2 = true;
                }
                haveParent1 = true;
            } else {
                if (haveParent1){
                    family.firstElement().setParent1(family.elementAt(1));
                }

                if (haveParent2){
                    family.firstElement().setParent2(family.elementAt(2));
                }

                ei.addItem(family.firstElement());
                
                if (family.size()>1){
                    families.add(family);
                }
                
                haveParent1 = false;
                haveParent2 = false;
            }
        }
        character.setEngimonInventory(ei);
        scan.close();


        //LOAD SKILL INVENT
        try {
            load = new File("save_data_skill_inventory.txt");
            scan = new Scanner(load);
        } catch (Exception e){
            System.out.println("Error on reading engimon inventory data");
            e.printStackTrace();
        }

        skillInventory si = new skillInventory();
        skill sk;
        while (scan.hasNextLine()){
            line = scan.nextLine();
            dataline = line.split(".");
            Vector<String> elmt = new Vector<String>();
            for (int i=5; i<dataline.length;i++){
                elmt.add(dataline[i]);
            }
            try {
                sk = new skill(dataline[0],Integer.parseInt(dataline[1]), Integer.parseInt(dataline[2]),elmt.toArray(new String[elmt.size()]));
                si.addItem(sk);
            } catch (Exception e){
                System.out.println("Error on reading skill data");
                e.printStackTrace();
            }
        }
        character.setSkillInventory(si);
        scan.close();

        this.character = character;
        this.map = map;
        this.families = families;
    }

    public player getPlayer(){
        return this.character;
    }

    public gameMap getMap(){
        return this.map;
    }

    public Vector<Vector<player_engimon>> getFamilies(){
        return this.families;
    }
}
