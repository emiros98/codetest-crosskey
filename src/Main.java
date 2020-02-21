import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        //m.calculate("a", 1000,5,2);
        try {
            m.getInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getInput() throws Exception {
        String filePath = new File("").getAbsolutePath(); //find absolute path

        BufferedReader reader = new BufferedReader(new FileReader(filePath + "/prospects.txt"));
        reader.readLine(); //Remove first line of input, containing headers
        String line;
        String[] splitLine;
        while ((line = reader.readLine()) != null){
            if (!line.isEmpty()){                       //remove empty lines
                handleInput(line);
            }

        }
    }

    private void handleInput(String input) throws Exception {
        String[] firstSplit = input.split("\"");
        firstSplit = Arrays.stream(firstSplit).filter(s -> !s.isEmpty()).toArray(String[]::new);

        if (firstSplit.length > 1){     //line contained "",
            String[] secondSplit = firstSplit[1].split(","); //we want to keep the comma in between the ""
            secondSplit = Arrays.stream(secondSplit).filter(s -> !s.isEmpty()).toArray(String[]::new);
            if (secondSplit.length != 3){
                throw new Exception("Problem parsing input");
            }
            calculate(firstSplit[0], Double.parseDouble(secondSplit[0]), Double.parseDouble(secondSplit[1]), Double.parseDouble(secondSplit[2]));
        }
        else {
            String[] split = input.split(",");

            if (split.length == 4){
                calculate(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));

            }
        }
    }

    /**
     * Calculates the sum that should be paid each month and prints to standard output
     *
     * @param name The name of the customer, taken from input
     * @param sum  Total sum of the original loan
     * @param interest Interest rate in %
     * @param years Desired number of years to pay back
     */
    private void calculate(String name, double sum, double interest, double years){
        double b = (interest/12.0)/100.0;
        double p = years*12.0;
        double x = pow(1.0+b, p);   // we need this number twice, so we calculate it in advance

        double e = sum*(b*x)/(x-1.0);   //Todo: round up

        System.out.println(name + " wants to borrow " + sum + "€ for a period of " + (int)years + " years and pay " + round(e) + "€ each month.");
    }

    /**
     * Calculates a^b
     * Returns result
     */
    private double pow(double a, double b){
        double res = a;
        for (int i = 1; i < b; i++){
            res = res*a;
        }
        return res;
    }

    private double round(double a){
        a = (long)((a*100.0)+0.5);
        return a/100.0;
    }
}
