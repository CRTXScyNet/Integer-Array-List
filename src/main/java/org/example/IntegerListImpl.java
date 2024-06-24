package org.example;

import org.example.integerListException.IllegalIndexException;
import org.example.integerListException.IntegerNotFoundException;
import org.example.integerListException.ItemIsNullException;
import org.example.integerListInterface.IntegerList;

public class IntegerListImpl implements IntegerList {
    private Integer[] values;
    private int size;
    private boolean isEmpty = true;

    public IntegerListImpl(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.values = new Integer[initialCapacity];
            size = 0;
        } else {
            throw new IllegalArgumentException("Недопустимый размер: " + initialCapacity);
        }
    }

    public IntegerListImpl() {
        this.values = new Integer[0];
        size = 0;
    }

    @Override
    public Integer add(Integer item) {
        checkNullPointerExceptionException(item);
        if (isBoundsAreReached()) {
            values = newArray(true);
        }
        values[size] = item;
        size++;
        isEmpty = false;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIndexOutOfBoundsException(index);
        checkNullPointerExceptionException(item);

        shiftArray(index, true);
        values[index] = item;
        size++;
        isEmpty = false;

        return item;
    }

    @Override
    public Integer set(int index, Integer item) {


        checkNullPointerExceptionException(item);

        Integer previous = Integer.MIN_VALUE;
        previous = values[index];
        values[index] = item;

        return previous;
    }

    @Override
    public Integer remove(Integer item) {
        boolean isExist = false;
        checkNullPointerExceptionException(item);
        Integer previous = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (values[i].equals(item)) {
                previous = values[i];
                shiftArray(i, false);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            throw new IntegerNotFoundException("Такой строки в листе нет");
        }
        size--;

        if (size == 0) {
            isEmpty = true;
        }
        return previous;
    }

    @Override
    public Integer remove(int index) {
        checkIndexOutOfBoundsException(index);
        Integer s = values[index];
        shiftArray(index, false);
        size--;
        if (size == 0) {
            isEmpty = true;
        }
        return s;
    }

    @Override
    public boolean contains(Integer item) {
        checkNullPointerExceptionException(item);
        sortInsertion();
        return binarySearch(item);
    }

    private boolean binarySearch(Integer element) {
        int min = 0;
        int max = values.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == values[mid]) {
                return true;
            }

            if (element < values[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (values[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndexOutOfBoundsException(index);
        return values[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        checkNullPointerExceptionException(otherList);
        if (otherList.size() != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!otherList.get(i).equals(values[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void clear() {
        size = 0;
        values = new Integer[0];
        isEmpty = true;
    }

    @Override
    public Integer[] toArray() {
        Integer[] newVal = new Integer[size];
        for (int i = 0; i < size; i++) {
            newVal[i] = values[i];
        }
        return newVal;
    }


    private void sortSelection() {
        for (int i = 0; i < size; i++) {
            int minIdx = i;
            for (int j = i; j < size; j++) {
                if (values[j] <
                        values[minIdx]) {
                    minIdx = j;
                }
            }
            swap(i, minIdx);
        }
    }

    private void sortBubble() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (values[j] > values[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    private void sortInsertion() {
        for (int i = 1; i < size; i++) {
            int temp = values[i];
            int j = i;
            while (j > 0 && values[j - 1] >= temp) {
                values[j] = values[j - 1];
                j--;
            }
            values[j] = temp;
        }
    }

    private void swap(int fIdx, int sIdx) {
        Integer temp = values[fIdx];
        values[fIdx] = values[sIdx];
        values[sIdx] = temp;
    }

    //custom methods
    private boolean isBoundsAreReached() {
        return values.length <= size;
    }

    private Integer[] newArray(boolean expand) {
        Integer[] newVal;

        if (expand) {
            newVal = new Integer[values.length + 1];
        } else {
            newVal = new Integer[values.length];
        }
        for (int i = 0; i < size; i++) {
            newVal[i] = values[i];
        }
        return newVal;
    }

    private void shiftArray(int index, boolean right) {
        Integer[] newVal;

        if (right) {
            if (isBoundsAreReached()) {
                values = newArray(true);
            } else {
                values = newArray(false);
            }
            for (int i = values.length - 1; i > index; i--) {
                values[i] = values[i - 1];
            }
        } else {
            values = newArray(false);
            for (int i = index; i < values.length - 1; i++) {
                values[i] = values[i + 1];
            }
        }
    }

    private void checkNullPointerExceptionException(Object item) {
        if (item == null) {
            throw new ItemIsNullException("Переданный в метод параметр равен 'null'");
        }
    }

    private void checkIndexOutOfBoundsException(int index) {
        if (index >= size || index < 0) {
            throw new IllegalIndexException("Недопустимый индекс '" + index + "' для размера '" + size + "'");
        }
    }

    static public void print(IntegerList list) {
        System.out.print("[ ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    public static IntegerListImpl generateRandomList(int i) {
        IntegerListImpl integerList = new IntegerListImpl();
        for (int j = 0; j < i; j++) {
            integerList.add((int) (Math.random() * 100) - 50);
        }
        return integerList;
    }

    static void checkTime(int i) {
        IntegerListImpl integerList = IntegerListImpl.generateRandomList(10000);

//        IntegerListImpl.print(integerList);
        long start = System.currentTimeMillis();
        if (i == 0) {
            integerList.sortSelection();
        } else if (i == 1) {
            integerList.sortBubble();
        } else {
            integerList.sortInsertion();
        }
        //ваш_метод_сортировки(arr);
        System.out.println("Method " + i + " takes " + (System.currentTimeMillis() - start) + " ms");
//        IntegerListImpl.print(integerList);
    }
}
