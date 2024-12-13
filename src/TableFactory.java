public class TableFactory {
     private int uniqueID;

     public TableFactory() {
         uniqueID = 0;
     }

     public Table getTable(int size) {
         if (size == (1)) {
             return new SmallTable(++uniqueID);
         } else if (size == (2)) {
             return new MediumTable(++uniqueID);
         } else if (size == (3)) {
             return new LargeTable(++uniqueID);
         }
         return null;
     }
}
