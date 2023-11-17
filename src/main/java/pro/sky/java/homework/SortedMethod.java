package pro.sky.java.homework;

import java.util.Comparator;
import java.util.function.BiConsumer;

public class SortedMethod<T> {

    private final String name;
    private final Comparator<T> comparator;
    private final BiConsumer<T[], Comparator<T>> biConsumer;

    public SortedMethod(Comparator<T> comparator, BiConsumer<T[], Comparator<T>> biConsumer, String name) {
        this.comparator = comparator;
        this.biConsumer = biConsumer;
        this.name = name;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public BiConsumer<T[], Comparator<T>> getBiConsumer() {
        return biConsumer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SortedMethod{" +
                "name='" + name + '\'' +
                '}';
    }
}
