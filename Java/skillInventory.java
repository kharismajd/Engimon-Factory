import java.util.Collections;
import java.util.Comparator;

public class skillInventory extends Inventory<skill> {
    public void addItem(skill item) {
        Boolean found = false;
        Integer i;
        for (i = 0; i < this.contents.size(); i++) {
            if (item.getSkillName() == this.contents.get(i).getSkillName()) {
                found = true;
                break;
            }
        }

        if (found) {
            Integer past_val = this.contents.get(i).getAmountInInventory();
            this.contents.get(i).setAmountInInventory(past_val + 1);
        }
        else {
            item.setAmountInInventory(1);
            this.contents.add(item);
            Collections.sort(this.contents, new skillComparator());
        }
    }

    public void deleteItem(skill item) {
        for (int i = 0; i < this.contents.size(); i++) {
            if (item.getSkillName() == this.contents.get(i).getSkillName()) {
                if (this.contents.get(i).getAmountInInventory() > 1) {
                    Integer past_val = this.contents.get(i).getAmountInInventory();
                    this.contents.get(i).setAmountInInventory(past_val - 1);
                }
                else {
                    this.contents.remove(i);
                }
            }
        }
    }

    public Integer countItem() {
        Integer count = 0;
        for (Integer i = 0; i < this.contents.size(); i++) {
            count += this.contents.get(i).getAmountInInventory();
        }
        return count;
    }
}

class skillComparator implements Comparator<skill> {

    @Override
    public int compare(skill s1, skill s2) {
        if (s2.getBasePower() > s1.getBasePower())
        {
            return 1;
        }
        else if (s2.getBasePower() < s1.getBasePower())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}