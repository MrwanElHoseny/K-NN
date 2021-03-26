package nearestNeighboor;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class Record {

    int price, maint, doors, capacity, luggage, safety, label;
    HashMap<String, Integer> price_map = new HashMap<>();
    HashMap<String, Integer> maint_map = new HashMap<>();
    HashMap<String, Integer> doors_map = new HashMap<>();
    HashMap<String, Integer> capacity_map = new HashMap<>();
    HashMap<String, Integer> luggage_map = new HashMap<>();
    HashMap<String, Integer> safety_map = new HashMap<>();
    HashMap<String, Integer> label_map = new HashMap<>();
    HashMap<Integer, String> label_reverse = new HashMap<>();

    public Record() {
        price_map.put("low", 0);
        price_map.put("med", 1);
        price_map.put("high", 2);
        price_map.put("vhigh", 3);

        maint_map.put("low", 0);
        maint_map.put("med", 1);
        maint_map.put("high", 2);
        maint_map.put("vhigh", 3);

        doors_map.put("2", 0);
        doors_map.put("3", 1);
        doors_map.put("4", 2);
        doors_map.put("5more", 3);

        capacity_map.put("2", 0);
        capacity_map.put("4", 1);
        capacity_map.put("more", 2);

        luggage_map.put("small", 0);
        luggage_map.put("med", 1);
        luggage_map.put("big", 2);

        safety_map.put("low", 0);
        safety_map.put("med", 1);
        safety_map.put("high", 2);

        label_map.put("unacc", 0);
        label_map.put("acc", 1);
        label_map.put("good", 2);
        label_map.put("vgood", 3);

        label_reverse.put(0, "unacc");
        label_reverse.put(1, "acc");
        label_reverse.put(2, "good");
        label_reverse.put(3, "vgood");

    }

    void map(String input) {
        List<String> values = Arrays.asList(input.split(","));
        price = price_map.get(values.get(0));
        maint = maint_map.get(values.get(1));
        doors = doors_map.get(values.get(2));
        capacity = capacity_map.get(values.get(3));
        luggage = luggage_map.get(values.get(4));
        safety = safety_map.get(values.get(5));
        label = label_map.get(values.get(6));
    }

    String getLabel(int label) {
        return label_reverse.get(label);
    }

}
