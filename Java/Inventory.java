import java.util.ArrayList;
import java.util.List;

public abstract class Inventory<T> {
    protected ArrayList<T> contents = new ArrayList<T>();
    public static Integer maxInventory = 50;

    public abstract void addItem(T item);
    public abstract void deleteItem(T item);
    public abstract Integer countItem();

    public ArrayList<T> getContents() {
        return this.contents;
    }
}
