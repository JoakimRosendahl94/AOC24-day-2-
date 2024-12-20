import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Solver {

    ArrayList<Integer> firstList = new ArrayList<>();
    ArrayList<Integer> secondList = new ArrayList<>();
    int numberOfSafeReports;
    boolean isSafe;
    int numberOfProblems;

    public Solver() {
        this.firstList.add(0);
        this.secondList.add(0);
        this.numberOfSafeReports = 0;
        this.numberOfProblems = 0;
    }

    public void readInput(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(

        new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            ArrayList<Integer> numbers = new ArrayList<>();
            int i = 0;
            for(String element : line.split(" ")) {
                if(!element.equals("")) {
                    numbers.add(i, Integer.parseInt(element));
                    i++;
                }               
            }
            this.numberOfProblems = 0;
            if(trueIfSafe(numbers)) {
                numberOfSafeReports++;
            }
        }
        reader.close();
    }


    private boolean trueIfSafe(ArrayList<Integer> numbers) {
        ArrayList<Integer> listOfDiff = new ArrayList<>();

        for(int i = 0; i < numbers.size() - 1; i++) {
            listOfDiff.add(i, numbers.get(i + 1) - numbers.get(i));
        }

        evaluateDiffs(listOfDiff, numbers);

        return isSafe;
    }

    private boolean evaluateDiffs(ArrayList<Integer> listOfDiff, ArrayList<Integer> numbers) {
        isSafe = false;

        //if (not(arg1 or arg2))
        if(!(isAllNegative(listOfDiff, numbers) || isAllPositive(listOfDiff, numbers))) {

            this.numberOfProblems++;
            int largestdiff = 0;

            for(int i = 0; i < listOfDiff.size(); i++) {
                if(Math.abs(listOfDiff.get(i)) > Math.abs(largestdiff)) {
                    largestdiff = listOfDiff.get(i);
                }
            }

            int i = listOfDiff.indexOf(largestdiff) + 1;            
            eliminateFirstOffendingValue(numbers, i);
        }

        if(isAllNegative(listOfDiff, numbers) || isAllPositive(listOfDiff, numbers)) {
            if(difInBounds(listOfDiff, numbers) && this.numberOfProblems < 2){
                isSafe = true;
            }
        }

        return isSafe;
        // all diffs 0 < d < 4
        
    }

    private boolean isAllNegative(ArrayList<Integer> listOfDiff, ArrayList<Integer> numbers) {
        boolean isAllNegative = true;

        for(int i = 0; i < listOfDiff.size(); i++) {
            if(listOfDiff.get(i) > - 1) {
                isAllNegative = false;
                break;
            }
        }
        return isAllNegative;
    }

    private boolean isAllPositive(ArrayList<Integer> listOfDiff, ArrayList<Integer> numbers) {
        boolean isAllPositive = true;

        for(int i = 0; i < listOfDiff.size(); i++) {
            if(listOfDiff.get(i) > - 1) {
                isAllPositive = false;
                break;
            } 
        }
        return isAllPositive;
    }


    private boolean difInBounds(ArrayList<Integer> listOfDiff, ArrayList<Integer> numbers) {
        boolean isInBound = true;

        for(int i = 0; i < listOfDiff.size(); i++) {
            if(listOfDiff.get(i) == 0) {
                eliminateFirstOffendingValue(numbers, i);
                if(this.numberOfProblems > 1) {
                    isInBound = false;
                    break;
                }
            } else if(listOfDiff.get(i) < (-3)) {
                eliminateFirstOffendingValue(numbers, i);
                if(this.numberOfProblems > 1) {
                    isInBound = false;
                    break;
                }
            } else if (listOfDiff.get(i) > 3) {
                eliminateFirstOffendingValue(numbers, i);
                if(this.numberOfProblems > 1) {
                    isInBound = false;
                    break;
                }
            }
        }

        return isInBound;
    }

    private void eliminateFirstOffendingValue(ArrayList<Integer> list, int index) {

        if(this.numberOfProblems == 1) {
            list.remove(index);
            trueIfSafe(list);
        }
    }
}


   