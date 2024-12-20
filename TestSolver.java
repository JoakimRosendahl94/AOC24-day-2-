import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class TestSolver {

    ArrayList<Integer> firstList = new ArrayList<>();
    ArrayList<Integer> secondList = new ArrayList<>();
    ArrayList<ArrayList<Integer>> allReleventPermutationsOfReports = new ArrayList<>();
    ArrayList<Integer> listOfDiff = new ArrayList<>();
    ArrayList<Integer> report = new ArrayList<>();
    ArrayList<Boolean> listOfSafe = new ArrayList<>();

    int numberOfSafeReports;
    boolean isSafe;
    boolean isPermutationSafe;

    int numberOfProblems;

    public TestSolver() {
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

            allReleventPermutationsOfReports.add(numbers);

            listAllReleventPermutatiuons(numbers);

            if(trueIfSafe(allReleventPermutationsOfReports)) {
                numberOfSafeReports++;
            }

            allReleventPermutationsOfReports.clear();
        }
        reader.close();
    }


    private boolean trueIfSafe(ArrayList<ArrayList<Integer>> allPermutations) {
        isSafe = false; 

        for(int i = 0; i < allPermutations.size(); i++) {
            report.addAll(allPermutations.get(i));

            listTheDiff(report);

            //if any of all permutation returns safe then is safe
            if(evaluateReports(listOfDiff)) {
                listOfSafe.add(true);
                
            }

            listOfDiff.clear();
            report.clear();
        }

        if(listOfSafe.contains(true)) {
            isSafe = true;
        }

        listOfSafe.clear();

        return isSafe;
        
    }

    private ArrayList<Integer> listTheDiff(ArrayList<Integer> report) {

        for(int i = 0; i < report.size() - 1; i++) {
            int diff = (report.get(i) - report.get(i + 1));
            listOfDiff.add(diff);
        }

        return listOfDiff;
    }

    private boolean evaluateReports(ArrayList<Integer> listOfDiff) {
        isPermutationSafe = false;

        if(isAllNegative(listOfDiff) || isAllPositive(listOfDiff)) {
            if(difInBounds(listOfDiff)) {
                isPermutationSafe = true;
            }
        }

        return isPermutationSafe;        
    }

    private boolean isAllNegative(ArrayList<Integer> listOfDiff) {
        boolean isAllNegative = true;

        for(int i = 0; i < listOfDiff.size(); i++) {
            if(listOfDiff.get(i) > - 1) {
                isAllNegative = false;
                break;
            }
        }
        return isAllNegative;
    }

    private boolean isAllPositive(ArrayList<Integer> listOfDiff) {
        boolean isAllPositive = true;

        for(int i = 0; i < listOfDiff.size(); i++) {
            if(listOfDiff.get(i) < 1) {
                isAllPositive = false;
                break;
            } 
        }
        return isAllPositive;
    }


    private boolean difInBounds(ArrayList<Integer> listOfDiff) {
        boolean isInBound = true;

        for(int i = 0; i < listOfDiff.size(); i++) {
            if(listOfDiff.get(i) == 0) {
                isInBound = false;
                break;
            } else if(listOfDiff.get(i) < (-3)) {
                isInBound = false;
                break;
            } else if (listOfDiff.get(i) > 3) {
                isInBound = false;
                break;
                }
        }
        return isInBound;
    }


    public ArrayList<ArrayList<Integer>> listAllReleventPermutatiuons(ArrayList<Integer> numbers) {
        for(int i = 0; i < numbers.size(); i++) {   
            int temp = numbers.remove(i); 
            allReleventPermutationsOfReports.add(new ArrayList<>(numbers));
            numbers.add(i, temp);
        }

        return allReleventPermutationsOfReports;
    }
    

}
