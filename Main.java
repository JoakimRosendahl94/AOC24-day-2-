import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //Solver s = new Solver();
        Solver s2 = new Solver();
        TestSolver test = new TestSolver();

        try {

            //s.readInput("data.txt");
            s2.readInput("testData.txt");
            test.readInput("data.txt");

        } catch (IOException e) {

            System.err.println("Nope");

            return;

        }

        System.err.println(s2.numberOfSafeReports);

        System.err.println(test.numberOfSafeReports);

        //System.out.println(s.numberOfSafeReports);

    }
    
}