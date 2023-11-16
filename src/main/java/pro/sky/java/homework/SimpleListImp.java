package pro.sky.java.homework;

import pro.sky.java.homework.exceptions.ElementNotFoundException;
import pro.sky.java.homework.exceptions.IndexNotFoundException;
import pro.sky.java.homework.exceptions.ParamNullException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class SimpleListImp<E> implements SimpleList<E> {
    private static final int CAPACITY = 10;
    private int size = 0;

    private Object[] elements;

    public SimpleListImp() {
        this.elements = new Object[CAPACITY];
    }
    public SimpleListImp(E[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
        this.size = elements.length;
    }

    @Override
    public E add(E item) {
        validateElement(item);
        resizeArray();
        elements[size++] = item;
        return item;
    }

    @Override
    public E add(int index, E item) {
        validateElement(item);
        validateIndex(index);
        resizeArray();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
        return item;
    }

    @Override
    public E set(int index, E item) {
        validateIndex(index);
        validateElement(item);
        elements[index] =  item;
        return item;
    }

    @Override
    public E remove(E item) {
        validateElement(item);
        int index = indexOf(item);
        if (index < 0) {
            throw new ElementNotFoundException(
                    String.format(ElementNotFoundException.TEMPLATE_MESSAGE, item));
        }
        return remove(index);
    }

    @Override
    public E remove(int index) {
        validateIndex(index);
        E item = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return item;
    }

    @Override
    public boolean contains(E item) {
        validateElement(item);
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(E item) {
        validateElement(item);
        return IntStream.range(0, size)
                .filter(e -> elements[e].equals(item))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public int lastIndexOf(E item) {
        validateElement(item);
        return IntStream.range(0, size)
                .map(e -> size - 1 - e)
                .filter(e -> elements[e].equals(item))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) elements[index];
    }

    @Override
    public boolean equals(SimpleList<E> otherList) {
        validateElement(otherList);
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        this.elements = new String[CAPACITY];
        size = 0;
    }

    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(elements, size);
    }

    private void resizeArray() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, elements.length + CAPACITY);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException(
                    String.format(IndexNotFoundException.TEMPLATE_MESSAGE, index));
        }
    }

    private void validateElement(Object item) {
        if (item == null) {
            throw new ParamNullException();
        }
    }
}
