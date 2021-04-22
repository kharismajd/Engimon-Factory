import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class skillInventory {
    public void addItem(skill item) {
        Boolean found = false;
        for (Integer i = 0; i < this.contents.size(); i++) {
            if (item.getSkillName() == this.contents.get(i)) {
                found = true;
                break;
            }
        }

        if (found) {
            Integer past_val = this.contents.get(i).getAmountInInventory();
            this.contents.get(i).setAmountInInventory(past_val + 1);
        }
        else {
            item.setAmountInInventory(0);
            this.contents.add(item);
            Collections.sort(this.contents, new skillComparator());
        }
    }

    public void deleteItem(skill item) {
        for (Integer i = 0; i < this.contents.size(); i++) {
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
        return s2.getBasePower().compareTo(s1.getBasePower());
    }
}