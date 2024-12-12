import java.io.Serializable;

public record Table(int chairs, int tableId) implements Serializable, Comparable<Table> {

    @Override
    public int compareTo(Table o) {
        if (this.chairs < o.chairs) return -1;
        else return 1;
    }
}
