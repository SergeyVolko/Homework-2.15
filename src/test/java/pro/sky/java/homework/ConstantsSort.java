package pro.sky.java.homework;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class ConstantsSort {
    public static final Integer[] ARRAY = GenerateArray.getArrays(100000);
    public static final Integer[] TEST_ARRAY = GenerateArray.getArrays(20);
    public static final Integer[] SORT_ARRAY = IntStream.range(0, 20).boxed().toArray(Integer[]::new);
    public static final String NAME_METHOD_BUBBLE = "Bubble sort";
    public static final String NAME_METHOD_SELECT = "Select sort";
    public static final String NAME_METHOD_INSERT = "Insert sort";
    public static final String NAME_METHOD_FAST = "Fast sort";
    public static final int NUM_FOR_TEST_ARRAY = 12;
    public static final Comparator<Integer> BINARY_COMPARATOR = Comparator.comparingInt(o -> o);
    public static final SortedMethod<Integer> BUBBLE_SORT = new SortedMethod<>(
            Comparator.comparingInt(o -> o),
            (a, c) -> {
                for (int i = 0; i < a.length - 1; i++) {
                    for (int j = 0; j < a.length - 1 - i; j++) {
                        if (c.compare(a[j],  a[j + 1]) > 0) {
                            int tmp = a[j];
                            a[j] = a[j + 1];
                            a[j + 1] = tmp;
                        }
                    }
                }
            },
            NAME_METHOD_BUBBLE
    );

    public static final SortedMethod<Integer> SELECT_SORT = new SortedMethod<>(
            Comparator.comparingInt(o -> o),
            (a, c) -> {
                for (int i = 0; i < a.length - 1; i++) {
                    int minElementIndex = i;
                    for (int j = i + 1; j < a.length; j++) {
                        if (c.compare(a[j], a[minElementIndex]) < 0) {
                            minElementIndex = j;
                        }
                    }
                    int tmp = a[i];
                    a[i] = a[minElementIndex];
                    a[minElementIndex] = tmp;
                }
            },
            NAME_METHOD_SELECT
    );

    public static final SortedMethod<Integer> INSERT_SORT = new SortedMethod<>(
            Comparator.comparingInt(o -> o),
            (a, c) -> {
                for (int i = 1; i < a.length; i++) {
                    int temp = a[i];
                    int j = i;
                    while (j > 0 && c.compare(a[j -1], temp) >= 0) {
                        a[j] = a[j - 1];
                        j--;
                    }
                    a[j] = temp;
                }
            },
            NAME_METHOD_INSERT
    );

    public static final BiConsumer<Integer[], Comparator<Integer>> FAST_SORT = new BiConsumer<Integer[], Comparator<Integer>>() {
        @Override
        public void accept(Integer[] integers, Comparator<Integer> integerComparator) {
            quickSort(integers, 0, integers.length - 1, integerComparator);
        }

        private void quickSort(Integer[] arr, int begin, int end, Comparator<Integer> c) {
            if (begin < end) {
                int partitionIndex = partition(arr, begin, end, c);
                quickSort(arr, begin, partitionIndex - 1, c);
                quickSort(arr, partitionIndex + 1, end, c);
            }
        }

        private int partition(Integer[] arr, int begin, int end, Comparator<Integer> comparator) {
            int pivot = arr[end];
            int i = (begin - 1);

            for (int j = begin; j < end; j++) {
                if (comparator.compare(arr[j], pivot) <= 0) {
                    i++;

                    swapElements(arr, i, j);
                }
            }

            swapElements(arr, i + 1, end);
            return i + 1;
        }

        private void swapElements(Integer[] arr, int left, int right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    };

    public static final SortedMethod<Integer> FAST_SORTED = new SortedMethod<>(
            Comparator.comparingInt(o -> o),
            FAST_SORT,
            NAME_METHOD_FAST
    );

}
