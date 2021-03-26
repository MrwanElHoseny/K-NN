package nearestNeighboor;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class KNN {

    public static void main(String[] args) {

        int positiveClassifications = 0;
        int numOfTestRecords = 0;
        int k;
        
        Scanner input = new Scanner(System.in);
        System.out.print("Number Of Neighboors: ");
        k = input.nextInt();
        
        String rawFile = "RawData.CSV";
        Data raw = new Data(rawFile);
        raw.randomFiles(rawFile);

        Utils utils = new Utils();

        File trainFile = new File("TrainData.txt");
        File testFile = new File("TestData.txt");

        Record query = new Record();

        try {

            Scanner testScanner = new Scanner(testFile);

            while (testScanner.hasNext()) {

                numOfTestRecords++;
                Scanner trainScanner = new Scanner(trainFile);
                query.map(testScanner.nextLine());

                String label = query.getLabel(query.label);
                String estimate = utils.classify(query, trainScanner, k);

                System.out.println(query.price + "," + query.maint + "," + query.doors + "," + query.capacity + "," + query.luggage + "," + query.safety
                        + " :: " + label + " ---> " + estimate);

                if (label.equals(estimate)) {
                    positiveClassifications++;
                }
            }
            
            System.out.println("Accuracy: " + ((float) positiveClassifications / numOfTestRecords) * 100 + "%");
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }

}
