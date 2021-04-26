import java.util.ArrayList;

public abstract class Inventory<T> {
    protected ArrayList<T> contents = new ArrayList<T>();

    public abstract void addItem(T item);
    public abstract void deleteItem(T item);
    public abstract Integer countItem();

    public ArrayList<T> getContents() {
        return this.contents;
    }
}
