import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        try {
            m.getInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @throws IOException if the file prospects.txt can't be found, or if Bufferedreader throws Exception
     */
    private void getInput() throws IOException {
        String filePath = new File("").getAbsolutePath(); //find absolute path

        BufferedReader reader = new BufferedReader(new FileReader(filePath + "/prospects.txt"));
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
                calculate(firstSplit[0], Double.parseDouble(secondSplit[0]), Double.parseDouble(secondSplit[1]), Double.parseDouble(secondSplit[2]));
            }

        }
        else {
            // if no ", just split on ","
            String[] split = input.split(",");

            // split should contain a name and three numbers, otherwise we ignore this line
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

        double e = sum*(b*x)/(x-1.0);

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

    /**
     * Function for rounding a double to two decimals
     * @param a the double to be rounded
     */
    private double round(double a){
        a = (long)((a*100.0)+0.5);
        return a/100.0;
    }
}
