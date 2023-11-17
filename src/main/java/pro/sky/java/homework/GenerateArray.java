package pro.sky.java.homework;

import java.util.Random;
import java.util.stream.IntStream;

public class GenerateArray {
    public static Integer[] getArrays(int size) {
        Integer[] res = IntStream.range(0, size).boxed().toArray(Integer[]::new);
        shuffle(res);
        return res;
    }

    private static void shuffle(Integer[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int randIndex = i + random.nextInt(array.length - i);
            int tmp = array[i];
            array[i] = array[randIndex];
            array[randIndex] = tmp;
        }
    }

    public static void main(String[] args) {
        GenerateArray.getArrays(10);
    }
}
