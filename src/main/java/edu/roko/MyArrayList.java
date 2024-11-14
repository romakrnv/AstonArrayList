package edu.roko;

import java.util.Arrays;
import java.util.Comparator;

/**
 * реализация ArrayList
 * Обеспечивает базовую функциональность для добавления, удаления, получения,
 * очистки и сортировки элементов.
 */
public class MyArrayList<T> {
    private Object[] elements;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Создает пустой список с начальной емкостью = 10.
     */
    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент по определенному индексу.
     *
     * @param index   индекс, по которому будет добавлен элемент
     * @param element элемент для добавления
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Получает элемент по определенному индексу.
     *
     * @param index индекс элемента
     * @return элемент, находящийся на указанной позиции в этом списке
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по определенному индексу.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        T removedElement = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Очищает список.
     */
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Сортирует список, используя алгоритм быстрой сортировки.
     *
     * @param comparator компаратор, определяет порядок элементов в списке
     */
    public void quicksort(Comparator<? super T> comparator) {
        quicksort(0, size - 1, comparator);
    }

    /**
     * Обеспечивает емкость внутреннего массива для добавления новых элементов.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Реализация быстрой сортировки.
     *
     * @param low        нижняя граница диапазона сортировки
     * @param high       верхняя граница диапазона сортировки
     * @param comparator компаратор для сравнения элементов
     */
    private void quicksort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quicksort(low, pivotIndex - 1, comparator);
            quicksort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Разделяет массив для быстрой сортировки и возвращает индекс опорного элемента.
     *
     * @param low        нижняя граница раздела
     * @param high       верхняя граница раздела
     * @param comparator компаратор для сравнения элементов
     * @return индекс опорного элемента
     */

    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве.
     *
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
