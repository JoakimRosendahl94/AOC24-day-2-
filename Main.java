import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Solver s = new Solver();
        Solver s2 = new Solver();

        try {

            s.readInput("data.txt");
            s2.readInput("testData.txt");

        } catch (IOException e) {

            System.err.println("Nope");

            return;

        }

       

        System.err.println(s2.numberOfSafeReports);
        System.out.println(s.numberOfSafeReports);

    }
    
}