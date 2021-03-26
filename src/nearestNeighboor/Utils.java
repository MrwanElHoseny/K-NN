package nearestNeighboor;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Utils {   ///Utility functions

    double distance(Record query, Record data) {
        return Math.sqrt(
                Math.pow(query.price - data.price, 2)
                + Math.pow(query.maint - data.maint, 2)
                + Math.pow(query.doors - data.doors, 2)
                + Math.pow(query.luggage - data.luggage, 2)
                + Math.pow(query.capacity - data.capacity, 2)
                + Math.pow(query.safety - data.safety, 2)
        );
    }

    String classify(Record query, Scanner trainData, int k) {

        String mode = "no mode";
        double recordNum = 1;
        List<ArrayList<Double>> distances = new ArrayList<>();

        Record trainIndex = new Record();

        while (trainData.hasNext()) {
            trainIndex.map(trainData.nextLine());
            distances.add(new ArrayList<>(Arrays.asList(recordNum, distance(query, trainIndex), new Double(trainIndex.label))));

            recordNum++;
        }


        Collections.sort(distances, (d1, d2) -> d1.get(1).compareTo(d2.get(1)));

        int[][] labels = new int[k][2]; ///[label][freq]

        outerloop:
        for (int i = 0; i < k; i++) {

            for (int j = 0; j < i; j++) {

                if (labels[j][0] == (int) Math.round(distances.get(i).get(2))) {
                    labels[j][1]++;
                    continue outerloop;
                }
            }
            labels[i][0] = (int) Math.round(distances.get(i).get(2));
            labels[i][1] = 1;
        }
        
        ///finding maximum freq (mode)

        int maxIndex = 0;
        for (int q = 1; q < k; q++) {

            if (labels[q][1] > labels[maxIndex][1]) {
                maxIndex = q;
            }
        }

        return trainIndex.label_reverse.get(labels[maxIndex][0]);
    }
}
