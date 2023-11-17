package pro.sky.java.homework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortedMethodAnalise<T> {
    private final T[] array;
    private final SortedMethod<T>[] methods;

    public SortedMethodAnalise(T[] array, SortedMethod<T>[] methods) {
        this.array = array;
        this.methods = methods;
    }

    public Map<Long, SortedMethod<T>> getMapMethods() {
        Map<Long, SortedMethod<T>> map = new HashMap<>();
        for (SortedMethod<T> method : methods) {
            T[] a = Arrays.copyOf(array, array.length);
            long start = System.currentTimeMillis();
            method.getBiConsumer().accept(a, method.getComparator());
            long time = System.currentTimeMillis() - start;
            map.put(time, method);
        }
        return map;
    }

    public SortedMethod<T> getFastMethod() {
        return getMapMethods().entrySet().stream().min((e1, e2) -> (int) (e1.getKey() - e2.getKey()))
                .get().getValue();
    }

    public SortedMethod<T> getSlowMethod() {
        return getMapMethods().entrySet().stream().max((e1, e2) -> (int) (e1.getKey() - e2.getKey()))
                .get().getValue();
    }
}
