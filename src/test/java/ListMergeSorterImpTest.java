import list_merge_sorter.ListMergeSorterImp;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListMergeSorterImpTest {
    @Test
    public void test1() {
        ListMergeSorterImp<Integer> sorter = new ListMergeSorterImp<>();

        List<Integer> listToSort = Arrays.asList(5, 2, 8, -100, 180, 1, 0);
        List<Integer> expected = Arrays.asList(-100, 0, 1, 2, 5, 8, 180);

        sorter.setComparator(Integer::compareTo);
        sorter.sort(listToSort);

        assertEquals(expected, listToSort);
    }

    @Test
    public void test2() {
        ListMergeSorterImp<Integer> sorter = new ListMergeSorterImp<>();

        List<Integer> listToSort = Arrays.asList(5, 2, 8, -100, 180, 1, 0);
        List<Integer> expected = Arrays.asList(180, 8, 5, 2, 1, 0, -100);

        sorter.setComparator((x, y) -> - Integer.compare(x, y));
        sorter.sort(listToSort);

        assertEquals(expected, listToSort);
    }

    @Test
    public void test3() {
        ListMergeSorterImp<String> sorter = new ListMergeSorterImp<>();

        List<String> listToSort = Arrays.asList("aca", "aab", "fly", "rabbit", "zzz", "dictionary", "love");
        List<String> expected = Arrays.asList("aab", "aca", "dictionary", "fly", "love", "rabbit", "zzz");

        sorter.setComparator(String::compareTo);
        sorter.sort(listToSort);

        assertEquals(expected, listToSort);
    }
}