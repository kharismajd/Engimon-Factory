import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle extends element_advantage {
    private player_engimon mymon;
    private wild_engimon yourmon;
    private player character;

    Battle(player_engimon mymon, wild_engimon yourmon, player character){
        this.mymon = mymon;
        this.yourmon = yourmon;
        this.character = character;
    }

    public engimon getMyEngimon(){
        return this.mymon;
    }

    public engimon getYourEngimon(){
        return this.yourmon;
    }

    public player getCharacter(){
        return this.character;
    }

    public double[] getEffectiveElmt(){
        List<String> my_elmt_arr = new ArrayList<String>();
        List<String> your_elmt_arr = new ArrayList<String>();
        my_elmt_arr.add(this.mymon.element1);
        if (this.mymon.element2!=engimon.element_null){
            my_elmt_arr.add(this.mymon.element2);
        }
        
        your_elmt_arr.add(this.yourmon.element1);
        if (this.yourmon.element2!=engimon.element_null){
            your_elmt_arr.add(this.yourmon.element2);
        }

        double my_most_effective_elmt = 0;
        double your_most_effective_elmt = 0;
        double my_curAdv;
        double your_curAdv;
        for (String myelmt : my_elmt_arr){
            for (String yourelmt : your_elmt_arr){

                my_curAdv = getElementAdvantage(myelmt, yourelmt);
                if (my_most_effective_elmt < my_curAdv){
                    my_most_effective_elmt = my_curAdv;
                }

                your_curAdv = getElementAdvantage(yourelmt, myelmt);
                if (your_most_effective_elmt < your_curAdv){
                    your_most_effective_elmt = your_curAdv;
                }
            }
        }
        double[] retVal = {my_most_effective_elmt, your_most_effective_elmt};
        return retVal;
    }

    public double[] power(){
        int my_total_skill_power = 0;
        int your_total_skill_power = 0;
        int my_skill_base; int your_skill_base;
        int my_skill_mastery; int your_skill_mastery;

        for (int i=0;i<4;i++){
            if (this.mymon.getMove(i) != null){
                my_skill_base = this.mymon.getMove(i).getBasePower();
                my_skill_mastery = this.mymon.getMove(i).getMasteryLv();
                my_total_skill_power += my_skill_base * my_skill_mastery;
            }

            if (this.yourmon.getMove(i) != null){
                your_skill_base = this.yourmon.getMove(i).getBasePower();
                your_skill_mastery = this.yourmon.getMove(i).getMasteryLv();
                your_total_skill_power += your_skill_base * your_skill_mastery;
            }   
        }

        double[] elmtAdv = this.getEffectiveElmt();
        double my_lv_elmt_power = this.mymon.getLevel() * elmtAdv[0];
        double your_lv_elmt_power = this.yourmon.getLevel() * elmtAdv[1];

        double my_total_power = my_lv_elmt_power + my_total_skill_power;
        double your_total_power = your_lv_elmt_power + your_total_skill_power;
        double[] retVal = {my_total_power, your_total_power};
        return retVal;
    }

    public int battleConfirmation(){
        this.yourmon.showAttributes();
        double[] powerList = power();
        System.out.printf("\nMy engimon power: %.2f\n", powerList[0]);
        System.out.printf("Wild engimon power: %.2f\n", powerList[1]);
        Scanner stdin = new Scanner(System.in);
        System.out.println("Ingin lanjut gelud? Y/N");
        String descision =  stdin.nextLine();

        int retVal;
        if ("Y".equals(descision) || "y".equals(descision)){
            System.out.println("Battle Commencing!!");
            retVal = this.initBattle();
        } else if ("N".equals(descision) || "n".equals(descision)){
            System.out.println("Gajadi gelud");
            retVal = -1;
        } else {
            System.out.println("Invalid input");
            retVal = -1;
        }

//        stdin.close();
        return retVal;
    }

    public void win(){
        player_engimon newmon = new player_engimon(this.yourmon);
        this.character.addInventoryContent(newmon);
        System.out.println("Kamu Menang (^_^)b");

        int LVbefore = this.mymon.getLevel();
        int diffLv = yourmon.getLevel() - mymon.getLevel();
        int exp = (int)Math.floor((10*diffLv + 600)/(80-diffLv)+5);
        this.mymon.gainExp(exp);
        System.out.printf("%s telah mendapatkan %d exp\n", this.mymon.getName(), exp);
        int LVafter = this.mymon.getLevel();
        if (LVafter > LVbefore){
            System.out.printf("%s telah naik level!\n", this.mymon.getName());
        }
        String skill_item_get = this.yourmon.getMove(0).getSkillName();
        try {
            this.character.addInventoryContent(skill_item_get);
            System.out.printf("Anda mendapatkan skill item %s\n", skill_item_get);
        } catch (Exception e){
            System.out.println(e);
        }
        
        if (this.mymon.getCummulativeExp()>100000){
            System.out.printf("%s telah mencapai batas usia, ucapkan selamat tinggal (T_T)/\n", this.mymon.getName());
            this.character.deleteActiveEngimon();
        }
    }

    public void lose(){
        System.out.println(this.mymon.getLife());
        int life_after = this.mymon.getLife()-1;
        this.mymon.setLife(life_after);
        System.out.printf("Kamu K4L4H!!\n");
        if (life_after == 0){
            System.out.printf("%s telah banyak menderita, kini saatnya dia untuk pergi (T_T)/\n", this.mymon.getName());
            this.character.deleteActiveEngimon();
        } else {
            System.out.printf("Berhati-hatilah, Life %s tersisa %d\n",this.mymon.getName(), life_after);
        }
    }

    //Jangan pake ini buat dipake di main,, pake fungsi battleConfirmation()
    public int initBattle(){
        double[] power_var = power();
        if (this.mymon == null){
            System.out.println("Kamu tidak memiliki engimon aktif");
            return -1;
        }

        if (power_var[0] >= power_var[1]){
            win();
            return 1;
        } else {
            lose();
            return 0;
        }
    }
}
