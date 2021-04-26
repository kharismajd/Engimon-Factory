import java.io.*;
import java.util.Vector;
import java.lang.*;

public class Save {
  public Save(gameMap map, player character){
    //SAVE MAP
    File save;
    FileWriter fw = null;
    BufferedWriter bw = null;
    File save2;
    FileWriter fw2 =null;
    BufferedWriter bw2=null;

    try {
      save = new File ("save_data_map.txt");
      fw = new FileWriter("save_data_map.txt");
      bw = new BufferedWriter(fw);
      fw2 = new FileWriter("save_data_wild_map.txt");
      bw2 = new BufferedWriter(fw2);
    } catch (Exception e){
      System.out.println("Error on creating save file map");
      e.printStackTrace();
    }
    
    Vector<Vector<tile>> tiles = new Vector<Vector<tile>>();
    tiles = map.getTilesMap();
    char sym;
    try { 
      for (Vector<tile> row : tiles){
        for (tile curtile : row){
          sym = curtile.getSymbol();
          if (!('^'==sym || 'o'==sym || '-'==sym || '.'==sym)){
            bw.write(curtile.getSymbol()+"."); 
            bw.write(curtile.getX().toString()+"."+curtile.getY().toString());
            if (curtile.getEngimon()!=null){
              this.saveEngiData(bw2, curtile.getEngimon());
              if (curtile.getEngimon().getParent1()!=null){
                this.saveEngiData(bw2, curtile.getEngimon().getParent1());
              }
              if (curtile.getEngimon().getParent2()!=null){
                this.saveEngiData(bw2, curtile.getEngimon().getParent2());
              }
              bw2.newLine();
            }
            bw.newLine();
          }
        }
      }
    bw.close();
    fw.close();
    bw2.close();
    fw2.close();
    System.out.println("Berhasil!");
    } catch (Exception e){
      System.out.println("Error on saving file map");
      e.printStackTrace();
    }

    //SAVE PLAYER
    try {
      save = new File("save_data_player.txt");
      fw = new FileWriter("save_data_player.txt");
      bw = new BufferedWriter(fw);
    } catch (Exception e){
      System.out.println("Error on creating save file player");
      e.printStackTrace();
    }

    try {
      bw.write(character.name+".");
      bw.write(character.max_inventory_capacity.toString()+".");
      bw.write(character.player_x.toString()+".");
      bw.write(character.player_y.toString()+".");
      bw.write(character.activeEngimon_x.toString()+".");
      bw.write(character.activeEngimon_y.toString()+".");
      bw.close();
      fw.close();
      System.out.println("Berhasil!!");
    } catch (Exception e){
      System.out.println("Error on saving player data");
      e.printStackTrace();
    }

    //SAVE ENGIMON INVENTORY AND ACTIVE ENGIMON
    try {
      save = new File("save_data_engimon_inventory.txt");
      fw = new FileWriter("save_data_engimon_inventory.txt");
      bw = new BufferedWriter(fw);
    } catch (Exception e){
      System.out.println("Error on creating save file engimon inventory");
      e.printStackTrace();
    }

    try {
      save2 = new File("save_data_engimon_aktif.txt");
      fw2 = new FileWriter("save_data_engimon_aktif.txt");
      bw2 = new BufferedWriter(fw2);
    } catch (Exception e){
      System.out.println("Error on creating save file engimon aktif");
      e.printStackTrace();
    }

    try {
      for (player_engimon pe : character.engimon_inventory.contents){
        if (pe.isActive()){
          this.saveEngiData(bw2, pe);
          this.saveEngiData(bw, pe);
          if (pe.getParent1()!=null){
            this.saveEngiData(bw2, pe.getParent1());
            this.saveEngiData(bw, pe.getParent1());
          }
          if (pe.getParent2()!=null){
            this.saveEngiData(bw, pe.getParent2());
            this.saveEngiData(bw2, pe.getParent2());
          }
          bw2.newLine();
        } else {
          this.saveEngiData(bw, pe);
          if (pe.getParent1()!=null){
            this.saveEngiData(bw, pe.getParent1());
          }
          if (pe.getParent2()!=null){
            this.saveEngiData(bw, pe.getParent2());
          }
          bw.newLine();
        }
      }
      bw2.close();
      fw2.close();
      bw.close();
      fw.close();
    } catch (Exception e){
      System.out.println("Error on saving active engimon data");
      e.printStackTrace();
    }

    //SAVE SKILL INVENTORY
    try{
      save = new File("save_data_skill_inventory.txt");
      fw = new FileWriter("save_data_skill_inventory.txt");
      bw = new BufferedWriter(fw);
    } catch (Exception e){
      System.out.println("Error on creating save file skill inventory");
      e.printStackTrace();
    }

    Integer base_power, mastery, amount;
    try {
      for (skill sk : character.skill_inventory.contents){
        base_power = sk.getBasePower();
        mastery = sk.getMasteryLv();
        amount = sk.getAmountInInventory();
        bw.write(sk.getSkillName()+"."+base_power.toString()+"."+mastery.toString()+"."+amount.toString()+".");
        if (sk.getUniqueEngimonName()!=null){
          bw.write(sk.getUniqueEngimonName());
        }
        for (String elmt : sk.getElements()){
          bw.write("."+elmt);
        }
        bw.newLine();
      }
      bw.close();
      fw.close();
      System.out.println("Berhasil!!!!");
    } catch (Exception e){
      System.out.println("Error on writing skill inventory data");
      e.printStackTrace();
    }
  }

  private void saveEngiData(BufferedWriter bw, engimon pe) throws Exception{
    try {
      bw.write(pe.getName()+".");
      if (pe.getParent1()!=null){
          bw.write(pe.getParent1().getName()+".");
      } else {
        bw.write("null.");
      } 

      if (pe.getParent2()!=null){
        bw.write(pe.getParent2().getName()+".");
      } else {
          bw.write("null.");
      }

      bw.write(pe.getSpecies()+".");
        for (int i =0; i<4;i++){
        if (pe.getMove(i)!=null){
            bw.write(pe.getMove(i).getSkillName()+"."+pe.getMove(i).getMasteryLv()+".");
        } else {
            bw.write("null.");
        }
      }
      bw.write(pe.getElmt1()+".");
      if (pe.getElmt2()!=null){
      bw.write(pe.getElmt2()+".");
      } else {
      bw.write("null.");
      }

      bw.write(pe.getLevel().toString()+"."+pe.getExp()+"."+pe.getLife().toString()+"\n");
      System.out.println("Berhasil!!!");
    } catch (Exception e){
      throw (e);
    }
    
  }

}
  
