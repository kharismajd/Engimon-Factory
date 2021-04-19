import com.sun.org.apache.xpath.internal.operations.Bool;

public interface Database <T> {

    public Boolean isExist(String name);

    public T find(String name);

    public T random();

    public void IntializeDatabase();
    //public is compatible?
}
