import java.io.Serializable;

public interface Table extends Comparable<Table>, Serializable {

    int getTableID ();
    int getChairs();

    int compareTo(Table o);
}

