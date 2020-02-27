# codetest-crosskey

This application reads information about prospects from a .txt file, computes the exact amount each customer should pay each month, and prints the result to stdout. More precisely: Given a total loan of X € and a fixed yearly interest rate of Y %. The customer wants to pay the same amount each month for Z
years. The application calculates how much the customer should pay each month to pay off the whole debt.

The first line of the .txt input file should be something like "Customer,Total loan,Interest,Years", subsequent lines should be in the format of "[name],[Total sum of loan],[Yearly interest rate in %],[Number of years]".

Example:
> Customer,Total loan,Interest,Years <br>
Juha,1000,5,2 <br>
"Clarencé,Andersson",2000,6,4

To run the application, download and unzip the .zip or the .tar file located in /build/distrbutions. From a terminal, navigate into the /bin/ directory and run  "echo '/../prospects.txt' | ./codetest", if you're using linux or macOS, or  "echo '/../prospects.txt' | ./codetest.bat", if you're using Windows.
