package nearestNeighboor;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;

public class Data {

    int records = 0;

    public Data(String fileName) {
        File file = new File(fileName);
        try {
            Scanner mainData = new Scanner(file);
            while (mainData.hasNext()) {
                records++;
                mainData.next();
            }
        } catch (FileNotFoundException ex) {
        }

    }

    void randomFiles(String fileName) {
        int split = (int) (records * (75.0f / 100.0f));

        try {

            FileWriter trainData = new FileWriter("TrainData.txt");
            FileWriter testData = new FileWriter("TestData.txt");
            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            ArrayList<Integer> indexes = new ArrayList<>();
            ArrayList<Integer> trainIndexes = new ArrayList<>();
            ArrayList<Integer> testIndexes = new ArrayList<>();
            for (int i = 0; i < records; i++) {
                indexes.add(i);
            }

            Collections.shuffle(indexes);

            for (int i = 0; i < split; i++) {
                trainIndexes.add(indexes.get(i));
            }
            for (int i = split; i < records; i++) {
                testIndexes.add(indexes.get(i));
            }

            for (int i = 0; i < records; i++) {
                if (trainIndexes.contains(i)) {
                    trainData.write(scan.next());
                    trainData.write("\n");
                } else if (testIndexes.contains(i)) {
                    testData.write(scan.next());
                    testData.write("\n");
                } else {
                    System.err.print("Error while generating random data files.");
                }
            }

            trainData.flush();
            trainData.close();
            testData.flush();
            testData.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
