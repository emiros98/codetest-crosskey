package codetest;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<Double> loanSumList = new ArrayList<>();
    private ArrayList<Double> interestList = new ArrayList<>();
    private ArrayList<Double> yearList = new ArrayList<>();

    public static void main(String[] args) {
        Main m = new Main();
        try {
            m.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        getInput();
        String name;
        double sum;
        double interest;
        double year;
        for (int i = 0; i < nameList.size(); i++){
            name = nameList.get(i);
            sum = loanSumList.get(i);
            interest = interestList.get(i);
            year = yearList.get(i);

            double s = calculate(sum, interest, year);
            System.out.println("Prospect " + (i+1) + ": " + name + " wants to borrow " + sum + "€ for a period of " + (int)year + " years and pay " + round(s) + "€ each month.");
        }
    }

    /**
     * Reads input from file, calls handeinput() to extract info
     * @throws IOException if the file prospects.txt can't be found, or if Bufferedreader throws Exception
     */
    private void getInput() throws IOException {
        String absolutePath = new File("").getAbsolutePath(); //find absolute path

        BufferedReader stdinReader = new BufferedReader(new InputStreamReader(System.in)); // Reader for user input
        String filename = stdinReader.readLine();
        stdinReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(absolutePath + filename));                // Reader for input from file

        reader.readLine(); //Remove first line of input, containing headers

        String line;
        while ((line = reader.readLine()) != null){
            if (!line.isEmpty()){                       //remove empty lines
                handleInput(line);
            }

        }
    }

    /**
     * Parses one line of input by splitting by quotation marks and by commas, and extracting the
     * information necessary to call calculate().
     *
     * If any line contains information that can't be parsed, i.e. if splitting produces too many
     * or too few fields, it is ignored.
     *
     * @param input one line from the input file
     */
    private void handleInput(String input) {
        String[] firstSplit = input.split("\""); //split on ", if there are any

        // remove potential empty strings created
        firstSplit = Arrays.stream(firstSplit).filter(s -> !s.isEmpty()).toArray(String[]::new);

        //if input contained ", ignore commas between the quotation marks
        if (firstSplit.length > 1){

            //we want to keep the comma in between the ""
            String[] secondSplit = firstSplit[1].split(",");

            // remove potential empty strings created
            secondSplit = Arrays.stream(secondSplit).filter(s -> !s.isEmpty()).toArray(String[]::new);

            //if there are too many or too few numbers
            if (secondSplit.length == 3){
                //calculate(firstSplit[0], Double.parseDouble(secondSplit[0]), Double.parseDouble(secondSplit[1]), Double.parseDouble(secondSplit[2]));
                nameList.add(firstSplit[0]);
                loanSumList.add(Double.parseDouble(secondSplit[0]));
                interestList.add(Double.parseDouble(secondSplit[1]));
                yearList.add(Double.parseDouble(secondSplit[2]));
            }

        }
        else {
            // if no ", just split on ","
            String[] split = input.split(",");

            // split should contain a name and three numbers, otherwise we ignore this line
            if (split.length == 4){
                    nameList.add(split[0]);
                    loanSumList.add(Double.parseDouble(split[1]));
                    interestList.add(Double.parseDouble(split[2]));
                    yearList.add(Double.parseDouble(split[3]));
            }
        }
    }

    /**
     * Calculates and returns the sum that should be paid each month
     *
     * @param sum  Total sum of the original loan
     * @param interest Interest rate in %
     * @param years Desired number of years to pay back
     */
    protected double calculate(double sum, double interest, double years){
        double b = (interest/12.0)/100.0;
        double p = years*12.0;
        double x = pow(1.0+b, p);   // we need this number twice, so we calculate it in advance

        double e = sum*(b*x)/(x-1.0);

        return e;
    }

    /**
     * Calculates a^b
     * Returns result
     */
    protected double pow(double a, double b){
        double res = a;
        for (int i = 1; i < b; i++){
            res = res*a;
        }
        return res;
    }

    /**
     * Function for rounding a double to two decimals
     * @param a the double to be rounded
     */
    protected double round(double a){
        a = (long)((a*100.0)+0.5);
        return a/100.0;
    }
}
