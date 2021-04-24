import java.util.Collections;
import java.util.Comparator;

public class engimonInventory extends Inventory<player_engimon> {
    @java.lang.Override

    public void addItem(player_engimon item) {
        this.contents.add(item);
        Collections.sort(this.contents, new engimonComparator());
    }

    public void deleteItem(player_engimon item) {
        for (int i = 0; i < this.contents.size(); i++) {
            if (this.contents.get(i) == item) {
                this.contents.remove(i);
                break;
            }
        }
    }

    public Integer countItem() {
        return this.contents.size();
    }
}

class engimonComparator implements Comparator<player_engimon> {

    @Override
    public int compare(player_engimon e1, player_engimon e2) {
        int value1 = e1.getName().compareTo(e2.getName());
        if (value1 == 0) {
            return e2.getLevel().compareTo(e1.getLevel());
        }
        return value1;
    }
}