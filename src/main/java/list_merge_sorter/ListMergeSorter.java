package list_merge_sorter;

import java.util.Comparator;
import java.util.List;

public interface ListMergeSorter<T> {

    void setComparator(Comparator<T> comparator);
    void sort(List<T> listToSort);

}
