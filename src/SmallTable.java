public class SmallTable implements Table {

    private final int chairs, tableID;

    public SmallTable(int tableID) {
        chairs = 2;
        this.tableID = tableID;
    }
    @Override
    public int getTableID() {
        return tableID;
    }

    @Override
    public int getChairs() {
        return chairs;
    }

    @Override
    public int compareTo(Table o) {
        if (this.chairs < o.getChairs()) return -1;
        else return 1;
    }
}
