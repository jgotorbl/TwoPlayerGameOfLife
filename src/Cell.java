/**
 * Created by Jaime on 3/21/2017.
 */
public enum Cell {
    CELL_DEAD("."), CELL_ONE("1"), CELL_TWO("2");
    private String c;
    private Cell(String c){
        this.c = c;
    }

    /*
     * @return the string associated to a specific cell.
     */
    public String getC(){
        return c;
    }

    /*
     * @param s string used to assign a cell
     * @return cell depending on the string passed as argument
     */
    public static Cell assignCell(String s){
        switch(s){
            case "1":
                return CELL_ONE;
            case "2":
                return CELL_TWO;
            default:
                return CELL_DEAD;
        }
    }
}
