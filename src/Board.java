import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public static class Board {
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
                    }else if(i == 1){
                        m = Integer.parseInt(value);
                    }else if(i == 1){
                        m = Integer.parseInt(value);
                    }else if(i == 1){
                        m = Integer.parseInt(value);
                    }els




                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
