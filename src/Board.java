import RegionP.Region;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
    public Region[][] regions;
    private int PlayerNum;
    private int rows;
    private int cols;
    private int init_plan_min;
    private int init_plan_sec;
    private int init_budget;
    private int init_center_dep;
    private int plan_rev_min;
    private int plan_rev_sec;
    private int rev_cost;
    private int max_dep;
    private int interest_pct;

    public Board(int rows,int cols){



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
//                    if(i == 1){
//                        rows = Integer.parseInt(value);
//                    }
//                    if(i == 2){
//                        cols = Integer.parseInt(value);
//                    }
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

        this.rows = rows;
        this.cols = cols;
        regions = new Region[rows][cols];
        initializeBoard();



    }
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                regions[i][j] = new Region(i, j);
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            // Add spacing for visual representation of hexagonal grid
            System.out.print("        ");
            for (int j = 0; j < cols; j++) {
                if(j%2 == 1){
                    System.out.print(" " + regions[i][j].getOwner());
                    if(regions[i][j].isStandHere()){
                        System.out.print("C");
                    }else{
                        System.out.print("-");
                    }
                }
            }
            System.out.println();
            System.out.print("Row:" + (i+1) + " ");
            for (int j = 0; j < cols; j++) {
                if(j%2 == 0){
                    System.out.print(" "+ regions[i][j].getOwner());
                    if(regions[i][j].isStandHere()){
                        System.out.print("C");
                    }else{
                        System.out.print("-");
                    }
                }
            }
            System.out.println();
        }
    }
}
