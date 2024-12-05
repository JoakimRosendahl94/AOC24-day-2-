import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Solver {

    ArrayList<Integer> firstList = new ArrayList<>();
    ArrayList<Integer> secondList = new ArrayList<>();
    int numberOfSafeReports;
    boolean isSafe;

    public Solver() {
        this.firstList.add(0);
        this.secondList.add(0);
        this.numberOfSafeReports = 0;
    }

    public void readInput(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(

        new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            int[] numbers = new int[line.split(" ").length];
            int i = 0;
            for(String element : line.split(" ")) {
                if(!element.equals("")) {
                    numbers[i] = Integer.parseInt(element);
                    i++;
                }               
            }
            if(trueIfSafe(numbers)) {
                numberOfSafeReports++;
            }
        }
        reader.close();
    }


    private boolean trueIfSafe(int[] numbers) {
        int[] listOfDiff = new int[numbers.length - 1];

        for(int i = 0; i < listOfDiff.length; i++) {
            listOfDiff[i] = numbers[i + 1] - numbers[i];
        }

        evaluateDiffs(listOfDiff);

        return isSafe;
    }

    private boolean evaluateDiffs(int[] listOfDiff) {
        isSafe = false;

        if(isAllNegative(listOfDiff) || isAllPositive(listOfDiff)) {
            if(difInBounds(listOfDiff)) {
                isSafe = true;
            }
        }

        return isSafe;
        // all diffs 0 < d < 4
        
    }

    private boolean isAllNegative(int[] listOfDiff) {
        boolean isAllNegative = true;
        for(int element : listOfDiff) {
            if(element > -1) {
                isAllNegative = false;
                break;
                } 
            }
        return isAllNegative;
    }

    private boolean isAllPositive(int[] listOfDiff) {
        boolean isAllPositive = true;
        for(int element : listOfDiff) {
            if(element < 1) {
                isAllPositive = false;
                break;
                } 
            }
        return isAllPositive;
    }


    private boolean difInBounds(int[] listOfDiff) {
        boolean isInBound = true;

        for(int element : listOfDiff) {
            if(element == 0) {
                isInBound = false;
                break;
            } else if(element < (-3)) {
                isInBound = false;
                break;
            } else if (element > 3) {
                isInBound = false;
                break;
            }
        }

        return isInBound;
    }
}


   