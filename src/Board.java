import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
    private Region[][] regions ;
    private int PlayerNum;
    private int m;
    private int n;
    private int init_plan_min;
    private int init_plan_sec;
    private int init_budget;
    private int init_center_dep;
    private int plan_rev_min;
    private int plan_rev_sec;
    private int rev_cost;
    private int max_dep;
    private int interest_pct;

    public Board(int PlayerNum){

        this.PlayerNum = PlayerNum;

        //read configuration file  part
        String filePath = "config.txt";

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            int i=1;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line based on the '=' character
                String[] parts = line.split("=");

                // Ensure there are two parts before accessing the value
                if (parts.length == 2) {
                    // Trim leading and trailing whitespaces and print the value
                    String value = parts[1].trim();

                    //set the value to the variable that we want
                    if(i == 1){
                        m = Integer.parseInt(value);
                    }
                    if(i == 2){
                        n = Integer.parseInt(value);
                    }
                    if(i == 3){
                        init_plan_min = Integer.parseInt(value);
                    }
                    if(i == 4){
                        init_plan_sec = Integer.parseInt(value);
                    }
                    if(i == 5){
                        init_budget = Integer.parseInt(value);
                    }
                    if(i == 6){
                        init_center_dep = Integer.parseInt(value);
                    }
                    if(i == 7){
                        plan_rev_min = Integer.parseInt(value);
                    }
                    if(i == 8){
                        plan_rev_sec = Integer.parseInt(value);
                    }
                    if(i == 9){
                        rev_cost = Integer.parseInt(value);
                    }
                    if(i == 10){
                        max_dep = Integer.parseInt(value);
                    }
                    if(i == 11){
                        interest_pct = Integer.parseInt(value);
                    }
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getHexagonRepresentation(String filled) {
        String top = " / \\ ";
        String middle;
        if(filled.equals(" "){
            middle = "/   \\";
        }else{
            middle = "/ "+filled+" \\"
        }
        String bottom = "\\_/ ";
        return top + "\n" + middle + "\n" + bottom;
    }

    public  void printBoard() {

        for (int row = 0; row < m; row++) {
            // Print top row of hexagons
            for (int col = 0; col < n; col++) {
                System.out.print(getHexagonRepresentation(region[row][col]));
            }
            System.out.println();

            // Print bottom row of hexagons (shifted for odd rows)
            if (row % 2 == 1) {
                for (int col = 0; col < n; col++) {
                    System.out.print("   ");
                }
                System.out.println();
            }
        }

    }



}
