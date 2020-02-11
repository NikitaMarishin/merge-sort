package operations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListMergeSorter<T> {

    private Comparator<T> comparator = (x, y) -> 0;

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sort(List<T> listToSort) {
        mergeSort(listToSort, 0, listToSort.size());
    }

    private void mergeSort(List<T> list, int leftIndex, int rightIndex) {
        if (rightIndex <= leftIndex + 1) {
            return;
        }
        int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
        mergeSort(list, leftIndex, midIndex);
        mergeSort(list, midIndex, rightIndex);

        merge(list, leftIndex, midIndex, rightIndex);
    }

    private void merge(List<T> list, int leftIndex, int midIndex, int rightIndex) {
        int i = leftIndex;
        int j = midIndex;

        List<T> temp = new ArrayList<>();

        while (i < midIndex && j < rightIndex) {
            if (compare(list.get(i), list.get(j))) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }

        for(; i < midIndex; i++) {
            temp.add(list.get(i));
        }

        for (; j < rightIndex; j++) {
            temp.add((list.get(j)));
        }

        for(int k = leftIndex; k < rightIndex; k++) {
            list.set(k, temp.get(k - leftIndex));
        }

    }

    private boolean compare(T var1, T var2) {
        return comparator.compare(var1, var2) <= 0;
    }

}
