import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.ArrayList;

/**
 * Created by Jaime on 3/20/2017.
 */
public class DataParser {

    /*
     * Parses the contents of a file and stores them into an arrayList.
     * @param filepath path of the file being parsed.
     * @return initialData arrayList containing the contents of the file.
     */
    public static ArrayList<String> initializeBoard(String filePath){
        ArrayList<String> initialData = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String cLine;
            while((cLine = br.readLine()) != null){
                initialData.add(cLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initialData;
    }

    /*
     * Validates if the initialarrayList containing the contents of the file.
     * @param initialData
     * @return true if the file fits all the conditions. False otherwise
     */
    public static boolean validateInitialBoard(ArrayList<String> initialData){
        String firstRow = initialData.get(0);
        String[] dims = firstRow.split(" ");
        try{
            int numberOfRows = Integer.parseInt(dims[0]);
            int numberOfColumns = Integer.parseInt(dims[1]);
            return areRowsCorrect(numberOfRows, initialData)
                    && areColumnsCorrect(numberOfColumns, initialData)
                    && isDataCorrect(initialData);
        }catch (NumberFormatException e){
            return false;
        }
    }

    /*
     * Validates if the initialarrayList containing the contents of the file.
     * @param initialData arrayList with the initial board contents
     * @return true if the data of the file only contains .,1 and 2, and false otherwise
     */
    private static boolean isDataCorrect(ArrayList<String> initialData) {
        for(int i=1; i< initialData.size();i++){
            String s = initialData.get(i);
            for(int j=0; j< s.length(); j++){
                //boolean expression returns true if char is not ., 1 or 2. False otherwise
                if(!(s.charAt(j) == '.') && !(s.charAt(j) == '1') && !(s.charAt(j) == '2')){
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Validates if the initialarrayList containing the contents of the file.
     * @param initialData  arrayList with the initial board contents
     * @return true if all the columns have the specified size
     */
    private static boolean areColumnsCorrect(int numberOfColumns, ArrayList<String> initialData) {
        for(int i=1; i< initialData.size();i++){
            if(numberOfColumns != initialData.get(i).length()){
                return false;
            }
        }
        return true;
    }

    /*
     * Validates if the initialarrayList containing the contents of the file.
     * @param initialData arrayList with the initial board contents
     * @return true if the file has the correct number of rows.
     */
    private static boolean areRowsCorrect(int numberOfRows, ArrayList<String> initialData) {
        return numberOfRows == initialData.size() -1;
    }


}
