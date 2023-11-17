package pro.sky.java.homework;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.homework.ConstantsSort.*;

class SortedMethodAnaliseTest {

    @Test
    public void whenMethodSort() {
        SortedMethod<Integer>[] methods = new SortedMethod[]{
                BUBBLE_SORT,
                SELECT_SORT,
                INSERT_SORT
        };
        SortedMethodAnalise<Integer> analise = new SortedMethodAnalise<>(ARRAY, methods);
        for (int i = 0; i < 10; i++) {
            System.out.println(analise.getFastMethod());
        }
    }

}