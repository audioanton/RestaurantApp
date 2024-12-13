public class LargeTable implements Table {

    private int chairs, tableID;

    public LargeTable(int tableID) {
        chairs = 8;
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
