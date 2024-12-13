public class MediumTable implements Table {

    private int chairs, tableID;

    public MediumTable( int tableID) {
        chairs = 4;
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
